package com.github.sebyplays.logmanager.event;

import com.github.sebyplays.jevent.api.Event;
import com.github.sebyplays.logmanager.utils.LogType;
import lombok.Getter;

public class MessageLoggedEvent extends Event {

    @Getter
    private String message;
    @Getter
    private LogType logType;
    @Getter
    private boolean colored;
    @Getter
    private boolean printed;
    @Getter
    private boolean time;
    @Getter
    private boolean date;
    @Getter
    private String logFile;

    public MessageLoggedEvent(LogType logType, String message, boolean colored, boolean printed, boolean time, boolean date, String file) {
        this.logType = logType;
        this.message = message;
        this.colored = colored;
        this.printed = printed;
        this.time = time;
        this.date = date;
        this.logFile = file;
    }

    public MessageLoggedEvent() {

    }

}
