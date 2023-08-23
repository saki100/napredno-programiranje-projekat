package operation.clan;

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

import controller.Controller;
import domain.Clan;
import form.DBConfigModel;

class PretraziClanaTest {

	private PretraziClana pretraziClana;
	private static Clan clan;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		clan=new Clan();
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
		pretraziClana=new PretraziClana();
	}

	@AfterEach
	void tearDown() throws Exception {
		pretraziClana=null;
	}

	@Test
	void test() {
	clan.setIme("Tea"); clan.setPrezime("Tomic");
     try {
		List<Clan> filtriranaLista=Controller.getInstance().getClanovePoUslovu(clan);
		System.out.println("Broj pronadjenih clanova je: "+filtriranaLista.size());
		for(Clan c:filtriranaLista) {
			assertEquals("Tea", c.getIme());
			assertEquals("Tomic", c.getPrezime());
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
	}

	
	
	
	
	
	
	
	
	
	
}
