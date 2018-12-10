/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author usuario1
 */
public class ControllerAgenda {

    ModelAgenda modelAgenda;
    ViewAgenda viewAgenda;
    private String Desicion = "";

    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewAgenda.jbtn_primero_registro) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior_registro) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente_registro) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo_registro) {
                jbtn_ultimo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jb_editar){
                jb_editar_actionPerformend();
            } else if (e.getSource() == viewAgenda.jb_eliminar){
                jb_eliminar_actionPerformend();
            } else if (e.getSource() == viewAgenda.jb_nuevo){
                jb_nuevo_actionPerformend();
            } else if (e.getSource() == viewAgenda.jb_guardar){
                jb_guardar_actionPerformend();
            } else if (e.getSource() == viewAgenda.jb_cancelar){
                jb_cancelar_actionPerformend();
            }

        }

    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        initComponents();
        setActionListener();
        initDB();
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    public void initDB(){
        modelAgenda.conectarDB();
        enviarVista();
    }
    /**
     * Metodo para inicializar la ViewAgenda
     */
    public void initComponents() {
        viewAgenda.setLocationRelativeTo(null);
        viewAgenda.setTitle("Agenda MVC");
        viewAgenda.setVisible(true);
        viewAgenda.jtf_email.setEnabled(false);
        viewAgenda.jtf_nombre.setEnabled(false);
    }

    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    public void setActionListener() {
        viewAgenda.jbtn_primero_registro.addActionListener(actionListener);
        viewAgenda.jbtn_anterior_registro.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente_registro.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo_registro.addActionListener(actionListener);
        viewAgenda.jb_nuevo.addActionListener(actionListener);
        viewAgenda.jb_editar.addActionListener(actionListener);
        viewAgenda.jb_guardar.addActionListener(actionListener);
        viewAgenda.jb_cancelar.addActionListener(actionListener);
        viewAgenda.jb_eliminar.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        System.out.println("Action del boton jbtn_primero");
        //invocar al metodo moverPrimerRegistro
        modelAgenda.moverPrimerRegistro();
        //mostrar nombre en la vista
        //mostar email en la vista
        enviarVista();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos
     */
    private void jbtn_anterior_actionPerformed() {
        System.out.println("Action del boton jbtn_anterior");
        //invocar al metodo moverAnteriorRegistro
        modelAgenda.moverAnteriorRegistro();
        //mostrar nombre en la vista
        //mostar email en la vista
        enviarVista();
    }

    /**
     * Método para ver el último registro de la tabla contactos
     */
    private void jbtn_ultimo_actionPerformed() {
        System.out.println("Action del boton jbtn_ultimo");
        //invocar al metodo moverUltimoRegistro
        modelAgenda.moverUltimoRegistro();
        //mostrar nombre en la vista
        //mostar email en la vista
        enviarVista(); 
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos
     */
    private void jbtn_siguiente_actionPerformed() {
        System.out.println("Action del boton jbtn_siguiente");
        //invocar al metodo moverSiguienteRegistro
        modelAgenda.moverSiguienteRegistro();
        //mostrar nombre en la vista
        //mostar email en la vista
        enviarVista();
    }
    
    
    private void jb_guardar_actionPerformend(){
        System.err.println("accion del boton guardar");
        if(Desicion == "nuevo"){
            modelAgenda.guardarRegistro(viewAgenda.jtf_email.getText(), viewAgenda.jtf_nombre.getText());
        }else if (Desicion == "editar"){
            modelAgenda.editarRegistro(viewAgenda.jtf_email.getText(), viewAgenda.jtf_nombre.getText());
        }
        viewAgenda.jtf_email.setEnabled(false);
        viewAgenda.jtf_nombre.setEnabled(false);
        viewAgenda.jb_eliminar.setEnabled(true);
        viewAgenda.jb_nuevo.setEnabled(true);
        viewAgenda.jb_editar.setEnabled(true);
        viewAgenda.jb_guardar.setEnabled(false);
        
    }
    
    private void jb_eliminar_actionPerformend(){
        System.err.println("accion del boton eliminar");
        modelAgenda.eliminarRegistro();
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
    }
    
    private void jb_nuevo_actionPerformend(){
        System.err.println("accion del boton nuevo");
        Desicion = "nuevo";
        viewAgenda.jtf_email.setText("");
        viewAgenda.jtf_nombre.setText("");
        viewAgenda.jtf_email.setEnabled(true);
        viewAgenda.jtf_nombre.setEnabled(true);
        viewAgenda.jb_eliminar.setEnabled(false);
        viewAgenda.jb_nuevo.setEnabled(false);
        viewAgenda.jb_editar.setEnabled(false);
        viewAgenda.jb_guardar.setEnabled(true);
    }
    
    private void jb_editar_actionPerformend(){
        System.err.println("accion del boton editar");
        Desicion = "editar";
        viewAgenda.jtf_email.setEnabled(true);
        viewAgenda.jtf_nombre.setEnabled(true);
        viewAgenda.jb_eliminar.setEnabled(false);
        viewAgenda.jb_nuevo.setEnabled(false);
        viewAgenda.jb_editar.setEnabled(false);
        viewAgenda.jb_guardar.setEnabled(true);
    }
    
    private void jb_cancelar_actionPerformend(){
        viewAgenda.jtf_email.setEnabled(false);
        viewAgenda.jtf_nombre.setEnabled(false);
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jb_eliminar.setEnabled(true);
        viewAgenda.jb_nuevo.setEnabled(true);
        viewAgenda.jb_editar.setEnabled(true);
        viewAgenda.jb_guardar.setEnabled(false);
    }
    
    
    
    
    private void enviarVista(){
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
    }
}
