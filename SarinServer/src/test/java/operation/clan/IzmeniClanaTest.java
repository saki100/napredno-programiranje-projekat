package operation.clan;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.ZoneId;
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
import domain.Ishrana;
import form.DBConfigModel;
import operation.planIshrane.IzmeniPlanIshrane;
import repository.db.DbConnectionFactory;

class IzmeniClanaTest {

	private static IzmeniClana izmeniClana;
	private Clan clan;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		izmeniClana = new IzmeniClana();

		// setapovanje da se koristi test baza
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

		// setapovanje da se koristi prava baza
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
	void test() throws Exception {

		clan.setIme("Ana");
		clan.setPrezime("Antic");
		clan.setEmail("ana.antic@gmail.com");
		clan.setBrojTelefona(new String("0656000168"));
		Date datum = Date.from(LocalDate.of(2002, 8, 8).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		clan.setDatumRodjenja(datum);
		clan.setGrad(new Grad(2l, "Novi Sad"));

		PretraziClana pretraziClana = new PretraziClana();
		pretraziClana.execute(clan);
		clan.setRbClana(pretraziClana.getClanovi().get(0).getRbClana());
		izmeniClana.execute(clan);
		pretraziClana.execute(clan);
		Clan izmenjen = pretraziClana.getClanovi().get(0);

		assertEquals(clan.getEmail(), izmenjen.getEmail());
		assertEquals(clan.getBrojTelefona(), izmenjen.getBrojTelefona());
		assertEquals(clan.getDatumRodjenja(), izmenjen.getDatumRodjenja());
		assertEquals(clan.getGrad().getGradID(), izmenjen.getGrad().getGradID());

		// vracanje na staro
		clan = new Clan();
		clan.setIme("Ana");
		clan.setPrezime("Antic");
		clan.setEmail("ana@gmail.com");
		clan.setBrojTelefona(new String("0641234567"));
		datum = Date.from(LocalDate.of(2002, 7, 7).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		clan.setDatumRodjenja(datum);

		Grad grad = new Grad();
		grad.setGradID(2);
		clan.setGrad(grad);

		pretraziClana.execute(clan);
		clan.setRbClana(pretraziClana.getClanovi().get(0).getRbClana());
		izmeniClana.execute(clan);

	}

}
