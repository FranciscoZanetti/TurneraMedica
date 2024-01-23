/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.service;

import com.company.dao.DAOException;
import com.company.dao.DAOConsultorioMedico;
import com.company.dao.DAOMedico;
import com.company.entidades.ConsultorioMedico;
import com.company.validaciones.ValidarLocalDate;
import com.company.validaciones.ValidarNumeroPositivo;
import com.company.validaciones.ValidarSoloLetras;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author User
 */
public class ServiceConsultorioMedico {
    private DAOConsultorioMedico daoConsultorioMedico;
    private DAOMedico daoMedico;
    
    public ServiceConsultorioMedico(){
        daoConsultorioMedico = new DAOConsultorioMedico();
        daoMedico = new DAOMedico();
    }
    
    public void create(ConsultorioMedico consultorioMedico) throws ServiceException{
        try{
            daoConsultorioMedico.create(consultorioMedico);
            daoMedico.manageActivation(1, consultorioMedico.getMedico().getDNI());
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void update(ConsultorioMedico consultorioMedico) throws ServiceException{
        try{
            daoConsultorioMedico.update(consultorioMedico);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
//            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void delete(int primaryKey) throws ServiceException{
        try{
            daoConsultorioMedico.delete(primaryKey);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public void deleteByMedico(int dni) throws ServiceException{
        try{
            daoConsultorioMedico.deleteByMedico(dni);
        }catch (DAOException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
    
    public ConsultorioMedico search(int primaryKey) throws ServiceException{
        ConsultorioMedico consultorioMedico = null;
        try{
            try{
                ValidarNumeroPositivo validarNumeroPositivo = new ValidarNumeroPositivo(primaryKey);
            }catch (ServiceException exception){
                throw new ServiceException(exception.getMessage());
            }
            try{
                daoConsultorioMedico.search(primaryKey);
            }catch (DAOException exception){
                throw new ServiceException("No se pudieron encontrar resultados");
            }
        }catch (ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }finally {
            return consultorioMedico;
        }
    }
    
    public ArrayList<ConsultorioMedico> searchAll(int dni) throws ServiceException{
        ArrayList<ConsultorioMedico> consultoriosMedicos;
        try {
            consultoriosMedicos = daoConsultorioMedico.searchAll(dni);
            return consultoriosMedicos;
        }catch (DAOException exception){
            throw new ServiceException("No se pudieron encontrar resultados");
        }
    }
    
    
    public ArrayList<String> generateConsultoriosArray(ArrayList<ConsultorioMedico> consultoriosMedicos){
        for (ConsultorioMedico consultorioMedico: consultoriosMedicos){
            System.out.println("\n" + consultorioMedico.toString());
        }
        ArrayList<String> consultorios = new ArrayList<>();
        for (ConsultorioMedico consultorioMedico: consultoriosMedicos){
            consultorios.add(consultorioMedico.getConsultorio());
        }
        HashSet<String> consultoriosHash = new HashSet<>(consultorios);
        consultorios = new ArrayList<>(consultoriosHash);
        return consultorios;
    }
}
