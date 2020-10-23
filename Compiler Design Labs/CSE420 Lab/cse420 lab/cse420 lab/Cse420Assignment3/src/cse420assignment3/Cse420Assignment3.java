/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse420assignment3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*; 
/**
 *
 * @author 12201027
 */
public class Cse420Assignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        FileReader in=null;
        try {
            in = new FileReader("input.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cse420Assignment3.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br= new BufferedReader(in);

        ArrayList<Pattern> pattern=new ArrayList();
        
        int n = Integer.parseInt(br.readLine());
        while(n>0){
            n--;
            String str=br.readLine();
            Pattern p=Pattern.compile(str);
            pattern.add(p);
        }
        
        int m = Integer.parseInt(br.readLine());
        while(m>0){
            m--;
            String str=br.readLine();
            boolean b=false;
            for (int i=0; i<pattern.size(); i++) {
                Matcher ma=pattern.get(i).matcher(str);
                if(ma.matches()){
                    b=true;
                    System.out.println("Yes, "+ ++i );
                    break;
                }
            }
            if(!b) System.out.println("No, 0");
        }
    }
}
