/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.domain;
import java.util.List;

import pt.tooyummytogo.catalogs.*;


public class Produto {

	private int cod;
	private List<String> ingredientes;
	private String nome;
	private int quantidade;
	private double preco;


	/**
	 * Construtor do Produto
	 * @param nome - Nome do Produto
	 * @param ingredientes - Ingredientes do Produto
	 * @param cod - codigo do Produto
	 */
	public Produto(String nome, List<String> ingredientes, int cod) {
		this.nome=nome;
		this.ingredientes=ingredientes;
		this.quantidade=0;
		this.cod=cod;
	}


	/**
	 * Construtor do Produto
	 * @param nome - Nome do Produto
	 * @param preco - preco do Produto
	 * @param codigo - codigo do Produto
	 */
	public Produto(String nome, double preco, int codigo) {
		this.nome=nome;
		this.preco=preco;
		this.quantidade=0;
		this.cod=codigo;
	}


	/**
	 * Ir buscar o Codigo do Produto
	 * @return String do codigo
	 */
	public String getCodigo() {
		return String.valueOf(this.cod);
	}

	/**
	 * Ir buscar o nome do Produto
	 * @return String com o Nome
	 */
	public String getNome() {
		return this.nome;
	}

	
	/**
	 * Ir buscar a quantidade do Produto
	 * @return quantidade
	 */
	public int getQuantidade() {
		return this.quantidade;
	}
	
	/**
	 * Ir buscar o preco
	 * @return preco
	 */
	public double getPreco() {
		return this.preco;
	}
	
	/**
	 * Atualiza a quantidade do Produto para a pretendida 
	 * @param quant - quantidade para alterar
	 */
	public void atualizaQuantidade(int quant) {
		this.quantidade = quant;
	}

	/**
	 * COmparar quantidades (atual e pretendida)
	 * @param quant - quantidade a comparar
	 * @return true- true se a quantidade eh >= que a pretendida e false se o contrario
	 */
	public boolean comparaQuantidade(int quant) {
		return this.quantidade >= quant;
	}

	/**
	 * toString do Produto
	 */
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("nome: "+this.nome+"  codigo: "+this.cod+";\n");

		return sb.toString();
	}


}