package zxf.java.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WiremockServerTests {
    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(options().port(8089).withRootDirectory("src/main/resources"));
        wireMockServer.stubFor(WireMock.get("/greeting").willReturn(WireMock.ok("Hello Davis!")));
        wireMockServer.start();
    }
}
