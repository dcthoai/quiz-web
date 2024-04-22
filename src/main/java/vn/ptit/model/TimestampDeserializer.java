package vn.ptit.model;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimestampDeserializer implements JsonDeserializer<Timestamp> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Timestamp deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            String dateStr = jsonElement.getAsString();
            return new Timestamp(dateFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }
}
