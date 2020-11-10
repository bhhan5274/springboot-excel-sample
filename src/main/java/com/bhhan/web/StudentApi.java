package com.bhhan.web;

import com.bhhan.excel.multiSheet.MultiSheetExcelFile;
import com.bhhan.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;

    @GetMapping("/students")
    public void downloadStudents(HttpServletResponse response) throws IOException {
        List<StudentService.StudentResponse> students = studentService.findAll();
        MultiSheetExcelFile<StudentService.StudentResponse> multiSheetExcelFile = new MultiSheetExcelFile<>(students, StudentService.StudentResponse.class);
        response.setContentType("application/msexcel");
        response.setHeader("Content-Disposition", "attachment;filename=\"TestDown.xlsx\"");
        multiSheetExcelFile.write(response.getOutputStream());
    }
}
