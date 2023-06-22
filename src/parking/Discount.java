package parking;

import java.util.Scanner;

import mgr.Manageable;

public class Discount implements Manageable {
	String dType;
	String dNum;
	public int dcMin;
	
	Discount() {}
	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		dType = scan.next(); 
		dNum = scan.next();
		dcMin = scan.nextInt() * 60;
		}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return (dType.equals("«“¿Œ±«") && (dNum.equals(kwd)));
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
}