package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Ho-chang Chang
 * hc23882
 * 16220
 * Slip days used: <0>
 * Spring 2017
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
        System.out.print("critters>");
        String line = kb.nextLine();
        String[] parse = line.split(" ");
        while (true) {
        	try {
        		if (parse[0].equals("quit")) {
        			if (parse.length == 1) {
        				break;
        			}
        			else {
            			throw new IllegalArgumentException();
            		}
        		}
        		if (parse[0].equals("show")) {
        			if (parse.length == 1) {
        				Critter.displayWorld();
        			}
        			else {
            			throw new IllegalArgumentException();
            		}
            	}
            	else if (parse[0].equals("step")) {
            		if (parse.length == 2) {
            			int count = Integer.parseInt(parse[1]);
            			for (int i = 0; i < count; i++) {
            				Critter.worldTimeStep();
            			}
            		}
            		else if (parse.length == 1) {
            			Critter.worldTimeStep();
            		}
            		else {
            			throw new IllegalArgumentException();
            		}
            	}
            	else if (parse[0].equals("seed")) {
            		if (parse.length == 2) {
            			Critter.setSeed(Long.parseLong(parse[1]));
            		}
            		else {
            			throw new IllegalArgumentException();
            		}
            	}
            	else if (parse[0].equals("make")) {
            		if (parse.length == 3) {
            			int count = Integer.parseInt(parse[2]);
            			for (int i = 0; i < count; i++) {      				
            				Critter.makeCritter(myPackage + "." + parse[1]);
            			}
            		}
            		else if (parse.length == 2) {
            			Critter.makeCritter(myPackage + "." + parse[1]);
            		}
            		else {
            			throw new IllegalArgumentException();
            		}
            		
            	}
            	else if (parse[0].equals("stats")) {
            		if (parse.length == 2) {
            			List<Critter> list = Critter.getInstances(myPackage + "." + parse[1]);
                		Class<?> passCritter = Class.forName(myPackage + "." + parse[1]);
                		Method stats = passCritter.getMethod("runStats", List.class);
                		stats.invoke(null, list);
            		}
            		else {
            			throw new IllegalArgumentException();
            		}
            	}
            	else {
            		throw new Exception();
            	}
        	}
        	catch (IllegalArgumentException | InvalidCritterException e) {
        		System.out.println("error processing: " + line);
        	}
        	catch (Exception e) {
        		System.out.println("invalid command: " + line);
        	}       	
        	System.out.print("critters>");
        	line = kb.nextLine();
        	parse = line.split(" ");
        }       
        /* Write your code above */
        System.out.flush();

    }
}
