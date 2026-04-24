package btotw_turbine.cli;

import java.util.List;

import btotw_turbine.text.Color;
import btotw_turbine.text.RichText;

public class LightCommands {
    private static final List<Command> COMMANDS = List.of(
        new Command("help", "Displays this message.", LightCommands::printHelp)
    );


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
        String description = "Turbine CLI is a tool to help manage your vertical axis wind turbine. It provides features such as monitoring and customization.";
        Command.printHelp("turbine [command]", description, COMMANDS);
    }
    private static void printError() {
        System.out.println(RichText.text("Invalid arguments. Type help for list of commands.", Color.RED));
    }
}
