package br.ufg.inf.mestrado.hermesbase.listeners;

import br.ufg.inf.mestrado.hermesbase.coreDX.filtering.FilteringTypeDataReader;
import br.ufg.inf.mestrado.hermesbase.coreDX.filtering.FilteringTypeSeq;

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

public class HermesFilteringListener implements DataReaderListener {

	private ComponenteFilteringListener filterListener;
	
	public ComponenteFilteringListener getFilteringListener() {
		return filterListener;
	}

	public void setFilteringListener(ComponenteFilteringListener listener) {
		this.filterListener = listener;
	}

	@Override
	public long get_nil_mask() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void on_data_available(DataReader datareader) {
		FilteringTypeDataReader dr = (FilteringTypeDataReader)datareader;
		
		FilteringTypeSeq samples = new FilteringTypeSeq();
		SampleInfoSeq si = new SampleInfoSeq();
		
		ReturnCode_t retval = dr.read(samples, si, DDS.LENGTH_UNLIMITED, DDS.NOT_READ_SAMPLE_STATE, DDS.NEW_VIEW_STATE, DDS.ANY_INSTANCE_STATE);
		if (retval == ReturnCode_t.RETCODE_OK)
		{
			for (int i = 0; i < samples.value.length; i++)
			{
				if (si.value[i].instance_state == DDS.ALIVE_INSTANCE_STATE)
				{
					if (si.value[i].valid_data)
					{
						String nomeTopico = samples.value[i].nomeTopico;
						String idListener = samples.value[i].idListener;
						byte[] filterJson = samples.value[i].filtroJson;
						String consultaFiltro = samples.value[i].consultaFiltro;
						this.filterListener.handleContext(idListener, nomeTopico, filterJson, consultaFiltro);
						//facade.gerenciarCicloSemanticoSinalVital(nome_topico, samples.value[i].sensor_id, samples.value[i].rdf_serializado);
					}
				}
				else {
					  if (si.value[i].sample_rank == 0) {
					    
					    // call take_instance() to remove all traces of this dead instance...
						  FilteringTypeSeq deadsamples = new FilteringTypeSeq();
					    SampleInfoSeq deadsi     = new SampleInfoSeq();
					    retval = dr.take_instance(deadsamples, deadsi, DDS.LENGTH_UNLIMITED,
								      si.value[i].instance_handle, 
								      DDS.ANY_SAMPLE_STATE, 
								      DDS.ANY_VIEW_STATE, 
								      DDS.ANY_INSTANCE_STATE);
					    if (retval == ReturnCode_t.RETCODE_OK) 
					    	dr.return_loan(deadsamples, deadsi);
					  }
				}
			}
			//Devolve as amostras (samples) lidas ao middleware DDS, notificando-o que não deseja acessá-las novamente. Seu
			//valor de sample_state é atribuido para READ.
			dr.return_loan(samples, si);
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
