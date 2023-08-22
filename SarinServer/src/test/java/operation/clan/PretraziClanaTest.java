package operation.clan;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Controller;
import domain.Clan;

class PretraziClanaTest {

	private PretraziClana pretraziClana;
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
		pretraziClana=new PretraziClana();
	}

	@AfterEach
	void tearDown() throws Exception {
		pretraziClana=null;
	}

	@Test
	void test() {
	clan.setIme("Tea"); clan.setPrezime("Tomic");
     try {
		List<Clan> filtriranaLista=Controller.getInstance().getClanovePoUslovu(clan);
		System.out.println("Broj pronadjenih clanova je: "+filtriranaLista.size());
		for(Clan c:filtriranaLista) {
			assertEquals("Tea", c.getIme());
			assertEquals("Tomic", c.getPrezime());
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
	}

	
	
	
	
	
	
	
	
	
	
}
