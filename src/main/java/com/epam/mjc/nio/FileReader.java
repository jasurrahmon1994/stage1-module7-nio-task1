package com.epam.mjc.nio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


public class FileReader {
    public static void main(String[] args) {
        FileReader fr = new FileReader();
        File file = new File("src/main/resources/Profile.txt");
        System.out.println(fr.getDataFromFile(file));
    }

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        Path path = file.toPath();
        String lines;
        try(InputStream inputStream = Files.newInputStream(file.toPath());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            lines = Files.readAllLines(path).toString();
        } catch (IOException e) {
            throw new SomeException("Something happened");
        }
        String[] arr = lines.substring(1, lines.length() - 1).split(", ");
        for (int i = 0; i < arr.length; i++) {
            String[] str = arr[i].split(": ");
            switch (str[0]) {
                case "Name":
                    profile.setName(str[1]);
                    break;
                case "Age":
                    profile.setAge(Integer.parseInt(str[1]));
                    break;
                case "Email":
                    profile.setEmail(str[1]);
                    break;
                case "Phone":
                    profile.setPhone(Long.parseLong(str[1]));
                    break;
                default:
                    System.out.println("false");
                    break;
            }
        }
        return profile;
    }
}
