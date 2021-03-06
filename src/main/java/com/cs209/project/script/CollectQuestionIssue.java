package com.cs209.project.script;

import java.io.*;

public class CollectQuestionIssue {
    public static void main(String[]args) throws IOException {
        File f = new File("src/test/java/com/cs209/project/file/SpringBootStackoverflowQuestionDetail.txt");
        String s;
        BufferedReader br = new BufferedReader(new FileReader(f));
        File ff = new File("src/test/java/com/cs209/project/file/SpringBootStackoverflowQuestionZ.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(ff));
        String last = "";
        while((s = br.readLine())!=null){
            String parts = s.split("\t")[0];
            if(!last.equals(parts)){
                bw.write(parts);
                bw.write("\n");
            }
            last = parts;
        }
        bw.close();
    }
}
