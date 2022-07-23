package core.logger;

import core.enums.LogLevel;
import org.apache.log4j.Logger;

public class LoggerFactory {
    public Logger log;

    public LoggerFactory(Class className) {
        log = Logger.getLogger(className.getName());
    }


    public boolean logMessage(LogLevel logLevel, String messageToPrint) {
        switch (logLevel) {
            case INFO:
                log.info(messageToPrint);
                break;
            case WARN:
                log.warn(messageToPrint);
                break;
            case DEBUG:
                log.debug(messageToPrint);
                break;
            case ERROR:
                log.error(messageToPrint);
                break;
        }
        return true;
    }


}
