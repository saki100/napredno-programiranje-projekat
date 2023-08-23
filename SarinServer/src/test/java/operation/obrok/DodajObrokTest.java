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

class DodajObrokTest {

	private DodajObrok dodajObrok;
	private static Obrok obrok;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		obrok=new Obrok();
		
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
		dodajObrok=new DodajObrok();
	}

	@AfterEach
	void tearDown() throws Exception {
		dodajObrok=null;
	}

	@Test
	void testExecute() {
        obrok=new Obrok(18L, "Supica", 200);
        try {
			dodajObrok.execute(obrok);
			PretraziObrok pretraziObrok=new PretraziObrok();
			pretraziObrok.execute(obrok);
			Obrok vracen=pretraziObrok.getO();
			assertEquals(obrok.getObrokID(), vracen.getObrokID());
			assertEquals(obrok.getNaziv(), vracen.getNaziv());
			assertEquals(obrok.getKalorije(), vracen.getKalorije());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
