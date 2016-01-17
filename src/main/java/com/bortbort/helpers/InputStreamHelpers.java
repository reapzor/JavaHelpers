package com.bortbort.helpers;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chuck on 1/17/2016.
 */
public class InputStreamHelpers {

    public static Boolean fastReadBytesWithTimeout(InputStream inputStream,
                                                   byte[] buffer, long timeout) throws IOException {
        return fastReadBytesWithTimeout(inputStream, buffer, buffer.length, timeout);
    }

    public static Boolean fastReadBytesWithTimeout(InputStream inputStream,
                                                   byte[] buffer, int length,
                                                   long timeout) throws IOException {
        int readByteCount = 0;
        long timeoutTime = System.currentTimeMillis() + timeout;

        while (System.currentTimeMillis() < timeoutTime && readByteCount < length) {
            int readByte = inputStream.read();
            if (readByte == -1) {
                return false;
            }
            else {
                buffer[readByteCount] = (byte) readByte;
                readByteCount += 1;
            }
        }

        return true;
    }

}
