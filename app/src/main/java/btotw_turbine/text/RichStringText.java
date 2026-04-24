package btotw_turbine.text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record RichStringText(
    String text, 
    List<RichText> children,
    Set<Decoration> decorations, 
    Color foreground, 
    Color background) implements RichText {
    /**
     * Creates a new rich string text.
     * 
     * @param text the text
     * @param children the children of this
     * @param foreground The color of the text
     * @param background The background color, Can be null, which represents no background.
     */
    public RichStringText {
        children = List.copyOf(children);
        decorations = Set.copyOf(decorations);
    }

    @Override
    public RichText append(RichText child) {
        ArrayList<RichText> newChildren = new ArrayList<>(this.children);
        newChildren.add(child);
        return new RichStringText(this.text, newChildren, this.decorations, this.foreground, this.background);
    }

    @Override
    public RichText append(String child) {
        ArrayList<RichText> newChildren = new ArrayList<>(this.children);
        newChildren.add(new RichStringText(child, List.of(), Set.of(), Color.WHITE, null));
        return new RichStringText(this.text, newChildren, this.decorations, this.foreground, this.background);
    }

    @Override
    public RichText decorate(Decoration... decorations) {
        Set<Decoration> newDecor = new HashSet<>(this.decorations);
        for (Decoration decor : decorations) {
            newDecor.add(decor);
        }
        return new RichStringText(this.text, this.children, newDecor, this.foreground, this.background);
    }

    @Override
    public String toFormattedString() {
        //build ansi color sequences
        String reset = "\u001B[0m";
        String color = "\u001B[0";
        color = color + ";" + this.foreground.foreground();
        if (this.background != null) {
            color = color + ";" + this.background.background();
        }
        for (Decoration decor : this.decorations) {
            color = color + ";" + decor.code();
        }
        color = color + "m";

        StringBuilder text = new StringBuilder();
        text.append(color);
        text.append(this.text);
        //Add children
        for (RichText child : this.children) {
            text.append(child.toFormattedString());
        }
        text.append(reset);

        return text.toString();
    }

    @Override
    public RichText foreground(Color color) {
        return new RichStringText(this.text, this.children, this.decorations, color, this.background);
    }

    @Override
    public RichText background(Color color) {
        return new RichStringText(this.text, this.children, this.decorations, this.foreground, color);
    }

    @Override
    public final String toString() {
        return this.toFormattedString();
    }
}
