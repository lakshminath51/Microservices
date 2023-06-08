package com.lakshminath.covidAlertService.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakshminath.covidAlertService.dto.AlertStatus;
import com.lakshminath.covidAlertService.dto.StateData;
import com.lakshminath.covidAlertService.dto.SummaryData;

@Service
public class AlertService {

	@Autowired
	private Covid19DataProvider covid19DataProvider;
	
	public AlertStatus getAlertAboutState(String state) {
		
		AlertStatus alertStatus = new AlertStatus();
		
		//business logic to derive the alert goes here
		
		StateData stateData = covid19DataProvider.getStateData(state);
		
		alertStatus.setSummaryData(stateData);
		
		if(stateData.getTotalConfirmed()<1000) {
			alertStatus.setAlertLevel("GREEN");
			alertStatus.setMeasuresToBeTaken(Arrays.asList("Everything is normal!!"));
		}else if(stateData.getTotalConfirmed()>1000 && stateData.getTotalConfirmed() <10000) {
			alertStatus.setAlertLevel("ORANGE");
			alertStatus.setMeasuresToBeTaken(Arrays.asList("Only Essential services are allowed", "List of services that comes under essential service"));
		}else{
			alertStatus.setAlertLevel("RED");
			alertStatus.setMeasuresToBeTaken(Arrays.asList("Absolute lock-down", "Only Mediacl and Foodservices are allowed"));
		}

		return alertStatus;
		
	}

	public SummaryData getOverAllSummary() {
		return covid19DataProvider.getSummaryData("summary");
	}

	
}
