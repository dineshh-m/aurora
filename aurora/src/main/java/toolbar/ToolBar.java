package toolbar;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar{

	JButton demoButton;
	
	public ToolBar() {
		demoButton = new JButton("copy");
		this.add(demoButton);
	}
}
