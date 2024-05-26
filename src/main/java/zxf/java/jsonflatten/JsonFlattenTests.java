package zxf.java.jsonflatten;

import com.github.wnameless.json.flattener.FlattenMode;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;

import java.util.Map;

public class JsonFlattenTests {
    public static void main(String[] args) {
        String unflattenJson = "{ \"a\" : { \"b\" : 1, \"c\": null, \"d\": [false, true] }, \"e\": \"f\", \"g\":2.3 }";
        System.out.println(JsonFlattener.flatten(unflattenJson));

        Map<String, Object> flattenJsonMap = JsonFlattener.flattenAsMap(unflattenJson);
        printMap(flattenJsonMap);

        System.out.println(JsonUnflattener.unflatten(flattenJsonMap));
        System.out.println(new JsonUnflattener(flattenJsonMap).withFlattenMode(FlattenMode.KEEP_ARRAYS).unflatten());
        printMap(JsonUnflattener.unflattenAsMap(flattenJsonMap));

        String flattenJson = "{\"a.b\":1,\"a.c\":null,\"a.d[0]\":false,\"a.d[5]\":true,\"e\":\"f\",\"g\":2.3}";
        System.out.println(JsonUnflattener.unflatten(flattenJson));
        System.out.println(new JsonUnflattener(flattenJson).withFlattenMode(FlattenMode.KEEP_ARRAYS).unflatten());
        printMap(JsonUnflattener.unflattenAsMap(flattenJson));
    }

    private static void printMap(Map<String, Object> map) {
        System.out.println(map.getClass() + ":" + map);
    }
}
