import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;


public class Table {
	private Hashtable<MyDate,String> table;

	
	public Table() throws FileNotFoundException{
		table = new Hashtable<MyDate,String>();
		loadFromSource();
	}
	
	private void loadFromSource () throws FileNotFoundException {
		Scanner input = new Scanner(new File("diary.txt"));
		while(input.hasNext()){
			String st = input.nextLine();
			String[] str = st.split(";");
			String date = str[0];
			String note = str[1];
			String[] dateSplit = date.split("/");
			int year = Integer.parseInt(dateSplit[2]);
			int month = Integer.parseInt(dateSplit[1]);
			int day = Integer.parseInt(dateSplit[0]);
			MyDate tempDate = new MyDate(year,month,day);
			insert(tempDate, note);
		}
		input.close();
		
	}

	public void insert(MyDate date, String str){
		table.put(date, str);
	}
	
	public String getNote(MyDate date){
		return table.get(date);
	}
	public Set<MyDate> getKeySet(){
		return table.keySet();
	}

}
