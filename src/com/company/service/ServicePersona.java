/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.service;

import com.company.dao.DAOException;
import com.company.dao.DAOMedico;
import com.company.entidades.Medico;
import com.company.dao.DAOPaciente;
import com.company.entidades.Paciente;
import com.company.entidades.Persona;
import com.company.validaciones.ValidarLocalDate;
import com.company.validaciones.ValidarNumeroPositivo;
import com.company.validaciones.ValidarPersona;
import com.company.validaciones.ValidarSoloLetras;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ServicePersona {
    private DAOPaciente daoPaciente;
    private DAOMedico daoMedico;
    
    public ServicePersona(){
        System.out.println("Flag 3");
        daoPaciente = new DAOPaciente();
        System.out.println("Flag 4");
        daoMedico = new DAOMedico();
        System.out.println("Flag 5");
    }
    
    public Persona login(int primaryKey, String password) throws ServiceException{
        Persona persona = null;
        try{
            persona = daoPaciente.login(primaryKey, password);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }finally{
            if (persona == null){
                try{
                    persona = daoMedico.login(primaryKey, password);
                }catch (DAOException exception){
                    throw new ServiceException(exception.getMessage());
                }
            }
        }
        return persona;
    }
}
