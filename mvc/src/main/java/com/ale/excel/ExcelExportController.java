package com.ale.excel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.alibaba.excel.EasyExcelFactory.write;

/**
 * @author alewu
 * @date 2020/10/13 10:53
 */
@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelExportController {

    @PostMapping("/export")
    public void export(@RequestBody List<Menu> menus, HttpServletResponse response) {
        //需要合并的列
        //        int[] mergeColumnIndex = {0,1,2,3,4,5,8,9,11};
        int[] mergeColumnIndex = {0, 1};
        // 从那一列开始合并
        int mergeRowIndex = 0;
        write(getOutputStream("test", response))
                .sheet("SheetName")
                //设置合并单元格策略
                .registerWriteHandler(new ExcelFillCellMergeStrategy(mergeRowIndex,
                                                                     mergeColumnIndex))
                .doWrite(menus);

    }

    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        //创建本地文件
        String filePath = fileName + ".xlsx";
        File dbfFile = new File(filePath);
        try {
            if (!dbfFile.exists() || dbfFile.isDirectory()) {
                dbfFile.createNewFile();
            }
            fileName = new String(filePath.getBytes(), StandardCharsets.ISO_8859_1);
            response.reset();
            response.setContentType("octets/stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            return response.getOutputStream();
        } catch (IOException e) {
            log.error("failed", e);
        }
        return null;
    }
}
