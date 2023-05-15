/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.Interfaces;

import com.company.entidades.Consulta;

/**
 *
 * @author User
 */
public interface PanelEventListener {
    void onReservarTurnoEvent(Consulta consulta);
    void onProximosTurnosEvent(Consulta consulta);
    void mouseEntered();
    void mouseExited();
}
