/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse420assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author 12201027
 */
public class Cse420Assignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileReader in = new FileReader("input.txt");
        BufferedReader br = new BufferedReader(in);
        
        int n = Integer.parseInt(br.readLine());
        Map<String,Integer> idVal= new HashMap<>();
        ArrayList<String> id=new ArrayList();
        while(n>0){
            n--;
            String line = br.readLine();
            String[] tok= line.split(" ");
            id.add(tok[0]);
            idVal.put(tok[0], Integer.parseInt(tok[2]));
        }
        int m = Integer.parseInt(br.readLine());
        while(m>0){
            m--;
            String line = br.readLine();
            String[] tok= line.split(" ");
            boolean flag=false;
            String postFix="";
            int x , y;
            for (int i = 0; i < tok.length; i+=2) {
                if(!(id.contains(tok[i]))){
                    System.out.println("Compilation Error!");
                    flag=true;
                    break;
                }
            }
            if(!flag){
                //making postfix
                Stack<String> st= new Stack();
                for (int i = 0; i < tok.length; i++) {
                    if((tok[i].equals("+")) || (tok[i].equals("-")) || (tok[i].equals("x")) || (tok[i].equals("/"))){
                        //System.out.println(tok[i]);
                        if(st.isEmpty()){
                            st.push(tok[i]);
                        }
                        else{
                            while(!st.isEmpty()){
                                if(((tok[i].equals("x")) || (tok[i].equals("/"))) && ((st.peek().equals("+")) || (st.peek().equals("-")))){
                                    st.push(tok[i]);
                                    break;
                                }
                                else{
                                    postFix+=st.pop()+" ";
                                }
                            }
                            if(st.isEmpty()){
                                st.push(tok[i]);
                            }
                        }
                    }
                    else{
                        postFix+=tok[i]+" ";
                    }
                }
                while(!st.isEmpty()){
                    postFix+=st.pop()+" ";
                }
                //System.out.println(postFix);
                
                //calculating postfix eqn
                String[] postFixArray=postFix.split(" ");
                Stack<Integer> steqn= new Stack();
                for (int i = 0; i < postFixArray.length; i++) {
                    if((postFixArray[i].equals("+")) || (postFixArray[i].equals("-")) || (postFixArray[i].equals("x")) || (postFixArray[i].equals("/"))){
                        
                        y=steqn.pop();
                        x=steqn.pop();
                        if(postFixArray[i].equals("+")){
                           
                            steqn.push(x+y);
                        }
                        else if(postFixArray[i].equals("-")){
                            
                            steqn.push(x-y);
                        }
                        else if(postFixArray[i].equals("x")){
                            
                            steqn.push(x*y);
                        }
                        else if(postFixArray[i].equals("/")){
                            
                            steqn.push(x/y);
                        }
                    }
                    else{
                        
                        steqn.push(idVal.get(postFixArray[i]));
                    }
                }
                System.out.println(steqn.peek());
            }
        }
    }
    
}
