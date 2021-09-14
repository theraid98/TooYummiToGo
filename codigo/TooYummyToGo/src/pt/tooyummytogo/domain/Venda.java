/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.domain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.exception.WrongQuantityException;


public class Venda {
	
	private HashMap<String, Produto> listP;
	private LocalDateTime hora_inicio;
	private LocalDateTime hora_fim;
	
	
	/**
	 * Contrutor da venda
	 */
	public Venda() {
		this.listP = new HashMap<>();
	}
	
	/**
	 * Este metodo retorna uma Collection de Produtos
	 * @return
	 */
	public Collection<Produto> getListaProdutos(){
		return this.listP.values();
	}
	
	/**
	 * Este metodo retorna a hora de inicio da venda,
	 *  escolhida pelo comerciante
	 * @return a hora de inicio da venda
	 */
	public LocalDateTime getHoraInicio(){
		return this.hora_inicio;
	}
	/**
	 * Este metodo retorna a hora final da venda,
	 *  escolhida pelo comerciante
	 * @return a hora final da venda
	 */
	public LocalDateTime getHoraFim(){
		return this.hora_fim;
	}
	
	/**
	 * Este metodo adiciona um produto ao mapa de produtos,
	 * com key- codigo do produto e Value- Produto
	 * @param p - o produto a adicionar
	 */
	public void addProduto(Produto p) {
		this.listP.put(p.getCodigo(),p);
	}
	
	/**
	 * Este metodo devolve o produto, procurando-o pelo codigo e comparando a sua quantidade
	 * caso a quantidade do produto a venda seja menor que parametro quant, throw exception
	 * @param cod - codigo do produto
	 * @param quant - quantidade que o utilizador quer
	 * @return o produto caso a quantidade seja maior ou igual que a pedida
	 * @throws WrongQuantityException - caso a quantidade do produto a venda seja menor que parametro quant
	 */
	public Produto getProdutoComQuant(String cod, int quant) throws WrongQuantityException {
		for(Produto p: listP.values())
			if(p.getCodigo().equals(cod)) {
				if(p.comparaQuantidade(quant)) {
					Produto prod = new Produto(p.getNome(), p.getPreco(), Integer.parseInt(p.getCodigo()));
					return prod;
				}
				throw new WrongQuantityException("Quantidade incorreta. Máximo é: " + p.getQuantidade());
			}
				
		return null;
	}
	
	/**
	 * Este metodo atualiza a hora de inicio e de fim da venda
	 * @param hora_inicio - hora de inicio
	 * @param hora_fim - hora final
	 */
	public void atualizaHoras(LocalDateTime hora_inicio, LocalDateTime hora_fim) {
		this.hora_fim = hora_fim;
		this.hora_inicio = hora_inicio;
	}
	
	/**
	 * Este metodo ve se o periodo definido pelo utilizador esta dentro do
	 *  periodo definido pelo comerciante
	 * @param hInicio - a hora de inicio
	 * @param hFim - a hora final
	 * @return true se o periodo estiver dentro
	 */
	public Boolean estaDentroDoPeriodo(LocalDateTime hInicio, LocalDateTime hFim) {
		return this.hora_inicio.compareTo(hInicio) <= 0 && this.hora_fim.compareTo(hFim) >= 0;
		}
	
	/**
	 * Este metodo retorna a lista de Produto info de todos os produtos que estao a venda
	 * @return a lista de ProdutoInfo
	 */
	public List<ProdutoInfo> getTodosProdutos(){
		List<ProdutoInfo> lProdutos= new ArrayList<>();
		for(Produto p: listP.values()){
			lProdutos.add(new ProdutoInfo(p.getNome(),p.getCodigo()));
		}
		return lProdutos;
	}
	
	
	
	/**
	 * Formato textual da venda
	 */
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("Hora de inicio: "+ this.hora_inicio.toString()+"\n");
		sb.append("Hora de fim: "+ this.hora_fim.toString()+"\n");
		sb.append("Todos os Produtos a venda:\n");
		for(Produto prod: this.listP.values()) {
			sb.append(prod+" ");
		}
		sb.append(" \n");
		return sb.toString();
	}

	/**
	 * Este metodo  reduz a quantidade de um produto a venda apos a sua compra
	 * @param p - produto que foi comprado
	 */
	public void reduzirQuantidade(Produto p) {
		Produto aux = listP.get(p.getCodigo());
		aux.atualizaQuantidade(aux.getQuantidade()- p.getQuantidade());
		
		
	}
	


}
