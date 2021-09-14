/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.catalogs;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import pt.tooyummytogo.domain.Comerciante;
import pt.tooyummytogo.domain.Utilizador;

public class CatalogoUtilizadores {
	
	
	private Map<String,Utilizador> listUtilizadores;
	private int codigo=0;
	
	
	/**
	 * COnstrutor de Catalogo Utilizadores
	 */
	public CatalogoUtilizadores() {
		this.listUtilizadores = new HashMap<>();
		
	}
	
	
	/**
	 * Adicionar um Utilizador a Catalogo de Utilizadores
	 * @param username - username do Utilizador
	 * @param password - password do Utilizador
	 */
	public void addUtilizador(String username, String password) {
		Utilizador user= new Utilizador(username,password,codigo);
		this.listUtilizadores.put(user.getCodigo(), user);
		this.codigo++;
		
	}

	/**
	 * Ir buscar Utilizador pelo seu id
	 * @param id - id(codigo) do Utilizador
	 * @return Utilizador
	 */
	public Utilizador getUtilizador(String id){
		if(listUtilizadores.containsKey(id)) {
			return listUtilizadores.get(id);
		}
		System.out.println("Nao existe esse Utilizador.");
		return null;
	}
	
	
	/**
	 * Metodo para verificar se o Utilizador esta autenticado
	 * @param username - username do Utilizador
	 * @param password - password do Utilizador
	 * @return OPtional<Utilizador> se este estiver autenticado ou Optional.empty() se nao estiver
	 */
	public Optional<Utilizador> tryAutenticar(String user, String pass){
		Collection<Utilizador> utili = listUtilizadores.values();
		for(Utilizador u : utili) {
			if(u.ehUtilizador(user,pass)) {
				return Optional.of(u);
			}
		}
		return Optional.empty();
		
		
	}
	
	

}
