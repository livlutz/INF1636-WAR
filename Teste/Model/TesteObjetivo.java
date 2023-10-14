package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteObjetivo {
	
	@Test
	public void testeConstrutor() {
		Objetivo o = new Objetivo();
		assertNotNull(o);
	}
	
	@Test
	public void testeGetObjAleatorio() {
		Objetivo o = new Objetivo();
		Objetivo.criaObjetivos();
		assertNotNull(o.getObjetivoAleatorio());
	}

}
