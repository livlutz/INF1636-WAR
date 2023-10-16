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
	public void testeVerCartas() {
		Jogador j = new Jogador ("Jooj",1);
		j.verCartas();
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
		j.trocarCartas(null, null, null);
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
		j.addCarta();
		ArrayList <Cartas> c = new ArrayList <Cartas>();
		c.add(new Cartas());
		assertEquals(j.getCartas().size(),c.size());
	}
	
	@Test
	public void testaTrocaCartas() {
		Jogador j = new Jogador(null, 0);
		for(int i =0; i<5;i++) {
			j.addCarta();
		}
		assertTrue(j.temTroca());
	}
	
	@Test
	public void testeRodadaDePosicionamento() {
		//TODO
	}
	
}
