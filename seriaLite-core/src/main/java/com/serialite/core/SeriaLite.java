package com.serialite.core;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A facade for all serialization operations.
 * <p>
 * SeriaLite discovers implementations of {@link SerializationFormat} using
 * the built-in plugin system (Java ServiceLoader).
 */
public class SeriaLite {

    /**
     * Holds a map of formatName -> SerializationFormat.
     */
    private static final Map<String, SerializationFormat<?>> FORMATS = new ConcurrentHashMap<>();

    static {
        // Register built-in formats
        registerFormat(new JsonFormat());
        registerFormat(new XmlFormat());
        // Load plugin-based formats via SPI
        loadPlugins();
    }

    /**
     * Registers a serialization format.
     *
     * @param format the format to register
     */
    public static void registerFormat(SerializationFormat<?> format) {
        FORMATS.put(format.getFormatName().toLowerCase(), format);
    }

    /**
     * Serializes an object using the specified format name.
     *
     * @param object     the object to serialize
     * @param formatName the name/identifier for the format
     * @param <T>        the object's type
     * @return serialized byte array
     * @throws SerializationException if the format is not found or serialization fails
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T object, String formatName) throws SerializationException {
        SerializationFormat<T> format = (SerializationFormat<T>) FORMATS.get(formatName.toLowerCase());
        if (format == null) {
            throw new SerializationException("No format registered with name: " + formatName);
        }
        return format.serialize(object);
    }

    /**
     * Deserializes an object using the specified format name.
     *
     * @param data       the byte array to deserialize
     * @param clazz      the target class
     * @param formatName the name/identifier for the format
     * @param <T>        the target type
     * @return deserialized object
     * @throws SerializationException if the format is not found or deserialization fails
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(byte[] data, Class<T> clazz, String formatName) throws SerializationException {
        SerializationFormat<T> format = (SerializationFormat<T>) FORMATS.get(formatName.toLowerCase());
        if (format == null) {
            throw new SerializationException("No format registered with name: " + formatName);
        }
        return format.deserialize(data, clazz);
    }

    /**
     * Loads plugin-based formats via Java's ServiceLoader mechanism.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void loadPlugins() {
        ServiceLoader<SerializationFormat> loader = ServiceLoader.load(SerializationFormat.class);
        for (SerializationFormat<?> format : loader) {
            registerFormat(format);
        }
    }
}