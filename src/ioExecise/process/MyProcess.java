package ioExecise.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyProcess {
    public static String command(String command) {
        StringBuilder sb = new StringBuilder();
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            try {
                while((s = reader.readLine()) != null) {
                    sb.append(s);
                    sb.append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(command("git --version"));
    }
}
