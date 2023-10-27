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

}
