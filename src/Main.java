import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Integer> phishingKeywords = new HashMap<>();

    public static void main(String[] args) {
        initializePhishingKeywords();
        int totalPoints = exploreTextFile("ruta_al_archivo_de_texto");
        System.out.println("Total de puntos para todo el mensaje: " + totalPoints);
    }

    private static void initializePhishingKeywords() {
        // Lista de palabras clave de phishing a la lista
        phishingKeywords.put("banco", 3);
        phishingKeywords.put("tarjeta de crédito", 3);
        // Agrega más palabras clave según tu investigación
    }

    private static int exploreTextFile(String filePath) {
        int totalPoints = 0;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (String keyword : phishingKeywords.keySet()) {
                    if (line.contains(keyword)) {
                        int occurrences = countOccurrences(line, keyword);
                        int points = phishingKeywords.get(keyword);

                        totalPoints += occurrences * points;

                        System.out.println("Palabra o frase: " + keyword);
                        System.out.println("Número de ocurrencias: " + occurrences);
                        System.out.println("Total de puntos: " + (occurrences * points));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo encontrar el archivo: " + filePath);
        }

        return totalPoints;
    }

    private static int countOccurrences(String line, String keyword) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(keyword, index)) != -1) {
            count++;
            index += keyword.length();
        }
        return count;
    }
}
