/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.service;

import com.company.dao.DAOException;
import com.company.dao.DAOMedico;
import com.company.entidades.Medico;
import com.company.validaciones.ValidarLocalDate;
import com.company.validaciones.ValidarNumeroPositivo;
import com.company.validaciones.ValidarPersona;
import com.company.validaciones.ValidarSoloLetras;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ServiceMedico {
    private DAOMedico daoMedico;
    
    public ServiceMedico(){
        System.out.println("Flag 6");
        daoMedico = new DAOMedico();
        System.out.println("Flag 7");
    }
    
    public void validarMedico(Medico medico) throws ServiceException{
        ValidarPersona validarPersona = new ValidarPersona(medico);
        try{
            ValidarNumeroPositivo validarNumeroPositivo = new ValidarNumeroPositivo(medico.getHonorarios());
        }catch (ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void create(Medico medico) throws ServiceException{
        try{
            validarMedico(medico);
            daoMedico.create(medico);
        }catch (DAOException | ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void update(Medico medico, int primaryKey) throws ServiceException{
        try{
            validarMedico(medico);
            daoMedico.update(medico, primaryKey);
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
            daoMedico.delete(primaryKey);
        }catch (DAOException | ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void deactivate(Medico medico, int primaryKey) throws ServiceException{
        try{
            validarMedico(medico);
            try{
                ValidarNumeroPositivo validarNumeroPositivo = new ValidarNumeroPositivo(primaryKey);
            }catch (ServiceException exception){
                throw new ServiceException(exception.getMessage());
            }
            daoMedico.deactivate(medico, primaryKey);
        }catch (DAOException | ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public Medico search(int primaryKey) throws ServiceException{
        Medico medico = null;
        try{
            try{
                ValidarNumeroPositivo validarNumeroPositivo = new ValidarNumeroPositivo(primaryKey);
            }catch (ServiceException exception){
                throw new ServiceException(exception.getMessage());
            }
            try{
                medico = daoMedico.search(primaryKey);
            }catch (DAOException exception){
                throw new ServiceException("No se pudieron encontrar resultados");
            }
        }catch (ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }finally {
            return medico;
        }
    }
    
    public ArrayList<Medico> searchAll(int activo) throws ServiceException{
        ArrayList<Medico> medicos = new ArrayList<>();
        try {
            medicos = daoMedico.searchAll(activo);
//            return medicos;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
        finally{
            return medicos;
        }
    }
    
    public ArrayList<Medico> searchAllByApellido(int activo, String apellido) throws ServiceException{
        ArrayList<Medico> medicos = new ArrayList<>();
        try {
            medicos = daoMedico.searchAllByApellido(activo, apellido);
//            return medicos;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
        finally{
            return medicos;
        }
    }
    
//    public Medico login(int primaryKey, String password) throws ServiceException{
//        Medico medico = null;
//        try{
//            ValidarNumeroPositivo validarNumeroPositivo = new ValidarNumeroPositivo(primaryKey);
//        }catch (ServiceException exception){
//            throw new ServiceException(exception.getMessage());
//        }
//        try{
//            medico = daoMedico.login(primaryKey, password);
//        }catch (DAOException exception){
//            throw new ServiceException(exception.getMessage());
//        }
//        return medico;
//    }
}
