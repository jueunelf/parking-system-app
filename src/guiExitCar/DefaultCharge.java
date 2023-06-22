package guiExitCar;

public class DefaultCharge {
	int time;
	int price;

	public DefaultCharge(int t, int p) {
		time = t;
		price = p;
	}

	public Integer[] getObj() {
		// TODO Auto-generated method stub
		return new Integer[] { time, price };
	}
}
