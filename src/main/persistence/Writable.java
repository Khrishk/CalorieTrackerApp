package persistence;

import org.json.JSONObject;
// Citation : Uses code from sample file "JsonSerializationDemo"

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
