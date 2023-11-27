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
	
}
