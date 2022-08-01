package editor;

import java.awt.Component;
import java.io.File;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.icons.FlatClearIcon;

import app.Window;

public class TabbedPane extends JTabbedPane{

	int tabIndex = 0;
	Component component;
	Window window;
	FlatClearIcon clearIcon;
	
	public TabbedPane(Window window) {
		this.window = window;
		clearIcon = new FlatClearIcon();
		addListener();
	}
	
	private void addListener() {
//		this.addChangeListener(new ChangeListener() {
//
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				int index = TabbedPane.this.getSelectedIndex();
//				
//				if(tabIndex < TabbedPane.this.getTabCount()) {
//					TabbedPane.this.setBackgroundAt(tabIndex, new Color(0, 0, 0, Value.ALPHA_VALUE));
//				}
//				
//				TabbedPane.this.setBackgroundAt(index, new Color(0, 0, 0, 35));
//				tabIndex = index;
//				
//				
//				
//			}
//			
//		});
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int index = TabbedPane.this.getSelectedIndex();
				
				SyntaxScrollPane scrollPane = (SyntaxScrollPane) TabbedPane.this.getSelectedComponent();
				
				if(scrollPane != null) {
					File file = scrollPane.getFile();
					if( file != null && file.exists()) {
						String label = getLang(file);
						window.getStatusBar().setLang(label);
						window.getStatusBar().setPath(file.getAbsolutePath());
					}
				}
			}
			
		});
	}
	
	public int getTabIndex() {
		return this.tabIndex;
	}
	
	public void setTabIndex(int index) {
		this.tabIndex = index;
	}
	
	@Override
	public void addTab(String title, Component component) {
		// TODO Auto-generated method stub
		
		super.addTab(title, component);
	}
	
	String getLang(File file) {
		String fileName = file.getName();
		String extension = "";
		int lastIndex = fileName.lastIndexOf(".");
		
		if(lastIndex >=0 ) {
			extension = fileName.substring(lastIndex);
		}
		
		switch(extension) {
		case ".java":
			return "Java";
		case ".py":
			return "Python";
		case ".js":
			return "Javascript";
		case ".c":
			return "C";
		case ".cpp":
			return "CPP";
		case ".xml":
			return "XML";
		case ".css":
			return "CSS";
		case ".html":
			return "HTML";
		case ".gradle":
			return "Gradle";
		default:
			return "Plain";
		}
	}
	
	//getter for clear icon
	public FlatClearIcon getClearIcon()
	{
		return this.clearIcon;
	}
}
