/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.facade.dto;


public class ComercianteInfo {
	private String nome;
	private PosicaoCoordenadas loc;
	private String id; 


	/**
	 * 
	 * @param nome - nome do comerciante
	 * @param loc - localizacao do comerciante
	 * @param id - id do comerciante
	 */
	public ComercianteInfo(String nome, PosicaoCoordenadas loc, String id) {
		this.nome=nome;
		this.loc=loc;
		this.id=id;
		
	}
	
	/**
	 * Metodo que retorna o id do comerciante
	 * @return
	 */
	public String getCodigo() {
		return this.id;
	}
	
	/**
	 * Metodo toString do ComercianteInfo
	 */
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("Nome: "+ this.nome+";\n");
		sb.append("id: "+ this.id+";\n");
		sb.append("Localizacao:("+ this.loc.getLatitude()+", "+ this.loc.getLongitude()+").\n");
		
		return sb.toString();
		
	}

}
