package servicepackage;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "electricity") 
public class Electricity implements Serializable{
    private static final long serialVersionUID = 1L;
    private float el;
    private Date date;
    private int id;
    private float averageEl;
    private float maxEl;
    private float minEl;
    private float elpris;
    private Date dyrastTid;
    private Date billigastTid;
    
    public Electricity(){}
    
    public Electricity(float el, Date date, int id, float averageEl, float maxEl, float minEl, float elpris, Date dyrastTid, Date billigastTid){
        this.el = el;
        this.date = date;
        this.id = id;
        this.averageEl = averageEl;
        this.maxEl = maxEl;
        this.minEl = minEl;
        this.elpris = elpris;
        this.dyrastTid = dyrastTid;
        this.billigastTid = billigastTid;
    }
    @XmlElement 
    public void setEl(float el){
        this.el = el;
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
    public void setAverageEl(float averageEl){
        this.averageEl = averageEl;
    }
    @XmlElement 
    public void setMaxEl(float maxEl){
        this.maxEl = maxEl;
    }
    @XmlElement 
    public void setMinEl(float minEl){
        this.minEl = minEl;
    }
    @XmlElement 
    public void setElKostnad(float elpris){
        this.elpris = elpris;
    }
    @XmlElement 
    public void setDyrastTid(Date dyrastTid){
        this.dyrastTid = dyrastTid;
    }
    @XmlElement 
    public void setBilligastTid(Date billigastTid){
        this.billigastTid = billigastTid;
    }
    
    public float getEl(){
        return el;
    }
    
    public Date getDate(){
        return date;
    }
    
    public int getId(){
        return id;
    }
    
    public float getAverageEl(){
        return averageEl;
    }
    
    public float getMaxEl(){
        return maxEl;
    }
    
    public float getMinEl(){
        return minEl;
    }
    
    public float getElKostnad(){
        return elpris;
    }
    
    public Date getDyrastTid(){
        return dyrastTid;
    }
    
    public Date getBilligastTid(){
        return billigastTid;
    }
}
