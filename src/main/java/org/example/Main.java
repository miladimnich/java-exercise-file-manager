package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

  private static String filename = "";
  private static String mode = "";

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    boolean exitProgram = false;
    try {
      while (!exitProgram) {
        try {
          header(in);
          switch (mode) {
            case "1":
              exitProgram = mode1(in);
              break;
            case "2":
              mode2(in);
              break;
            case "3":
              exitProgram = mode3(in);
              break;
            case "4":
              mode4(in);
              break;
            default:
              System.out.println("Invalid mode. Please choose a valid mode.");
          }
        } catch (IOException e) {
          System.out.println("An error occurred: " + e.getMessage());
        }
      }
    } finally {
      in.close();
    }
  }

  public static void header(Scanner input) {
    System.out.println(
        "Please choose a mode! 1: create file, 2: read file, 3: write into file, 4: delete file ");
    System.out.print(" > ");
    mode = input.nextLine();
  }

  public static boolean mode1(Scanner input) throws IOException {
    System.out.println("Please enter a filename!");
    System.out.print(" > ");
    filename = input.nextLine();

    while (true) {
      Path path = Paths.get(filename);
      if (Files.notExists(path)) {
        Files.createFile(path);
        System.out.println("File is successfully created");
        return true;
      } else {
        System.out.println("File \"" + path + "\" already exists. Enter a different filename:");
        filename = input.nextLine();
      }
    }
  }


  public static void mode2(Scanner input) throws IOException {
    System.out.println("Please enter the filename you want to view:");
    System.out.print(" > ");
    filename = input.nextLine();
    Path path = Paths.get(filename);
    try {
      System.out.println(Files.readString(path));
      System.exit(0);
    } catch (IOException e) {
      System.out.println("File \"" + filename + "\" does not exist.");
    }
  }

  public static boolean mode3(Scanner input) throws IOException {
    System.out.println("Please enter the name of the file you want to edit:");
    System.out.print(" > ");
    filename = input.nextLine();
    Path path = Paths.get(filename);

    if (Files.exists(path)) {
      try (BufferedWriter writer = Files.newBufferedWriter(
          path); BufferedReader userInputReader = new BufferedReader(
          new InputStreamReader(System.in))) {
        while (true) {
          System.out.print(
              "Enter the new line of text to add to the file (press 0 to finish):");
          String in = userInputReader.readLine();
          if (in.equals("0")) {
            System.out.println("New lines of text have been added to the file.");
            return true;
          }
          writer.write(in + System.lineSeparator());
        }
      }
    } else {
      System.out.println("File \"" + filename + "\" does not exist.");
    }
    return false;

  }

  public static void mode4(Scanner input) throws IOException {
    System.out.println("Please enter the name of the file you want to delete:");
    System.out.print(" > ");
    filename = input.nextLine();
    Path path = Paths.get(filename);

    if (Files.exists(path)) {
      Files.delete(path);
      System.out.println("The file is deleted");
      System.exit(0);
    } else {
      System.out.println("File \"" + filename + "\" does not exist.");
    }
  }
}