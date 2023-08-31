package operation.planIshrane;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
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
import domain.GenericEntity;
import domain.PlanIshrane;
import domain.TipPlanaIshrane;
import domain.Trener;
import form.DBConfigModel;
import operation.trener.UcitajTrenere;
import repository.db.impl.RepositoryDBGeneric;

public class PretraziPlanoveIshraneTest {

	private static PretraziPlanoveIshrane pretraziPlanoveIshrane;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		pretraziPlanoveIshrane = new PretraziPlanoveIshrane();

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
	public void testExecute() throws Exception {
		Clan clan = new Clan();
		clan.setRbClana(5);
		
		pretraziPlanoveIshrane.execute(clan);
		
		List<PlanIshrane> vraceniPlanovi = pretraziPlanoveIshrane.getPlanoveIshrane();
		
		assertNotNull(vraceniPlanovi);
		assertFalse(vraceniPlanovi.isEmpty());
		assertEquals(1, vraceniPlanovi.size());
		
		assertEquals(5, vraceniPlanovi.get(0).getIshranaID());
		assertEquals(TipPlanaIshrane.VEGETARIJANSKA, vraceniPlanovi.get(0).getTip());
	}
}
