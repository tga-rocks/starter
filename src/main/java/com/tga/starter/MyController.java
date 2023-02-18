package com.tga.starter;

@RestController
public class MyController {

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/api/data")
  public String getData() {
    String url = "https://api.external.com/data";
    String response = restTemplate.getForObject(url, String.class);
    return "External API Response: " + response;
  }
}

