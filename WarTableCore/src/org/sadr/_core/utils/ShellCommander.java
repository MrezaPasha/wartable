/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author MSD
 */
public class ShellCommander {

    public static String exec(String command) {
        StringBuilder output = new StringBuilder();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader
                = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        OutLog.pl(output.toString());
        return output.toString();
    }

    public static String command_top() {
        ProcessBuilder pb = new ProcessBuilder("top", "-l", "1");
        pb.redirectError();
        try {
            Process p = pb.start();
            InputStream is = p.getInputStream();
            int value = -1;
            while ((value = is.read()) != -1) {
                OutLog.p(((char) value));
            }
            int exitCode = p.waitFor();
            OutLog.pl("Top exited with " + exitCode);
        } catch (IOException exp) {
            exp.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static void dropschema() {

    }

    public static String postgres(String backupDirectory, String databaseName, String databasePassword, String backupFileName, String type) {

        File backupFilePath = null;
        if (type != "drop" && type != "create") {
            OutLog.pl(backupDirectory);
            backupFilePath = new File(backupDirectory);

            if (!backupFilePath.exists()) {
                File dir = backupFilePath;
                dir.mkdirs();
            }
        }
        List<String> commands = getPgCommands(databaseName, backupFilePath, backupFileName, type);
        if (!commands.isEmpty()) {
            try {
                ProcessBuilder pb = new ProcessBuilder(commands);
                pb.environment().put("PGPASSWORD", databasePassword);
                Process process = pb.start();

                try (BufferedReader buf = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
                    String line = buf.readLine();
                    while (line != null) {
                        System.err.println(line);
                        line = buf.readLine();
                    }
                }
                process.waitFor();
                process.destroy();

                System.out.println("===> Success on " + type + " process.");
                return backupFilePath + "/" + backupFileName;
            } catch (IOException | InterruptedException ex) {
                System.out.println("Exception: " + ex);
                return null;
            }
        } else {

            System.out.println("Error: Invalid params.");
            return null;
        }
    }

    private static List<String> getPgCommands(String databaseName, File backupFilePath, String backupFileName, String type) {

        ArrayList<String> commands = new ArrayList<>();
        switch (type) {
            case "backup":
                commands.add("pg_dump");
                commands.add("-h"); //database server host
                commands.add("localhost");
                commands.add("-p"); //database server port number
                commands.add("5432");
                commands.add("-U"); //connect as specified database user
                commands.add("postgres");
                commands.add("-F"); //output file format (custom, directory, tar, plain text (default))
                commands.add("c");
                commands.add("-b"); //include large objects in dump
                commands.add("-v"); //verbose mode
                commands.add("-f"); //output file or directory name
                commands.add(backupFilePath.getAbsolutePath() + File.separator + backupFileName);
                commands.add("-d"); //database name
                commands.add(databaseName);
                break;
            case "restore":
                commands.add("pg_restore");
                commands.add("-h");
                commands.add("localhost");
                commands.add("-p");
                commands.add("5432");
                commands.add("-U");
                commands.add("postgres");
                commands.add("-d");
                commands.add(databaseName);
                commands.add("-v");
                commands.add(backupFilePath.getAbsolutePath() + File.separator + backupFileName);
                break;
            case "drop":
                commands.add("psql");
                commands.add("-h");
                commands.add("localhost");
                commands.add("-p");
                commands.add("5432");
                commands.add("-U");
                commands.add("postgres");
                commands.add("-d");
                commands.add(databaseName);
                commands.add("-c");
                commands.add("drop schema public cascade");
            case "create":
                commands.add("psql");
                commands.add("-h");
                commands.add("localhost");
                commands.add("-p");
                commands.add("5432");
                commands.add("-U");
                commands.add("postgres");
                commands.add("-d");
                commands.add(databaseName);
                commands.add("-c");
                commands.add("create schema public");

//                commands.add("-v");
//                commands.add(backupFilePath.getAbsolutePath() + File.separator + backupFileName);
                break;
            default:
                return Collections.EMPTY_LIST;
        }
        return commands;
    }
}
