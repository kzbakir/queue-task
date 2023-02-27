package com.example.queuetask;

import com.example.queuetask.service.impl.CodeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.queuetask.service.impl.CodeServiceImpl.generateNextCode;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class QueueTaskApplicationTests {

    @Test
    void contextLoads() {
        // Тесты на корректную генерацию средущего кода
        assertEquals("a0a1", generateNextCode("a0a0"));
        assertEquals("a0b0", generateNextCode("a0a9"));
        assertEquals("a1a0", generateNextCode("a0z9"));
        assertEquals("c1c0", generateNextCode("c1b9"));


        // Тесты на увеленичение серии
        assertEquals("a0a0a0", generateNextCode("z9z9"));
        assertEquals("a0a0a0a0", generateNextCode("z9z9z9"));
        assertEquals("a0a0a0a0a0", generateNextCode("z9z9z9z9"));
    }

}
