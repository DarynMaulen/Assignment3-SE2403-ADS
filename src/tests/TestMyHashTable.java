package tests;
import models.MyHashTable;

public class TestMyHashTable {
    public static void start() {
        MyHashTable<Integer, String> table = new MyHashTable<>();
        System.out.println("--- Testing Basic Operations ---");

        table.put(1, "One");
        table.put(2, "Two");
        System.out.println("Get key=1: " + table.get(1));
        System.out.println("Get key=2: " + table.get(2));

        table.remove(1);
        System.out.println("Get key=1 after removal: " + table.get(1));

        table.put(3, "Three");
        System.out.println("Contains value 'Three': " + table.contains("Three"));
        System.out.println("Key for value 'Three': " + table.getKey("Three"));
    }

}
