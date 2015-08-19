package br.ufg.inf.mestrado.hermesbase.factory;

import java.util.ArrayList;

import com.toc.coredx.DDS.DDS;
import com.toc.coredx.DDS.DataReader;
import com.toc.coredx.DDS.DomainParticipant;
import com.toc.coredx.DDS.ReturnCode_t;
import com.toc.coredx.DDS.Subscriber;
import com.toc.coredx.DDS.SubscriberQos;

/**
 * F�brica que cria inst�ncias de objetos {@link Subscriber} a partir da pol�tica de QoS partition. Os objetos {@link Subscriber} s�o containers de {@link DataReader} no DDS.
 * Se algum {@link Subscriber} j� criado atender a pol�tica partition especificada, ele � retornado. Como a �nica pol�tica de QoS parametrizada por Hermes at� o momento � partition,
 * somente ela foi considerada na avalia��o.
 * @author guilhermemaranhao
 *
 */
public class SubscriberFactory {
	
	private static ArrayList<Subscriber> subscribers = new ArrayList<>();
	
	/**
	 * Retorna uma inst�ncia de {@link Subscriber} que atenda a pol�tica partition informada.
	 * @param domain dom�nio DDS
	 * @param partition pol�tica partition exigida
	 * @return inst�ncia de {@link Subscriber}
	 */
	public static Subscriber getInstance(DomainParticipant domain, String partition)
	{
		
			SubscriberQos sub_qos = new SubscriberQos();	
			//Percorre lista de subscribers para verificar se j� existe algum que atenda ao QoS de partition.
			for (Subscriber sub : subscribers)
			{
				//Atualiza inst�ncia sub_qos com o QoS do Subscriber corrente.
				ReturnCode_t ret = sub.get_qos(sub_qos);
				if (ret == ReturnCode_t.RETCODE_OK)
				{
					//Compara QoS partition.
					if (sub_qos.partition.name.contains(partition))
					{
						return sub;
					}
				}
				sub_qos = new SubscriberQos();
			}
			
			//Cria novo subscriber e novo subscriberQoS e atribui novo QoS;
			sub_qos = new SubscriberQos();
			domain.get_default_subscriber_qos(sub_qos);
			sub_qos.partition.name.add(partition);
			Subscriber novo_sub = domain.create_subscriber(sub_qos, null, DDS.ALL_STATUS);
			novo_sub.set_qos(sub_qos);
			subscribers.add(novo_sub);
			
			return novo_sub;
	}

}
