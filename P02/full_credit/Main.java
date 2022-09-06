import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int hour, minute, second;
        Scanner in = new Scanner(System.in);
        System.out.print("Hours? ");
        hour = in.nextInt();
        System.out.print("Minute? ");
        minute = in.nextInt();
        System.out.print("Second? ");
        second = in.nextInt();

        Clock clock = new Clock(hour, minute, second);
        System.out.println("The time is " + clock);
    }
}