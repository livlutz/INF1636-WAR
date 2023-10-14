package Model;

import static org.junit.Assert.*;

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
		assertFalse(j.alterarQtdTerritorios(-5));
	}
	
	@Test
	public void testaGetQtdExecPos() {
		Jogador j = new Jogador ("Jooj",1);
		j.setQtdExercitoPosic(4);
		assertEquals(j.getQtdExecPos(),4);
	}
	
	@Test
	public void testaSetQtdExecPosic() {
		Jogador j = new Jogador ("Jooj",1);
		j.setQtdExercitoPosic(4);
		assertEquals(j.getQtdExecPos(),4);
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
	public void testaTrocarEGetCartas() {
		Jogador j = new Jogador ("Jooj",1);
		Jogador.trocarCartas(null, null, null);
		assertEquals(Jogador.getQtdTrocaCartas(),1);
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
	
	//falta 2 testes de metodos ainda nao implementados
	
	
}
