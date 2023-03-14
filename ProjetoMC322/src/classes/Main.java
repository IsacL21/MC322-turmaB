package classes;

public class Main {

	public static void main(String[] args) {
		Cliente cliente_teste = new Cliente("Ronaldo", "100.323.254-11", "31/12/2000", "Av. Santa Isabel, 1125", 42);
		System.out.println(cliente_teste);
		Sinistro s1 = new Sinistro("a","a");
		Sinistro s2 = new Sinistro("a","a");
		System.out.println(s1.getId());
		System.out.println(s2.getId());
	}

}
