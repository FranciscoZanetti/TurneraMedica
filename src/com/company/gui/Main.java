package com.company.gui;

import com.company.entidades.ConsultorioMedico;
import com.company.service.ServiceException;
import com.company.service.ServicePaciente;
import com.company.entidades.Paciente;
import com.company.entidades.Medico;
import com.company.entidades.Consulta;
import com.company.gui.ConsultaGui.ConsultaInfoPanel;
import com.company.gui.PanelManager;
import com.company.service.ServiceMedico;
import com.company.service.ServiceConsultorioMedico;
import com.company.service.ServiceConsulta;
import com.company.util.Fecha;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main {
    public static void main(String[] args) throws  ServiceException{

        ServiceMedico serviceMedico = new ServiceMedico();
        Medico medico = serviceMedico.search(2516162);
        
        ServiceConsultorioMedico serviceConsultorioMedico = new ServiceConsultorioMedico();
        ArrayList<ConsultorioMedico> consultoriosMedicos = serviceConsultorioMedico.searchAll(2516162);
        
        ServiceConsulta serviceConsulta = new ServiceConsulta();
        ArrayList<Consulta> consultas = serviceConsulta.serchAllByMedico(2516162, 1);
        ServicePaciente servicePaciente = new ServicePaciente();
        Paciente paciente = servicePaciente.search(1515);
        
        ArrayList<Consulta> consultasNew = serviceConsulta.generateArrayConsultas(paciente, consultas, consultoriosMedicos);
        
        System.out.println("\nTurnos disponibles\n");
        for (Consulta consultaNew : consultasNew){
            System.out.println(consultaNew.toString()+"\n");
        }
        
        Consulta consultaPrueba = consultasNew.get(0);
        
        JFrame frame = new JFrame();
        
        JPanel infoPanel = new JPanel();
        JLabel jLabel1 = new JLabel("Medico");
        JLabel jLabel2 = new JLabel("Consultorio");
        JLabel jLabel3 = new JLabel("Fecha");
        JLabel jLabel4 = new JLabel("Hora");
        JLabel jLabel5 = new JLabel("Obra Social");
        JLabel jLabel6 = new JLabel("Precio");
        JLabel jLabelfield1 = new JLabel(consultaPrueba.getMedico().getNombre() + " " + consultaPrueba.getMedico().getApellido());
        JLabel jLabelfield2 = new JLabel(consultaPrueba.getConsultorio());
        JLabel jLabelfield3 = new JLabel(consultaPrueba.getFecha().toString());
        JLabel jLabelfield4 = new JLabel(consultaPrueba.getHora().toString());
        JLabel jLabelfield5 = new JLabel(consultaPrueba.getMedico().getObrasocial());
        JLabel jLabelfield6 = new JLabel(String.valueOf(consultaPrueba.getPrecio()));
        
        JPanel labelContainer1 = new JPanel();
        JPanel labelContainer2 = new JPanel();
        JPanel labelContainer3 = new JPanel();
        JPanel labelContainer4 = new JPanel();
        JPanel labelContainer5 = new JPanel();
        JPanel labelContainer6 = new JPanel();
        
        
        // labelContainer1
        
        labelContainer1.setLayout(new BorderLayout());
        labelContainer1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelContainer1.setBackground(Color.WHITE);
        labelContainer1.setPreferredSize(new Dimension(120, 72));
        
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        jLabelfield1.setHorizontalAlignment(JLabel.CENTER);
        
        Dimension size1 = new Dimension(Integer.MAX_VALUE, 14);
        jLabel1.setPreferredSize(size1);
        jLabelfield1.setPreferredSize(size1);
        
        Color titleColor = new Color(153, 153, 153);
        Color fieldColor = new Color(51, 51, 51);
        jLabel1.setForeground(titleColor);
        jLabelfield1.setForeground(fieldColor);
        
        labelContainer1.add(jLabel1, BorderLayout.NORTH);
        labelContainer1.add(jLabelfield1, BorderLayout.CENTER);
        
        
        //labelContainer2
        
        labelContainer2.setLayout(new BorderLayout());
        labelContainer2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelContainer2.setBackground(Color.WHITE);
        labelContainer2.setPreferredSize(new Dimension(120, 72));
        
        jLabel2.setHorizontalAlignment(JLabel.CENTER);
        jLabelfield2.setHorizontalAlignment(JLabel.CENTER);
        
        Dimension size2 = new Dimension(Integer.MAX_VALUE, 14);
        jLabel2.setPreferredSize(size2);
        jLabelfield2.setPreferredSize(size2);
        
        jLabel2.setForeground(titleColor);
        jLabelfield2.setForeground(fieldColor);
        
        labelContainer2.add(jLabel2, BorderLayout.NORTH);
        labelContainer2.add(jLabelfield2, BorderLayout.CENTER);
        
        
        //labelContainer3
        
        labelContainer3.setLayout(new BorderLayout());
        labelContainer3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelContainer3.setBackground(Color.WHITE);
        labelContainer3.setPreferredSize(new Dimension(120, 72));
        
        jLabel3.setHorizontalAlignment(JLabel.CENTER);
        jLabelfield3.setHorizontalAlignment(JLabel.CENTER);
        
        Dimension size3 = new Dimension(Integer.MAX_VALUE, 14);
        jLabel3.setPreferredSize(size3);
        jLabelfield3.setPreferredSize(size3);
        
        jLabel3.setForeground(titleColor);
        jLabelfield3.setForeground(fieldColor);
        
        labelContainer3.add(jLabel3, BorderLayout.NORTH);
        labelContainer3.add(jLabelfield3, BorderLayout.CENTER);
        
        
        //labelContainer4
        
        labelContainer4.setLayout(new BorderLayout());
        labelContainer4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelContainer4.setBackground(Color.WHITE);
        labelContainer4.setPreferredSize(new Dimension(120, 72));
        
        jLabel4.setHorizontalAlignment(JLabel.CENTER);
        jLabelfield4.setHorizontalAlignment(JLabel.CENTER);
        
        Dimension size4 = new Dimension(Integer.MAX_VALUE, 14);
        jLabel3.setPreferredSize(size4);
        jLabelfield3.setPreferredSize(size4);
        
        jLabel4.setForeground(titleColor);
        jLabelfield4.setForeground(fieldColor);
        
        labelContainer4.add(jLabel4, BorderLayout.NORTH);
        labelContainer4.add(jLabelfield4, BorderLayout.CENTER);
        
        
        //labelContainer5
        
        labelContainer5.setLayout(new BorderLayout());
        labelContainer5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelContainer5.setBackground(Color.WHITE);
        labelContainer5.setPreferredSize(new Dimension(120, 72));
        
        jLabel5.setHorizontalAlignment(JLabel.CENTER);
        jLabelfield5.setHorizontalAlignment(JLabel.CENTER);
        
        Dimension size5 = new Dimension(Integer.MAX_VALUE, 14);
        jLabel3.setPreferredSize(size5);
        jLabelfield3.setPreferredSize(size5);
        
        jLabel5.setForeground(titleColor);
        jLabelfield5.setForeground(fieldColor);
        
        labelContainer5.add(jLabel5, BorderLayout.NORTH);
        labelContainer5.add(jLabelfield5, BorderLayout.CENTER);
        
        
        //labelContainer6
        
        labelContainer6.setLayout(new BorderLayout());
        labelContainer6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelContainer6.setBackground(Color.WHITE);
        labelContainer6.setPreferredSize(new Dimension(120, 72));
        
        jLabel6.setHorizontalAlignment(JLabel.CENTER);
        jLabelfield6.setHorizontalAlignment(JLabel.CENTER);
        
        Dimension size6 = new Dimension(Integer.MAX_VALUE, 14);
        jLabel6.setPreferredSize(size6);
        jLabelfield6.setPreferredSize(size6);
        
        jLabel6.setForeground(titleColor);
        jLabelfield6.setForeground(fieldColor);
        
        labelContainer6.add(jLabel6, BorderLayout.NORTH);
        labelContainer6.add(jLabelfield6, BorderLayout.CENTER);
        
        
        //infoPanel
        infoPanel.setPreferredSize(new Dimension(860, 72));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPanel.add(labelContainer1);
        infoPanel.add(labelContainer2);
        infoPanel.add(labelContainer3);
        infoPanel.add(labelContainer4);
        infoPanel.add(labelContainer5);
        infoPanel.add(labelContainer6);
        
        
        frame.getContentPane().add(infoPanel);
        frame.pack();
        frame.setVisible(true);
        
        
        
        
        
        
//        JFrame frame = new JFrame();
//        JPanel mainPanel = new JPanel();
//
//        // Establece un BoxLayout con dirección Y_AXIS en el panel principal
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//
//        // Agrega los paneles de información
//        for (Consulta consulta : consultasNew) {
//            ConsultaInfoPanel panel = new ConsultaInfoPanel(consulta);
//            mainPanel.add(Box.createVerticalStrut(5));
//            mainPanel.add(panel);
//        }
//
//        // Establece el tamaño preferido del panel principal
//        mainPanel.setPreferredSize(new Dimension(866, 2000));
//
//        // Crea un JScrollPane y agrega el panel principal dentro de él
//        JScrollPane scrollPane = new JScrollPane(mainPanel);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setPreferredSize(new Dimension(866, 298));
//
//        frame.getContentPane().add(scrollPane);
//        frame.pack();
//        frame.setVisible(true);
//        
//    }


    

//    JFrame frame = new JFrame();
//        JPanel mainPanel = new JPanel();
//
//        // Establece un BoxLayout con dirección Y_AXIS en el panel principal
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//
//        // Agrega los paneles de información
//        for (int i = 1; i <= 10; i++) {
//            JPanel panel = new JPanel();
//            panel.add(new JLabel("Panel " + i));
//            mainPanel.add(Box.createVerticalStrut(10)); // Agrega separación
//            mainPanel.add(panel);
//        }
//
//        // Establece el tamaño preferido del panel principal
//        mainPanel.setPreferredSize(new Dimension(300, 800));
//
//        // Crea un JScrollPane y agrega el panel principal dentro de él
//        JScrollPane scrollPane = new JScrollPane(mainPanel);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setPreferredSize(new Dimension(300, 300));
//
//        frame.getContentPane().add(scrollPane);
//        frame.pack();
//        frame.setVisible(true);
    }
    
}