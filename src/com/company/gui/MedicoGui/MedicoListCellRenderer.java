/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.MedicoGui;

import com.company.entidades.Medico;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author User
 */
public class MedicoListCellRenderer extends DefaultListCellRenderer{
    private Font font = new Font("Roboto Light", Font.PLAIN, 14);
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        System.out.println("Flag 69");
        String nombre = null;
        String apellido = null;
        String obraSocial = null;
        if (value instanceof Medico){
            nombre = ((Medico)value).getNombre();
            apellido = ((Medico)value).getApellido();
            obraSocial = ((Medico)value).getObrasocial();
            value = nombre + " " + apellido + " - " + obraSocial;
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setFont(font);
        setBackground(Color.WHITE);
        setBorder(null);
//        setBorder(BorderFactory.createEmptyBorder());
        return this;
    }
    
}
