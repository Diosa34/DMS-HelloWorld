package com.github.Diosa34.DMS_HelloWorld;

import java.io.IOException;

public class FileVerificationException extends IOException {
    private final String message;

    FileVerificationException(String message){
        this.message = message;
    }
}
