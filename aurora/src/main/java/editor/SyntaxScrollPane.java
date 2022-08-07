package editor;

import java.awt.*;
import java.io.File;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

import app.Window;
import editor.font.FontManager;
import listeners.LineListener;
import values.Value;

public class SyntaxScrollPane extends RTextScrollPane{

	RSyntaxTextArea textArea;
	boolean isSaved = false;
	File file;
	Gutter gutter;
	LineListener lineListener;
	
	public SyntaxScrollPane(RSyntaxTextArea textArea, Window window) {
		super(textArea);
		this.textArea = textArea;
		lineListener = new LineListener(window);
		Font font = new FontManager().getFont(Value.REGULAR);
		gutter = this.getGutter();
		gutter.setSize(gutter.getWidth()+20, gutter.getHeight());
		gutter.setLineNumberFont(font);
		gutter.setBorderColor(getBackground());
		gutter.setFoldBackground(getBackground());
		gutter.setBackground(new Color(49, 51, 53));
		this.setLineNumbersEnabled(true);
		textArea.addKeyListener(lineListener);
		textArea.addMouseListener(lineListener);
		
//		//temp code
//
//		textArea.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				String text = "";
//				TabbedPane tabPane = window.getTabbedPane();
//				SyntaxScrollPane scrollPane = (SyntaxScrollPane) tabPane.getSelectedComponent();
//				SyntaxTextArea textArea = (SyntaxTextArea) scrollPane.getTextArea();
//				if(textArea != null) {
//					int lineNumber = textArea.getCaretLineNumber();
//					int lineoffset = textArea.getCaretOffsetFromLineStart();
//					int length = textArea.getText().substring(0, textArea.getCaretPosition()).length();
//					text = lineNumber+1+" : "+lineoffset+" : "+length;
//					window.getStatusBar().setText(text);
//				}
//			}
//		});
	}
	
	public RSyntaxTextArea getSyntaxArea() {
		return this.textArea;
	}
	
	public boolean getIsSaved() {
		return isSaved;
	}
	
	public void setIsSaved(boolean val) {
		isSaved = val;
	}
	
	//set file
	public void setFile(File file) {
		this.file = file;
	}
	
	//get file
	public File getFile() {
		return this.file;
	}
	
	//setting up fonts
	private void setupGutter(Font font) {
		
	}
}
