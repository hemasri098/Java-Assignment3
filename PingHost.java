package com.company.JavaAssignment3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

public class PingHost
{
    //to find median of the time taken for the packets
    public static void findMedian(ArrayList<String> commandList) throws Exception {
        Logger LOGGER = Logger.getAnonymousLogger();
        ArrayList<BufferedReader> packets = readPackets(commandList);
        BufferedReader input = packets.get(0);
        BufferedReader error = packets.get(1);
        String packet = null;
        // to store packets time
        ArrayList<Double> times = new ArrayList<Double>();
        try {
            while((packet = input.readLine()) != null)
            {

                String [] line = packet.split(" ");
                //seperating time from each line
                if(line[line.length - 2].length() > 5)
                {
                    times.add(Double.parseDouble(line[line.length - 2].substring(5)));
                }
                Collections.sort(times);
                //finding median
                if(times.size() > 0) {
                    //if times are of even length
                    if (times.size() % 2 != 0) {
                        double median = times.size() / 2;
                        LOGGER.info(String.valueOf(median));
                    }
                    else {
                        double median = (times.get(times.size() / 2) + times.get(times.size() / 2 - 1)) / 2;
                        LOGGER.info(String.valueOf(median));
                    }
                }

            }
            //if we doesn't receive any packets while executing ping command
            while ((packet = error.readLine()) != null) {
                LOGGER.info(packet);
            }
        }
        catch (Exception e) {
            LOGGER.info("Packets stopped");
        }
    }

    // method for reading the ping statics of website
    public static ArrayList<BufferedReader> readPackets(ArrayList<String> commandList) throws Exception {

        // creating the sub process, execute system command
        ProcessBuilder build = new ProcessBuilder(commandList);
        Process process = build.start();

        // to read the packets return from ping command
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        ArrayList<BufferedReader> packets = new ArrayList<BufferedReader>();
        packets.add(input);
        packets.add(error);
        return packets;

    }

    public static void main(String args[]) throws Exception
    {
        // creating list for commands
        ArrayList<String> commandList = new ArrayList<String>();
        commandList.add("ping"); //command to ping ip address is added into list
        commandList.add("www.yahoo.com"); // host address
        findMedian(commandList);

    }
}
