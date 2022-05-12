package pl.coderslab.workshop2.entity;

import java.util.Scanner;

public class SPOJ_test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum=0;
        while(scanner.hasNextInt()){
            int x=scanner.nextInt();
            sum+=x;
            System.out.println(sum);
        }
    }
}
