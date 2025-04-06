package hu.bme.fungi;

import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.tekton.Tekton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


class MycologistTest {

    private Mycologist mycologist;
    private Insect mockInsect;
    private Entomologist mockEntomologist;
    private Tekton mockTekton;
    private Mycelium mockMycelium;
    private Hyphae mockHyphae;

    @BeforeEach
    void setUp() {
        mycologist = new Mycologist();
        mockInsect = mock(Insect.class);
        mockEntomologist = mock(Entomologist.class);
        mockTekton = mock(Tekton.class);
        mockMycelium = mock(Mycelium.class);
        mockHyphae = mock(Hyphae.class);

        // Add a mock mycelium to the mycologist's list
        List<Mycelium> myceliums = new ArrayList<>();
        myceliums.add(mockMycelium);
        mycologist.getMyceliums().addAll(myceliums);
    }

    @Test
    void testEatInsect_SuccessfulCase() {
        when(mockInsect.isStunned()).thenReturn(true);
        when(mockInsect.getCurrentTekton()).thenReturn(mockTekton);
        when(mockInsect.getEntomologist()).thenReturn(mockEntomologist);
        when(mockHyphae.getCurrentTekton()).thenReturn(List.of(mockTekton));
        when(mockTekton.getHyphaes()).thenReturn(List.of(mockHyphae));
        when(mockMycelium.clone()).thenReturn(mockMycelium);
        mycologist.getHyphaes().add(mockHyphae);
        mycologist.getMyceliums().add(mockMycelium);

        mycologist.eatInsect(mockInsect);

        verify(mockTekton).addMycelium(mockMycelium);
        verify(mockHyphae).addMycelium(mockMycelium);
        verify(mockMycelium).addHyphae(mockHyphae);
        verify(mockEntomologist).removeInsect(mockInsect);
        verify(mockInsect).setCurrentTekton(null);
    }

    @Test
    void testEatInsect_InsectCanCutHyphae() {
        // Arrange
        when(mockInsect.getCanCutHyphae()).thenReturn(true);

        // Act
        mycologist.eatInsect(mockInsect);

        // Assert
        verifyNoInteractions(mockEntomologist, mockTekton, mockMycelium);
    }

    @Test
    void insectNotOnSameTekton() {
        when(mockInsect.isStunned()).thenReturn(true);
        when(mockInsect.getCurrentTekton()).thenReturn(mockTekton);

        verifyNoInteractions(mockTekton, mockMycelium);
        verifyNoInteractions(mockEntomologist, mockInsect);
    }
}