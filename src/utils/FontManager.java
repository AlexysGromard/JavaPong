package utils;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * FontManager is a utility class that manages the loading and caching of fonts.
 * It allows you to easily retrieve fonts of different styles and sizes.
 * It optimizes the loading process by caching the fonts, so that they are only loaded once.
 */
public class FontManager {
    public enum OrbitronStyle {
        REGULAR, MEDIUM, SEMIBOLD, BOLD, EXTRA_BOLD, BLACK
    }

    private static final Map<OrbitronStyle, Font> baseFonts = new HashMap<>();
    private static final Map<String, Font> derivedFonts = new HashMap<>();

    /**
     * Returns a font of the Orbitron family with the specified size and style.
     *
     * @param size  The size of the font
     * @param style The style of the font (REGULAR, MEDIUM, SEMIBOLD, BOLD)
     * @return The requested font
     */
    public static Font getOrbitron(float size, OrbitronStyle style) {
        String key = style.name() + "-" + size;

        // If a derived version with this size already exists, it is returned
        if (derivedFonts.containsKey(key)) {
            return derivedFonts.get(key);
        }

        // Otherwise, we load the basic version if necessary
        if (!baseFonts.containsKey(style)) {
            try {
                String fileName = switch (style) {
                    case BLACK -> "fonts/Orbitron-Black.ttf";
                    case EXTRA_BOLD -> "fonts/Orbitron-ExtraBold.ttf";
                    case BOLD -> "fonts/Orbitron-Bold.ttf";
                    case SEMIBOLD -> "fonts/Orbitron-SemiBold.ttf";
                    case MEDIUM -> "fonts/Orbitron-Medium.ttf";
                    default -> "fonts/Orbitron-Regular.ttf";
                };
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fileName));
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
                baseFonts.put(style, font);
            } catch (Exception e) {
                e.printStackTrace();
                baseFonts.put(style, new Font("SansSerif", Font.PLAIN, 12)); // fallback
            }
        }

        // Derive the requested size and cache it
        Font derived = baseFonts.get(style).deriveFont(size);
        derivedFonts.put(key, derived);
        return derived;
    }
}
