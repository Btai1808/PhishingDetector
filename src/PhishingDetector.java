
import java.io.BufferedReader;
        import java.io.FileReader;
        import java.util.HashMap;

public class PhishingDetector {

    private static HashMap<String, Integer> keywords = new HashMap<>();

    static {
        keywords.put("urgente", 1);
        keywords.put("importante", 1);
        keywords.put("renovación", 1);
        keywords.put("su cuenta está a punto de caducar", 2);
        keywords.put("ha ganado un premio", 2);
        keywords.put("Microsoft", 3);
        keywords.put("Google", 3);
        keywords.put("Bank of America", 3);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("mensaje.txt"));
        String line;
        int totalPoints = 0;
        while ((line = reader.readLine()) != null) {
            for (String keyword : keywords.keySet()) {
                int count = line.split(keyword).length - 1;
                totalPoints += keywords.get(keyword) * count;
            }
        }
        System.out.println("Puntuación total: " + totalPoints);
        for (String keyword : keywords.keySet()) {
            int count = line.split(keyword).length - 1;
            if (count > 0) {
                System.out.println("Palabra clave: " + keyword + ", Ocurrencias: " + count + ", Puntos: " + keywords.get(keyword) * count);
            }
        }
    }
}
