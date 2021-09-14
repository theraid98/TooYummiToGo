package pt.tooyummytogo.facade;

import java.util.Optional;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.catalogs.CatalogoComerciantes;
import pt.tooyummytogo.catalogs.CatalogoUtilizadores;
import pt.tooyummytogo.domain.Comerciante;
import pt.tooyummytogo.domain.Utilizador;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

/**
 * Esta é a classe do sistema.
 */ 
public class TooYummyToGo {
	private CatalogoUtilizadores catUt = new CatalogoUtilizadores();
	private CatalogoComerciantes catCom = new CatalogoComerciantes();


	// UC1
	public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler(this.catUt);
	}

	/**
	 * Returns an optional Session representing the authenticated user.
	 * @param username
	 * @param password
	 * @return
	 * 
	 * UC2
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		Optional<Utilizador> utili = catUt.tryAutenticar(username, password);
		if(utili.isPresent()) {
			return utili.map(u ->new Sessao(u, this.catCom));
		}else {
			Optional<Comerciante> come = catCom.tryAutenticar(username, password);
			if(come.isPresent()) {
				return come.map(comerciante ->new Sessao(comerciante, this.catCom));
			}
			return Optional.empty();
		}
		
	}

	// UC3
	public RegistarComercianteHandler getRegistarComercianteHandler() {
		return new RegistarComercianteHandler(this.catCom);
	}


}
