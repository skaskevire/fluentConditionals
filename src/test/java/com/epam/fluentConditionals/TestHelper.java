package com.epam.fluentConditionals;

public class TestHelper {
    public static boolean somethingIsTrue() {
        return true;
    }

    public static int getHighNumber() {
        return 1000;
    }

    public static int getLowNumber() {
        return 1;
    }

    public static String getAString() {
        return "a string";
    }

    public static void printFoo() {
        System.out.println("Foo");
    }

    public static void printBar() {
        System.out.println("Bar");
    }

    public static RuntimeException createException() {
        return new RuntimeException();
    }

    public static void printFirstChar(String string) {
        System.out.println(string.charAt(0));
    }

    public static void printLastChar(String string) {
        System.out.println(string.charAt(string.length()-1));
    }

    public static AnotherClass extractMessageForHighNumber(SomeClass someClass) {
        return new AnotherClass(someClass.getMessageForHighNumber());
    }

    public static AnotherClass extractMessageForLowNumber(SomeClass someClass) {
        return new AnotherClass(someClass.getMessageForLowNumber());
    }
}

class SomeClass {
    String getMessageForHighNumber() {
        return "I'm so high";
    }

    String getMessageForLowNumber() {
        return "I'm so low";
    }
}

class AnotherClass {
    final String message;

    AnotherClass(String message) {
        this.message = message;
    }
}