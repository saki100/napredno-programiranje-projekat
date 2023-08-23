package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
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
import org.mockito.Mockito;

class PlanIshraneTest {

	private ResultSet rs;
	private PlanIshrane planIshrane;

	@BeforeEach
	void setUp() throws Exception {
		planIshrane=new PlanIshrane();
	}

	@AfterEach
	void tearDown() throws Exception {
		planIshrane=null;

	}

	@Test
	void testKonstruktor() {
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Grad grad = new Grad(1l, "Beograd");
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
        planIshrane=new PlanIshrane(88, TipPlanaIshrane.NORMALNA, datumOd, datumDo,clan);
	
        assertEquals(88, planIshrane.getIshranaID());
        assertEquals(TipPlanaIshrane.NORMALNA, planIshrane.getTip());
	    assertEquals(datumOd, planIshrane.getDatumOd());
	    assertEquals(datumDo, planIshrane.getDatumDo());
	    assertEquals(clan, planIshrane.getClan());
	}

	@Test
	public void testGetIshranaID(){
	planIshrane.setIshranaID(88);
    assertEquals(88, planIshrane.getIshranaID());
	}
	
	@Test
	public void testGetTip() {
		planIshrane.setTip(TipPlanaIshrane.NORMALNA);
        assertEquals(TipPlanaIshrane.NORMALNA, planIshrane.getTip());
	}
	
	@Test
	public void testGetDatumOd(){
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        planIshrane.setDatumOd(datumOd);
        assertEquals(datumOd, planIshrane.getDatumOd());
	}
	@Test
	public void testGetDatumDo() {
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        planIshrane.setDatumDo(datumDo);
        assertEquals(datumDo, planIshrane.getDatumDo());


	}
	@Test
	public void testGetClan() {
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Grad grad = new Grad(1l, "Beograd");
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
       planIshrane.setClan(clan);
       assertEquals(clan, planIshrane.getClan());
	}
	@Test
	public void testGetStatus() {
		Status status=Status.INSERT;
		planIshrane.setStatus(status);
		assertEquals(Status.INSERT, planIshrane.getStatus());
	}
	@Test
	public void testToString() {
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Grad grad = new Grad(1l, "Beograd");
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
        planIshrane=new PlanIshrane(88, TipPlanaIshrane.NORMALNA, datumOd, datumDo,clan);
		assertEquals("PlanIshrane{" + "ishranaID=" + 88 + ", tip=" + TipPlanaIshrane.NORMALNA + ", datumOd=" + datumOd + ", datumDo=" + datumDo + ", clan=" + clan + '}', planIshrane.toString());
	}
	@Test
	public void testGetTableName() {
		assertEquals("planIshrane", planIshrane.getTableName());
	}
	@Test
	public void testGetColumnNamesForInsert() {
		assertEquals("tip,datumOd,datumDo,clan", planIshrane.getColumnNamesForInsert());
	}
	@Test
	public void testGetInsertValues() {
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Clan clan=new Clan(); clan.setRbClana(18);
		planIshrane.setDatumOd(datumOd); planIshrane.setDatumDo(datumDo);
		planIshrane.setClan(clan); planIshrane.setTip(TipPlanaIshrane.NORMALNA);
		StringBuilder sb = new StringBuilder();
        sb.append("'").append(TipPlanaIshrane.NORMALNA).append("',")      
                .append("'").append(new java.sql.Date(datumOd.getTime())).append("',")
                .append("'").append(new java.sql.Date(datumDo.getTime())).append("',")
                .append(clan.getRbClana());
        String ocekivaniRezultat = sb.toString();
        
        assertEquals(ocekivaniRezultat, planIshrane.getInsertValues());
	
	}
	@Test
	public void testSetId() {
		planIshrane.setId(1234l);
		assertEquals(1234l, planIshrane.getIshranaID());
	}
	
	@Test
	public void testCreateObjectRS() throws Exception {
		rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		
        when(rs.getLong("ishranaID")).thenReturn(1l).thenReturn(2l);
		when(rs.getString("tip")).thenReturn(TipPlanaIshrane.HRONO.toString()).thenReturn(TipPlanaIshrane.VEGAN.toString());
		java.sql.Date datumOd = new java.sql.Date(new Date().getTime());
		java.sql.Date datumDo = new java.sql.Date(new Date().getTime());
		when(rs.getDate("datumOd")).thenReturn(datumOd).thenReturn(datumOd);
		when(rs.getDate("datumDo")).thenReturn(datumDo).thenReturn(datumDo);

       List<GenericEntity> listaGenericEntity = planIshrane.createObjectRS(rs);
		
		assertNotNull(listaGenericEntity);
		assertFalse(listaGenericEntity.isEmpty());
		assertEquals(2, listaGenericEntity.size());
	
		List<PlanIshrane> lista = (List<PlanIshrane>)(List<?>) listaGenericEntity;

	   assertEquals(1l, lista.get(0).getIshranaID());
	   assertEquals(TipPlanaIshrane.HRONO, lista.get(0).getTip());
	   assertEquals(datumOd, lista.get(0).getDatumOd());
	   assertEquals(datumDo, lista.get(0).getDatumDo());
	   
	   assertEquals(2l, lista.get(1).getIshranaID());
	   assertEquals(TipPlanaIshrane.VEGAN, lista.get(1).getTip());
	   assertEquals(datumOd, lista.get(1).getDatumOd());
	   assertEquals(datumDo, lista.get(1).getDatumDo());
	
	}
	@Test
	public void testGetConditionForOne() {
		Clan clan=new Clan(); clan.setRbClana(5);
		planIshrane.setClan(clan);
		
		String ocekivaniRezultat = "clan = " + clan.getRbClana() ;
		
		assertEquals(ocekivaniRezultat, planIshrane.getConditionForOne());
	}
	@Test
	public void testGetConditionUpdateDelete() {
		planIshrane.setIshranaID(15);
		String ocekivaniRezultat = "ishranaID ="+15;
		
		assertEquals(ocekivaniRezultat, planIshrane.getConditionUpdateDelete());
	}
	@Test
	public void testGetConditionSetEdit() {
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Grad grad = new Grad(1l, "Beograd");
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        planIshrane=new PlanIshrane(88, TipPlanaIshrane.HRONO,datumOd,datumDo,clan);
        String ocekivaniRezultat ="tip = '" + TipPlanaIshrane.HRONO + "', datumOd='" + sdf.format(datumOd) + "'"+", datumDo = '" + sdf.format(datumDo) 
        + "', clan= "+clan.getRbClana();
		/*String ocekivaniRezultat = "ime = '" + "Pera" + "', prezime='" + "Peric" + "'"+", email = '" + "pera@gmail.com" 
	               + "', datumRodjenja='" +sdf.format(datumRodjenja)+"'"  + " , brojTelefona='" + "+3811234567"+ "', gradID="+grad.getGradID();*/
		assertEquals(ocekivaniRezultat, planIshrane.getConditionSetEdit());
	}
	
	
	
}
