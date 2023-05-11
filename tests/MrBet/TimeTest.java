package MrBet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeTest {
	Time time;
	
	@BeforeEach
	void prepareTime() {
		this.time = new Time("ID", "Time", "Mascote");
	}

	@Test
	void toStringTest() {
		assertEquals("[ID] Time / Mascote", time.toString(), "Deve retornar a string formatada de time");
	}

	@Test
	void equalsVazioTest() {
		assertFalse(time.equals(null), "Deve retornar false ja que o objeto é vazio");
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void equalsClasseDiferenteTest() {
		Campeonato campeonato = new Campeonato("Campeonato", 1);
		
		assertFalse(time.equals(campeonato), "Deve retornar false ja que o objeto passado é diferente do esperado");
	}

	@Test
	void equalsIgualTest() {
		Time timeIgual = new Time("ID", "NãoImporta", "NãoImporta");
		
		assertTrue(time.equals(timeIgual), "Deve retornar true ja que os id's são iguais");
	}
	
	@Test
	void equalsDiferenteTest() {
		Time timeDiferente = new Time("IDiferente", "TimeDiferente", "MascoteDiferente");
		
		assertFalse(time.equals(timeDiferente), "Deve retornar false ja que os id's são diferentes");
	}
	
	@Test
	void addCampeonatoNuloTest() {
		try {
			time.addCampeonato(null);
			fail("Deveria quebrar pois o campeonato passado no parâmetro é nulo");
		} catch (NullPointerException npe) {
			assertEquals("CAMPEONATO INVÁLIDO!\n", npe.getMessage(), "Deve retornar uma mensagem indicando que o campeonato é inválido");
		}
	}
	
	@Test
	void addCampeonatoTest() {
		Campeonato campeonato = new Campeonato("Campeonato", 1);
		assertTrue(time.addCampeonato(campeonato), "Deve retornar true mostrando que o campeonato foi adicionado");
	}
}
