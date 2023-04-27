package Mr.Bet;

public class Time {
	private String id;
	private String nome;
	private String mascote;
	
	public Time(String id, String nome, String mascote) {
		this.id = id;
		this.nome = nome;
		this.mascote = mascote;
	}
	
	public String getId() {
		return id;
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
}
