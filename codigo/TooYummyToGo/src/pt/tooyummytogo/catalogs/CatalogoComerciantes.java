/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.catalogs;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import pt.tooyummytogo.domain.Comerciante;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;


public class CatalogoComerciantes {

	private Map<String,Comerciante> listComerciantes;
	private int codigo=0;


	/**
	 * Construtor do Catalogo de Comerciantes
	 */
	public CatalogoComerciantes() {
		this.listComerciantes = new HashMap<>();
	}

	/**
	 * Metodo para verificar se o Comerciante esta autenticado
	 * @param username - username do Comerciante
	 * @param password - password do Comerciante
	 * @return OPtional<Comerciante> se este estiver autenticado ou Optional.empty() se nao estiver
	 */
	public Optional<Comerciante> tryAutenticar(String username, String password) {
		Collection <Comerciante> comerciante = listComerciantes.values();
		for(Comerciante c : comerciante) {
			if(c.ehComerciante(username, password)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}


	/**
	 * Ir buscar o Comerciante pelo seu id
	 * @param id - id do OCmerciante pretentido
	 * @return - Comerciante
	 */
	public Comerciante getComerciante(String id){
		if(listComerciantes.containsKey(id)) {
			return listComerciantes.get(id);
		}
		System.out.println("Nao existe esse comerciante.");
		return null;
	}


	/**
	 * Colocar COmerciante dentro do Catalogo de Comerciantes
	 * @param nome - username do Comerciante
	 * @param password - Password do COmerciante
	 * @param loc - Localizacao do COmerciante
	 */
	public void putComerciante(String nome, String password, PosicaoCoordenadas loc) {
		Comerciante com = new Comerciante(nome, password, loc, codigo);
		this.listComerciantes.put(com.getCodigo(), com);
		codigo++;
	}



	/**
	 * toString referente ao Comerciante 
	 */
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("Todos os Comerciantes: \n<");
		for(int i=0; i<this.listComerciantes.size();i++) {
			sb.append(this.listComerciantes.get(String.valueOf(i))+"\n");
		}
		sb.setLength(sb.length()-2);
		sb.append("> \n");
		return sb.toString();

	}


	/**
	 * Ir buscar um comercainte consoante a sua Localizacao e Horas
	 * @param coordinate - Coordenadas da localizacao do Comerciante
	 * @param raio - Distancia a qual o Utilizador esta a Procura
	 * @param now - Hora de inicio da Procura
	 * @param horaFim - hora de fim da Procura
	 * @return Comerciantes que se encontra dentro dos requisitos esperados
	 */
	public List<ComercianteInfo> getComerciantePorLocEHoras(PosicaoCoordenadas coordinate, double raio, LocalDateTime now, LocalDateTime horaFim) {
		List<ComercianteInfo> comer= new ArrayList<>();
		for(Comerciante comI: this.listComerciantes.values()) {
			if(comI.getVenda()!=null) {
				if(comI.diferancaEhMenos(coordinate, raio) && comI.getVenda().estaDentroDoPeriodo(now,horaFim)) {
					ComercianteInfo comInfo = new ComercianteInfo(comI.getNome(),comI.getLocalizacao(), comI.getCodigo());
					comer.add(comInfo);
				}
			}		
		}
		return comer;
	}






}
