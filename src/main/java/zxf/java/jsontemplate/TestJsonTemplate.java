package zxf.java.jsontemplate;

import com.github.jsontemplate.JsonTemplate;

public class TestJsonTemplate {
    public static void main(String[] args) {
        String template1 = "{" +
                "  name : @s," +
                "  age : @i(max=100)," +
                "  role : @s[](min=1, max=3)," +
                "  address : {" +
                "    city : @s," +
                "    street : @s," +
                "    number : @i" +
                "  }" +
                "}";
        String json1 = new JsonTemplate(template1).prettyString();
        System.out.println(json1);

        String template2 = "{" +
                "  name : $name," +
                "  age : @i(max=100)," +
                "  role : @s[](min=1, max=3)," +
                "  address : {" +
                "    city : @s," +
                "    street : @s," +
                "    number : @i" +
                "  }" +
                "}";
        String json2 = new JsonTemplate(template2).withVar("name", "davis").prettyString();
        System.out.println(json2);
    }
}
