/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Reserva {

	private int codigo;
	private Map<String, Produto> listP;

	/**
	 * Construtor da Reserva
	 */
	public Reserva() {
		this.listP= new HashMap<>();
	}
	
	/**
	 * Ir buscar o Codigo da Reserva
	 * @return codigo da reserva
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Adicionar Produtos a Reserva
	 * @param produto - Produto a adicionar
	 */
	public void adicionaProduto(Produto produto) {
		listP.put(produto.getCodigo(), produto);
	}
	
	/**
	 * Gerar um codigo para a Reserva
	 * @return codigo da reserva
	 */
	public int geraCodigo() {
		int a= new Random().nextInt(9999999);
		this.codigo=a;
		return this.codigo;

	}

	/**
	 * Buscar o valor total de todos os Produtos pertencentes a Reserva
	 * @return valor total
	 */
	public double getContaFinal() {
		double result=0;
		for(Produto prod: listP.values()) {
			result += (prod.getPreco()*prod.getQuantidade());
		}
		return round(result,2);
	}
	
	
	/**
	 * Arredondar doubles a 2 casas decimais
	 * @param value - valor a arredondar
	 * @param places - casas que se pretendem
	 * @return double com arredondamento
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	/**
	 * Collection com todos os Produtos da Reserva
	 * @return Collection com todos os Produtos da Reserva
	 */
	public Collection<Produto> getProdutos(){
		return listP.values();
	}
	
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append(this.codigo+"\n");
		for(Produto prod: this.listP.values()) {
			sb.append(prod+" ");
		}
		return sb.toString();
	}
	

}
