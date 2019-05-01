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
    

    
    public List<Temperatur> getTempRapport(String id){
         List<Temperatur> list = new ArrayList<>();
         try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){

            PreparedStatement st = con.prepareStatement("SELECT temperatur, created "
                    + "from temperaturelog "
                    + "join serverhall on serverhall.id = temperaturelog.serverhallsId "
                    + "join location on location.id = serverhall.serverhallsId "
                    + "WHERE location.namn=?;");
            
            PreparedStatement st2 = con.prepareStatement("SELECT (avg(temperatur)), (max(temperatur)), (min(temperatur)) "
                    + "from temperaturelog join serverhall on serverhall.id = temperaturelog.serverhallsId "
                    + "join location on location.id = serverhall.serverhallsId "
                    + "WHERE location.namn=? ;");
            
            
            st2.setString(1, id);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            ResultSet rs2 = st2.executeQuery();
            
            
            while(rs.next()){
                Temperatur t = new Temperatur();
                t.setTemp(rs.getFloat("temperatur"));
                t.setDate(rs.getDate("created"));
                list.add(t);
                System.out.println(t.getDate()+" "+t.getId()+" " +t.getTemp());
            }
            while(rs2.next()){
                Temperatur t = new Temperatur();
                t.setAverageTemp(rs2.getFloat("(avg(temperatur))"));
                t.setMaxTemp(rs2.getFloat("(max(temperatur))"));
                t.setMinTemp(rs2.getFloat("(min(temperatur))"));
                list.add(t);
                System.out.println(t.getMaxTemp());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
    
    
      public List<Electricity> getElRapport(String id){
         int serverhallsId = 0;
         List<Electricity> list = new ArrayList<>();
         try(Connection con = DriverManager.getConnection(p.getProperty("connection"),
                p.getProperty("name"), p.getProperty("password"))){

            PreparedStatement st = con.prepareStatement("SELECT electricityconsumptionlog.elforbrukning, electricitycost.kostnad, electricityconsumptionlog.created, location.id " 
                    + "from electricityconsumptionlog " 
                    + "join serverhall on serverhall.id = electricityconsumptionlog.serverhallsId " 
                    + "join location on location.id = serverhall.serverhallsId " 
                    + "join electricitycost on serverhall.id = electricitycost.serverhallsId " 
                    + "WHERE location.namn=?;");
            
            PreparedStatement st2 = con.prepareStatement("SELECT (avg(elforbrukning)), (max(elforbrukning)), (min(elforbrukning)), electricitycost.kostnad "
                    + "from electricityconsumptionlog "
                    + "join serverhall on serverhall.id = electricityconsumptionlog.serverhallsId " 
                    + "join electricitycost on serverhall.id = electricitycost.serverhallsId " 
                    + "join location on location.id = serverhall.serverhallsId " 
                    + "WHERE location.namn=?;");
            
            PreparedStatement st3 = con.prepareStatement("SELECT created "
                    + "from electricityconsumptionlog " 
                    + "WHERE elforbrukning = (SELECT max(elforbrukning)from electricityconsumptionlog WHERE serverhallsId=?);");
            
            PreparedStatement st4 = con.prepareStatement("SELECT created "
                    + "from electricityconsumptionlog " 
                    + "WHERE elforbrukning = (SELECT min(elforbrukning)from electricityconsumptionlog WHERE serverhallsId=?);");
            
            st.setString(1, id);
            st2.setString(1, id);
            st3.setInt(1, serverhallsId);
            st4.setInt(1, serverhallsId);
            ResultSet rs = st.executeQuery();
            ResultSet rs2 = st2.executeQuery();
            ResultSet rs3 = st3.executeQuery();
            ResultSet rs4 = st4.executeQuery();
            
            while(rs.next()){
                Electricity e = new Electricity();
                e.setEl(rs.getFloat("electricityconsumptionlog.elforbrukning"));
                e.setElKostnad(rs.getFloat("electricitycost.kostnad"));
                e.setDate(rs.getDate("electricityconsumptionlog.created"));
                serverhallsId = rs.getInt("id");
                list.add(e);
            }

            while(rs2.next()){
                Electricity e = new Electricity();
                e.setAverageEl(rs2.getFloat("(avg(elforbrukning))"));
                e.setMaxEl(rs2.getFloat("(max(elforbrukning))"));
                e.setMinEl(rs2.getFloat("(min(elforbrukning))"));
                e.setElKostnad(rs.getFloat("electricitycost.kostnad"));
                list.add(e);
            }
            
            while(rs3.next()){
                Electricity e = new Electricity();
                e.setDyrastTid(rs3.getDate("created"));
                list.add(e);
            }
            
            while(rs4.next()){
                Electricity e = new Electricity();
                e.setBilligastTid(rs3.getDate("created"));
                list.add(e);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }
    
    
}
