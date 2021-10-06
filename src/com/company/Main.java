package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;


class WordLength {
    public static void main(String... args) throws IOException {

        //here will be written all text from reading file
        String text = "";

        //filepath reading
        String filePath = FilePath();

        //file context reading
        text = FileReading(text, filePath);

        //check that all words` length less than
        text = WordSubstring(text);

        //the longest world
        String longest = TheLongestWorld(text);
        System.out.print(longest);

    }

    public static String FilePath(){
        Scanner userPathInput = new Scanner(System.in);
        System.out.println("Enter path for the input file: ");
        String filePath = userPathInput.nextLine();
        userPathInput.close();
        return filePath;
    }

    public static String FileReading(String text, String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            text = sb.toString();
        } catch (IOException| NoSuchElementException |IllegalStateException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return text;
    }

    public static String TheLongestWorld(String text) {
        return Arrays.stream(text.split("[^a-zA-Z0-9]")).max(Comparator.comparingInt(String::length)).orElse(null);
    }

    public static String WordSubstring(String text){
        StringBuilder new_text = new StringBuilder();
        for (String word : text.split("[^a-zA-Z0-9]")) {
            word = word.toLowerCase();
            if (word.length() > 30) {
                word = word.substring(0, 30);
            }
            new_text.append(word + " ");
        }
        return new_text.toString();
    }
}