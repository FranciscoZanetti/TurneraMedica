package com.company.validaciones;

import com.company.service.ServiceException;

public class ValidarSoloLetras {

    private String cadena;

    public ValidarSoloLetras(String cadena) throws ServiceException {
        this.cadena = cadena;

        if(cadena.matches(".*\\d.*")) {
            throw new ServiceException("Los nombres no deben contener números");
        }
    }
}
