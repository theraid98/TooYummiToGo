package pt.tooyummytogo;

import pt.tooyummytogo.catalogs.CatalogoComerciantes;
import pt.tooyummytogo.catalogs.CatalogoUtilizadores;
import pt.tooyummytogo.domain.Comerciante;
import pt.tooyummytogo.domain.Utilizador;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public class Sessao { 
	
	private CatalogoComerciantes catCom;
	private Utilizador utilizador;
	private Comerciante com;
	
	public Sessao(Comerciante com, CatalogoComerciantes catCom) {
		this.catCom = catCom;
		this.com = com;
	}
	
	public Sessao(Utilizador u, CatalogoComerciantes catCom) {
		this.catCom = catCom;
		this.utilizador = u;
	}

	// UC4
	public AdicionarTipoDeProdutoHandler adicionarTipoDeProdutoHandler() {
		return new AdicionarTipoDeProdutoHandler(this.com, this.catCom);
	} 

	// UC5
	public ColocarProdutoHandler getColocarProdutoHandler() { 
		return new ColocarProdutoHandler(this.com,this.catCom);
	}

	public EncomendarHandler getEncomendarComerciantesHandler() {
		return new EncomendarHandler(this.catCom, this.utilizador);
	}
}
