package org.example;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author haoren
 * @date 2024/05/25 12:37
 **/
@Getter
@Setter
@EqualsAndHashCode
public class DemoData {
    @ExcelProperty("test1")
    private String string;
    @ExcelProperty(index = 1)
    private Date date;
    @ExcelProperty("test3")
    private Integer doubleData;
}
