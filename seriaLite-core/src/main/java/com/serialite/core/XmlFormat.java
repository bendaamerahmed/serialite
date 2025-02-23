package com.serialite.core;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
/**
 * An XML-based implementation of {@link SerializationFormat} using Jackson.
 */
public class XmlFormat implements SerializationFormat<Object> {

    private final XmlMapper xmlMapper;

    /**
     * Constructs a new XmlFormat with a default Jackson {@link XmlMapper}.
     */
    public XmlFormat() {
        this.xmlMapper = new XmlMapper();
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        try {
            return xmlMapper.writeValueAsBytes(object);
        } catch (Exception e) {
            throw new SerializationException("Failed to serialize to XML", e);
        }
    }

    @Override
    public Object deserialize(byte[] data, Class<Object> clazz) throws SerializationException {
        try {
            return xmlMapper.readValue(data, clazz);
        } catch (Exception e) {
            throw new SerializationException("Failed to deserialize XML", e);
        }
    }

    @Override
    public String getFormatName() {
        return "xml";
    }
}