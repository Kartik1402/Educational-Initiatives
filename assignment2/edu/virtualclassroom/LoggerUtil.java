package edu.virtualclassroom;

import java.io.IOException;
import java.util.logging.*;

public final class LoggerUtil {
    private static final Logger logger = Logger.getLogger("VirtualClassroomLogger");
    static {
        logger.setUseParentHandlers(false);
        try {
            Handler fh = new FileHandler("virtual_classroom.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            // if file handler fails, still keep logger but fallback to console
            ConsoleHandler ch = new ConsoleHandler();
            ch.setFormatter(new SimpleFormatter());
            logger.addHandler(ch);
            logger.warning("FileHandler unavailable, logging to console. Reason: " + e.getMessage());
        }
    }
    private LoggerUtil() {}
    public static Logger getLogger() { return logger; }
}
