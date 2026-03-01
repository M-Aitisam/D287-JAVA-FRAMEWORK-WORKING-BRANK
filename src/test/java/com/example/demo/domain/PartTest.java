package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for Part maximum and minimum inventory fields (requirement I).
 */
class PartTest {

    @Test
    void partMinAndMaxFieldsAreStoredAndRetrievedCorrectly() {
        InhousePart part = new InhousePart();
        part.setId(1L);
        part.setName("Test Part");
        part.setPrice(10.0);
        part.setInv(50);
        part.setMin(10);
        part.setMax(100);
        assertEquals(10, part.getMin());
        assertEquals(100, part.getMax());
        assertEquals(50, part.getInv());
    }

    @Test
    void partInventoryWithinMinMaxIsValid() {
        OutsourcedPart part = new OutsourcedPart();
        part.setMin(5);
        part.setMax(20);
        part.setInv(12);
        assertEquals(5, part.getMin());
        assertEquals(20, part.getMax());
        assertEquals(12, part.getInv());
        assertTrue(part.getInv() >= part.getMin() && part.getInv() <= part.getMax());
    }
}
