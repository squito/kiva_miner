package com.imranrashid.kiva_json_beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoanFile {
    //ignore header for now
    
    public ArrayList<LoanBean> loans;
    
    public static List<LoanBean> loadFile(File file) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(file, LoanFile.class).loans;
    }
    
    public static Iterator<LoanBean> loadDir(File dir) throws IOException {
        final File[] files = dir.listFiles();
        return new Iterator<LoanBean>(){
            Iterator<File> fileItr = Arrays.asList(files).iterator();
            Iterator<LoanBean> innerItr = loadFile(fileItr.next()).iterator();

            @Override
            public boolean hasNext() {
                while(!innerItr.hasNext() && fileItr.hasNext()) {
                    try {
                        File nextFile = fileItr.next();
                        System.out.println("opening file:" + nextFile);
                        innerItr = loadFile(nextFile).iterator();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return innerItr.hasNext();
            }

            @Override
            public LoanBean next() {
                hasNext();
                return innerItr.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        };
    }
}
