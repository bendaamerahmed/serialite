package com.serialite.plugins.messagepack;

import com.serialite.core.SerializationException;
import com.serialite.core.SerializationFormat;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A MessagePack-based SerializationFormat implementation.
 */
public class MessagePackFormat implements SerializationFormat<Object> {

    private final ObjectMapper msgpackMapper;

    /**
     * Constructs a new MessagePackFormat with a Jackson ObjectMapper
     * configured to use MessagePackFactory.
     */
    public MessagePackFormat() {
        this.msgpackMapper = new ObjectMapper(new MessagePackFactory());
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        try {
            return msgpackMapper.writeValueAsBytes(object);
        } catch (Exception e) {
            throw new SerializationException("Failed to serialize to MessagePack", e);
        }
    }

    @Override
    public Object deserialize(byte[] data, Class<Object> clazz) throws SerializationException {
        try {
            return msgpackMapper.readValue(data, clazz);
        } catch (Exception e) {
            throw new SerializationException("Failed to deserialize MessagePack", e);
        }
    }

    @Override
    public String getFormatName() {
        return "messagepack";
    }
}