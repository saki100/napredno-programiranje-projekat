package operation.clan;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Clan;

class ObrisiClanaTest {

	private ObrisiClana obrisiClana;
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
		obrisiClana=new ObrisiClana();
	}

	@AfterEach
	void tearDown() throws Exception {
		obrisiClana=null;
	}

	@Test
	void test() {
		clan.setIme("ZaBrisanjeIme");
		clan.setPrezime("ZaBrisanjePrezime");
		clan.setBrojTelefona(new String("0656000168"));
		Calendar kalendar = new GregorianCalendar(2003, 6, 15);
		Date datum = kalendar.getTime();
		clan.setDatumRodjenja(datum);
        
        try {
        	ZapamtiClana zapamtiClana=new ZapamtiClana();
			zapamtiClana.execute(clan);
			obrisiClana.execute(clan);
			PretraziClana pretraziClana=new PretraziClana();
			pretraziClana.execute(clan);
			assertEquals(pretraziClana.getClanovi().size(),0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	
	
	
	
	
	
	
	
}
