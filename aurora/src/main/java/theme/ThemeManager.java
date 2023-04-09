package theme;

import java.awt.*;

public class ThemeManager {
    public Theme oneDark;

    public ThemeManager() {
        oneDark = new Theme();
        configureOneDark();
    }

    private void configureOneDark() {
        oneDark.editorBackground = Color.decode("#282c34");//editor background
        oneDark.editorTextForeground = Color.decode("#ABB2BF");//editor foreground text;

        oneDark.reservedWord1.foreground = Color.decode("#c678dd");
        oneDark.reservedWord2.foreground = Color.decode("#61afef");
        oneDark.function.foreground = Color.decode("#e06c75");
        oneDark.literlaString.foreground = Color.decode("#98C370");
        oneDark.dataType.foreground = Color.decode("#61afef");
        oneDark.operator.foreground = Color.decode("#C678DD");

        //comments
        oneDark.comment.foreground = Color.decode("#ABB2BF");
        oneDark.commentMultiLine.foreground = Color.decode("#ABB2BF");

    }
}
