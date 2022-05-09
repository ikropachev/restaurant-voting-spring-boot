package org.ikropachev.votingspringboot.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ModeException extends RuntimeException {
    public ModeException(String message) {
        super(message);
    }
}
