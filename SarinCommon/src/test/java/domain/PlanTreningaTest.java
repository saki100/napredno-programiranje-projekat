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
public class PlanTreningaTest {

	private ResultSet rs;
	private PlanTreninga planTreninga;

	@BeforeEach
	public void setUp() throws Exception {
		planTreninga=new PlanTreninga();
	}

	@AfterEach
	public void tearDown() throws Exception {
		planTreninga=null;
	}

	@Test
	public void testKonstruktor() {
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Grad grad = new Grad(1l, "Beograd");
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
       planTreninga=new PlanTreninga(1L,TipPlanaTreninga.AEROBIK,datumOd,datumDo, clan);
	
       assertEquals(1L, planTreninga.getTreningID());
       assertEquals(TipPlanaTreninga.AEROBIK, planTreninga.getTip());
       assertEquals(datumOd, planTreninga.getDatumOD());
	    assertEquals(datumDo, planTreninga.getDatumDO());
	    assertEquals(clan, planTreninga.getClan());
	}
	@Test
	public void testSetTreningID(){
	planTreninga.setTreningID(10L);;
    assertEquals(10L, planTreninga.getTreningID());
	}
	@Test
	public void testSetTip() {
		planTreninga.setTip(TipPlanaTreninga.KARDIO);
        assertEquals(TipPlanaTreninga.KARDIO, planTreninga.getTip());
	}
	
	@Test
	public void testSetDatumOdOK(){
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        planTreninga.setDatumOD(datumOd);
        assertEquals(datumOd, planTreninga.getDatumOD());
	}
	@Test
	public void testSetDatumOdNull(){
		assertThrows(java.lang.NullPointerException.class, () -> planTreninga.setDatumOD(null));
	}
	@Test
	public void testSetDatumDoOK() {
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        planTreninga.setDatumDO(datumDo);
        assertEquals(datumDo, planTreninga.getDatumDO());
	}
	@Test
	public void testSetDatumDoNull(){
		assertThrows(java.lang.NullPointerException.class, () -> planTreninga.setDatumDO(null));
	}
	@Test
	public void testGetClan() {
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Grad grad = new Grad(1l, "Beograd");
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
       planTreninga.setClan(clan);
       assertEquals(clan, planTreninga.getClan());
	}
	@Test
	public void testSetClanNull(){
		assertThrows(java.lang.NullPointerException.class, () -> planTreninga.setClan(null));
	}
	@Test
	public void testGetStatus() {
		Status status=Status.INSERT;
		planTreninga.setStatus(status);
		assertEquals(Status.INSERT, planTreninga.getStatus());
	}
	@Test
	public void testToString() {
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Grad grad = new Grad(1l, "Beograd");
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
        planTreninga=new PlanTreninga(10L, TipPlanaTreninga.TEZINSKI, datumOd, datumDo,clan);
		assertEquals("PlanTreninga{" + "treningID=" + 10L + ", tip=" + TipPlanaTreninga.TEZINSKI + ", datumOD=" + datumOd + ", datumDO=" + datumDo + ", clan=" + clan + '}'
		   , planTreninga.toString());
	}
	@Test
	public void testGetTableName() {
		assertEquals("plantreninga", planTreninga.getTableName());
	}
	@Test
	public void testGetColumnNamesForInsert() {
		assertEquals("tip,datumOd,datumDo,clan", planTreninga.getColumnNamesForInsert());
	}
	@Test
	public void testGetInsertValues() {
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Clan clan=new Clan(); clan.setRbClana(18);
		planTreninga.setDatumOD(datumOd); planTreninga.setDatumDO(datumDo);
		planTreninga.setClan(clan); planTreninga.setTip(TipPlanaTreninga.HITT);
		StringBuilder sb = new StringBuilder();
		 sb.append("'").append(TipPlanaTreninga.HITT).append("',")      
         .append("'").append(new java.sql.Date(datumOd.getTime())).append("',")
         .append("'").append(new java.sql.Date(datumDo.getTime())).append("',")
         .append(clan.getRbClana());
        String ocekivaniRezultat = sb.toString();
        
        assertEquals(ocekivaniRezultat, planTreninga.getInsertValues());
	
	}
	@Test
	public void testSetId() {
		planTreninga.setId(1234l);
		assertEquals(1234l, planTreninga.getTreningID());
	}
	@Test
	public void testCreateObjectRS() throws Exception {
		rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		
        when(rs.getLong("treningID")).thenReturn(1l).thenReturn(2l);
		when(rs.getString("tip")).thenReturn(TipPlanaTreninga.HITT.toString()).thenReturn(TipPlanaTreninga.AEROBIK.toString());
		java.sql.Date datumOd = new java.sql.Date(new Date().getTime());
		java.sql.Date datumDo = new java.sql.Date(new Date().getTime());
		when(rs.getDate("datumOD")).thenReturn(datumOd).thenReturn(datumOd);
		when(rs.getDate("datumDO")).thenReturn(datumDo).thenReturn(datumDo);

       List<GenericEntity> listaGenericEntity = planTreninga.createObjectRS(rs);
		
		assertNotNull(listaGenericEntity);
		assertFalse(listaGenericEntity.isEmpty());
		assertEquals(2, listaGenericEntity.size());
	
		List<PlanTreninga> lista = (List<PlanTreninga>)(List<?>) listaGenericEntity;

	   assertEquals(1l, lista.get(0).getTreningID());
	   assertEquals(TipPlanaTreninga.HITT, lista.get(0).getTip());
	   assertEquals(datumOd, lista.get(0).getDatumOD());
	   assertEquals(datumDo, lista.get(0).getDatumDO());
	   
	   assertEquals(2l, lista.get(1).getTreningID());
	   assertEquals(TipPlanaTreninga.AEROBIK, lista.get(1).getTip());
	   assertEquals(datumOd, lista.get(1).getDatumOD());
	   assertEquals(datumDo, lista.get(1).getDatumDO());
	
	}
	@Test
	public void testGetConditionForOne() {
		Clan clan=new Clan(); clan.setRbClana(5);
		planTreninga.setClan(clan);
		
		String ocekivaniRezultat = "clan = " + clan.getRbClana() ;
		
		assertEquals(ocekivaniRezultat, planTreninga.getConditionForOne());
	}
	@Test
	public void testGetConditionUpdateDelete() {
		planTreninga.setTreningID(15);
		String ocekivaniRezultat = "treningID ="+15;
		
		assertEquals(ocekivaniRezultat, planTreninga.getConditionUpdateDelete());
	}
	@Test
	public void testGetConditionSetEdit() {
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Grad grad = new Grad(1l, "Beograd");
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        planTreninga=new PlanTreninga(10L, TipPlanaTreninga.KRUZNI,datumOd,datumDo,clan);
        String ocekivaniRezultat ="tip = '" + TipPlanaTreninga.KRUZNI + "', datumOD='" + sdf.format(datumOd) + "'"+", datumDO = '" + sdf.format(datumDo) 
                + "', clan= "+clan.getRbClana();
		assertEquals(ocekivaniRezultat, planTreninga.getConditionSetEdit());
	}
	
	
}
