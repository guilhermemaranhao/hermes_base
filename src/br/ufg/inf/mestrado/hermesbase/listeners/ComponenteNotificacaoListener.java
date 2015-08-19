package br.ufg.inf.mestrado.hermesbase.listeners;

/**
 * Interface a ser implementada pelos listeners de notificação dos componentes de Hermes.
 * @author guilhermemaranhao
 *
 */
public interface ComponenteNotificacaoListener {
	
	/**
	 * Método que será invocado pelo listener do HB no assinante, quando a notificação for informada.
	 * @param tipoInferencia
	 * @param topicos
	 */
	public void handleContext(String idEntidade, String nomeTopico, String complementoTopico, String caminhoOntologia, byte[] contexto, String tipoSerializacao);

}
