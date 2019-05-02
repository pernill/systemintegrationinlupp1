package servicepackage;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "temperature") 
public class Temperatur implements Serializable{
    private static final long serialVersionUID = 1L;
    private float temp;
    private Date date;
    private int id;
    private float averageTemp;
    private float maxTemp;
    private float minTemp;
    
    public Temperatur(){}
    
    public Temperatur(float temp, Date date, int id, float averageTemp, float maxTemp, float minTemp){
        this.temp = temp;
        this.date = date;
        this.id = id;
        this.averageTemp = averageTemp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }
    @XmlElement 
    public void setTemp(float temp){
        this.temp = temp;
    }
    @XmlElement 
    public void setDate(Date date){
        this.date = date;
    }
    @XmlElement 
    public void setId(int id){
        this.id = id;
    }
    @XmlElement 
    public void setAverageTemp(float averageTemp){
        this.averageTemp = averageTemp;
    }
    @XmlElement 
    public void setMaxTemp(float maxTemp){
        this.maxTemp = maxTemp;
    }
    @XmlElement 
    public void setMinTemp(float minTemp){
        this.minTemp = minTemp;
    }
    public float getTemp(){
        return temp;
    }
    
    public Date getDate(){
        return date;
    }
    
    public int getId(){
        return id;
    }
    
    public float getAverageTemp(){
        return averageTemp;
    }
    
    public float getMaxTemp(){
        return maxTemp;
    }
    
    public float getMinTemp(){
        return minTemp;
    }
    
    public String getOurTimeZone(Date d){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df.format(d);
    }
    
}
