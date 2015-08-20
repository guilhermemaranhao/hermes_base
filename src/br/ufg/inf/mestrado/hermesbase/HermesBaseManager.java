package br.ufg.inf.mestrado.hermesbase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.UUID;

import br.ufg.inf.mestrado.hermesbase.coreDX.configuracao.ConfiguracaoType;
import br.ufg.inf.mestrado.hermesbase.coreDX.configuracao.ConfiguracaoTypeDataWriter;
import br.ufg.inf.mestrado.hermesbase.coreDX.configuracao.ConfiguracaoTypeTypeSupport;
import br.ufg.inf.mestrado.hermesbase.coreDX.filtering.FilteringType;
import br.ufg.inf.mestrado.hermesbase.coreDX.filtering.FilteringTypeDataWriter;
import br.ufg.inf.mestrado.hermesbase.coreDX.filtering.FilteringTypeTypeSupport;
import br.ufg.inf.mestrado.hermesbase.coreDX.notificacao.NotificacaoType;
import br.ufg.inf.mestrado.hermesbase.coreDX.notificacao.NotificacaoTypeDataWriter;
import br.ufg.inf.mestrado.hermesbase.coreDX.notificacao.NotificacaoTypeTypeSupport;
import br.ufg.inf.mestrado.hermesbase.factory.PublisherFactory;
import br.ufg.inf.mestrado.hermesbase.factory.SubscriberFactory;
import br.ufg.inf.mestrado.hermesbase.listeners.ComponenteConfiguracaoListener;
import br.ufg.inf.mestrado.hermesbase.listeners.ComponenteFilteringListener;
import br.ufg.inf.mestrado.hermesbase.listeners.ComponenteNotificacaoListener;
import br.ufg.inf.mestrado.hermesbase.listeners.HermesConfiguracaoListener;
import br.ufg.inf.mestrado.hermesbase.listeners.HermesFilteringListener;
import br.ufg.inf.mestrado.hermesbase.listeners.HermesNotificacaoListener;
import br.ufg.inf.mestrado.hermesbase.utils.Constantes;

import com.hp.hpl.jena.tdb.store.Hash;
import com.toc.coredx.DDS.DDS;
import com.toc.coredx.DDS.DataReader;
import com.toc.coredx.DDS.DataReaderQos;
import com.toc.coredx.DDS.DataWriter;
import com.toc.coredx.DDS.DataWriterQos;
import com.toc.coredx.DDS.DiscoveryQosPolicy;
import com.toc.coredx.DDS.DomainParticipant;
import com.toc.coredx.DDS.DomainParticipantFactory;
import com.toc.coredx.DDS.DomainParticipantQos;
import com.toc.coredx.DDS.DurabilityQosPolicyKind;
import com.toc.coredx.DDS.Duration_t;
import com.toc.coredx.DDS.HistoryQosPolicyKind;
import com.toc.coredx.DDS.InstanceHandle_t;
import com.toc.coredx.DDS.OwnershipQosPolicyKind;
import com.toc.coredx.DDS.Publisher;
import com.toc.coredx.DDS.ReliabilityQosPolicyKind;
import com.toc.coredx.DDS.ReturnCode_t;
import com.toc.coredx.DDS.Subscriber;
import com.toc.coredx.DDS.Topic;
import com.toc.coredx.DDS.TopicDescription;
import com.toc.coredx.DDS.coredx;

/**
 * Classe central no HB, pois exp�e os m�todos para cria��o de t�picos, publicadores e assinantes. Ser� utilizado o termo 'participante' para identificador tanto 
 * publicadores quanto assinantes em DDS.
 * Todos os participantes em DDS devem criar localmente os t�picos com os quais interagir�o. N�o h� um servidor de t�picos em que um participante possa acessar
 * os t�picos. Ele precisa t�-los localmente em sua m�quina de execu��o.
 * Cada t�pico em DDS est� associado a uma estrutura DDL que descreve os dados suportados pelo t�pico, e que ser�o trafegados na comunica��o publicador/assinante.
 * Assim, para cada DDL, o vendor DDS cria classes espec�ficas para manipula��o dessa estrutura, por exemplo, para o DDL notificacao, o vendor coreDX gera as classes
 * NotificacaoType.java, NotificacaoTypeDataReader.java, NotificacaoTypeDataWriter.java, NotificacaoTypeSeq.java, NotificacaoTypeTypeSupport.java
 * Informa��es detalhadas sobre DDS podem ser obtidas no tutorial: DDS_to_Real_Time_Systems.pdf na pasta doc desse projeto. Documenta��o sobre o vendor coredx podem ser
 * obtidas pelos links: http://www.coredx.com/documents/CoreDX_DDS_Programmers_Guide_v3.6.pdf e http://www.coredx.com/documents/refman_html_3.6.8/CoreDX_DDS_Java_Reference_3.6.8/index.html
 * @author guilhermemaranhao
 */
public class HermesBaseManager {
	
	DomainParticipant dominio_participante       = null;
	
	/**
	 * O construtor instancia um domain participant, objeto central na comunica��o via DDS pois se refere ao dom�nio pelo qual os publicadores e assinantes
	 * estabelecer�o a sua comunica��o. Uma ambiente DDS pode conter v�rios domain participants, entretanto, o HB s� instancia um para comunica��o em Hermes.
	 * Um domain participant � identificado por uma valor num�rico, que no caso de Hermes � a chave 123.
	 */
	public HermesBaseManager(){
		DomainParticipantFactory dpf = DomainParticipantFactory.get_instance();
		dominio_participante = dpf.lookup_participant(123);
		
		if (dominio_participante == null)
		{
			DomainParticipantQos dq_os = new DomainParticipantQos();
			DiscoveryQosPolicy dqospol = new DiscoveryQosPolicy();
			Duration_t dt = new Duration_t(10, 5);
			dqospol.dpd_push_period = dt;
			dq_os.discovery = dqospol;
			dominio_participante = dpf.create_participant(123, dq_os, null, coredx.getDDS_ALL_STATUS());
			
			createFilteringTopic("createFilter");
		}
	}
	
	/**
	 * Este m�todo cria um t�pico para o DDL notificacao, que � utilizado para comunicar os dados de contexto dos HWs.
	 * 
	 * @param nome_topico nome do t�pico que deseja ser criado.
	 */
	public void createNotificationTopic(String nome_topico)
	{
		NotificacaoTypeTypeSupport notificacaoTypeTypeSupport = new NotificacaoTypeTypeSupport();
		notificacaoTypeTypeSupport.register_type(dominio_participante, "NotificacaoType");
		dominio_participante.create_topic(nome_topico, "NotificacaoType", DDS.TOPIC_QOS_DEFAULT, null, coredx.getDDS_ALL_STATUS());
	}
	
	/**
	 * Este m�todo cria um t�pico para o DDL configuracao, que � utilizado para alterar os itens de configura��o dos componentes.
	 * 
	 * @param nome_topico nome do t�pico que deseja ser criado.
	 */
	public void createConfigurationTopic(String nome_topico)
	{
		ConfiguracaoTypeTypeSupport conSupport = new ConfiguracaoTypeTypeSupport();
		conSupport.register_type(dominio_participante, "ConfiguracaoType");
		dominio_participante.create_topic(nome_topico, "ConfiguracaoType", DDS.TOPIC_QOS_DEFAULT, null, coredx.getDDS_ALL_STATUS());
	}
	
	/**
	 * Este m�todo cria um t�pico para o DDL filtragem, que � utilizado para comunicar os filtros de assinantes ao HI.
	 * 
	 * @param nome_topico nome do t�pico que deseja ser criado.
	 */
	public void createFilteringTopic(String nome_topico)
	{
		FilteringTypeTypeSupport filterSupport = new FilteringTypeTypeSupport();
		filterSupport.register_type(dominio_participante, "FilteringType");
		dominio_participante.create_topic(nome_topico, "FilteringType", DDS.TOPIC_QOS_DEFAULT, null, coredx.getDDS_ALL_STATUS());
	}
	
	/**
	 * M�todo que efetua a assinatura do t�pico de notifica��o. Se � informado um filtro para o t�pico, cria-se uma chave no formato UUID que ser� informada ao HI
	 * para identifica��o do assinante na rede DDS. Essa chave ser� utilizada como a pol�tica de QoS partition e assim, somente o respectivo assinante ser� notificado
	 * quando seu contexto for filtrado. Para isso, obt�m inst�ncia da classe {@link Subscriber}, que � um container de {@link DataReader} e inst�ncia um data reader para o t�pico.
	 * @param nomeTopico nome do t�pico assinado.
	 * @param complementoTopico utilizado como a pol�tica partition QoS. At� o momento n�o � utilizado por nenhum assinante em Hermes.
	 * @param listener Inst�ncia da classe listener do assinante. Deve implementar a interface {@link ComponenteNotificacaoListener}, que ser� invocado por HB por meio do m�todo handleContext(). 
	 * @param filtroJson filtro com os par�metros assinados.
	 */
	public void subscribeNotificationTopic (String nomeTopico, String complementoTopico, ComponenteNotificacaoListener listener, HashMap<String, String> filtroJson) 
	{
		if (filtroJson != null)
		{
			UUID idFiltro = UUID.randomUUID();
			complementoTopico = idFiltro.toString();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutput out = null;
			byte[] filtroArrayBytes = null;
			try{
				out = new ObjectOutputStream(baos);
				out.writeObject(filtroJson);
				filtroArrayBytes = baos.toByteArray();
			}catch(IOException ioex)
			{
				
			}finally{
				try{
					if (out != null)
					{
						out.close();
					}
				}catch (IOException ioex){}
				try{
					baos.close();
				}catch (IOException ioex){
					
				}
			}
			
			publishFiltering("createFilter", "", complementoTopico, nomeTopico, filtroArrayBytes);
		}
		
		Subscriber sub = SubscriberFactory.getInstance(dominio_participante, complementoTopico);
		
		HermesNotificacaoListener hermesListener = new HermesNotificacaoListener();
		hermesListener.setNotificationListener(listener);
		TopicDescription td = dominio_participante.lookup_topicdescription(nomeTopico);
			
		sub.create_datareader(td, getQosDataReader(sub, getQoSAssinante(nomeTopico)), hermesListener, maskDataReader());
	}
	
	/**
	 * M�todo que efetua a assinatura do t�pico de filtragem. Para isso, obt�m inst�ncia da classe {@link Subscriber}, que � um container de {@link DataReader} e inst�ncia um data reader para o t�pico.
	 * @param nomeTopico nome do t�pico de filtragem
	 * @param complementoTopico
	 * @param listener Inst�ncia da classe listener do assinante. Deve implementar a interface {@link ComponenteFilteringListener}, que ser� invocado por HB por meio do m�todo handleContext().
	 */
	public void subscribeFilteringTopico(String nomeTopico, String complementoTopico, ComponenteFilteringListener listener)
	{
		Subscriber sub = SubscriberFactory.getInstance(dominio_participante, complementoTopico);

		HermesFilteringListener hermesListener = new HermesFilteringListener();
		hermesListener.setFilteringListener(listener);
		TopicDescription td = dominio_participante.lookup_topicdescription(nomeTopico);
		
		sub.create_datareader(td, getQosDataReader(sub, getQoSAssinante(nomeTopico)), hermesListener, maskDataReader());
	}
	
	/**
	 * M�todo que efetua a assinatura do t�pico de configura��o. Para isso, obt�m inst�ncia da classe {@link Subscriber}, que � um container de {@link DataReader} e inst�ncia um data reader para o t�pico.
	 * @param nomeTopico nome do t�pico de filtragem
	 * @param complementoTopico
	 * @param listener Inst�ncia da classe listener do assinante. Deve implementar a interface {@link ComponenteConfiguracaoListener}, que ser� invocado por HB por meio do m�todo handleContext().
	 */
	public void subscribeConfigurationTopic(String nome_topico, String complementoTopico, ComponenteConfiguracaoListener listener) 
	{
		Subscriber sub = SubscriberFactory.getInstance(dominio_participante, complementoTopico);
		
		HermesConfiguracaoListener hermesListener = new HermesConfiguracaoListener();
		hermesListener.setConfiguracaoListener(listener);
		TopicDescription td = dominio_participante.lookup_topicdescription(nome_topico);
		
		sub.create_datareader(td, getQosDataReader(sub, getQoSAssinante(nome_topico)), hermesListener, maskDataReader());
	}
	
	/**
	 * Cria um objeto {@link DataWriter} de notifica��o para o t�pico em quest�o. Esse objeto ser� utilizado posteriormente para publica��o de contexto.
	 * @param idEntidade Entidade principal da publica��o. Utilizado apenas para orientar os assinantes sobre o contexto que est� sendo publicado.
	 * @param nomeTopico Nome do t�pico ao qual o publicador est� associado. Utilizado para criar o objeto da classe {@link Topic} que ser� adicionado no {@link DataWriter}
	 * @param complementoTopico Se houver complemento, ser� utilizado como a pol�tica de QoS partition.
	 * @param caminhoOntologia Orienta o assinante sobre o dom�nio de contexto que est� sendo publicado.
	 * @param contexto Principal informa��o entre os argumentos do m�todo. Cont�m o objeto de contexto serializado.
	 * @param tipoSerializacao Utilizado para orientar o assinante no procedimento de deserializa��o do objeto de contexto.
	 * @return sucesso ou n�o da opera��o de cria��o do publicador
	 */
	public boolean publishNotification(String idEntidade, String nomeTopico, String complementoTopico, String caminhoOntologia, byte[] contexto, String tipoSerializacao)
	{
		NotificacaoType notificacao = new NotificacaoType(idEntidade, nomeTopico, complementoTopico, caminhoOntologia, contexto, tipoSerializacao);
		Publisher pub = PublisherFactory.getInstance(dominio_participante, complementoTopico);
		NotificacaoTypeDataWriter ndw = (NotificacaoTypeDataWriter)pub.lookup_datawriter(nomeTopico);
		
		if (ndw == null)
		{
			Topic topico = dominio_participante.find_topic(nomeTopico, new Duration_t(DDS.DURATION_INFINITE_SEC, DDS.DURATION_INFINITE_NSEC));
			ndw = (NotificacaoTypeDataWriter)pub.create_datawriter(topico, getQosDataWriter(pub, getQoSPublicador(nomeTopico)), null, maskDataWriter());
		}
		
		InstanceHandle_t notificacaoHandle = ndw.register_instance(notificacao);
		ReturnCode_t ret = ndw.write(notificacao, notificacaoHandle);
		if (ret != ReturnCode_t.RETCODE_OK)
		{
			System.out.println("Erro em publicar notifica��o de " + idEntidade);
			return false;
		}
		return true;
	}
	
	/**
	 * Cria um objeto {@link DataWriter} de filtragem para o t�pico em quest�o. Esse objeto ser� utilizado posteriormente para publica��o de filtro.
	 * @param nomeTopico nome do t�pico para o qual se deseja criar um publicador.
	 * @param complementoTopico complemento de t�pico, se existente
	 * @param idFiltro identificador do filtro para ser utilizado pelo HI como partition de suas publica��es
	 * @param nomeTopicoParaFiltragem nome do t�pico para o qual o filtro est� associado.
	 * @param filtroJson par�metros de filtros.
	 * @return sucesso ou falha da cria��o de publicador
	 */
	private boolean publishFiltering(String nomeTopico, String complementoTopico, String idFiltro, String nomeTopicoParaFiltragem, byte[] filtroJson)
	{
		FilteringType filtragem = new FilteringType(idFiltro, nomeTopicoParaFiltragem, filtroJson, null);
		Publisher pub = PublisherFactory.getInstance(dominio_participante, complementoTopico);
		FilteringTypeDataWriter fdw = (FilteringTypeDataWriter)pub.lookup_datawriter(nomeTopico);
		
		if (fdw == null)
		{
			Topic topico = dominio_participante.find_topic(nomeTopico, new Duration_t(DDS.DURATION_INFINITE_SEC, DDS.DURATION_INFINITE_NSEC));
			fdw = (FilteringTypeDataWriter)pub.create_datawriter(topico, getQosDataWriter(pub, getQoSPublicador(nomeTopico)), null, maskDataWriter());
		}
		
		InstanceHandle_t filtragemHandle = fdw.register_instance(filtragem);
		ReturnCode_t ret = fdw.write(filtragem, filtragemHandle);
		if (ret != ReturnCode_t.RETCODE_OK)
		{
			System.out.println("Erro em publicar filtragem");
			return false;
		}
		return true;
	}
	
	/**
	 * Cria publicador {@link DataWriter}, para o t�pico de configura��o. At� o momento, utilizado para configurar tipo de infer�ncia para lista de t�picos.
	 * @param nomeTopico nome do t�pico para o qual se deseja criar um publicador.
	 * @param complementoTopico pol�tica de QoS partition, se existir
	 * @param tipoInferencia novo tipo de infer�ncia para t�pico
	 * @param topicos lista de t�picos que ter�o o tipo de infer�ncia configurado.
	 * @return sucesso ou falha da opera��o de cria��o de publicador
	 */
	public boolean publishConfiguration(String nomeTopico, String complementoTopico, String tipoInferencia, String[] topicos)
	{
		ConfiguracaoType configuracaoType = new ConfiguracaoType(tipoInferencia, topicos);
		Publisher pub = PublisherFactory.getInstance(dominio_participante, complementoTopico);
		ConfiguracaoTypeDataWriter cdw = (ConfiguracaoTypeDataWriter)pub.lookup_datawriter(nomeTopico);
		
		if (cdw == null)
		{
			Topic topico = dominio_participante.find_topic(nomeTopico, new Duration_t(DDS.DURATION_INFINITE_SEC, DDS.DURATION_INFINITE_NSEC));
			cdw = (ConfiguracaoTypeDataWriter)pub.create_datawriter(topico, getQosDataWriter(pub, getQoSPublicador(nomeTopico)), null, maskDataWriter());
		}	
		
		InstanceHandle_t notificacaoHandle = cdw.register_instance(configuracaoType);
		ReturnCode_t ret = cdw.write(configuracaoType, notificacaoHandle);
		if (ret != ReturnCode_t.RETCODE_OK)
		{
			System.out.println("Erro em publicar configura��o de " + tipoInferencia);
			return false;
		}
		return true;
		
	}
	
	/**
	 * Retorna pol�ticas de Qos padr�o para publicador.
	 * @param nome_topico
	 * @return objeto {@link HashMap} com pol�ticas e valores
	 */
	private HashMap<String, Object> getQoSPublicador(String nome_topico)
	{
		//Per�odo m�ximo em que o publicador se compromete a publicar algo. Deadline pode ser parametrizado com o valor da frequencia de leitura de sensor.
		//-1 significa tempo infinito, ou seja, n�o h� deadline.
		//float deadline = -1.0f;
		
		//M�ximo atraso aceit�vel desde o tempo que o dado � escrito at� o momento que � inserido no assinante. � apenas uma sugest�o para o middleware, nada que
		//ser� for�ado.
		float latency = 1.0f;
		int history_depth = 1;
		DurabilityQosPolicyKind durability = DurabilityQosPolicyKind.TRANSIENT_LOCAL_DURABILITY_QOS;
		ReliabilityQosPolicyKind reliability = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
		HistoryQosPolicyKind history = HistoryQosPolicyKind.KEEP_ALL_HISTORY_QOS;
		
		OwnershipQosPolicyKind ownership = OwnershipQosPolicyKind.SHARED_OWNERSHIP_QOS;
		
		int ownership_strength = 0;
		
		HashMap<String, Object> topicosQoS = new HashMap<>();
		//topicosQoS.put(Constantes.QoS_deadline, deadline);
		topicosQoS.put(Constantes.QoS_latency, latency);
		topicosQoS.put(Constantes.QoS_history_depth, history_depth);
		topicosQoS.put(Constantes.QoS_durability, durability);
		topicosQoS.put(Constantes.QoS_reliability, reliability);
		topicosQoS.put(Constantes.QoS_history, history);
		topicosQoS.put(Constantes.QoS_ownership, ownership);
		topicosQoS.put(Constantes.QoS_ownership_strength, ownership_strength);
		
		return topicosQoS;
	}
	
	/**
	 * Retorna pol�ticas de Qos padr�o para assinante.
	 * @param nome_topico
	 * @return objeto {@link HashMap} com pol�ticas e valores
	 */
	private HashMap<String, Object> getQoSAssinante(String nome_topico)
	{
		//Per�odo m�ximo que o assinante deseja ser notificado. Deadline pode ser parametrizado com o valor da frequencia de leitura de sensor.
		//-1 significa tempo infinito, ou seja, n�o h� deadline.
		//float deadline = -1.0f;
		
		//M�ximo atraso aceit�vel desde o tempo que o dado � escrito at� o momento que � inserido no assinante. � apenas uma sugest�o para o middleware, nada que
		//ser� for�ado.
		float latency = 1.0f;
		int history_depth = 1;
		
		//Per�odo m�nimo em que o assinante deseja ser notificado com apenas UMA atualiza��o.
		//int time_based_filter = 1;
		DurabilityQosPolicyKind durability = DurabilityQosPolicyKind.TRANSIENT_LOCAL_DURABILITY_QOS;
		ReliabilityQosPolicyKind reliability = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
		HistoryQosPolicyKind history = HistoryQosPolicyKind.KEEP_ALL_HISTORY_QOS;
		
		OwnershipQosPolicyKind ownership = OwnershipQosPolicyKind.SHARED_OWNERSHIP_QOS;
		int ownership_strength = 0;
		
		HashMap<String, Object> topicosQoS = new HashMap<>();
		//topicosQoS.put(Constantes.QoS_deadline, deadline);
		topicosQoS.put(Constantes.QoS_latency, latency);
		//topicosQoS.put(Constantes.QoS_time_based_filter, time_based_filter);
		topicosQoS.put(Constantes.QoS_history_depth, history_depth);
		topicosQoS.put(Constantes.QoS_durability, durability);
		topicosQoS.put(Constantes.QoS_reliability, reliability);
		topicosQoS.put(Constantes.QoS_history, history);
		topicosQoS.put(Constantes.QoS_ownership, ownership);
		topicosQoS.put(Constantes.QoS_ownership_strength, ownership_strength);
		
		return topicosQoS;
	}
	
	/**
	 * Cria objeto da API , {@link DataWriterQos} com valores est�ticos de QoS.
	 * @param nome_topico
	 * @return objeto {@link DataWriterQos}
	 */
	private static DataWriterQos getQosDataWriter(Publisher pub, HashMap<String, Object> topicoQoS)
		{	
			DataWriterQos dwQos = new DataWriterQos();
			
			pub.get_default_datawriter_qos(dwQos);
			//float deadline = (float)topicoQoS.get(Constantes.QoS_deadline);
//			if (deadline != -1.0) {
//				dwQos.deadline.period.sec     = (int)deadline;
//				dwQos.deadline.period.nanosec = (long)((deadline - (int)deadline) * 1000000000);
//			}
			
			float latency = (float)topicoQoS.get(Constantes.QoS_latency);
			dwQos.latency_budget.duration.sec      = (int)latency;
			dwQos.latency_budget.duration.nanosec  = (long)((latency - (int)latency) * 1000000000);
			
			HistoryQosPolicyKind history = (HistoryQosPolicyKind)topicoQoS.get(Constantes.QoS_history);
			dwQos.history.kind = history;
			if (history == HistoryQosPolicyKind.KEEP_LAST_HISTORY_QOS) 
				dwQos.history.depth = (int)topicoQoS.get(Constantes.QoS_history_depth);
			dwQos.reliability.kind = (ReliabilityQosPolicyKind)topicoQoS.get(Constantes.QoS_reliability);
			dwQos.durability.kind  = (DurabilityQosPolicyKind)topicoQoS.get(Constantes.QoS_durability);
			dwQos.ownership.kind   = (OwnershipQosPolicyKind)topicoQoS.get(Constantes.QoS_ownership);
			dwQos.ownership_strength.value = (int)topicoQoS.get(Constantes.QoS_ownership_strength);
			dwQos.writer_data_lifecycle.autodispose_unregistered_instances = true;
			
			return dwQos;
		}
		
	/**
	 * Cria objeto da API , {@link DataReaderQos} com valores est�ticos de QoS.
	 * @param nome_topico
	 * @return objeto {@link DataReaderQos}
	 */
	private static DataReaderQos getQosDataReader(Subscriber sub, HashMap<String, Object> topicoQos)
	{	
		DataReaderQos drQos = new DataReaderQos();
		sub.get_default_datareader_qos(drQos);
//		float deadline = (float)topicoQos.get(Constantes.QoS_deadline);
//		if (deadline != -1.0) {
//			drQos.deadline.period.sec     = (int)deadline;
//			drQos.deadline.period.nanosec = (long)((deadline - (int)deadline) * 1000000000);
//		}
		float latency = (float)topicoQos.get(Constantes.QoS_latency);
		drQos.latency_budget.duration.sec      = (int)latency;
		drQos.latency_budget.duration.nanosec  = (long)((latency - (int)latency) * 1000000000);
		
		HistoryQosPolicyKind history = (HistoryQosPolicyKind)topicoQos.get(Constantes.QoS_history);
		drQos.history.kind = history;
		if (history == HistoryQosPolicyKind.KEEP_LAST_HISTORY_QOS) 
			drQos.history.depth = (int)topicoQos.get(Constantes.QoS_history_depth);
		drQos.reliability.kind = (ReliabilityQosPolicyKind)topicoQos.get(Constantes.QoS_reliability);
		drQos.durability.kind  = (DurabilityQosPolicyKind)topicoQos.get(Constantes.QoS_durability);
		drQos.ownership.kind   = (OwnershipQosPolicyKind)topicoQos.get(Constantes.QoS_ownership);
		
//		int time_based_filter = (int)topicoQos.get(Constantes.QoS_time_based_filter);
//		drQos.time_based_filter.minimum_separation.sec = (int)time_based_filter;
//		drQos.time_based_filter.minimum_separation.nanosec = (int)((time_based_filter - (int)time_based_filter)*1000000000);
		
		return drQos;
	}
	
		private static long maskDataWriter()
		{
			//Aqui se define os m�todos do DataWriter Listener que ser�o executados.
			return DDS.PUBLICATION_MATCHED_STATUS|
					DDS.LIVELINESS_LOST_STATUS |
					DDS.OFFERED_DEADLINE_MISSED_STATUS |
					DDS.OFFERED_INCOMPATIBLE_QOS_STATUS;
		}
		
		private static long maskDataReader()
		{
			return coredx.getDDS_ALL_STATUS();
		}
	
	}
