package br.ufg.inf.mestrado.hermesbase.listeners;


public interface ComponenteNotificacaoListener {
	
	public void handleContext(String idEntidade, String nomeTopico, String complementoTopico, String caminhoOntologia, byte[] contexto, String tipoSerializacao);

}
