package com.personal.project.readsearch.service;

import java.util.Scanner;

public class InputValidation {
    private Scanner input;

    public InputValidation() {
        this.input = new Scanner(System.in);
    }

    public int nextInt(String message) {
        boolean valid = true;
        int number = 0;
        do {
            System.out.println(message);
            try {
                valid = true;
                number = Integer.parseInt(this.input.nextLine());
                if (number < 0) {
                    System.out.println("The number should be positive");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                valid = false;
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!valid);

        return number;
    }

    public String nextString() {
        return this.input.nextLine();
    }

}
