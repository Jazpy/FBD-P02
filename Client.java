package Taqueria;

public class Client {
	private int id;
	private String name;
	
	public Client(int id, String name) {
		this.id = id;
		
		if(name.equals(""))
			this.name = "John Smith";
		else
			this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}	