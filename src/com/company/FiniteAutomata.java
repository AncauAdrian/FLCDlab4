package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FiniteAutomata {
    private final List<String> states = new LinkedList<>();
    private final List<String> alphabet = new LinkedList<>();
    private final HashMap<Pair<String, String>, String> transitions = new HashMap<>();
    private String startState;
    private final List<String> finalStates = new LinkedList<>();
    private boolean deterministic = true;

    public FiniteAutomata() {

    }

//    public FiniteAutomata(String path) {
//        readFromPath(path);
//    }

    public void readFromPath(String path) {
        try {
            File finiteAutomataFile = new File(path);
            Scanner reader = new Scanner(finiteAutomataFile);

            // Read the tokens (each is separated by a white space)

            String line = reader.nextLine();

            // Store states
            states.addAll(Arrays.asList(line.split(",")));
            line = reader.nextLine();

            // Store input symbols
            alphabet.addAll(Arrays.asList(line.split(",")));
            line = reader.nextLine();

            // Store start state
            startState = line;
            line = reader.nextLine();

            // Store final states
            finalStates.addAll(Arrays.asList(line.split(",")));

            while(reader.hasNextLine()) {
                line = reader.nextLine().replaceAll("\\s", "");
                String[] components = line.split("[,=]");

                if(components.length != 3) {
                    throw new RuntimeException("Reading error");
                }

                if(!alphabet.contains(components[1]))
                    throw new RuntimeException("Reading error: " + components[1]);

                if(!states.contains(components[0]))
                    throw new RuntimeException("Reading error: " + components[0]);

                if(!states.contains(components[2]))
                    throw new RuntimeException("Reading error: " + components[2]);

                Pair<String, String > key = new Pair<>(components[0], components[1]);

                if(deterministic && transitions.containsKey(key))
                    deterministic = false;

                transitions.put(key, components[2]);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file: tokens.in");
            e.printStackTrace();
        }
    }

    public boolean isAccepted(String sequence) {
        if(!deterministic)
            throw new RuntimeException("Non Deterministic");

        String currentState = startState;

        for(int i = 0; i < sequence.length(); i++) {
            String c = String.valueOf(sequence.charAt(i));

            if(!alphabet.contains(c)) {
                throw new RuntimeException("Invalid input symbol: " + c);
            }

            Pair<String, String> pair = new Pair<>(currentState, c);

            currentState = transitions.get(pair);

            if(currentState == null)
                throw new RuntimeException("No available transition for " + pair);
        }

        return finalStates.contains(currentState);
    }

    public List<String> getStates() {
        return states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public HashMap<Pair<String, String>, String> getTransitions() {
        return transitions;
    }

    public String getStartState() {
        return startState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    @Override
    public String toString() {
        return  "States: " + states.toString() + '\n' +
                "Input Symbols: " + alphabet.toString() + '\n' +
                "Start State: " + startState + '\n' +
                "Final States: " + finalStates.toString() + '\n' +
                "Transitions: " + transitions.toString() + '\n';
    }
}