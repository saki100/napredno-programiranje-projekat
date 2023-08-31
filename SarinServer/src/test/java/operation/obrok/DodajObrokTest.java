package operation.obrok;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Obrok;
import form.DBConfigModel;
import repository.db.DbConnectionFactory;

public class DodajObrokTest {

	private static DodajObrok dodajObrok;
	private Obrok obrok;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dodajObrok=new DodajObrok();
		
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
		obrok=new Obrok();
	}

	@AfterEach
	void tearDown() throws Exception {
		obrok=null;
	}

	@Test
	void testExecute() throws Exception {
        try {   	
        	obrok=new Obrok(0, "Supica", 200);
			dodajObrok.execute(obrok);
			
			PretraziObrok pretraziObrok=new PretraziObrok();
			pretraziObrok.execute(obrok);
			Obrok vracen=pretraziObrok.getO();
			assertEquals(obrok.getObrokID(), vracen.getObrokID());
			assertEquals(obrok.getNaziv(), vracen.getNaziv());
			assertEquals(obrok.getKalorije(), vracen.getKalorije());
			
			String upit = "Delete from obrok where obrokID=?";
			PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(upit);
			ps.setLong(1, obrok.getObrokID());
			ps.execute();
			
			DbConnectionFactory.getInstance().getConnection().commit();
		} catch (Exception e) {
			DbConnectionFactory.getInstance().getConnection().rollback();
			throw e;
		}finally {
			DbConnectionFactory.getInstance().disconnect();
		}
	}

}
