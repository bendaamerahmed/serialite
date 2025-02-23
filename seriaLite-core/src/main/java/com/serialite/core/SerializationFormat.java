package com.serialite.core;

/**
 * Generic interface for serialization and deserialization.
 *
 * @param <T> the type of object to serialize and deserialize.
 */
public interface SerializationFormat<T> {

    /**
     * Serializes an object into a byte array.
     *
     * @param object the object to serialize
     * @return the serialized byte array
     * @throws SerializationException if any error occurs
     */
    byte[] serialize(T object) throws SerializationException;

    /**
     * Deserializes an object from a byte array.
     *
     * @param data  the byte array containing serialized data
     * @param clazz the target class for deserialization
     * @return an instance of {@code T}
     * @throws SerializationException if any error occurs
     */
    T deserialize(byte[] data, Class<T> clazz) throws SerializationException;

    /**
     * @return the name/identifier for this format (e.g., "json", "xml", etc.)
     */
    String getFormatName();
}