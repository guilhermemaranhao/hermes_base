package br.ufg.inf.mestrado.hermesbase.listeners;

public interface ComponenteFilteringListener {
	
	public void handleContext(String idListener, String nomeTopico, byte[] filterJson, String consultaFiltro);
	
}
