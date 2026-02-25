package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // print the file
    public static void printAll(BufferedReader in) throws IOException{
        while (in.readLine() != null){
            System.out.println(in.readLine());
        }
    }

    public static void printColumn (int colnum , BufferedReader in) throws IOException {
        int i = colnum;
        while(in.readLine() != null){
            String s = in.readLine();
            int j = 0;
            while(i != 0 && j < s.length()){
                if(s.charAt(i) == ','){
                    i--;
                }
                j++;
            }
            while(j != 0 && j < s.length()){
                if(s.charAt(j) == ','){
                    j = 0;
                }
                System.out.println(s.charAt(j));
                j++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("testset.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // first line category
        String s = in.readLine();

        // filter the category
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int cnt = -1;
        Map<Integer , String > mp = new LinkedHashMap<>();
        while(i < s.length()){
            if(s.charAt(i) == ','){
                mp.put(++cnt , sb.toString() );
                sb.delete(0 , sb.length());
            }else {
                sb.append(s.charAt(i));
            }
            i++;
        }
        System.out.println(mp);

        mp.forEach((x , y) -> {
            System.out.println("-> "+x +" to print column "+y);
        });

        while(true){
            System.out.println("Enter the topic number to filter and display or enter -1 to show all:");
            int choose = sc.nextInt();
            // check choose
            if(!(choose >= -1 && choose < cnt)){
                System.out.println("Enter valid choice ....");
                continue;
            }
            // to print needed
            if (choose == -1) {
                printAll(in);
            } else {
                printColumn(choose, in);
            }
        }


    }
}