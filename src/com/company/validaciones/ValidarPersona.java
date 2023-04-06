/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.validaciones;

import com.company.entidades.Persona;
import com.company.service.ServiceException;
import com.company.validaciones.ValidarLocalDate;
import com.company.validaciones.ValidarNumeroPositivo;
import com.company.validaciones.ValidarSoloLetras;

/**
 *
 * @author User
 */
public class ValidarPersona {
    public ValidarPersona(Persona persona) throws ServiceException{
        try{
            ValidarNumeroPositivo validarNumeroPositivo = new ValidarNumeroPositivo(persona.getDNI());
        }catch (ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
        try{
            ValidarSoloLetras validarSoloLetras = new ValidarSoloLetras(persona.getNombre());
        }catch (ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
        try{
            ValidarSoloLetras validarSoloLetras = new ValidarSoloLetras(persona.getApellido());
        }catch (ServiceException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
}
