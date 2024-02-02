package zxf.java.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;

public class WiremockClientTests {
    public static void main(String[] args) {
        WireMock.configureFor("localhost", 8089);
        // Dynamically add mock to Mock server.
        WireMock.stubFor(WireMock.get("/testing").willReturn(WireMock.ok("Hello ZAN!")));
    }
}
