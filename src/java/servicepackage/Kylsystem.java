package servicepackage;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "kylsystem")
public class Kylsystem implements Serializable{
    private static final long serialVersionUID = 1L;
    private int temperatur;
    private int serverhallsId;
    private int teknikerId;
    private String teknikerNamn;
    private Date date;
    
    public Kylsystem(){
        
    }
    
    public Kylsystem(int temperatur, int teknikerId){
        this.temperatur = temperatur;
        this.teknikerId = teknikerId;
    }
    
        public Kylsystem(int temperatur, int teknikerId, String teknikerNamn, Date date){
        this.temperatur = temperatur;
        this.teknikerId = teknikerId;
        this.teknikerNamn = teknikerNamn;
        this.date = date;
    }
    
    @XmlElement 
    public void setTemp(int temperatur){
        this.temperatur = temperatur;
    }
    @XmlElement 
    public void setServerhallsId(int serverhallsId){
        this.serverhallsId = serverhallsId;
    }
    @XmlElement 
    public void setTeknikerId(int teknikerId){
        this.teknikerId = teknikerId;
    }
    
    @XmlElement 
    public void setTeknikerNamn(String teknikerNamn){
        this.teknikerNamn = teknikerNamn;
    }
    
    @XmlElement 
    public void setDate(Date date){
        this.date = date;
    }
    
    public int getTemp(){
        return temperatur;
    }
    
    public int getServerhallsId(){
        return serverhallsId;
    }
    
    public int getTeknikerId(){
        return teknikerId;
    }
    
    public String getTeknikerNamn(){
        return teknikerNamn;
    }
    
    public Date getDate(){
        return date;
    }
    
    public String getOurTimeZone(Date d){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df.format(d);
    }
}
