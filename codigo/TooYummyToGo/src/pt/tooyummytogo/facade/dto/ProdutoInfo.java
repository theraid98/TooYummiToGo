package pt.tooyummytogo.facade.dto;
import java.util.List;

import pt.tooyummytogo.catalogs.*;

public class ProdutoInfo {
	
	private String cod;
	private String nome;
	
	
	/**
	 * Construtor de ProdutoInfo
	 * @param nome - nome do produto
	 * @param cod - codigo do produto
	 */
	public ProdutoInfo(String nome, String cod) {
		this.nome=nome;
		this.cod=cod;
	}
	
	/**
	 * Este metodo retorna o codigo deste produtoInfo
	 * @return codigo
	 */
	public String getCodigo() {
		return this.cod;
	}
	
	/**
	 * Este metodo retorna o nome do ProdutoInfo
	 * @return o nome
	 */
	public String getNome() {
		return this.nome;
	}
	
	
	
	/**
	 * Formato textual do ProdutoInfo
	 */
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("nome: "+this.nome+"      codigo: "+this.cod+"\n");
		return sb.toString();
	}
	
	
}
