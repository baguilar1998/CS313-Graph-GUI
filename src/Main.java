import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * @author Brian Aguilar
 */
public class Main {

	private static GraphGUI gui;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager
			.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
		}

		gui = new GraphGUI();

	}

}
