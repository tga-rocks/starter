package com.tga.starter;

@Configuration
public class JaegerConfig {

  @Bean
  public Tracer tracer() {
    return new io.jaegertracing.Configuration(
        "starter",
        new io.jaegertracing.Configuration.SamplerConfiguration(1),
        new io.jaegertracing.Configuration.ReporterConfiguration())
        .getTracer();
  }

  @Bean
  public Brave brave(Tracer tracer) {
    return Brave.newBuilder().traceId128Bit(true).reporter(new JaegerSpanReporter(tracer)).build();
  }

  @Bean
  public BraveServletFilter braveServletFilter(Brave brave) {
    return new BraveServletFilter(brave.serverRequestInterceptor(), brave.serverResponseInterceptor(), new DefaultSpanNameProvider());
  }

  @Bean
  public RestTemplate restTemplate(Brave brave) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getInterceptors().add(new BraveClientHttpRequestInterceptor(brave.clientRequestInterceptor(), brave.clientResponseInterceptor(), new DefaultSpanNameProvider()));
    return restTemplate;
  }
}
