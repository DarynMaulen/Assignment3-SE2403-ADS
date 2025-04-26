package tests;
import models.MyHashTable;
import models.MyTestingClass;
import models.Student;
import java.util.Random;

public class UniformDistributionTest {
    public static void start(){
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random random = new Random();
        System.out.println("--- Testing Uniform Distribution ---");

        for (int i = 0; i < 10000; i++) {
            int randomId = random.nextInt(10000);
            String randomName = "Name" + random.nextInt(10000);
            MyTestingClass key = new MyTestingClass(randomId,randomName);
            table.put(key, new Student("Student" + i, random.nextInt(100)));
        }
        table.printBucketSizes();
    }
}
