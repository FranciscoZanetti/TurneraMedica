/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.ConsultaGui;

import com.company.entidades.Consulta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ConsultaInfoPanel extends JPanel {
    
    private Consulta consulta;
    
    public ConsultaInfoPanel(Consulta consulta){
        
        this.consulta = consulta;
        
        String nombreCompleto = consulta.getMedico().getNombre() + " " + consulta.getMedico().getApellido();
        ConsultaFieldPanel nombreApellido = new ConsultaFieldPanel("Médico", nombreCompleto);
        ConsultaFieldPanel consultorio = new ConsultaFieldPanel("Consultorio", consulta.getConsultorio());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM. yyyy", new Locale("es", "ES"));
        String formattedDate = consulta.getFecha().format(formatter);
        ConsultaFieldPanel fecha = new ConsultaFieldPanel("Fecha", formattedDate);
        
        ConsultaFieldPanel hora = new ConsultaFieldPanel("Hora", consulta.getHora().toString());
        ConsultaFieldPanel obraSocial = new ConsultaFieldPanel("Obra Social", consulta.getMedico().getObrasocial());
        ConsultaFieldPanel precio = new ConsultaFieldPanel("Precio", String.valueOf(consulta.getPrecio()));
        
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(866, 72));
        add(nombreApellido);
        add(consultorio);
        add(fecha);
        add(hora);
        add(obraSocial);
        add(precio);
        
    }
    
    public Consulta getConsulta(){
        return consulta;
    }
    
    
}
