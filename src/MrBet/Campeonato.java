package MrBet;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Classe que armazena todas as informações necessárias para o funcionamento
 * de campeonatos no sistema, possuindo métodos que alteram a própria classe,
 * adicionando times e apostas a si.
 * @author Samuel Lucas Vieira Matos
 */

public class Campeonato {
	private String nome;
	private int qtdeParticipantes;
	private HashSet<Time> times;
	private ArrayList<Aposta> apostas;
	
	/**
	 * Construtor de campeonato que recebe nome como indentificador único da
	 * classe e a quantidade de participantes máxima que se pode ter em si,
	 * a verificação se o nome é válido ocorre antes de chamar o construtor. 
	 * @param nome Nome do campeonato, que serve para o identificar
	 * @param qtdeParticipantes Quantidade de participantes máxima que se
	 * pode ser adicionado no campeonato
	 */
	
	public Campeonato(String nome, int qtdeParticipantes) {
		this.nome = nome;
		this.qtdeParticipantes = qtdeParticipantes;
		this.times = new HashSet<Time>();
		this.apostas = new ArrayList<Aposta>();
	}
	
	/**
	 * Método que retorna a string formatada de campeonato, levando informações
	 * como o nome, a quantidade de times já inscritos no campeonato e a 
	 * quantidade máxima de times que podem ser adicionados em si.
	 * @return response String formatada do campeonato 
	 */
	
	@Override
	public String toString() {
		String response = "* " + nome + " - " + times.size() + "/" + qtdeParticipantes;
		return response;
	}
	
	/**
	 * Método que analisa se um campeonato é igual ao outro, retornando false para 
	 * quando o objeto passado é nulo ou de classe diferente de campeonato ou quando
	 * o nome do campeonato é diferente do outro e retornando true pro contrario.
	 * @return boolean indicando se o objeto passado é igual ou não ao indicado
	 */
	
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
	
	/**
	 * Método que indica o código hash do campeonato, sendo baseado no nome de tal
	 * @return hashCode em formato de inteiro
	 */
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}
	
	/**
	 * Método que adicona o time passado no campeonato, verificando se o time é
	 * ou não válido e se já atingiu a quantidade máxima de participantes.
	 * @throws NullPointerException no caso do time não existir e ser passado como null
	 * @param time Time que será adicionado no campeonato
	 * @return boolean retornando se foi realizado ou não a adição do time no
	 * campeonato
	 */
	
	public boolean addTime(Time time) {
		if (time == null) throw new NullPointerException("TIME INVÁLIDO!\n");
		if (times.size() >= qtdeParticipantes && !times.contains(time)) throw new IndexOutOfBoundsException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!\n");
		times.add(time);
		return true;
	}
	
	/**
	 * Método que adiciona apostas ao campeonato em questão, verificando se o time
	 * e se a colocação passada são ou não válidos.
	 * @throws NullPointerException no caso do time não existir e ser passado como null
	 * @throws IllegalArgumentException no caso da colocação passada ser maior ou
	 * menor do que a os limites de quantidade de participantes no campeonato
	 * @param time Time no qual será colocado a aposta
	 * @param colocacao Colocação em que o apostador acredita que o time terminará
	 * @param valor Valor em dinheiro do quanto o apostador investiu na aposta
	 * @return boolean indicando se foi ou não realizada a aposta
	 */
	
	public boolean addAposta(Time time, int colocacao, double valor) {
		if (time == null) throw new NullPointerException("TIME INVÁLIDO!\n");
		if (colocacao <= 0 || colocacao > qtdeParticipantes) throw new IllegalArgumentException("APOSTA NÃO REGISTRADA!\n");
		
		Aposta aposta = new Aposta(time, this, colocacao, valor);	
		apostas.add(aposta);
		return true;
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
	 
}
