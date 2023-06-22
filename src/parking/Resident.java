package parking;

import java.util.Scanner;

public class Resident extends Customer {

	String addr;

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		String carNum = scan.next();
		ownCar = ParkingLot.findCar(carNum);
		if (ownCar == null) {
			ownCar = new Car();
			super.setOut(carNum);
		}
		ownCar.isfree = true;
		name = scan.next();
		addr = scan.next();
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

	@Override
	public int out() {
		// TODO Auto-generated method stub
		ownCar.out();
		log.add(ownCar.outTime);
		return 0;
	}

	@Override
	public String toString() {
		return addr + "芭林 " + name + "绊按丛\n" + ownCar
				+ "\n [林瞒 扁废] \n" + super.parkingLog();
	}
	
	@Override
	public String showDetail() {
		return name + "\n" + ownCar.simpledata() + "\n林家: " + addr; 
	}
	
}
