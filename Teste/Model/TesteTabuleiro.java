package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteTabuleiro {

	@Test
	public void testaConstrutor() {
		Tabuleiro t = new Tabuleiro();
		assertNotNull(t);
	}
	
	@Test
	public void testeVerificarAtaque() {
		Tabuleiro t = new Tabuleiro();
		Territorio a = new Territorio("A");
		Territorio b = new Territorio("B");
		Territorio c = new Territorio("C");
		
		a.setQntExercitos(10);
		b.setQntExercitos(7);
		c.setQntExercitos(1);
		a.AddAdjacente(b);
		c.AddAdjacente(a);
		
		assertTrue(t.VerificarAtaque(a, b));
		assertFalse(t.VerificarAtaque(c, a));
	}
	
	//falta implementar na classe Tabuleiro
	@Test
	public void testeRealizaAtque() {
		
	}
	
	//falta implementar o metodo incializaContinente na classe Tabuleiro
	@Test
	public void testeInicializaContinente() {}
	
	@Test
	public void testeMoverExercitos() {
		Tabuleiro t = new Tabuleiro();
		Territorio a = new Territorio("A");
		Territorio b = new Territorio("B");
		Territorio c = new Territorio("C");
		Territorio d = new Territorio("D");
		a.setQntExercitos(5);
		b.setQntExercitos(2);
		t.MoverExercitos(3, a, b);
		c.setQntExercitos(2);
		d.setQntExercitos(1);
		t.MoverExercitos(2, c, d);
		
		assertEquals(a.getQntExercitos(),2);
		assertEquals(b.getQntExercitos(),5);
		assertEquals(c.getQntExercitos(),2);
		assertEquals(d.getQntExercitos(),1);
	}
	
	//falta implementar na classe tabuleiro
	@Test
	public void testeVerificarObjetivoConcluido() {
		
	}
	
	//falta implementar na classe tabuleiro
	@Test
	public void testeVerificarExercitosASeremPosicionados() {
			
	}
	
	//discutir como vamos implementar os testes das outras funoes

}
