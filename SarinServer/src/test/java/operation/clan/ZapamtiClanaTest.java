package operation.clan;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Clan;
import repository.db.DbConnectionFactory;

class ZapamtiClanaTest {

	private ZapamtiClana zapamtiClana;
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
		zapamtiClana =new ZapamtiClana();
	}

	@AfterEach
	void tearDown() throws Exception {
		zapamtiClana=null;
	}

	@Test 
	void testPreconditionsNeispravanBrTel() {
		assertThrows(java.lang.Exception.class, () -> zapamtiClana.preconditions(new domain.Clan()));
	}
	
	@Test
	void testExecute() {
		clan.setIme("Ana");
		clan.setPrezime("Antic");
		clan.setBrojTelefona(new String("0656000168"));
		Calendar kalendar = new GregorianCalendar(2002, 7, 11);
		Date datum = kalendar.getTime();
		clan.setDatumRodjenja(datum);
		try {
		 zapamtiClana.execute(clan);
		
		boolean condition = false;
		
		Clan clan2=(Clan) zapamtiClana.getClan();
		if(clan.getIme().equals(clan2.getIme())) {
			System.out.println(clan2+"Usao u if zapamtiClanaTest");
		condition=true;
		}
		assertTrue(condition);
		assertEquals(clan.getPrezime(), clan2.getPrezime());
		assertEquals(clan.getDatumRodjenja(), clan2.getDatumRodjenja());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
