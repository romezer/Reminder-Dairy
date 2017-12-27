import java.util.Objects;



public class MyDate{

	private int year;
	private int month;
	private int day;

	public MyDate(int year, int month , int day){
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay(){
		return this.day;
	}

	public int getMonth(){
		return this.month;
	}

	public int getYear(){
		return this.year;
	}
	
	public String toString(){
		return this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
	}
	@Override
	public boolean equals(Object other){
		if(other instanceof MyDate){
			MyDate date = (MyDate)other;
			if(this.day == date.getDay() && this.month == date.getMonth() && this.year == date.getYear()){
				return true;
			}
			return false;
		}
		return false;
	}
	@Override
    public int hashCode() {
		 int result = 17;
	        result = 31 * result + this.year;
	        result = 31 * result + this.month;
	        result = 31 * result + this.day;
	        return result;
    }


}
