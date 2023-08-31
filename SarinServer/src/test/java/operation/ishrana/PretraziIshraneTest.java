package operation.ishrana;

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

import domain.Dan;
import domain.Ishrana;
import domain.Obrok;
import domain.PlanIshrane;
import domain.VremeObroka;
import form.DBConfigModel;

public class PretraziIshraneTest {
	
	private static PretraziIshrane pretraziIshrane;
	private Ishrana ishrana;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pretraziIshrane = new PretraziIshrane();

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
		ishrana = new Ishrana();
	}

	@AfterEach
	void tearDown() throws Exception {
		ishrana = null;
	}

	@Test
	void test() throws Exception {
		
		PlanIshrane planIsh = new PlanIshrane();
		planIsh.setIshranaID(9L);
		
		ishrana = new Ishrana(planIsh, new Obrok(), VremeObroka.VECERA, Dan.SREDA);
		
		pretraziIshrane.execute(ishrana);
		
		List<Ishrana> rezultat = pretraziIshrane.getIshrane();
		
		assertNotNull(rezultat);
		assertFalse(rezultat.isEmpty());
		assertEquals(2, rezultat.size());
		
		Ishrana ishrana1 = rezultat.get(0);
		assertEquals(2, ishrana1.getObrok().getObrokID());
		assertEquals(VremeObroka.RUCAK, ishrana1.getVreme());
		assertEquals(Dan.SREDA, ishrana1.getDan());
		
		Ishrana ishrana2 = rezultat.get(1);
		assertEquals(4, ishrana2.getObrok().getObrokID());
		assertEquals(VremeObroka.VECERA, ishrana2.getVreme());
		assertEquals(Dan.SREDA, ishrana2.getDan());
	}

}
