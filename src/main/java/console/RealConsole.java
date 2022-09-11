package console;

import java.util.Scanner;

public class RealConsole implements Console{
    Scanner scanner = new Scanner(System.in);

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }

    @Override
    public void print(String line) {
        System.out.print(line);
    }
}
