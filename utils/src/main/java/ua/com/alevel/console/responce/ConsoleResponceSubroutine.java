package ua.com.alevel.console.responce;

import ua.com.alevel.console.Subroutine;

import java.io.BufferedReader;

public abstract class ConsoleResponceSubroutine extends Subroutine {

    private final String URL = "";

    public abstract Responce run(BufferedReader bufferedReader);

    public String getSubroutineURL() {
        return URL;
    }
}