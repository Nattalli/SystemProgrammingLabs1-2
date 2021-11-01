package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Automaton {
    public static void main(String[] args) {

        NFA automaton = Reading();

        UnreachableStates(automaton);

        DeadStates(automaton);
    }

    public static NFA Reading(){
        Scanner scanner = new Scanner(System.in);
        System.out.format("Enter the filepath:");
        String pathname = scanner.next();
        NFA nfa = null;
        try {
            nfa = new NFA(pathname);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return nfa;
    }

    public static void UnreachableStates(NFA nfa)
    {
        Set<Integer> notReachableStates = nfa.getNotReachable();
        System.out.println("Недосяжні стани:");
        printStates(notReachableStates, "\t", " ");
    }

    public static void DeadStates(NFA nfa)
    {
        Set<Integer> deadStates = nfa.getDead();
        System.out.println("Тупикові стани:");
        printStates(deadStates, "\t", " ");
    }

    private static void printStates(Set<Integer> iterable, String indent, String separator) {
        System.out.print(indent);
        for (Integer t : iterable) {
            System.out.print(t + separator);
        }
        System.out.println();
    }

    static Scanner getScanner(String pathname) throws FileNotFoundException {
        File file = new File(pathname);

        if (!file.exists()) {
            System.out.format("File '%s' does not exist.%n", pathname);
        }

        if (!file.canRead()) {
            System.out.format("Cannot read file '%s'.%n", pathname);
        }

        return new Scanner(file);
    }
}
