package cyberthieves.entities;


//This class extends entity  it is at lower level to control individual entity
public abstract class Mob extends Entity {
	protected String name;
	protected int speed;
	protected boolean isMoving;
	protected int movingDir = 1;
	protected int sacle = 1;
	public Mob(String name,int x, int y, int speed){
		super();
		this.name = name;
		this.speed = speed;
		this.x = x;
		this.y = y;
	}
	
	// This funtion help in moving the objects from one place to another
	public void setLocation(int xa, int ya){
		if(xa !=0 && ya != 0){
			setLocation(xa,0);
			setLocation(0,ya);
			return;
		}
		
		//This will be a entity dependent function to check whether collision has taken place or not 
		if(!hasCollided(xa,ya)){
			if(ya <0){
				movingDir = 0;
			}
			if (ya >0){
				movingDir = 1;
			}
			if(xa <0){
				movingDir = 2;
			}
			if(xa >0){
				movingDir = 3;
			}
			
			x+= xa*speed;
			y+= ya*speed;
		}		
	}
	//abstract class to forward to the classes which extends this class 
	public abstract boolean hasCollided(int xa, int ya);
	
	//functions to get name of the entity
	public String getName(){
		return name;
	}
	

}
