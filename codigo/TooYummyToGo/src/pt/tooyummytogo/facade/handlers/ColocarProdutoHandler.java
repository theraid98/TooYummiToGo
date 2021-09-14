/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.facade.handlers;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import pt.tooyummytogo.catalogs.CatalogoComerciantes;
import pt.tooyummytogo.domain.Comerciante;


public class ColocarProdutoHandler {
	private Comerciante com;
	private CatalogoComerciantes catCom;
	

	/**
	 * 
	 * @param c - comerciante a inicializar
	 */
	public ColocarProdutoHandler(Comerciante c) {
		this.com= c;
	}
	
	/**
	 * Construtor que inicializa a venda
	 * @param c - comerciante a inicializar
	 * @param catCom - catalogo de comerciantes a inicializar
	 */
	public ColocarProdutoHandler(Comerciante c, CatalogoComerciantes catCom) {
		this.catCom= catCom;
		this.com = c;
		this.com.inicializaVenda();
	}
	
	
	/**
	 * Metodo que retorna a lista de produtos do comerciante
	 * @return lista de produtos do comerciante
	 */
	public List<String> inicioDeProdutosHoje() {
		return com.disponibilizarProduto();
	}
	

	/**
	 * Metodo que adiciona ah venda os produtos que o comerciante tem para a vender
	 * @param cod - codigo do produto
	 * @param q - quantidade do produto
	 */
	public void indicaProduto(String cod, int q) {
		try {
			com.escolheProduto(cod, q);
		}catch(NullPointerException e) {
			Random r = new Random();
			int preco = 20;
			com.addProduto(cod, r.nextDouble() * 10);
			List<String> produtos = inicioDeProdutosHoje();
			indicaProduto(produtos.get(produtos.size() - 1), q);
		}
		
	}

	/**
	 * Metodo que confirma se o comerciante esta disponivel num intervalo de tempo
	 * @param now - hora atual
	 * @param plusHours - hora final
	 */
	public void confirma(LocalDateTime now, LocalDateTime plusHours) {
		com.estaDisponivel(now, plusHours);
		
	}

}
