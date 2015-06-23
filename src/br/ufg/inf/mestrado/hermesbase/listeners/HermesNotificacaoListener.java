package br.ufg.inf.mestrado.hermesbase.listeners;

import br.ufg.inf.mestrado.hermesbase.coreDX.notificacao.NotificacaoTypeDataReader;
import br.ufg.inf.mestrado.hermesbase.coreDX.notificacao.NotificacaoTypeSeq;

import com.toc.coredx.DDS.DDS;
import com.toc.coredx.DDS.DataReader;
import com.toc.coredx.DDS.DataReaderListener;
import com.toc.coredx.DDS.LivelinessChangedStatus;
import com.toc.coredx.DDS.RequestedDeadlineMissedStatus;
import com.toc.coredx.DDS.RequestedIncompatibleQosStatus;
import com.toc.coredx.DDS.ReturnCode_t;
import com.toc.coredx.DDS.SampleInfoSeq;
import com.toc.coredx.DDS.SampleLostStatus;
import com.toc.coredx.DDS.SampleRejectedStatus;
import com.toc.coredx.DDS.SubscriptionMatchedStatus;

public class HermesNotificacaoListener implements DataReaderListener{

	private ComponenteNotificacaoListener notificacaoListener;
	
	public void setNotificationListener(ComponenteNotificacaoListener listener)
	{
		this.notificacaoListener = listener;
	}
	
	public ComponenteNotificacaoListener getNotificationListener()
	{
		return this.notificacaoListener;
	}

	@Override
	public long get_nil_mask() {
		return 0;
	}

	@Override
	public void on_data_available(DataReader datareader) {
		
			NotificacaoTypeDataReader dr = (NotificacaoTypeDataReader)datareader;
			NotificacaoTypeSeq amostrasNotification = new NotificacaoTypeSeq();
		
			SampleInfoSeq si = new SampleInfoSeq();
			ReturnCode_t retval = dr.read(amostrasNotification, si, DDS.LENGTH_UNLIMITED, DDS.NOT_READ_SAMPLE_STATE, DDS.ANY_VIEW_STATE, DDS.ANY_INSTANCE_STATE);
			if (retval == ReturnCode_t.RETCODE_OK)
			{
				if (amostrasNotification.value.length == 0)
				{
					System.out.println(" ----- Amostra VAZIA no DDS!");
				}
				for (int i = 0; i < amostrasNotification.value.length; i++)
				{
//					if (si.value[i].instance_state == DDS.ALIVE_INSTANCE_STATE)
//					{
						if (si.value[i].valid_data)
						{
						
							String idEntidade = amostrasNotification.value[i].id_entidade;
							String nomeTopico = amostrasNotification.value[i].nome_topico;
							String complementoTopico = amostrasNotification.value[i].complemento_topico;
							String caminhoOntologia = amostrasNotification.value[i].caminho_ontologia;
							byte[] contexto = amostrasNotification.value[i].contexto;
							String tipoSerializacao = amostrasNotification.value[i].tipo_serializacao;
							
							this.notificacaoListener.handleContext(idEntidade, nomeTopico, complementoTopico, caminhoOntologia, contexto, tipoSerializacao);
						}
						else
						{
							System.out.println(" --- Instância não válida ---- ");
						}
					//}
//					else {
//						System.out.println(" --- Instância NOT ALIVE --- ");
//						if (si.value[i].sample_rank == 0) {
//					    
//					    // call take_instance() to remove all traces of this dead instance...
//						  NotificacaoTypeSeq deadsamples = new NotificacaoTypeSeq();
//						  SampleInfoSeq deadsi     = new SampleInfoSeq();
//						  retval = dr.take_instance(deadsamples, deadsi, DDS.LENGTH_UNLIMITED,
//								      si.value[i].instance_handle, 
//								      DDS.ANY_SAMPLE_STATE, 
//								      DDS.ANY_VIEW_STATE, 
//								      DDS.ANY_INSTANCE_STATE);
//						  if (retval == ReturnCode_t.RETCODE_OK) 
//							  dr.return_loan(deadsamples, deadsi);
//						}
//					}
				}
				//Devolve as amostras (samples) lidas ao middleware DDS, notificando-o que não deseja acessá-las novamente. Seu
				//valor de sample_state é atribuido para READ.
				dr.return_loan(amostrasNotification, si);
			}
			else
			{
				System.out.println(" **** Amostra não lida no DDS !!");
			}
	}
	
	@Override
	public void on_liveliness_changed(DataReader arg0,
			LivelinessChangedStatus arg1) {	
	}

	@Override
	public void on_requested_deadline_missed(DataReader arg0,
			RequestedDeadlineMissedStatus arg1) {
	}

	@Override
	public void on_requested_incompatible_qos(DataReader arg0,
			RequestedIncompatibleQosStatus arg1) {
	}

	@Override
	public void on_sample_lost(DataReader arg0, SampleLostStatus arg1) {
	}

	@Override
	public void on_sample_rejected(DataReader arg0, SampleRejectedStatus arg1) {
	}

	@Override
	public void on_subscription_matched(DataReader arg0,
			SubscriptionMatchedStatus arg1) {
	}

}
