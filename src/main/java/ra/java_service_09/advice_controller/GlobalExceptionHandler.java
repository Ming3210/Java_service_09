package ra.java_service_09.advice_controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ra.java_service_09.model.dto.response.ErrorDataResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(ArithmeticException ex) {
        log.error("Global handler - ArithmeticException: {}", ex.getMessage());
        return ResponseEntity.badRequest().body("Lỗi toán học: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDataResponse<Map<String,String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        for (int i = 0; i < ex.getAllErrors().size(); i++) {
            ObjectError objectError = ex.getAllErrors().get(i);
            errors.put("error - "+i, objectError.getDefaultMessage());
        }
        log.error("{} error - {}", LocalDateTime.now().toString(), errors.toString());
        return new ResponseEntity<>(new ErrorDataResponse<>("errors", errors, HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDataResponse<String>> handleNoSuchElementException(NoSuchElementException ex) {
        log.error("Global handler - NoSuchElementException: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorDataResponse<>("errors", ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

}
