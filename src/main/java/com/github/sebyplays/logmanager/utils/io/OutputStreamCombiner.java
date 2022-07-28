package com.github.sebyplays.logmanager.utils.io;

import lombok.SneakyThrows;

import java.io.OutputStream;
import java.util.List;

public class OutputStreamCombiner extends OutputStream {

    private List<OutputStream> outputStreams;

    public OutputStreamCombiner(List<OutputStream> outputStreams) {
        this.outputStreams = outputStreams;
    }

    @SneakyThrows
    public void write(int b) {
        for (OutputStream os : outputStreams) {
            os.write(b);
        }
    }

    @SneakyThrows
    public void flush() {
        for (OutputStream os : outputStreams) {
            os.flush();
        }
    }

    @SneakyThrows
    public void close() {
        for (OutputStream os : outputStreams) {
            os.close();
        }
    }

}
