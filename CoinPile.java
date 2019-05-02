import java.util.ArrayList;

public class CoinPile<T> {
	
	int location;
	int total;
	
	public CoinPile() {
		location = 0;
		total = 1;
	}
	public CoinPile(int l, int t) {
		location = l;
		total = t;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	public int getTotal() {
		return this.total;
	}
	public void reOrder(ArrayList<CoinPile<T>> p) {
		for(int k = 0; k < p.size(); k++) {
			for(int c = 0; c < p.size(); c++) {
				if(p.get(k).getTotal() > p.get(c).getTotal()) {
					CoinPile<T> temp = p.get(c);
					p.set(c, p.get(k));
					p.set(k, temp);
				}
			}
		}
	}
}
