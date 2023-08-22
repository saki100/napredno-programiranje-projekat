package operation.planIshrane;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import domain.PlanIshrane;
import repository.db.impl.RepositoryDBGeneric;

class IzmeniPlanIshraneTest {

	private IzmeniPlanIshrane izmeniPlanIshrane;
	
	
	@Test
	public void testExecuteOperationThrowsException() throws Exception {
		PlanIshrane planIshrane = new PlanIshrane();

		try (MockedConstruction<RepositoryDBGeneric> mocked = Mockito.mockConstruction(RepositoryDBGeneric.class,
				(repository, context) -> {

					//za void metode bacanje exceptiona
					doThrow(new RuntimeException()).when(repository).edit(planIshrane);
				})) {
			izmeniPlanIshrane = new IzmeniPlanIshrane();
			assertThrows(RuntimeException.class, () -> izmeniPlanIshrane.executeOperation(planIshrane));

			verify(mocked.constructed().get(0), times(1)).edit(eq(planIshrane));

		}
	}
	
	@Test
	public void testExecuteOperation() throws Exception {
		PlanIshrane planIshrane = new PlanIshrane();

		try (MockedConstruction<RepositoryDBGeneric> mocked = Mockito.mockConstruction(RepositoryDBGeneric.class,
				(repository, context) -> {
					
					doNothing().when(repository).edit(planIshrane);
				})) {
			izmeniPlanIshrane = new IzmeniPlanIshrane();
			assertDoesNotThrow(() -> izmeniPlanIshrane.executeOperation(planIshrane));

			verify(mocked.constructed().get(0), times(1)).edit(eq(planIshrane));

		}
	}

}
