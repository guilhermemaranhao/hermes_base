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
 * Classe central no HB, pois expõe os métodos para criação de tópicos, publicadores e assinantes. Será utilizado o termo 'participante' para identificador tanto 
 * publicadores quanto assinantes em DDS.
 * Todos os participantes em DDS devem criar localmente os tópicos com os quais interagirão. Não há um servidor de tópicos em que um participante possa acessar
 * os tópicos. Ele precisa tê-los localmente em sua máquina de execução.
 * Cada tópico em DDS está associado a uma estrutura DDL que descreve os dados suportados pelo tópico, e que serão trafegados na comunicação publicador/assinante.
 * Assim, para cada DDL, o vendor DDS cria classes específicas para manipulação dessa estrutura, por exemplo, para o DDL notificacao, o vendor coreDX gera as classes
 * NotificacaoType.java, NotificacaoTypeDataReader.java, NotificacaoTypeDataWriter.java, NotificacaoTypeSeq.java, NotificacaoTypeTypeSupport.java
 * Informações detalhadas sobre DDS podem ser obtidas no tutorial: DDS_to_Real_Time_Systems.pdf na pasta doc desse projeto. Documentação sobre o vendor coredx podem ser
 * obtidas pelos links: http://www.coredx.com/documents/CoreDX_DDS_Programmers_Guide_v3.6.pdf e http://www.coredx.com/documents/refman_html_3.6.8/CoreDX_DDS_Java_Reference_3.6.8/index.html
 * @author guilhermemaranhao
 */
public class HermesBaseManager {
	
	DomainParticipant dominio_participante       = null;
	
	/**
	 * O construtor instancia um domain participant, objeto central na comunicação via DDS pois se refere ao domínio pelo qual os publicadores e assinantes
	 * estabelecerão a sua comunicação. Uma ambiente DDS pode conter vários domain participants, entretanto, o HB só instancia um para comunicação em Hermes.
	 * Um domain participant é identificado por uma valor numérico, que no caso de Hermes é a chave 123.
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
	 * Este método cria um tópico para o DDL notificacao, que é utilizado para comunicar os dados de contexto dos HWs.
	 * 
	 * @param nome_topico nome do tópico que deseja ser criado.
	 */
	public void createNotificationTopic(String nome_topico)
	{
		NotificacaoTypeTypeSupport notificacaoTypeTypeSupport = new NotificacaoTypeTypeSupport();
		notificacaoTypeTypeSupport.register_type(dominio_participante, "NotificacaoType");
		dominio_participante.create_topic(nome_topico, "NotificacaoType", DDS.TOPIC_QOS_DEFAULT, null, coredx.getDDS_ALL_STATUS());
	}
	
	/**
	 * Este método cria um tópico para o DDL configuracao, que é utilizado para alterar os itens de configuração dos componentes.
	 * 
	 * @param nome_topico nome do tópico que deseja ser criado.
	 */
	public void createConfigurationTopic(String nome_topico)
	{
		ConfiguracaoTypeTypeSupport conSupport = new ConfiguracaoTypeTypeSupport();
		conSupport.register_type(dominio_participante, "ConfiguracaoType");
		dominio_participante.create_topic(nome_topico, "ConfiguracaoType", DDS.TOPIC_QOS_DEFAULT, null, coredx.getDDS_ALL_STATUS());
	}
	
	/**
	 * Este método cria um tópico para o DDL filtragem, que é utilizado para comunicar os filtros de assinantes ao HI.
	 * 
	 * @param nome_topico nome do tópico que deseja ser criado.
	 */
	public void createFilteringTopic(String nome_topico)
	{
		FilteringTypeTypeSupport filterSupport = new FilteringTypeTypeSupport();
		filterSupport.register_type(dominio_participante, "FilteringType");
		dominio_participante.create_topic(nome_topico, "FilteringType", DDS.TOPIC_QOS_DEFAULT, null, coredx.getDDS_ALL_STATUS());
	}
	
	/**
	 * Método que efetua a assinatura do tópico de notificação. Se é informado um filtro para o tópico, cria-se uma chave no formato UUID que será informada ao HI
	 * para identificação do assinante na rede DDS. Essa chave será utilizada como a política de QoS partition e assim, somente o respectivo assinante será notificado
	 * quando seu contexto for filtrado. Para isso, obtém instância da classe {@link Subscriber}, que é um container de {@link DataReader} e instância um data reader para o tópico.
	 * @param nomeTopico nome do tópico assinado.
	 * @param complementoTopico utilizado como a política partition QoS. Até o momento não é utilizado por nenhum assinante em Hermes.
	 * @param listener Instância da classe listener do assinante. Deve implementar a interface {@link ComponenteNotificacaoListener}, que será invocado por HB por meio do método handleContext(). 
	 * @param filtroJson filtro com os parâmetros assinados.
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
	 * Método que efetua a assinatura do tópico de filtragem. Para isso, obtém instância da classe {@link Subscriber}, que é um container de {@link DataReader} e instância um data reader para o tópico.
	 * @param nomeTopico nome do tópico de filtragem
	 * @param complementoTopico
	 * @param listener Instância da classe listener do assinante. Deve implementar a interface {@link ComponenteFilteringListener}, que será invocado por HB por meio do método handleContext().
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
	 * Método que efetua a assinatura do tópico de configuração. Para isso, obtém instância da classe {@link Subscriber}, que é um container de {@link DataReader} e instância um data reader para o tópico.
	 * @param nomeTopico nome do tópico de filtragem
	 * @param complementoTopico
	 * @param listener Instância da classe listener do assinante. Deve implementar a interface {@link ComponenteConfiguracaoListener}, que será invocado por HB por meio do método handleContext().
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
	 * Cria um objeto {@link DataWriter} de notificação para o tópico em questão. Esse objeto será utilizado posteriormente para publicação de contexto.
	 * @param idEntidade Entidade principal da publicação. Utilizado apenas para orientar os assinantes sobre o contexto que está sendo publicado.
	 * @param nomeTopico Nome do tópico ao qual o publicador está associado. Utilizado para criar o objeto da classe {@link Topic} que será adicionado no {@link DataWriter}
	 * @param complementoTopico Se houver complemento, será utilizado como a política de QoS partition.
	 * @param caminhoOntologia Orienta o assinante sobre o domínio de contexto que está sendo publicado.
	 * @param contexto Principal informação entre os argumentos do método. Contém o objeto de contexto serializado.
	 * @param tipoSerializacao Utilizado para orientar o assinante no procedimento de deserialização do objeto de contexto.
	 * @return sucesso ou não da operação de criação do publicador
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
			System.out.println("Erro em publicar notificação de " + idEntidade);
			return false;
		}
		return true;
	}
	
	/**
	 * Cria um objeto {@link DataWriter} de filtragem para o tópico em questão. Esse objeto será utilizado posteriormente para publicação de filtro.
	 * @param nomeTopico nome do tópico para o qual se deseja criar um publicador.
	 * @param complementoTopico complemento de tópico, se existente
	 * @param idFiltro identificador do filtro para ser utilizado pelo HI como partition de suas publicações
	 * @param nomeTopicoParaFiltragem nome do tópico para o qual o filtro está associado.
	 * @param filtroJson parâmetros de filtros.
	 * @return sucesso ou falha da criação de publicador
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
	 * Cria publicador {@link DataWriter}, para o tópico de configuração. Até o momento, utilizado para configurar tipo de inferência para lista de tópicos.
	 * @param nomeTopico nome do tópico para o qual se deseja criar um publicador.
	 * @param complementoTopico política de QoS partition, se existir
	 * @param tipoInferencia novo tipo de inferência para tópico
	 * @param topicos lista de tópicos que terão o tipo de inferência configurado.
	 * @return sucesso ou falha da operação de criação de publicador
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
			System.out.println("Erro em publicar configuração de " + tipoInferencia);
			return false;
		}
		return true;
		
	}
	
	/**
	 * Retorna políticas de Qos padrão para publicador.
	 * @param nome_topico
	 * @return objeto {@link HashMap} com políticas e valores
	 */
	private HashMap<String, Object> getQoSPublicador(String nome_topico)
	{
		//Período máximo em que o publicador se compromete a publicar algo. Deadline pode ser parametrizado com o valor da frequencia de leitura de sensor.
		//-1 significa tempo infinito, ou seja, não há deadline.
		//float deadline = -1.0f;
		
		//Máximo atraso aceitável desde o tempo que o dado é escrito até o momento que é inserido no assinante. É apenas uma sugestão para o middleware, nada que
		//será forçado.
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
	 * Retorna políticas de Qos padrão para assinante.
	 * @param nome_topico
	 * @return objeto {@link HashMap} com políticas e valores
	 */
	private HashMap<String, Object> getQoSAssinante(String nome_topico)
	{
		//Período máximo que o assinante deseja ser notificado. Deadline pode ser parametrizado com o valor da frequencia de leitura de sensor.
		//-1 significa tempo infinito, ou seja, não há deadline.
		//float deadline = -1.0f;
		
		//Máximo atraso aceitável desde o tempo que o dado é escrito até o momento que é inserido no assinante. É apenas uma sugestão para o middleware, nada que
		//será forçado.
		float latency = 1.0f;
		int history_depth = 1;
		
		//Período mínimo em que o assinante deseja ser notificado com apenas UMA atualização.
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
	 * Cria objeto da API , {@link DataWriterQos} com valores estáticos de QoS.
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
	 * Cria objeto da API , {@link DataReaderQos} com valores estáticos de QoS.
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
			//Aqui se define os métodos do DataWriter Listener que serão executados.
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
