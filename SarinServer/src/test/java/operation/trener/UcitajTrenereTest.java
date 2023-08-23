package operation.trener;

import domain.GenericEntity;
import domain.Trener;
import form.DBConfigModel;
import repository.Repository;
import repository.db.impl.RepositoryDBGeneric;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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

public class UcitajTrenereTest {

	private static UcitajTrenere ucitajTrenere;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ucitajTrenere=new UcitajTrenere();
		
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


	@Test
	public void testExecute() throws Exception {
		ucitajTrenere.execute(new Trener());
		
		List<Trener> vracenaLista = ucitajTrenere.getTreneri();
		
		assertNotNull(vracenaLista);
		assertFalse(vracenaLista.isEmpty());
		assertEquals(2, vracenaLista.size());
		
		assertEquals(1, vracenaLista.get(0).getId());
		assertEquals("sara", vracenaLista.get(0).getIme());
		assertEquals("spanovic", vracenaLista.get(0).getPrezime());
		
		assertEquals(2, vracenaLista.get(1).getId());
		assertEquals("mica", vracenaLista.get(1).getIme());
		assertEquals("micic", vracenaLista.get(1).getPrezime());
	}
}
