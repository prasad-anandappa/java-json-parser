
package dev.prasad.json;
import java.util.*;
public sealed interface JsonValue permits JsonValue.JsonObject, JsonValue.JsonArray, JsonValue.JsonString, JsonValue.JsonNumber, JsonValue.JsonBoolean, JsonValue.JsonNull {
  record JsonObject(LinkedHashMap<String,JsonValue> entries) implements JsonValue { public JsonValue get(String k){return entries.get(k);} }
  record JsonArray(List<JsonValue> items) implements JsonValue {}
  record JsonString(String value) implements JsonValue {}
  record JsonNumber(String raw) implements JsonValue { public double doubleValue(){return Double.parseDouble(raw);} public long longValue(){return Long.parseLong(raw);} }
  record JsonBoolean(boolean value) implements JsonValue {}
  record JsonNull() implements JsonValue {}
  JsonNull NULL=new JsonNull(); JsonBoolean TRUE=new JsonBoolean(true); JsonBoolean FALSE=new JsonBoolean(false);
  static JsonObject obj(Object... kv){ var m=new LinkedHashMap<String,JsonValue>(); for(int i=0;i<kv.length;i+=2) m.put((String)kv[i],(JsonValue)kv[i+1]); return new JsonObject(m); }
  static JsonString str(String s){return new JsonString(s);} static JsonNumber num(long v){return new JsonNumber(Long.toString(v));} static JsonNumber num(double v){return new JsonNumber(Double.toString(v));}
}
