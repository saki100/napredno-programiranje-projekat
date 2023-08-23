package operation.trener;

import domain.Clan;
import domain.PlanIshrane;
import domain.TipPlanaIshrane;
import domain.Trener;
import form.DBConfigModel;
import operation.planIshrane.DodajPlanIshrane;
import operation.planIshrane.PretraziPlanoveIshrane;
import repository.Repository;
import repository.db.DbConnectionFactory;
import repository.db.impl.RepositoryDBGeneric;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

class LogovanjeSOTest {

	private static LogovanjeSO logovanjeSO;
	private Trener trener;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		logovanjeSO=new LogovanjeSO();
		
		DBConfigModel dbConfigModel = new DBConfigModel();
		dbConfigModel.setUrl("jdbc:mysql://localhost:3306/sportski_klub_test");
		dbConfigModel.setUsername("root");
		dbConfigModel.setPassword("");
		ObjectMapper objectMapper = new ObjectMapper();
    	
    	BufferedWriter bufferedWriter =Files.newBufferedWriter(Paths.get("dbconfigJson.txt"), StandardOpenOption.TRUNCATE_EXISTING);
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
    	
    	BufferedWriter bufferedWriter =Files.newBufferedWriter(Paths.get("dbconfigJson.txt"), StandardOpenOption.TRUNCATE_EXISTING);
    	bufferedWriter.write(objectMapper.writeValueAsString(dbConfigModel));
    	bufferedWriter.flush();
    	bufferedWriter.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		trener=new Trener();
	}

	@AfterEach
	void tearDown() throws Exception {
		trener=null;
	}

	@Test
	public void testExecute() throws Exception {
		trener.setUsername("saki100");
		trener.setPassword("saki");
		
		logovanjeSO.execute(trener);
		
		Trener vraceniTrener = logovanjeSO.getTrener();
		
		assertNotNull(vraceniTrener);
		assertEquals(1, vraceniTrener.getId());
		assertEquals("sara", vraceniTrener.getIme());
		assertEquals("spanovic", vraceniTrener.getPrezime());
	}

}
