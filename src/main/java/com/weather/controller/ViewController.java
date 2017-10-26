package com.weather.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.weather.xmlObjects.ForecastData;

public class ViewController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	//LinkedHashMap is chosen, because it maintains insertion order (HashMap is unordered, TreeMap has automatic sort by key)
	public static ArrayList<LinkedHashMap<String, String>> forecasts = new ArrayList<LinkedHashMap<String, String>>();
	public static int forecastsListSizeModified = 0;
	private static int counter = 0;

	//Check for null and empty value
	public static boolean empty( final String s ) {
		  // Null-safe, short-circuit evaluation
		  return s == null || s.trim().isEmpty();
		}

	public static void convertToShow(ArrayList<ForecastData> fdArray, ModelMap model)  {

		for (ForecastData fd : fdArray) {
			LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
				if(!empty(fd.getDate())) values.put("Date", fd.getDate());
				if(!empty(fd.getTime())) values.put("Unit of time", fd.getTime());
				if(!empty(fd.getPhenomenon())) values.put("Phenomenon", fd.getPhenomenon());
				if(!empty(fd.getTempmin())) values.put("Minimum temperature", fd.getTempmin());
				if(!empty(fd.getTempmax())) values.put("Maximum temperature", fd.getTempmax());
				if(!empty(fd.getText())) values.put("Description", fd.getText());
				if(!empty(fd.getPlaceName())) values.put("Name of the location", fd.getPlaceName());
				if(!empty(fd.getPlacePhenomenon())) values.put("Phenomenon of this location", fd.getPlacePhenomenon());
				if(!empty(fd.getPlaceTempmin())) values.put("Minimum temperature of the location", fd.getPlaceTempmin());
				if(!empty(fd.getPlaceTempmax())) values.put("Maximum temperature of the location", fd.getPlaceTempmax());
				if(!empty(fd.getWindName())) values.put("Wind location", fd.getWindName());
				if(!empty(fd.getWindDirection())) values.put("Wind direction", fd.getWindDirection());
				if(!empty(fd.getWindSpeedmin())) values.put("Wind minimum speed", fd.getWindSpeedmin());
				if(!empty(fd.getWindSpeedmax())) values.put("Wind maximum speed", fd.getWindSpeedmax());
				if(!empty(fd.getSea())) values.put("Sea", fd.getSea());
				if(!empty(fd.getPeipsi())) values.put("Peipsi", fd.getPeipsi());
				forecasts.add(values);
		}
		
		//Avoid adding values, when page is refreshed
		if (counter == 0) {
			int forecastsListSize = forecasts.size();
			forecastsListSizeModified = (forecastsListSize) - 1;
			counter++;

		}
		
		ServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("forecasts", forecasts);	
			
		}
	}

