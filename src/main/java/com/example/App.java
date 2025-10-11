package com.example;

public class App {
    public static String greet() {
        return "Hello, Jenkins CI!";
    }

    public static void main(String[] args) {
        System.out.println(greet());
        System.out.println("Setup Complete");
        System.out.println("Test");
    }
}
