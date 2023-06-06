package classes;

public class Validacao {
	
	private static boolean is_equal_characters(String str) {
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(0) != str.charAt(i))
				return false;
		}
		return true;
	}
	
	private static boolean has_valid_cpf_dv (String cpf) {
		int i,j;
		
		//verifica primeiro dv
		for (i = 0, j = 0; i < 9; i++)
			j += (10 - i) * (Integer.parseInt(String.valueOf(cpf.charAt(i))));
		if ((j % 11) == 1 || (j % 11) == 0) {
			if (cpf.charAt(9) != 0)
				return false;
		}
		else {
			if ((11 - (j%11)) != (Integer.parseInt(String.valueOf(cpf.charAt(9)))))
				return false;
		}
		
		//verifica segundo dv
		for (i = 0, j = 0; i < 10; i++)
			j += (11 - i) * (Integer.parseInt(String.valueOf(cpf.charAt(i))));
		if ((j % 11) == 1 || (j % 11) == 0) {
			if (cpf.charAt(10) != 0)
				return false;
		}
		else {
			if ((11 - (j%11)) != (Integer.parseInt(String.valueOf(cpf.charAt(10)))))
				return false;
		}
		
		return true;
	}
	
	
	public static boolean validarCPF (String cpf) {
		int n_digitos;
		cpf = cpf.replaceAll("[^0-9]","");
		
		n_digitos = cpf.length();
		if (n_digitos != 11)
			return false;
		
		if (is_equal_characters(cpf))
			return false;
		
		return has_valid_cpf_dv(cpf);
	}
	
	private static boolean has_valid_cnpj_dv (String cnpj) {
		int i, sum, weight, dv;
		
		//verifica primeiro dv
		sum = 0;
		weight = 11;
		for (i = 0; i < 12; i++) {
			sum += ((weight % 8) + 2) * (Integer.parseInt(String.valueOf(cnpj.charAt(i))));
			weight--;
		}
		dv = (sum % 11) > 2 ? (11 - (sum % 11)) : 0;
		if ((Integer.parseInt(String.valueOf(cnpj.charAt(12)))) != dv)
				return false;
		
		//verifica segundo dv
		sum = 0;
		weight = 12;
		for (i = 0; i < 13; i++) {
			sum += ((weight % 8) + 2) * (Integer.parseInt(String.valueOf(cnpj.charAt(i))));
			weight--;
		}
		dv = (sum % 11) > 2 ? (11 - (sum % 11)) : 0;
		if ((Integer.parseInt(String.valueOf(cnpj.charAt(13)))) != dv)
			return false;
		return true;
	}
	
	public static boolean validarCNPJ (String cnpj) {
		int n_digitos;
		cnpj = cnpj.replaceAll("[^0-9]","");
		
		n_digitos = cnpj.length();
		if (n_digitos != 14)
			return false;
		
		if (is_equal_characters(cnpj))
			return false;
		
		return has_valid_cnpj_dv(cnpj);
	}
	
	/*Retorna falso se o nome possuir caracteres diferentes de letras e espaços*/
	public static boolean validarNome (String nome) {
		for (int i = 1; i < nome.length(); i++) {
			if (nome.matches("(.*)[^[[A-Za-z]]| ](.*)"))
				return false;
		}
		return true;
	}
	
	
	

}
