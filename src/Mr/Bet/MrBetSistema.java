package Mr.Bet;

import java.util.HashMap;

public class MrBetSistema {
	private HashMap<String, Campeonato> campeonatos;
	private HashMap<String, Time> times;

	public void addCampeonato(String nomeCamp, int qtdeParticipantesCamp) {
		campeonatos.put(nomeCamp, new Campeonato(nomeCamp, qtdeParticipantesCamp));
	}

	public void addTime(String idTime, String nomeTime, String mascoteTime) {
		times.put(idTime, new Time(idTime, nomeTime, mascoteTime));
	}
}
