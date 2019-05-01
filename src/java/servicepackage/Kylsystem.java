package servicepackage;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "kylsystem")
public class Kylsystem implements Serializable{
    private static final long serialVersionUID = 1L;
    private int temperatur;
    private int serverhallsId;
    private int teknikerId;
    
    public Kylsystem(){
        
    }
    
    public Kylsystem(int temperatur, int teknikerId){
        this.temperatur = temperatur;
        this.teknikerId = teknikerId;
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
    
    public int getTemp(){
        return temperatur;
    }
    
    public int getServerhallsId(){
        return serverhallsId;
    }
    
    public int getTeknikerId(){
        return teknikerId;
    }
}
