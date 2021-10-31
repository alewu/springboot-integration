package com.ale.open;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Data
@Slf4j
public class SignInfo {
    private String token;
    private String timestamp;
    private String clientId;
    private String nonce;
    private String signaure;

    private String sign() {
        StringBuffer sb = new StringBuffer();
        sb.append(token).append(timestamp).append(clientId).append(nonce);
        signaure = shaEncode(sb.toString());
        return signaure;
    }

    private boolean checkSignature(String timestamp, String nonce, String signature){
        return false;
    }


    @SneakyThrows
    public static String shaEncode(String inStr) {
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            log.error("shaEncode failed", e);
            return "";
        }

        byte[] byteArray = inStr.getBytes(StandardCharsets.UTF_8);
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
