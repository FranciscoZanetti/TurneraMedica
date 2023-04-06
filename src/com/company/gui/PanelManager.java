package com.company.gui;

import com.company.gui.PacienteGui.FormularioPaciente;
import com.company.service.ServiceException;
import com.company.service.ServicePaciente;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    private ServicePaciente servicePaciente;

    JFrame window;

    FormularioPaciente formularioPaciente;


    public PanelManager() throws ServiceException{
        window = new JFrame("Turnera Médica");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(window.MAXIMIZED_BOTH);

        formularioPaciente = new FormularioPaciente(this);
        mostrarFormularioPaciente(formularioPaciente, BorderLayout.NORTH);

        window.setVisible(true);
        window.setResizable(false);
        window.pack();
    }

    void mostrarFormularioPaciente(JPanel panel, String ubicacion){
        window.getContentPane().removeAll();
        window.getContentPane().add(ubicacion, panel);
        window.getContentPane().validate();
        window.getContentPane().repaint();
        window.pack();
    }

}
