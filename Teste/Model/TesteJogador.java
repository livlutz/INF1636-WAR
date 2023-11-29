package Model;

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Color;

public class TesteJogador {

	@Test
	public void testeConstrutor() {
		Jogador j = new Jogador ("Jooj",null);
		assertNotNull(j);
	}
	
	@Test
	public void testaGetQtdExecPos() {
		Jogador j = new Jogador ("Jooj",null);
		j.setQtdExercitoPosic(4);
		assertEquals(j.getQtdExercitoPosic(),4);
	}
	
	@Test
	public void testaSetQtdExecPosic() {
		Jogador j = new Jogador ("Jooj",null);
		j.setQtdExercitoPosic(4);
		assertEquals(j.getQtdExercitoPosic(),4);
	}
	
	@Test
	public void testaGetCor() {
		Color c = new Color (255,213,45);
		Jogador j = new Jogador ("Jooj", c);
		assertEquals(j.getCor(),c);
	}
	
	@Test
	public void testaGetNome() {
		Jogador j = new Jogador ("Jooj",null);
		assertEquals(j.getNome(),"Jooj");
	}
	
	@Test
	public void testaTemTroca() {
		Jogador j = new Jogador(null,null);
		Carta c1 = new Carta(0, null);
		Carta c2 = new Carta(1, null);
		Carta c3 = new Carta(2, null);
		j.getCartas().add(c1);
		j.getCartas().add(c2);
		j.getCartas().add(c3);
		assertTrue(j.temTroca());
	}


	@Test
	public void testaAddTerritorio() {
		Jogador j = new Jogador ("Jooj",null);
		Territorio t = new Territorio("A");
		j.addTerritorio(t);
		assertEquals(j.getTerritorios().size(),1);
	}

	@Test
	public void testaGetTerritorios() {
		Jogador j = new Jogador ("Jooj",null);
		Territorio t = new Territorio("A");
		j.addTerritorio(t);
		assertEquals(j.getTerritorios().size(),1);
	}
	
	@Test
	public void testaRemoveTerritorio() {
		Jogador j = new Jogador ("Jooj",null);
		Territorio t = new Territorio("A");
		j.addTerritorio(t);
		j.removeTerritorio(t);
		assertEquals(j.getTerritorios().size(),0);
	}
	
	@Test
	public void testaGetCartas() {
		Jogador j = new Jogador ("Jooj",null);
		Carta c = new Carta(1, null);
		j.addCarta(c);
		assertEquals(j.getCartas().size(),1);
	}

	@Test
	public void testaGetQtdExercitoPosic() {
		Jogador j = new Jogador ("Jooj",null);
		Carta c = new Carta(1, null);
		j.addCarta(c);
		assertEquals(j.getQtdExercitoPosic(),0);
	}

	@Test
	public void testaSetQtdExercitoPosic() {
		Jogador j = new Jogador ("Jooj",null);
		Carta c = new Carta(1, null);
		j.addCarta(c);
		j.setQtdExercitoPosic(1);
		assertEquals(j.getQtdExercitoPosic(),1);
	}

	@Test
	public void testaGetQtdTerritorios() {
		Jogador j = new Jogador ("Jooj",null);
		Territorio t = new Territorio("A");
		j.addTerritorio(t);
		assertEquals(j.getQtdTerritorios(),1);
	}

	@Test
	public void testaAddCarta(){
		Jogador j = new Jogador ("Jooj",null);
		Carta c = new Carta(1, null);
		j.addCarta(c);
		assertEquals(j.getCartas().size(),1);
	}

	@Test
	public void testaAtualizaQtdExecPosicGeral(){
		Jogador j = new Jogador ("Jooj",null);
		Carta c = new Carta(1, null);
		j.addCarta(c);
		j.atualizaQtdExPosicGeral(4);
		assertEquals(j.getQtdExercitoPosic(),7);
	}

}
