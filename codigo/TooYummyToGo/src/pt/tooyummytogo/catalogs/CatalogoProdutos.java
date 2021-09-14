/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.catalogs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.tooyummytogo.domain.*;
import pt.tooyummytogo.facade.dto.ProdutoInfo;

public class CatalogoProdutos {
	private Map<String,Produto> listProdutos;
	
	private int codigo=0;

	
	/**
	 * Construtor do Catalogo de Produtos
	 */
	public CatalogoProdutos() {
		this.listProdutos = new HashMap<>();
	}

	
	/**
	 * Ir buscar a Lista de Produtos disponivel, do Comerciante
	 * @return - nomes de todos os Produtos disponiveis
	 */
	public List<String> getListaProdutos(){
		List<String> lProdutos= new ArrayList<>();
		for(Map.Entry<String,Produto> entry: this.listProdutos.entrySet()){
			lProdutos.add(entry.getKey());
		}
		return lProdutos;
	}
	
	
	/**
	 * Ir buscar o Produto pelo seu codigo
	 * @param cod - codigo do Produto
	 * @return - Produto
	 */
	public Produto getProduto(String cod){
		if(listProdutos.containsKey(cod)) {
			return listProdutos.get(cod);
		}
		return null;
	}
	
	
	/**
	 * Adicionar novo Produto ao Catalogo
	 * @param ing - ingredientes do Produtos
	 * @param nome - Nome do Produto
	 */
	public void addProduto(List<String> ing, String nome) {
		Produto p = new Produto(nome, ing, this.codigo);
		this.listProdutos.put(String.valueOf(codigo), p);
		this.codigo++;
	}
	
	
	/**
	 * dicionar novo Produto ao Catalogo
	 * @param nome - nome do Produto
	 * @param preco - preco do Produto
	 */
	public void addProduto(String nome, double preco) {
		Produto p = new Produto(nome, preco, this.codigo);
		this.listProdutos.put(String.valueOf(codigo), p);
		this.codigo++;
		
	}
	
	
	/**
	 * toString do Catalogo de Produtos
	 */
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("Todos os Produtos:\n");
		for(int i=0; i<this.listProdutos.size();i++) {
			sb.append(this.listProdutos.get(String.valueOf(i)));
		}
		sb.append("\n");
		return sb.toString();
	}
	
	
	////UC7

	/**
	 * Passar toda a Informacao dos Produtos para DTO do cliente
	 * @return lista de Produtos Info
	 */
	public List<ProdutoInfo> getTodosProdutos(){
		List<ProdutoInfo> lProdutos= new ArrayList<>();
		for(Map.Entry<String,Produto> entry: this.listProdutos.entrySet()){
			lProdutos.add(new ProdutoInfo(entry.getValue().getNome(),entry.getValue().getCodigo()));
		}
		return lProdutos;
	}


	

}
