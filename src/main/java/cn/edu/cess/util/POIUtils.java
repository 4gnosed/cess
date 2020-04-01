package cn.edu.cess.util;

import cn.edu.cess.entity.content.student.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 实现毕业生信息表 导出与导入
 *
 * @author Gnosed Lu
 * @since 2020-03-30
 */
public class POIUtils {
    static String excelFileName = "毕业生信息表";
    static String[] heads = new String[]{
            "编号", "姓名", "学号", "性别", "出生日期",
            "身份证号码", "民族", "籍贯", "政治面貌", "邮件", "电话号码",
            "联系地址", "最高学位", "毕业院校", "所属院系", "专业",
            "学生职位", "外语水平", "计算机水平", "入学时间", "毕业时间",
    };

    public static void student2Excel(List<Student> list, HttpServletResponse response) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("毕业生信息");
        //文档管理员
        docInfo.setManager("cess");
        //设置公司信息
        docInfo.setCompany("www.cess.guet.edu.cn");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle(excelFileName);
        //文档作者
        summInfo.setAuthor("cess");
        // 文档备注
        summInfo.setComments("本文档由 cess 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet(excelFileName);
        //设置列的宽度
        for (int i = 0; i < heads.length; i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);
        for (int i = 0; i < heads.length; i++) {
            HSSFCell cell = r0.createCell(i);
            cell.setCellValue(heads[i]);
            cell.setCellStyle(headerStyle);
        }
        //7. 填充数据
        Student student = null;
        for (int i = 0; i < list.size(); i++) {
            student = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
//            "编号", "姓名", "学号", "性别", "出生日期",
//                    "身份证号码", "民族", "籍贯", "政治面貌", "邮件", "电话号码",
//                    "联系地址", "最高学位", "毕业院校", "所属院系", "专业",
//                    "学生职位", "外语水平", "计算机水平", "入学时间", "毕业时间",
            Nation nation = student.getNation();
            Politics politics = student.getPolitics();
            Department department = student.getDepartment();
            Specialty specialty = student.getSpecialty();
            Position position = student.getPosition();
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getStudentId());
            row.createCell(3).setCellValue(student.getGender());
            row.createCell(5).setCellValue(student.getIdCard());
            row.createCell(6).setCellValue(nation == null ? null : nation.getName());
            row.createCell(7).setCellValue(student.getNativePlace());
            row.createCell(8).setCellValue(politics == null ? null : politics.getName());
            row.createCell(9).setCellValue(student.getEmail());
            row.createCell(10).setCellValue(student.getPhone());
            row.createCell(11).setCellValue(student.getAddress());
            row.createCell(12).setCellValue(student.getTopDegree());
            row.createCell(13).setCellValue(student.getSchool());
            row.createCell(14).setCellValue(department == null ? null : department.getName());
            row.createCell(15).setCellValue(specialty == null ? null : specialty.getName());
            row.createCell(16).setCellValue(position == null ? null : position.getName());
            row.createCell(17).setCellValue(student.getLanguageLevel());
            row.createCell(18).setCellValue(student.getComputerLevel());
            HSSFCell cell19 = row.createCell(19);
            cell19.setCellStyle(dateCellStyle);
            cell19.setCellValue(student.getBeginDate());
            HSSFCell cell20 = row.createCell(20);
            cell20.setCellStyle(dateCellStyle);
            cell20.setCellValue(student.getEndDate());
            HSSFCell cell4 = row.createCell(4);
            cell4.setCellStyle(dateCellStyle);
            cell4.setCellValue(student.getBirthday());
        }
        //8. 将Excel文件写入response输出流中
        OutputStream os = null;
        try {
            String fileName = new String((excelFileName + ".xls").getBytes("UTF-8"), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/ms-excel;charset=UTF-8");
            os = response.getOutputStream();
            workbook.write(os);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.flush();
                os.close();
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Excel 解析成 毕业生数据集合
     *
     * @param file
     * @param allNations
     * @param allPolitics
     * @param allDepartments
     * @param allPositions
     * @param allJobLevels
     * @return
     */
//    public static List<Student> excel2Student(MultipartFile file, List<Nation> allNations, List<Politics> allPolitics, List<Department> allDepartments, List<Position> allPositions, List<JobLevel> allJobLevels) {
//        List<Student> list = new ArrayList<>();
//        Student student = null;
//        try {
//            //1. 创建一个 workbook 对象
//            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
//            //2. 获取 workbook 中表单的数量
//            int numberOfSheets = workbook.getNumberOfSheets();
//            for (int i = 0; i < numberOfSheets; i++) {
//                //3. 获取表单
//                HSSFSheet sheet = workbook.getSheetAt(i);
//                //4. 获取表单中的行数
//                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//                for (int j = 0; j < physicalNumberOfRows; j++) {
//                    //5. 跳过标题行
//                    if (j == 0) {
//                        continue;//跳过标题行
//                    }
//                    //6. 获取行
//                    HSSFRow row = sheet.getRow(j);
//                    if (row == null) {
//                        continue;//防止数据中间有空行
//                    }
//                    //7. 获取列数
//                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
//                    student = new Student();
//                    for (int k = 0; k < physicalNumberOfCells; k++) {
//                        HSSFCell cell = row.getCell(k);
//                        switch (cell.getCellType()) {
//                            case STRING:
//                                String cellValue = cell.getStringCellValue();
//                                switch (k) {
//                                    case 1:
//                                        student.setName(cellValue);
//                                        break;
//                                    case 2:
//                                        student.setWorkID(cellValue);
//                                        break;
//                                    case 3:
//                                        student.setGender(cellValue);
//                                        break;
//                                    case 5:
//                                        student.setIdCard(cellValue);
//                                        break;
//                                    case 6:
//                                        student.setWedlock(cellValue);
//                                        break;
//                                    case 7:
//                                        int nationIndex = allNations.indexOf(new Nation(cellValue));
//                                        student.setNationId(allNations.get(nationIndex).getId());
//                                        break;
//                                    case 8:
//                                        student.setNativePlace(cellValue);
//                                        break;
//                                    case 9:
//                                        int politicstatusIndex = allPolitics.indexOf(new Politics(cellValue));
//                                        student.setPoliticId(allPolitics.get(politicstatusIndex).getId());
//                                        break;
//                                    case 10:
//                                        student.setPhone(cellValue);
//                                        break;
//                                    case 11:
//                                        student.setAddress(cellValue);
//                                        break;
//                                    case 12:
//                                        int departmentIndex = allDepartments.indexOf(new Department(cellValue));
//                                        student.setDepartmentId(allDepartments.get(departmentIndex).getId());
//                                        break;
//                                    case 13:
//                                        int jobLevelIndex = allJobLevels.indexOf(new JobLevel(cellValue));
//                                        student.setJobLevelId(allJobLevels.get(jobLevelIndex).getId());
//                                        break;
//                                    case 14:
//                                        int positionIndex = allPositions.indexOf(new Position(cellValue));
//                                        student.setPosId(allPositions.get(positionIndex).getId());
//                                        break;
//                                    case 15:
//                                        student.setEngageForm(cellValue);
//                                        break;
//                                    case 16:
//                                        student.setTiptopDegree(cellValue);
//                                        break;
//                                    case 17:
//                                        student.setSpecialty(cellValue);
//                                        break;
//                                    case 18:
//                                        student.setSchool(cellValue);
//                                        break;
//                                    case 20:
//                                        student.setWorkState(cellValue);
//                                        break;
//                                    case 21:
//                                        student.setEmail(cellValue);
//                                        break;
//                                }
//                                break;
//                            default: {
//                                switch (k) {
//                                    case 4:
//                                        student.setBirthday(cell.getDateCellValue());
//                                        break;
//                                    case 19:
//                                        student.setBeginDate(cell.getDateCellValue());
//                                        break;
//                                    case 23:
//                                        student.setBeginContract(cell.getDateCellValue());
//                                        break;
//                                    case 24:
//                                        student.setEndContract(cell.getDateCellValue());
//                                        break;
//                                    case 22:
//                                        student.setContractTerm(cell.getNumericCellValue());
//                                        break;
//                                    case 25:
//                                        student.setConversionTime(cell.getDateCellValue());
//                                        break;
//                                }
//                            }
//                            break;
//                        }
//                    }
//                    list.add(student);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
}
