package com.lakshminath.covidAlertService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lakshminath.covidAlertService.dto.AlertStatus;
import com.lakshminath.covidAlertService.dto.SummaryData;
import com.lakshminath.covidAlertService.service.AlertService;

@RestController
@RequestMapping("/india")
public class AlertController {

	@Autowired
	private AlertService alertService;
	
	@GetMapping("/{state}")
	public AlertStatus getAlertAboutStatus(@PathVariable String state) {
		return alertService.getAlertAboutState(state);
	}
	

	@GetMapping("/summary")
	public SummaryData getOverAllSummary(){
		return alertService.getOverAllSummary();
	}
	
	
	
	
}
