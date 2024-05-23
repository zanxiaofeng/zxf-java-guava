package zxf.java.jsonflatten;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;

import java.util.Map;

public class JsonFlattenTests {
    public static void main(String[] args) {
        String json = "{ \"a\" : { \"b\" : 1, \"c\": null, \"d\": [false, true] }, \"e\": \"f\", \"g\":2.3 }";
        Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(json);
        System.out.println(flattenJson);
        String jsonStr = JsonFlattener.flatten(json);
        System.out.println(jsonStr);
        String nestedJson = JsonUnflattener.unflatten(jsonStr);
        System.out.println(nestedJson);
    }
}
