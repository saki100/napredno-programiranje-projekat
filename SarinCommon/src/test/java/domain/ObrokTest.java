package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ObrokTest {

	private ResultSet rs;
	private Obrok obrok;
	

	@BeforeEach
	void setUp() throws Exception {
		obrok=new Obrok();
	}

	@AfterEach
	void tearDown() throws Exception {
		obrok=null;
	}

	@Test
	void testKonstruktor() {
		obrok=new Obrok(5L, "Palenta", 380);
		
		assertEquals(5L, obrok.getObrokID());
		assertEquals("Palenta", obrok.getNaziv());
		assertEquals(380, obrok.getKalorije());
	}
    @Test
    public void testGetKallorije() {
    	obrok.setKalorije(380);
		assertEquals(380, obrok.getKalorije());
    }
	@Test 
	public void testGetObrokID() {
		obrok.setObrokID(5L);
		assertEquals(5L, obrok.getObrokID());
	}
	@Test 
	public void testGetNaziv() {
		obrok.setNaziv("Palenta");
		assertEquals("Palenta", obrok.getNaziv());
	}
	@Test
	public void testGetToString() {
		obrok.setNaziv("Palenta");
        assertEquals("Palenta", obrok.toString());
	}
	@Test
	public void testGetTableName() {
		assertEquals("obrok", obrok.getTableName());
	}
	@Test
	public void testGetColumnNamesForInsert() {
		assertEquals("naziv,kalorije", obrok.getColumnNamesForInsert());
	}
	@Test
	public void testGetInsertValues() {
		obrok=new Obrok(); obrok.setNaziv("Palenta"); obrok.setKalorije(380);
		
		StringBuilder sb = new StringBuilder();
        sb.append("'").append("Palenta").append("',")         
          .append(380);
        String ocekivaniRezultat = sb.toString();
        
        assertEquals(ocekivaniRezultat, obrok.getInsertValues());
	}
	@Test
	public void testSetId() {
		obrok.setId(1234l);
		assertEquals(1234l, obrok.getObrokID());
	}
	@Test
	public void testCreateObjectRS() throws Exception {
		rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		
		when(rs.getLong("obrokid")).thenReturn(1l).thenReturn(2l);
		when(rs.getString("naziv")).thenReturn("Palenta").thenReturn("Tortilja sa piletinom");
		when(rs.getInt("kalorije")).thenReturn(380).thenReturn(550);
		
		
		List<GenericEntity> listaGenericEntity = obrok.createObjectRS(rs);
		
		assertNotNull(listaGenericEntity);
		assertFalse(listaGenericEntity.isEmpty());
		assertEquals(2, listaGenericEntity.size());
		
		List<Obrok> lista = (List<Obrok>)(List<?>) listaGenericEntity;
	
		assertEquals(1l, lista.get(0).getObrokID());
		assertEquals("Palenta", lista.get(0).getNaziv());
		assertEquals(380, lista.get(0).getKalorije());
		
		assertEquals(2l, lista.get(1).getObrokID());
		assertEquals("Tortilja sa piletinom", lista.get(1).getNaziv());
		assertEquals(550, lista.get(1).getKalorije());
	
	}
	@Test
	public void testGetConditionForOne() {
		obrok.setObrokID(5L);
		
        String ocekivaniRezultat = "obrokID="+5L;
		assertEquals(ocekivaniRezultat, obrok.getConditionForOne());
	}
	@Test 
	public void testCreateOneObjectRS() throws Exception {
		rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getLong("obrokID")).thenReturn(1l);
		when(rs.getString("naziv")).thenReturn("Palenta");
		when(rs.getInt("kalorije")).thenReturn(380);
		
		GenericEntity genericEntity=obrok.createOneObjectRS(rs);
		
		assertNotNull(genericEntity);
		
		Obrok o=(Obrok)genericEntity;
		
		assertEquals(1l, o.getObrokID());
		assertEquals("Palenta", o.getNaziv());
		assertEquals(380, o.getKalorije());
	}
	@Test
	public void testGetConditionUpdateDelete() {
		obrok.setObrokID(5L);
        String ocekivaniRezultat = " obrokID="+5L;
		
		assertEquals(ocekivaniRezultat, obrok.getConditionUpdateDelete());
	}
	@Test
	public void testGetConditionSetEdit() {
		obrok.setNaziv("Palenta"); obrok.setKalorije(380);
		String ocekivaniRezultat ="naziv = '" + "Palenta" + "', kalorije=" + 380;
		assertEquals(ocekivaniRezultat, obrok.getConditionSetEdit());
	}
	
	
}
