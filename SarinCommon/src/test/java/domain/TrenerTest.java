package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
