package operation.ishrana;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import repository.db.DbConnectionFactory;

class IzmeniIshranuTest {
	
	private static IzmeniIshranu izmeniIshranu;
	private Ishrana ishrana;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		izmeniIshranu = new IzmeniIshranu();

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
	void testExecute() throws Exception  {

		try {
			PlanIshrane planIsh = new PlanIshrane();
			planIsh.setIshranaID(9L);
			
			Obrok o = new Obrok();
			o.setObrokID(5L);
	
			ishrana = new Ishrana(planIsh, o, VremeObroka.VECERA, Dan.SREDA);
			izmeniIshranu.execute(ishrana);
			
			
			String upit = "Select * from ishrana where planIshraneID=? and obrokID=? and vreme=? and dan=?";
			PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(upit);
			ps.setLong(1, planIsh.getIshranaID());
			ps.setLong(2, o.getObrokID());
			ps.setString(3, VremeObroka.VECERA.name());
			ps.setString(4, Dan.SREDA.name());
			ResultSet rs = ps.executeQuery();
			assertTrue(rs.next());
			
			String upitUpdate = "Update ishrana set obrokID=? where planIshraneID=? and vreme=? and dan=?";
			ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(upitUpdate);
			ps.setLong(1, 4);
			ps.setLong(2, planIsh.getIshranaID());
			ps.setString(3, VremeObroka.VECERA.name());
			ps.setString(4, Dan.SREDA.name());
			ps.execute();
			DbConnectionFactory.getInstance().getConnection().commit();
		}catch(Exception ex) {
			DbConnectionFactory.getInstance().getConnection().rollback();
			throw ex;
		}finally {
			DbConnectionFactory.getInstance().disconnect();
		}
	}

}
