package hu.bme.fungi;

import hu.bme.insect.Entomologist;
import hu.bme.insect.Insect;
import hu.bme.tekton.Tekton;
import hu.bme.tekton.AbsrobingTekton;
import hu.bme.tekton.KeeperTekton;
import hu.bme.tekton.MultiTypeTekton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.discovery.predicates.IsNestedTestClass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestMycologist {

    private Mycologist mycologist;
    private Tekton tekton1;
    private Tekton tekton2;
    private Tekton tekton3;
    private Mycelium mycelium;
    private Hyphae hyphae;

    @BeforeEach
    void setUp() {
        mycologist = new Mycologist();
        tekton1 = new AbsrobingTekton();
        tekton2 = new KeeperTekton();
        tekton3 = new MultiTypeTekton();
        mycelium = new Mycelium(tekton1);
        hyphae = new Hyphae();

        mycologist.addMycelium(mycelium);
    }

    @Test
    void testAddMycelium() {
        assertEquals(1, mycologist.getMyceliums().size());
        assertTrue(mycologist.getMyceliums().contains(mycelium));
    }

    @Test
    void testGrowHyphaeOnSameTektonAsMycelium() {
        mycologist.growHyphaeOnTekton(mycelium, tekton1);
    
        assertTrue(tekton1.getHyphaes().stream().anyMatch(h -> h.getOwner() == mycologist));
    }

    @Test
    void testGrowHyphaeToNonNeighbourTekton() {
        mycologist.growHyphaeOnTekton(mycelium, tekton1);

        assertFalse(tekton2.getHyphaes().contains(hyphae));
        assertFalse(hyphae.getCurrentTekton().contains(tekton2));
    }

    @Test
    void testEatInsect_SuccessfulCase() {
        Insect insect = mock(Insect.class);
        Entomologist entomologist = mock(Entomologist.class);

        when(insect.isStunned()).thenReturn(true);
        when(insect.getCurrentTekton()).thenReturn(tekton1);
        when(insect.getEntomologist()).thenReturn(entomologist);

        Mycelium mycelium = mock(Mycelium.class);
        when(mycelium.clone()).thenReturn(mock(Mycelium.class));
        mycologist.addMycelium(mycelium);
    
        Hyphae hyphae = mock(Hyphae.class);
        when(hyphae.getCurrentTekton()).thenReturn(List.of(tekton1));
        mycologist.getHyphaes().add(hyphae);

        mycologist.eatInsect(insect);

        verify(entomologist).removeInsect(insect);
        verify(insect).setCurrentTekton(null);
    }

    @Test
    void testEatInsect_InsectNotStunned() {
        
        Insect insect = mock(Insect.class);
        Entomologist entomologist = mock(Entomologist.class); 
        // Set up the mock behavior
        when(insect.isStunned()).thenReturn(false); 
        when(insect.getEntomologist()).thenReturn(entomologist);

        // Call the method
        mycologist.eatInsect(insect);

        // Verify that no interactions occur with the insect
        verify(insect, never()).setCurrentTekton(null);
        verify(entomologist, never()).removeInsect(insect); 
    }

    @Test
    void testEatInsect_InsectNotOnSameTekton() {
        Entomologist entomologist = new Entomologist();
        Insect insect = new Insect();
    
        entomologist.addInsect(insect);
        insect.setEntomologist(entomologist);
        insect.setCurrentTekton(tekton2);
    
        assertEquals(1, entomologist.getInsectCount());
    
        mycologist.eatInsect(insect);
    
        assertEquals(1, entomologist.getInsectCount());
        assertNotNull(insect.getCurrentTekton());
    }

    @Test
    void testGrowHyphaeBetweenTektons() {
        hyphae.addCurrentTekton(tekton1);
        tekton1.addHyphae(hyphae);
        tekton1.addNeighbour(tekton2);
        mycologist.growHyphaeToTekton(hyphae, tekton2);

        assertTrue(hyphae.getCurrentTekton().contains(tekton1));
        assertFalse(hyphae.getCurrentTekton().contains(tekton2));
    }

    @Test
    void testAddHyphaeToTekton() {
        tekton1.addHyphae(hyphae);
        assertTrue(tekton1.getHyphaes().contains(hyphae));
    }

    @Test
    void testAddHyphaeToNonNeighbourTekton() {
        tekton1.addNeighbour(tekton2);
        mycologist.growHyphaeOnTekton(mycelium, hyphae);

        assertFalse(tekton3.getHyphaes().contains(hyphae));
    }

    @Test
    void testMycologistCannotGrowHyphaeToNonNeighbour() {
        mycologist.growHyphaeOnTekton(mycelium, hyphae);

        assertFalse(tekton2.getHyphaes().contains(hyphae));
    }

    @Test
    void testMycologistCannotGrowHyphaeWithoutMycelium() {
        Mycelium invalidMycelium = new Mycelium(tekton3);
        mycologist.growHyphaeOnTekton(invalidMycelium, hyphae);

        assertFalse(tekton3.getHyphaes().contains(hyphae));
    }
}