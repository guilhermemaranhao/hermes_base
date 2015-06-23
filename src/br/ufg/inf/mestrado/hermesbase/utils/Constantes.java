package br.ufg.inf.mestrado.hermesbase.utils;

import java.util.HashMap;
import java.util.Map;


public final class Constantes {

	private Constantes()
	{
		
	}
	
	public static final int    DOMINIO_SINAIS_VITAIS = 1;
	
	public static final int    DOMINIO_ALARMES = 2;
	
	public static final String variaveisSWRL = "http://www.w3.org/2003/11/swrl#";
	//public static final String defaultNamespace = "http://www.semanticweb.org/ontologies/2013/1/Ontology1361391792831.owl#";
	public static final String defaultNamespace = "";
	public static final String SOURCE_URL = "http://www.semanticweb.org/ontologies/2013/1/Ontology1361391792831.owl";
	public static final String SOURCE_FILE = "./src/ontologia/msvh.owl";
	public static final String NON_NEGATIVE_INTEGER = "http://www.w3.org/2001/XMLSchema#nonNegativeInteger";
	
	public static final String 		PRESSAO_SANGUINEA			= defaultNamespace + "VSO_0000005";
	public static final String	 	SATURACAO_OXIGENIO_SANGUE	= defaultNamespace + "OxygenSaturationMeasurementDatum";
	public static final String 		FREQUENCIA_PULSO			= defaultNamespace + "VSO_0000030";
	public static final String 		FREQUENCIA_RESPIRATORIA		= defaultNamespace + "VSO_0000035";
	public static final String 		TEMPERATURA					= defaultNamespace + "VSO_0000008";
	
    public static final String PARTITION_SINAL_VITAL = "partitionSinalVital";
	
    public static final String PARTITION_ALARME_PRESSAO_SANGUINEA_HIPERTENSAO_ESTAGIO_1 = defaultNamespace + "Stage1Hypertension";
	public static final String PARTITION_ALARME_PRESSAO_SANGUINEA_HIPERTENSAO_ESTAGIO_2 = defaultNamespace + "Stage2Hypertension";
	public static final String PARTITION_ALARME_PRESSAO_SANGUINEA_HIPERTENSAO_ESTAGIO_3 = defaultNamespace + "Stage3Hypertension";
	public static final String PARTITION_PRESSAO_SANGUINEA_LIMITROPHE = defaultNamespace + "LimitropheBloodPressure";
	
	private static final String[] partitionsPressaoSanguinea = {Constantes.PARTITION_ALARME_PRESSAO_SANGUINEA_HIPERTENSAO_ESTAGIO_1,
		Constantes.PARTITION_ALARME_PRESSAO_SANGUINEA_HIPERTENSAO_ESTAGIO_2,
		Constantes.PARTITION_ALARME_PRESSAO_SANGUINEA_HIPERTENSAO_ESTAGIO_3,
		Constantes.PARTITION_PRESSAO_SANGUINEA_LIMITROPHE
	};
	
	public static final String[] topicosPartitionSinalVital = {Constantes.PRESSAO_SANGUINEA, Constantes.SATURACAO_OXIGENIO_SANGUE, Constantes.FREQUENCIA_PULSO, Constantes.FREQUENCIA_RESPIRATORIA,
		Constantes.TEMPERATURA};
	
	public static final Map<String, String[]> topicosPartitionAlarme;
	static
	{
		topicosPartitionAlarme = new HashMap<String, String[]>();
		topicosPartitionAlarme.put(Constantes.PRESSAO_SANGUINEA, partitionsPressaoSanguinea);
	}
	
	public static final String QoS_deadline = "Qos_deadline";
	public static final String QoS_latency = "QoS_latency";
	public static final String QoS_time_based_filter = "QoS_time_based_filter";
	public static final String QoS_history_depth = "QoS_history_depth";
	public static final String QoS_durability = "QoS_durability";
	public static final String QoS_reliability = "QoS_reliability";
	public static final String QoS_history = "QoS_history";
	public static final String QoS_ownership = "QoS_ownership";
	public static final String QoS_ownership_strength = "QoS_ownership_strength";
	public static final String QoS_transport_priority = "QoS_transport_priority";

	//public static final String NS = defaultNamespace + "#";


}
