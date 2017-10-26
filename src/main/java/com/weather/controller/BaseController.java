package com.weather.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.weather.xmlObjects.ForecastData;


@Controller
public class BaseController {

	private static final String VIEW_INDEX = "index";
    private static ForecastData fd = new ForecastData();
    private static ArrayList<ForecastData> fdArray = new ArrayList<ForecastData>();

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String weatherGetter(String[] args, ModelMap model) throws Exception {

		String url = "http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng");
		CloseableHttpResponse response1 = httpclient.execute(httpGet);

		try {
		    
		    HttpEntity entity1 = response1.getEntity();

		    // Get content from url
		    Content websiteContentXml = Request.Get(url).execute().returnContent();
		    String vebsiteContentXmlValue = valueOf(websiteContentXml);
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();	        
	        DocumentBuilder builder = factory.newDocumentBuilder();	 
	        
	        // Use String reader to convert input string of website to xml
	        Document document = builder.parse( new InputSource( new StringReader( vebsiteContentXmlValue ) ) );	 
	        document.getDocumentElement().normalize();

	        NodeList listOfForecasts = document.getElementsByTagName("forecast");
	        int totalForecasts = listOfForecasts.getLength();
	        
	        if (totalForecasts > 0){
	        	
	        	//Iterate through all xml nodes 
	        	for (int i = 0; i < listOfForecasts.getLength(); i++)
	        	{
	        	    Element labTest = (Element) listOfForecasts.item(i);

	        		String date = listOfForecasts.item(i).getAttributes().getNamedItem("date").getNodeValue();	        
	        		
	        	    //NIGHT START 
	        	    NodeList nightValues = labTest.getElementsByTagName("night");
	        	    
	    	        fd = new ForecastData();
	        	    fd = addDateXMLMainValues(nightValues, date, "Night");
	        		fdArray.add(fd);
	        		
	    	        fd = new ForecastData();
	        		fd = addDateXMLPlaceValues(nightValues, date, "Night");
	        		
	    	        fd = new ForecastData();
	        		fd = addDateXMLWindValues(nightValues, date, "Night");	        		
	        		//NIGHT END
	        		//DAY START
	        	    NodeList dayValues = labTest.getElementsByTagName("day");

	        	    fd = new ForecastData();
	        	    fd = addDateXMLMainValues(dayValues, date, "Day");
	        		fdArray.add(fd);
	        		
	    	        fd = new ForecastData();
	        		fd = addDateXMLPlaceValues(dayValues, date, "Day");
	        		
	    	        fd = new ForecastData();
	        		fd = addDateXMLWindValues(dayValues, date, "Day");	        		
	        	    //DAY END
	        	}
	        }
	        
		    EntityUtils.consume(entity1);
		} finally {
			//correct deallocation of system resources
		    response1.close();
		}

		model.addAttribute("copyright", "@PoukisaOufesa");

		ViewController.convertToShow(fdArray, model);
		
		return VIEW_INDEX;
    }

    public static String valueOf(Object obj)
    {
        return (obj == null) ? "null" : obj.toString();
    }
    
    //Check tad for existence and get its value
	private static String getXmlValue(Element value, String valueName) {
		String xmlValue = null;
		if (value.getElementsByTagName(valueName).getLength() > 0){
        	xmlValue = value.getElementsByTagName(valueName).item(0).getTextContent(); 
        	return xmlValue;
		} else return null;
	}

	private ForecastData addDateXMLMainValues(NodeList list, String date, String unitOfTime) {
	    
		ForecastData fdMain = new ForecastData();
		
		if (list.getLength() > 0) 
		{

		    for (int j = 0; j < list.getLength(); j++)
	 	    {
	 	    	
		    	fdMain = new ForecastData();
		    	
			    fdMain.setDate(date);
			    fdMain.setTime(unitOfTime);

	 	        Element value = (Element) list.item(j);

	 	        String phenomenon = getXmlValue(value, "phenomenon");
	 	        fdMain.setPhenomenon(phenomenon);
	  	        String tempmin = getXmlValue(value, "tempmin"); 
	  	        fdMain.setTempmin(tempmin);;
	  	        String tempmax = getXmlValue(value, "tempmax");
	  	        fdMain.setTempmax(tempmax);
	  	        String text = getXmlValue(value, "text");
	  	        fdMain.setText(text);	        	        
	  	        
	 	        String sea = getXmlValue(value, "sea");	
	 	        fdMain.setSea(sea);
	 	        String peipsi = getXmlValue(value, "peipsi");	        	        
	 	        fdMain.setPeipsi(peipsi);
	 	        
	 	    }
		    
			return fdMain;
			
		}
		else {
			return null;
		}
	}
	
	private ForecastData addDateXMLPlaceValues(NodeList list, String date, String unitOfTime) {

		ForecastData fdPlace = new ForecastData();
		
		if (list.getLength() > 0) 
		{

			for (int j = 0; j < list.getLength(); j++)
	 	    {
	 	    	
	 	        Element value = (Element) list.item(j);
	 	        
	 	        if (value.getElementsByTagName("place").getLength() > 0){
	
		 	        NodeList conditionList = value.getElementsByTagName("place");
		 	        for (int k = 0; k < conditionList.getLength(); k++)
		 	        {
		 	        	fdPlace = new ForecastData();

		 				fdPlace.setDate(date);
		 				fdPlace.setTime(unitOfTime);
		 				
		     	        Element value1 = (Element) conditionList.item(k);
		     	        
		     	        String placeName = getXmlValue(value1, "name");	      
		     	        fdPlace.setPlaceName(placeName);
		     	        String placePhenomenon = getXmlValue(value1, "phenomenon");	 
		     	        fdPlace.setPlacePhenomenon(placePhenomenon);
		     	        String placeTempmin = getXmlValue(value1, "tempmin");	        	        
		     	        if (placeTempmin != null) fdPlace.setPlaceTempmin(placeTempmin);
		     	        String placeTempmax = getXmlValue(value1, "tempmax");	        	        
		     	        if (placeTempmax != null) fdPlace.setPlaceTempmin(placeTempmax);

		      	        fdArray.add(fdPlace);

		 	        }
	 	        }
	 	    }
			
			return fdPlace;
			
		} else 
		{
			return null;
		}
		
	}
	
	private ForecastData addDateXMLWindValues(NodeList list, String date, String unitOfTime) {
		
     	ForecastData fdWind = new ForecastData();
     	if (list.getLength() > 0) 
		{

			for (int j = 0; j < list.getLength(); j++)
	 	    {
	 	    	
	 	        Element value = (Element) list.item(j);
	 	        
	 	        if (value.getElementsByTagName("wind").getLength() > 0){
	
		 	        NodeList windList = value.getElementsByTagName("wind");
		 	        for (int l = 0; l < windList.getLength(); l++)
		 	        {
		 	        	fdWind = new ForecastData();

		 		     	fdWind.setDate(date);
		 		     	fdWind.setTime(unitOfTime);
		 		     	
		 	        	Element value2 = (Element) windList.item(l);
		     	        String windName = getXmlValue(value2, "name");	 
		     	        fdWind.setWindName(windName);
		     	        String windDirection = getXmlValue(value2, "direction");
		     	        fdWind.setWindDirection(windDirection);
		     	        String windSpeedmin = getXmlValue(value2, "speedmin");	
		     	        fdWind.setWindSpeedmin(windSpeedmin);
		     	        String windSpeedmax = getXmlValue(value2, "speedmax");
		     	        fdWind.setWindSpeedmax(windSpeedmax);

		      	        fdArray.add(fdWind);
		 	        }
	 	    	}
	 	    }
			
			return fdWind;
			
		} else 
		{
			return null;
		}
	}
}