package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TreningTest {

	private ResultSet rs;
    private Trening trening;
	
	@BeforeEach
	public void setUp() throws Exception {
		trening=new Trening();
	}

	@AfterEach
	public void tearDown() throws Exception {
		trening=null;
	}

	@Test
	public void testKonstruktor() {
		PlanTreninga planTr=new PlanTreninga();
		Vezba vezba=new Vezba();
        trening=new Trening(planTr, vezba, Dan.SREDA,3,4,12);
        
        assertEquals(planTr, trening.getPlanTreninga());
        assertEquals(vezba, trening.getVezba());
        assertEquals(Dan.SREDA, trening.getDan());
        assertEquals(3, trening.getRbVezbe());
        assertEquals(4, trening.getBrSerija());
        assertEquals(12, trening.getBrPonavljanja());

	}

	@Test
	public void testSetPlanTreningaOK() {
		PlanTreninga planTr=new PlanTreninga();
		trening.setPlanTreninga(planTr);
        assertEquals(planTr, trening.getPlanTreninga());
	}
	@Test
	public void setPlanTreningaNull() {
		assertThrows(java.lang.NullPointerException.class, () -> trening.setPlanTreninga(null));
	}
	@Test
	public void testSetVezbaOK() {
		Vezba vezba=new Vezba();
		trening.setVezba(vezba);
        assertEquals(vezba, trening.getVezba());
	}
	@Test
	public void setVezbaNull() {
		assertThrows(java.lang.NullPointerException.class, () -> trening.setVezba(null));
	}
	@Test
	public void testSetDan() {
		trening.setDan(Dan.SREDA);
        assertEquals(Dan.SREDA, trening.getDan());
	}
	@Test
	public void testSetRbVezbe() {
		trening.setRbVezbe(3);
        assertEquals(3, trening.getRbVezbe());
	}
	@Test
	public void testSetBrSerija() {
		trening.setBrSerija(4);
        assertEquals(4, trening.getBrSerija());
	}
	@Test
	public void testSetBrPonavljanjaOK() {
		trening.setBrPonavljanja(12);
        assertEquals(12, trening.getBrPonavljanja());
	}
	@Test
	public void testSetBrPonavljanjaVeciOdDvadeset() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> trening.setBrPonavljanja(25));
	}
	@Test
	public void testSetStatus() {
		trening.setStatus(Status.INSERT);
		assertEquals(Status.INSERT, trening.getStatus());
	}
	@Test
	public void testToString() {
		PlanTreninga planTr=new PlanTreninga();
		Vezba vezba=new Vezba();
        trening=new Trening(planTr, vezba, Dan.SREDA,3,4,12);
        trening.setStatus(Status.UPDATE);
        assertEquals("Trening{" + "planTreninga=" + planTr +
        		", vezba=" + vezba + ", dan=" + Dan.SREDA + 
        		", rbVezbe=" + 3 + ", brSerija=" + 4 + ", brPonavljanja=" + 12 + 
        		", status=" + Status.UPDATE + '}', trening.toString());
	}
	@Test
	public void testgetTableName() {
		assertEquals("trening", trening.getTableName());
	}
	
	@Test
	public void testGetColumnNamesForInsert() {
		assertEquals("planTreningaID,vezbaID,dan,rbVezbe,brSerija,brPonavljanja", trening.getColumnNamesForInsert());
	}
	@Test
	public void testGetInsertValues() {
		PlanTreninga planTr=new PlanTreninga();
		Vezba vezba=new Vezba();
        trening=new Trening(planTr, vezba, Dan.SREDA,3,4,12);
		
        StringBuilder sb = new StringBuilder();
        sb.append(planTr.getTreningID()).append(",")         //da li dodati rbClana
                .append(vezba.getVezbaID()).append(",")
                .append("'").append(Dan.SREDA).append("',")
                .append(3).append(",").append(4).append(",").append(12);
      
          String ocekivaniRezultat = sb.toString();
        
        assertEquals(ocekivaniRezultat, trening.getInsertValues());
	}
	@Test
	public void testCreateObjectRS() throws Exception {
		rs = Mockito.mock(ResultSet.class);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
	    
		Vezba v1=new Vezba(1L,"Sklekovi");
		Vezba v2=new Vezba(2L,"Trbusnjaci");
		
		when(rs.getLong("vezbaID")).thenReturn(v1.getVezbaID()).thenReturn(v2.getVezbaID());
		when(rs.getString("dan")).thenReturn(Dan.SREDA.toString()).thenReturn(Dan.CETVRTAK.toString());
		when(rs.getInt("rbVezbe")).thenReturn(3).thenReturn(2);
		when(rs.getInt("brSerija")).thenReturn(4).thenReturn(3);
		when(rs.getInt("brPonavljanja")).thenReturn(12).thenReturn(10);

       List<GenericEntity> listaGenericEntity = trening.createObjectRS(rs);
	    
		assertNotNull(listaGenericEntity);
		assertFalse(listaGenericEntity.isEmpty());
		assertEquals(2, listaGenericEntity.size());
		
		List<Trening> lista = (List<Trening>)(List<?>) listaGenericEntity;
		
		assertEquals(1L, lista.get(0).getVezba().getVezbaID());
		assertEquals(Dan.SREDA, lista.get(0).getDan());
		assertEquals(3, lista.get(0).getRbVezbe());
		assertEquals(4, lista.get(0).getBrSerija());
		assertEquals(12, lista.get(0).getBrPonavljanja());
		
		assertEquals(2L, lista.get(1).getVezba().getVezbaID());
		assertEquals(Dan.CETVRTAK, lista.get(1).getDan());
		assertEquals(2, lista.get(1).getRbVezbe());
		assertEquals(3, lista.get(1).getBrSerija());
		assertEquals(10, lista.get(1).getBrPonavljanja());
	}
	@Test
	public void testGetConditionForOne() {
		PlanTreninga planTr =new PlanTreninga();
		planTr.setTreningID(5L);
		trening.setPlanTreninga(planTr);
        String ocekivaniRezultat = "planTreningaID="+planTr.getTreningID();
		assertEquals(ocekivaniRezultat, trening.getConditionForOne());
	
	}
	
	
	
}
