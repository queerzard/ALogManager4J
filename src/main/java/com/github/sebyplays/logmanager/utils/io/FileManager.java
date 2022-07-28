package com.github.sebyplays.logmanager.utils.io;

import com.github.sebyplays.logmanager.LogManager;
import com.github.sebyplays.logmanager.utils.DateUtil;
import com.github.sebyplays.logmanager.utils.LogType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    /**
     * It creates a file with the name of the test, the date, and the .log extension
     *
     * @param name The name of the file.
     * @return A file object
     */
    public File getFile(String name) {
        DateUtil dateUtil = new DateUtil();
        File file = new File(System.getProperty("user.dir") + "/logs/", name + "-" + dateUtil.getDate() + ".log");
        return file;
    }

    /**
     * If the file exists, create a new file with a suffix
     *
     * @param logName The name of the log file.
     * @return The name of the log file.
     */
    public String initLogFile(String logName) throws IOException {
        File file = getFile(logName);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
            return logName;
        }

        int suffix = 0;
        while (file.exists()) {
            suffix++;
            file = getFile(logName + "_" + suffix);
        }
        file.createNewFile();
        LogManager.getLogManager(logName).log(LogType.INFORMATION, logName.toUpperCase() + "-LOG initialized..", false, false, true, true);
        return logName + "_" + suffix;
    }

    /**
     * If the file doesn't exist, create it, then append the message to the end of the file.
     *
     * @param logName The name of the log file.
     * @param message The message you want to log.
     */
    public void log(String logName, String message) throws IOException {
        File file = getFile(logName);
        if (!file.exists()) {
            initLogFile(logName);
        }
        String messageLine = "";
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) messageLine += scanner.nextLine() + "\n";
        FileWriter writer = new FileWriter(file);
        writer.write(messageLine + message);
        writer.close();
    }


}
