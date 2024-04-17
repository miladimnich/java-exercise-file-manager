package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Please choose a mode! 1: create file, 2: read file, 3: write into file, 4: delete file ");
        System.out.print(" > ");
        String mode = in.nextLine();

        System.out.println("Please enter a filename!");
        System.out.print(" > ");
        String filename = in.nextLine();

        // Path path = Paths.get("files/test.txt");
        // Do something with the file


        System.out.printf("finished with mode: " +mode + ": " + filename);
    }
}