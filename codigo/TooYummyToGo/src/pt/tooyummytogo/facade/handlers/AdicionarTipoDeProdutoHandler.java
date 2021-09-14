/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.facade.handlers;
import pt.tooyummytogo.domain.*;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.catalogs.*;

public class AdicionarTipoDeProdutoHandler {
	private Comerciante com;
	private CatalogoComerciantes catCom;

	/**
	 * 
	 * @param com - Comerciante a inicializar
	 */
	public AdicionarTipoDeProdutoHandler(Comerciante com) {
		this.com=com;
	}

	
	/** Construtor que tem como parametro o id do comerciante
	 * 
	 * @param com - Comerciante a inicializar
	 * @param catCom - Catalogo de comerciantes a inicializar
	 */
	public AdicionarTipoDeProdutoHandler(Comerciante com, CatalogoComerciantes catCom) {
		this.catCom= catCom;
		this.com = com;
	}


	/** Adiciona o tipo de produto ao catalogo de produtos do comerciante
	 * 
	 * @param tp - tipo de produto
	 * @param preco - preco do produto
	 */
	public void registaTipoDeProduto(String tp, double preco) {
		com.addProduto(tp, preco);

	}

}
