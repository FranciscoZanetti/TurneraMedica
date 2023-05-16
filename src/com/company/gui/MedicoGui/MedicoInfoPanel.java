/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.MedicoGui;

import com.company.entidades.Medico;
import com.company.gui.ConsultaGui.ConsultaInfoPanel;
import com.company.gui.ConsultaGui.InfoFieldPanel;
import com.company.gui.Interfaces.PanelEventListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class MedicoInfoPanel extends JPanel{
    private Medico medico;
    
    private PanelEventListener listener;
    
    public MedicoInfoPanel(Medico medico, String user){
        
        this.medico = medico;
        
        InfoFieldPanel dni = new InfoFieldPanel("DNI", String.valueOf(medico.getDNI()));
        
        InfoFieldPanel nombre = new InfoFieldPanel("Nombre", medico.getNombre());
        
        InfoFieldPanel apellido = new InfoFieldPanel("Apellido", medico.getApellido());
        
        InfoFieldPanel honorarios = new InfoFieldPanel("Honorarios", String.valueOf(medico.getHonorarios()));
        
        InfoFieldPanel obraSocial = new InfoFieldPanel("Obra Social", medico.getObrasocial());
        
        
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(866, 72));
        add(dni);
        add(nombre);
        add(apellido);
        add(honorarios);
        add(obraSocial);
        
        JPanel editarPanel = new JPanel();
        
        editarPanel.setLayout(new BorderLayout());
        editarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        editarPanel.setBackground(Color.WHITE);
        editarPanel.setPreferredSize(new Dimension(100, 72));
        editarPanel.setMinimumSize(new Dimension(100, 72));
        
        JLabel titleLabel = new JLabel("Editar");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        Dimension size = new Dimension(Integer.MAX_VALUE, 14);
        titleLabel.setPreferredSize(size);
        titleLabel.setFont(new Font("Roboto Medium", 0, 14));
        Color titleColor = new Color(0, 204, 153);
        titleLabel.setForeground(titleColor);
        
        editarPanel.add(titleLabel, BorderLayout.CENTER);
        
        
        
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
                if (source instanceof MedicoInfoPanel){
                    Medico medico1 = ((MedicoInfoPanel) source).getMedico();
                    System.out.println(".mouseClicked2()");
                    if (listener != null){
                        System.out.println(".mouseClickedListener()");
                        listener.onAdministrarMedicosEvent(medico1);
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
    
    public Medico getMedico(){
        return medico;
    }
    
    
}
