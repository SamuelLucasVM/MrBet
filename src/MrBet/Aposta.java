package MrBet;

/**
 * Classe que possui as informações das apostas registradas no sistema,
 * possuindo informaçẽso como time, campeonato, colocação e valor.
 * @author Samuel Lucas Vieira Matos
 */

public class Aposta {
	private Time time;
	private Campeonato campeonato;
	private int colocacao;
	private double valor;
	
	/**
	 * Construtor de aposta, que recebe time, campeonato, colocação e valor,
	 * a verificação de validade dos parâmetros ocorre previamente na classe 
	 * de sistema, assim sem a necessidade de testar novamente no construtor.
	 * @param time Time em que está se apostando
	 * @param campeonato Campeonato em que está o time que esta apostando
	 * @param colocacao Colocação na qual o apostador aposta em que o time ficará
	 * @param valor Valor que o apostador colocou em sua aposta
	 */
	
	public Aposta(Time time, Campeonato campeonato, int colocacao, double valor) {
		this.time = time;
		this.campeonato = campeonato;
		this.colocacao = colocacao;
		this.valor = valor;
	}
	
	/**
	 * Método que retorna as informações formatadas de nome do time e campeonato
	 * da aposta além da colocação e valor apostado.
	 * @return response String formatada 
	 */
	
	@Override
	public String toString() {
		String response = time.toString() + "\n" + campeonato.getNome() + "\n" + colocacao + "/" + campeonato.getQtdeParticipantes() + "\n" + "R$ " + valor;
		return response;
	}
	
	/**
	 * Método que analisa se uma aposta é igual à outra, retornando false para 
	 * quando o objeto passado é nulo ou de classe diferente de aposta ou quando 
	 * as informações das duas apostas forem diferentes por completo e retornando 
	 * true pro contrario.
	 * @return boolean indicando se o objeto passado é igual ou não ao indicado
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != this.getClass()) return false;
		
		Aposta aposta = (Aposta) obj;
		
		if (aposta.getTime().equals(this.getTime()) && aposta.getCampeonato().equals(this.getCampeonato()) && aposta.getColocacao() == this.getColocacao() && aposta.getValor() == this.getValor()) {
			return true;
		}
		else {	
			return false;
		}		
	}
	
	public Time getTime() {
		return time;
	}
	
	public int getColocacao() {
		return colocacao;
	}
	
	public Campeonato getCampeonato() {
		return campeonato;
	}
	
	public double getValor() {
		return valor;
	}
}