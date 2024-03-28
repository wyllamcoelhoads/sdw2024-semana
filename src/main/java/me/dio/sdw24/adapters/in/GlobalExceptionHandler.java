package me.dio.sdw24.adapters.in;

import me.dio.sdw24.domain.exeption.ChampionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import javax.xml.parsers.SAXParser;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ChampionNotFoundException.class)
    public ApiError handleDomainException(ChampionNotFoundException domainError){
        return new ApiError(domainError.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiError handleDomainException(Exception domainError){
        return new ApiError("Ops! Ocorreu um erro inesperado!");
    }

    public record ApiError(String message){

    }
}
