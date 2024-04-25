package org.squidmin.javacryptographylabs.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CryptoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testEncryptDecryptIntegration() {
        String plainText = "test";
        // Encrypt
        ResponseEntity<String> encryptedResponse = restTemplate.postForEntity(
            "http://localhost:" + port + "/api/crypto/encrypt",
            plainText,
            String.class
        );
        Assertions.assertEquals(HttpStatus.OK, encryptedResponse.getStatusCode());
        String encryptedText = encryptedResponse.getBody();

        // Decrypt
        ResponseEntity<String> decryptedResponse = restTemplate.postForEntity(
            "http://localhost:" + port + "/api/crypto/decrypt",
            encryptedText,
            String.class
        );
        Assertions.assertEquals(HttpStatus.OK, decryptedResponse.getStatusCode());
        String decryptedText = decryptedResponse.getBody();

        Assertions.assertEquals(plainText, decryptedText);
    }

}
