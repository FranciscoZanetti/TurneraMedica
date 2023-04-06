/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.service;

import com.company.dao.DAOException;
import com.company.dao.DAOConsultorio;
import com.company.entidades.Consultorio;
import com.company.validaciones.ValidarLocalDate;
import com.company.validaciones.ValidarNumeroPositivo;
import com.company.validaciones.ValidarSoloLetras;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ServiceConsultorio {
    private DAOConsultorio daoConsultorio;
    
    public ServiceConsultorio(){
        daoConsultorio = new DAOConsultorio();
    }
    
    public void create(Consultorio consultorio) throws ServiceException{
        try{
            daoConsultorio.create(consultorio);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void update(Consultorio consultorio, String primaryKey) throws ServiceException{
        try{
            daoConsultorio.update(consultorio, primaryKey);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
//            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void delete(String primaryKey) throws ServiceException{
        try{
            daoConsultorio.delete(primaryKey);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void deactivate(String primaryKey) throws ServiceException{
        try{
            daoConsultorio.deactivate(primaryKey);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
}
