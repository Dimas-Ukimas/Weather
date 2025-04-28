package com.dimasukimas.exception.handler;

import com.dimasukimas.exception.DataNotFoundException;
import com.dimasukimas.exception.UserAlreadyExistsException;
import com.dimasukimas.exception.UserAuthenticationFailedException;
import com.dimasukimas.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String SIGN_IN_PAGE = "sign-in";
    private static final String SIGN_IN_PAGE_REDIRECT = "redirect:/sign-in";
    private static final String SIGN_UP_PAGE = "sign-up";
    private static final String ERROR_PAGE = "error";

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserAuthenticationFailedException.class)
    public String handleAuthenticationFailed(UserAuthenticationFailedException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());

        return SIGN_IN_PAGE;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(UserNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());

        return SIGN_IN_PAGE;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return SIGN_UP_PAGE;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataNotFoundException.class)
    public String handleDataNotFoundException(DataNotFoundException ex) {

        return SIGN_IN_PAGE_REDIRECT;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Something went wrong, please try again later");
        return ERROR_PAGE;
    }
}
