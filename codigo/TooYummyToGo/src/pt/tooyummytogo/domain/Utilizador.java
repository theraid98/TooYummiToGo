/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;

public class Utilizador {
	
	private String username;
	private String password;
	private int codigo;
	private Procura procura;
	private List<Reserva> listReserva;
	
	/**
	 * Construtor do Utilizador 
	 * @param username - username do Utilizador
	 * @param password - password do Utilizador
	 * @param codigo - codigo do Utilizador
	 */
	public Utilizador(String username, String password, int codigo) {
		this.username=username;
		this.password=password;
		this.codigo=codigo;
		listReserva = new ArrayList<>();
		
	}

	/**
	 * Buscar Codigo do Utilizador
	 * @return - codigo do Utilizador
	 */
	public String getCodigo() {
		return String.valueOf(codigo);
	}

	/**
	 * Metodo para ir buscar a Password
	 * @return password do Utilizador
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Metodo que cria a Procura
	 */
	public void criarProcura() {
		this.procura= new Procura();
	}
	
	
	/**
	 * Metodo para ir buscar a Procura
	 * @return
	 */
	public Procura getProcura() {
		return this.procura;
	}

	/**
	 * Metodo que confirma se o utilizador e as suas credenciais estao corretas
	 * @param user - usarname do utilizador
	 * @param pass - password do utilizador
	 * @return
	 */
	public boolean ehUtilizador(String user, String pass) {
		return user.equals(username) && pass.equals(password);
	}

	/**
	 * Metodo que inicializa a procura = p
	 * @param p - procura
	 */
	public void adicionaProcura(Procura p) {
		this.procura = p;
		
	}

	/**
	 * Metodo que adiciona a reserva ah lista de reservas
	 * @param reserva - reserva a adicionar
	 */
	public void addReserva(Reserva reserva) {
		listReserva.add(reserva);
		
	}
	

}
