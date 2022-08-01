package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import app.Window;
import editor.SyntaxScrollPane;
import editor.SyntaxTextArea;

public class FileListener implements ActionListener{
	
	Window window;
	JTabbedPane tabPane;
	
	public FileListener(Window window, JTabbedPane tabPane) {
		this.window = window;
		this.tabPane = tabPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("Save")) {
			save();
		}else if(actionCommand.equals("Save As")) {
			saveAs();
		}
	}
	
	//method for save
	private void save() {

		int index = tabPane.getSelectedIndex();
		System.out.println("Index: "+index);
		SyntaxScrollPane scrollPane = (SyntaxScrollPane) tabPane.getSelectedComponent();
		SyntaxTextArea textArea = (SyntaxTextArea) scrollPane.getSyntaxArea();
		String text = scrollPane.getSyntaxArea().getText();
		
		if(scrollPane.getIsSaved() == false) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			int response = chooser.showSaveDialog(window);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file= chooser.getSelectedFile();
				String fileName = file.getName();
				setExt(file, textArea);
				try(FileWriter writer = new FileWriter(file)) {
					
					writer.write(text);
					textArea.setHashValue(text.hashCode());
					scrollPane.setFile(file);
					scrollPane.setIsSaved(true);
					JLabel label = window.list.get(index);
					label.setText(fileName);
					
				}catch(IOException e) {
					
				}
			}
		}else if(scrollPane.getIsSaved()) {
			
			if(text.hashCode() != textArea.getHashValue()) {
				
				try (FileWriter writer = new FileWriter(scrollPane.getFile())) {
					setExt(scrollPane.getFile(), textArea);
					writer.write(text);
					scrollPane.setIsSaved(true);
				}catch(IOException e) {
					
				}
			}
		}
	}

	//method for save as
	private void saveAs() {
		SyntaxScrollPane scrollPane = (SyntaxScrollPane) tabPane.getSelectedComponent();
		SyntaxTextArea textArea = (SyntaxTextArea) scrollPane.getSyntaxArea();
		String text = textArea.getText();
		int index = tabPane.getSelectedIndex();
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int response = chooser.showSaveDialog(window);
		
		if(response == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			
			try(FileWriter writer = new FileWriter(file)) {
				
				writer.write(text);
				textArea.setHashValue(text.hashCode());
				scrollPane.setFile(file);
				scrollPane.setIsSaved(true);
				JLabel label = window.list.get(index);
				label.setText(file.getName());
				setExt(file, textArea);
			}catch(IOException e) {
				
			}
		}
	}
	
	//get extension based on file name extension
//	private void setExtension(String fileName, RSyntaxTextArea textArea) {
//		int last = fileName.lastIndexOf('.');
//		
//		if(fileName.endsWith(".java")) {
//			System.out.println(fileName);
//			 textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
//		}else if(fileName.endsWith(".py")) {
//			System.out.println(fileName);
//			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
//		}else if(fileName.endsWith(".html")) {
//			System.out.println(fileName);
//			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
//		}else if(fileName.endsWith(".css")) {
//			System.out.println(fileName);
//			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
//		}else if(fileName.endsWith(".c")) {
//			System.out.println(fileName);
//			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
//		}else if(fileName.endsWith(".cpp")) {
//			System.out.println(fileName);
//			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
//		}else if(fileName.endsWith(".js")) {
//			System.out.println(fileName);
//			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
//		}else if(fileName.endsWith(".php")) {
//			System.out.println(fileName);
//			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP);
//		}else if(fileName.endsWith(".json")) {
//			System.out.println(fileName);
//			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
//		}else {
//			System.out.println("No match found");
//		}
//		
//	}
	
	private void setExt(File file, SyntaxTextArea textArea) {
		String fileName = file.getName();
		int lastIndex = fileName.lastIndexOf('.');
		String extension = "";
		
		if(lastIndex >= 0) extension = fileName.substring(lastIndex);
		
		switch(extension) {
		case ".java":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
			break;
		case ".py":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
			break;
		case ".js":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
			break;
		case ".html":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
			break;
		case ".css":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
			break;
		case ".c":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
			break;
		case ".cpp":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
			break;
		case ".php":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP);
			break;
		case ".dart":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DART);
			break;
		case ".sh":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL);
			break;
		case ".xml":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
			break;
		case ".gradle":
			textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
			break;
		}
	}
}
