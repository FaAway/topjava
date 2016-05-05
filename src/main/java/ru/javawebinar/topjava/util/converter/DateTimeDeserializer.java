package ru.javawebinar.topjava.util.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.javawebinar.topjava.util.TimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by FarAway on 04.05.2016.
 */
public class DateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return TimeUtil.parseLocalDateTime(p.getValueAsString());
    }
}
