/**
 * CryptionUtil.java A class that provides some basic encryption functions.
 */



package org.magee.util;

// import other packages

/**
 * The <code>CryptionUtil</code> class provides some basic encryption
 * functions. It cannot be instantiated.
 * <p>
 * 
 * @author Anthony W. Magee
 * @version 1.0, August 12, 2002
 */

/*
 * version changes
 */
public class CryptionUtil {

    public static final int BLOCK_SIZE = 128;

    public static String encrypt(String s, String key) {
        if(s.length() > BLOCK_SIZE) {
            int sub = s.length() / BLOCK_SIZE;
            int temp = Math.round(sub / 2) * BLOCK_SIZE;
            return encrypt(s.substring(0, temp), key) + encrypt(s.substring(temp), key);
        }

        int rand = (int) (Math.random() * 256);
        StringBuffer returnString = new StringBuffer();
        returnString.append(MathUtil.longToHex(rand));

        for(int i = 0; i < s.length(); i++) {
            returnString.append(MathUtil.longToHex((int) (s.charAt(i)) ^ rand ^ (int) (key.charAt(i % key.length()))));
        }

        return returnString.toString();
    }

    public static String decrypt(String s, String key) {
        int dataBlockSize = (BLOCK_SIZE + 1) * 2;
        if(s.length() > dataBlockSize) {
            int sub = s.length() / dataBlockSize;
            int temp = Math.round(sub / 2) * dataBlockSize;
            return decrypt(s.substring(0, temp), key) + decrypt(s.substring(temp), key);
        }

        int checksum = (int) MathUtil.hexToLong(s.substring(0, 2));
        StringBuffer returnString = new StringBuffer();
        for(int i = 2; i < s.length(); i += 2) {
            returnString.append((char) (MathUtil.hexToLong(s.substring(i, 2 + i)) ^ checksum ^ (int) (key.charAt((i - 2) / 2 % key.length()))));
        }

        return returnString.toString();
    }
}