package com.github.gkislin.common;

import org.apache.log4j.Level;

public enum LoggingLevel {
    TRACE(Level.TRACE), //The TRACE Level designates finer-grained informational events than the DEBUG
    DEBUG(Level.DEBUG), // The DEBUG Level designates fine-grained informational events that are most useful to debug an application.
    INFO(Level.INFO), // The INFO level designates informational messages that highlight the progress of the application at coarse-grained level.
    WARN(Level.WARN), //The WARN level designates potentially harmful situations.
    ERROR(Level.ERROR), // The ERROR level designates error events that might still allow the application to continue running.
    FATAL(Level.FATAL); //The FATAL level designates very severe error events that will presumably lead the application to abort.

    Level level;

    LoggingLevel(Level level) {
        this.level = level;
    }
}