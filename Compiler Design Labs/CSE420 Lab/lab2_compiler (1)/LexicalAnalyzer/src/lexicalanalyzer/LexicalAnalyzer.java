//package lexicalanalyzer;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class LexicalAnalyzer {

 public static void main(String[] args) {

  File file = new File("test.txt");
  
  try{

   Scanner input = new Scanner(file);
   
   ArrayList keyword = new ArrayList();
   ArrayList mathOperater = new ArrayList();
   ArrayList logicalOperater = new ArrayList();
   ArrayList other = new ArrayList();
   
   keyword.add("int");
   keyword.add("float");
   keyword.add("if");
   keyword.add("else");
   
   mathOperater.add("+");
   mathOperater.add("-");
   mathOperater.add("=");

   logicalOperater.add(">");

   other.add("(");
   other.add(")");
   other.add("{");
   other.add("}");
   other.add(",");
   other.add(";");
   
   ArrayList keywords = new ArrayList();
   ArrayList mathOperators = new ArrayList();
   ArrayList logicalOperators = new ArrayList();
   ArrayList identifiers = new ArrayList();
   ArrayList numericalValues = new ArrayList();
   ArrayList others = new ArrayList();

   while(input.hasNext()){

    String str = input.next();
    
    if(str.indexOf(';')!=-1){
                                    if(!(others.contains(";")))
                                    others.add(";");
     str = str.replace(";","");
                                    
    }

    boolean intCheck =  intChecker(str);
    boolean floatCheck =  floatChecker(str);

    if(intCheck == true || floatCheck == true){
     
     numericalValues.add(str);
    }
    
    else if(keyword.contains(str)){
     
     keywords.add(str); 
    }

    else if(mathOperater.contains(str)){
                                     if(!(mathOperators.contains(str)))
     mathOperators.add(str); 
    }

    else if(logicalOperater.contains(str)){
                                    if(!(logicalOperators.contains(str)))
     logicalOperators.add(str);
    }

    else if(str.equals("(")||str.equals(")")||str.equals("{")||str.equals("}")||str.equals("[")||str.equals("]")){
                                    if(!(others.contains(str)))
     others.add(str);
    }

    else {
     
     if(str.indexOf(',')!= -1)
                                            if(!(others.contains(",")))
      others.add(",");
     String[] parts = str.split(",");
     for(int i=0; i<parts.length; i++){
                                            if(!(identifiers.contains(parts[i])))
      identifiers.add(parts[i]);
     }
     
    }
   }
                        System.out.print("keywords: ");
   for(int i = 0; i < keywords.size(); i++) {   
                        System.out.print(keywords.get(i)+" ");
                        }
   System.out.println();
   System.out.print("Math Operators: ");

   for(int i = 0; i < mathOperators.size(); i++) {   
                        System.out.print(mathOperators.get(i)+" ");
                        }
                        

   System.out.println();
   System.out.print("Logical Operators: ");

   for(Object str: logicalOperators){

    System.out.print(str.toString()+" ");
   }

   System.out.println();
   System.out.print("Identifiers: ");

   for(Object str: identifiers){

    System.out.print(str.toString()+" ");
   }

   System.out.println();
   System.out.print("Numerical values: ");

   for(Object str: numericalValues){

    System.out.print(str.toString()+" ");
   }

   System.out.println();
   System.out.print("Others: ");

   for(Object str: others){

    System.out.print(str.toString()+" ");
   }
                        System.out.println();

  } catch(Exception e){
   System.out.println(e.toString());
  }
 }

 
 
 public static boolean intChecker(String string){
  try{
   Integer.parseInt(string);
   return true;
   
  } catch (Exception e){
   return false;
  }
 }


 public static boolean floatChecker(String string){
  try{
   Double.parseDouble(string);
   return true;

  } catch (Exception e){
   return false;
  }
 }

}
