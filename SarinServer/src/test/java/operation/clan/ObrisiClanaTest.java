package operation.clan;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Clan;
import domain.Grad;
import domain.Obrok;
import form.DBConfigModel;
import repository.db.DbConnectionFactory;

public class ObrisiClanaTest {

	private static ObrisiClana obrisiClana;
	private Clan clan;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		obrisiClana = new ObrisiClana();

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
	void test() throws SQLException, Exception {

		clan.setIme("ZaBrisanjeIme");
		clan.setPrezime("ZaBrisanjePrezime");
		clan.setBrojTelefona(new String("0656000168"));
		clan.setGrad(new Grad(1, null));
		Calendar kalendar = new GregorianCalendar(2003, 6, 15);
		Date datum = kalendar.getTime();
		clan.setDatumRodjenja(datum);

		ZapamtiClana zapamtiClana = new ZapamtiClana();
		zapamtiClana.execute(clan);

		obrisiClana.execute(clan);
		PretraziClana pretraziClana = new PretraziClana();
		pretraziClana.execute(clan);
		assertEquals(pretraziClana.getClanovi().size(), 0);

		DbConnectionFactory.getInstance().getConnection().commit();

	}

}
