package com.csv.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVRecord;

/**
 * Maps a CSV column ti a class field, depending on the field class
 * @author jose.rubalcaba
 */
public class CSVMapping {
    
    private String csvFieldName; 
    private String classFieldName; 
    private Class<?> fieldType; 
    
    public CSVMapping() {
    }

    public CSVMapping(String csvFieldName, String classFieldName, Class<?> fieldType) {
        this.csvFieldName = csvFieldName;
        this.classFieldName = classFieldName;
        this.fieldType = fieldType;
    }

    
    public String getCsvFieldName() {
        return csvFieldName;
    }

    public void setCsvFieldName(String csvFieldName) {
        this.csvFieldName = csvFieldName;
    }

    public String getClassFieldName() {
        return classFieldName;
    }

    public void setClassFieldName(String classFieldName) {
        this.classFieldName = classFieldName;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }
    
    
}
