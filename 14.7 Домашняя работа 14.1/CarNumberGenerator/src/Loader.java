import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            int finalI = i;
            threads.add(new Thread(() -> {
                StringBuilder stringBuilder = new StringBuilder();

                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(String.format("res/numbers%d.txt", finalI));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

                for (int regionCode = 1; regionCode < 100; regionCode++) {
                    stringBuilder.setLength(0);
                    for (int number = 1; number < 1000; number++) {
                        for (char firstLetter : letters) {
                            for (char secondLetter : letters) {
                                for (char thirdLetter : letters) {
                                    stringBuilder.append(firstLetter);
                                    stringBuilder.append(padNumber(number, 3));
                                    stringBuilder.append(secondLetter);
                                    stringBuilder.append(thirdLetter);
                                    stringBuilder.append(padNumber(regionCode, 2));
                                    stringBuilder.append('\n');
                                }
                            }
                        }
                    }
                    writer.write(stringBuilder.toString());
                }

                writer.flush();
                writer.close();
            }));
        }

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}
