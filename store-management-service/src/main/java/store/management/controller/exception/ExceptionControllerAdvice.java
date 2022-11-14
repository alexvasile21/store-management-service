package store.management.controller.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import store.management.error.ErrorResponse;
import store.management.error.InvalidRequestException;
import store.management.error.InvalidStatusException;
import store.management.error.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStatusException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }


}
