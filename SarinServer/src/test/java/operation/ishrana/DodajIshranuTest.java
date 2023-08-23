package operation.ishrana;

import static org.junit.jupiter.api.Assertions.*;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Dan;
import domain.Ishrana;
import domain.Obrok;
import domain.PlanIshrane;
import domain.VremeObroka;
import form.DBConfigModel;
import operation.obrok.PretraziObrok;

class DodajIshranuTest {
	
	private DodajIshranu dodajIshranu;
	private static Ishrana ishrana;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		ishrana=new Ishrana();
		
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
		dodajIshranu=new DodajIshranu();
	}

	@AfterEach
	void tearDown() throws Exception {
		dodajIshranu=null;
	}

	@Test
	void testExecute() {
		 try {
      PlanIshrane planIsh=new PlanIshrane(); planIsh.setIshranaID(6L);
      Obrok o=new Obrok(); o.setObrokID(3L); 
      PretraziObrok pretraziObrok=new PretraziObrok();
      pretraziObrok.execute(o);
      o=pretraziObrok.getO();
      System.out.println("Obrok je: "+o);
      ishrana=new Ishrana(planIsh, o, VremeObroka.RUCAK,Dan.CETVRTAK);
      
     
		dodajIshranu.execute(ishrana);
		PretraziIshrane pretraziIsh=new PretraziIshrane();
		pretraziIsh.execute(ishrana);
		List<Ishrana> lista=new LinkedList<>();
		lista=pretraziIsh.getIshrane();

		assertEquals(1, lista.size());
		System.out.println(lista.get(0));
		assertEquals(VremeObroka.RUCAK, lista.get(0).getVreme());
		assertEquals(Dan.CETVRTAK, lista.get(0).getDan());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	
	
	
	
	
	
	
	
	
	
	
}
