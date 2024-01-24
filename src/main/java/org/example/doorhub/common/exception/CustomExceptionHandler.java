package org.example.doorhub.common.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.TokenMgrError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler
{
    public CustomErrorResponse buildErrorResponse( String message, HttpStatus status )
    {
        return buildErrorResponse( message, status, null );
    }

    public CustomErrorResponse buildErrorResponse( Map<String, Object> errors, HttpStatus status )
    {
        return buildErrorResponse( null, status, errors );
    }

    public CustomErrorResponse buildErrorResponse( String message, HttpStatus status, Map<String, Object> errors )
    {
        return new CustomErrorResponse( message, status, errors, LocalDateTime.now() );
    }

    @ExceptionHandler( Exception.class )
    public CustomErrorResponse handleExceptions( Exception e )
    {
        log.error( e.getMessage(), e );
        return buildErrorResponse( "Something is wrong, please repeat later", HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @ExceptionHandler( EntityNotFoundException.class )
    public ResponseEntity<CustomErrorResponse> handleEntityNotFoundException( EntityNotFoundException e )
    {
        log.error( e.getMessage(), e );
        return ResponseEntity
            .status( HttpStatus.NOT_FOUND )
            .body( buildErrorResponse( e.getMessage(), HttpStatus.NOT_FOUND ) );
    }

    @ExceptionHandler( DataIntegrityViolationException.class )
    // todo We need to handle these exceptions correctly
    public ResponseEntity<CustomErrorResponse> handleDataIntegrityViolationException( DataIntegrityViolationException e )
    {
        log.error( e.getMessage(), e );

        String message = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
        message = e.getCause().getCause() == null ? message : e.getCause().getCause().getMessage();
        message = e.getCause().getCause().getCause() == null ? message : e.getCause().getCause().getCause().getMessage();

        return ResponseEntity
            .status( HttpStatus.CONFLICT )
            .body( buildErrorResponse( message, HttpStatus.CONFLICT ) );
    }

//    @ExceptionHandler( MethodArgumentNotValidException.class )
//    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValidException(String e )
//    {
//        Map<String, Object> errors = new HashMap<>();
//
//        // Collecting errors
//        e.getBindingResult().getAllErrors().forEach( error -> {
//            String fieldName = ( (FieldError) error ).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put( fieldName, errorMessage );
//        } );
//
//        log.error( e.getMessage(), e );
//        return ResponseEntity
//            .status( HttpStatus.BAD_REQUEST )
//            .body( buildErrorResponse( errors, HttpStatus.BAD_REQUEST ) );
//    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNoResourceFoundException(NoResourceFoundException e){
        log.error(e.getMessage(),e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CustomErrorResponse.builder()
                        .message("Wrong swagger-url entered")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccessDeniedException(AccessDeniedException e){
        log.error(e.getMessage(),e);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(CustomErrorResponse.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.FORBIDDEN)
                        .build());
    }
    @ExceptionHandler(PhoneNumberNotVerifiedException.class)
    public ResponseEntity<CustomErrorResponse> handlePhoneNumberNotVerifiedException(PhoneNumberNotVerifiedException e){
        log.error(e.getMessage(),e);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(CustomErrorResponse.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.FORBIDDEN)
                        .build());
    }
    @ExceptionHandler(SmsVerificationException.class)
    public ResponseEntity<CustomErrorResponse> handleSmsVerificationException(SmsVerificationException e){
        log.error(e.getMessage(),e);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(CustomErrorResponse.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.FORBIDDEN)
                        .build());
    }

    @ExceptionHandler(CustomExceptionThisUsernameOlReadyTaken.class)
    public ResponseEntity<CustomErrorResponse> handleSmsVerificationException(CustomExceptionThisUsernameOlReadyTaken e){
        log.error(e.getMessage(),e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CustomErrorResponse.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }

    @ExceptionHandler(ExceptionUNAUTHORIZED.class)
    public ResponseEntity<CustomErrorResponse> handleSmsVerificationException(ExceptionUNAUTHORIZED e){
        log.error(e.getMessage(),e);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(CustomErrorResponse.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.UNAUTHORIZED)
                        .build());
    }
    public ResponseEntity<CustomErrorResponse> handleSmsAlreadySentException(SmsAlreadySentException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CustomErrorResponse.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }

}
