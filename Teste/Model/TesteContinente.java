package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TesteContinente {

	@Test
	public void TestaConstrutor() {
		Continente c = new Continente("Continente",0, null);
		assertNotNull(c);
	}
	
	@Test
	public void testeAddTerritorios() {
		Continente c = new Continente("Continente",0, null);
		Territorio t = new Territorio("Teste");
		ArrayList <Territorio> a = new ArrayList <Territorio> ();
		a.add(t);
		c.addTerritorio(t);
		assertEquals(a,c.territorios);
	}
	
	@Test
	public void testeDominado() {
		Continente c = new Continente("Continente",0, null);
		Territorio t = new Territorio("Territorio Teste 1");
		Territorio a = new Territorio("Territorio Teste 2");
		Jogador j = new Jogador("Jooj", null);
		t.setJogador(j);
		a.setJogador(j);
		c.addTerritorio(t);
		c.addTerritorio(a);
		assertTrue(c.dominado(j));
	}
	
	@Test
	public void testeNoContinente() {
		Continente c = new Continente(null, 0, null);
		Territorio t = new Territorio("A");
		c.territorios.add(t);
		
		assertTrue(c.noContinente(t));
		
		t = new Territorio("B");
		assertFalse(c.noContinente(t));
		
	}

}
