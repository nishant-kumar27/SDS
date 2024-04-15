package rispl.ds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.opensymphony.xwork2.ActionSupport;

public class DateUtil extends ActionSupport {
	private String date1;

	private String diffInDays;

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
		String dateFormat = getText("format.date");
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			Date date1D = sdf.parse(date1);
			Date dateToday = sdf.parse(sdf.format(new Date()));
			long diff = dateToday.getTime() - date1D.getTime();
		    long dateDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			System.out.println ("Days: " + dateDiff);
			setDiffInDays(String.valueOf(dateDiff));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getDiffInDays() {
		return diffInDays;
	}

	public void setDiffInDays(String diffInDays) {
		this.diffInDays = diffInDays;
	}

}
