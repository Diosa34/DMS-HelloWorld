package com.github.diosa.dms;

import java.io.IOException;

public class SameLinksException extends IOException {
    public static String message = "Этот скрипт уже выполняется в данный момент";
}
