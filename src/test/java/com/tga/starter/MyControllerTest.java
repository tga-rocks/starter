package com.tga.starter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class MyControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private WireMockServer wireMockServer;

  @Test
  public void testGetData() {
    // Set up WireMock stub for the external API
    wireMockServer.stubFor(WireMock.get("/data")
        .willReturn(WireMock.aResponse()
            .withStatus(HttpStatus.OK.value())
            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .withBody("Test Data")));

    // Call the REST API using TestRestTemplate
    ResponseEntity<String> response = restTemplate.getForEntity("/api/data", String.class);

    // Verify the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("External API Response: Test Data", response.getBody());

    // Verify the WireMock stub was called
    wireMockServer.verify(WireMock.getRequestedFor(WireMock.urlEqualTo("/data")));
  }
}
