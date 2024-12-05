package me.elhakimi.hunters_league.exceptions;

public class UserPasswordWrongException extends RuntimeException {
    public UserPasswordWrongException(String message) {
        super("Error : " + message);
    }
}
