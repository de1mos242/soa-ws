package com.github.gkislin.common.io;

import com.github.gkislin.common.ExceptionType;
import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.util.Util;

import java.io.File;

/**
 * User: gkislin
 * Date: 7/27/11
 * Replace ${placeholder}  return existed directory
 */
public class Directory extends File {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(Directory.class);

    public Directory(String pathname) {
        this(pathname, false);
    }

    public Directory(String pathname, boolean create) {
        super(Util.resolveReplacement(pathname));
        if (create && !isDirectory()) {
            mkdirs();
        }
        checkExist();
    }

    public void checkWrite() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(getPath());
        }
    }

    public void checkExist() {
        if (!isDirectory()) {
            throw LOGGER.getStateException(getAbsolutePath() + " не является каталогом", ExceptionType.FILE);
        }
    }
}
