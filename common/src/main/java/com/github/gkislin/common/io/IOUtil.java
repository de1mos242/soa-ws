package com.github.gkislin.common.io;

import java.io.IOException;
import java.io.OutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * User: gkislin
 * Date: 28.01.14
 */
public class IOUtil {
    /**
     * from org.codehaus.plexus.util.StringOutputStream
     * Wraps a String as an OutputStream.
     */
    public static class StringOutputStream extends OutputStream {
        private StringBuffer buf = new StringBuffer();

        public void write(byte[] b) throws IOException {
            buf.append(new String(b, UTF_8));
        }

        public void write(byte[] b, int off, int len) throws IOException {
            buf.append(new String(b, off, len, UTF_8));
        }

        public void write(int b) throws IOException {
            byte[] bytes = new byte[1];
            bytes[0] = (byte) b;
            buf.append(new String(bytes, UTF_8));
        }

        public String toString() {
            return buf.toString();
        }
    }

}
