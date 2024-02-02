# Start standalone
- java -jar wiremock-2.1.10-standalone.jar –port 9999 —verbose

# Core classes
- com.github.tomakehurst.wiremock.WireMockServer;
- com.github.tomakehurst.wiremock.client.WireMock;
- com.github.tomakehurst.wiremock.core.WireMockConfiguration
- com.github.tomakehurst.wiremock.matching.ValueMatcher
- com.github.tomakehurst.wiremock.matching.EqualToJsonPattern
- com.github.tomakehurst.wiremock.matching.EqualToPattern

# Testing
- curl http://localhost:8089/greeting
- curl http://localhost:8089/testing
- curl http://localhost:8089/abc
- curl http://localhost:8089/abc?code=400
