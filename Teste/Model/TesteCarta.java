package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteCarta {

	@Test
	public void TestaCarta() {
		Carta c = new Carta(0, null);
		assertEquals(c.f, Carta.Formato.quadrado);
		assertEquals(c.getTerritorio(), null);
	}

	@Test
	public void TestaGetTerritorio() {
		Carta c = new Carta(0, null);
		assertEquals(c.getTerritorio(), null);
	}

}
