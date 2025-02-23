package com.serialite.core;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A JSON-based implementation of {@link SerializationFormat} using Jackson.
 */
public class JsonFormat implements SerializationFormat<Object> {

    private final ObjectMapper objectMapper;

    /**
     * Constructs a new JsonFormat with a default Jackson {@link ObjectMapper}.
     */
    public JsonFormat() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (Exception e) {
            throw new SerializationException("Failed to serialize to JSON", e);
        }
    }

    @Override
    public Object deserialize(byte[] data, Class<Object> clazz) throws SerializationException {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (Exception e) {
            throw new SerializationException("Failed to deserialize JSON", e);
        }
    }

    @Override
    public String getFormatName() {
        return "json";
    }
}