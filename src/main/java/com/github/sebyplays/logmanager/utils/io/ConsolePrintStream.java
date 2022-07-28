package com.github.sebyplays.logmanager.utils.io;

import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * It creates a new PrintStream that combines the default System.out with a ByteArrayOutputStream
 */
public class ConsolePrintStream {
    public PrintStream defaultPrintStream;
    @Getter
    public ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public ConsolePrintStream() {
        defaultPrintStream = System.out;
        System.setOut(new PrintStream(new OutputStreamCombiner(Arrays.asList(System.out, byteArrayOutputStream))));
    }

    public void setDefaults() {
        System.setOut(defaultPrintStream);
    }

}
