import java.io.FileNotFoundException;

import javax.swing.JFrame;


public class Test {
	private static int height = 500;
	private static int weidth = 500;
	public static void main(String[] args) throws FileNotFoundException {
		Table myTable = new Table();
		MyPanel p = new MyPanel(myTable);
		JFrame frame = new JFrame("Diary");
		frame.add(p);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(height, weidth);
		frame.setVisible(true);
		
	}

}
