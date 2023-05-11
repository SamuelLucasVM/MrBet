package MrBet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que faz papel de controller, respositório e services do sistema MrBet, 
 * realizando o intermédio entre a classe de interface com o usuário main e as
 * outras classes do projeto.
 * No MrBetSistema há a delegação de trabalhos vindo do main para outras classes
 * e o armazenamento de campeonatos e times, além das apostas.
 * @author Samuel Lucas Vieira Matos
 */

public class MrBetSistema {
	private HashMap<String, Campeonato> campeonatos;
	private HashMap<String, Time> times;
	
	/**
	 * Construtor do sistema, servindo para inicializar os repositorios e métodos
	 * utilizados no projeto.
	 */
	
	public MrBetSistema() {
		this.campeonatos = new HashMap<String, Campeonato>();
		this.times = new HashMap<String, Time>();
	}
	
	/**
	 * Método que pede o ID do time solicitado e retorna-o, verificando se existe.
	 * @throws NullPointerException no caso de o time não existir e ser nulo no map
	 * @param codigo Codigo ID do time que será retornao
	 * @return time que solicitado pelo parâmetro código
	 */
	
	public Time getTime(String codigo) {
		if (times.get(codigo) == null) throw new NullPointerException("O TIME NÃO EXISTE!\n");
		return times.get(codigo);
	}
	
	/**
	 * Método que pede o nome do campeonato solicitado e retorna-o verificando se
	 * existe.
	 * @throws NullPointerException no caso do campeonato não exisitir e ser nulo no map
	 * @param nome Nome do campeonato solicitado
	 * @return campeonato que foi solicitado pelo parâmetro nome
	 */
	
	public Campeonato getCampeonato(String nome) {
		if (campeonatos.get(nome) == null) throw new NullPointerException("O CAMPEONATO NÃO EXISTE!\n");
		return campeonatos.get(nome);
	}
	
	/**
	 * Método que retorna os times que estão mais frequentes nos campeonatos, passando
	 * por todos os times e pegando os que estão empatados ou sozinhos na quantia de 
	 * campeonatos cadastrados.
	 * @return timesMaisFrequentes que é um array list possuindo todos os times que
	 * ficaram em primeiro lugar no ranking de frequência em campeonatos
	 */
	
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
	
	/**
	 * Método que retorna os times que não estão inscritos em nenhum campeonato,
	 * passando por todos os times e analisando se sua lista de campeonatos está vazia
	 * ou não
	 * @return timesSemParticipacao que é um array list dos times que tem sua lista de 
	 * campeonatos vazia
	 */
	
	public ArrayList<Time> getTimesNaoParticiparam() {
		ArrayList<Time> timesSemParticipacao = new ArrayList<Time>();
		
		for (Time time : times.values()) {
			if (time.getCampeonatos().size() == 0) {
				timesSemParticipacao.add(time);
			}
		}
		
		return timesSemParticipacao;
	}
	
	/**
	 * Método que retorna a popularidade de todos os times em apostas, ou seja
	 * em quantas apostas os times estão.
	 * @return popularidadeApostas que é um array list de strings formatadas 
	 * contendo nome e popularidade do time
	 */
	
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
	
	/**
	 * Método que retorna as apostas registradas no sistema.
	 * @return apostas que é um array list de apostas
	 */
	
	public ArrayList<Aposta> getApostas() {
		ArrayList<Aposta> apostas = new ArrayList<Aposta>();  
		
		for (Campeonato campeonato : campeonatos.values()) {
			for (Aposta aposta : campeonato.getApostas()) {
				apostas.add(aposta);				
			}
		}
		
		return apostas;
	}
	
	/**
	 * Método que adiciona campeonatos no sistema com as informações de seu nome e
	 * sua quantidade máxima de participantes possível, verificando se o nome do 
	 * campeonato é vazio ou nulo, lançando execessões nesses casos.
	 * @throws NullPointerException no caso do nome do campeonato for nulo
	 * @throws IllegalArgumentException no caso do nome do campeonato for vazio
	 * @param nomeCamp Nome que será registrado no campeonato como indetificador único
	 * @param qtdeParticipantesCamp Quantidade de participantes máxima no campeonato
	 * @return boolean retornando se houve ou não a adição do campeonato no sistema
	 */

	public boolean addCampeonato(String nomeCamp, int qtdeParticipantesCamp) {
		if (nomeCamp == null) throw new NullPointerException("NOME DE CAMPEONATO NULO!\n");
		if (nomeCamp == "") throw new IllegalArgumentException("NOME DE CAMPEONATO VAZIO!\n");
		
		if (containsKeyIndistinct(nomeCamp)) throw new IllegalArgumentException("CAMPEONATO JÁ EXISTE!\n");
		
		Campeonato novoCampeonato = new Campeonato(nomeCamp, qtdeParticipantesCamp);
		campeonatos.put(nomeCamp, novoCampeonato);
		return true;
	}
	
	/**
	 * Método que adiciona times no sistema com as informações de seu id e
	 * seu nome e o nome de seu mascote vericando se o id do time é vazio ou nulo,
	 * lançando execessões nesses casos.
	 * @throws NullPointerException no caso do id do time for nulo
	 * @throws IllegalArgumentException no caso do id do time for vazio
	 * @param idTime Identificador único do time no formato de string
	 * @param nomeTime Nome do time que será adicionado no sistema
	 * @param mascoteTime Mascote do time que será adicionado no sistema, no formato
	 * de string
	 * @return boolean retornando se houve ou não a adição do time no sistema
	 */

	public boolean addTime(String idTime, String nomeTime, String mascoteTime) {
		if (idTime == null) throw new NullPointerException("ID DO TIME NULO!\n");
		if (idTime == "") throw new IllegalArgumentException("ID DO TIME VAZIO!\n");
		
		if (times.containsKey(idTime)) throw new IllegalArgumentException("TIME JÁ EXISTE!\n");
		
		Time novoTime = new Time(idTime, nomeTime, mascoteTime);
		times.put(idTime, novoTime);
		return true;
	}
	
	/**
	 * Método que adiciona um time informado num campeonato informado, e que adiciona
	 * o campeonato na lista de campeonatos de um time, lançando uma excessão lançada 
	 * dentro da classe de campeonato indicando se o campeonato já está lotado.
	 * @throws IndexOutOfBoundsException no caso do campeonato estar sem vagas para times
	 * @param campeonato Campeonato em que será adicionado o time
	 * @param time Time que será adicionado no campeonato
	 * @return boolean retornando se o time foi adicionado no campeonato
	 */
	
	public boolean addTimeCampeonato(Campeonato campeonato, Time time) {
		try {
			campeonato.addTime(time);			
			time.addCampeonato(campeonato);
			return true;
		} catch (IndexOutOfBoundsException iobe) {
			throw iobe;
		}
	}
	
	/**
	 * Método que verifica se um time está ou não em um campeonato indicado,
	 * verificando se o time ou o campeonato existem e lançando excessões nesses casos.
	 * @throws NullPointerException no caso de o campeonato não existir e estar como nulo no map
	 * @throws NullPointerException no caso de o time não existir e estar como nulo no map
	 * @param campeonato Campeonato em que verifica-se se o time está
	 * @param time Time que será verificado se está no campeonato
	 * @return boolean indicando se o time está no campeonato, retornando true, ou não, 
	 * retornando false
	 */
	
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
	
	/**
	 * Método que serve para registrar apostas no campeonato indicando o time, a colocação 
	 * e o valor apostado pelo apostador lançando uma excessão lançada na classe de campeonato
	 * no caso da aposta ser inválida.
	 * @throws NullPointerException no caso de o time não existir e estar como nulo no map
	 * @throws IllegalArgumentException no caso de a colocação indicada for abaixo ou acima 
	 * dos limites da quantidade de participantes do campeonato
	 * @param time Time em qual será apostado
	 * @param campeonato Campeonato no qual será apostado e registrado a aposta
	 * @param colocacao Colocação apostada pelo apostador
	 * @param valor Valor em dinheiro apostado pelo apostador
	 * @return boolean retornando se houve ou não o registro da aposta
	 */
	
	public boolean apostar(Time time, Campeonato campeonato, int colocacao, double valor) {
		try {
			campeonato.addAposta(time, colocacao, valor);	
			return true;
		} catch (Exception e) {
			throw e;
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
