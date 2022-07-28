package com.github.sebyplays.logmanager.utils;

import com.github.sebyplays.logmanager.LogManager;
import lombok.SneakyThrows;

public class Logger {

    private String name;
    private String prefix;
    private boolean print = true;
    private boolean printDefaultValue = true;

    public Logger(String name) {
        this.name = name;
    }

    public Logger(String name, boolean print) {
        this.name = name;
        this.printDefaultValue = print;
        this.print = printDefaultValue;
    }

    /**
     * This function is used to change the prefix of this logger.
     *
     * @param name The name of the logger.
     * @return The Logger object itself.
     */
    public Logger name(String name) {
        this.prefix = name;
        return this;
    }

    /**
     * This function sets the print variable to the value of the parameter print and returns the Logger object.
     *
     * @param print Whether or not to print the log to the console.
     * @return The Logger object itself.
     */
    public Logger print(boolean print) {
        this.print = print;
        return this;
    }

    @SneakyThrows
    // Logging a message with the log type INFORMATION.
    public void info(String msg, String... args) {
        LogManager.getLogManager(name).log(LogType.INFORMATION, prefix != null ? "[" + prefix + "] " + msg : msg, true, false, true, print, args);
        prefix = null;
        print = printDefaultValue;
    }

    @SneakyThrows
    // Logging a message with the WARNING log type.
    public void warning(String msg, String... args) {
        LogManager.getLogManager(name).log(LogType.WARNING, prefix != null ? "[" + prefix + "] " + msg : msg, true, false, true, print, args);
        prefix = null;
        print = printDefaultValue;

    }

    @SneakyThrows
    // Logging an error message with the log type ERROR.
    public void error(String msg, String... args) {
        LogManager.getLogManager(name).log(LogType.ERROR, prefix != null ? "[" + prefix + "] " + msg : msg, true, false, true, print, args);
        prefix = null;
        print = printDefaultValue;
    }

    /**
     * "Logs a message with the NORMAL log type."
     * <p>
     * The first parameter is the message to log. The second parameter is a list of arguments to format the message with
     *
     * @param msg The message to be logged
     */
    @SneakyThrows
    public void normal(String msg, String... args) {
        LogManager.getLogManager(name).log(LogType.NORMAL, prefix != null ? "[" + prefix + "] " + msg : msg, true, false, true, print, args);
        prefix = null;
        print = printDefaultValue;
    }

}
