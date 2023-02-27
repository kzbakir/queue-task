package com.example.queuetask.service.impl;

import com.example.queuetask.model.entity.GeneratedCode;
import com.example.queuetask.repository.GeneratedCodeRepository;
import com.example.queuetask.service.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {
    private final GeneratedCodeRepository codeRepository;

    @Override
    public String generateCode() {
        var generatedCode = codeRepository.findFirstByLastCodeNotNull();
        var lastCode = generateNextCode(generatedCode.getLastCode());
        save(lastCode, generatedCode);
        return lastCode;
    }

    /**
     * save() - Store the last code in the database.
     *
     * @param lastCode      - Last Code (Current)
     * @param generatedCode - Last record in DB
     */
    private void save(String lastCode, GeneratedCode generatedCode) {
        var previousCode = generatedCode.getLastCode();
        generatedCode.setPreviousCode(previousCode);
        generatedCode.setLastCode(lastCode);
        codeRepository.save(generatedCode);
        log.info("jhYr5ejm :: Last code saved - value: {}", lastCode);
    }

    /**
     * generateNextCode() - Returns the next code generation by series.
     *
     * @param code - Last code
     * @return Next code generation by series.
     */
    public static String generateNextCode(String code) {
        final var MIN_CODE_SIZE = 4; // Минимальное количество цифр в коде
        final var MIN_CHAR = 'a'; // Минимальный символ серии
        final var MAX_CHAR = 'z'; // Максимальный символ серии
        final var MIN_DIGIT = '0'; // Минимальная цифра серии
        final var MAX_DIGIT = '9'; // Максимальная цифра серии

        var codeChars = code.toCharArray();
        var lastIndex = codeChars.length - 1;

        var lastChar = codeChars[lastIndex];
        if (lastChar == MAX_DIGIT) {
            codeChars[lastIndex] = MIN_DIGIT;
            lastIndex--;
        } else {
            codeChars[lastIndex] = (char) (lastChar + 1);
            return new String(codeChars);
        }

        while (lastIndex >= 0) {
            var currentChar = codeChars[lastIndex];
            if (currentChar == MAX_CHAR) {
                codeChars[lastIndex] = MIN_CHAR;
                lastIndex--;
            } else if (currentChar == MAX_DIGIT) {
                codeChars[lastIndex] = MIN_DIGIT;
                lastIndex--;
            } else {
                codeChars[lastIndex] = (char) (currentChar + 1);
                break;
            }
        }

        if (lastIndex < 0 && codeChars.length >= MIN_CODE_SIZE) {
            var newCodeChars = new char[codeChars.length + 2];
            for (int i = 0; i < newCodeChars.length; i++) {
                if (i % 2 == 0) {
                    newCodeChars[i] = MIN_CHAR;
                    continue;
                }
                newCodeChars[i] = MIN_DIGIT;
            }
            return new String(newCodeChars);
        }
        return new String(codeChars);
    }
}
