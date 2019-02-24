package fii.hotel.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Spa type not found")
public class SpaTypeNotFoundException extends RuntimeException {

    public SpaTypeNotFoundException(String message) {
        super(message);
    }

    public SpaTypeNotFoundException(Long id) {
        this("Spa type with id " + id + " doesn't exist.");
    }
}
