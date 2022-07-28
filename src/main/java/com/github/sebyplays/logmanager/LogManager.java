package com.github.sebyplays.logmanager;

import com.github.sebyplays.coloredconsole.ColoredConsole;
import com.github.sebyplays.jevent.JEvent;
import com.github.sebyplays.logmanager.event.MessageLoggedEvent;
import com.github.sebyplays.logmanager.utils.LogType;
import com.github.sebyplays.logmanager.utils.io.FileManager;
import lombok.Getter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogManager {

    @Getter
    private static HashMap<String, LogManager> logManagers = new HashMap<>();
    private final FileManager fileManager = new FileManager();
    private String logFile;

    // It creates a new LogManager object.
    public LogManager(String logFile) throws IOException {
        this.logFile = logFile;
        logManagers.put(logFile, this);
        FileManager fileManager = new FileManager();
        this.logFile = fileManager.initLogFile(logFile);
    }

    /**
     * If the log manager with the given name exists, return it, otherwise create a new one.
     *
     * @param name The name of the log manager.
     * @return A new LogManager object.
     */
    public static LogManager getLogManager(String name) throws IOException {
        for (Map.Entry<String, LogManager> entry : LogManager.getLogManagers().entrySet()) {
            if (entry.getKey().equals(name)) return entry.getValue();
        }

        return new LogManager(name);
    }

    /**
     * It logs a message to a file, and optionally prints it to the console
     *
     * @param logType  The type of log.
     * @param message  The message to log
     * @param colorize Whether or not to colorize the message.
     * @param date     Whether or not to include the date in the log
     * @param time     Whether or not to print the time
     * @param print    Whether or not to print the message to the console.
     */
    public void log(LogType logType, String message, boolean colorize, boolean date, boolean time, boolean print) throws IOException {
        fileManager.log(logFile, ColoredConsole.getString("[" + logType.getPrefix() + "] -> " + message, colorize, date, time));
        if (print)
            ColoredConsole.print("[" + (colorize ? logType.getColor() : "") + logType.getPrefix() + (colorize ? "§r" : "") + "] -> " + message, colorize, date, time);
        new JEvent(new MessageLoggedEvent(logType, message, colorize, print, time, date, logFile)).callEvent();
    }

    /**
     * It logs a message to a file
     *
     * @param logType  The type of log.
     * @param message  The message to log
     * @param colorize Whether or not to colorize the message.
     * @param date     Whether or not to include the date in the log.
     * @param time     If the time should be printed
     * @param print    Whether or not to print the message to the console.
     */
    public void log(LogType logType, String message, boolean colorize, boolean date, boolean time, boolean print, String... placeholders) throws IOException {
        String messageToLog = message;
        for (String placeholder : placeholders)
            messageToLog = messageToLog.replaceFirst("\\{}", placeholder);

        fileManager.log(logFile, ColoredConsole.getString("[" + logType.getPrefix() + "] -> " + messageToLog, colorize, date, time));
        if (print)
            ColoredConsole.print("[" + (colorize ? logType.getColor() : "") + logType.getPrefix() + (colorize ? "§r" : "") + "] -> " + messageToLog, colorize, date, time);
        new JEvent(new MessageLoggedEvent(logType, messageToLog, colorize, print, time, date, logFile)).callEvent();
    }


}
