package com.udemy.currencyconversionservice.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.udemy.currencyconversionservice.model.CurrencyConversion;

@FeignClient(name="currency-exchange")
public interface CurrencyConversionFieng {

	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retiveExchangeValueDb(@PathVariable String from,
			@PathVariable String to);
}
