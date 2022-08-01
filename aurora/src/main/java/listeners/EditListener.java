package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Window;
import editor.SyntaxScrollPane;
import editor.SyntaxTextArea;
import editor.TabbedPane;

public class EditListener implements ActionListener {

	Window window;
	TabbedPane tabPane;
	
	public EditListener(Window window, TabbedPane tabPane) {
		this.window = window;
		this.tabPane = tabPane;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("Cut")) {
			cut();
		}else if(actionCommand.equals("Copy")) {
			copy();
		}else if(actionCommand.equals("Paste")) {
			paste();
		}
	}
	
	//cut routine
	private void cut() {
		int index = tabPane.getSelectedIndex();
		SyntaxScrollPane scrollPane = (SyntaxScrollPane) tabPane.getComponentAt(index);
		scrollPane.getTextArea().cut();
	}
	
	//copy routine
	private void copy() {
		int index = tabPane.getSelectedIndex();
		SyntaxScrollPane scrollPane = (SyntaxScrollPane) tabPane.getComponentAt(index);
		scrollPane.getTextArea().copy();
	}
	
	//paste routine
	private void paste() {
		int index = tabPane.getSelectedIndex();
		SyntaxScrollPane scrollPane = (SyntaxScrollPane) tabPane.getComponentAt(index);
		scrollPane.getTextArea().paste();
	}
}
