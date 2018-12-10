/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Zeo
 */
public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    private String nombre;
    private String email;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método que realiza las siguietnes acciones:
     * 1.- Conecta con la base agenda_mvc.
     * 2.- Consulta todo los registros de la tabla contactos.
     * 3.- Obtiene el nombre y el email y los guarda en las variables globales
     * nombre y email.
     */
    public Connection conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "root", "");
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM contactos;");
            rs.next();
            id = rs.getString("id_contacto");
            nombre = rs.getString("nombre");
            email = rs.getString("email");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }
        
        return conexion;
    }
    
    
     
     
     
    public void guardarRegistro(String nombre, String email){
        try {
            Connection conexion = null;
            conexion = conectarDB();
            ps = conexion.prepareStatement("INSERT INTO contactos(nombre,email) VALUES (?,?)");
            ps.setString(1, email);
            ps.setString(2, nombre);
            int resultado  = ps.executeUpdate();
            tablaContacto();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Datos de Guardados");
            } else {
                JOptionPane.showMessageDialog(null, "Error 001-guardar");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void eliminarRegistro(){
          try {
            Connection conexion = null;
            conexion = conectarDB();
            ps = conexion.prepareStatement("DELETE FROM contactos WHERE id_contacto=?");
            ps.setString(1, rs.getString("id_contacto"));
            int resultado = ps.executeUpdate();
            tablaContacto();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Contacto Eliminado");
                nombre = rs.getString("nombre");
                email = rs.getString("email");
            } else {
                JOptionPane.showMessageDialog(null, "Error 001-guardar");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarRegistro(String nombre, String email){
         try {
            Connection conexion = null;
            conexion = conectarDB();
            ps = conexion.prepareStatement("UPDATE contactos SET nombre=?,email=? WHERE id_contacto=?");
            ps.setString(1, email);
            ps.setString(2, nombre);
            ps.setString(3, id);
            int resultado  = ps.executeUpdate();
            tablaContacto();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Datos de Actualizados");
            } else {
                JOptionPane.showMessageDialog(null, "Error 001-guardar");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void tablaContacto(){
        try {
            conexion = conectarDB();
            ps = conexion.prepareStatement("SELECT * FROM contactos");
            rs = ps.executeQuery();
            rs.next();
            id = rs.getString("id_contacto");
            nombre = rs.getString("nombre");
            email = rs.getString("email");
        } catch (SQLException ex) {
            Logger.getLogger(ModelAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

   
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro(){
        System.out.print("Programa accion moverPrimerRegistro");
       try{
            if(rs.isFirst() == false){
                rs.first();
                nombre = rs.getString("nombre");
                email = rs.getString("email");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error_S"+ e.getMessage());
        }
    }
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al siguiente registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverSiguienteRegistro(){
        System.out.print("Programa accion moverSiguienteRegistro");
        try{
            if(rs.isLast() == false){
                rs.next();
                nombre = rs.getString("nombre");
                email = rs.getString("email");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error_S"+ e.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al anterior registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverAnteriorRegistro(){
        System.out.print("Programa accion moverAnteriorRegistro");
         try{
            if(rs.isFirst() == false){
                rs.previous();
                nombre = rs.getString("nombre");
                email = rs.getString("email");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error_S"+ e.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro(){
        System.out.print("Programa accion moverUltimoRegistro");
         try{
            if(rs.isLast()== false){
                rs.last();
                nombre = rs.getString("nombre");
                email = rs.getString("email");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error_S"+ e.getMessage());
        }
    }
}
