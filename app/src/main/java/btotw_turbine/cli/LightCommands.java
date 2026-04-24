package btotw_turbine.cli;

import java.util.List;

import btotw_turbine.Main;
import btotw_turbine.Status;
import btotw_turbine.text.Color;
import btotw_turbine.text.RichText;

public class LightCommands {
    private static final List<Command> COMMANDS = List.of(
        new Command("help", "Displays this message.", LightCommands::printHelp),
        new Command("status", "Displays the status of the light", LightCommands::status),
        new Command("brightness", "brightness [level]", "Changes the brightness of the light", LightCommands::brightness)
    );


    private static void brightness(String[] args) {
        if (args.length != 1) {
            printError();
            return;
        }
        try {
            float brightness = Float.parseFloat(args[0]);
            if (brightness < 0 || brightness > 1) {
                System.out.println(RichText.text("Brightness must be within the range [0,1]", Color.BRIGHT_RED));
                return;
            }

            System.out.println(RichText.text(String.format("Set the brightness to %.2f", brightness), Color.BRIGHT_WHITE));

            Main.getTurbine().light().setBrightness(brightness);
        } catch (NumberFormatException e) {
            System.out.println(RichText.text("Invalid number format.", Color.BRIGHT_RED));
        }
    }


    private static void status(String[] args) {
        System.out.println(RichText.text("The current status of the light is: ", Color.BRIGHT_WHITE)
            .append(
                RichText.text(Status.OKAY.name(), Status.OKAY.color()).bold()
            )
        );
        float brightness = Main.getTurbine().light().getBrightness();

        System.out.println(RichText.text("  Brightness: ").append(RichText.text(String.format("%.2f", brightness))).toFormattedString());

    }


    /**
     * Used to query information about the mounted light
     * @param args
     */
    public static void lightCommand(String[] args) {
        if (args.length < 1) {
            printError();
            return;
        }
        for (Command command : COMMANDS) {
            if (command.name().equals(args[0])) {
                String[] cmdArgs = new String[args.length - 1];
                for (int i = 1; i < args.length; i++) {
                    cmdArgs[i - 1] = args[i];
                }
                command.callback().run(cmdArgs);
                return;
            }
        }
        printError();
        return;
    }

    private static void printHelp(String[] args) {
        String description = "The light command allows you to view and modify the mounted light on the wind turbine.";
        Command.printHelp("turbine light [command]", description, COMMANDS);
    }
    private static void printError() {
        System.out.println(RichText.text("Invalid arguments. Type help for list of commands.", Color.BRIGHT_RED));
    }
}
