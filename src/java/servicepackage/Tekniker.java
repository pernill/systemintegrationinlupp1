package servicepackage;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tekniker") 
public class Tekniker implements Serializable{
    private static final long serialVersionUID = 1L;
    private String namn;
    
    public Tekniker(){
        
    }
    
    public Tekniker(String namn){
        this.namn = namn;
    }
    
    @XmlElement 
    public void setName(String namn){
        this.namn = namn;
    }
    
    public String getName(){
        return namn;
    }
}
