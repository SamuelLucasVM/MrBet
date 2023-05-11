package MrBet;

import java.util.ArrayList;

public class Time {
	private String id;
	private String nome;
	private String mascote;
	private ArrayList<Campeonato> campeonatos;
	
	public Time(String id, String nome, String mascote) {
		this.id = id;
		this.nome = nome;
		this.mascote = mascote;
		this.campeonatos = new ArrayList<Campeonato>();
	}

	@Override
	public String toString() {
		String response = "[" + id + "] " + nome + " / " + mascote; 
		return response;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) return false;
		
		Time o = (Time) obj;
		
		if (o.getId().equals(this.getId())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	public String getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public ArrayList<Campeonato> getCampeonatos() {
		return campeonatos;
	}
	
	public boolean addCampeonato(Campeonato campeonato) {
		if (campeonato == null) throw new NullPointerException("CAMPEONATO INVÁLIDO!\n");
		campeonatos.add(campeonato);
		return true;
	}
	
}
