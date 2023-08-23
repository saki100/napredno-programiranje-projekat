package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
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

class IshranaTest {

	private ResultSet rs;
	private Ishrana ishrana;
	
	@BeforeEach
	void setUp() throws Exception {
		ishrana=new Ishrana();
	}

	@AfterEach
	void tearDown() throws Exception {
		ishrana=null;
	}

	@Test
	public void testKonsturktor() {
		PlanIshrane planIsh=new PlanIshrane();
		planIsh.setIshranaID(5l);
		planIsh.setTip(TipPlanaIshrane.HRONO);
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		planIsh.setDatumOd(datumOd); planIsh.setDatumDo(datumDo);
		Grad grad = new Grad(1l, "Beograd");
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
        planIsh.setClan(clan);
        ishrana.setPlanIshrane(planIsh);
        Obrok obrok=new Obrok(123, "Tortilja sa piletinom",550);
        ishrana.setObrok(obrok);
        ishrana.setVreme(VremeObroka.VECERA);
        ishrana.setDan(Dan.SREDA);
        
        assertEquals(planIsh, ishrana.getPlanIshrane());
        assertEquals(obrok, ishrana.getObrok());
        assertEquals(VremeObroka.VECERA, ishrana.getVreme());
        assertEquals(Dan.SREDA, ishrana.getDan());
        
	}

	@Test
	public void testGetPlanIshrane() {
		PlanIshrane planIsh=new PlanIshrane();
		planIsh.setIshranaID(5l);
		planIsh.setTip(TipPlanaIshrane.HRONO);
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		planIsh.setDatumOd(datumOd); planIsh.setDatumDo(datumDo);
		Grad grad = new Grad(1l, "Beograd");
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
        planIsh.setClan(clan);
        ishrana.setPlanIshrane(planIsh);
        assertEquals(planIsh, ishrana.getPlanIshrane());
		
	}
   
	@Test
	public void testGetObrok() {
		 Obrok obrok=new Obrok(123, "Tortilja sa piletinom",550);
	        ishrana.setObrok(obrok);
	        assertEquals(obrok, ishrana.getObrok());
	}
	
	@Test 
	public void testGetVreme() {
		ishrana.setVreme(VremeObroka.VECERA);
		 assertEquals(VremeObroka.VECERA, ishrana.getVreme());
	}
	
	@Test
	public void testGetDan() {
		ishrana.setDan(Dan.SREDA);
		 assertEquals(Dan.SREDA, ishrana.getDan());
	}
	
	@Test
	public void testGetStatus() {
		ishrana.setStatus(Status.INSERT);
		assertEquals(Status.INSERT, ishrana.getStatus());	
	}
	
	@Test
	public void testToString() {
		PlanIshrane planIsh=new PlanIshrane();
		planIsh.setIshranaID(5l);
		planIsh.setTip(TipPlanaIshrane.HRONO);
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		planIsh.setDatumOd(datumOd); planIsh.setDatumDo(datumDo);
		Grad grad = new Grad(1l, "Beograd");
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
        planIsh.setClan(clan);
        ishrana.setPlanIshrane(planIsh);
        Obrok obrok=new Obrok(123, "Tortilja sa piletinom",550);
        ishrana.setObrok(obrok);
        ishrana.setVreme(VremeObroka.VECERA);
        ishrana.setDan(Dan.SREDA);
        
        assertEquals("Ishrana{" + "planIshrane=" + planIsh + ", obrok=" + obrok + ", vreme=" + VremeObroka.VECERA + ", dan=" + Dan.SREDA + '}', ishrana.toString());
	}
	
	@Test
	public void testgetTableName() {
		assertEquals("ishrana", ishrana.getTableName());
	}
	
	@Test
	public void testGetColumnNamesForInsert() {
		assertEquals("planIshraneID,obrokID,vreme,dan", ishrana.getColumnNamesForInsert());
	}
	
	@Test
	public void testGetInsertValues() {
		PlanIshrane planIsh=new PlanIshrane();
		planIsh.setIshranaID(5l);
		planIsh.setTip(TipPlanaIshrane.HRONO);
		Date datumOd= Date.from(LocalDate.of(2023, 6, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date datumDo= Date.from(LocalDate.of(2023, 7, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		planIsh.setDatumOd(datumOd); planIsh.setDatumDo(datumDo);
		Grad grad = new Grad(1l, "Beograd");
		Date datumRodjenja=Date.from(LocalDate.of(2000, 5, 12).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Clan clan = new Clan(123L, "Pera", "Peric", "pera@gmail.com", datumRodjenja, "+3811234567", grad);
        planIsh.setClan(clan);
        ishrana.setPlanIshrane(planIsh);
        Obrok obrok=new Obrok(123, "Tortilja sa piletinom",550);
        ishrana.setObrok(obrok);
        ishrana.setVreme(VremeObroka.VECERA);
        ishrana.setDan(Dan.SREDA);
		
		StringBuilder sb = new StringBuilder();
		sb.append(planIsh.getIshranaID()).append(",")         //da li dodati rbClana
        .append(obrok.getObrokID()).append(",")
        .append("'").append(VremeObroka.VECERA).append("',")
        .append("'").append(Dan.SREDA).append("'");
        
        String ocekivaniRezultat = sb.toString();
        
        assertEquals(ocekivaniRezultat, ishrana.getInsertValues());
	}
	
	@Test
	public void testCreateObjectRS() throws Exception {
		rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
	    
		
        Obrok obrok1=new Obrok(10, "Tortilja sa piletinom",550);
        Obrok obrok2=new Obrok(12, "Palenta",380);

        
		when(rs.getLong("obrokID")).thenReturn(obrok1.getObrokID()).thenReturn(obrok2.getObrokID());
		when(rs.getString("vreme")).thenReturn(VremeObroka.VECERA.toString()).thenReturn(VremeObroka.DORUCAK.toString());
		when(rs.getString("dan")).thenReturn(Dan.SREDA.toString()).thenReturn(Dan.PONEDELJAK.toString());

		List<GenericEntity> listaGenericEntity = ishrana.createObjectRS(rs);
	    
		assertNotNull(listaGenericEntity);
		assertFalse(listaGenericEntity.isEmpty());
		assertEquals(2, listaGenericEntity.size());
		
		List<Ishrana> lista = (List<Ishrana>)(List<?>) listaGenericEntity;
		System.out.println("Duzina liste je "+lista.size());
		
		assertEquals(10l,lista.get(0).getObrok().getObrokID());
	    assertEquals(VremeObroka.VECERA, lista.get(0).getVreme());
	    assertEquals(Dan.SREDA, lista.get(0).getDan());
	    
	    assertEquals(12l,lista.get(1).getObrok().getObrokID());
	    assertEquals(VremeObroka.DORUCAK, lista.get(1).getVreme());
	    assertEquals(Dan.PONEDELJAK, lista.get(1).getDan());
	    
	}
	
	@Test
	public void testGetConditionForOne() {
		PlanIshrane planIsh=new PlanIshrane();
		planIsh.setIshranaID(3);
		ishrana.setPlanIshrane(planIsh);
		String ocekivaniRezultat = "planIshraneID="+planIsh.getIshranaID();
		
		assertEquals(ocekivaniRezultat, ishrana.getConditionForOne());
	}
	
	@Test
	public void testGetConditionUpdateDelete() {
		PlanIshrane planIsh=new PlanIshrane();
		planIsh.setIshranaID(3);
		ishrana.setPlanIshrane(planIsh);
		ishrana.setDan(Dan.CETVRTAK); ishrana.setVreme(VremeObroka.DORUCAK);
		Dan dan=Dan.CETVRTAK; VremeObroka vreme=VremeObroka.DORUCAK;
		String ocekivaniRezultat = "planIshraneID= "+planIsh.getIshranaID()+" and vreme='"+vreme.toString()+"' and dan='"+dan.toString()+"'";
		  
		
		assertEquals(ocekivaniRezultat, ishrana.getConditionUpdateDelete());
	}
	@Test
	public void testGetConditionSetEdit() {
		Obrok obrok=new Obrok();
		obrok.setObrokID(5);
		ishrana.setObrok(obrok);
		String ocekivaniRezultat = "obrokID= "+obrok.getObrokID();
		assertEquals(ocekivaniRezultat, ishrana.getConditionSetEdit());

	}
	
	
	
	
	
	
}
