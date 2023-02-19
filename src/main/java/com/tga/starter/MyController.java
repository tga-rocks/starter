package com.tga.starter;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  private static final String responseTemplate = "some API response: %s";
  private final AtomicLong someData = new AtomicLong();

  @GetMapping("/data")
  public MyData myData(@RequestParam(value = "label", defaultValue = "someLabel") String label) {
    return new MyData(String.format(responseTemplate, label), someData.incrementAndGet());
  }
}

