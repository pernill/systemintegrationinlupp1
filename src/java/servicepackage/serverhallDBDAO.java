package servicepackage;

import java.io.FileInputStream;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;




public class serverhallDBDAO {
    
    Properties p = new Properties();
    
    public serverhallDBDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            p.load(new FileInputStream("C:\\Users\\LarsB\\Documents\\databasfil\\Settings.properties.txt"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    
    
    public Temperatur getTemperatur(String id){
         Temperatur t = new Temperatur();
         int serverhallsId = 0;
         try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){
             
            PreparedStatement st = con.prepareStatement("SELECT id "
                    + "from location "
                    + "WHERE location.namn=?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                serverhallsId = (rs.getInt("id"));
            }

            PreparedStatement st2 = con.prepareStatement("SELECT temperatur, created "
                    + "from temperaturelog "
                    + "WHERE id = (SELECT max(id)from temperaturelog WHERE serverhallsId=?);");
            st2.setInt(1, serverhallsId);
            ResultSet rs2 = st2.executeQuery();
            while(rs2.next()){
                t.setTemp(rs2.getFloat("temperatur"));
                t.setDate(rs2.getDate("created"));
                System.out.println(t.getTemp());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
         return t;
    }
    
    
    public Electricity getElectricity(String id){
         Electricity el = new Electricity();
         int serverhallsId = 0;
         try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){
             
            PreparedStatement st = con.prepareStatement("SELECT id "
                    + "from location "
                    + "WHERE location.namn=?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                serverhallsId = (rs.getInt("id"));
            }
            
            PreparedStatement st2 = con.prepareStatement("SELECT elforbrukning, created "
                    + "from electricityconsumptionlog "
                    + "WHERE id = (SELECT max(id)from temperaturelog WHERE serverhallsId=?);");
            st2.setInt(1, serverhallsId);
            ResultSet rs2 = st2.executeQuery();
            while(rs2.next()){
                el.setEl(rs2.getFloat("elforbrukning"));
                el.setDate(rs2.getDate("created"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
         return el;
    }
    
    
    public void setNewElectricityPrice(String id, Electricity el){
         int serverhallsId = 0;
         try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){
             
            PreparedStatement st = con.prepareStatement("SELECT id "
                    + "from location "
                    + "WHERE location.namn=?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                serverhallsId = (rs.getInt("id"));
            }
            

            PreparedStatement st2 = con.prepareStatement("UPDATE electricitycost SET kostnad = ? WHERE serverhallsId =?");
            
            st2.setFloat(1, el.getElKostnad());
            st2.setInt(2, serverhallsId);
            st2.executeUpdate();
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Electricity getElectricityPrice(String id){
         Electricity el = new Electricity();
         int serverhallsId = 0;
         try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){
             
            PreparedStatement st = con.prepareStatement("SELECT id "
                    + "from location "
                    + "WHERE location.namn=?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                serverhallsId = (rs.getInt("id"));
            }
            

            PreparedStatement st2 = con.prepareStatement("SELECT kostnad, created "
                    + "from electricitycost "
                    + "WHERE serverhallsId=?;");
            

            st2.setInt(1, serverhallsId);
            ResultSet rs2 = st2.executeQuery();
            
            while(rs2.next()){
                el.setElKostnad(rs2.getFloat("kostnad"));
                el.setDate(rs2.getDate("created"));
                System.out.println("Kostnad "+el.getElKostnad());
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
         return el;
    }
    

    public List<Temperatur> getTempRapport(String id){
         List<Temperatur> list = new ArrayList<>();
         try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){

            PreparedStatement st = con.prepareStatement("SELECT temperatur, created "
                    + "from temperaturelog "
                    + "join serverhall on serverhall.id = temperaturelog.serverhallsId "
                    + "join location on location.id = serverhall.serverhallsId "
                    + "WHERE location.namn=?;");
            
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Temperatur t = new Temperatur();
                t.setTemp(rs.getFloat("temperatur"));
                t.setDate(rs.getDate("created"));
                list.add(t);
                System.out.println(t.getDate()+" "+t.getId()+" " +t.getTemp());
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
    
    
    public Electricity getMaxMinAverageEl(String id){
        Electricity el = new Electricity();
        try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){
            
            
            PreparedStatement st = con.prepareStatement("SELECT (avg(elforbrukning)), (max(elforbrukning)), (min(elforbrukning)) "
                    + "from electricityconsumptionlog "
                    + "join serverhall on serverhall.id = electricityconsumptionlog.serverhallsId " 
                    + "join location on location.id = serverhall.serverhallsId " 
                    + "WHERE location.namn=?;");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                el.setAverageEl(rs.getFloat("(avg(elforbrukning))"));
                el.setMaxEl(rs.getFloat("(max(elforbrukning))"));
                el.setMinEl(rs.getFloat("(min(elforbrukning))"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return el;
    }
    
    
    public Temperatur getMaxMinAverageTemp(String id){
        Temperatur t = new Temperatur();
        try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){
            
            
            PreparedStatement st = con.prepareStatement("SELECT (avg(temperatur)), (max(temperatur)), (min(temperatur)) "
                    + "from temperaturelog join serverhall on serverhall.id = temperaturelog.serverhallsId "
                    + "join location on location.id = serverhall.serverhallsId "
                    + "WHERE location.namn=? ;");

            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                t.setAverageTemp(rs.getFloat("(avg(temperatur))"));
                t.setMaxTemp(rs.getFloat("(max(temperatur))"));
                t.setMinTemp(rs.getFloat("(min(temperatur))"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
    
    
    
    public List<Electricity> getElRapport(String id){
         List<Electricity> list = new ArrayList<>();
         try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){

            PreparedStatement st = con.prepareStatement("SELECT electricityconsumptionlog.elforbrukning, electricityconsumptionlog.created " 
                    + "from electricityconsumptionlog " 
                    + "join serverhall on serverhall.id = electricityconsumptionlog.serverhallsId " 
                    + "join location on location.id = serverhall.serverhallsId " 
                    + "WHERE location.namn=?;");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Electricity e = new Electricity();
                e.setEl(rs.getFloat("electricityconsumptionlog.elforbrukning"));
                e.setDate(rs.getDate("electricityconsumptionlog.created"));
                list.add(e);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
    
    
     public Electricity getBilligastDyrastTimme(String id){
         int serverhallsId = 0;
         Electricity el = new Electricity();
         try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){
            
            PreparedStatement st = con.prepareStatement("SELECT id "
                    + "from location "
                    + "WHERE location.namn=?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                serverhallsId = (rs.getInt("id"));
            }
            

            PreparedStatement st2 = con.prepareStatement("SELECT created "
                    + "from electricityconsumptionlog " 
                    + "WHERE elforbrukning = (SELECT max(elforbrukning)from electricityconsumptionlog WHERE serverhallsId=?);");
            
            st2.setInt(1, serverhallsId);
            ResultSet rs3 = st2.executeQuery();
            while(rs3.next()){
                el.setDyrastTid(rs3.getDate("created"));
            }
            

            PreparedStatement st3 = con.prepareStatement("SELECT created "
                    + "from electricityconsumptionlog " 
                    + "WHERE elforbrukning = (SELECT min(elforbrukning)from electricityconsumptionlog WHERE serverhallsId=?);");

            st3.setInt(1, serverhallsId);
            ResultSet rs4 = st3.executeQuery();
            while(rs4.next()){
                el.setBilligastTid(rs4.getDate("created"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
         return el;
    }
     
     
     public void setNewTemperatur(String id, Kylsystem k){
         int serverhallsId = 0;
         try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){
             
            PreparedStatement st = con.prepareStatement("SELECT id "
                    + "from location "
                    + "WHERE location.namn=?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                serverhallsId = (rs.getInt("id"));
            }
            PreparedStatement st2 = con.prepareStatement("UPDATE kylsystem SET temperatur = ?, teknikerId = ? WHERE serverhallsId=?;");
            
            st2.setInt(1, k.getTemp());
            st2.setInt(2, k.getTeknikerId());
            st2.setInt(3, serverhallsId);
            st2.executeUpdate();
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     
    public void addTekniker(Tekniker t){
         try(Connection con = DriverManager.getConnection(
           p.getProperty("connection"), p.getProperty("name"), p.getProperty("password"));){
             PreparedStatement st = con.prepareStatement("INSERT INTO kyltekniker (Namn) VALUES (?) ");
             st.setString(1, t.getName());
             st.executeUpdate();
             
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
