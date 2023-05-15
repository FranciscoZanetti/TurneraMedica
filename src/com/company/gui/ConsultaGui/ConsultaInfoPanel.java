/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.ConsultaGui;

import com.company.entidades.Consulta;
import com.company.gui.Interfaces.PanelEventListener;
import java.awt.Color;
import java.awt.Cursor;
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
public class ConsultaInfoPanel extends JPanel {
    
    private Consulta consulta;
    
    private PanelEventListener listener;
    
    public ConsultaInfoPanel(Consulta consulta, String user){
        
        this.consulta = consulta;
        
        ConsultaFieldPanel nombreApellido;
        if (user.equals("paciente")){
            String nombreCompleto = consulta.getMedico().getNombre() + " " + consulta.getMedico().getApellido();
            nombreApellido = new ConsultaFieldPanel("Médico", nombreCompleto);
        }
        else{
            String nombreCompleto = consulta.getPaciente().getNombre() + " " + consulta.getPaciente().getApellido();
            nombreApellido = new ConsultaFieldPanel("Paciente", nombreCompleto);
        }
        ConsultaFieldPanel consultorio = new ConsultaFieldPanel("Consultorio", consulta.getConsultorio());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM. yyyy", new Locale("es", "ES"));
        String formattedDate = consulta.getFecha().format(formatter);
        ConsultaFieldPanel fecha = new ConsultaFieldPanel("Fecha", formattedDate);
        
        ConsultaFieldPanel hora = new ConsultaFieldPanel("Hora", consulta.getHora().toString());
        ConsultaFieldPanel obraSocial = new ConsultaFieldPanel("Obra Social", consulta.getMedico().getObrasocial());
        ConsultaFieldPanel precio = new ConsultaFieldPanel("Precio", String.valueOf((Integer) consulta.getPrecio()));
        
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(866, 72));
        add(nombreApellido);
        add(consultorio);
        add(fecha);
        add(hora);
        add(obraSocial);
        add(precio);
//        addMouseListener(new MouseAdapter(){
//            @Override
//            public void mouseClicked(MouseEvent e){
//                Object source = e.getSource();
//                if (source instanceof ConsultaInfoPanel){
//                    Consulta consulta1 = ((ConsultaInfoPanel) source).getConsulta();
//                    System.out.println(".mouseClicked()");
////                    if (listener != null){
////                        System.out.println(".mouseClickedListener()");
////                        listener.onConsultaEvent(consulta1);
////                    }
//                    generateEvent(consulta1);
//                }
//            }
//        });
        
    }
    
    public void setPanelEventListener(PanelEventListener listener){
        this.listener = listener;
    }
    
    public void addMouseListener(String type){
        System.out.println("com.company.gui.ConsultaGui.ConsultaInfoPanel.addMouseClicked()");
        addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println(".mouseClicked1()");
                Object source = e.getSource();
                if (source instanceof ConsultaInfoPanel){
                    Consulta consulta1 = ((ConsultaInfoPanel) source).getConsulta();
                    System.out.println(".mouseClicked2()");
                    if (listener != null){
                        System.out.println(".mouseClickedListener()");
                        if (type == "reservar"){
                            listener.onReservarTurnoEvent(consulta1);
                        }
                        if (type == "proximos"){
                            listener.onProximosTurnosEvent(consulta1);
                        }
                    }
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e){
                if (listener != null){
                    System.out.println(".mouseClickedListener()");
                    listener.mouseEntered();
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                if (listener != null){
                    System.out.println(".mouseClickedListener()");
                    listener.mouseExited();
                }
            }
        });
    }
    
    public Consulta getConsulta(){
        return consulta;
    }
    
    
}
