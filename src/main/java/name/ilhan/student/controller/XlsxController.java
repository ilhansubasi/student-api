package name.ilhan.student.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import name.ilhan.student.dto.StudentInput;
import name.ilhan.student.enums.Gender;
import name.ilhan.student.model.Student;
import name.ilhan.student.service.StudentService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/xlsx")
public class XlsxController {
    @Autowired
    private StudentService studentService;

    private final String[] titles = {"Student ID", "First Name", "Last Name", "Gender", "Date of Birth"};

    @GetMapping(value = "/", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiOperation(value = "Download all students as XLSX file")
    public ResponseEntity downloadStudents() throws IOException {
        Workbook wb;
        wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Students");
        Row row = null;
        row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            row.createCell(i).setCellValue(titles[i]);
        }
        int rowNumber = 1;
        ArrayList<Student> students = studentService.getAllStudents();
        CreationHelper creationHelper = wb.getCreationHelper();
        for (Student student : students) {
            row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getFirstName());
            row.createCell(2).setCellValue(student.getLastName());
            row.createCell(3).setCellValue(student.getGender().toString());
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));
            Cell cell = row.createCell(4);
            cell.setCellValue(student.getDob());
            cell.setCellStyle(cellStyle);
        }
        for(int i = 0; i < titles.length; i++) {
            sheet.autoSizeColumn(i);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        wb.write(out);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=students.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())));
    }

    @PostMapping(value = "/")
    @ApiOperation(value = "Upload students as XLSX file")
    public ResponseEntity uploadStudents(
            @ApiParam(value = "XLSX file")
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = wb.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            StudentInput studentInput = new StudentInput();
            studentInput.setFirstName(row.getCell(1).getStringCellValue());
            studentInput.setLastName(row.getCell(2).getStringCellValue());
            studentInput.setGender(Gender.valueOf(row.getCell(3).getStringCellValue()));
            studentInput.setDob(row.getCell(4).getDateCellValue());
            Cell idCell = row.getCell(0);
            if(idCell == null || idCell.getCellType() == CellType.BLANK) {
                studentService.createStudent(studentInput);
            }
            else {
                Double studentIdDouble = row.getCell(0).getNumericCellValue();
                Integer studentId = studentIdDouble.intValue();
                studentService.fullyUpdateStudent(studentId, studentInput);
            }
        }
        return ResponseEntity.ok().body("ok");
    }
}
