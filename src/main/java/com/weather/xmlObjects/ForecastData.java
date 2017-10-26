package com.weather.xmlObjects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ForecastData {

 	String date;
    String phenomenon;
    String time;
    String tempmin;
    String tempmax;
    String text;
    
	String placeName;
	String placePhenomenon;
	String placeTempmin;
	String placeTempmax;
	
	String windName;
	String windDirection;
	String windSpeedmin;
	String windSpeedmax;
		
	String sea;
	String peipsi;

	//Date
    public String getDate() {
        return date;
    }
    @XmlAttribute
    public void setDate(String date) {
        this.date = date;
    }
    
    //Time
    public String getTime() {
        return time;
    }
    @XmlElement
    public void setTime(String time) {
        this.time = time;
    }
    
    //Phenomenon
    public String getPhenomenon() {
        return phenomenon;
    }
    @XmlElement
    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }
    
	//Tempmin
    public String getTempmin() {
        return tempmin;
    }
    @XmlElement
    public void setTempmin(String tempmin) {
        this.tempmin = tempmin;
    }
    
    //Tempmax
    public String getTempmax() {
        return tempmax;
    }
    @XmlElement
    public void setTempmax(String tempmax) {
        this.tempmax = tempmax;
    }
    
    //Text
    public String getText() {
        return text;
    }
    @XmlElement
    public void setText(String text) {
        this.text = text;
    }

    //PlaceName
    public String getPlaceName() {
        return placeName;
    }
    @XmlElement
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    
    //PlacePhenomenon
    public String getPlacePhenomenon() {
        return placePhenomenon;
    }
    @XmlElement
    public void setPlacePhenomenon(String placePhenomenon) {
        this.placePhenomenon = placePhenomenon;
    }
    
	//PlaceTempmin
    public String getPlaceTempmin() {
        return placeTempmin;
    }
    @XmlElement
    public void setPlaceTempmin(String placeTempmin) {
        this.placeTempmin = placeTempmin;
    }
    
   	//PlaceTempmin
    public String getPlaceTempmax() {
       return placeTempmax;
    }
    @XmlElement
    public void setPlaceTempmax(String placeTempmax) {
       this.placeTempmax = placeTempmax;
    }
    
    //WindName
    public String getWindName() {
        return windName;
    }
    @XmlElement
    public void setWindName(String windName) {
        this.windName = windName;
    }
    
    //WindDirection
    public String getWindDirection() {
        return windDirection;
    }
    @XmlElement
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }
    
	//WindSpeedmin
    public String getWindSpeedmin() {
        return windSpeedmin;
    }
    @XmlElement
    public void setWindSpeedmin(String windSpeedmin) {
        this.windSpeedmin = windSpeedmin;
    }
    
    //WindSpeedmax
    public String getWindSpeedmax() {
        return windSpeedmax;
    }
    @XmlElement
    public void setWindSpeedmax(String windSpeedmax) {
        this.windSpeedmax = windSpeedmax;
    }
    
    //Sea
    public String getSea() {
        return sea;
    }
    @XmlAttribute
    public void setSea(String sea) {
        this.sea = sea;
    }
    
    //Peipsi
    public String getPeipsi() {
        return peipsi;
    }
    @XmlElement
    public void setPeipsi(String peipsi) {
        this.peipsi = peipsi;
    }
    
}
