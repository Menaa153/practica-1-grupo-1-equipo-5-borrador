package uiMain;

import java.io.IOException;

public class App {

    public static void intializeApp() {
        loadClasses();
    }

    public static void loadClasses() {

    }

    public static void run() throws IOException {
        while (true) {
            System.out.println("Men�\n" +
                    "1. Pr�estamo");
            int input = System.in.read();
            switch (input) {
                case 1:
                    prestamo();
            }
        }
    }

    private static void prestamo() {
    }
}
