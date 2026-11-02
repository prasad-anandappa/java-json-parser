
package dev.prasad.json;
import org.junit.jupiter.api.*; import static org.junit.jupiter.api.Assertions.*; import dev.prasad.json.JsonValue.*;
class JsonTest {
  @Test void parsesObject(){ var v=(JsonObject)Json.parse("{\"name\":\"Prasad\",\"age\":30}"); assertEquals("Prasad",((JsonString)v.get("name")).value()); assertEquals(30,((JsonNumber)v.get("age")).longValue()); }
  @Test void parsesNestedAndArray(){ var v=(JsonObject)Json.parse("{\"a\":[1,2,{\"b\":true}]}"); var arr=(JsonArray)v.get("a"); assertEquals(3,arr.items().size()); assertEquals(JsonValue.TRUE,((JsonObject)arr.items().get(2)).get("b")); }
  @Test void parsesNullBoolNumber(){ assertEquals(JsonValue.NULL,Json.parse("null")); assertEquals(JsonValue.FALSE,Json.parse(" false ")); assertEquals(-12.5,((JsonNumber)Json.parse("-12.5")).doubleValue(),1e-9); assertEquals(1000.0,((JsonNumber)Json.parse("1e3")).doubleValue(),1e-9); }
  @Test void escapesAndUnicode(){ var v=(JsonString)Json.parse("\"a\\n\\u0041\\\"q\\\"\""); assertEquals("a\nA\"q\"",v.value()); }
  @Test void roundTrip(){ String src="{\"x\":[1,2.5,null,true,\"hi\"],\"y\":{}}"; assertEquals(src,Json.stringify(Json.parse(src))); }
  @Test void stringifyEscapes(){ assertEquals("\"a\\\"b\"",Json.stringify(new JsonString("a\"b"))); }
  @Test void emptyStructures(){ assertEquals("{}",Json.stringify(Json.parse("{}"))); assertEquals("[]",Json.stringify(Json.parse("[]"))); }
  @Test void malformedThrows(){ assertThrows(Json.JsonParseException.class,()->Json.parse("{")); assertThrows(Json.JsonParseException.class,()->Json.parse("[1,")); assertThrows(Json.JsonParseException.class,()->Json.parse("{\"a\":}")); assertThrows(Json.JsonParseException.class,()->Json.parse("tru")); }
  @Test void buildsProgrammatically(){ var v=JsonValue.obj("ok",JsonValue.TRUE,"n",JsonValue.num(42)); assertEquals("{\"ok\":true,\"n\":42}",Json.stringify(v)); }
}
