package MrBet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MrBetSistemaTest {
	MrBetSistema sistema;
	
	@BeforeEach
	void prepareMrBetSistema() {
		sistema = new MrBetSistema();
	}

	@Test
	void getTimeTest() {
		Time time = new Time("ID", "Time", "Mascote");
		sistema.addTime("ID", "Time", "Mascote");
		
		assertEquals(time.getId(), sistema.getTime("ID").getId(), "Deve retornar o id do time de id ID");
	}
	
	@Test
	void getTimeInexistenteTest() {
		try {
			sistema.getTime("IDInexistente");
			fail("Deveria quebrar ao passar um id de um time inexistente");
		} catch (NullPointerException npe) {
			assertEquals("O TIME NÃO EXISTE!\n", npe.getMessage(), "Deve retornar a mensagem de erro indicando que o time não existe");
		}
	}

	@Test
	void getCampeonatoTest() {
		Campeonato campeonato = new Campeonato("Campeonato", 1);
		sistema.addCampeonato("Campeonato", 1);
		
		assertEquals(campeonato.getNome(), sistema.getCampeonato("Campeonato").getNome(), "Deve retornar o nome campeonato de nome Campeonato");
	}
	
	@Test
	void getCampeonatoInexistenteTest() {
		try {
			sistema.getCampeonato("CampeonatoInexistente");
			fail("Deveria quebrar ao passar um nome de um campeonato inexistente");
		} catch (NullPointerException npe) {
			assertEquals("O CAMPEONATO NÃO EXISTE!\n", npe.getMessage(), "Deve retornar a mensagem de erro indicando que o campeonato não existe");
		}
	}
	
	@Test
	void getTimesMaisFrequentesTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");

		sistema.addCampeonato("Campeonato1", 3);
		sistema.addCampeonato("Campeonato2", 3);
		
		sistema.addTimeCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID1"));
		assertEquals("ID1", sistema.getTimesMaisFrequentes().get(0).getId(), "Deve retornar o id do time mais frequente, no caso o time1");

		sistema.addTimeCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID2"));
		assertEquals("ID1", sistema.getTimesMaisFrequentes().get(1).getId(), "Deve retornar o id do time mais frequente empatado, no caso o time1");
		assertEquals("ID2", sistema.getTimesMaisFrequentes().get(0).getId(), "Deve retornar o id do time mais frequente empatado, no caso o time2");
		
		sistema.addTimeCampeonato(sistema.getCampeonato("Campeonato2"), sistema.getTime("ID2"));
		assertEquals("ID2", sistema.getTimesMaisFrequentes().get(0).getId(), "Deve retornar o id do time mais frequente, no caso o time2");
	}
	
	@Test
	void getTimesMaisFrequentesSemAddTimeEmCapeonatoTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");

		sistema.addCampeonato("Campeonato1", 3);
		sistema.addCampeonato("Campeonato2", 3);

		assertEquals("ID2", sistema.getTimesMaisFrequentes().get(0).getId(), "Deve retornar o id do time empatado com frequencia zero, nesse caso o time2");
		assertEquals("ID1", sistema.getTimesMaisFrequentes().get(1).getId(), "Deve retornar o id do time empatado com frequencia zero, nesse caso o time1");
	}
	
	@Test
	void getTimesMaisFrequentesSemTimeTest() {
		sistema.addCampeonato("Campeonato1", 3);
		
		assertEquals(true, sistema.getTimesMaisFrequentes().isEmpty(), "Deve retornar um array list vazio já que não há nenhum time registrado");
	}
	
	@Test
	void getTimesMaisFrequentesSemCampeonatoTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");

		assertEquals("ID2", sistema.getTimesMaisFrequentes().get(0).getId(), "Deve retornar o id do time empatado com frequencia zero, nesse caso o time2");
		assertEquals("ID1", sistema.getTimesMaisFrequentes().get(1).getId(), "Deve retornar o id do time empatado com frequencia zero, nesse caso o time1");
	}
	
	@Test
	void getTimesNaoParticiparamTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");

		sistema.addCampeonato("Campeonato1", 3);
	
		assertEquals(sistema.getTime("ID1"), sistema.getTimesNaoParticiparam().get(1), "Deve retornar um dos times que não participaram de nenhum campeonato");
		assertEquals(sistema.getTime("ID2"), sistema.getTimesNaoParticiparam().get(0), "Deve retornar um dos times que não participaram de nenhum campeonato");
		
		sistema.addTimeCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID1"));
		assertEquals(sistema.getTime("ID2"), sistema.getTimesNaoParticiparam().get(0), "Deve retornar o unico time que não participou de nenhum campeonato, no caso o time 2");
	}
	
	@Test
	void getTimesNaoParticiparamTodosParticipandoTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");

		sistema.addCampeonato("Campeonato1", 3);
		
		sistema.addTimeCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID1"));
		sistema.addTimeCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID2"));
		assertEquals(true, sistema.getTimesNaoParticiparam().isEmpty(), "Deve retornar true já que todos os times estão participando de pelo menos 1 campeonato");
	}
	
	@Test
	void getTimesNaoParticiparamSemTimeTest() {
		sistema.addCampeonato("Campeonato1", 3);
		
		assertEquals(true, sistema.getTimesNaoParticiparam().isEmpty(), "Deve retornar true já que não há nenhum time registrado");
	}
	
	@Test
	void getTimesNaoParticiparamSemCapeonatoTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");
		
		assertEquals(sistema.getTime("ID1"), sistema.getTimesNaoParticiparam().get(1), "Deve retornar um dos times que não participaram de nenhum campeonato");
		assertEquals(sistema.getTime("ID2"), sistema.getTimesNaoParticiparam().get(0), "Deve retornar um dos times que não participaram de nenhum campeonato");
	}
	
	@Test
	void getPopularidadeApostasTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");
		
		sistema.addCampeonato("Campeonato1", 3);
		
		sistema.apostar(sistema.getTime("ID1"), sistema.getCampeonato("Campeonato1"), 1, 100);
		ArrayList<String> popularidadeTimes = sistema.getPopularidadeApostas();
		assertEquals("Time1 / 1", popularidadeTimes.get(1), "Deve retornar a string representando a popularidade do time de id ID1 com mais uma aposta");
	}
	
	@Test
	void getPopularidadeApostasSemApostasTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");
		
		sistema.addCampeonato("Campeonato1", 3);
		
		ArrayList<String> popularidadeTimes = sistema.getPopularidadeApostas();
		assertEquals("Time2 / 0", popularidadeTimes.get(0), "Deve retornar a string representando a popularidade do time de id ID1");
		assertEquals("Time1 / 0", popularidadeTimes.get(1), "Deve retornar a string representando a popularidade do time de id ID2");
	}
	
	@Test
	void getApostasTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");
		
		sistema.addCampeonato("Campeonato1", 3);
		
		sistema.apostar(sistema.getTime("ID1"), sistema.getCampeonato("Campeonato1"), 1, 100);
		ArrayList<Aposta> apostas1 = sistema.getApostas();
		assertEquals("[ID1] Time1 / Mascote1\n"
				+ "Campeonato1\n"
				+ "1/3\n"
				+ "R$ 100.0", apostas1.get(0).toString(),"Deve retornar a string formatada da única aposta registrada no sistema");
	}
	
	@Test
	void getApostasVaziaTest() {
		ArrayList<Aposta> apostas = sistema.getApostas();
		
		assertEquals(true, apostas.isEmpty(),"Deve true já que não há apostas registradas no sistema");
	}
	
	@Test
	void addCampeonatoNomeNuloTest() {
		try {
			sistema.addCampeonato(null, 1);
		} catch (NullPointerException npe) {
			assertEquals("NOME DE CAMPEONATO NULO!\n", npe.getMessage(), "Deve retornar uma mensagem indicando que o nome do campeonato está nulo");
		}
	}
	
	@Test
	void addCampeonatoNomeVazioTest() {
		try {
			sistema.addCampeonato("", 1);
		} catch (IllegalArgumentException iae) {
			assertEquals("NOME DE CAMPEONATO VAZIO!\n", iae.getMessage(), "Deve retornar uma mensagem indicando que o nome do campeonato está vazio");
		}
	}
	
	@Test
	void addCampeonatoTest() {
		assertTrue(sistema.addCampeonato("Campeonato", 1), "Deve retornar true mostrando que o campeonato foi adicionado");
	}
	
	@Test
	void addCampeonatoExistenteTest() {
		sistema.addCampeonato("Campeonato", 1);
		
		try {
			sistema.addCampeonato("Campeonato", 1);
			fail("Deveria quebrar já que o campeonato com nome Campeonato já existe");
		} catch (IllegalArgumentException iae) {
			assertEquals("CAMPEONATO JÁ EXISTE!\n", iae.getMessage(), "Deverá retornar a mensagem indicando que o campeonato já existe");
		}
	}
	
	@Test
	void addTimeIdNuloTest() {
		try {
			sistema.addTime(null, "Time", "Mascote");
			fail("Deveria quebrar pois está passando um id nulo como parâmetro");
		} catch (NullPointerException npe) {
			assertEquals("ID DO TIME NULO!\n", npe.getMessage(), "Deve retornar uma mensagem indicando que o ID do time está nulo");
		}
	}
	
	@Test
	void addTimeIdVazioTest() {
		try {
			sistema.addTime("", "Time", "Mascote");
			fail("Deveria quebrar pois está passando um id vazio como parâmetro");
		} catch (IllegalArgumentException iae) {
			assertEquals("ID DO TIME VAZIO!\n", iae.getMessage(), "Deve retornar uma mensagem indicando que o ID do time está vazio");
		}
	}
	
	@Test
	void addTimeTest() {
		assertTrue(sistema.addTime("ID", "Time", "Mascote"), "Deve retornar true mostrando que o time foi adicionado");
	}
	
	@Test
	void addTimeExistenteTest() {
		sistema.addTime("ID", "Time", "Mascote");
		
		try {
			sistema.addTime("ID", "Time", "Mascote");
			fail("Deveria quebrar já que o time com id ID já existe");
		} catch (IllegalArgumentException iae) {
			assertEquals("TIME JÁ EXISTE!\n", iae.getMessage(), "Deverá retornar a mensagem indicando que o time já existe");
		}
	}
	
	@Test
	void addTimeCampeonatoTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");
		
		sistema.addCampeonato("Campeonato1", 1);
		
		assertTrue(sistema.addTimeCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID1")), "Deve retornar true mostrando que o time foi adicionado");
	}
	
	@Test
	void addTimeCampeonatoCheioTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");
		
		sistema.addCampeonato("Campeonato1", 1);
		
		sistema.addTimeCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID1"));
		
		try {
			sistema.addTimeCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID2"));
			fail("Deveria quebrar já que não há mais espaço para times no campeonato");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!\n", iobe.getMessage(), "Deve retornar uma mensagem indicando que não há mais espaço para times no campeonato");
		}
	}
	
	@Test
	void timeEstaEmCampeonatoNuloTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		
		try {
			sistema.timeEstaEmCampeonato(null, sistema.getTime("ID1"));
			fail("Deveria quebrar já que está passando um campeonato nulo como parâmetro");
		} catch (NullPointerException npe) {
			assertEquals("CAMPEONATO INVÁLIDO!\n", npe.getMessage(), "Deve retornar uma mensagem indicando que o campeonato é inválido");
		}
	}
	
	@Test
	void timeNuloEstaEmCampeonatoTest() {
		sistema.addCampeonato("Campeonato1", 1);
		
		try {
			sistema.timeEstaEmCampeonato(sistema.getCampeonato("Campeonato1"), null);
			fail("Deveria quebrar já que está passando um time nulo como parâmetro");
		} catch (NullPointerException npe) {
			assertEquals("TIME INVÁLIDO!\n", npe.getMessage(), "Deve retornar uma mensagem indicando que o time é inválido");
		}
	}
	
	@Test
	void timeEstaEmCampeonatoTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		
		sistema.addCampeonato("Campeonato1", 1);
		
		sistema.addTimeCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID1"));
		
		assertEquals(true, sistema.timeEstaEmCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID1")), "Deve retornar true já que o time está no campeonato");
	}
	
	@Test
	void timeNaoEstaEmCampeonatoTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		
		sistema.addCampeonato("Campeonato1", 1);

		assertEquals(false, sistema.timeEstaEmCampeonato(sistema.getCampeonato("Campeonato1"), sistema.getTime("ID1")), "Deve retornar false já que o time não está no campeonato");
	}
	
	@Test
	void apostarTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");
		
		sistema.addCampeonato("Campeonato1", 1);
		
		assertTrue(sistema.apostar(sistema.getTime("ID1"),sistema.getCampeonato("Campeonato1"), 1, 100), "Deve true mostrando que a aposta foi registrada");
	}
	
	@Test
	void apostaAcimaDoLimiteTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");
		
		sistema.addCampeonato("Campeonato1", 1);
		
		try {
			sistema.apostar(sistema.getTime("ID2"),sistema.getCampeonato("Campeonato1"), 2, 100);
			fail("Deveria quebrar já que a aposta é inválida");
		} catch (IllegalArgumentException iae) {
			assertEquals("APOSTA NÃO REGISTRADA!\n", iae.getMessage(), "Deve retornar uma mensagem de erro indicando que a aposta não foi registrada");
		}
	}
	
	@Test
	void apostaAbaixoDoLimiteTest() {
		sistema.addTime("ID1", "Time1", "Mascote1");
		sistema.addTime("ID2", "Time2", "Mascote2");
		
		sistema.addCampeonato("Campeonato1", 1);
		
		try {
			sistema.apostar(sistema.getTime("ID2"),sistema.getCampeonato("Campeonato1"), 0, 100);
			fail("Deveria quebrar já que a aposta é inválida");
		} catch (IllegalArgumentException iae) {
			assertEquals("APOSTA NÃO REGISTRADA!\n", iae.getMessage(), "Deve retornar uma mensagem de erro indicando que a aposta não foi registrada");
		}
	}
}
