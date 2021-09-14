/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ComercianteInfo;

public class Procura {

	private int codigo;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFim;
	private double raio=5;

	private PosicaoCoordenadas loc;
	private Comerciante comerciante;
	private Reserva reserva;
		
	/**
	 * Construtor da Procura
	 */
	public Procura() {
		this.horaInicio = LocalDateTime.now();
		this.horaFim= this.horaInicio.plusHours(1);
	}
	/**
	 * Este metodo retorna o raio que vai procurar comerciantes,
	 * tem como default 5
	 * @return o raio da procura
	 */
	public double getRaio() {
		return this.raio;
	}
	/**
	 * Este metodo atualiza o raio da procura
	 * @param raio - raio de procura
	 */
	public void setRaio(double raio) {
		this.raio= raio;
	}
	/**
	 * Este metodo retorna a localizacao do utilizador
	 * @return a localizacao do utilizador
	 */
	public PosicaoCoordenadas getLocalizacao() {
		return this.loc;
	}
	/**
	 * Este metodo atualiza a localizacao do utilizador
	 * @param loc - a localizacao do utilizador
	 */
	public void setLocalizacao(PosicaoCoordenadas loc) {
		this.loc= loc;
	}
	/**
	 * Este metodo retorna a hora de inicio da procura,
	 * tem como default a hora atual
	 * @return a hora de inicio da procura
	 */
	public LocalDateTime getHoraInicio() {
		return this.horaInicio;
	}
	/**
	 * Este metodo atualiza a hora de inicio da procura
	 * @param horaInicio - uma hora que o utilizador fornecer
	 */
	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio= horaInicio;
	}
	/**
	 *  Este metodo retorna a hora final da procura,
	 * tem como default uma hora a mais que a hora atual
	 * @return a hora final da procura
	 */
	public LocalDateTime getHoraFim() {
		return this.horaFim;
	}
	
	/**
	 * Este metodo atualiza a hora final da procura
	 * @param horaFim - uma hora que o utilizador fornecer
	 */
	public void setHoraFim(LocalDateTime horaFim) {
		this.horaFim= horaFim;
	}
	
	/**
	 * Este metodo associa a procura corrente ao comerciante
	 *  escolhido pelo utilizador
	 * @param comerciante - o comerciante escoçhido pelo utilizador
	 */
	public void associaComerciante(Comerciante comerciante) {
		this.comerciante = comerciante;	
	}

	/**
	 * Este metodo cria uma reserva
	 */
	public void criarReserva() {
		this.reserva= new Reserva();
		
	}
	/**
	 * Este metodo retorna a reserva 
	 * @return a reserva
	 */
	public Reserva getReserva() {
		return this.reserva;
	}
	
	/**
	 * Este metodo adiciona um produto a lista de produtos
	 *  na reserva que pertence a venda
	 * @param produto - o produto escolhido pelo utilizador
	 */
	public void adicionaProdReserva(Produto produto) {
		this.reserva.adicionaProduto(produto);
	}
	/**
	 * Este metodo reduz a quantidade de um produto que esta
	 *  ah venda apos o seu pagamento
	 */
	public void reduzirQuantidade() {
		comerciante.reduzirQuantidades(reserva);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
