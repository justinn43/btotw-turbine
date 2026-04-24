package btotw_turbine.cli;

import java.util.List;

import btotw_turbine.text.Color;
import btotw_turbine.text.Decoration;
import btotw_turbine.text.RichText;
import btotw_turbine.text.TextWrapper;

public record Command(String name, String fullname, String description, Execution callback) {
    public Command(String name, String description, Execution callback) {
        this(name, name, description, callback);
    }

    public interface Execution {
        void run(String[] args);
    }


    /**
     * Prints a help screen
     * @param command The root command
     * @param description The description of the root command
     * @param commands Subcommands to print help for
     */
    public static void printHelp(String command, String description, List<Command> commands) {
        System.out.println(TextWrapper.wrapString(description, 46).replace("\n", "\n  "));
        String usage = command;

        System.out.println();
        System.out.println(RichText.text("Usage: ", Color.GREEN).decorate(Decoration.BOLD).append(usage).toFormattedString());
        System.out.println("List of commands below.");
        System.out.println("  Name            Description");
        System.out.println("  ----            -----------");
        for (Command cmd : commands) {
            System.out.println(String.format("  %-15s %s", cmd.fullname(), cmd.description()));
        }
    }
}
