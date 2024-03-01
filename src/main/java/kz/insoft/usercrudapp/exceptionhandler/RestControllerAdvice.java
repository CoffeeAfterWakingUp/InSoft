package kz.insoft.usercrudapp.exceptionhandler;

import kz.insoft.usercrudapp.dto.ErrorDTO;
import kz.insoft.usercrudapp.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(Exception exception) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus.value(), exception.getMessage());
        return new ResponseEntity<>(errorDTO, httpStatus);
    }


    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(Exception e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus.value(), e.getMessage());
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorDTO> handleRuntimeException(Exception e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus.value(), e.getMessage());
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

}
