package com.wondacabinetinc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.InvalidInputException;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.NotFoundException;
import com.wondacabinetinc.wondacabinetinc.utils.http.GlobalControllerExceptionHandler;
import com.wondacabinetinc.wondacabinetinc.utils.http.HttpErrorInfo;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ExceptionTests {
    @Autowired
    GlobalControllerExceptionHandler exceptionHandler;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void InvalidInputExceptionWithEmptyConstructorTest(){
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, ()->{
            throw new InvalidInputException();
        });
        assertEquals(invalidInputException.getMessage(), null);
    }

    @Test
    public void InvalidInputExceptionWithMessageTest(){
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, ()->{
            throw new InvalidInputException("Appropriate InvalidInputException message");
        });
        assertNotNull(invalidInputException.getMessage());
    }

    @Test
    public void NotFoundExceptionWithEmptyConstructorTest(){
        NotFoundException notFoundException = assertThrows(NotFoundException.class, ()->{
            throw new NotFoundException();
        });
        assertEquals(notFoundException.getMessage(), null);
    }

    @Test
    public void NotFoundExceptionWithMessageTest(){
        NotFoundException notFoundException = assertThrows(NotFoundException.class, ()->{
            throw new NotFoundException("Appropriate NotFoundException message");
        });
        assertNotNull(notFoundException.getMessage());
    }

    @Test
    public void HttpErrorInfoWithNoConstructorTest(){
        HttpErrorInfo httpErrorInfo = new HttpErrorInfo();
        assertEquals(httpErrorInfo.getTimestamp(), null);
        assertEquals(httpErrorInfo.getHttpStatus(), null);
        assertEquals(httpErrorInfo.getPath(), null);
        assertEquals(httpErrorInfo.getMessage(), null);
    }

    @Test
    public void HttpErrorInfoWithConstructorTest(){
        HttpErrorInfo httpErrorInfo = new HttpErrorInfo(HttpStatus.BAD_REQUEST, "/owners/9999/", "Owner does not exist");
        assertNotNull(httpErrorInfo.getTimestamp());
        assertEquals(httpErrorInfo.getHttpStatus(), HttpStatus.BAD_REQUEST);
        assertEquals(httpErrorInfo.getPath(), "/owners/9999/");
        assertEquals(httpErrorInfo.getMessage(), "Owner does not exist");
    }

}
