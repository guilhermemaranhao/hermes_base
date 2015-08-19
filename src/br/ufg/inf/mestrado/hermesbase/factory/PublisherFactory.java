package br.ufg.inf.mestrado.hermesbase.factory;

import java.util.ArrayList;

import com.toc.coredx.DDS.DDS;
import com.toc.coredx.DDS.DataWriter;
import com.toc.coredx.DDS.DomainParticipant;
import com.toc.coredx.DDS.Publisher;
import com.toc.coredx.DDS.PublisherQos;
import com.toc.coredx.DDS.ReturnCode_t;

/**
 * F�brica que cria inst�ncias de objetos {@link Publisher} a partir da pol�tica de QoS partition. Os objetos {@link Publisher} s�o containers de {@link DataWriter} no DDS.
 * Se algum {@link Publisher} j� criado atender a pol�tica partition especificada, ele � retornado. Como a �nica pol�tica de QoS parametrizada por Hermes at� o momento � partition,
 * somente ela foi considerada na avalia��o.
 * @author guilhermemaranhao
 *
 */
public class PublisherFactory {

	private static ArrayList<Publisher> publishers = new ArrayList<>();
	
	/**
	 * Retorna uma inst�ncia de {@link Publisher} que atenda a pol�tica partition informada.
	 * @param domain dom�nio DDS
	 * @param partition pol�tica partition exigida
	 * @return inst�ncia de {@link Publisher}
	 */
	public static Publisher getInstance(DomainParticipant domain, String partition)
	{
		
			PublisherQos pub_qos = new PublisherQos();	
			//Percorre lista de publishers para verificar se j� existe algum que atenda ao QoS de partition.
			for (Publisher pub : publishers)
			{
				//Atualiza inst�ncia pub_qos com o QoS do publisher corrente.
				ReturnCode_t ret = pub.get_qos(pub_qos);
				if (ret == ReturnCode_t.RETCODE_OK)
				{
					//Compara QoS partition.
					if (pub_qos.partition.name.contains(partition))
					{
						return pub;
					}
				}
				pub_qos = new PublisherQos();
			}
			
			//Cria novo publiser e novo publisherQoS e atribui novo QoS;
			pub_qos = new PublisherQos();
			domain.get_default_publisher_qos(pub_qos);
			pub_qos.partition.name.add(partition);
			Publisher novo_pub = domain.create_publisher(pub_qos, null, DDS.ALL_STATUS);
			novo_pub.set_qos(pub_qos);
			publishers.add(novo_pub);
			
			return novo_pub;
	}
	
}
