package Database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

    private static String algorithm="SHA-256";

    public static String generateHash(String data) throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        byte[] hash = digest.digest(data.getBytes());

        return bytesToStringHex(hash);
    }

    private final static char[] hexArray="0123456789ABCDEF".toCharArray();

    private static String bytesToStringHex(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for(int i=0;i<bytes.length;i++)
        {
            int v=bytes[i] & 0xFF;
            hexChars[i*2]=hexArray[v >>> 4];
            hexChars[i*2 +1 ]=hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }

}
