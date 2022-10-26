package persistence;

import org.json.JSONArray;

// Interface indicating class can be converted to a Json array
// Credit: taken from JsonSerialisationDemo
public interface Writable {

    // EFFECTS: returns this as JSON array
    JSONArray toJson();
}
