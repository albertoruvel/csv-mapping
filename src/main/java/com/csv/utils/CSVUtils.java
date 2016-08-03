package com.csv.utils;

import com.csv.data.CSVMapping;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author jose.rubalcaba
 */
public class CSVUtils {
    
    private static final Logger log = Logger.getAnonymousLogger(); 
    
    /**
     * Read a CSV file and return a CSVRecord Iterable object
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static Iterable<CSVRecord> readFile(FileReader fileReader)throws FileNotFoundException, IOException{ 
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader().withIgnoreSurroundingSpaces(true);
         
        return new CSVParser(fileReader, csvFileFormat);
    }

    /**
     * Set the field value depending on the class type 
     * (Using only generics, modify if another class is needed)
     * @param <T>
     * @param field
     * @param csvValue
     * @param instance
     * @throws IllegalAccessException 
     */
    private static <T> void setFieldValue(Field field, String csvValue, T instance) throws IllegalAccessException{
        if(field.getType() == Boolean.TYPE){
            field.setBoolean(instance, Boolean.parseBoolean(csvValue));
        }else if(field.getType() == String.class){
            field.set(instance, csvValue);
        }else if(field.getType() == Double.TYPE){
            field.setDouble(instance, Double.parseDouble(csvValue));
        }else if(field.getType() == Short.TYPE){
            field.setShort(instance, Short.parseShort(csvValue)); 
        }else if(field.getType() == Float.TYPE){
            field.setFloat(instance, Float.parseFloat(csvValue)); 
        }else if(field.getType() == Character.TYPE){
            field.setChar(instance, csvValue.charAt(0));
        }else if(field.getType() == Long.TYPE){
            field.setLong(instance, Long.parseLong(csvValue));
        }else if(field.getType() == Integer.TYPE){
            field.setInt(instance, Integer.parseInt(csvValue));
        }
    }

    /**
     * Read a CSV file and return a generic type list
     * @param <T>
     * @param mappings
     * @param file
     * @param aClass
     * @return
     * @throws Exception 
     */
    public static <T> List<T> readFile(List<CSVMapping> mappings, File file, Class<T> aClass) throws Exception{
        //read file 
        FileReader reader = new FileReader(file); 
        Iterable<CSVRecord> records = readFile(reader); 
        List<T> list = new ArrayList(); 
        T instance;
        Field field;
        String csvValue; 
        for(CSVRecord record : records){
            instance = aClass.newInstance(); 
            for(CSVMapping mapping : mappings){
                field = instance.getClass().getDeclaredField(mapping.getClassFieldName());
                //set field accessible 
                field.setAccessible(true);
                csvValue = record.get(mapping.getCsvFieldName()); 
                setFieldValue(field, csvValue, instance);
                field.setAccessible(false);
            }
            list.add(instance);
        }
        reader.close();
        return list; 
    }
}
