package operation.trener;

import domain.GenericEntity;
import domain.Trener;
import repository.Repository;
import repository.db.impl.RepositoryDBGeneric;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

public class UcitajTrenereTest {

	private UcitajTrenere ucitajTrenere;

	@Test
	public void testExecuteOperationThrowsException() throws Exception {
		Trener trener = new Trener();

		try (MockedConstruction<RepositoryDBGeneric> mocked = Mockito.mockConstruction(RepositoryDBGeneric.class,
				(repository, context) -> {

					when(repository.getAll(trener)).thenThrow(new RuntimeException());
				})) {
			ucitajTrenere = new UcitajTrenere();
			assertThrows(RuntimeException.class, () -> ucitajTrenere.executeOperation(trener));

			verify(mocked.constructed().get(0), times(1)).getAll(eq(trener));

		}
	}

	@Test
	public void testExecuteOepration() throws Exception {
		Trener trener = new Trener();

		try (MockedConstruction<RepositoryDBGeneric> mocked = Mockito.mockConstruction(RepositoryDBGeneric.class,
				(repository, context) -> {

					List<GenericEntity> listaZaVracanje = new LinkedList<>();
					listaZaVracanje.add(new Trener(1, "Pera", "Peric", "Username", "Password"));

					when(repository.getAll(trener)).thenReturn(listaZaVracanje);
				})) {
			ucitajTrenere = new UcitajTrenere();
			ucitajTrenere.executeOperation(trener);
			verify(mocked.constructed().get(0), times(1)).getAll(eq(trener));

			List<Trener> vracenaLista = ucitajTrenere.getTreneri();

			assertNotNull(vracenaLista);
			assertFalse(vracenaLista.isEmpty());
			assertEquals(1, vracenaLista.size());
			assertEquals(1, vracenaLista.get(0).getId());
			assertEquals("Pera", vracenaLista.get(0).getIme());
			assertEquals("Peric", vracenaLista.get(0).getPrezime());
			assertEquals("Username", vracenaLista.get(0).getUsername());
			assertEquals("Password", vracenaLista.get(0).getPassword());
		}
	}
}
