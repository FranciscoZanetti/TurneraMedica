/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.ReportesGui;

import com.company.entidades.Consulta;
import javax.swing.JPanel;
import com.company.entidades.Medico;
import com.company.gui.ConsultaGui.ConsultaInfoPanel;
import com.company.gui.ConsultaGui.InfoFieldPanel;
import com.company.gui.Interfaces.PanelEventListener;
import com.company.service.ServiceConsulta;
import com.company.service.ServiceException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class RecaudacionesReportesInfoPanel extends JPanel{
    
    private Medico medico;
    
    public RecaudacionesReportesInfoPanel(Medico medico, JLabel jLabel){
        
        this.medico = medico;
        
        ServiceConsulta serviceConsulta = new ServiceConsulta();
        int acum = 0;
        try{
            acum = serviceConsulta.recaudacionByMedico(medico.getDNI());
        }catch(ServiceException exception){
            
        }finally{
            String dniString = String.valueOf(medico.getDNI());
            String recaudacionString = String.valueOf(acum);
            
            RecaudacionesReportesInfoFieldPanel dni = new RecaudacionesReportesInfoFieldPanel("DNI", dniString);
            RecaudacionesReportesInfoFieldPanel nombre = new RecaudacionesReportesInfoFieldPanel("Nombre", medico.getNombre());
            RecaudacionesReportesInfoFieldPanel apellido = new RecaudacionesReportesInfoFieldPanel("Apellido", medico.getApellido());
            RecaudacionesReportesInfoFieldPanel recaudacion = new RecaudacionesReportesInfoFieldPanel("Recaudación", recaudacionString);
            
            setBackground(Color.WHITE);
            setLayout(new FlowLayout(FlowLayout.LEFT));
            setPreferredSize(new Dimension(334, 72));
            
            add(dni);
            add(nombre);
            add(apellido);
            add(recaudacion);
            
            setTotal(jLabel, acum);
        }
        
    }
    
    
    public RecaudacionesReportesInfoPanel(Medico medico, LocalDate fecha, String aclaracion, JLabel jLabel){
        
        this.medico = medico;
        
        ServiceConsulta serviceConsulta = new ServiceConsulta();
        int acum = 0;
        try{
            if (aclaracion.equals("desde")){
                acum = serviceConsulta.recaudacionByMedicoBetween(medico.getDNI(), fecha, LocalDate.now());
            }
            else{
                acum = serviceConsulta.recaudacionByMedicoBetween(medico.getDNI(), LocalDate.of(2001, 1, 1), fecha);
            }
        }catch(ServiceException exception){
            
        }finally{
            String dniString = String.valueOf(medico.getDNI());
            String recaudacionString = String.valueOf(acum);
            
            RecaudacionesReportesInfoFieldPanel dni = new RecaudacionesReportesInfoFieldPanel("DNI", dniString);
            RecaudacionesReportesInfoFieldPanel nombre = new RecaudacionesReportesInfoFieldPanel("Nombre", medico.getNombre());
            RecaudacionesReportesInfoFieldPanel apellido = new RecaudacionesReportesInfoFieldPanel("Apellido", medico.getApellido());
            RecaudacionesReportesInfoFieldPanel recaudacion = new RecaudacionesReportesInfoFieldPanel("Recaudación", recaudacionString);
            
            setBackground(Color.WHITE);
            setLayout(new FlowLayout(FlowLayout.LEFT));
            setPreferredSize(new Dimension(334, 72));
            
            add(dni);
            add(nombre);
            add(apellido);
            add(recaudacion);
            
            setTotal(jLabel, acum);
        }
        
    }
    
    
    public RecaudacionesReportesInfoPanel(Medico medico, LocalDate desde, LocalDate hasta, JLabel jLabel){
        
        this.medico = medico;
        
        ServiceConsulta serviceConsulta = new ServiceConsulta();
        int acum = 0;
        try{
            acum = serviceConsulta.recaudacionByMedicoBetween(medico.getDNI(), desde, hasta);
        }catch(ServiceException exception){
            
        }finally{
            String dniString = String.valueOf(medico.getDNI());
            String recaudacionString = String.valueOf(acum);
            
            RecaudacionesReportesInfoFieldPanel dni = new RecaudacionesReportesInfoFieldPanel("DNI", dniString);
            RecaudacionesReportesInfoFieldPanel nombre = new RecaudacionesReportesInfoFieldPanel("Nombre", medico.getNombre());
            RecaudacionesReportesInfoFieldPanel apellido = new RecaudacionesReportesInfoFieldPanel("Apellido", medico.getApellido());
            RecaudacionesReportesInfoFieldPanel recaudacion = new RecaudacionesReportesInfoFieldPanel("Recaudación", recaudacionString);
            
            setBackground(Color.WHITE);
            setLayout(new FlowLayout(FlowLayout.LEFT));
            setPreferredSize(new Dimension(334, 72));
            
            add(dni);
            add(nombre);
            add(apellido);
            add(recaudacion);
            
            setTotal(jLabel, acum);
        }
        
    }
    
    
    private void setTotal(JLabel jLabel, int recaudacion){
        int total = Integer.parseInt(jLabel.getText());
        total += recaudacion;
        String totalString = String.valueOf(total);
        jLabel.setText(totalString);
    }

    public Medico getMedico() {
        return medico;
    }
    
    
}
