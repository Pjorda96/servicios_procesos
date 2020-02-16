package seguridad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Desencriptador {

	public static void main(String[] args) throws IOException {
		String text = "";
		String currentLine;

		BufferedReader reader = new BufferedReader(new FileReader("cifradoCesar.txt"));
		while((currentLine = reader.readLine()) != null) {
			text += currentLine;
		}
		reader.close();

		Map<Character, Integer> char_counter = new HashMap<>();
		for(int i = 0; i < text.length(); i++) {
			Character c = text.charAt(i);

			if(char_counter.containsKey(c)) {
				Integer counter = char_counter.get(c);
				counter += 1;
				char_counter.replace(c, counter);
			}else {
				char_counter.put(c, 1);
			}
		}

		AtomicReference<Character> charMax = new AtomicReference<>('\u0000');
		AtomicReference<Integer> countMax = new AtomicReference<>(0);

		char_counter.forEach((c, counter) -> {
			if(counter >= countMax.get()) {
				charMax.set(c);
				countMax.set(counter);
			}
		});

		char e = charMax.get();

		int desplazamiento = e - 32;//El espacio en blanco es el carácter más repetido en el español escrito (13 espacios, 11 'e')
		String decriptedText = "";

		for(int i = 0; i < text.length(); i++) {
			char decriptedChar = (char) ((int) text.charAt(i) - desplazamiento);
			decriptedText += decriptedChar;
		}

		System.out.println(decriptedText);
	}
	
}
