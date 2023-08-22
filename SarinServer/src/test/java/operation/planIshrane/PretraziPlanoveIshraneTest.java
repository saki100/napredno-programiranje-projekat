package operation.planIshrane;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import domain.Clan;
import domain.GenericEntity;
import domain.PlanIshrane;
import domain.Trener;
import operation.trener.UcitajTrenere;
import repository.db.impl.RepositoryDBGeneric;

class PretraziPlanoveIshraneTest {

	private PretraziPlanoveIshrane pretraziPlanoveIshrane;

	@Test
	public void testExecuteOperationThrowsException() throws Exception {

		try (MockedConstruction<RepositoryDBGeneric> mocked = Mockito.mockConstruction(RepositoryDBGeneric.class,
				(repository, context) -> {

					when(repository.getAllCondition(any())).thenThrow(new RuntimeException());
				})) {
			pretraziPlanoveIshrane = new PretraziPlanoveIshrane();
			assertThrows(RuntimeException.class, () -> pretraziPlanoveIshrane.executeOperation(new Clan()));

			verify(mocked.constructed().get(0), times(1)).getAllCondition(any());
		}
	}

	@Test
	public void testExecuteOepration() throws Exception {

		try (MockedConstruction<RepositoryDBGeneric> mocked = Mockito.mockConstruction(RepositoryDBGeneric.class,
				(repository, context) -> {

					List<GenericEntity> listaZaVracanje = new LinkedList<>();
					PlanIshrane planIshrane = new PlanIshrane();
					planIshrane.setIshranaID(123L);
					listaZaVracanje.add(planIshrane);

					when(repository.getAllCondition(any())).thenReturn(listaZaVracanje);
				})) {
			pretraziPlanoveIshrane = new PretraziPlanoveIshrane();
			pretraziPlanoveIshrane.executeOperation(new Clan());
			verify(mocked.constructed().get(0), times(1)).getAllCondition(any());

			List<PlanIshrane> vracenaLista = pretraziPlanoveIshrane.getPlanoveIshrane();

			assertNotNull(vracenaLista);
			assertFalse(vracenaLista.isEmpty());
			assertEquals(1, vracenaLista.size());
			assertEquals(123, vracenaLista.get(0).getIshranaID());
		}
	}

}
