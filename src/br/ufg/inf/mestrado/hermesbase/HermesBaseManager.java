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

import com.toc.coredx.DDS.DDS;
import com.toc.coredx.DDS.DataReaderQos;
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

/*
 * Comunica com o Middleware CoreDX para publicação e assinatura de tópicos.
 */
public class HermesBaseManager {
	
	DomainParticipant dominio_participante       = null;
	
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
	
	public void createNotificationTopic(String nome_topico)
	{
		NotificacaoTypeTypeSupport notificacaoTypeTypeSupport = new NotificacaoTypeTypeSupport();
		notificacaoTypeTypeSupport.register_type(dominio_participante, "NotificacaoType");
		dominio_participante.create_topic(nome_topico, "NotificacaoType", DDS.TOPIC_QOS_DEFAULT, null, coredx.getDDS_ALL_STATUS());
	}
	
	public void createConfigurationTopic(String nome_topico)
	{
		ConfiguracaoTypeTypeSupport conSupport = new ConfiguracaoTypeTypeSupport();
		conSupport.register_type(dominio_participante, "ConfiguracaoType");
		dominio_participante.create_topic(nome_topico, "ConfiguracaoType", DDS.TOPIC_QOS_DEFAULT, null, coredx.getDDS_ALL_STATUS());
	}
	
	public void createFilteringTopic(String nome_topico)
	{
		FilteringTypeTypeSupport filterSupport = new FilteringTypeTypeSupport();
		filterSupport.register_type(dominio_participante, "FilteringType");
		dominio_participante.create_topic(nome_topico, "FilteringType", DDS.TOPIC_QOS_DEFAULT, null, coredx.getDDS_ALL_STATUS());
	}
	
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
	
	public void subscribeFilteringTopico(String nomeTopico, String complementoTopico, ComponenteFilteringListener listener)
	{
		Subscriber sub = SubscriberFactory.getInstance(dominio_participante, complementoTopico);

		HermesFilteringListener hermesListener = new HermesFilteringListener();
		hermesListener.setFilteringListener(listener);
		TopicDescription td = dominio_participante.lookup_topicdescription(nomeTopico);
		
		sub.create_datareader(td, getQosDataReader(sub, getQoSAssinante(nomeTopico)), hermesListener, maskDataReader());
	}
	
	public void subscribeConfigurationTopic(String nome_topico, String complementoTopico, ComponenteConfiguracaoListener listener) 
	{
		Subscriber sub = SubscriberFactory.getInstance(dominio_participante, complementoTopico);
		
		HermesConfiguracaoListener hermesListener = new HermesConfiguracaoListener();
		hermesListener.setConfiguracaoListener(listener);
		TopicDescription td = dominio_participante.lookup_topicdescription(nome_topico);
		
		sub.create_datareader(td, getQosDataReader(sub, getQoSAssinante(nome_topico)), hermesListener, maskDataReader());
	}
	
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
