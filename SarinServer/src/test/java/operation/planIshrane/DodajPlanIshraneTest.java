package operation.planIshrane;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import domain.PlanIshrane;
import domain.Trener;
import operation.trener.UcitajTrenere;
import repository.db.impl.RepositoryDBGeneric;

class DodajPlanIshraneTest {

	private DodajPlanIshrane dodajPlanIshrane;
	
	
	@Test
	public void testExecuteOperationThrowsException() throws Exception {
		PlanIshrane planIshrane = new PlanIshrane();

		try (MockedConstruction<RepositoryDBGeneric> mocked = Mockito.mockConstruction(RepositoryDBGeneric.class,
				(repository, context) -> {

					//za void metode bacanje exceptiona
					doThrow(new RuntimeException()).when(repository).add(planIshrane);
				})) {
			dodajPlanIshrane = new DodajPlanIshrane();
			assertThrows(RuntimeException.class, () -> dodajPlanIshrane.executeOperation(planIshrane));

			verify(mocked.constructed().get(0), times(1)).add(eq(planIshrane));

		}
	}
	
	@Test
	public void testExecuteOperation() throws Exception {
		PlanIshrane planIshrane = new PlanIshrane();

		try (MockedConstruction<RepositoryDBGeneric> mocked = Mockito.mockConstruction(RepositoryDBGeneric.class,
				(repository, context) -> {
					
					doNothing().when(repository).add(planIshrane);
				})) {
			dodajPlanIshrane = new DodajPlanIshrane();
			assertDoesNotThrow(() -> dodajPlanIshrane.executeOperation(planIshrane));

			verify(mocked.constructed().get(0), times(1)).add(eq(planIshrane));

		}
	}
}
