package com.challenger.superhero.Exceptions.Model;

import com.challenger.superhero.Commons.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ApiExceptionResponse {

    private HttpStatus status;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern =  "dd-MM-yyyy hh:mm:ss"
    )
    private String timeStamp;
    private String message;
    private String debugMessage;
    private ApiExceptionResponse() {
        this.timeStamp = Utils.LocalDateTimeNow().toString();
    }

    public ApiExceptionResponse(HttpStatus status) {
        this();
        this.status = status;
        this.timeStamp = Utils.LocalDateTimeNow().toString();
    }

    public ApiExceptionResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
        this.timeStamp = Utils.LocalDateTimeNow().toString();
    }

    public ApiExceptionResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
        this.timeStamp = Utils.LocalDateTimeNow().toString();
    }

}
