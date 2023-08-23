package operation.obrok;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Obrok;
import form.DBConfigModel;

class PretraziObrokTest {
	
	private static PretraziObrok pretraziObrok;
	private Obrok obrok;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pretraziObrok = new PretraziObrok();
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
		obrok = new Obrok();
	}

	@AfterEach
	void tearDown() throws Exception {
		obrok = null;
	}

	@Test
	void test() throws Exception {
		obrok.setObrokID(27);
		
		pretraziObrok.execute(obrok);
		
		Obrok vraceniObrok = pretraziObrok.getO();
		
		assertNotNull(vraceniObrok);
		assertEquals(27, vraceniObrok.getObrokID());
		assertEquals("Burger", vraceniObrok.getNaziv());
		assertEquals(606, vraceniObrok.getKalorije());
	}

}
