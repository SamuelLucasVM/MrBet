package MrBet;

import java.util.ArrayList;

/**
 * Classe que possui as informações dos times registradas no sistema,
 * possuindo informaçẽso como seu id, seu nome, seu mascote e seus 
 * campeonatos no qual está participando.
 * @author Samuel Lucas Vieira Matos
 */


public class Time {
	private String id;
	private String nome;
	private String mascote;
	private ArrayList<Campeonato> campeonatos;
	
	/**
	 * Construtor da classe de time, onde será passado seu identificador
	 * único ID, seu nome e o nome de seu mascote, podendo passar as duas
	 * últimas informações como uma string vázia mas o ID não.
	 * @param id Identificador único do time em formato de string
	 * @param nome Nome do time registrado
	 * @param mascote Mascote do time registrado
	 */
	
	public Time(String id, String nome, String mascote) {
		this.id = id;
		this.nome = nome;
		this.mascote = mascote;
		this.campeonatos = new ArrayList<Campeonato>();
	}
	
	/**
	 * Método que retorna a string formatada de time, levando informações
	 * como o id, o nome e o mascote do time.
	 * @return response String formatada do time
	 */

	@Override
	public String toString() {
		String response = "[" + id + "] " + nome + " / " + mascote; 
		return response;
	}
	
	/**
	 * Método que analisa se um time é igual ao outro, retornando false para 
	 * quando o objeto passado é nulo ou de classe diferente de time ou quando 
	 * o id do time for diferente do outro e retornando true pro contrario.
	 * @return boolean indicando se o objeto passado é igual ou não ao indicado
	 */
	
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
	
	/**
	 * Método que indica o código hash do time, sendo baseado no id de tal
	 * @return hashCode em formato de inteiro
	 */
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	/**
	 * Método que adiciona um campeonato na lista de campeonatos que um time
	 * possui, verificando se o campeonato é válido.
	 * @throws NullPointerException no caso do campeonato não existir e ser 
	 * passado como null
	 * @param campeonato Campeonato que será adicionado ao time
	 * @return boolean indicando se houve ou não a adição do campeonato no time
	 */
	
	public boolean addCampeonato(Campeonato campeonato) {
		if (campeonato == null) throw new NullPointerException("CAMPEONATO INVÁLIDO!\n");
		campeonatos.add(campeonato);
		return true;
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
}
