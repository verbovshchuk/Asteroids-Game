//collision interface, to be implemented by all objects that can collide
package com.mycompany.a3;

public interface ICollider 
{
	public int handleCollision(ICollider other);
	public boolean collidesWith (ICollider other);
	public void setDeath();
	public boolean isReadyToRemove();
	public boolean checkVector(ICollider other);
	public void addToVector(ICollider other);
	public void clearCollisions();
}
