/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.catalogs.CatalogoComerciantes;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class RegistarComercianteHandler {
	
	private CatalogoComerciantes catCom;
	
	/**
	 * 
	 * @param catCom - catalogo de comerciantes a inicializar
	 */
	public RegistarComercianteHandler(CatalogoComerciantes catCom) {
		this.catCom=catCom;
	}
	/**
	 * Regista um Comerciante.
	 * @param Username - username do comerciante
	 * @param Password - password do comerciante
	 * @ensures existe um comerciante com esse username
	 */
	public void registarComerciante(String username, String password, PosicaoCoordenadas p) {
		this.catCom.putComerciante(username, password, p);
	}
	
	
	/**
	 * Metodo que devolve o catalodo de comerciantes
	 * @return catalogo de comerciantes
	 */
	public CatalogoComerciantes getCatCom() {
		return this.catCom;
	}

}
