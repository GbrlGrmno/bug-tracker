package com.gabrielgermano.bugtracker.exception;

import com.gabrielgermano.bugtracker.exception.project.ProjectNotFoundException;
import com.gabrielgermano.bugtracker.exception.role.RoleNotFoundException;
import com.gabrielgermano.bugtracker.exception.ticket.TicketNotFoundException;
import com.gabrielgermano.bugtracker.exception.user.UserAlreadyExistsException;
import com.gabrielgermano.bugtracker.exception.user.UserNotFoundException;
import com.gabrielgermano.bugtracker.payload.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex,
                                                                 HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(
                OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<ErrorResponse>(response,
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex,
                                                                     HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(
                OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<ErrorResponse>(response,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException ex,
                                                                     HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(
                OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<ErrorResponse>(response,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProjectNotFoundException(ProjectNotFoundException ex,
                                                                        HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(
                OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<ErrorResponse>(response,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTicketNotFoundException(TicketNotFoundException ex,
                                                                       HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(
                OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<ErrorResponse>(response,
                HttpStatus.NOT_FOUND);
    }
}
