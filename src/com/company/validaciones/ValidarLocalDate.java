package com.company.validaciones;

import com.company.service.ServiceException;

import java.time.LocalDate;

public class ValidarLocalDate {
    public ValidarLocalDate(String input) throws ServiceException {
        try{
            LocalDate.parse(input);
        }catch (Exception exception){
            throw new ServiceException("La fecha ingresada es inválida");
        }
    }
}
