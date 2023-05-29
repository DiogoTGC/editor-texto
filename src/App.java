import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App {
	public static void main(String[] args) {
		String[] texto = new String[200];
		Scanner input = new Scanner(System.in);
		int c = 0;
		int escrevendo = 1;

		texto = carregar_texto();
		c = ponto_inicial(texto);

		while (escrevendo != 0) {
			mostrar_texto(texto);

			System.out.println("Digite uma frase");
			String linha = input.nextLine();
			
			escrevendo = checar_texto(linha);
			if (escrevendo == 2) {
				String[] palavras = linha.split(" ");
				int num_linha = Integer.parseInt(palavras[1]);
				
				System.out.println("A frase a ser editada é:");
				System.out.printf("%s\n", texto[num_linha]);
				
				texto[num_linha] = editar(palavras[0], num_linha);
			} else { 
				if (escrevendo == 1) {
					texto[c] = linha;
					c++;
				} else {
					if (escrevendo == 3){
						deletar_tudo(texto);
					} else {
						if (escrevendo == 0) {
							criar_arquivo();
						}
					}
					gravar_arquivo(texto);
				}
			}
		}
		input.close();
	}

	public static String[] carregar_texto() {
		String[] texto_arquivo = new String[200];
		int i = 0;
		try {
			File myObj = new File("arquivo.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				texto_arquivo[i] = myReader.nextLine();
				i++;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Não tem nenhum arquivo para pegar os dados.");
		}
		return texto_arquivo;
	}

	public static int ponto_inicial(String[] vetor) {
		int inicio = 0;
		
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] != null) {
				inicio++;
			}
		}
		return inicio;
	}

	public static void mostrar_texto(String[] texto) {
		System.out.println("----TEXTO----");
			for (int i = 0; i < texto.length; i++) {
				if (texto[i] != null) {
					System.out.printf("%d %s\n", i, texto[i]);
				}
			}
		System.out.println("-------------");	
	}

	public static int checar_texto(String linha) {
		String[] palavras = linha.split(" ");
		if (palavras[0].equals("/FIM")) {
			return 0;
		} else {
			if (palavras[0].equals("/EDITAR")) {
				return 2;
			} else {
				if (palavras[0].equals("/DELETEALL")) {
					return 3;
				}
			}
		}
		return 1;
	}

	public static String editar(String linha, int num_linha) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Digite a nova frase:");
		linha = sc.nextLine();

		return linha;		
	}

	public static void deletar_tudo(String[] vetor) {
		for (int i = 0; i < vetor.length; i++) {
			vetor[i] = null;
		}
	}

	public static void criar_arquivo() {
		try {
			File arquivo = new File("arquivo.txt");
			if (arquivo.createNewFile()) {
			  	System.out.println("Arquivo criado: " + arquivo.getName());
			} else {
			  	System.out.println("Arquivo ja existente.");
			}
		} catch (IOException e) {
			System.out.println("Um erro aconteceu.");
			e.printStackTrace();
		}
	}

	public static void gravar_arquivo(String[] texto) {
		try {
			FileWriter myWriter = new FileWriter("arquivo.txt");
			
			for (int i = 0; i < texto.length; i++) {
				if (texto[i] != null) {
					String linha = texto[i] + "\n";
					myWriter.write(linha);
				}
			}
			myWriter.close();
			System.out.println("Gravado com sucesso");
		} catch (IOException e) {
			System.out.println("Aconteceu um erro");
			e.printStackTrace();	
		}
	}
}