/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.command_shell;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author IBN SAEED
 */
public class Command_Shell {
    
    public static void main(String[] args) {
        Command_Shell object = new Command_Shell();
        object.waitMethod();
        
    }
       private synchronized void waitMethod() {
 
	while (true) { 
            Scanner userInput= new Scanner(System.in);
            String command = "";

            var homeDir = System.getProperty("user.dir");
            System.out.println(homeDir+"/$ "); 
            command=userInput.nextLine();

            System.err.println("\b");
            ProcessBuilder processBuilder = new ProcessBuilder().command("cmd", "/C", command);

            try {
                Process process = processBuilder.start();

                //read the output
                InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String output = null;
                while ((output = bufferedReader.readLine()) != null) {
                    System.out.println(output);
                }

                //wait for the process to complete
                process.waitFor();

                //close the resources
                bufferedReader.close();
                process.destroy();

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
       }
    }

