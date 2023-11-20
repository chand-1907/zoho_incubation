import java.util.*;

public class TrafficSignalApp {
	public static void main (String args[]) {
		
		TrafficSignal tf = new TrafficSignal();
		
		Thread redLight = new Thread(new TrafficLight(tf, LightColor.RED));
		Thread yellowLight = new Thread(new TrafficLight(tf, LightColor.YELLOW));
		Thread greenLight = new Thread(new TrafficLight(tf, LightColor.GREEN));
	
		redLight.start();
		greenLight.start();
		yellowLight.start();
	}
}

enum LightColor {
	RED, GREEN, YELLOW;
}

class TrafficSignal {
	private LightColor currentColor = LightColor.RED;
	
	public synchronized LightColor getCurrentColor () {
		return currentColor;
	}
	
	public void changeColor () {
		
		switch (currentColor) {
			case RED : currentColor = LightColor.YELLOW;
			break;
			
			case YELLOW : currentColor = LightColor.GREEN;
			break;
			
			case GREEN : currentColor = LightColor.RED;
			break;
		}
	}
}

class TrafficLight implements Runnable {
	
	LightColor lightColor;
	TrafficSignal trafficSignal;
	
	public TrafficLight (TrafficSignal trafficSignal, LightColor lightColor) {
		this.lightColor = lightColor;
		this.trafficSignal = trafficSignal;
	}
	
	public void run () {
		while (true) {
			if (trafficSignal.getCurrentColor() == lightColor) {
				System.out.println(lightColor + " - Light is ON");
				
				try {
					Thread.sleep(3000);
				}
				
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				trafficSignal.changeColor();
			}
		}
	}
}

