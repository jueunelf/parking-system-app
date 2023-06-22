package parking;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class Customer implements Manageable {
	public Car ownCar;
	public Discount myDc = new Discount();
	public String name;
	public ArrayList<String> log = new ArrayList<String>();

	@Override
	public void read(Scanner scan) {
		String carNum = scan.next();
		ownCar = ParkingLot.findCar(carNum);
		if (ownCar == null) {
			ownCar = new Car();
			setOut(carNum);
		}
		name = scan.next();
		myDc.read(scan);
		String token = null;
		while (true) {
			token = scan.next();
			if (token.contentEquals("0"))
				break;
			log.add(token);
		}
		if (ownCar.carState) {
			log.add(ownCar.inTime);
		}
	}
	
	void setOut(String str) {
		// TODO Auto-generated method stub
		ownCar.carStr = str.replaceAll("[0-9]", "");
		ownCar.carNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
		ownCar.carState = false;
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return "Á¤±â°í°´ "+name+"°í°´´Ô\n" +ownCar + "\n [ÁÖÂ÷ ±â·Ï] \n" + parkingLog();
	}
	
	public String parkingLog() {
		StringBuilder sb = new StringBuilder();
		for(String s : log) {
			if (log.indexOf(s) % 2 == 0)
				sb.append(s +"\t");
			else
				sb.append(s +"\n");
		}
		return sb.toString();
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		if (kwd.equals(name))
			return true;
		if (ownCar == null)
			return false;
		return ownCar.matches(kwd);
	}

	public void addTime(int time) {
		// TODO Auto-generated method stub
		myDc.dcMin = myDc.dcMin + time * 60;
	}
	
	public String showDetail() {
		return  name + "\n" + ownCar.simpledata() + "\n" + remainTime() + "\n";
		}

	public String remainTime() {
		return "ÀÜ¿©½Ã°£: " + myDc.dcMin/60 + "½Ã°£" + myDc.dcMin%60 + "ºÐ";
	}
	public int out() {
		// TODO Auto-generated method stub
		int pay = 0;
		if (myDc.dcMin >= (int)ownCar.pMin) {
			myDc.dcMin = myDc.dcMin - (int)ownCar.pMin;
		}
		else {
			ownCar.pMin = ownCar.pMin - (long)myDc.dcMin;
			ownCar.calPrice();
			pay = ownCar.price;
			myDc.dcMin = 0;
		}
		ownCar.out();
		log.add(ownCar.outTime);
		return pay;
	}
	

}