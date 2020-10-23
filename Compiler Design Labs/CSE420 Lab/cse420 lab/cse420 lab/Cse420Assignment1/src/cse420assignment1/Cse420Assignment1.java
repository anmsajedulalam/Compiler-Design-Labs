package cse420assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12201027
 */
public class Cse420Assignment1 {
    
    public static void main(String[] args) throws IOException {
        
        //storing keywords in an arrays
        String[] keywords={"break" ,"else", "long","switch","case","enum","register","typedef"
                ,"char","extern","return","union","const","float","short","unsigned","continue",
                "for","signed","void","default","goto","sizeof","volatile","do","if","static",
                "while","auto",	"double","int","struct"};
        String[] mathOp={"+" , "-" ,"/", "*", "%", "="};
        String[] logicalOp={">", "<", ">=", "<=", "=="};
        //end to storing keywors in array//
        
        //print items in arrayList
        ArrayList<String> keywordsOutPut=new ArrayList<>();
        ArrayList<String> mathOpOutPut=new ArrayList<>();
        ArrayList<String> logicalOpOutPut=new ArrayList<>();
        ArrayList<String> identifierOutPut=new ArrayList<>();
        ArrayList<String> numericalOutPut=new ArrayList<>();
        ArrayList<String> otherOutPut=new ArrayList<>();
        //-end of print item arrayList//
        
        //taking input as a file
        FileReader in=null;
        try {
            in = new FileReader("input.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cse420Assignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br=new BufferedReader(in);
        String line;
        while((line = br.readLine())!=null){
            
            //generating tokens
            String[] spaceRmv = line.split(" ");
            for (int i = 0; i < spaceRmv.length; i++) {
                spaceRmv[i]=spaceRmv[i].trim();
            //    System.out.println(spaceRmv[i]);
            }
          //  System.out.println("");
            
            ArrayList<String> tokens=new ArrayList<>();
            for (int i = 0; i < spaceRmv.length; i++) {
                if(spaceRmv[i].contains(",")){
                    String[] tmp= spaceRmv[i].split(",");
                    tokens.add(tmp[0]);
                    tokens.add(",");
                }
                else if(spaceRmv[i].contains(";")){
                    String[] tmp= spaceRmv[i].split(";");
                    tokens.add(tmp[0]);
                    tokens.add(";");
                    
                }
                else{
                    tokens.add(spaceRmv[i]);
                }
            }
            
//            for (int i = 0; i < tokens.size(); i++) {
//                System.out.println(tokens.get(i));
//            }
//            System.out.println("");
            //---token generation done-//
            
            //storing in different arrayList
            for (int i = 0; i < tokens.size(); i++) {
                if(Arrays.asList(keywords).contains(tokens.get(i))){
                    if(!(keywordsOutPut.contains(tokens.get(i)))){
                        keywordsOutPut.add(tokens.get(i));
                    
                    }
                }
                else if(Arrays.asList(mathOp).contains(tokens.get(i))){
                    if(!(mathOpOutPut.contains(tokens.get(i)))){
                        mathOpOutPut.add(tokens.get(i));
                    }
                }
                else if(Arrays.asList(logicalOp).contains(tokens.get(i))){
                    if(!(logicalOpOutPut.contains(tokens.get(i)))){
                        logicalOpOutPut.add(tokens.get(i));
                    }
                }
                else if(Character.isLetter(tokens.get(i).charAt(0))){
                    if(!(identifierOutPut.contains(tokens.get(i)))){
                        identifierOutPut.add(tokens.get(i));
                    }
                }
                else if(Character.isDigit(tokens.get(i).charAt(0))){
                    if(!(numericalOutPut.contains(tokens.get(i)))){
                        numericalOutPut.add(tokens.get(i));
                    }
                }
                else{
                    if(!(otherOutPut.contains(tokens.get(i)))){
                        otherOutPut.add(tokens.get(i));
                    }
                }
            }
            //--storing in arrayList done-///
        }
        //-----readLine ended(intput file reading done----//
        
         //printing different items
            //keywords
            System.out.print("keywords: "+keywordsOutPut.get(0) );
            for (int i = 1; i < keywordsOutPut.size(); i++) {
                System.out.print(", "+keywordsOutPut.get(i));
            }
            System.out.println("");
            
            //identifiers
            System.out.print("identifiers: "+identifierOutPut.get(0) );
            for (int i = 1; i < identifierOutPut.size(); i++) {
                System.out.print(", "+identifierOutPut.get(i));
            }
            System.out.println("");
            
            //mathOperators
            System.out.print("math operations: "+mathOpOutPut.get(0) );
            for (int i = 1; i < mathOpOutPut.size(); i++) {
                System.out.print(", "+mathOpOutPut.get(i));
            }
            System.out.println("");
            
            //logicalOperators
            System.out.print("logical operators: "+logicalOpOutPut.get(0) );
            for (int i = 1; i < logicalOpOutPut.size(); i++) {
                System.out.print(", "+logicalOpOutPut.get(i));
            }
            System.out.println("");
            
            //numerical values
            System.out.print("numerical values: "+numericalOutPut.get(0) );
            for (int i = 1; i < numericalOutPut.size(); i++) {
                System.out.print(", "+numericalOutPut.get(i));
            }
            System.out.println("");
            
            //others
            System.out.print("others:");
            for (int i = 0; i < otherOutPut.size(); i++) {
                System.out.print(" "+otherOutPut.get(i));
            }
            System.out.println("");
            
         //---printing items done....??
    }
    
}
