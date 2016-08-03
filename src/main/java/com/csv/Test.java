package com.csv;

import com.csv.data.CSVMapping;
import com.csv.utils.CSVUtils;
import com.csv.data.Person;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author jose.rubalcaba
 */
public class Test {
    public static void main(String[] args) throws Exception{       
        List<CSVMapping> mappings = new ArrayList(); 
        mappings.add(new CSVMapping("Effective Name", "name", String.class)); 
        mappings.add(new CSVMapping("Effective Age", "age", Integer.class)); 
        mappings.add(new CSVMapping("Effective Address", "address", String.class)); 
        
        List<Person> persons = CSVUtils.readFile(mappings, new File("csv-test.csv"), Person.class); 
        for (Person person : persons) 
            Logger.getAnonymousLogger().info(person.toString()); 
    }
}
