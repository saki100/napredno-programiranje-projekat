package operation.clan;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Clan;
import domain.Grad;
import form.DBConfigModel;
import repository.db.DbConnectionFactory;

public class ZapamtiClanaTest {

	private static ZapamtiClana zapamtiClana;
	private static Clan clan;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		zapamtiClana = new ZapamtiClana();
		DBConfigModel dbConfigModel = new DBConfigModel();
		dbConfigModel.setUrl("jdbc:mysql://localhost:3306/sportski_klub_test");
		dbConfigModel.setUsername("root");
		dbConfigModel.setPassword("");
		ObjectMapper objectMapper = new ObjectMapper();

		BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("dbconfigJson.txt"),
				StandardOpenOption.TRUNCATE_EXISTING);
		bufferedWriter.write(objectMapper.writeValueAsString(dbConfigModel));
		bufferedWriter.flush();
		bufferedWriter.close();

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		DBConfigModel dbConfigModel = new DBConfigModel();
		dbConfigModel.setUrl("jdbc:mysql://localhost:3306/sportski_klub");
		dbConfigModel.setUsername("root");
		dbConfigModel.setPassword("");
		ObjectMapper objectMapper = new ObjectMapper();

		BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("dbconfigJson.txt"),
				StandardOpenOption.TRUNCATE_EXISTING);
		bufferedWriter.write(objectMapper.writeValueAsString(dbConfigModel));
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		clan = new Clan();
	}

	@AfterEach
	void tearDown() throws Exception {
		clan = null;
	}

	@Test
	void testPreconditionsNeispravanBrTel() {
		assertThrows(java.lang.Exception.class, () -> zapamtiClana.preconditions(new domain.Clan()));
	}

	@Test
	void testExecute() throws Exception {
		clan.setIme("Novi");
		clan.setPrezime("Clan");
		clan.setBrojTelefona(new String("0656000168"));
		Calendar kalendar = new GregorianCalendar(2002, 7, 11);
		Date datum = kalendar.getTime();
		clan.setDatumRodjenja(datum);
		Grad grad = new Grad(1, "Beograd");
		clan.setGrad(grad);

		zapamtiClana.execute(clan);

		Clan zapamceniClan = (Clan) zapamtiClana.getClan();

		assertNotNull(zapamceniClan);
		assertEquals(clan.getIme(), zapamceniClan.getIme());
		assertEquals(clan.getPrezime(), zapamceniClan.getPrezime());
		assertEquals(clan.getBrojTelefona(), zapamceniClan.getBrojTelefona());
		assertEquals(clan.getDatumRodjenja(), zapamceniClan.getDatumRodjenja());

		ObrisiClana obrisiClana = new ObrisiClana();
		obrisiClana.execute(zapamceniClan);

		DbConnectionFactory.getInstance().getConnection().commit();

	}

}
