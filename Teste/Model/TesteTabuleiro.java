package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class TesteTabuleiro {
	
	/*@Test
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
		
		Jogador j = new Jogador(null, 20);
		Jogador k = new Jogador(null, 1);
		
		a.setJogador(j);
		b.setJogador(k);
		c.setJogador(k);
		a.setQntExercitos(10);
		b.setQntExercitos(7);
		c.setQntExercitos(1);
		a.AddAdjacente(b);
		c.AddAdjacente(a);
		
		assertTrue(t.VerificarAtaque(j, a, b));
		assertFalse(t.VerificarAtaque(k, c, a));
	}
	
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
	
	@Test
	public void testaGetTerritorio() {
		Tabuleiro t = new Tabuleiro();
		Territorio terr = new Territorio("Teste");
		t.getMapTerritorios().put("Teste", terr);
		assertEquals(Tabuleiro.getTerritorio("Teste"),terr);
	}
	
	@Test
	public void testaGetContinente() {
		Tabuleiro t = new Tabuleiro();
		Continente c = new Continente("A", 0, null);
		HashMap<String,Continente> mapContinente = new HashMap<String,Continente>();
		t.setMapContinente(mapContinente);
		assertEquals(Tabuleiro.getContinentes(),mapContinente);
	}
	
	//TODO
	@Test
	public void testeVerificarObjetivoConcluido() {}
	
	//TODO
	@Test
	public void testeVerificarExercitosASeremPosicionados() {}
	
	//TODO
	@Test
	public void testeRealizaAtque() {}*/
		
	
	
	

}
