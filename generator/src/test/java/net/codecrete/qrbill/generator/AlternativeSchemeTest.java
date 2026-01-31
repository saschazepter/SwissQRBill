//
// Swiss QR Bill Generator
// Copyright (c) 2018 Manuel Bleichenbacher
// Licensed under MIT License
// https://opensource.org/licenses/MIT
//

package net.codecrete.qrbill.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlternativeSchemeTest {

    @Test
    void defaultConstructorTest() {
        AlternativeScheme scheme = new AlternativeScheme();
        assertNull(scheme.getName());
        assertNull(scheme.getInstruction());
    }

    @Test
    void constructorTest() {
        AlternativeScheme scheme = new AlternativeScheme("Paymit/12341234/1241234");
        assertEquals("Paymit", scheme.getName());
        assertEquals("Paymit/12341234/1241234", scheme.getInstruction());
        assertEquals("/12341234/1241234", scheme.getInstructionWithoutName());
    }

    @Test
    void deprecatedConstructorTest() {
        AlternativeScheme scheme = new AlternativeScheme("Paymit", "PM,12341234,1241234");
        assertEquals("PM", scheme.getName());
        assertEquals("PM,12341234,1241234", scheme.getInstruction());
    }

    @Test
    void setInstructionTest() {
        AlternativeScheme scheme = new AlternativeScheme();
        scheme.setInstruction("!@#%#$%");
        assertEquals("", scheme.getName());
        assertEquals("!@#%#$%", scheme.getInstruction());
        assertEquals("!@#%#$%", scheme.getInstructionWithoutName());
    }

    @Test
    void toStringTest() {
        AlternativeScheme scheme = new AlternativeScheme("PM,12341234,1241234");
        String text = scheme.toString();
        assertEquals("AlternativeScheme{instruction='PM,12341234,1241234'}", text);
    }

    @SuppressWarnings({"AssertBetweenInconvertibleTypes", "EqualsWithItself"})
    @Test
    void testEqualsTrivial() {
        AlternativeScheme scheme = new AlternativeScheme("PM,12341234,1241234");
        assertEquals(scheme, scheme);
        assertNotEquals(null, scheme);
        assertNotEquals("xxx", scheme);
    }

    @Test
    void testEquals() {
        AlternativeScheme scheme1 = new AlternativeScheme("PM,12341234,1241234");
        AlternativeScheme scheme2 = new AlternativeScheme("PM,12341234,1241234");
        assertEquals(scheme1, scheme2);
        assertEquals(scheme1, scheme2);

        scheme2.setInstruction("PM,12341234,1241239");
        assertNotEquals(scheme1, scheme2);
    }

    @Test
    void testHashCode() {
        AlternativeScheme scheme = new AlternativeScheme("PM,12341234,1241234");
        assertEquals(388185787, scheme.hashCode());
    }

}
