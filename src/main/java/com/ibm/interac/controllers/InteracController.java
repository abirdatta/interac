package com.ibm.interac.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.interac.domain.Transfer;
import com.ibm.interac.services.InteracService;
import com.ibm.interac.services.InteracServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@RestController
public class InteracController
{
	private static final Logger logger = LoggerFactory.getLogger(InteracServiceImpl.class);

	@Autowired
	private InteracService interacService;

	@RequestMapping(path = "/transfer/{id}", method = RequestMethod.GET)
	public Transfer getTransfer(@PathVariable String id)
	{
		logger.debug("Fetching transfer with id: %s", id);
		return interacService.find(id);
	}

	@RequestMapping(path = "/transfer", method = RequestMethod.POST)
	public Transfer createNewTransfer(@RequestBody(required = true) Transfer transfer)
	{
		logger.debug("Creating transfer: %s", transfer);
		return interacService.createTransfer(transfer);
	}
	
	
}