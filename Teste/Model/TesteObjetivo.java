package Model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class TesteObjetivo {
	
	//Teste do construtor
	@Test
	public void testeConstrutorObjetivoContinentes(){
		Continente c1 = new Continente("America do Sul", 2, null);
		Continente c2 = new Continente("America do Norte", 2, null);
		Objetivo o = new ObjetivoContinentes(c1, c2, false);
		assertNotNull(o);
	}

	@Test
	public void testeConstrutorObjetivoTerritorios(){
		Objetivo o = new ObjetivoTerritorios(0);
		assertNotNull(o);
	}

	@Test
	public void testaObjetivoDestruir(){
		Jogador j = new Jogador("Jooj", Color.BLACK);
		Objetivo o = new ObjetivoDestruir(j);
		assertNotNull(o);
	}

	//Teste do método alcancou
	@Test
	public void testeAlcancouObjetivoContinentes(){
		Continente c1 = new Continente("America do Sul", 2, null);
		Continente c2 = new Continente("America do Norte", 2, null);
		Objetivo o = new ObjetivoContinentes(c1, c2, false);
		Jogador j = new Jogador("Jooj", Color.BLACK);
		//add territorios de cada continente ao jogador
		for (Territorio t: c1.getTerritorios()){
			j.addTerritorio(t);
		}
		for (Territorio t: c2.getTerritorios()){
			j.addTerritorio(t);
		}
		assertTrue(o.alcancou(j));
	}

	@Test
	public void testaGetQtdTerritorios(){
		Objetivo o = new ObjetivoTerritorios(0);
		assertEquals(((ObjetivoTerritorios) o).getQtdTerritorios(),0);
	}

	@Test
	public void testaAlcancouObjetivoTerritorios(){
		Objetivo o = new ObjetivoTerritorios(0);
		Jogador j = new Jogador("Jooj", Color.BLACK);
		//add 18 territorios ao jogador
		for (int i = 0; i < 18; i++){
			j.addTerritorio(new Territorio("Territorio " + i));
		}
		assertTrue(o.alcancou(j));
	}

	@Test
	public void testaAlcancouObjetivoDestruir(){
		Jogador j = new Jogador("Jooj", Color.BLACK);
		Jogador jAlvo = new Jogador("Jooj2", Color.WHITE);
		Objetivo o = new ObjetivoDestruir(jAlvo);
		assertFalse(o.alcancou(j));
	}

}
