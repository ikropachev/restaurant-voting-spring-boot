package org.ikropachev.votingspringboot.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class LateVoteException extends RuntimeException {
    public LateVoteException(String message) {
        super(message);
    }
}
