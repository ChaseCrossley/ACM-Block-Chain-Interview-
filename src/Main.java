import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean runARace = true;
        while (runARace) {
            Race r = new Race();
            r.setQuitStatement();
            r.runPolls();
            System.out.println("Would you like to do another poll?");
            System.out.println("Y/N");
            runARace = input.next().toLowerCase().equals("Y");
        }
    }
}