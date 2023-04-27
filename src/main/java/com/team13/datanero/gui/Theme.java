package com.team13.datanero.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Theme {
    private static Theme instance;

    public enum ThemeType {
        LIGHT,
        DARK
    }

    public enum FontStyle {
        BOLD,
        LIGHT,
        MEDIUM,
        REGULAR,
        RETINA,
        SEMIBOLD
    }

    private ThemeType currentTheme;

    private Theme() {
    }

    public static Theme getInstance() {
        if (instance == null) {
            instance = new Theme();
            instance.setCurrentTheme(ThemeType.LIGHT);
        }
        return instance;
    }

    /**
     * Getter for current theme.
     * 
     * @return Class variable currentTheme.
     */
    public ThemeType getCurrentTheme() {
        return this.currentTheme;
    }

    /**
     * Method that can be used to change the current theme.
     * 
     * @param theme LIGHT or DARK.
     */
    public void setCurrentTheme(ThemeType theme) {
        this.currentTheme = theme;
    }

    /**
     * Defines the color for general buttons depending on the style.
     * 
     * @return Color depending on the style.
     */
    public Color getButtonTextColor() {
        if (currentTheme == Theme.ThemeType.LIGHT) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    /**
     * Defines the color for general text that has no background, depending on the
     * style.
     * 
     * @return Color depending on the style.
     */
    public Color getGeneralTextColor() {
        if (currentTheme == Theme.ThemeType.LIGHT) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    /**
     * Defines the color for screen background depending on the style.
     * 
     * @return Color depending on the style.
     */
    public Color getScreenBackGroundColor() {
        if (currentTheme == Theme.ThemeType.LIGHT) {
            return Color.WHITE;
        }
        return Color.DARK_GRAY;
    }

    /**
     * Defines the color for answer buttons depending on the style.
     * 
     * @return Color depending on the style.
     */
    public Color getAnswerButtonColor() {
        if (currentTheme == Theme.ThemeType.LIGHT) {
            return Color.DARK_GRAY;
        }
        return Color.BLACK;
    }

    /**
     * Method that retrieves a font from <b>Fira Code</b> font family.
     * <p>
     * Example of usage: theme.getCustomFont(FontStyle.LIGHT, 48)
     * 
     * @param style    Desired font style. Available styles are defined as ENUMS in
     *                 Theme class.
     *                 <p>
     *                 <b>Available styles:</b>
     *                 </p>
     *                 <ul>
     *                 <li>BOLD</li>
     *                 <li>LIGHT</li>
     *                 <li>MEDIUM</li>
     *                 <li>REGULAR</li>
     *                 <li>RETINA</li>
     *                 <li>SEMIBOLD</li>
     *                 </ul>
     * @param fontSize Desired font size eg. 32, 48, 56, ...
     * @return Font with the desired style and size.
     */
    public Font getCustomFont(FontStyle style, float fontSize) {
        String filepath;

        switch (style) {
            case BOLD:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Bold.ttf";
                break;
            case LIGHT:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Light.ttf";
                break;
            case MEDIUM:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Medium.ttf";
                break;
            case REGULAR:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Retina.ttf";
                break;
            case RETINA:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Retina.ttf";
                break;
            case SEMIBOLD:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-SemiBold.ttf";
                break;
            default:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Retina.ttf";
        }

        File fontFile = new File(filepath);
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException e) {
            System.out.println("Error: Problem with custom font format: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: IOException occurred with custom font: " + e.getMessage());
            e.printStackTrace();
        }

        if (font != null) {
            font = font.deriveFont(fontSize);
        }
        return font;
    }
}