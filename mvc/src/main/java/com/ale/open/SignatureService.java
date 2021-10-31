package com.ale.open;

import org.springframework.stereotype.Service;

@Service
public class SignatureService {
    public boolean checkSignature(String timestamp, String nonce, String signature) {
        return false;
    }
}
