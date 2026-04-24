package btotw_turbine.text;

import java.util.List;
import java.util.Set;

/**
 * Represents text that can be formatted and displayed on a terminal that supports ansi colors.
 */
public sealed interface RichText permits RichStringText {
    /**
     * Gets the children text of this component
     * @return A unmodifiable list of children
     */
    List<RichText> children();

    /**
     * Gets a set of the decorations this text has
     * @return A set
     */
    Set<Decoration> decorations();
    
    /**
     * Creates a new rich text with the text added to the end
     * @param child The text to add
     * @return A new rich text with the added child
     */
    RichText append(RichText child);
    /**
     * Creates a new rich text with the plain string added to the end
     * @param child The text to add
     * @return A new rich text with the added string
     */
    RichText append(String child);


    /**
     * Creates a new rich text with the specified foreground color
     * @param color the color
     * @return a new rich text with the foreground color
     */
    RichText foreground(Color color);
    /**
     * Creates a new rich text with the specified background color
     * @param color the color
     * @return a new rich text with the background color
     */
    RichText background(Color color);

    /**
     * Creates a new rich text with the specified decorations added on
     * @param decorations The decorations to add
     * @return A new rich text
     */
    RichText decorate(Decoration... decorations);

    /**
     * Gets the foreground (text) color
     * @return the color
     */
    Color foreground();
    /**
     * Gets the background color
     * @return the color
     */
    Color background();

    /**
     * Returns a formatted string containing ansi formatting codes which can be sent to a terminal.
     * @return The formatted string
     */
    String toFormattedString();


    /**
     * Creates a rich text from the passed string. The color will be white on no background.
     * @param text The text to use
     * @return A new rich text
     */
    public static RichText text(String text) {
        return new RichStringText(text, List.of(), Set.of(), Color.WHITE, null);
    }
    /**
     * Creates a rich text from the passed string with the specified color as the foreground.
     * @param text The text to use
     * @param foreground The foreground color
     * @return A new rich text
     */
    public static RichText text(String text, Color foreground) {
        return new RichStringText(text, List.of(), Set.of(), foreground, null);
    }
}
