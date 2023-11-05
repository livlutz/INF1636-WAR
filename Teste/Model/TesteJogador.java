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
	public void testaTrocarEGetQtdDeCartas() {
		Jogador j = new Jogador ("Jooj",null);
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
	public void testaAddCarta() {
		Jogador j = new Jogador ("Jooj",null);
		j.addCarta(new Cartas(1, null));
		assertEquals(j.getCartas().size(),1);
	}
	
	@Test
	public void testaTemTroca() {
		Jogador j = new Jogador(null,null);
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
		Jogador j = new Jogador(null, null);
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
		Cartas c = new Cartas(1, null);
		j.addCarta(c);
		assertEquals(j.getCartas().size(),1);
	}
}
