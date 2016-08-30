package Taqueria;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args){
		// Clientes.txt testing
	    ClientFileIO clientDB = new ClientFileIO("db/Clientes.txt");
	    
	    clientDB.addClient("Juan Perez");
	    clientDB.addClient("Rosa Melo");
	    clientDB.addClient("Benito Camelo");
	    
	    clientDB.removeClient("Benito Camelo");
	    
	    clientDB.addClient("Flex Plexico");
	    	    
	    clientDB.writeChanges();
	    
	    // Ordenes.txt testing
	    OrderFileIO orderDB = new OrderFileIO("db/Ordenes.txt");
	    
	    Order o1 = new Order(1, 1, 1, 1);
	    Order o2 = new Order(1, 2, 2, 2);
	    Order o3 = new Order(2, 2, 2, 2);
	    Order o4 = new Order(3, 3, 3, 3);
	    
	    orderDB.addOrder(o1);
	    orderDB.addOrder(o2);
	    orderDB.addOrder(o3);
	    orderDB.addOrder(o4);
	    
	    ArrayList<Order> orders = orderDB.getOrder(1);

	    for (Order o : orders)
	    	System.out.println(o.toString());
	}
}