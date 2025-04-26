package tests;
import models.BST;
import java.util.Iterator;
import java.util.Map;

public class TestBST {
    public static void start() {
        testInsertAndGet();
        testDelete();
        testIterator();
    }

    public static void testInsertAndGet() {
        BST<Integer, String> tree = new BST<>();
        System.out.println("--- Testing Insert/Get ---");

        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        System.out.println("Get key=3: " + tree.get(3));
        System.out.println("Size after insertions: " + tree.size());
    }

    public static void testDelete() {
        BST<Integer, String> tree = new BST<>();
        System.out.println("\n--- Testing Delete ---");

        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.delete(5);
        System.out.println("Get key=5 after deletion: " + tree.get(5));
        System.out.println("Size after deletion: " + tree.size());
    }

    public static void testIterator() {
        BST<Integer, String> tree = new BST<>();
        System.out.println("\n--- Testing Iterator ---");

        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.put(2, "Two");
        tree.put(4, "Four");

        System.out.println("In-order traversal:");
        for (BST<Integer, String>.NodeWrapper elem : tree) {
            System.out.println("Key: " + elem.getKey() + ", Value: " + elem.getValue());
        }
    }
}