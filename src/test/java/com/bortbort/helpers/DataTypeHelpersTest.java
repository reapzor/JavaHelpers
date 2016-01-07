package com.bortbort.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chuck on 1/6/2016.
 */
public class DataTypeHelpersTest {

    @Test
    public void testTwoSevenBitByteEncodingString() throws Exception {
        String testString = "This Is A Test~~String 123 !./]})-=$#";
        String testEncodedHex = "540068006900730020004900730020004100200054006500" +
                "730074007E007E0053007400720069006E006700200031003200330020002100" +
                "2E002F005D007D0029002D003D0024002300";

        byte[] encodedBytes = DataTypeHelpers.encodeTwoSevenBitByteSequence(testString);

        assertEquals(testEncodedHex, DataTypeHelpers.bytesToHexString(encodedBytes));

        String decodedTestString = DataTypeHelpers.decodeTwoSevenBitByteString(encodedBytes);

        assertEquals(testString, decodedTestString);
    }

    @Test
    public void testTwoSevenBitByteEncodingRawBytes() throws Exception {
        byte[] rawBytes = new byte[] { (byte) 0xFE, (byte) 0xFF, (byte) 0xC3, (byte) 0x00 };
        String encodedBytesHex = "7E7F7F7F437F0000";

        byte[] encodedRawBytes = DataTypeHelpers.encodeTwoSevenBitByteSequence(rawBytes);

        assertEquals(encodedBytesHex, DataTypeHelpers.bytesToHexString(encodedRawBytes));

        byte[] decodedRawBytes = DataTypeHelpers.decodeTwoSevenBitByteSequence(encodedRawBytes);

        assertEquals(rawBytes, decodedRawBytes);
    }
}