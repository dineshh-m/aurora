package app;

import java.awt.Color;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;

//import com.formdev.flatlaf.FlatArcOrangeIJTheme;
//import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaIJTheme;
public class Main {

	public static void main(String[] args) {
		
		try {
		//	FlatDarculaLaf.setup();
		//	UIManager.setLookAndFeel(new FlatDarculaLaf());
		//	FlatDarculaLaf.setup();
			//FlatDarkFlatIJTheme.setup();
			FlatDarculaLaf.setup();
			UIManager.put("TabbedPane.showTabSeparators", false);
			//UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
			UIManager.put("TabbedPane.selectedBackground", new Color(0, 0, 0, 13));
			UIManager.put("Component.focusWidth", 0);
			UIManager.put("Component.innerFocusWidth", 0);
		}catch(Exception ex) {
			System.err.println("Failed to load theme");
		}
		
		Window window = new Window();
	}

}
