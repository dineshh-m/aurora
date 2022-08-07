package explorer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import com.formdev.flatlaf.icons.FlatClearIcon;

import app.Icon;
import app.Window;
import editor.SyntaxScrollPane;
import editor.SyntaxTextArea;
import editor.TabbedPane;
import values.Value;

public class ClickListener implements TreeSelectionListener, MouseListener {
	
	File directory;
	String filePath = "";
	JTree tree;
	String qualifiedPath = "";
	Window window;
	SyntaxTextArea textArea;
	SyntaxScrollPane scrollPane;
	TabbedPane tabPane;
	
	public ClickListener(File directory, JTree tree, Window window) {
		this.window = window;
		this.directory = directory;
		this.tree = tree;
		tabPane = window.getTabbedPane();
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
	//	System.out.println(e.getPath().toString());
		TreePath path = e.getPath();
		filePath = "";
		
		Object obj[] = path.getPath();
		
		for(int i = 1; i < path.getPathCount(); i++) {
			filePath += obj[i].toString();
			if(i != path.getPathCount() - 1) filePath +="\\";
		}
	//	System.out.println(filePath);
		qualifiedPath = directory.getAbsolutePath()+"\\"+filePath;
		System.out.println("Qualified Path: "+qualifiedPath);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		if(e.getSource() instanceof JTree) {
			if(e.getClickCount() == 2) {
				if(!filePath.isEmpty()) {
					File file = new File(qualifiedPath);
					if(window.fileNameList.contains(file.getName())) {
						int index = window.fileNameList.indexOf(file.getName());
						tabPane.setSelectedIndex(index);
					}
					else if(file.exists()) {
						if(file.isFile()) {
							openFile(file);
						}
					}
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	private void openFile(File file) {
		String str = "";
		try(Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
				str += scanner.nextLine() + "\n";
			}
			addTab(file, str);
		}catch(IOException e) {
			
		}
	}
	
	private void addTab(File file, String code) {
		
		String title = file.getName();
		textArea = new SyntaxTextArea();
		textArea.setText(code);
		scrollPane = new SyntaxScrollPane(textArea, window);
		scrollPane.setFile(file);
		scrollPane.setIsSaved(true);
		tabPane.addTab(title, scrollPane);
		int index = tabPane.indexOfTab(title);
		JPanel pnlTab = new JPanel(new GridBagLayout());
		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(title);
	    JButton btnClose = new JButton(tabPane.getClearIcon());
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
					window.list.remove(tabindex);
					window.fileNameList.remove(tabindex);//temp code
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
		window.i++;
		tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
		//tabPane.setBackgroundAt(index, new Color(0, 0, 0, Value.ALPHA_VALUE)); //temp code
		this.setExt(file, textArea);
	}
	
	private void configureButton(JButton btnClose) {
		btnClose.putClientProperty("JButton.buttonProperty", "roundRect");
		btnClose.setOpaque(false);
//		btnClose.setFont(new Font(btnClose.getFont().getFamily(), Font.BOLD, btnClose.getFont().getSize()));
//		btnClose.setForeground(Color.white);
//		btnClose.setBorder(BorderFactory.createEmptyBorder());
		btnClose.setFocusPainted(false);
//		btnClose.setBorderPainted(false);
		btnClose.setContentAreaFilled(false);
//		btnClose.setRolloverEnabled(false);
		btnClose.addMouseListener(new MouseAdapter() {
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
		});
		btnClose.setToolTipText("Close");
	}
	
	//extension setter
	private void setExt(File file, SyntaxTextArea textArea) {
		String fileName = file.getName();
		int lastIndex = fileName.lastIndexOf('.');
		String extension = "";
		
		if(lastIndex >= 0) extension = fileName.substring(lastIndex);
		
		if(!extension.isEmpty())
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
