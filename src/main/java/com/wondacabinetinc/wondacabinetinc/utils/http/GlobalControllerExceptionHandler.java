package com.wondacabinetinc.wondacabinetinc.utils.http;

import com.wondacabinetinc.wondacabinetinc.utils.exceptions.InvalidInputException;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.NotFoundException;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.TokenRefreshException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public HttpErrorInfo handleNotFoundException(ServerHttpRequest request, Exception ex){
        return createHttpErrorInfo(NOT_FOUND, request, ex);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidInputException.class)
    public HttpErrorInfo handleInvalidInputException(ServerHttpRequest request, Exception ex){
        return createHttpErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY, request, ex);
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(TokenRefreshException.class)
    public HttpErrorInfo handleTokenRefreshException(ServerHttpRequest request, Exception ex){
        return createHttpErrorInfo(FORBIDDEN, request, ex);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, ServerHttpRequest request, Exception ex){

        final String path = request.getPath().pathWithinApplication().value();
        final String message = ex.getMessage();

        LOG.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);

        return new HttpErrorInfo(httpStatus, path, message);
    }
}
