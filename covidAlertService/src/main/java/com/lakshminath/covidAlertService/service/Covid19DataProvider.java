package com.lakshminath.covidAlertService.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lakshminath.covidAlertService.dto.CovidApiData;
import com.lakshminath.covidAlertService.dto.StateData;
import com.lakshminath.covidAlertService.dto.SummaryData;

@Service
public class Covid19DataProvider {

    static final String URL = "https://api.rootnet.in/covid19-in/stats/latest";
	
    @Autowired
    RestTemplate restTemplate; 
    
    StateData getStateData(String state) {
	  CovidApiData covidApiData =  restTemplate.getForObject(URL, CovidApiData.class);
	  
	  return Arrays.stream(covidApiData.getData().getRegional())
	  .filter(e -> e.getLoc().equalsIgnoreCase(state)).findAny().orElse(new StateData());
	  
	  
			  
	}

	public SummaryData getSummaryData(String string) {
		 CovidApiData covidApiData =  restTemplate.getForObject(URL, CovidApiData.class);
		 
		 SummaryData summaryData = covidApiData.getData().getSummary();
	
		 summaryData.setUpdateTime(covidApiData.getLastRefreshed());
		 return summaryData;
	}
    
    
    
}
