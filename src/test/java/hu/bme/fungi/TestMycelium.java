package hu.bme.fungi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.bme.fungi.spore.DefensiveSpore;
import hu.bme.tekton.KeeperTekton;
import hu.bme.tekton.SingleTypeTekton;
import hu.bme.tekton.Tekton;

class TestMycelium {
    private Mycelium mycelium;
    private SingleTypeTekton singleTypeTekton;

    @BeforeEach
    void setUp() {
        singleTypeTekton = mock(SingleTypeTekton.class);
        mycelium = new Mycelium(singleTypeTekton);
    }

    @Test
    void testInitialization() {
        assertFalse(mycelium.isUpgraded(), "Mycelium should not be upgraded");
    }

    @Test
    void upgradeFailsWithoutEnoughSpores() {
        when(singleTypeTekton.getSporeCount()).thenReturn(2);
        mycelium.upgrade();
        assertFalse(mycelium.isUpgraded());
    }
    
    @Test
    void testUpgradeSuccess() {
        when(singleTypeTekton.getSporeCount()).thenReturn(3);
        mycelium.upgrade();
        assertTrue(mycelium.isUpgraded());
    }
}
