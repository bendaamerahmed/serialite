package com.serialite.benchmarks;

import com.serialite.core.SeriaLite;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * JMH benchmark for SeriaLite.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class SeriaLiteBenchmark {

    private TestObject testObject;
    private byte[] serializedJson;
    private byte[] serializedMsgPack;

    @Setup(Level.Trial)
    public void setup() {
        testObject = new TestObject("benchmark", 999);
        serializedJson = SeriaLite.serialize(testObject, "json");
        serializedMsgPack = SeriaLite.serialize(testObject, "messagepack");
    }

    @Benchmark
    public void testJsonSerialization(Blackhole bh) {
        byte[] data = SeriaLite.serialize(testObject, "json");
        bh.consume(data);
    }

    @Benchmark
    public void testJsonDeserialization(Blackhole bh) {
        TestObject obj = SeriaLite.deserialize(serializedJson, TestObject.class, "json");
        bh.consume(obj);
    }

    @Benchmark
    public void testMessagePackSerialization(Blackhole bh) {
        byte[] data = SeriaLite.serialize(testObject, "messagepack");
        bh.consume(data);
    }

    @Benchmark
    public void testMessagePackDeserialization(Blackhole bh) {
        TestObject obj = SeriaLite.deserialize(serializedMsgPack, TestObject.class, "messagepack");
        bh.consume(obj);
    }

    public static class TestObject {
        private String text;
        private int number;

        public TestObject() {}

        public TestObject(String text, int number) {
            this.text = text;
            this.number = number;
        }

        // getters / setters
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
        public int getNumber() { return number; }
        public void setNumber(int number) { this.number = number; }
    }
}