package com.picpaysimplificado.infra.controllerException;


import com.picpaysimplificado.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tnreatDuplicatEntry(DataIntegrityViolationException exception) {
        ExceptionDTO exceptionDto = new ExceptionDTO("Usuario cadastrado!", "400");
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tnreat404(EntityNotFoundException exception) {
        ExceptionDTO exceptionDto = new ExceptionDTO("Usuario cadastrado!", "400");
        return ResponseEntity.badRequest().body(exceptionDto);
    }
}