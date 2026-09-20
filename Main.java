package com.glamlogistics.app;

import com.glamlogistics.transport.Logistics;
import com.glamlogistics.transport.RoadLogistics;
import com.glamlogistics.transport.SeaLogistics;
import com.glamlogistics.ui.GUIFactory;
import com.glamlogistics.ui.MacOSFactory;
import com.glamlogistics.ui.WindowsFactory;

import java.util.Scanner;

public class Main {

    private static final String CARGO = "satin evening dress collection";
    private static final String DESTINATION = "Almaty Flagship Boutique";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String deliveryInput = readInput(args, 0, scanner, "Enter delivery mode (ROAD or SEA): ");
        Logistics logistics = resolveLogistics(deliveryInput);
        if (logistics == null) {
            System.out.println("Invalid delivery mode: '" + deliveryInput + "'. Supported values: ROAD, SEA.");
            return;
        }

        String platformInput = readInput(args, 1, scanner, "Enter UI platform (WINDOWS or MACOS): ");
        GUIFactory guiFactory = resolveGuiFactory(platformInput);
        if (guiFactory == null) {
            System.out.println("Invalid UI platform: '" + platformInput + "'. Supported values: WINDOWS, MACOS.");
            return;
        }

        System.out.println("Delivery mode: " + normalize(deliveryInput));
        System.out.println("UI platform: " + normalize(platformInput));

        DeliveryApplication application = new DeliveryApplication(guiFactory, logistics);
        application.run(CARGO, DESTINATION);
    }

    private static String readInput(String[] args, int index, Scanner scanner, String prompt) {
        if (args.length > index && args[index] != null && !args[index].isBlank()) {
            return args[index];
        }
        System.out.print(prompt);
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }

    private static Logistics resolveLogistics(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }
        return switch (normalize(input)) {
            case "ROAD" -> new RoadLogistics();
            case "SEA" -> new SeaLogistics();
            default -> null;
        };
    }

    private static GUIFactory resolveGuiFactory(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }
        return switch (normalize(input)) {
            case "WINDOWS" -> new WindowsFactory();
            case "MACOS" -> new MacOSFactory();
            default -> null;
        };
    }

    private static String normalize(String input) {
        return input.trim().toUpperCase();
    }
}
