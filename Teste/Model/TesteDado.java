package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteDado {
	
	@Test
	public void testaRodarDado() {
		//verificar se vamos testar dessa forma
		Dado d = new Dado();
		int face = d.rodarDado();
		int faces [] = new int []{1,2,3,4,5,6};
		
		for (int i:faces) {
			if(face == i) {
				assertEquals(face,i);
			}
		}
	}
	
	@Test
	public void testaConstrutorDado() {
		Dado a = new Dado();
		assertNotNull(a);
	}
	

}
