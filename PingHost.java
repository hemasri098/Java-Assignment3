package com.company;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PingHost
{
    // method for finding the ping statics of website
    public static void commands(ArrayList<String> commandList) throws Exception
    {
        // creating the sub process, execute system command
        ProcessBuilder build = new ProcessBuilder(commandList);
        Process process = build.start();

        // to read the output
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader Error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String s = null;
        ArrayList<String> packets = new ArrayList<String>();
        ArrayList<Double> time = new ArrayList<Double>();
        while((s = input.readLine()) != null)
        {
            String [] pac = s.split(" ");
            if(pac[pac.length - 2].length() > 5)
            {
                time.add(Double.parseDouble(pac[pac.length - 2].substring(5)));
            }
            Collections.sort(time);
            if(time.size() > 0) {
                if (time.size() % 2 != 0)
                    System.out.println(time.get(time.size() / 2));
                else
                    System.out.println((time.get(time.size() / 2) + time.get(time.size() / 2 - 1)) / 2);
            }

        }

        while((s = Error.readLine()) != null)
        {
            System.out.println(s);
        }

    }

    public static void main(String args[]) throws Exception
    {
        // creating list for commands
        ArrayList<String> commandList = new ArrayList<String>();

        commandList.add("ping"); //command to ping ip address is added into list

        commandList.add("www.yahoo.com"); // host address

        commands(commandList);
    }
}