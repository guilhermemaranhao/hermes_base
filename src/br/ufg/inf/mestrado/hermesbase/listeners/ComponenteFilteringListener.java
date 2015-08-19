package br.ufg.inf.mestrado.hermesbase.listeners;

/**
 * Interface a ser implementada pelos listeners de filtragem dos componentes de Hermes.
 * @author guilhermemaranhao
 *
 */
public interface ComponenteFilteringListener {
	
	/**
	 * Método que será invocado pelo listener do HB no assinante, quando a notificação filtragem for informada.
	 */
	public void handleContext(String idListener, String nomeTopico, byte[] filterJson, String consultaFiltro);
	
}
