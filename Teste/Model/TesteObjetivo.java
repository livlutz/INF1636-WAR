package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteObjetivo {
	
	@Test
	public void testeConstrutor() {
		Objetivo o = new Objetivo();
		assertNotNull(o);
	}
	
	//tem q ver se e assim q testa 
	@Test
	public void testeGetObjAleatorio() {
		Objetivo o = new Objetivo();
		Objetivo.criaObjetivos();
		String s = Objetivo.descricao.get(0);
		assertEquals(o.getObjetivoAleatorio(),s);
	}

}
