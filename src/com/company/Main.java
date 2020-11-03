package com.company;

import java.util.Scanner;

public class Main {
    public static final FiniteAutomata finiteAutomata = new FiniteAutomata();

    public static void main(String[] args) {
	    finiteAutomata.readFromPath("FA.in");

	    System.out.println(finiteAutomata);

        Scanner in = new Scanner(System.in);

        label:
        while(true) {
            System.out.print(">>");
            switch (in.nextLine()) {
                case "exit":
                    break label;
                case "1":
                    System.out.println("States: " + finiteAutomata.getStates());
                    break;
                case "2":
                    System.out.println("Input Symbols: " + finiteAutomata.getAlphabet());
                    break;
                case "3":
                    System.out.println("Start State: " + finiteAutomata.getStartState());
                    break;
                case "4":
                    System.out.println("Final States: " + finiteAutomata.getFinalStates().toString());
                    break;
                case "5":
                    System.out.println("Transitions: " + finiteAutomata.getTransitions());
                    break;
                case "sequence":
                    System.out.print("Enter Sequence: ");
                    String sequence = in.nextLine();
                    if(finiteAutomata.isAccepted(sequence))
                        System.out.println("The sequence is ACCEPTED");
                    else
                        System.out.println("The sequence is NOT ACCEPTED");
                    break;
            }
}
    }
}
