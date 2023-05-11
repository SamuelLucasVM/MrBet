package MrBet;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MrBetSistema sistema = new MrBetSistema();
		
		System.out.println("---Mr.Bet---\n");
		
		String comando;
		while(true) {
			System.out.println("(M)Minha inclusão de times\n"
					+ "(R)Recuperar time\n"
					+ "(.)Adicionar campeonato\n"
					+ "(B)Bora incluir time em campeonato e Verificar se time está em campeonato\n"
					+ "(E)Exibir campeonatos que o time participa\n"
					+ "(T)Tentar a sorte e status\n"
					+ "(H)Histórico\n"
					+ "(!)Já pode fechar o programa!\n");
			System.out.print("Comando> ");
			comando = sc.nextLine();
			selecionaAcao(comando, sc, sistema);	
			}		
		}
		
	private static void selecionaAcao(String comando, Scanner sc, MrBetSistema sistema) {
		switch (comando) {
		case "M": {
			incluirTime(sc, sistema);
			break;
		}
		case "R": {
			recuperarTimes(sc, sistema);
			break;
		}
		case ".": {
			adicionarCampeonato(sc, sistema);
			break;
		}
		case "B": {
			incluirOuVerificarTimeCampeonato(sc, sistema);
			break;
		}
		case "E": {
			exibirCampeonatosTime(sc, sistema);
			break;
		}
		case "T": {
			tentarSorte(sc, sistema);
			break;
		}
		case "H": {
			historico(sc, sistema);
			break;
		}
		case "!": {
			System.out.println("\nPor hoje é só pessoal!\n");
			System.exit(0);
		}
		default:
			System.out.println("Comando inválido.\n");
			break;
		}
	}
	
	private static void incluirTime(Scanner sc, MrBetSistema sistema) {
		System.out.print("Código: ");
		String codigo = sc.nextLine();
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		System.out.print("Mascote: ");
		String mascote = sc.nextLine();
		
		try {
			sistema.addTime(codigo, nome, mascote);
			System.out.println("INCLUSÃO REALIZADA!\n");
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
	}
	
	private static void recuperarTimes(Scanner sc, MrBetSistema sistema) {
		System.out.print("Código: ");
		String codigo = sc.nextLine();
		
		try {
			Time time = sistema.getTime(codigo);
			System.out.println(time + "\n");
		} catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
		}
	}
	
	private static void adicionarCampeonato(Scanner sc, MrBetSistema sistema) {
		System.out.print("Campeonato: ");
		String campeonato = sc.nextLine();
		System.out.print("Participantes: ");
		int qtdeParticipantes = sc.nextInt();
		sc.nextLine();
		
		try {
			sistema.addCampeonato(campeonato, qtdeParticipantes);	
			System.out.println("CAMPEONATO ADICIONADO!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void incluirOuVerificarTimeCampeonato(Scanner sc, MrBetSistema sistema) {
		System.out.print("\n(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato? ");
		String comando = sc.nextLine();
		if (!comando.equals("I") && !comando.equals("V")) {
			System.out.println("\nComando inválido.\n");				
			return;
		}
		
		System.out.print("Código: ");
		String codigo = sc.nextLine();
		System.out.print("Campeonato: ");
		String nomeCampeonato = sc.nextLine();
		
		Time time = null;
		try {
			time = sistema.getTime(codigo);			
		} catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
			return;
		}
		
		Campeonato campeonato = null;
		try {
			campeonato = sistema.getCampeonato(nomeCampeonato);							
		} catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
			return;
		}
		
		if (comando.equals("I")) {
			incluirTimeCampeonato(time, campeonato, sistema);
		}
		else {
			verificarTimeCampeonato(time, campeonato, sistema);
		}
	}
	
	private static void incluirTimeCampeonato(Time time, Campeonato campeonato, MrBetSistema sistema) {
		try {
			sistema.addTimeCampeonato(campeonato, time);
			System.out.println("TIME INCLUÍDO NO CAMPEONATO!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void verificarTimeCampeonato(Time time, Campeonato campeonato, MrBetSistema sistema) {
		try {
			if (sistema.timeEstaEmCampeonato(campeonato, time)) {
				System.out.println("O TIME ESTÁ NO CAMPEONATO!\n");				
			} else {
				System.out.println("O TIME NÃO ESTÁ NO CAMPEONATO!\n");
			}
		} catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
		}
	}
	
	private static void exibirCampeonatosTime(Scanner sc, MrBetSistema sistema) {
		System.out.print("Time: ");
		String nomeTime = sc.nextLine();
		
		Time time = null;
		try {
			time = sistema.getTime(nomeTime);
		} catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
			return;
		}
	
		System.out.println("Campeonatos do " + time.getNome() + ": ");
		for(Campeonato campeonato : time.getCampeonatos()) {
			System.out.println(campeonato);
		}
		System.out.println();
	}
	
	private static void tentarSorte(Scanner sc, MrBetSistema sistema) {
		System.out.print("\n(A)Apostar ou (S)Status das Apostas? ");
		String comando = sc.nextLine();
		if (!comando.equals("A") && !comando.equals("S")) {
			System.out.println("\nComando inválido.\n");				
			return;
		}
		
		if (comando.equals("A")){
			apostar(sc, sistema);
		}
		else {
			statusAposta(sc, sistema);
		}
		
	}
	
	private static void apostar(Scanner sc, MrBetSistema sistema) {
		System.out.print("Código: ");
		String codigoTime = sc.nextLine();
		System.out.print("Campeonato: ");
		String nomeCampeonato = sc.nextLine();
		System.out.print("Colocação: ");
		int colocacao = sc.nextInt();
		sc.nextLine();
		System.out.print("valor: ");
		double valor = sc.nextDouble();
		sc.nextLine();
		
		Time time = null;
		try {
			time = sistema.getTime(codigoTime);			
		} catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
			return;
		}
		
		Campeonato campeonato = null;
		try {
			campeonato = sistema.getCampeonato(nomeCampeonato);			
		} catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
			return;
		}
	
		try {
			sistema.apostar(time, campeonato, colocacao, valor);
			System.out.println("APOSTA REGISTRADA!\n");
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		};
	}
	
	private static void statusAposta(Scanner sc, MrBetSistema sistema) {
		ArrayList<Aposta> apostas = sistema.getApostas();

		System.out.println("Apostas: \n");
		for (Aposta aposta : apostas) {
			System.out.print((apostas.indexOf(aposta) + 1) + ". ");
			System.out.println(aposta + "\n");
		}
	}
	
	private static void historico(Scanner sc, MrBetSistema sistema) {
		System.out.println("Participação mais frequente em campeonatos");
		ArrayList<Time> timesMaisFrequentes = sistema.getTimesMaisFrequentes();
		
		for (Time time : timesMaisFrequentes) {
			System.out.println(time + "\n");
		}
		
		System.out.println("Ainda não participou de campeonato");
		ArrayList<Time> timesSemParticipacao= sistema.getTimesNaoParticiparam();
		
		if (timesSemParticipacao.isEmpty()) {
			System.out.println();
		}
		for (Time time : timesSemParticipacao) {
			System.out.println(time + "\n");			
		}
		
		System.out.println("Popularidade em apostas");
		
		for (String popularidade : sistema.getPopularidadeApostas()) {
			System.out.println(popularidade);
		}
		System.out.println();
	}
}