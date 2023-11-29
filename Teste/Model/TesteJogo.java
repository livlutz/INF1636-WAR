package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class TesteJogo {
	@Test
	public void testeConstrutor() {
		Jogo j = Jogo.getJogo();
		assertNotNull(j);
	}

	@Test
	public void testeGetJogadores() {
		Jogo j = Jogo.getJogo();
		ArrayList<Jogador> a = new ArrayList<Jogador>();
		assertEquals(a, j.getJogadores());
	}

	@Test
	public void testeAddJogador(){
		Jogo j = Jogo.getJogo();
		Jogador j1 = new Jogador("Jooj", null);
		j.addJogador(j1);
		assertEquals(j.getJogadores().size(),1);
	}

	@Test
	public void testagetJogador(){
		Jogo j = Jogo.getJogo();
		Jogador j1 = new Jogador("Jooj", null);
		j.addJogador(j1);
		assertEquals(j.getJogador("Jooj"),j1);
	}

	@Test
	public void testaGetJogadorVez(){
		Jogo j = Jogo.getJogo();
		Jogador j1 = new Jogador("Jooj", null);
		j.addJogador(j1);
		assertEquals(j.getJogadorVez(0),j1);
	}

	@Test
	public void testaGetJogadores(){
		Jogo j = Jogo.getJogo();
		Jogador j1 = new Jogador("Jooj", null);
		j.addJogador(j1);
		ArrayList<Jogador> a = new ArrayList<Jogador>();
		a.add(j1);
		assertEquals(j.getJogadores(),a);
	}

	@Test
	public void testaGetCartaNome(){
		Jogo j = Jogo.getJogo();
		Carta c = new Carta(0, null);
		j.getListaCartas().add(c);
		assertEquals(j.getCartaNome("Coringa"),c);
	}

	@Test
	public void testeGetCartasLista(){
		Jogo j = Jogo.getJogo();
		Carta c = new Carta(0, null);
		j.getListaCartas().add(c);
		ArrayList<Carta> a = new ArrayList<Carta>();
		a.add(c);
		assertEquals(j.getListaCartas(),a);
	}
	
}
