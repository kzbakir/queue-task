package com.example.queuetask.controller;

import com.example.queuetask.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/code")
public class CodeController {
    private final CodeService codeService;

    @GetMapping()
    private ResponseEntity<String> generateCode() {
        return ResponseEntity.ok(codeService.generateCode());
    }
}
