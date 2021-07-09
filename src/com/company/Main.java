package com.company;
/*
 Written By: Jessica Graham
 Date: July 8th, 2021
 Description: Simple Java program to read two levels of a directory and
 print to a txt. The idea is to create a high level table of contents.

 */

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) {
        folderList();
    }//Main Method

    //This Method will allow the user to select a folder
    public static String SelectFolder() {
        System.out.println("Choosing a file");
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);

        System.out.println("Selected Folder " + f.getSelectedFile());
        return String.valueOf(f.getSelectedFile());
    }//SelectFolder

    //Read the folder structure and print to a file
    public static void folderList() {
        //Get list of the folders
        String path = SelectFolder();
        File folder = new File(path);
        File[] subdirectory;
        File[] directories = new File(path).listFiles(File::isDirectory);

        //Create the files
        FileWriter doc = null;
        try {
            doc = new FileWriter(path + "/DirectoryHierarchy.txt");
            doc.write("Terabyte Hierarchy List");
            doc.write(String.format("%n"));

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            doc.write("" + timestamp);

            doc.write(String.format("%n"));
            doc.write(String.format("%n"));

            //Prints out the folders and their full path
            for (File f : directories) {
                System.out.printf("%-50s  %-50s%n", f.getName(), f);
                doc.write(String.format("%-50s  %-50s%n", f.getName(), f));

                path = f.getPath();//Move to next path
                subdirectory = new File(path).listFiles(File::isDirectory);


                //Next we will need a for loop to get the directories next list.
                //This is hardcoding and is not dynamic.
                for (File sf : subdirectory) {
                    System.out.printf("%-50s  %-50s%n", "\t" + sf.getName(), sf);
                    doc.write(String.format("%-50s  %-50s%n", "\t" + sf.getName(), sf));

                }
            }

            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}//End Main class


