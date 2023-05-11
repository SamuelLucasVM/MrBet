package MrBet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApostaTest {
	Aposta aposta;
	
	@BeforeEach
	void prepareAposta() {
		Time time = new Time("ID", "Time", "Mascote");
		Campeonato campeonato = new Campeonato("Campeonato", 2);
		this.aposta = new Aposta(time, campeonato, 1, 100);
	}
	
	@Test
	void toStringTest() {
		assertEquals("[ID] Time / Mascote\nCampeonato\n1/2\nR$ 100.0", aposta.toString(), "Deve retornar a string formatada de aposta");
	}
	
	@Test
	void equalsVazioTest() {
		assertFalse(aposta.equals(null), "Deve retornar false pois o objeto passado é vazio");
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	void equalsClasseDiferenteTest() {
		Time time = new Time("ID", "Time", "Mascote");
		assertFalse(aposta.equals(time), "Deve retornar false pois o objeto passado é diferente do comparado");
	}
	
	@Test
	void equalsIgualTest() {
		Aposta apostaIgual = new Aposta(aposta.getTime(), aposta.getCampeonato(), 1, 100);
		assertTrue(aposta.equals(apostaIgual), "Deve retornar true pois a aposta é igual");
	}
	
	@Test
	void equalsDiferenteTest() {
		Time time = new Time("ID", "Time", "Mascote");
		Campeonato campeonato = new Campeonato("Campeonato", 2);
		
		Aposta apostaDiferente = new Aposta(time, campeonato, 1, 80);
		assertFalse(aposta.equals(apostaDiferente), "Deve retornar false pois a aposta é diferente");
	}

}
