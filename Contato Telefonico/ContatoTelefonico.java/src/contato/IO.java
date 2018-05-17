package contato;

import java.util.ArrayList;
import java.util.Scanner;

class Telefone{
	String foneID;
	int numero;
	
	public Telefone (String foneID, int numero) {
		this.foneID = foneID;
		this.numero = numero;
	}
	public String toString() {
		return "" + this.foneID + " : " + this.numero;
	}
	
}
class Contato{
	String nome;
	ArrayList<Telefone>telefones;
	
	public Contato (String nome) {
		this.nome = nome;
		this.telefones = new ArrayList<Telefone>(); 
	}
	public String toString() {
		return "" + this.nome + "" + this.telefones;
	}
	public boolean addFone(Telefone telefone) {
		for(Telefone amor : this.telefones) {
			if(amor.foneID.equals(telefone.foneID)) {
				throw new RuntimeException("FoneID já existe");
			}
		}	
		this.telefones.add(new Telefone(telefone.foneID, telefone.numero));
		return true;
		
	}
	public boolean rmFone (String foneID) {
		for (int i = 0; i < this.telefones.size();i++) {
			if(this.telefones.get(i).foneID.equals(foneID)) {
				telefones.remove(i);
				return true;
			}
		}
		return false;
	}
}
class Controller {
	Contato newContato;
	
	public Controller() {
		newContato = new Contato(null);
}

	public String oracle(String line){
		String ui[] = line.split(" ");

		if(ui[0].equals("help"))
			return "show, init _nome, addFone _id _numero, rmFone _id";
		else if(ui[0].equals("init"))
			newContato = new Contato(ui[1]);
		else if(ui[0].equals("show"))
			return "" + newContato;
		else if(ui[0].equals("addFone"))
			 newContato.addFone(new Telefone(ui[1], Integer.parseInt(ui[2])));
		else if(ui[0].equals("rmFone")) {
			 newContato.rmFone(ui[1]);
		}
		else
			return "comando invalido";
		return "done";
	}
}

public class IO {

	static Scanner receber = new Scanner(System.in);

	 static String tab(String text){
		return "  " + String.join("\n  ", text.split("\n"));
}

	public static void main(String[] args) {
		Controller cont = new Controller();
		System.out.println("Digite um comando ou help:");
		while(true){
			String line = receber.nextLine();
			try {
				System.out.println(tab(cont.oracle(line)));
			}catch(Exception e) {
				System.out.println(tab(e.getMessage()));
			}
		}
	}
}
