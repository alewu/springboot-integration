package com.ale.excel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;
import java.util.Objects;

/**
 * The type Excel fill cell merge strategy.
 *
 * @author alewu
 * @date 2020 /10/13
 */
public class ExcelFillCellMergeStrategy implements CellWriteHandler {
    private final int[] mergeColumnIndex;
    private final int mergeRowIndex;

    /**
     * Instantiates a new Excel fill cell merge strategy.
     */
    public ExcelFillCellMergeStrategy(int mergeRowIndex, int[] mergeColumnIndex) {
        this.mergeRowIndex = mergeRowIndex;
        this.mergeColumnIndex = mergeColumnIndex;
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                                 Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
                                Head head, Integer integer, Boolean aBoolean) {

    }

//    @Override
//    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
//                                       CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

//    @Override
//    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
//                                 List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
//        int curRowIndex = cell.getRowIndex();
//        int curColIndex = cell.getColumnIndex();
//        if (curRowIndex > mergeRowIndex) {
//            for (int columnIndex : mergeColumnIndex) {
//                if (curColIndex == columnIndex) {
//                    mergeWithPrevRow(writeSheetHolder, cell, curRowIndex, curColIndex);
//                    break;
//                }
//            }
//        }
//    }

    /**
     * 当前单元格向上合并
     *
     * @param cell        当前单元格
     * @param curRowIndex 当前行
     * @param curColIndex 当前列
     */
    private void mergeWithPrevRow(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex) {
        Object curData = cell.getCellTypeEnum() == CellType.STRING ? cell.getStringCellValue() :
                cell.getNumericCellValue();
        Cell preCell = cell.getSheet().getRow(curRowIndex - 1).getCell(curColIndex);
        Object preData = preCell.getCellTypeEnum() == CellType.STRING ? preCell.getStringCellValue() :
                preCell.getNumericCellValue();
        // 将当前单元格数据与上一个单元格数据比较
        Boolean dataBool = preData.equals(curData);
        //此处需要注意：因为我是按照序号确定是否需要合并的，所以获取每一行第一列数据和上一行第一列数据进行比较，如果相等合并
        Boolean bool = Objects.equals(cell.getRow().getCell(0).getStringCellValue(),
                                      cell.getSheet().getRow(curRowIndex - 1).getCell(0).getStringCellValue());
        if (dataBool && bool) {
            Sheet sheet = writeSheetHolder.getSheet();
            List<CellRangeAddress> mergeRegions = sheet.getMergedRegions();
            boolean isMerged = false;
            for (int i = 0; i < mergeRegions.size() && !isMerged; i++) {
                CellRangeAddress cellRangeAddr = mergeRegions.get(i);
                // 若上一个单元格已经被合并，则先移出原有的合并单元，再重新添加合并单元
                if (cellRangeAddr.isInRange(curRowIndex - 1, curColIndex)) {
                    sheet.removeMergedRegion(i);
                    cellRangeAddr.setLastRow(curRowIndex);
                    sheet.addMergedRegion(cellRangeAddr);
                    isMerged = true;
                }
            }
            // 若上一个单元格未被合并，则新增合并单元
            if (!isMerged) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(curRowIndex - 1, curRowIndex, curColIndex,
                                                                         curColIndex);
                sheet.addMergedRegion(cellRangeAddress);
            }
        }
    }
}
