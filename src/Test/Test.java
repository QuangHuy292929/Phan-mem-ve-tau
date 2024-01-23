package Test;

import javax.swing.UIManager;
import view.BanVeTauView;

public class Test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new BanVeTauView();
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}

}
