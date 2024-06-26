package org.squidmin.javacryptographylabs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squidmin.javacryptographylabs.service.CryptoService;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@RestController
@RequestMapping("/api/crypto")
@Slf4j
public class CryptoController {

    private final CryptoService cryptoService;
    private final SecretKey key;
    private final IvParameterSpec iv;

    @Autowired
    public CryptoController(CryptoService cryptoService) throws Exception {
        this.cryptoService = cryptoService;
        key = cryptoService.generateKey();
        iv = cryptoService.generateIv();
    }

    @PostMapping("/encrypt")
    public String encrypt(@RequestBody String data) throws Exception {
        return cryptoService.encrypt(data, key, iv);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedData) throws Exception {
        return cryptoService.decrypt(encryptedData, key, iv);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        // Optionally log the exception or handle specific exceptions differently
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
