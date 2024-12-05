package me.elhakimi.hunters_league.exceptions;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String message) {
        super("Error : " + message);
    }
}
