package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TesteJogador {

	@Test
	public void testeConstrutor() {
		Jogador j = new Jogador ("Jooj",1);
		assertNotNull(j);
	}
	
	@Test 
	public void testaAlteraQtdTerritorios() {
		Jogador j = new Jogador ("Jooj",1);
		j.setQtdTerritorios(4);
		assertTrue(j.alterarQtdTerritorios(1));
		assertFalse(j.alterarQtdTerritorios(-6));
	}
	
	@Test
	public void testaGetQtdExecPos() {
		Jogador j = new Jogador ("Jooj",1);
		j.setQtdExercitoPosic(4);
		assertEquals(j.getQtdExercitoPosic(),4);
	}
	
	@Test
	public void testaSetQtdExecPosic() {
		Jogador j = new Jogador ("Jooj",1);
		j.setQtdExercitoPosic(4);
		assertEquals(j.getQtdExercitoPosic(),4);
	}
	
	@Test
	public void testaGetQtdTerritorios() {
		Jogador j = new Jogador ("Jooj",1);
		j.setQtdTerritorios(2);
		assertEquals(j.getQtdTerritorios(),2);
	}
	
	@Test
	public void testaSetQtdTerritorios() {
		Jogador j = new Jogador ("Jooj",1);
		j.setQtdTerritorios(2);
		assertEquals(j.getQtdTerritorios(),2);
	}
	
	@Test
	public void testaGetObj() {
		Jogador j = new Jogador ("Jooj",1);
		j.setObj("A");
		assertEquals(j.getObj(),"A");
	}
	
	@Test
	public void testaSetObj() {
		Jogador j = new Jogador ("Jooj",1);
		j.setObj("A");
		assertEquals(j.getObj(),"A");
	}
	
	@Test
	public void testaTrocarEGetQtdDeCartas() {
		Jogador j = new Jogador ("Jooj",1);
		Cartas c1 = new Cartas(0, null);
		Cartas c2 = new Cartas(1, null);
		Cartas c3 = new Cartas(2, null);
		j.getCartas().add(c1);
		j.getCartas().add(c2);
		j.getCartas().add(c3);
		j.trocarCartas(c1, c2, c3);
		assertEquals(j.getQtdTrocaCartas(),1);
		assertEquals(j.getQtdExercitoPosic(),4);
	}
	
	@Test
	public void testaGetCor() {
		Jogador j = new Jogador ("Jooj",1);
		assertEquals(j.getCor(),1);
	}
	
	@Test
	public void testaGetNome() {
		Jogador j = new Jogador ("Jooj",1);
		assertEquals(j.getNome(),"Jooj");
	}
	
	@Test
	public void testaAddCarta() {
		Jogador j = new Jogador ("Jooj",1);
		j.addCarta(new Cartas(1, null));
		assertEquals(j.getCartas().size(),1);
	}
	
	@Test
	public void testaTemTroca() {
		Jogador j = new Jogador(null, 0);
		Cartas c1 = new Cartas(0, null);
		Cartas c2 = new Cartas(1, null);
		Cartas c3 = new Cartas(2, null);
		j.getCartas().add(c1);
		j.getCartas().add(c2);
		j.getCartas().add(c3);
		assertTrue(j.temTroca());
	}

	@Test
	public void testaTrocaCartas(){
		Jogador j = new Jogador(null, 0);
		Cartas c1 = new Cartas(0, null);
		Cartas c2 = new Cartas(1, null);
		Cartas c3 = new Cartas(2, null);
		j.getCartas().add(c1);
		j.getCartas().add(c2);
		j.getCartas().add(c3);

		//primeira troca
		j.trocarCartas(c1, c2, c3);
		assertEquals(j.getQtdExercitoPosic(), 4);

		//segunda troca
		j.getCartas().add(c1);
		j.getCartas().add(c2);
		j.getCartas().add(c3);
		j.trocarCartas(c1, c2, c3);

		//terceira troca
		j.getCartas().add(c1);
		j.getCartas().add(c2);
		j.getCartas().add(c3);
		j.trocarCartas(c1, c2, c3);

		//quarta troca
		j.getCartas().add(c1);
		j.getCartas().add(c2);
		j.getCartas().add(c3);
		j.trocarCartas(c1, c2, c3);

		//quinta troca
		j.getCartas().add(c1);
		j.getCartas().add(c2);
		j.getCartas().add(c3);
		j.trocarCartas(c1, c2, c3);

		//sexta troca
		j.getCartas().add(c1);
		j.getCartas().add(c2);
		j.getCartas().add(c3);
		j.trocarCartas(c1, c2, c3);
		assertEquals(j.getQtdExercitoPosic(), 55);

		//setima troca
		j.getCartas().add(c1);
		j.getCartas().add(c2);
		j.getCartas().add(c3);
		j.trocarCartas(c1, c2, c3);
		assertEquals(j.getQtdExercitoPosic(), 75);

	}

	@Test
	public void testaAddTerritorio() {
		Jogador j = new Jogador ("Jooj",1);
		Territorio t = new Territorio("A");
		j.addTerritorio(t);
		assertEquals(j.getTerritorios().size(),1);
	}

	@Test
	public void testaGetTerritorios() {
		Jogador j = new Jogador ("Jooj",1);
		Territorio t = new Territorio("A");
		j.addTerritorio(t);
		assertEquals(j.getTerritorios().size(),1);
	}
	
	@Test
	public void testaRemoveTerritorio() {
		Jogador j = new Jogador ("Jooj",1);
		Territorio t = new Territorio("A");
		j.addTerritorio(t);
		j.removeTerritorio(t);
		assertEquals(j.getTerritorios().size(),0);
	}
	
	@Test
	public void testaGetCartas() {
		Jogador j = new Jogador ("Jooj",1);
		Cartas c = new Cartas(1, null);
		j.addCarta(c);
		assertEquals(j.getCartas().size(),1);
	}
}
