package com.udemy.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private  Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api", fallbackMethod = "call")
	//@CircuitBreaker(name = "default", fallbackMethod = "call")
	//To limit the API call per second or for customized time like telling allow only 10 API call's per 1 second
	//@RateLimiter(name = "default")
	@Bulkhead(name = "default")
	public String SampleApi() {
		logger.info("Sample API");
		//ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/uri", String.class);
		//return entity.getBody();
		return "Sample API";
	}
	
	public String call(Exception ex) {
		return "FallBack Response for Sample API";
	}
}
