package btotw_turbine.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class RichTextTests {
    
    @Test
    void testAppendRichText() {
        RichText base = RichText.text("Hello");
        RichText child = RichText.text(" World");

        RichText result = base.append(child);

        assertEquals(1, result.children().size());
        assertEquals(child, result.children().get(0));
    }

    @Test
    void testAppendString() {
        RichText base = RichText.text("Hello");

        RichText result = base.append(" World");

        assertEquals(1, result.children().size());
        assertEquals(" World", ((RichStringText) result.children().get(0)).text());
    }

    @Test
    void testImmutabilityAppend() {
        RichText base = RichText.text("Hello");

        RichText result = base.append("!");

        // Ensure original is unchanged
        assertTrue(base.children().isEmpty());
        assertNotSame(base, result);
    }

    @Test
    void testForegroundColorSet() {
        RichText base = RichText.text("Hello");

        RichText colored = base.foreground(Color.RED);

        assertEquals(Color.RED, colored.foreground());
        assertNotSame(base, colored);
    }

    @Test
    void testBackgroundColorSet() {
        RichText base = RichText.text("Hello");

        RichText colored = base.background(Color.BLUE);

        assertEquals(Color.BLUE, colored.background());
        assertNotSame(base, colored);
    }

    @Test
    void testDecorationsAdded() {
        RichText base = RichText.text("Hello");

        RichText decorated = base.decorate(Decoration.BOLD, Decoration.UNDERLINE);

        Set<Decoration> decorations = decorated.decorations();

        assertTrue(decorations.contains(Decoration.BOLD));
        assertTrue(decorations.contains(Decoration.UNDERLINE));
        assertEquals(2, decorations.size());
    }

    @Test
    void testMoreDecorations() {
        RichText base = RichText.text("Hello")
                .decorate(Decoration.BOLD);

        RichText result = base.decorate(Decoration.ITALIC);

        assertTrue(result.decorations().contains(Decoration.BOLD));
        assertTrue(result.decorations().contains(Decoration.ITALIC));
    }

    @Test
    void testChildrenUnmodifiable() {
        RichText base = RichText.text("Hello");
        RichText result = base.append("World");

        List<RichText> children = result.children();

        assertThrows(UnsupportedOperationException.class, () -> {
            children.add(RichText.text("Nope!"));
        });
    }

    @Test
    void testDecorationsUnmodifiable() {
        RichText base = RichText.text("Hello")
                .decorate(Decoration.BOLD);

        Set<Decoration> decorations = base.decorations();

        assertThrows(UnsupportedOperationException.class, () -> {
            decorations.add(Decoration.ITALIC);
        });
    }

    @Test
    void testColorEnumValues() {
        assertEquals(31, Color.RED.foreground());
        assertEquals(41, Color.RED.background());

        assertEquals(94, Color.BRIGHT_BLUE.foreground());
        assertEquals(104, Color.BRIGHT_BLUE.background());
    }

    @Test
    void testDecorationCodes() {
        assertEquals(1, Decoration.BOLD.code());
        assertEquals(4, Decoration.UNDERLINE.code());
        assertEquals(9, Decoration.CROSSED_OUT.code());
    }

    @Test
    void testToFormattedStringNotNull() {
        RichText base = RichText.text("Hello");

        String formatted = base.toFormattedString();

        assertNotNull(formatted);
        assertFalse(formatted.isEmpty());
    }

    @Test
    void testToFormattedStringContainsAnsiCodes() {
        RichText base = RichText.text("Hello")
                .foreground(Color.RED)
                .decorate(Decoration.BOLD);

        String formatted = base.toFormattedString();

        // ANSI escape prefix
        assertTrue(formatted.contains("\u001B["));
    }

    @Test
    void testChainedOperations() {
        RichText text = RichText.text("Hello")
                .append(" World")
                .foreground(Color.GREEN)
                .background(Color.BLACK)
                .decorate(Decoration.BOLD, Decoration.UNDERLINE);

        assertEquals(Color.GREEN, text.foreground());
        assertEquals(Color.BLACK, text.background());
        assertEquals(2, text.decorations().size());
        assertEquals(1, text.children().size());
    }


    @Test
    void testConvienienceColorMethod() {
        RichText text = RichText.text("Hello, World!", Color.GREEN);

        assertEquals(Color.GREEN, text.foreground());
    }

    @Test
    void testFormattedComplexString() {
        RichText text = RichText.text("Hello")
                .background(Color.BLUE)
                .decorate(Decoration.FAINT)
                .append(RichText.text(" World", Color.BRIGHT_RED))
                .append(RichText.text("!", Color.GREEN)
                    .decorate(Decoration.UNDERLINE)
                );


        String formatted = text.toFormattedString();
        assertEquals("\u001B[0;37;44;2mHello\u001B[0;91m World\u001B[0m\u001B[0;32;4m!\u001B[0m\u001B[0m", formatted);
    }
}
