/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.catalogs.CatalogoUtilizadores;

public class RegistarUtilizadorHandler {
	
	private CatalogoUtilizadores catUt;
	
	/**
	 * 
	 * @param catUt - catalogo de utilizadores a inicializar
	 */
	public RegistarUtilizadorHandler(CatalogoUtilizadores catUt) {
		this.catUt=catUt;
	}
	/**
	 * Regista um utilizador normal.
	 * @param Username - username do utilizador
	 * @param Password - password do utilizador
	 * @ensures existe um utilizador com esse username
	 */
	public void registarUtilizador(String username, String password) {
		this.catUt.addUtilizador(username, password);
	}

}
