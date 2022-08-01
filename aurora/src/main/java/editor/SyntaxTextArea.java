package editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.IOException;
import java.util.List;

import javax.swing.ListCellRenderer;
import javax.swing.ToolTipManager;
import javax.swing.text.JTextComponent;

import org.fife.rsta.ac.LanguageSupport;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.java.JavaLanguageSupport;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.LanguageAwareCompletionProvider;
import org.fife.ui.autocomplete.ParameterChoicesProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Style;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.ToolTipSupplier;

import editor.font.FontManager;
import values.Value;

public class SyntaxTextArea extends RSyntaxTextArea{
	
	boolean isEdited = false;
	int hashCode;
	
	public SyntaxTextArea() {
		//--------auto complete test
//		DefaultCompletionProvider provider = new DefaultCompletionProvider();
//		
//		LanguageAwareCompletionProvider prov = new
//				LanguageAwareCompletionProvider(provider);
//		AutoCompletion ac = new AutoCompletion(prov);
//		ac.install(this);
//		ac.setAutoCompleteEnabled(true);
//		ac.setAutoActivationEnabled(true);
//		LanguageSupportFactory langSupport = LanguageSupportFactory.get();
//		LanguageSupport support = langSupport.getSupportFor(SYNTAX_STYLE_JAVA);
//		JavaLanguageSupport jls = (JavaLanguageSupport)support;
//		langSupport.register(this);
//		ToolTipManager.sharedInstance().registerComponent(this);
		//---------------------------------------------------------------->another test
		LanguageSupportFactory.get().register(this);
		//auto complete test
		this.changeStyleViaThemeXml();
	    this.setCodeFoldingEnabled(true);
		this.setBackground(new Color(60, 63, 65));
		this.changeStyleProgrammatically();
	}
	
	
	//setting font 
	 private static void setFontType(RSyntaxTextArea textArea, Font font) {
	      if (font != null) {
	         SyntaxScheme ss = textArea.getSyntaxScheme();
	        ss = (SyntaxScheme) ss.clone();
	         for (int i = 0; i < ss.getStyleCount(); i++) {
	            if (ss.getStyle(i) != null) {
	               ss.getStyle(i).font = font;
	            }
	         }
	         textArea.setSyntaxScheme(ss);
	         textArea.setFont(font);
	      }
	   }
	 
	 private void changeStyleProgrammatically() {

	      // Set the font for all token types.
		 Font font = new FontManager().getFont(Value.REGULAR);
	      setFontType(this, font);

	      // Change a few things here and there.
	      SyntaxScheme scheme = this.getSyntaxScheme();

	  /**    scheme.getStyle(Token.RESERVED_WORD).foreground = Color.orange;
	      scheme.getStyle(Token.DATA_TYPE).foreground = new Color(242, 102, 31);
	      scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).underline = false;
	      scheme.getStyle(Token.IDENTIFIER).foreground = new Color(169, 183, 198);
	      scheme.getStyle(Token.COMMENT_EOL).font = new FontManager().getFont(Value.REGULAR); **/
	      
	      this.setCaretColor(Color.white);
	      Style style = new Style(new Color(255, 219, 77));
	         
	      scheme.setStyle(Token.FUNCTION, style);
	      scheme.getStyle(Token.ERROR_IDENTIFIER).background = Color.red;
	      scheme.getStyle(Token.ERROR_IDENTIFIER).underline = true;
	      scheme.getStyle(Token.IDENTIFIER).foreground = new Color(169, 183, 198);
	      scheme.getStyle(Token.RESERVED_WORD).foreground= new Color(204, 120, 50);
	    //  scheme.getStyle(Token.RESERVED_WORD_2).foreground = new Color(255, 153, 0);
	      scheme.getStyle(Token.DATA_TYPE).foreground = new Color(152, 118, 170);
	      scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).underline = false;
	      scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = new Color(95, 173, 133);
	      scheme.getStyle(Token.COMMENT_EOL).font = new FontManager().getFont(Value.ITALIC);
	      scheme.getStyle(Token.COMMENT_MULTILINE).foreground = new Color(27, 105, 37);
	      scheme.getStyle(Token.ERROR_STRING_DOUBLE).foreground = new Color(95, 173, 133);
	      scheme.getStyle(Token.PREPROCESSOR).foreground = new Color(80, 200, 120);
	     // scheme.getStyle(Token.FUNCTION).foreground = new Color(225, 173, 1);
	     
	      
	      scheme.getStyle(Token.LITERAL_BOOLEAN).foreground = new Color(136, 77, 255);
	    //  scheme.getStyle(Token.VARIABLE).foreground = new Color(0, 128, 128);
	      scheme.getStyle(Token.OPERATOR).foreground = Color.white;
	      
	      
	      this.revalidate();
	      this.setCurrentLineHighlightColor(getBackground());
	   }
	 
	 private void changeStyleViaThemeXml() {
	      try {
	         Theme theme = Theme.load(getClass().getResourceAsStream(
	               "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
	         theme.apply(this);
	      } catch (IOException ioe) { // Never happens
	         ioe.printStackTrace();
	      }
	   }
	
	 //setting hash value from another class
	 public void setHashValue(int hash) {
		 this.hashCode = hash;
	 }
	 
	 //getter for hash
	 public int getHashValue() {
		 return this.hashCode;
	 }
}
