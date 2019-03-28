package fii.hotel.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Aliment not found")
public class AlimentNotFoundException extends RuntimeException {

    public AlimentNotFoundException(String message) {
        super(message);
    }

    public AlimentNotFoundException(Long id) {
        this("Aliment with id " + id + " doesn't exist.");
    }
}
