package statusbar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class StatusBar extends JPanel{
	
	JLabel label, langLabel, path;
	String lang="", line="";
	Color labelForeground;
	JToolBar toolBar;
	
	
	//constructor
	public StatusBar() {
		toolBar = new JToolBar();
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBackground(new Color(51, 153, 255));
		this.setPreferredSize(new Dimension(this.getWidth(), 16));
		label = new JLabel("   ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		langLabel = new JLabel("");
		langLabel.setHorizontalAlignment(SwingConstants.LEFT);
		path = new JLabel("");
		labelForeground = new Color(255, 255, 255);
		
		//setBorder(BorderFactory.createEtchedBorder());
		setLabelForeground();
		this.add(langLabel);
		this.add(path);
		this.add(label);
	}
	
	public void setLine(String line) {
		this.line ="                                          "+ line;
		updateLabel();
	}
	
	public void setLang(String lang) {
//		this.lang = lang;
//		updateLabel();
		langLabel.setText("        "+lang);
	}
	
	private void updateLabel() {
		label.setText("  "+line+"    ");
	}
	
	public void setPath(String path) {
		this.path.setText("             "+path);
	}
	
	private void setLabelForeground() {
		this.label.setForeground(labelForeground);
		this.langLabel.setForeground(labelForeground);
		this.path.setForeground(labelForeground);
	}
}
