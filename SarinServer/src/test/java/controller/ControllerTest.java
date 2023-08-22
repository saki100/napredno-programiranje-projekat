package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import domain.Trener;
import operation.trener.LogovanjeSO;
import repository.db.impl.RepositoryDBGeneric;

class ControllerTest {

	@Test
	public void testLoginSOThrowsException() throws Exception {
		Trener trener = new Trener();

		try (MockedConstruction<LogovanjeSO> mocked = Mockito.mockConstruction(LogovanjeSO.class,
				(logovanjeSO, context) -> {
					
					doThrow(new RuntimeException()).when(logovanjeSO).execute(trener);
				})) {
			assertThrows(RuntimeException.class, () -> Controller.getInstance().login(trener));

			verify(mocked.constructed().get(0), times(1)).execute(eq(trener));

		}
	}
	
	
	@Test
	public void testLoginSO() throws Exception {
		Trener trener = new Trener();

		try (MockedConstruction<LogovanjeSO> mocked = Mockito.mockConstruction(LogovanjeSO.class,
				(logovanjeSO, context) -> {
					
					doNothing().when(logovanjeSO).execute(trener);
					when(logovanjeSO.getTrener()).thenReturn(trener);
				})) {

			Object objekat = Controller.getInstance().login(trener);
			
			verify(mocked.constructed().get(0), times(1)).execute(eq(trener));
			verify(mocked.constructed().get(0), times(2)).getTrener();
			
			assertNotNull(objekat);
			assertTrue(objekat instanceof Trener);
		}
	}

}
