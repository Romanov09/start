package exception.main.handler.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import exception.main.handler.resource.ResourceBadRequestException;
import exception.main.handler.resource.ResourceForbiddenException;
import exception.main.handler.resource.ResourceNotFoundException;
import exception.main.handler.resource.ResourceUnauthorizedException;

@ControllerAdvice
public class CustomException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<String> handleResourceBadRequestException(ResourceBadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceUnauthorizedException.class)
    public ResponseEntity<String> handleResourceUnauthorizedException(ResourceUnauthorizedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceForbiddenException.class)
    public ResponseEntity<String> handleResourceForbiddenException(ResourceForbiddenException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}
