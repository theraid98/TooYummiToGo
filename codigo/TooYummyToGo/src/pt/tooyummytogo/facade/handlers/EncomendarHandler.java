/**
 * @author Carlota Filipe, fc51027
 * @author Carolina Duque, fc51087
 * @author Francisco Martins,fc51073
 */
package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.List;

import com.monstercard.Card;
import com.monstercard.MonsterCardAPI;

import pt.tooyummytogo.catalogs.CatalogoComerciantes;
import pt.tooyummytogo.domain.Comerciante;
import pt.tooyummytogo.domain.Procura;
import pt.tooyummytogo.domain.Produto;
import pt.tooyummytogo.domain.Reserva;
import pt.tooyummytogo.domain.Utilizador;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.exception.WrongQuantityException;


public class EncomendarHandler {
	
	private CatalogoComerciantes catCom;
	private Procura p;
	private Comerciante com;
	private Utilizador utilizador;
	
	/**
	 * 
	 * @param catCom - catalogo de comerciantes a inicializar
	 * @param utilizador - utilizador a inicializar
	 */
	public EncomendarHandler(CatalogoComerciantes catCom, Utilizador utilizador) {
		this.catCom= catCom;
		this.utilizador = utilizador;
	}
	
	
	/**
	 * 	Metodo que inicializa uma procura e devolve uma lista de informacoes dos 
	 *  comerciantes que correspondem ah procura do utilizador
	 * @param coordinate - coordenadas do comerciante
	 * @return lista de informacoes dos comerciantes correspondente ah procura
	 */
	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas coordinate) {
		p = new Procura();
		p.setLocalizacao(coordinate);
		return this.catCom.getComerciantePorLocEHoras(coordinate, p.getRaio(), p.getHoraInicio(), p.getHoraFim());
	}
	
	
	/**
	 * Metodo que redefine o raio da procura do utilizador 
	 * @param i - novo raio
	 * @return lista de informacoes dos comerciantes correspondente ah nova redefinicao da procura
	 */
	public List<ComercianteInfo> redefineRaio(int i) {
		p.setRaio(i);
		return this.catCom.getComerciantePorLocEHoras(p.getLocalizacao(), i, p.getHoraInicio(), p.getHoraFim());
	}
	
	
	/**
	 * Metodo que redefine a janela temporal da procura do utilizador
	 * @param now - hora atual
	 * @param plusHours - hora final
	 * @return lista de informacoes dos comerciantes correspondente ah nova redefinicao da procura
	 */
	public List<ComercianteInfo> redefinePeriodo(LocalDateTime now, LocalDateTime plusHours) {
		this.p.setHoraInicio(now);
		this.p.setHoraFim(plusHours);

		return this.catCom.getComerciantePorLocEHoras(p.getLocalizacao(), p.getRaio(), now, p.getHoraFim());
	}

	/**
	 * Metodo onde o utilizador escolhe o comerciante que quer e devolve a lista de informacao dos produtos disponiveis
	 * @param comercianteInfo - comerciante escolhido
	 * @return lista de informacao dos produtos disponiveis desse comerciante
	 */
	public List<ProdutoInfo> escolheComerciante(ComercianteInfo comercianteInfo) {
		this.com = catCom.getComerciante(comercianteInfo.getCodigo());
		p.associaComerciante(this.com);
		return this.com.getVenda().getTodosProdutos();
	}

	
	/**
	 * Metodo onde o utiliador escolhe o produto que quer e a sua quantidade, numa nova reserva ou previamente criada
	 * @param p - produto
	 * @param i - quantidade
	 */
	public void indicaProduto(ProdutoInfo p, int i) {
		
		try {
			Produto prod = this.com.getVenda().getProdutoComQuant(p.getCodigo(), i);
			prod.atualizaQuantidade(i);
			
			if(this.p.getReserva() == null) {
				this.p.criarReserva();
			}
			
			this.p.getReserva().adicionaProduto(prod);
		}catch (WrongQuantityException e) {
			System.out.println("Quantidade do produto da venda eh maior que o que deseja.");
			System.out.println(e.getMessage());
			
			System.out.println();
		}
		
		
	}

	/**
	 * Metodo onde o utilizador efetua o pagamento indicando os dados do seu cartao
	 * @param number - numero de cartao
	 * @param data - data de validade do cartao
	 * @param ccv - codigo ccv do cartao
	 * @return codigo da reserva
	 */
	public String indicaPagamento(String number, String data, String ccv) {
		String aux[] = data.split("/");
		Card card = new Card(number, ccv, aux[0], "20"+aux[1]);
		MonsterCardAPI ms = new MonsterCardAPI();
		
		card.validate();
		if(ms.isValid(card)) {
			double conta = this.p.getReserva().getContaFinal();
			
			if(ms.block(card, conta)) {
				System.out.println("Blocking");
			}
			
			if(ms.charge(card, conta)) {
				System.out.println("Charging");
				int codigo = p.getReserva().geraCodigo();
				//this.utilizador.adicionaProcura(this.p);			ISTO EH COMO ESTA NO MODELO DE CLASSES
				p.reduzirQuantidade();
				this.utilizador.addReserva(p.getReserva());
				return String.valueOf(codigo);
				
			}
		}
		return "not ok";
	}

}
