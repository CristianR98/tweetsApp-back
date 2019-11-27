package database;

import java.sql.SQLException;
import java.util.ArrayList;
import models.Busqueda;
import models.Respuesta;

public class DB extends MySql {
    
    public static Respuesta postBusqueda(String termino) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
            
            stm = con.prepareStatement("SELECT * FROM `history` WHERE `history-term` = ?");
            stm.setString(1, termino);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                
                stm = con.prepareStatement("DELETE FROM `history` WHERE `history-term` = ?");
                stm.setString(1, termino);
                
                stm.executeUpdate();
                
            }
            
            stm = con.prepareStatement("INSERT INTO `history` (`history-term`) VALUES (?)");
            stm.setString(1, termino);
            
            stm.executeUpdate();
            closeConnection();
            
        }catch (SQLException e) {
            respuesta = new Respuesta(500, false, e.getMessage());
        }
        
        return respuesta;
        
    }
    
    public static Respuesta<ArrayList<Busqueda>> getBusquedas() {
        
        Respuesta<ArrayList<Busqueda>> respuesta = getConnection();
        ArrayList<Busqueda> listaBusquedas = new ArrayList();
        
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
            
            stm = con.prepareStatement("SELECT * FROM `history` ORDER BY `history-id` DESC");
            
            rs = stm.executeQuery();
            
            while(rs.next()) {
                
                Busqueda busqueda = new Busqueda(rs.getString("history-term"));
                
                busqueda.setId(rs.getInt("history-id"));
                busqueda.setFecha(rs.getString("history-date"));
                
                listaBusquedas.add(busqueda);
                
            }
            
            respuesta.setContent(listaBusquedas);
            
            closeConnection();
            
        }catch (SQLException e) {
            respuesta = new Respuesta(500, false, e.getMessage());
        }
        
        return respuesta;
        
    } 
    
    public static Respuesta deleteBusqueda(int id) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
            
            stm = con.prepareStatement("DELETE FROM `history` WHERE `history-id` = ?");
            stm.setInt(1, id);
            
            stm.executeUpdate();
            
        }catch (SQLException e) {
            respuesta = new Respuesta(500, false, e.getMessage());
        }
        
        
        return respuesta;
        
    }
    
}
