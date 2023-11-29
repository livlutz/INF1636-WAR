package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TesteTerritorio {

	@Test
	public void TesteConstrutorTerritorio() {
		Territorio t = new Territorio("Território teste");
		assertNotNull(t);
	}
	
	@Test
	public void TestaAddAdjacente() {
		Territorio a = new Territorio("Territorio teste A");
		Territorio b = new Territorio ("Territorio teste B");
		ArrayList <Territorio> list = new ArrayList <Territorio>();
		list.add(b);
		
		a.AddAdjacente(b);
		
		assertEquals(a.getAdjacentes(),list);
	}
	
	@Test
	public void TestaVerificaAdjacencia() {
		Territorio a = new Territorio("Territorio teste 1");
		Territorio b = new Territorio ("Territorio teste 2");
		Territorio c = new Territorio("Territorio teste 3");
		
		a.AddAdjacente(b);
		
		assertTrue(a.verificaAdjacencia(b));
		assertFalse(a.verificaAdjacencia(c));
	}
	
	@Test
	public void TestaAlterarQtdExercitos() {
		Territorio a = new Territorio("Territorio teste");
		Jogador j = new Jogador("jooj",null);
		j.setQtdExercitoPosic(10);
		a.setJogador(j);
		assertTrue(a.alterarQndExercitos(1));
		assertFalse(a.alterarQndExercitos(-10));
	}
	
	@Test
	public void testaGetJogador() {
		Territorio t = new Territorio ("Territorio teste");
		Jogador j = new Jogador ("Jooj",null);
		t.setJogador(j);
		assertEquals(t.getJogador(),j);
	}
	
	@Test
	public void testaSetJogador() {
		Territorio t = new Territorio ("Territorio teste");
		Jogador j = new Jogador ("Jooj",null);
		t.setJogador(j);
		assertEquals(t.getJogador(),j);
	}
	
	@Test
	public void testaGetQtdExercitos() {
		Territorio t = new Territorio ("Territorio teste");
		assertEquals(t.getQntExercitos(),0);
	}
	
	@Test
	public void testaSetQtdExercitos() {
		Territorio t = new Territorio ("Territorio teste");
		t.setQntExercitos(1);
		assertEquals(t.getQntExercitos(),1);
	}
	
	@Test
	public void testaGetNome() {
		Territorio t = new Territorio ("Territorio teste");
		assertEquals(t.getNome(),"Territorio teste");
	}
	
	@Test
	public void testaGetAdjacentes() {
		Territorio t = new Territorio ("Territorio teste");
		Territorio b = new Territorio ("Territorio teste 2");
		t.AddAdjacente(b);
		ArrayList<Territorio> lista = new ArrayList<Territorio>();
		lista.add(b);
		assertEquals(t.getAdjacentes(),lista);
	}

	@Test
	public void testaInstanciaAdjacentes(){
		Territorio t = new Territorio("Brasil");
		t.instanciaAdjacentes();
		assertTrue(t.getAdjacentes().size() != 0);
	}
	
	@Test
	public void testaGetCor(){
		Territorio t = new Territorio("Brasil");
		Jogador j = new Jogador("Jooj", null);
		t.setJogador(j);
		assertEquals(t.getCor(), j.getCor());
	}
	

}
