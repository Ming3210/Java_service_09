package ra.java_service_09;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingExample {

    public void runLoggingDemo() {
        log.trace("TRACE level log: This is a very detailed trace log.");
        log.debug("DEBUG level log: Debugging application logic here.");
        log.info("INFO level log: User has successfully logged in.");
        log.warn("WARN level log: Disk space running low.");
        log.error("ERROR level log: Failed to connect to database.");
    }
}
