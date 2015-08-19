package br.ufg.inf.mestrado.hermesbase.listeners;

/**
 * Interface a ser implementada pelos listeners de configuração dos componentes de Hermes.
 * @author guilhermemaranhao
 *
 */
public interface ComponenteConfiguracaoListener {

	/**
	 * Método que será invocado pelo listener do HB no assinante, quando a notificação de configuração for informada.
	 * @param tipoInferencia
	 * @param topicos
	 */
	public void handleContext(String tipoInferencia, String[] topicos);
	
}
