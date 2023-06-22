package parking;

import java.util.Scanner;

import mgr.Manager;
import mgr.Factory;
import mgr.Manageable;

public class ParkingLot {

	public static int price = 400;
	public static int perMin = 5;

	private static ParkingLot p = null;

	public static ParkingLot getInstance() {
		if (p == null)
			p = new ParkingLot();
		return p;
	}

	Scanner scan = new Scanner(System.in);
	public static Manager carMgr = new Manager();
	public static Manager dcMgr = new Manager();
	public static Manager pcMgr = new Manager();
	public static Manager cstMgr = new Manager();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParkingLot pl = new ParkingLot();
		pl.run();
	}

	public void run() {
		// TODO Auto-generated method stub
		pcMgr.readAll("parkingCell.txt", new Factory() {
			public ParkingCell create() {
				return new ParkingCell();
			}
		});
		dcMgr.readAll("discount.txt", new Factory() {
			public Discount create() {
				return new Discount();
			}
		});
		carMgr.readAll("car.txt", new Factory() {
			public Car create() {
				return new Car();
			}
		});
		cstMgr.readAll("customer.txt", new Factory() {
			public Customer create() {
				return new Customer();
			}
		});
		cstMgr.readAll("resident.txt", new Factory() {
			public Resident create() {
				return new Resident();
			}
		});
		
	}

	public static Car findCar(String kwd) {
		return (Car) carMgr.find(kwd);
	}

	public static Discount findDc(String kwd) {
		return (Discount) dcMgr.find(kwd);
	}

	public static ParkingCell findPC(String kwd) {
		return (ParkingCell) pcMgr.find(kwd);
	}

	public static Customer findCst(String kwd) {
		return (Customer) cstMgr.find(kwd);
	}

}
