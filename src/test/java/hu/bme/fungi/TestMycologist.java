package hu.bme.fungi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.fungi.spore.Spore;
import hu.bme.tekton.Tekton;

class TestMycologist {
    
    private Mycologist mycologist;

    @Mock 
    private Hyphae hyphae;
    @Mock
    private Mycelium mycelium;

    @Mock
    private Tekton tekton, neighbourTekton;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mycologist = new Mycologist();
    }

    @Test
    public void testGrowHyphaeToTektonSucess(){
        when(hyphae.getCurrentTekton()).thenReturn(Collections.singletonList(tekton));
        when(tekton.getNeighbours()).thenReturn(Collections.singletonList(neighbourTekton));
        when(neighbourTekton.addHyphae(any(Hyphae.class))).thenReturn(true);

        mycologist.growHyphaeToTekton(hyphae, neighbourTekton);

        verify(neighbourTekton).addHyphae(any(Hyphae.class));
    }

    @Test
    public void testGrowHyphaeToTekton_Failure_NotNeighbor() {
        when(hyphae.getCurrentTekton()).thenReturn(Collections.singletonList(tekton));
        when(tekton.getNeighbours()).thenReturn(Collections.emptyList());

        mycologist.growHyphaeToTekton(hyphae, neighbourTekton);

        verify(neighbourTekton, never()).addHyphae(any(Hyphae.class));
    }

    @Test
    public void testReleaseSpores() {
        when(mycelium.getRemainingSporeReleases()).thenReturn(0);

        mycologist.releaseSpore(mycelium);

        verify(mycelium).setCurrentTekton(null);
        verify(mycelium).removeAllHyphae();
    }

    @Test
    public void testGrowMycelium_Sucess() {
        when(hyphae.getCurrentTekton()).thenReturn(Collections.singletonList(tekton));
        when(tekton.getSporeCount()).thenReturn(3);
        when(tekton.addMycelium(any(Mycelium.class))).thenReturn(true);
        when(tekton.getSpores()).thenReturn(Arrays.asList(mock(DefensiveSpore.class), mock(DefensiveSpore.class), mock(DefensiveSpore.class)));

        mycologist.growMycelium(hyphae, tekton);

        verify(tekton, times(3)).removeSpore(any(Spore.class));
    }

    @Test
    public void testGrowMycelium_Fail() {
        when(hyphae.getCurrentTekton()).thenReturn(Collections.singletonList(tekton));
        when(tekton.getSporeCount()).thenReturn(2);
        when(tekton.addMycelium(any(Mycelium.class))).thenReturn(true);
        when(tekton.getSpores()).thenReturn(Arrays.asList(mock(DefensiveSpore.class), mock(DefensiveSpore.class), mock(DefensiveSpore.class)));

        mycologist.growMycelium(hyphae, tekton);

        verify(tekton, times(0)).removeSpore(any(Spore.class));
    }

    @Test
    public void testGrowHyphaeOnTekton_Success() {
        when(hyphae.getCurrentTekton()).thenReturn(Arrays.asList(tekton, neighbourTekton));
        when(neighbourTekton.addHyphae(any(Hyphae.class))).thenReturn(true);
        when(hyphae.isConnectedToMycelium()).thenReturn(true);

        mycologist.growHyphaeOnTekton(hyphae, neighbourTekton);

        verify(neighbourTekton).addHyphae(any(Hyphae.class));
    }

}
