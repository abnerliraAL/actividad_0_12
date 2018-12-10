/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import models.ModelAgenda;
import views.ViewAgenda;
import controllers.ControllerAgenda;

/**
 *
 * @author Zeo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModelAgenda modelAgenda = new ModelAgenda();
        ViewAgenda viewAgenda = new ViewAgenda();
        ControllerAgenda controllerAgenda = new ControllerAgenda(modelAgenda, viewAgenda);
    }
    
}
