package com.bortbort.helpers;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Created by chuck on 1/4/2016.
 */
public class DataTypeHelpers {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHexString(byte... bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String decodeTwoSevenBitByteString(byte[] bytes) throws UnsupportedEncodingException {
        return decodeTwoSevenBitByteString(bytes, 0, bytes.length);
    }

    public static String decodeTwoSevenBitByteString(byte[] bytes, int offset, int size)
            throws UnsupportedEncodingException {
        byte[] decodedBytes = decodeTwoSevenBitByteSequence(bytes, offset, size);
        return new String(decodedBytes, "UTF-8");
    }

    public static byte[] decodeTwoSevenBitByteSequence(byte[] encodedBytes) {
        return decodeTwoSevenBitByteSequence(encodedBytes, 0, encodedBytes.length);
    }

    public static byte[] decodeTwoSevenBitByteSequence(byte[] encodedBytes, int offset, int size) {
        size = size >>> 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);

        for (int x = 0; x < size; x++) {
            byteBuffer.put(
                    decodeTwoSevenBitByteSequence(
                            encodedBytes[offset++],
                            encodedBytes[offset++]));
        }

        return byteBuffer.array();
    }

    public static byte decodeTwoSevenBitByteSequence(int byte1, int byte2) {
        return (byte) (byte1 + (byte2 << 7));
    }


    public static byte[] encodeTwoSevenBitByteSequence(String string) {
        return encodeTwoSevenBitByteSequence(string.getBytes());
    }

    public static byte[] encodeTwoSevenBitByteSequence(byte[] bytes) {
        return encodeTwoSevenBitByteSequence(bytes, 0, bytes.length);
    }

    public static byte[] encodeTwoSevenBitByteSequence(byte[] bytes, int offset, int size) {
        int encodedSize = size << 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(encodedSize);

        for (int x = 0; x < size; x++) {
            byteBuffer.put(encodeTwoSevenBitByteSequence(bytes[offset]));
            offset++;
        }

        return byteBuffer.array();
    }

    public static byte[] encodeTwoSevenBitByteSequence(byte decodedByte) {
        return new byte[] {
                (byte) (decodedByte & 0x7F),
                (byte) ((decodedByte >> 7) & 0x7F)};
    }


}
