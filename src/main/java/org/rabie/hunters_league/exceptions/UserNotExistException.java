package org.rabie.hunters_league.exceptions;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String message) {
        super("Error : " + message);
    }
}
