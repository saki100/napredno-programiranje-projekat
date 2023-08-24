package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VezbaTest {

	private ResultSet rs;
	private Vezba vezba;
	
	@BeforeEach
	void setUp() throws Exception {
		vezba=new Vezba();
	}

	@AfterEach
	void tearDown() throws Exception {
		vezba=null;
	}

	@Test
	public void testKonstruktor() {
     vezba=new Vezba(2L,"Sklekovi");
     
     assertEquals(2L, vezba.getVezbaID());
     assertEquals("Sklekovi", vezba.getNaziv());
	}
    @Test
    public void testgetVezbaID() {
    	vezba.setVezbaID(2L);
    	assertEquals(2L, vezba.getVezbaID());
    }
	@Test
	public void testGetNaziv() {
		vezba.setNaziv("Sklekovi");
	     assertEquals("Sklekovi", vezba.getNaziv());
	}
	@Test
	public void testGetTableName() {
		assertEquals("vezba", vezba.getTableName());
	}
	@Test
	public void testCreateObjectRS() throws Exception {
		rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		
		when(rs.getLong("vezbaID")).thenReturn(1l).thenReturn(2l);
		when(rs.getString("naziv")).thenReturn("Iskoraci").thenReturn("Zgibovi");		
		
		List<GenericEntity> listaGenericEntity = vezba.createObjectRS(rs);
		
		assertNotNull(listaGenericEntity);
		assertFalse(listaGenericEntity.isEmpty());
		assertEquals(2, listaGenericEntity.size());
		
		List<Vezba> lista = (List<Vezba>)(List<?>) listaGenericEntity;
	
		assertEquals(1l, lista.get(0).getVezbaID());
		assertEquals("Iskoraci", lista.get(0).getNaziv());
		
		assertEquals(2l, lista.get(1).getVezbaID());
		assertEquals("Zgibovi", lista.get(1).getNaziv());
	
	}
	
	
	
	
	
	
	
	
}
