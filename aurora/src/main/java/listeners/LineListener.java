package listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import app.Window;
import editor.SyntaxScrollPane;
import editor.SyntaxTextArea;
import editor.TabbedPane;

public class LineListener implements KeyListener, MouseListener{
	
	Window window;
	TabbedPane tabPane;
	
	public LineListener(Window window) {
		this.window = window;
		this.tabPane = window.getTabbedPane();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		fetchLine();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		fetchLine();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		fetchLine();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		fetchLine();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		fetchLine();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void fetchLine() {
		String text = "";
		TabbedPane tabPane = window.getTabbedPane();
		SyntaxScrollPane scrollPane = (SyntaxScrollPane) tabPane.getSelectedComponent();
		SyntaxTextArea textArea = (SyntaxTextArea) scrollPane.getTextArea();
		if(textArea != null) {
			int lineNumber = textArea.getCaretLineNumber();
			int lineoffset = textArea.getCaretOffsetFromLineStart();
			int length = textArea.getText().substring(0, textArea.getCaretPosition()).length();
			//text = (lineNumber+1) + " : " + (lineoffset+1) +" : "+ length + "              ";
			text = "line: "+(lineNumber+1)+"   Col: "+(lineoffset+1);
			window.getStatusBar().setLine(text);
		}
	}

}
