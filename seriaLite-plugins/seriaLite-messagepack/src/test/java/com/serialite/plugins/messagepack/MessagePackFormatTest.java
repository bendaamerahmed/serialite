package com.serialite.plugins.messagepack;

import com.serialite.core.SeriaLite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessagePackFormatTest {

    @Test
    void testMessagePackSerialization() {
        TestObject original = new TestObject("hello", 42);

        byte[] data = SeriaLite.serialize(original, "messagepack");
        TestObject result = SeriaLite.deserialize(data, TestObject.class, "messagepack");

        Assertions.assertEquals("hello", result.getName());
        Assertions.assertEquals(42, result.getValue());
    }

    static class TestObject {
        private String name;
        private int value;

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