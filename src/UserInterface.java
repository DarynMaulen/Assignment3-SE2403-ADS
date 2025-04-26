import tests.TestBST;
import tests.TestMyHashTable;
import tests.UniformDistributionTest;

import java.util.Scanner;

public class UserInterface {
    public static void start(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n1. Test MyHashTable");
            System.out.println("2. Test Uniform Distribution");
            System.out.println("3. Test BST");
            System.out.println("4. Exit\n");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    TestMyHashTable.start();
                    break;
                case 2:
                    UniformDistributionTest.start();
                    break;
                case 3:
                    TestBST.start();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
            }

        }
    }
}
