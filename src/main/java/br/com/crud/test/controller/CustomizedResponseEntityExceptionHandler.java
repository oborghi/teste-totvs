package br.com.crud.test.controller;

import br.com.crud.test.entity.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        StringBuilder detailsBuilder = new StringBuilder();
        List<String> detailsList = ex.getBindingResult().getAllErrors().stream()
                .map(error -> messageSource.getMessage(error, Locale.getDefault())).collect(Collectors.toList());

        detailsList.forEach(error -> {
            detailsBuilder.append(error);
            detailsBuilder.append("\r\n");
        });

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Field validation failed", detailsBuilder.toString());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
