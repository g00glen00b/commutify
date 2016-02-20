package be.g00glen00b.commutify.controller.api;

import be.g00glen00b.commutify.dto.MessageDTO;
import be.g00glen00b.commutify.service.InvalidProfileException;
import be.g00glen00b.commutify.service.ProfileAlreadyExistsException;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ControllerAdvice
public class ControllerExceptionHandler {
    @Autowired
    private MessageSource msgSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationExceptions(MethodArgumentNotValidException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        return new MessageDTO.Builder()
            .code("INV_OBJECT")
            .message(msgSource.getMessage(getDefaultMessage(ex), null, locale))
            .build();
    }

    @ExceptionHandler(ProfileAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processAlreadyExistsException(ProfileAlreadyExistsException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        return new MessageDTO.Builder()
            .code("PROFILE_ALREADY_EXISTS")
            .message(msgSource.getMessage("userSignup.existing", null, locale))
            .build();
    }

    @ExceptionHandler(InvalidProfileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processInvalidException(ProfileAlreadyExistsException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        return new MessageDTO.Builder()
            .code("INV_OBJECT")
            .message(msgSource.getMessage("profile.nonExisting", null, locale))
            .build();
    }

    private String getDefaultMessage(MethodArgumentNotValidException ex) {
        FieldError error = ex.getBindingResult().getFieldError();
        ObjectError globalError = ex.getBindingResult().getGlobalError();
        if (error == null) {
            return globalError.getDefaultMessage();
        } else {
            return error.getDefaultMessage();
        }
    }
}
