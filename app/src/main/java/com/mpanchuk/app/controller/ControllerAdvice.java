package com.mpanchuk.app.controller;

import com.mpanchuk.app.domain.ErrorMsg;
import com.mpanchuk.app.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>("No such " + ex.getMessage() + " element", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException ex) {
        return new ResponseEntity<>("INCORRECT INPUT", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PriceException.class)
    public ResponseEntity<String> handlePriceException(PriceException ex) {
        return new ResponseEntity<>("summary stash cost is less then 1000", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvocationTargetException.class)
    public ResponseEntity<String> handleInvocationTargetException(InvocationTargetException ex) {
        return new ResponseEntity<>("request body invalid", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("illegal argument exception", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchCityException.class)
    public ResponseEntity<String> handleNoSuchCityException(NoSuchCityException ex) {
        return new ResponseEntity<>("no such city", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("request body is not valid", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoSuchItemException.class})
    public ResponseEntity<Object> handeNoSuchItemExc(NoSuchItemException ex) {
        return new ResponseEntity<>(new ErrorMsg(HttpStatus.NOT_FOUND.toString(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ItemToAddValidationException.class)
    public ResponseEntity<Object> handleItemToAddValidationExc(ItemToAddValidationException ex) {
        return new ResponseEntity<>(new ErrorMsg(HttpStatus.NOT_FOUND.toString(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntity<>("wrong password or username", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {UsernameExistsException.class})
    public ResponseEntity<String> handleUsernameExistsException(UsernameExistsException ex) {
        return new ResponseEntity<>("username already exists", HttpStatus.FORBIDDEN);
    }
}
