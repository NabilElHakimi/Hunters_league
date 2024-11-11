package me.elhakimi.hunters_league.web.errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();

            if (!errors.containsKey(fieldName)) {
                errors.put(fieldName, new ArrayList<>());
            }
            errors.get(fieldName).add(errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
}