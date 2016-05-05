package ru.javawebinar.topjava.util.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import ru.javawebinar.topjava.util.TimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by FarAway on 04.05.2016.
 */
public class DateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private ToStringSerializer stringSerializer = ToStringSerializer.instance;

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        //gen.writeFieldName()
        String serializedValue = TimeUtil.toString(value);
        stringSerializer.serialize(serializedValue, gen, serializers);
    }
}
