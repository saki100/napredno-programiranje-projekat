package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

  public class ClanTest {

	private ResultSet rs;
	
	//private Clan clan=new Clan();
	private Clan clan;
	
	@BeforeEach
	public void setUp() {
		clan = new Clan();
	}
	
	@AfterEach
	public void tearDown() {
		clan = null;
	}
	
	@Test
	public void testKonsturktor() {
		Date datumRodjenja = new Date();
		Grad grad = new Grad(1l, "Beograd");
		clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
		
		assertEquals(123L, clan.getRbClana());
		assertEquals("Pera", clan.getIme());
		assertEquals("Peric", clan.getPrezime());
		assertEquals("pera@gmail.com", clan.getEmail());
		assertEquals(datumRodjenja, clan.getDatumRodjenja());
		assertEquals("+3811234567", clan.getBrojTelefona());
		assertEquals(grad.getGradID(), clan.getGrad().getGradID());
		assertEquals(grad.getNazivGrada(),clan.getGrad().getNazivGrada());
	}
	
	@Test
	public void testGetRbClana() {
		clan.setRbClana(1234l);
		assertEquals(1234l, clan.getRbClana());
	}
	
	@Test
	public void testSetImeOK() {
		clan.setIme("Mika");
		assertEquals("Mika", clan.getIme());
	}
	@Test
	public void testSetImeNeispravno() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> clan.setIme("Im"));
		assertThrows(java.lang.IllegalArgumentException.class, () -> clan.setIme(""));
	}
	@Test
	public void testSetImeNUll() {
		assertThrows(java.lang.NullPointerException.class, () -> clan.setIme(null));
	}
	@Test
	public void testSetPrezime() {
		clan.setPrezime("Mikic");
		assertEquals("Mikic", clan.getPrezime());
	}
	@Test
	public void testSetPrezimeNeispravno() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> clan.setPrezime("Pr"));
		assertThrows(java.lang.IllegalArgumentException.class, () -> clan.setPrezime(""));
	}
	@Test
	public void testSetPrezimeNUll() {
		assertThrows(java.lang.NullPointerException.class, () -> clan.setPrezime(null));
	}
	
	@Test
	public void testSetEmailOK() {
		clan.setEmail("mika@gmail.com");
		assertEquals("mika@gmail.com", clan.getEmail());
	}
	@Test
	public void testSetEmailNeispravan() {
		//assertThrows(java.lang.IllegalArgumentException.class, () -> clan.setEmail("sara.spanovic"));
		clan.setEmail("mika.gmail.com");
		assertEquals(null, clan.getEmail());
	}
	@Test
	public void testSetEmailNUll() {
		assertThrows(java.lang.NullPointerException.class, () -> clan.setEmail(null));
	}
	/*@Test
	public void testSetDatumRodjenja() {
		Date datum= new Date();
		clan.setDatumRodjenja(datum);
		assertEquals(datum, clan.getDatumRodjenja());
	}*/
	@Test 
	public void testSetDatumRodjenjaIspravan() {
		clan.setDatumRodjenja(new Date());
		assertEquals(new Date(), clan.getDatumRodjenja());
	}
	@Test 
	public void testSetDatumRodjenjaNemoguc() {
		Date d = Date.from(LocalDate.of(3000, 8, 8).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		assertThrows(java.lang.IllegalArgumentException.class, () -> clan.setDatumRodjenja(d));
	}
	@Test 
	public void testSetDatumRodjenjaNull() {
		assertThrows(java.lang.NullPointerException.class, () -> clan.setDatumRodjenja(null));
	}
	@Test
	public void testSetBrojTelefonaOK() {
		clan.setBrojTelefona("+38164123456");
		assertEquals("+38164123456", clan.getBrojTelefona());
	}
	@Test 
	public void testSetBrojTelefonaNull() {
		assertThrows(java.lang.NullPointerException.class, () -> clan.setBrojTelefona(null));
	}
	/*@Test
	public void testSetBrojTelefonaNeispravan() {
		assertThrows(java.lang.IllegalArgumentException.class, ()->clan.setBrojTelefona("38164123456"));
		
	}*/
	@Test
	public void testToString() {
		clan.setIme("Pera");
		clan.setPrezime("Peric");
		assertEquals("Pera Peric", clan.toString());
	}
	
	@Test
	public void testGetTableName() {
		assertEquals("clan", clan.getTableName());
	}
	
	@Test
	public void testGetColumnNamesForInsert() {
		assertEquals("ime,prezime,email,datumRodjenja,brojTelefona,gradID", clan.getColumnNamesForInsert());
	}
	
	@Test
	public void testGetInsertValues() {
		Date datumRodjenja = new Date();
		Grad grad = new Grad(1l, "Beograd");
		clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
		
		StringBuilder sb = new StringBuilder();
        sb.append("'").append("Pera").append("',")         //da li dodati rbClana
                .append("'").append("Peric").append("',")
                .append("'").append("pera@gmail.com").append("',")
                .append("'").append(new java.sql.Date(datumRodjenja.getTime())).append("',")
                .append("'").append("+3811234567").append("',")
        		.append(grad.getGradID()+"");
        String ocekivaniRezultat = sb.toString();
        
        assertEquals(ocekivaniRezultat, clan.getInsertValues());
	}
	
	@Test
	public void testSetId() {
		clan.setId(1234l);
		assertEquals(1234l, clan.getRbClana());
	}
	
	
	@Test
	public void testCreateObjectRS() throws Exception {
		rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		
		when(rs.getLong("gradID")).thenReturn(1l).thenReturn(2l);
		
		when(rs.getString("ime")).thenReturn("Pera").thenReturn("Mika");
		when(rs.getString("prezime")).thenReturn("Peric").thenReturn("Mikic");
		when(rs.getString("email")).thenReturn("pera@gmail.com").thenReturn("mika@gmail.com");
		java.sql.Date datumRodjenjaPera = new java.sql.Date(new Date().getTime());
		java.sql.Date datumRodjenjaMika = new java.sql.Date(new Date().getTime());
		when(rs.getDate("datumRodjenja")).thenReturn(datumRodjenjaPera).thenReturn(datumRodjenjaMika);
		when(rs.getString("brojTelefona")).thenReturn("+38163123456").thenReturn("+38164123456");
		
		
		List<GenericEntity> listaGenericEntity = clan.createObjectRS(rs);
		
		assertNotNull(listaGenericEntity);
		assertFalse(listaGenericEntity.isEmpty());
		assertEquals(2, listaGenericEntity.size());
		
		List<Clan> lista = (List<Clan>)(List<?>) listaGenericEntity;
		
		assertEquals(1, lista.get(0).getGrad().getGradID());
		assertEquals("Pera", lista.get(0).getIme());
		assertEquals("Peric", lista.get(0).getPrezime());
		assertEquals("pera@gmail.com", lista.get(0).getEmail());
		assertEquals(datumRodjenjaPera.getTime(), lista.get(0).getDatumRodjenja().getTime());
		assertEquals("+38163123456", lista.get(0).getBrojTelefona());
		
		assertEquals(2, lista.get(1).getGrad().getGradID());
		assertEquals("Mika", lista.get(1).getIme());
		assertEquals("Mikic", lista.get(1).getPrezime());
		assertEquals("mika@gmail.com", lista.get(1).getEmail());
		assertEquals(datumRodjenjaPera.getTime(), lista.get(1).getDatumRodjenja().getTime());
		assertEquals("+38164123456", lista.get(1).getBrojTelefona());
		
	}
	
	@Test
	public void testGetConditionForOne() {
		clan.setIme("Pera");
		clan.setPrezime("Peric");
		
		String ocekivaniRezultat = "ime = '" + "Pera" + "' and prezime='" + "Peric" + "'";
		
		assertEquals(ocekivaniRezultat, clan.getConditionForOne());
	}
	
	@Test
	public void testGetConditionUpdateDelete() {
		clan.setRbClana(1234l);
		String ocekivaniRezultat = "rbClana ="+1234l;
		
		assertEquals(ocekivaniRezultat, clan.getConditionUpdateDelete());
	}
	
	@Test
	public void testGetConditionSetEdit() {
		Date datumRodjenja = new Date();
		Grad grad = new Grad(1l, "Beograd");
		clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String ocekivaniRezultat = "ime = '" + "Pera" + "', prezime='" + "Peric" + "'"+", email = '" + "pera@gmail.com" 
	               + "', datumRodjenja='" +sdf.format(datumRodjenja)+"'"  + " , brojTelefona='" + "+3811234567"+ "', gradID="+grad.getGradID();
		assertEquals(ocekivaniRezultat, clan.getConditionSetEdit());
	}
}
