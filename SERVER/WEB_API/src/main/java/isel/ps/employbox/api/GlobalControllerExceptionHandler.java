package isel.ps.employbox.api;

import isel.ps.employbox.api.exceptions.BadRequestException;
import isel.ps.employbox.api.exceptions.InvalidApiKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidApiKeyException.class)
    public ResponseEntity<ErrorModel> handleInvalidAPIKey(){
        ErrorModel error = new ErrorModel();
        error.message = "Invalid api key";
        error.status = 401;
        error.type = "http://example.com/error/types/rels/some-type";
        return new ResponseEntity<ErrorModel>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorModel> handleBadRequest(BadRequestException exception){
        ErrorModel error = new ErrorModel();
        error.message = exception.getMessage();
        error.status = 403;
        error.type = "http://example.com/error/types/rels/some-type";
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    public static class ErrorModel {
        public String type;
        public int status = 400;
        public String message;
    }
}
