package br.ufg.inf.mestrado.hermesbase.listeners;

/**
 * Interface a ser implementada pelos listeners de notifica��o dos componentes de Hermes.
 * @author guilhermemaranhao
 *
 */
public interface ComponenteNotificacaoListener {
	
	/**
	 * M�todo que ser� invocado pelo listener do HB no assinante, quando a notifica��o for informada.
	 * @param tipoInferencia
	 * @param topicos
	 */
	public void handleContext(String idEntidade, String nomeTopico, String complementoTopico, String caminhoOntologia, byte[] contexto, String tipoSerializacao);

}
