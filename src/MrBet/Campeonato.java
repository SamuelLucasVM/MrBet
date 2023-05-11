package MrBet;

import java.util.ArrayList;
import java.util.HashSet;

public class Campeonato {
	private String nome;
	private int qtdeParticipantes;
	private HashSet<Time> times;
	private ArrayList<Aposta> apostas;
	
	public Campeonato(String nome, int qtdeParticipantes) {
		this.nome = nome;
		this.qtdeParticipantes = qtdeParticipantes;
		this.times = new HashSet<Time>();
		this.apostas = new ArrayList<Aposta>();
	}
	
	@Override
	public String toString() {
		String response = "* " + nome + " - " + times.size() + "/" + qtdeParticipantes;
		return response;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != this.getClass()) return false;
		
		Campeonato campeonato = (Campeonato) obj;
		
		if (campeonato.getNome().equals(this.getNome())) {
			return true;
		}
		else {
			return false;			
		}
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}
	
	public String getNome() {
		return nome;
	}
	
	public HashSet<Time> getTimes() {
		return times;
	}
	
	public int getQtdeParticipantes() {
		return qtdeParticipantes;
	}
	
	public ArrayList<Aposta> getApostas(){
		return apostas;
	}
	
	public boolean addTime(Time time) {
		if (time == null) throw new NullPointerException("TIME INVÁLIDO!\n");
		if (times.size() >= qtdeParticipantes && !times.contains(time)) throw new IndexOutOfBoundsException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!\n");
		times.add(time);
		return true;
	}
	
	
	public boolean addAposta(Time time, int colocacao, double valor) {
		if (time == null) throw new NullPointerException("TIME INVÁLIDO!\n");
		if (colocacao <= 0 || colocacao > qtdeParticipantes) throw new IllegalArgumentException("APOSTA NÃO REGISTRADA!\n");
		
		Aposta aposta = new Aposta(time, this, colocacao, valor);	
		apostas.add(aposta);
		return true;
	}
}
