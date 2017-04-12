package uk.co.boardgame;

import javaslang.Tuple;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.co.boardgame.model.Errors;

import javax.servlet.http.HttpServletRequest;

@EnableWebMvc
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<Object> handleBusinessException(HttpServletRequest request, BusinessException exception) {
        return ResponseEntity.status(403).body(new Errors(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<Object> handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException exception) {
        return ResponseEntity.status(404).body(new Errors(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(LocationUnavailableException.class)
    ResponseEntity<Object> handleLocationUnavailableException(HttpServletRequest request, LocationUnavailableException exception) {
        return ResponseEntity.status(409).body(new Errors(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    ResponseEntity<Object> handleValidationException(HttpServletRequest request, ValidationException exception) {
        return ResponseEntity.status(400).body(new Errors(exception.errors()));
    }
}
