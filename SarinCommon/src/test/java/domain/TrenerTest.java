package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TrenerTest {

	private ResultSet rs;
	private Trener trener;

	@BeforeEach
	void setUp() throws Exception {
		trener=new Trener();
	}

	@AfterEach
	void tearDown() throws Exception {
		trener=null;
	}

	@Test
	void testKonstruktor() {
		trener =new Trener(7L, "Filip","Filipovic","filip10","password");
		
		assertEquals(7L, trener.getId());
		assertEquals("Filip", trener.getIme());
		assertEquals("Filipovic", trener.getPrezime());
		assertEquals("filip10", trener.getUsername());
		assertEquals("password", trener.getPassword());
	}
	@Test
	public void testGetID() {
		trener.setId(7L);
		assertEquals(7L, trener.getId());
	}
	@Test
	public void testGetIme() {
		trener.setIme("Filip");;
		assertEquals("Filip", trener.getIme());
	}
	@Test
	public void testGetPrezime() {
		trener.setPrezime("Filipovic");
		assertEquals("Filipovic", trener.getPrezime());
	}
	@Test
	public void testGetUsername() {
		trener.setUsername("filip10");
		assertEquals("filip10", trener.getUsername());
	}
	@Test
	public void testGetPassword() {
		trener.setPassword("password");
		assertEquals("password", trener.getPassword());
	}
	@Test
	public void testGetToString() {
		trener =new Trener(7L, "Filip","Filipovic","filip10","password");
        assertEquals("Trener [id=" + 7L + ", ime=" + "Filip" + ", prezime=" + "Filipovic" + ", username=" + "filip10" + ", password="
				+ "password" + "]", trener.toString());
	}
	@Test
	public void testGetTableName() {
		assertEquals("trener", trener.getTableName());
	}
	@Test
	public void testGetColumnNamesForInsert() {
		assertEquals("ime,prezime,username,password", trener.getColumnNamesForInsert());
	}
	@Test
	public void testGetInsertValues() {
		trener=new Trener(); 
		trener.setIme("Filip"); trener.setPrezime("Filipovic");
		trener.setUsername("filip10"); trener.setPassword("password");
		StringBuilder sb = new StringBuilder();
        sb.append("'").append("Filip").append("',")     
                .append("'").append("Filipovic").append("',")
                .append("'").append("filip10").append("',")
                .append("'").append("password").append("'");
        String ocekivaniRezultat = sb.toString();
        
        assertEquals(ocekivaniRezultat, trener.getInsertValues());
	}
	@Test
	public void testSetId() {
		trener.setId(1234l);
		assertEquals(1234l, trener.getId());
	}
	@Test
	public void testCreateObjectRS() throws Exception {
		rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		
		when(rs.getLong("id")).thenReturn(1l).thenReturn(2l);
		when(rs.getString("ime")).thenReturn("Pera").thenReturn("Mika");
		when(rs.getString("prezime")).thenReturn("Peric").thenReturn("Mikic");
		when(rs.getString("username")).thenReturn("perica").thenReturn("mikica");
		when(rs.getString("password")).thenReturn("peraPass").thenReturn("mikaPass");

       List<GenericEntity> listaGenericEntity = trener.createObjectRS(rs);
		
		assertNotNull(listaGenericEntity);
		assertFalse(listaGenericEntity.isEmpty());
		assertEquals(2, listaGenericEntity.size());
		
		List<Trener> lista = (List<Trener>)(List<?>) listaGenericEntity;
		
		assertEquals(1L, lista.get(0).getId());
		assertEquals("Pera", lista.get(0).getIme());
		assertEquals("Peric", lista.get(0).getPrezime());
		assertEquals("perica", lista.get(0).getUsername());
		assertEquals("peraPass", lista.get(0).getPassword());
		
		assertEquals(2L, lista.get(1).getId());
		assertEquals("Mika", lista.get(1).getIme());
		assertEquals("Mikic", lista.get(1).getPrezime());
		assertEquals("mikica", lista.get(1).getUsername());
		assertEquals("mikaPass", lista.get(1).getPassword());

	}
	@Test
	public void testGetConditionForOne() {
		trener.setUsername("mikica");
		trener.setPassword("mikaPass");
		
		String ocekivaniRezultat = "username = '" + "mikica" + "' and password='" + "mikaPass" + "'";
		assertEquals(ocekivaniRezultat, trener.getConditionForOne());
	}
	
	@Test 
	public void testCreateOneObjectRS() throws Exception {
		rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(false);
		
		when(rs.getLong("id")).thenReturn(1l);
		when(rs.getString("ime")).thenReturn("Mika");
		when(rs.getString("prezime")).thenReturn("Mikic");
		when(rs.getString("username")).thenReturn("mikica");
		when(rs.getString("password")).thenReturn("mikaPass");

		
		GenericEntity genericEntity=trener.createOneObjectRS(rs);
		
		assertNotNull(genericEntity);
		
		Trener t=(Trener)genericEntity;
		
		assertEquals(1l, t.getId());
		assertEquals("Mika", t.getIme());
		assertEquals("Mikic", t.getPrezime());
		assertEquals("mikica", t.getUsername());
		assertEquals("mikaPass", t.getPassword());

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
