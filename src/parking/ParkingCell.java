package parking;

import java.util.Scanner;

import mgr.Manageable;

public class ParkingCell implements Manageable {
	public String pArea;
	public int pNum;
	public String pName;
	public boolean occupied;

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		pName = scan.next();
		pArea = returnStr(pName);
		pNum = returnNum(pName);
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return pName.equals(kwd);
	}

	private String returnStr(String str) {
		return str.replaceAll("[0-9]", "");
	}

	int returnNum(String str) {
		return Integer.parseInt(str.replaceAll("[^0-9]", ""));
	}
	
}