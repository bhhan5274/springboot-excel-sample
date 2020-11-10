package com.bhhan.config;

import com.bhhan.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@Component
@RequiredArgsConstructor
public class InitCommandRunner implements CommandLineRunner {
    private final StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0; i < 30000; i++){
            studentService.addStudent(
                    StudentService.StudentRequest.builder()
                            .age(i + 1)
                            .address((i + 1) + "address")
                            .hobby((i + 1) + "hobby")
                            .name((i + 1) + "name")
                            .totalAmount(i + 1)
                            .build()
            );
        }
    }
}
