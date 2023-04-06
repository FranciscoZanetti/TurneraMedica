package com.company.validaciones;

import com.company.service.ServiceException;

public class ValidarNumeroPositivo {
    private int numero;

    public ValidarNumeroPositivo(int numero) throws ServiceException {
        this.numero = numero;

        if(numero <= 0) {
            throw new ServiceException("El número es negativo");
        }
    }
}
