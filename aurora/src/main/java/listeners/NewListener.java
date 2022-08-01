package listeners;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.icons.FlatClearIcon;

import app.Window;
import editor.SyntaxScrollPane;
import editor.SyntaxTextArea;
import editor.TabbedPane;

public class NewListener implements ActionListener, MouseListener{

	TabbedPane tabPane;
	String title;
	Window window;
	JButton btnClose;
	SyntaxTextArea textArea;
	SyntaxScrollPane scrollPane;
	JLabel lblTitle;
	
	public NewListener(Window window,TabbedPane tabPane, String title) {
		this.tabPane = tabPane;
		this.title = title;
		this.window = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("File")) {
			
			String title = this.title+"-"+window.i;
			textArea = new SyntaxTextArea();
			scrollPane = new SyntaxScrollPane(textArea, window);
			tabPane.addTab(title, scrollPane);
			int index = tabPane.indexOfTab(title);
			JPanel pnlTab = new JPanel(new GridBagLayout());
			pnlTab.setOpaque(false);
			lblTitle = new JLabel(title);
		    btnClose = new JButton(tabPane.getClearIcon());
		    window.list.add(lblTitle);
		    window.fileNameList.add(lblTitle.getText());
		    configureButton(btnClose);
			btnClose.setActionCommand(title);
			btnClose.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String tabTitle = e.getActionCommand();
					
					int tabindex = tabPane.indexOfTab(tabTitle);
					System.out.println(tabindex);
					if(tabindex >= 0) {
						tabPane.removeTabAt(tabindex);
						window.list.remove(tabindex); //temp code	
						window.fileNameList.remove(tabindex);
				    }
				}	
			});

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 1;

			pnlTab.add(lblTitle, gbc);

			gbc.gridx++;
			gbc.weightx = 0;
			pnlTab.add(btnClose, gbc);
		//	tabPane.setBackgroundAt(index, new Color(0, 0, 0, 30));
			
			tabPane.setTabComponentAt(index, pnlTab);
		//	tabPane.setBackgroundAt(index, new Color(0, 0, 0, Value.ALPHA_VALUE));//temp code 
			window.i++;
			tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		
		FlatClearIcon icon = (FlatClearIcon) button.getIcon();
		Graphics graphics = button.getGraphics();
		graphics.setColor(Color.orange);
		icon.paintIcon(button, graphics, 0, 0);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		
		FlatClearIcon icon = (FlatClearIcon) button.getIcon();
		Graphics graphics = button.getGraphics();
		graphics.setColor(Color.white);
		icon.paintIcon(button, graphics, 0, 0);
		
	}
	
	private void configureButton(JButton btnClose) {
		btnClose.setOpaque(false);
		btnClose.setFont(new Font(btnClose.getFont().getFamily(), Font.BOLD, btnClose.getFont().getSize()));
		btnClose.setForeground(Color.white);
		btnClose.setBorder(BorderFactory.createEmptyBorder());
		btnClose.setFocusPainted(false);
		btnClose.setBorderPainted(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setRolloverEnabled(false);
		btnClose.addMouseListener(this);
		btnClose.setToolTipText("Close");
	}
}
