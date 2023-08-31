package operation.planTreninga;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import operation.planIshrane.IzmeniPlanIshrane;

public class IzmeniPlanTreningaTest {
	
	private static IzmeniPlanTreninga izmeniPlanTreninga;
	private PlanTreninga planTreninga;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		izmeniPlanTreninga = new IzmeniPlanTreninga();

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
		planTreninga = new PlanTreninga();
	}

	@AfterEach
	void tearDown() throws Exception {
		planTreninga = null;
	}

	@Test
	void test() throws Exception {
		Clan clan = new Clan();
		clan.setRbClana(1);
		
		planTreninga.setTreningID(9);
		planTreninga.setClan(clan);
		planTreninga.setDatumOD(Date.from(LocalDate.of(2023, 10, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		planTreninga.setDatumDO(Date.from(LocalDate.of(2023, 12, 15).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		planTreninga.setTip(TipPlanaTreninga.AEROBIK);
		
		izmeniPlanTreninga.execute(planTreninga);
		
		PretraziPlanoveTreninga pretraziPlanoveTreninga = new PretraziPlanoveTreninga();
		pretraziPlanoveTreninga.execute(clan);
		List<PlanTreninga> vraceniPlanoviTreninga = pretraziPlanoveTreninga.getPlanovi();
		
		assertNotNull(vraceniPlanoviTreninga);
		assertFalse(vraceniPlanoviTreninga.isEmpty());
		assertEquals(1, vraceniPlanoviTreninga.size());
		assertEquals(planTreninga.getTreningID(), vraceniPlanoviTreninga.get(0).getTreningID());
		assertEquals(planTreninga.getTip(), vraceniPlanoviTreninga.get(0).getTip());
		
		Clan stariClan = new Clan();
		stariClan.setRbClana(5);
		planTreninga.setClan(stariClan);
		planTreninga.setTip(TipPlanaTreninga.SPRAVE);
		
		izmeniPlanTreninga.execute(planTreninga);
		
	}

}
