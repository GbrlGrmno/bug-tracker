package com.gabrielgermano.bugtracker.exception.user;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String username) {
    super("User with username " + username + " not found");
  }

}
