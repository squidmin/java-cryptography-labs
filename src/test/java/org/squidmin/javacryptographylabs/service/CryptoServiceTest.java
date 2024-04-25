package org.squidmin.javacryptographylabs.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@SpringBootTest
public class CryptoServiceTest {

    private CryptoService cryptoService;
    private SecretKey secretKey;
    private IvParameterSpec iv;

    @BeforeEach
    void beforeEach() throws Exception {
        cryptoService = new CryptoService();
        secretKey = cryptoService.generateKey();
        iv = cryptoService.generateIv();
    }

    @Test
    public void encrypt_decrypt() throws Exception {
        String input = "Plain Text";
        String encrypted = cryptoService.encrypt(input, secretKey, iv);
        String decrypted = cryptoService.decrypt(encrypted, secretKey, iv);
        Assertions.assertEquals(input, decrypted);
    }

}
