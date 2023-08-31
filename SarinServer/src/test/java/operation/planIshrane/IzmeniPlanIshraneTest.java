package operation.planIshrane;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Clan;
import domain.Grad;
import domain.Obrok;
import domain.PlanIshrane;
import domain.TipPlanaIshrane;
import form.DBConfigModel;
import operation.clan.IzmeniClana;
import operation.clan.PretraziClana;
import repository.db.impl.RepositoryDBGeneric;

public class IzmeniPlanIshraneTest {

	private static IzmeniPlanIshrane izmeniPlanIshrane;
	private PlanIshrane planIshrane;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		izmeniPlanIshrane = new IzmeniPlanIshrane();

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
		planIshrane=new PlanIshrane();
	}

	@AfterEach
	void tearDown() throws Exception {
		planIshrane=null;
	}

	@Test
	void test() throws Exception {

		Clan clan = new Clan();
		clan.setId(1l);
		
		planIshrane.setIshranaID(10);
		planIshrane.setClan(clan);
		planIshrane.setDatumOd(Date.from(LocalDate.of(2023, 5, 4).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		planIshrane.setDatumDo(Date.from(LocalDate.of(2023, 7, 15).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		planIshrane.setTip(TipPlanaIshrane.NORMALNA);
		
		izmeniPlanIshrane.execute(planIshrane);
		
		PretraziPlanoveIshrane pretraziPlanoveIshrane = new PretraziPlanoveIshrane();
		pretraziPlanoveIshrane.execute(clan);
		List<PlanIshrane> vraceniPlanoviIshrane = pretraziPlanoveIshrane.getPlanoveIshrane();
		
		assertNotNull(vraceniPlanoviIshrane);
		assertFalse(vraceniPlanoviIshrane.isEmpty());
		assertEquals(1, vraceniPlanoviIshrane.size());
		
		PlanIshrane vraceniPlanIshrane = vraceniPlanoviIshrane.get(0);

		assertEquals(planIshrane.getIshranaID(), vraceniPlanIshrane.getIshranaID());
		assertEquals(planIshrane.getTip(), vraceniPlanIshrane.getTip());

		Clan clanStari = new Clan();
		clanStari.setRbClana(2);
		planIshrane.setClan(clanStari);
		planIshrane.setTip(TipPlanaIshrane.HRONO);
		
		izmeniPlanIshrane.execute(planIshrane);
		
	}
}
