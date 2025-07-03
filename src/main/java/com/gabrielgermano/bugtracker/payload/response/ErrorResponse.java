package com.gabrielgermano.bugtracker.payload.response;

public record ErrorResponse(String timestamp, int status, String error, String message, String path) {

}
