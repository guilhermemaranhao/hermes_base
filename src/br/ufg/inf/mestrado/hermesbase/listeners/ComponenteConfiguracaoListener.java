package br.ufg.inf.mestrado.hermesbase.listeners;

/**
 * Interface a ser implementada pelos listeners de configura��o dos componentes de Hermes.
 * @author guilhermemaranhao
 *
 */
public interface ComponenteConfiguracaoListener {

	/**
	 * M�todo que ser� invocado pelo listener do HB no assinante, quando a notifica��o de configura��o for informada.
	 * @param tipoInferencia
	 * @param topicos
	 */
	public void handleContext(String tipoInferencia, String[] topicos);
	
}
