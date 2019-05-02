import java.util.ArrayList;

public class PogoStick<T> {
	
	int cost;
	int dist;
	
	public PogoStick(int d) {
		cost = 0;
		dist = d;
	}
	public PogoStick(int c, int d){
		cost = c;
		dist = d;
	}
	public PogoStick(PogoStick<Integer> p) {
		cost = p.getCost();
		dist = p.getDist();
	}
	public int getDist() {
		return this.dist;
	}
	public int getCost() {
		return this.cost;
	}
	public void setDist(int d) {
		this.dist = d;
	}
	public void setCost(int c) {
		this.cost = c;
	}
	
	public int getDistTotal(ArrayList<PogoStick<T>> p) {
		int total = 0;
		
		for(int k = 0; k < p.size(); k++) {
			total += p.get(k).getDist();
		}
		
		return total;
	}
	public int getDistTotal(ArrayList<PogoStick<T>> p, int k) {
		int total = 0;
		
		for(; k > -1; k--) {
			total += p.get(k).getDist();
		}
		
		return total;
	}
	public int getCostTotal(ArrayList<PogoStick<T>> p) {
		int total = 0;
		
		for(int k = 0; k < p.size(); k++) {
			total += p.get(k).getCost();
		}
		
		return total;
	}
	public void reOrder(ArrayList<PogoStick<T>> p) {
		
		for(int k = 0; k < p.size(); k++) {
			for(int c = 0; c < p.size(); c++) {
				if(p.get(k).getDist() > p.get(c).getDist()) {
					PogoStick<T> temp = p.get(c);
					p.set(c, p.get(k));
					p.set(k, temp);
				}
			}
		}
	}
	
	public boolean pogoContain(ArrayList<PogoStick<T>> pogos, int temp) {
		
		for(int k = 0; k < pogos.size(); k++) {
			if(pogos.get(k).getDist() == temp) {
				return true;
			}
		}
		
		return false;
	}
}
