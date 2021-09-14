package pt.tooyummytogo.main;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.facade.TooYummyToGo;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

public class ClienteExemplo {
	public static void main(String[] args) {
		TooYummyToGo ty2g = new TooYummyToGo();


		// UC1
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Felismina", "hortadafcul");


		// UC3
		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();

		regComHandler.registarComerciante("Silvino", "bardoc2", new PosicaoCoordenadas(34.5, 45.2));
		regComHandler.registarComerciante("Maribel", "torredotombo", new PosicaoCoordenadas(33.5, 45.2));


		// UC4
		Optional<Sessao> talvezSessao = ty2g.autenticar("Silvino", "bardoc2");

		Random r = new Random();
		talvezSessao.ifPresent( (Sessao s) -> {
			AdicionarTipoDeProdutoHandler atp = s.adicionarTipoDeProdutoHandler();
			for (String tp : new String[] {"Pão", "Pão de Ló", "Mil-folhas"}) {
				atp.registaTipoDeProduto(tp, r.nextDouble() * 10);
			}
		});

		

		// UC5
		Optional<Sessao> talvezSessao2 = ty2g.autenticar("Silvino", "bardoc2");
		talvezSessao2.ifPresent( (Sessao s) -> {
			ColocarProdutoHandler cpv = s.getColocarProdutoHandler();


			List<String> listaTiposDeProdutos = cpv.inicioDeProdutosHoje();

			System.out.println(listaTiposDeProdutos);

			cpv.indicaProduto(listaTiposDeProdutos.get(0), 10); // Pão
			cpv.indicaProduto(listaTiposDeProdutos.get(2), 5); // Mil-folhas
			cpv.indicaProduto("20NaFase3DeDCO", 5); // produto para criar



			cpv.confirma(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
			

			System.out.println("Produtos disponiveis");
		});

		// UC6 + UC7
		Optional<Sessao> talvezSessao3 = ty2g.autenticar("Felismina", "hortadafcul");
		talvezSessao3.ifPresent( (Sessao s) -> {
			EncomendarHandler lch = s.getEncomendarComerciantesHandler();
			List<ComercianteInfo> cs = lch.indicaLocalizacaoActual(new PosicaoCoordenadas(34.5, 45.2));
			List<ProdutoInfo> ps = null;


			for (ComercianteInfo i : cs) {
				System.out.println(i);
			}

			boolean redefineRaio = false;
			if (redefineRaio) {
				cs = lch.redefineRaio(100);
				for (ComercianteInfo i : cs) {
					System.out.println(i);
				}
			}

			boolean redefinePeriodo = false;
			if (redefinePeriodo) {
				//nao vai preencher cs pois a hora de fim eh uns milesimos de segundo mais tarde que o da venda
				cs = lch.redefinePeriodo(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
				for (ComercianteInfo i : cs) {
					System.out.println(i);
				}
			}
			try {
				// A partir de agora é UC7
				System.out.println("dentro do try");
				ps = lch.escolheComerciante(cs.get(0));

				System.out.println(cs.get(0));


			}catch(IndexOutOfBoundsException e) {
				System.out.println("Nao existe este comerciante dentro deste raio ou neste periodo");
				cs = lch.redefineRaio(100);
				for (ComercianteInfo i : cs) {
					System.out.println(i);
				}
				ps = lch.escolheComerciante(cs.get(0));
			}

			for (ProdutoInfo p : ps) {
				lch.indicaProduto(p, 1); // Um de cada
			}
			//lch.indicaProduto(ps.get(0), 2);
			//lch.indicaProduto(ps.get(1), 10);

			String codigoReserva = lch.indicaPagamento("365782312312", "02/21", "766");
			System.out.println("Reserva " + codigoReserva + " feita com sucesso");




		});

	}
}
