package com.company.service;

import com.company.dao.DAOException;
import com.company.dao.DAOPaciente;
import com.company.entidades.Paciente;
import com.company.validaciones.ValidarLocalDate;
import com.company.validaciones.ValidarNumeroPositivo;
import com.company.validaciones.ValidarSoloLetras;
import com.company.validaciones.ValidarPersona;

import javax.swing.*;
import java.util.ArrayList;

public class ServicePaciente {
    private DAOPaciente daoPaciente;

    public ServicePaciente(){
        daoPaciente = new DAOPaciente();
    }

    public void create(Paciente paciente) throws ServiceException{
        try{
            ValidarPersona validarPersona = new ValidarPersona(paciente);
            daoPaciente.create(paciente);
        }catch (DAOException | ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
    }

    public void update(Paciente paciente, int primaryKey) throws ServiceException{
        try{
            ValidarPersona validarPersona = new ValidarPersona(paciente);
            daoPaciente.update(paciente, primaryKey);
        }catch (DAOException | ServiceException exception){
            throw new ServiceException(exception.getMessage());
//            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete(int primaryKey) throws ServiceException{
        try{
            try{
                ValidarNumeroPositivo validarNumeroPositivo = new ValidarNumeroPositivo(primaryKey);
            }catch (ServiceException exception){
                throw new ServiceException(exception.getMessage());
            }
            daoPaciente.delete(primaryKey);
        }catch (DAOException | ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
    }

    public Paciente search(int primaryKey) throws ServiceException{
        Paciente paciente = null;
        try{
            try{
                ValidarNumeroPositivo validarNumeroPositivo = new ValidarNumeroPositivo(primaryKey);
            }catch (ServiceException exception){
                throw new ServiceException(exception.getMessage());
            }
            try{
                paciente = daoPaciente.search(primaryKey);
            }catch (DAOException exception){
                throw new ServiceException("No se pudieron encontrar resultados");
            }
        }catch (ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }finally {
            return paciente;
        }
    }

    public ArrayList<Paciente> searchAll() throws ServiceException{
        ArrayList<Paciente> pacientes;
        try {
            pacientes = daoPaciente.searchAll();
            return pacientes;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
//    public Paciente login(int primaryKey, String password) throws ServiceException{
//        Paciente paciente = null;
//        try{
//            ValidarNumeroPositivo validarNumeroPositivo = new ValidarNumeroPositivo(primaryKey);
//        }catch (ServiceException exception){
//            throw new ServiceException(exception.getMessage());
//        }
//        try{
//            paciente = daoPaciente.login(primaryKey, password);
//        }catch (DAOException exception){
//            throw new ServiceException(exception.getMessage());
//        }
//        return paciente;
//    }

}
