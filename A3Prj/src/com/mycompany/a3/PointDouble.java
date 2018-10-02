package com.mycompany.a3;


public class PointDouble {

	
	private double x;	//x-value
	private double y;	//y-value
	
	
	/* This is the default constructor. It will set both points to zero if not given.
	 */
	public PointDouble(){
		this.x = 0.0;
		this.y = 0.0;
	}
	
	/* This is the alternate constructor that accepts two points for input.
	 */
	public PointDouble( double xval, double yval ){
		this.x = xval;
		this.y = yval;
	}
	
	
	//This method returns the x-value for the point
	public double getX(){
		return this.x;
	}
	
	//This method returns the y-value for the point
	public double getY(){
		return this.y;
	}
	
	//This method will set/reset the x-value for the point
	public void setX( double xval ){
		this.x = xval;
	}
	
	//This method will set/reset the y-value for the point
	public void setY( double yval ){
		this.y = yval;
	}
	
	//This method will set/reset both values at once. 'X' followed by 'Y'
	public void setXY( double xval, double yval ){
		this.x = xval;
		this.y = yval;
	}
	
	//This method will return the point as a string
	public String toString(){
		double rdX = Math.round( this.x *  10.0 ) / 10.0;
		double rdY = Math.round( this.y *  10.0 ) / 10.0;
		return "(" + rdX + ", " + rdY + ")";
	}
	
}
