package com.serialite.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Basic tests for the SeriaLite facade with built-in JSON format.
 */
public class SeriaLiteTest {

    @Test
    void testJsonSerializationDeserialization() {
        TestObject original = new TestObject("example", 123);

        byte[] data = SeriaLite.serialize(original, "json");
        TestObject result = SeriaLite.deserialize(data, TestObject.class, "json");

        Assertions.assertEquals(original.getName(), result.getName());
        Assertions.assertEquals(original.getValue(), result.getValue());
    }

    static class TestObject {
        private String name;
        private int value;

        // Constructors, getters, setters...
        public TestObject() {
        }

        public TestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getValue() { return value; }
        public void setValue(int value) { this.value = value; }
    }
}