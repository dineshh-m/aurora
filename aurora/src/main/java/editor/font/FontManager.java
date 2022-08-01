package editor.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class FontManager {
	
	public FontManager() {
		
	}

	public Font getFont(String fontType) {
		
		Font font = null;
		
		try {
			
			font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/fonts/"+fontType));
			font = font.deriveFont(12f);
		}catch(FontFormatException | IOException e) {
			System.out.println("Failed to load font");
		}
		return font;
	}
}
