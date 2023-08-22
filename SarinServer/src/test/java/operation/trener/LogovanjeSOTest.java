package operation.trener;

import domain.Trener;
import repository.Repository;
import repository.db.impl.RepositoryDBGeneric;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

class LogovanjeSOTest {

	private LogovanjeSO logovanjeSO;
	private static Trener trener;

	@Test
	public void testPreconditions() throws Exception {
		assertThrows(Exception.class, () -> logovanjeSO.preconditions(""));
	}

	@Test
	public void testExecuteOperation() throws Exception {
		// definisemo parametre koje cemo proslediti u metodu koju testiramo
		Trener trener = new Trener();

		try (MockedConstruction<RepositoryDBGeneric> mocked = Mockito.mockConstruction(RepositoryDBGeneric.class,
				(repository, context) -> {
					
					//za metode koje nisu void bacanje exception
					when(repository.getObject(trener)).thenReturn(trener);
				})) {
			logovanjeSO = new LogovanjeSO();
			assertDoesNotThrow(() -> logovanjeSO.executeOperation(trener));

			verify(mocked.constructed().get(0), times(1)).getObject(eq(trener));

		}
	}

	@Test
	public void testExecuteOperationThrowsException() throws Exception {
		Trener trener = new Trener();

		try (MockedConstruction<RepositoryDBGeneric> mocked = Mockito.mockConstruction(RepositoryDBGeneric.class,
				(repository, context) -> {
					// further stubbings ...
					when(repository.getObject(trener)).thenThrow(new RuntimeException());
				})) {
			logovanjeSO = new LogovanjeSO();
			assertThrows(RuntimeException.class, () -> logovanjeSO.executeOperation(trener));
			verify(mocked.constructed().get(0), times(1)).getObject(eq(trener));
		}
	}

}
