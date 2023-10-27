package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteCartas {

	@Test
	public void testaConstrutor() {
		Territorio t = new Territorio(null);
		Cartas c = new Cartas (1,t);
		assertNotNull(c);
	}
	
	@Test
	public void testaTerrPertenceJogador() {
		Territorio t = new Territorio("Teste");
		Cartas c = new Cartas (1,t);
		Jogador j = new Jogador(null, 0);
		Jogador a = new Jogador(null, 1);
		j.addTerritorio(t);
		assertTrue(c.terrPertenceJogador(j));
		assertFalse(c.terrPertenceJogador(a));
	}
	

}
