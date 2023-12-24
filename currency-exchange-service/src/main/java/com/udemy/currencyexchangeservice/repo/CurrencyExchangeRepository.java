package com.udemy.currencyexchangeservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.currencyexchangeservice.Model.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	
	CurrencyExchange findByFromAndTo(String from,String to);

}
