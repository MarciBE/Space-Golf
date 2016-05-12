package spelelementen;

import java.awt.Color;
import java.awt.Graphics;

import tools.Vector;

public class OutOfBoundsBox {

	public static Vector box_plaats(Bal bal, int breedte, int hoogte) {
		Vector midden = new Vector(breedte / 2, hoogte / 2);
		Vector midden_to_bal = Vector.aftrekking(bal.getPlaats(), midden);
		double theta;
		if (midden_to_bal.getY() <= 0)
			theta = Math.acos(midden_to_bal.getX() / midden_to_bal.modulus());
		else
			theta = 2* Math.PI- Math.acos(midden_to_bal.getX() / midden_to_bal.modulus());
		double phi = Math.atan(((double) hoogte)/ (double)breedte);
		double x;
		double y;
		double rico = (bal.getPlaats().getY()-midden.getY())/(bal.getPlaats().getX()-midden.getX());
		if (theta>=2*Math.PI-phi){
			x = breedte-50;
			y = rico*(x - midden.getX())+midden.getY();
		}else if (theta>=Math.PI+phi){
			y = hoogte-70;
			x = (1/rico)*(y - midden.getY())+midden.getX();
		}else if (theta>=Math.PI-phi){
			x = 0;
			y = rico*(x - midden.getX())+midden.getY();
		}else if (theta>=phi){
			y = 0;
			x = (1/rico)*(y - midden.getY())+midden.getX();
		}else{
			x = breedte-50;
			y = rico*(x - midden.getX())+midden.getY();
		}
		return new Vector(x,y);
	}
	
	private static double distance(Bal bal, int breedte, int hoogte) {
		double afstand = Vector.aftrekking(bal.getPlaats(), box_plaats(bal, breedte,hoogte)).modulus();
		return afstand;
	}
	
	public static void drawString(Graphics g,Bal bal,int breedte, int hoogte) {
		Vector box = box_plaats(bal, breedte,hoogte);
		g.setColor(Color.WHITE);
		int distance = (int) distance(bal, breedte, hoogte);
		String distancestring = Integer.toString(distance);
		if (Integer.toString(distance).length()>4){
			distancestring = "infinite";
		}
		g.drawString(distancestring, (int) box.getX()+2, (int) box.getY()+18);
	}
	
	public static void drawBox(Graphics g,Bal bal,int breedte, int hoogte) {
		Vector box = box_plaats(bal, breedte,hoogte);
		g.setColor(Color.WHITE);
		g.drawRect((int) box.getX(), (int) box.getY(), 30,30 );
	}
	
}
