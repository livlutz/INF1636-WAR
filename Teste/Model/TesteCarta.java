package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteCarta {

	@Test
	public void TestaCarta() {
		Carta c = new Carta(0, null);
		assertNotNull(c);
	}

	@Test
	public void TestaGetTerritorio() {
		Carta c = new Carta(0, null);
		assertEquals(c.getTerritorio(), null);
	}

	@Test
	public void TestaSetTerritorio() {
		Carta c = new Carta(0, null);
		c.setTerritorio(null);
		assertEquals(c.getTerritorio(), null);
	}

	@Test
	public void TestaGetF() {
		Carta c = new Carta(0, null);
		assertEquals(c.getF(), Carta.Formato.quadrado);
	}

	@Test
	public void TestaSetF() {
		Carta c = new Carta(0, null);
		c.setF(Carta.Formato.circulo);
		assertEquals(c.getF(), Carta.Formato.circulo);
	}

}
