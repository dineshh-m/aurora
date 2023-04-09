package theme;

import java.awt.*;

public class Theme {

    public Color editorBackground; //background for editor
    public Color editorTextForeground;
    public  TokenProperty reservedWord1, reservedWord2, function;
    public TokenProperty dataType, operator;
    public TokenProperty literlaString, literalBoolean;
    public TokenProperty comment, commentMultiLine;

    public Theme() {

        reservedWord1 = new TokenProperty();
        reservedWord2 = new TokenProperty();
        function = new TokenProperty();
        dataType = new TokenProperty();
        operator = new TokenProperty();
        literlaString = new TokenProperty();
        literalBoolean = new TokenProperty();
        comment = new TokenProperty();
        commentMultiLine = new TokenProperty();
    }

    public Theme(Color editorBackground, TokenProperty proerties[]) {
        this.editorBackground = editorBackground;

    }
}
