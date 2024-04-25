package org.squidmin.javacryptographylabs.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.squidmin.javacryptographylabs.service.CryptoService;

@WebMvcTest(CryptoController.class)
public class CryptoControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CryptoService cryptoService;

    @Test
    public void testEncrypt() throws Exception {
        String plainText = "hello";
        String encryptedText = "encrypted";

        BDDMockito.given(
            cryptoService.encrypt(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.any())
        ).willReturn(encryptedText);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/crypto/encrypt")
                .contentType(MediaType.TEXT_PLAIN)
                .content(plainText))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(encryptedText));
    }

    @Test
    public void testDecrypt() throws Exception {
        String encryptedText = "encrypted";
        String decryptedText = "hello";

        BDDMockito.given(
            cryptoService.decrypt(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.any())
        ).willReturn(decryptedText);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/crypto/decrypt")
                .contentType(MediaType.TEXT_PLAIN)
                .content(encryptedText))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(decryptedText));
    }

    @Test
    public void testEncryptError() throws Exception {
        String plainText = "hello";
        BDDMockito.given(
            cryptoService.encrypt(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.any())
        ).willThrow(new RuntimeException("Encryption error"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/crypto/encrypt")
                .contentType(MediaType.TEXT_PLAIN)
                .content(plainText))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError())
            .andExpect(MockMvcResultMatchers.content().string("Encryption error"));
    }

    @Test
    public void testDecryptError() throws Exception {
        String encryptedText = "encrypted";
        BDDMockito.given(
            cryptoService.decrypt(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.any())
        ).willThrow(new RuntimeException("Decryption error"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/crypto/decrypt")
                .contentType(MediaType.TEXT_PLAIN)
                .content(encryptedText))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError())
            .andExpect(MockMvcResultMatchers.content().string("Decryption error"));
    }

}
