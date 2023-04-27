package Mr.Bet;

public class Campeonato {
	private String nome;
	private Time[] times;
	private int timeVazio;
	
	public Campeonato(String nome, int qtdeParticipantes) {
		this.nome = nome;
		this.times = new Time[qtdeParticipantes];
		this.timeVazio = 0;
	}
	
	public String getNome() {
		return nome;
	}
	
	public boolean addTime(Time time) {
		if (timeVazio == times.length) {
			return false;
		}
		else {
			times[timeVazio++] = time;
			return true;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) return false;
		
		Campeonato o = (Campeonato) obj;
		
		if (o.getNome().toLowerCase().equals(this.getNome().toLowerCase())) {
			return true;
		}
		else {
			return false;
		}
	}
}
