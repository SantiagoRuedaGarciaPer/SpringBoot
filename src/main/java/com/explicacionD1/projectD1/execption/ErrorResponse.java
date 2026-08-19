package com.explicacionD1.projectD1.execption;

import java.time.LocalDateTime;

public record ErrorResponse (
        LocalDateTime timestamp, int status, String message, String errorCode
){

}
