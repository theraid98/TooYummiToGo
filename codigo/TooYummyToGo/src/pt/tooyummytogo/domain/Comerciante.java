/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import pt.tooyummytogo.catalogs.CatalogoProdutos;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class Comerciante {
	private String nome;
	private String password;
	private PosicaoCoordenadas loc;
	private int id;
	private HashMap<Integer, Reserva> reservaClientes = new HashMap<>();
	
	
	private CatalogoProdutos catP;
	private Venda v;
	
	/**
	 * Construtor de um comerciante
	 * @param nome - o nome/username do comerciante
	 * @param password - a password do comerciante
	 * @param loc - a localizacao do comerciante
	 * @param id - o identificador/codigo da conta do comerciante
	 */
	public Comerciante(String nome, String password, PosicaoCoordenadas loc, int id) {
		this.catP= new CatalogoProdutos();
		this.nome=nome;
		this.password=password;
		this.loc=loc;
		this.id=id;
	}
	/**
	 * Este metodo retorna o nome do comerciante
	 * @return - o nome do comerciante
	 */
	public String getNome() {
		return this.nome;
	}
	/**
	 * Este metodo retorna a password do comerciante
	 * @return - a password do comerciante
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * Este metodo retorna a localizacao do comerciante
	 * @return - a localizacao do comerciante
	 */
	public PosicaoCoordenadas getLocalizacao() {
		return this.loc;
	}
	/**
	 * Este metodo retorna o codigo do comerciante em formato de string
	 * @return - a codigo do comerciante
	 */
	public String getCodigo() {
		return String.valueOf(this.id);
	}
	
	/**
	 * Este metodo retorna o catalogo de produtos deponivel para este comerciante
	 * @return - o catalogo de produtos deponivel do comerciante
	 */
	public CatalogoProdutos getCatalogo() {
		return this.catP;
	}
	/**
	 * Este metodo retorna a lista de produtos para venda deste comerciante
	 * @return - a lista de produtos para venda do comerciante
	 */
	public Venda getVenda() {
		return this.v;
	}
	
//	public List<String> getListaProduto(){
//		return catP.getListaProdutos();
//	}
	

//////////////////////Diagrama iteracao 1
	/**
	 * Este metodo retorna os produtos disponiveis para o comerciante meter a venda
	 * @return - a lista de codigo de produtos em formato de string
	 */
	public List<String> disponibilizarProduto() {
		return catP.getListaProdutos();
	}
	/**
	 * Este metodo cria uma nova venda, e torna-a corrente para esse comerciante
	 */
	public void inicializaVenda() {
		this.v= new Venda();
		
	}

	/**
	 * Este metodo adiciona a venda corrente um produto com codigo pertencente
	 *  ao catalogo de produtos deste comerciante com uma certa quantidade
	 * @param cod - codigo escolhido pelo comerciante
	 * @param quant - quantidade disponivel na venda
	 */
	public void escolheProduto(String cod, int quant) {
		Produto p = this.catP.getProduto(cod);
		p.atualizaQuantidade(quant);
		this.v.addProduto(p);
		
	}
	
	
	/**       Este metodo nao e usado, contudo, como esta no enunciado, decidimos implementala
	 * Este metodo adiciona um produto ao catalogo de produtos do comerciante
	 * @param ing - lista de ingredientes do produto
	 * @param nome - nome do produto
	 */
	public void addProduto(List<String> ing, String nome) {
		this.catP.addProduto(ing, nome);
	}
	/**
	 * Este metodo adiciona um produto ao catalogo de produtos do comerciante
	 * @param nome - nome do produto
	 * @param preco - preco do produto
	 */
	public void addProduto(String nome, double preco) {
		this.catP.addProduto(nome, preco);
	}

	/**
	 * Este metodo indica o periodo em que a venda ira decorrer
	 * @param hora_inicio - hora que a venda comeca
	 * @param hora_fim - hora que a venda acaba
	 */
	public void estaDisponivel(LocalDateTime hora_inicio, LocalDateTime hora_fim) {
		v.atualizaHoras(hora_inicio, hora_fim);
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////	
//////////////////////////////////UC6/////////////////////////////////////////////////
	/**
	 * Este metodo compara a localizacao do comerciante com as coordenadas do utilizador, 
	 *  e ve se a distancia entre ambos eh menor que o raio fornecido
	 * @param coordinate - coordenadas do utilizador
	 * @param raio - raio em metros
	 * @return - true se a distancia for menor ou igual ao raio
	 */
	public boolean diferancaEhMenos(PosicaoCoordenadas coordinate, double raio) {
		return loc.distanciaEmMetros(coordinate) <= raio;
	}
	
//////////////////////////////////////////////////////////////////////////////////////
	

	/**
	 * Metodo que converte a classe comerciante para um formato textual
	 */
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("Nome: "+ this.nome+";\n");
		sb.append("id: "+ this.id+";\n");
		sb.append("Localizacao:("+ this.loc.getLatitude()+", "+ this.loc.getLongitude()+").\n");
		
		return sb.toString();
		
	}
	
	/**
	 * Este metodo confirma se um user eh comerciante, comparando o seu username e password
	 * @param username - o user name do user
	 * @param password2 - a password do user
	 * @return true se o user for um comerciante
	 */
	public boolean ehComerciante(String username, String password2) {
		return username.equals(nome) && password2.equals(password);
	}

	/**
	 * Este metodo reduz a quantidade de um produto que esta
	 *  ah venda apos o seu pagamento
	 * @param reserva - a reserva que contem a lista de produtos que um utilizador comprou
	 */
	public void reduzirQuantidades(Reserva reserva) {
		reservaClientes.put(reserva.getCodigo(), reserva);
		for(Produto p : reserva.getProdutos()) {
			v.reduzirQuantidade(p);
		}
		
	}


}
