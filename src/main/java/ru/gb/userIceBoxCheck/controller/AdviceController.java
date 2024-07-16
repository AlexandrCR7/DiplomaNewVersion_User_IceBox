package ru.gb.userIceBoxCheck.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.gb.userIceBoxCheck.exeptions.ExceptionBody;
import ru.gb.userIceBoxCheck.exeptions.IceBoxIsNullException;
import ru.gb.userIceBoxCheck.exeptions.IceBoxNotFoundException;
import ru.gb.userIceBoxCheck.exeptions.UserNotFoundException;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(IceBoxIsNullException.class)
    public ResponseEntity<ExceptionBody> handlerIceBoxIsNullException
            (IceBoxIsNullException iceBoxIsNullException) {
        return new ResponseEntity<>(new ExceptionBody(iceBoxIsNullException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IceBoxNotFoundException.class)
    public ResponseEntity<ExceptionBody> handlerIceBoxNotFoundException
            (IceBoxNotFoundException iceBoxNotFoundException) {
        return new ResponseEntity<>(new ExceptionBody(iceBoxNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionBody> handlerUserNotFoundException
            (UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(new ExceptionBody(userNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

}
