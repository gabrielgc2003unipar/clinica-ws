package com.example.clinicaws.exceptions;

import jakarta.xml.ws.WebFault;

@WebFault(name = "ValidacaoException")
public class ValidacaoException extends Exception{
    public ValidacaoException(String message) {
        super(message);
    }

}
