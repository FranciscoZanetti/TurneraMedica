/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.Interfaces;

import com.company.entidades.Consulta;
import com.company.entidades.Medico;

/**
 *
 * @author User
 */
public interface PanelEventListener {
    void onReservarTurnoEvent(Consulta consulta);
    void onProximosTurnosEvent(Consulta consulta);
    void onAdministrarMedicosEvent(Medico medico);
    void mouseEntered();
    void mouseExited();
}
