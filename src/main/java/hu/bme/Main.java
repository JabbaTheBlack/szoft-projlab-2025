package hu.bme;

import hu.bme.console.ConsoleApp;

/**
 * The main entry point of the application.
 * 
 * This class initializes and runs the test runner.
 * 
 * @see TestRunner
 */
public class Main {
    
    /**
     * The main method where the program execution begins.
     * 
     * It creates an instance of the TestRunner and calls its runTests method.
     * 
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args){
        ConsoleApp app = new ConsoleApp();
        app.run();
    }
}