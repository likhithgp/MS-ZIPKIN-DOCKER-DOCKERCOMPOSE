package com.udemy.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.udemy.currencyconversionservice.feing.CurrencyConversionFieng;
import com.udemy.currencyconversionservice.model.CurrencyConversion;


@RestController
public class CurrencyConversionController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CurrencyConversionFieng fieng;
	
  @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateConversionRate(@PathVariable String to,
		  @PathVariable String from,@PathVariable BigDecimal quantity) {	
	  
	  String port = env.getProperty("local.server.port");
	  
	  Map<String,String> uriVar= new HashMap<>();
	  uriVar.put("from", from);
	  uriVar.put("to", to);
	  
	  ResponseEntity<CurrencyConversion> entity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/USD/to/INR", 
			  CurrencyConversion.class,uriVar);
	  
	  CurrencyConversion ce = entity.getBody();
	  
	  return new CurrencyConversion(ce.getId(),from,to,quantity,BigDecimal.ONE,ce.getConversionMultiple().multiply(quantity),ce.getEnvironment());
	 

  }
  
  @GetMapping("currency-conversion-fieng/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateConversionRatefieng(@PathVariable String to,
		  @PathVariable String from,@PathVariable BigDecimal quantity) {	
	  
	  String port = env.getProperty("local.server.port");
	  
	 
	  
	  CurrencyConversion ce = fieng.retiveExchangeValueDb(from, to);
	  
	  return new CurrencyConversion(ce.getId(),from,to,quantity,BigDecimal.ONE,ce.getConversionMultiple().multiply(quantity),ce.getEnvironment());
	 

  }
}
