import java.util.ArrayList;

public class Map extends Actor{
	private ArrayList<Road> map;
	
	public Map() {
		map = new ArrayList<Road>();
		for(int i = 0; i < 15; i++) {
			map.add(new Road(getWorld().getWidth(), getWorld().getHeight()/15));
			map.get(i).setX(0);
			map.get(i).setY(i * getHeight()/15);
		}
	}
	
	public void updateDisplay() {
		
	}

	@Override
	public void act(long now) {
		if(map.get(0).getY() + map.get(0).getHeight() < 0) {
			map.remove(0);
			map.add(new Road(getWorld().getWidth(), getWorld().getHeight()/15));
		}
		updateDisplay();
	}
	
}
