import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MyPanel extends JPanel{
	private Table myTable;
	private String[] days;
	private String[] months;
	private String[] years;
	private String currNote = "";
	private final int numDayes = 30;
	private final int numMonth = 12;
	private final int startYear = 1990;
	private final int endYear = 2200;

	private JComboBox dayBox , monthBox , yearBox;
	private JButton cmdSave , cmdFind;
	private JTextField note;

	public MyPanel(Table t){
		this.myTable = t;
		setLayout (new BoxLayout (this, BoxLayout.Y_AXIS)); 
		insertStaticValues();
		dayBox = new JComboBox(days);
		//dayBox.addActionListener(new ComboListener());
		monthBox = new JComboBox(months);
		//monthBox.addActionListener(new ComboListener());
		yearBox = new JComboBox(years);
		//yearBox.addActionListener(new ComboListener());
		add(dayBox);
		add(monthBox);
		add(yearBox);
		cmdSave = new JButton("Save");
		cmdFind = new JButton("Find");
		cmdSave.addActionListener(new ButtonListener());
		cmdFind.addActionListener(new ButtonListener());	
		note = new JTextField();
		note.setSize(500, 500);
		note.addActionListener(new NoteListener());
		note.setText(currNote);
		add(note);
		add(cmdSave);
		add(cmdFind);

	}
	


	private void insertStaticValues() {
		ArrayList<String> days = new ArrayList<String>();
		ArrayList<String> months = new ArrayList<String>();
		ArrayList<String> years = new ArrayList<String>();

		for(Integer i = 1 ; i <= numDayes ; i ++){
			days.add(i.toString());
		}
		for(Integer j = 1 ; j <= numMonth ; j ++){
			months.add(j.toString());
		}
		for(Integer k = startYear ; k <= endYear ; k ++ ){
			years.add(k.toString());
		}
		this.days = new String[days.size()];
		this.days = days.toArray(this.days);
		this.months = new String[months.size()];
		this.months = months.toArray(this.months);
		this.years = new String[years.size()];
		this.years = years.toArray(this.years);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cmdSave){
				MyDate date = new MyDate(Integer.parseInt((String) yearBox.getSelectedItem()),Integer.parseInt((String) monthBox.getSelectedItem()), Integer.parseInt((String) dayBox.getSelectedItem()));
				String str = note.getText();
				myTable.insert(date, str);
				String line = date.toString() + ";" + str;
				try {
					writeToFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(e.getSource() == cmdFind){
				MyDate date = new MyDate(Integer.parseInt((String) yearBox.getSelectedItem()),Integer.parseInt((String) monthBox.getSelectedItem()), Integer.parseInt((String) dayBox.getSelectedItem()));
				String str = myTable.getNote(date);
				note.setText(str);
				repaint();

			}

		}
		
		private void writeToFile() throws IOException{
			PrintWriter writer = new PrintWriter("diary.txt", "UTF-8");
			Set<MyDate> keys = myTable.getKeySet();
			for(MyDate key : keys ){
				writer.println(key.toString() + ";" + myTable.getNote(key));
				
			}
			
			writer.close();
		}

		private void loadnewNote(String line) {
			try(FileWriter fw = new FileWriter("diary.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw))
					{
				out.println(line);
					} catch (IOException e) {
					}

		}
	}
	private class NoteListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == note){
				currNote = note.getText();
			}

		}
	}
	
}
