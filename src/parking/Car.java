package parking;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;

import mgr.Manageable;

public class Car implements Manageable {
	String carStr;
	public int carNum;
	public ParkingCell parkingArea = null;
	public String inTime;
	public boolean carState;
	public int price;
	public boolean isfree;
	public boolean firstCalc = true;
	public String outTime;
	long pMin = 0;

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		carStr = scan.next();
		carNum = scan.nextInt();
		String pA = scan.next();
		parkingArea = ParkingLot.findPC(pA);
		inTime = scan.next();
		carState = true;
		updateParkingCell();
	}

	void updateParkingCell() {
		// TODO Auto-generated method stub
		if (carState) {
			parkingArea.occupied = true;
		} else
			parkingArea.occupied = false;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(this);
	}

	@Override
	public String toString() {
		if (firstCalc) {
			beforeDis();
			firstCalc = false;
		}
		String s = null;
		s = "차량번호: " + carStr + carNum + "\n주차구역: "
		+ parkingArea.pName + "\n입차시간: " + inTime + "\n"
		+ pMin+ "분("+pMin/60+"시간 " + pMin%60 + "분)"+ " 주차하였습니다.";
		if (!isfree)
		s = s + "\n요금은 " + price + "원입니다.";
		return s;
	}

	public void beforeDis() {
		calTime();
		calPrice();
	}
	
	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return (kwd.contains("" + carNum));
	}

	public void calTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
		Date carDate = null;
		try {
			carDate = df.parse(inTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date now = (new Date());
		outTime = df.format(now);
		pMin = (now.getTime() - carDate.getTime()) / 60000;
	}

	public void calPrice() {
		price = (int) (ParkingLot.price * (pMin/ParkingLot.perMin));
	}
	
	public void applyDc(Discount dc) {
		pMin = (int)pMin - dc.dcMin;
		if (pMin < 0)
			pMin = 0;
		calPrice();
	}

	public void out() {
		// TODO Auto-generated method stub
		carState = false;
		updateParkingCell();
		pMin = 0;
	}

	public String simpledata() {
		// TODO Auto-generated method stub
		return carStr + " " + carNum;
	}
}
