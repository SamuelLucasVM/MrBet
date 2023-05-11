package MrBet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CampeonatoTest {
	Campeonato campeonato;
	
	@BeforeEach
	void prepareCampeonato() {
		this.campeonato = new Campeonato("Campeonato", 1);
	}

	@Test
	void toStringTest() {
		assertEquals("* Campeonato - 0/1", campeonato.toString(), "Deve retornar a string formatada de campeonato");
	}
	
	@Test
	void equalsVazioTest() {
		assertFalse(campeonato.equals(null), "Deve retornar false pois o objeto passado é nulo");
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void equalsClasseDiferenteTest() {
		Time time = new Time("ID", "Time", "Mascote");
		assertFalse(campeonato.equals(time), "Deve retornar false pois o objeto passado é de uma classe diferente");
	}

	@Test
	void equalsIgualTest() {
		Campeonato campeonatoIgual = new Campeonato("Campeonato", 2);
		assertTrue(this.campeonato.equals(campeonatoIgual),"Deve retornar true pois os nomes dos campeonatos são iguais");
	}

	@Test
	void equalsDiferenteTest() {
		Campeonato campeonatoDiferente = new Campeonato("CampeonatoDiferente", 1);
		assertFalse(this.campeonato.equals(campeonatoDiferente),"Deve retornar false pois os nomes dos campeonatos são diferentes");
	}
	
	@Test
	void addTimeVazioTest() {
		try {
			campeonato.addTime(null);
			fail("Deveria quebrar pois passou um parâmetro nulo");
		} catch (NullPointerException npe) {
			assertEquals("TIME INVÁLIDO!\n", npe.getMessage(), "Deve retornar uma mensagem indicando que o time é inválido");
		}
	}
	
	@Test
	void addTimeTest() {
		Time time1 = new Time("ID", "Time1", "Mascote1");
		
		assertTrue(campeonato.addTime(time1), "Deve retornar true mostrando que o time foi adicionado");
		assertTrue(campeonato.addTime(time1), "Deve retornar true mostrando que o time foi adicionado");
	}
	
	@Test
	void addTimeCampeonatoCheioTest() {
		Time time1 = new Time("ID", "Time1", "Mascote1");
		campeonato.addTime(time1);
		
		Time time2 = new Time("ID2", "Time2", "Mascote2");
		try {
			campeonato.addTime(time2);
			fail("Deveria quebrar pois a quantidade máxima de times ja foi atingida nesse campeonato");
		} catch (IndexOutOfBoundsException iobe) {
			assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!\n", iobe.getMessage(), "Deve receber o erro de que ja atingiu o limite de times no campeonato");
		}
	}
	
	@Test
	void addApostaNulaTest() {
		try {
			campeonato.addAposta(null, 0, 0);
			fail("Deveria quebrar pois foi passado um time nulo");
		} catch (NullPointerException npe) {
			assertEquals("TIME INVÁLIDO!\n", npe.getMessage(), "Deve retornar uma mensagem indicando que o time é inválido");
		}
	}
	
	@Test
	void addApostaTest() {
		Time time = new Time("ID", "Time", "Mascote");

		assertTrue(campeonato.addAposta(time, 1, 100), "Deve retornar true, mostrando que o campeonato foi adicionado");
	}
	
	@Test
	void addApostaAcimaDoLimiteTest() {
		Time time = new Time("ID", "Time", "Mascote");
		
		try {
			campeonato.addAposta(time, 2, 100);
			fail("Deveria quebrar pois a posição está além da quantidade de times total no campeonato");
		} catch (IllegalArgumentException iae) {
			assertEquals("APOSTA NÃO REGISTRADA!\n", iae.getMessage(), "Deve retornar a mensagem de que a posição selecionada é inválida");
		}
	}

	@Test
	void addApostaAbaixoDoLimiteTest() {
		Time time = new Time("ID", "Time", "Mascote");
		
		try {
			campeonato.addAposta(time, 0, 100);
			fail("Deveria quebrar pois a posição está abaixo da quantidade de times total no campeonato");
		} catch (IllegalArgumentException iae) {
			assertEquals("APOSTA NÃO REGISTRADA!\n", iae.getMessage(), "Deve retornar a mensagem de que a posição selecionada é inválida");
		}
	}

}
