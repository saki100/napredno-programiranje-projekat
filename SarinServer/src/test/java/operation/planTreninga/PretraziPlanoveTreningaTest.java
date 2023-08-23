package operation.planTreninga;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Clan;
import domain.PlanTreninga;
import domain.TipPlanaTreninga;
import form.DBConfigModel;

class PretraziPlanoveTreningaTest {

	private static PretraziPlanoveTreninga pretraziPlanoveTreninga;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		pretraziPlanoveTreninga = new PretraziPlanoveTreninga();

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


	@Test
	void test() throws Exception {
		Clan clan = new Clan();
		clan.setRbClana(7);
		
		pretraziPlanoveTreninga.execute(clan);
		
		List<PlanTreninga> vraceniPlanoviTreninga = pretraziPlanoveTreninga.getPlanovi();
		
		assertNotNull(vraceniPlanoviTreninga);
		assertFalse(vraceniPlanoviTreninga.isEmpty());
		assertEquals(1, vraceniPlanoviTreninga.size());
		assertEquals(3, vraceniPlanoviTreninga.get(0).getTreningID());
		assertEquals(TipPlanaTreninga.TEZINSKI, vraceniPlanoviTreninga.get(0).getTip());
	}

}
