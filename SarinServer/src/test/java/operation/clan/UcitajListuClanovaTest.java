package operation.clan;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Clan;
import domain.GenericEntity;

class UcitajListuClanovaTest {

	private UcitajListuClanova ucitajListuClanova;
	private static Clan clan;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		clan=new Clan();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		ucitajListuClanova=new UcitajListuClanova();
	}

	@AfterEach
	void tearDown() throws Exception {
		ucitajListuClanova=null;
	}

	@Test
	void testExecute() {
		try {
			ucitajListuClanova.execute(clan);
			List<Clan> lista=ucitajListuClanova.getClanove();
			assertNotNull(lista);
			System.out.println(lista.size()+" broj clanova");
			assertTrue(lista.size()>=1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	
	
	
}
