package com.example.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUltils {
    public static String loadTextFile(final String filename) throws IOException{
        long time = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;

        while((line = br.readLine()) != null){
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        time = System.currentTimeMillis() - time;

        System.out.println("Read file: " + filename + " in " + time + "ms");
        return sb.toString();
    }
    
}
