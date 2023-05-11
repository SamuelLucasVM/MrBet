package MrBet;

import java.util.ArrayList;
import java.util.HashMap;

public class MrBetSistema {
	private HashMap<String, Campeonato> campeonatos;
	private HashMap<String, Time> times;		
	
	public MrBetSistema() {
		this.campeonatos = new HashMap<String, Campeonato>();
		this.times = new HashMap<String, Time>();
	}
	
	public Time getTime(String codigo) {
		if (times.get(codigo) == null) throw new NullPointerException("O TIME NÃO EXISTE!\n");
		return times.get(codigo);
	}
	
	public Campeonato getCampeonato(String nome) {
		if (campeonatos.get(nome) == null) throw new NullPointerException("O CAMPEONATO NÃO EXISTE!\n");
		return campeonatos.get(nome);
	}
	
	public ArrayList<Time> getTimesMaisFrequentes() {
		ArrayList<Time> timesMaisFrequentes = new ArrayList<Time>();
		int maiorFrequencia = 0;
		
		for (Time time : times.values()) {
			if (time.getCampeonatos().size() == maiorFrequencia) {
				timesMaisFrequentes.add(time);
			}
			else if (time.getCampeonatos().size() > maiorFrequencia) {
				timesMaisFrequentes.clear();
				timesMaisFrequentes.add(time);
				maiorFrequencia = time.getCampeonatos().size();
			}
		}
		
		return timesMaisFrequentes;
	}
	
	public ArrayList<Time> getTimesNaoParticiparam() {
		ArrayList<Time> timesSemParticipacao = new ArrayList<Time>();
		
		for (Time time : times.values()) {
			if (time.getCampeonatos().size() == 0) {
				timesSemParticipacao.add(time);
			}
		}
		
		return timesSemParticipacao;
	}
	
	public ArrayList<String> getPopularidadeApostas() {
		ArrayList<String> popularidadeApostas = new ArrayList<String>();
		
		for (Time time : times.values()) {
			String popularidade = time.getNome() + " / ";
			int qtdePrimeiroLugar = 0;
			
			for (Campeonato campeonato : campeonatos.values()) {				
				for (Aposta aposta : campeonato.getApostas()) {
					if (aposta.getTime() == time && aposta.getColocacao() == 1) {
						qtdePrimeiroLugar++;
					}
				}
			}
			popularidade += qtdePrimeiroLugar;
			
			popularidadeApostas.add(popularidade);
		}
		
		return popularidadeApostas;
	}
	
	public ArrayList<Aposta> getApostas() {
		ArrayList<Aposta> apostas = new ArrayList<Aposta>();  
		
		for (Campeonato campeonato : campeonatos.values()) {
			for (Aposta aposta : campeonato.getApostas()) {
				apostas.add(aposta);				
			}
		}
		
		return apostas;
	}

	public boolean addCampeonato(String nomeCamp, int qtdeParticipantesCamp) {
		if (nomeCamp == null) throw new NullPointerException("NOME DE CAMPEONATO NULO!\n");
		if (nomeCamp == "") throw new IllegalArgumentException("NOME DE CAMPEONATO VAZIO!\n");
		
		if (containsKeyIndistinct(nomeCamp)) throw new IllegalArgumentException("CAMPEONATO JÁ EXISTE!\n");
		
		Campeonato novoCampeonato = new Campeonato(nomeCamp, qtdeParticipantesCamp);
		campeonatos.put(nomeCamp, novoCampeonato);
		return true;
	}

	public boolean addTime(String idTime, String nomeTime, String mascoteTime) {
		if (idTime == null) throw new NullPointerException("ID DO TIME NULO!\n");
		if (idTime == "") throw new IllegalArgumentException("ID DO TIME VAZIO!\n");
		
		if (times.containsKey(idTime)) throw new IllegalArgumentException("TIME JÁ EXISTE!\n");
		
		Time novoTime = new Time(idTime, nomeTime, mascoteTime);
		times.put(idTime, novoTime);
		return true;
	}
	
	public boolean addTimeCampeonato(Campeonato campeonato, Time time) {
		try {
			campeonato.addTime(time);			
			time.addCampeonato(campeonato);
			return true;
		} catch (IndexOutOfBoundsException iobe) {
			throw iobe;
		}
	}
	
	public boolean timeEstaEmCampeonato(Campeonato campeonato, Time time) {
		if (campeonato == null) throw new NullPointerException("CAMPEONATO INVÁLIDO!\n");
		if (time == null) throw new NullPointerException("TIME INVÁLIDO!\n");
		for (Time timeIncluso : campeonato.getTimes()) {
			if (time.equals(timeIncluso)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean apostar(Time time, Campeonato campeonato, int colocacao, double valor) {
		try {
			campeonato.addAposta(time, colocacao, valor);	
			return true;
		} catch (IllegalArgumentException iae) {
			throw iae;
		}
	}
	
	private boolean containsKeyIndistinct(String key) {
		for (String campeonato : campeonatos.keySet()) {
			if (campeonato.toLowerCase().equals(key.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
}
