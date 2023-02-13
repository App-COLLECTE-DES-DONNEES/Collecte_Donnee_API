package com.assurance.services.exception;

public class AssuranceAlreadyExistException extends Exception {
    /**
     * cette exception sert à signaler qu'une assurance valide est déjà enregistré pour un véhicule
     *
     */
    private static final long serialVersionUID = 1L;

    public AssuranceAlreadyExistException() {
        super();
    }

    public AssuranceAlreadyExistException(String s) {
        super(s);
    }
}
