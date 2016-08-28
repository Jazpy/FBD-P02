package Taqueria;

public class Main {

	public static void main(String[] args){
	    ClientFileIO clientDB = new ClientFileIO("db/Clientes.txt");
	    
	    clientDB.addClient("Juan Perez");
	    clientDB.addClient("Rosa Melo");
	    clientDB.addClient("Benito Camelo");
	    
	    clientDB.removeClient("Benito Camelo");
	    	    
	    clientDB.writeChanges();
	}
}