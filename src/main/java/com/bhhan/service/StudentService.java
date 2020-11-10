package com.bhhan.service;

import com.bhhan.DefaultBodyStyle;
import com.bhhan.DefaultHeaderStyle;
import com.bhhan.ExcelColumn;
import com.bhhan.ExcelColumnStyle;
import com.bhhan.domain.Student;
import com.bhhan.domain.StudentRepository;
import com.bhhan.style.DefaultExcelCellStyle;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@Transactional
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class StudentRequest {
        private String name;
        private int age;
        private String address;
        private String hobby;
        private double totalAmount;

        @Builder
        public StudentRequest(String name, int age, String address,
                              String hobby, double totalAmount){
            this.name = name;
            this.address = address;
            this.age = age;
            this.hobby = hobby;
            this.totalAmount = totalAmount;
        }

        public Student toEntity(){
            return Student.builder()
                    .address(address)
                    .age(age)
                    .hobby(hobby)
                    .name(name)
                    .totalAmount(totalAmount)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @DefaultHeaderStyle(style = @ExcelColumnStyle(excelCellStyleClass = DefaultExcelCellStyle.class, enumName = "BLUE_HEADER"))
    @DefaultBodyStyle(style = @ExcelColumnStyle(excelCellStyleClass = DefaultExcelCellStyle.class, enumName = "BODY"))
    public static class StudentResponse {

        @ExcelColumn(headerName = "이름")
        private String name;
        @ExcelColumn(headerName = "나이")
        private int age;
        @ExcelColumn(headerName = "주소")
        private String address;
        @ExcelColumn(headerName = "취미")
        private String hobby;
        @ExcelColumn(headerName = "재산")
        private double totalAmount;

        StudentResponse(Student student){
            this.name = student.getName();
            this.address = student.getAddress();
            this.age = student.getAge();
            this.hobby = student.getHobby();
            this.totalAmount = student.getTotalAmount();
        }
    }

    public List<StudentResponse> findAll(){
        return studentRepository.findAll()
                .stream()
                .map(StudentResponse::new)
                .collect(toList());
    }

    public void addStudent(StudentRequest studentRequest){
        studentRepository.save(studentRequest.toEntity());
    }
}
