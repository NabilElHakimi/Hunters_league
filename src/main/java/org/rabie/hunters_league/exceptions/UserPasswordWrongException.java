package org.rabie.hunters_league.exceptions;

public class UserPasswordWrongException extends RuntimeException {
    public UserPasswordWrongException(String message) {
        super("Error : " + message);
    }
}
