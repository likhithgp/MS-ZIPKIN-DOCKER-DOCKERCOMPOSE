package com.udemy.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.currencyexchangeservice.Model.CurrencyExchange;
import com.udemy.currencyexchangeservice.repo.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	private  Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@Autowired
	CurrencyExchangeRepository repo;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/currency/from/{from}/to/{to}")
	public CurrencyExchange retiveExchangeValue(@PathVariable String from,
			@PathVariable String to) {
		
		return new CurrencyExchange(1000L,from,to,BigDecimal.valueOf(50),
				env.getProperty("local.server.port"));
		
	}
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retiveExchangeValueDb(@PathVariable String from,
			@PathVariable String to) {
		logger.info("Currency Exchange called");
		String port =env.getProperty("local.server.port");
		
		 CurrencyExchange ce= repo.findByFromAndTo(from, to);
		 ce.setEnvironment(port);
		 
		 if(ce==null)throw new RuntimeException("Unable to find data for "+from+" and "+to);
		 
		
		 
		 return ce;
		
	}


}
