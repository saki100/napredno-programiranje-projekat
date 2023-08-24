package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GradTest {

	private ResultSet rs;
	private Grad grad;
	

	@BeforeEach
	void setUp() throws Exception {
		grad=new Grad();
	}

	@AfterEach
	void tearDown() throws Exception {
		grad=null;
	}

	@Test
	public void testKonstruktor() {
       grad=new Grad(1L, "Beograd");
       
       assertEquals(1L, grad.getGradID());
       assertEquals("Beograd", grad.getNazivGrada());
	}
   @Test
   public void testGetGradID() {
	   grad.setGradID(3L);
	   assertEquals(3L, grad.getGradID());
   }
   @Test
   public void testGetNazivGrada() {
	   grad.setNazivGrada("Beograd");
	   assertEquals("Beograd", grad.getNazivGrada());
   }
   @Test
	public void testGetTableName() {
		assertEquals("grad", grad.getTableName());
	}
   @Test
	public void testCreateObjectRS() throws Exception {
	   rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		
		when(rs.getLong("gradID")).thenReturn(1l).thenReturn(2l);
		when(rs.getString("nazivGrada")).thenReturn("Beograd").thenReturn("Novi Sad");
		
      List<GenericEntity> listaGenericEntity = grad.createObjectRS(rs);
		
		assertNotNull(listaGenericEntity);
		assertFalse(listaGenericEntity.isEmpty());
		assertEquals(2, listaGenericEntity.size());
		
		List<Grad> lista = (List<Grad>)(List<?>) listaGenericEntity;

		assertEquals(1L, lista.get(0).getGradID());
		assertEquals("Beograd", lista.get(0).getNazivGrada());
		
		assertEquals(2L, lista.get(1).getGradID());
		assertEquals("Novi Sad", lista.get(1).getNazivGrada());
   }
	
	
	
	
	
	
}
