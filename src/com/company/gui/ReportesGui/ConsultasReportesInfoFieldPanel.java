/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.ReportesGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ConsultasReportesInfoFieldPanel extends JPanel{
    
    public ConsultasReportesInfoFieldPanel(String title, String field){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);
        if ((title == "Hora") || (title == "Activo")){
            setPreferredSize(new Dimension(75, 72));
            setMaximumSize(new Dimension(75, 72));
        }
        else{
            setPreferredSize(new Dimension(83, 72));
            setMaximumSize(new Dimension(83, 72));
        }
        
        JLabel titleLabel = new JLabel(title);
        JLabel fieldLabel = new JLabel(field);
        
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        fieldLabel.setHorizontalAlignment(JLabel.CENTER);
        
        Dimension size = new Dimension(Integer.MAX_VALUE, 12);
        titleLabel.setPreferredSize(size);
        fieldLabel.setPreferredSize(size);
        fieldLabel.setMaximumSize(new Dimension(78, 12));
        
        titleLabel.setFont(new Font("Roboto Light", 0, 12));
        fieldLabel.setFont(new Font("Roboto Medium", 0, 12));
        
        Color titleColor = new Color(153, 153, 153);
        Color fieldColor = new Color(51, 51, 51);
        titleLabel.setForeground(titleColor);
        fieldLabel.setForeground(fieldColor);
        
        add(titleLabel, BorderLayout.NORTH);
        add(fieldLabel, BorderLayout.CENTER);
        
    }
    
}
