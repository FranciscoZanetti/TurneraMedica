/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.ReportesGui;

import com.company.entidades.Consulta;
import com.company.gui.ConsultaGui.ConsultaInfoPanel;
import com.company.gui.ConsultaGui.InfoFieldPanel;
import com.company.gui.Interfaces.PanelEventListener;
import com.company.gui.MedicoGui.MedicoInfoPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ConsultasReportesInfoPanel extends JPanel{
    
    private Consulta consulta;
    
    public ConsultasReportesInfoPanel(Consulta consulta, JLabel jLabel){
        System.out.println("FLAG com.company.gui.ReportesGui.ConsultasReportesInfoPanel.<init>()");
        
        this.consulta = consulta;
        
        String dniField = String.valueOf(consulta.getMedico().getDNI());
        InfoFieldPanel dni = new InfoFieldPanel("DNI", dniField);
        
        String medicoField = consulta.getMedico().getNombre() + " " + consulta.getMedico().getApellido();
        InfoFieldPanel medico = new InfoFieldPanel("Médico", medicoField);
        
        String pacienteField = consulta.getPaciente().getNombre() + " " + consulta.getPaciente().getApellido();
        InfoFieldPanel paciente = new InfoFieldPanel("Paciente", pacienteField);
        
        InfoFieldPanel consultorio = new InfoFieldPanel("Consultorio", consulta.getConsultorio());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM. yyyy", new Locale("es", "ES"));
        String formattedDate = consulta.getFecha().format(formatter);
        InfoFieldPanel fecha = new InfoFieldPanel("Fecha", formattedDate);
        
        InfoFieldPanel hora = new InfoFieldPanel("Hora", consulta.getHora().toString());
        InfoFieldPanel obraSocial = new InfoFieldPanel("Obra Social", consulta.getMedico().getObrasocial());
        InfoFieldPanel precio = new InfoFieldPanel("Precio", String.valueOf((Integer) consulta.getPrecio()));
        
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(700, 72));
        add(dni);
        add(medico);
        add(paciente);
        add(consultorio);
        add(fecha);
        add(hora);
        add(obraSocial);
        add(precio);
        
        setTotal(jLabel, consulta.getPrecio());
        
    }
    
    private void setTotal(JLabel jLabel, int precio){
        int recaudacion = Integer.parseInt(jLabel.getText());
        recaudacion += precio;
        String recaudacString = String.valueOf(recaudacion);
        jLabel.setText(recaudacString);
    }
    
    public Consulta getConsulta(){
        return consulta;
    }
    
    
}
