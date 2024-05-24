import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestsGenerate {
    public static void main(String[] args) {
        Random r = new Random();
        int number = 0;
        for (int i = 1; i < 100; i++) {
            number+=100;
            File myFile = new File("text" + i + ".txt");

            try {
                FileWriter writer = new FileWriter("text" + i + ".txt");
                writer.write(number + "\n");
                for (int j = 1; j <= number; j++) {
                    int randomNumber2 = r.nextInt(9900) + 100;
                    writer.write(randomNumber2  + "\n");
                }
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
