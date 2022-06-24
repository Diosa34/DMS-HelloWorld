package com.github.diosa.dms;

import java.io.IOException;

public class FileVerificationException extends IOException {
    private final String message;

    FileVerificationException(String message){
        this.message = message;
    }
}
