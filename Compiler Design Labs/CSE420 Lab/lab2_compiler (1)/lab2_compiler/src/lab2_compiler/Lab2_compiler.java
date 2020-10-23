/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2_compiler;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author R
 */
public class Lab2_compiler {

    static String[] variables;
    static String[] expressions;
    static double[] values;
    static double result = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

// TODO code application logic here
        Scanner key = new Scanner(System.in);
        System.out.println("Enter the number of Variables: ");
        int numbOfVariables = key.nextInt();
        System.out.println("Enter the Variables: ");
        values = new double[numbOfVariables];
        variables = new String[numbOfVariables];

        String[] ss = new String[numbOfVariables];   //put in temp ss , like a=5
        Scanner in1 = new Scanner(System.in);
        for (int a = 0; a < numbOfVariables; a++) {
            ss[a] = in1.nextLine();
        }

        System.out.println("Variables: ");
        for (int a = 0; a < numbOfVariables; a++) {     // from string SPLIT the value and keep in array
            String[] splited = ss[a].split(" ");
            values[a] = Double.parseDouble(splited[2]);
            variables[a] = splited[0];
            System.out.println(variables[a] + "  :  " + values[a]);
        }

        System.out.println("Enter the number of Expressions: ");
        int NumberOfexpressions = key.nextInt();
        // int NumberOfexpressions=1;
        expressions = new String[NumberOfexpressions];
        System.out.println("Enter the Expressions: ");

        //Get the expressions and convert the variables with it
        for (int a = 0; a < NumberOfexpressions; a++) {
            String temp = in1.nextLine();
            //String temp="a + [ { a + ( a * a ) } * 5 ] + ( a + a ) ";
            String[] splited = temp.split(" ");             //for split
            for (int i = 0; i < splited.length; i++) {              //expression's value
                for (int ii = 0; ii < numbOfVariables; ii++) {           //input value
                    if (splited[i].equals(variables[ii])) {
                        splited[i] = values[ii] + "";
                    }
                }
            }

            for (int i = 0; i < splited.length; i++) {            // made string to value
                try {
                    int t = expressions[a].length();
                    expressions[a] = expressions[a] + " " + splited[i];
                } catch (NullPointerException rr) {
                    expressions[a] = splited[i];
                }
            }
            //System.out.println(expressions[a]);

        }

        int[] lengths = new int[NumberOfexpressions];
        String[] results = new String[NumberOfexpressions];
        for (int counter = 0; counter < NumberOfexpressions; counter++) {
            int start = -1;
            int end = -1;
            boolean flag = false;

            String[] splited = expressions[counter].split(" ");            //break string into array
            lengths[counter] = splited.length;
            for (int i = 0; i < lengths[counter]; i++) {
                if (splited[i].equals("(")) {
                    start = i;
                    flag = true;
                }
                if (flag && splited[i].equals(")")) {
                    end = i;
                    flag = false;
                    //arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
                    //System.out.println("Start : "+start+"  end: "+end);
                    String[] temp = new String[end - start + 1];

                    System.arraycopy(splited, start, temp, 0, (end - start + 1));

                    String res = BODMAS(temp);

                    splited[start] = res;

                    for (int a = start + 1; a < splited.length - temp.length + 1; a++) //left shift
                    {
                        splited[a] = splited[a + (temp.length - 1)];
                    }
                    i = 0;
                    lengths[counter] -= (temp.length - 1);
                    continue;
                }

                if (splited[i].equals("{")) {
                    start = i;
                    flag = true;
                }
                if (flag && splited[i].equals("}")) {
                    end = i;
                    flag = false;
                    //arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
                    //System.out.println("Start : "+start+"  end: "+end);
                    String[] temp = new String[end - start + 1];

                    System.arraycopy(splited, start, temp, 0, (end - start + 1));

                    String res = BODMAS(temp);

                    splited[start] = res;

                    for (int a = start + 1; a < splited.length - temp.length + 1; a++) {
                        splited[a] = splited[a + (temp.length - 1)];
                    }
                    i = 0;
                    lengths[counter] -= (temp.length - 1);
                    continue;
                }

                if (splited[i].equals("[")) {
                    start = i;
                    flag = true;
                }
                if (flag && splited[i].equals("]")) {
                    end = i;
                    flag = false;
                    //arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
                    // System.out.println("Start : "+start+"  end: "+end);
                    String[] temp = new String[end - start + 1];

                    System.arraycopy(splited, start, temp, 0, (end - start + 1));

                    String res = BODMAS(temp);

                    splited[start] = res;

                    for (int a = start + 1; a < splited.length - temp.length + 1; a++) {
                        splited[a] = splited[a + (temp.length - 1)];
                    }
                    i = 0;
                    lengths[counter] -= (temp.length - 1);
                    continue;
                }

                String[] asd = new String[lengths[counter] + 2];

                //for(int r=0;r< lengths[counter] ;r++){System.out.print(r+"-->"+splited[r]+"   ");}
                for (int aa = 1; aa < asd.length - 1; aa++) {
                    asd[aa] = splited[aa - 1];
                }      //for bracket
                asd[0] = "(";
                asd[asd.length - 1] = ")";

                try {
                    results[counter] = BODMAS(asd);
                } catch (Exception r) {
                }
                // System.out.println(results[counter]);
            }

            //     System.out.println(lengths[0]);
            for (int i = 0; i < NumberOfexpressions; i++) {
                System.out.println("Expression [" + i + "] -->  " + results[i] + "   ");
            }

        }

    }

    static String BODMAS(String[] arr) {

        int length = arr.length;

        for (int i = 0; i < length; i++) {
            if (arr[i].equals("/")) {
                arr[i - 1] = (Double.parseDouble(arr[i - 1]) / Double.parseDouble(arr[i + 1])) + "";
                for (int a = i; a < length - 2; a++) {
                    arr[a] = arr[a + 2];
                }
                length = length - 2;
                i = 0;
            }
        }

        for (int i = 0; i < length; i++) {
            if (arr[i].equals("*")) {
                arr[i - 1] = (Double.parseDouble(arr[i - 1]) * Double.parseDouble(arr[i + 1])) + "";
                for (int a = i; a < length - 2; a++) {
                    arr[a] = arr[a + 2];
                }
                length = length - 2;
                i = 0;
            }
        }

        for (int i = 0; i < length; i++) {
            if (arr[i].equals("+")) {
                arr[i - 1] = (Double.parseDouble(arr[i - 1]) + Double.parseDouble(arr[i + 1])) + "";
                for (int a = i; a < length - 2; a++) {
                    arr[a] = arr[a + 2];
                }
                length = length - 2;
                i = 0;
            }
        }

        for (int i = 0; i < length; i++) {
            if (arr[i].equals("-")) {
                arr[i - 1] = (Double.parseDouble(arr[i - 1]) - Double.parseDouble(arr[i + 1])) + "";
                for (int a = i; a < length - 2; a++) {
                    arr[a] = arr[a + 2];
                }
                length = length - 2;
                i = 0;
            }
        }

        return arr[1];
    }
}
