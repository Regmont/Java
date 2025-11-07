import classes.*;

import java.io.*;
import java.util.Vector;

public class ClientRunner {
    public static void main(String[] args) {
        ClientRunner.task_1();
        ClientRunner.task_2();
        ClientRunner.task_3();
        ClientRunner.task_4();
        ClientRunner.task_5();
    }

    public static void task_1() {
        Animal[] animals = new Animal[2];
        animals[0] = new Animal("Generic", 3);
        animals[1] = new Dog("Buddy", 2);

        for (Animal animal : animals) {
            animal.makeSound();
        }
    }

    public static void task_2() {
        Circle circle = new Circle("red", 5.0);
        circle.draw();
        circle.resize(2.0);
        System.out.println("New radius: " + circle.getRadius());
    }

    public static void task_3() {
        Vector<Pair<String, Integer>> vector = new Vector<>();
        vector.add(new Pair<>("Alice", 25));
        vector.add(new Pair<>("Bob", 30));
        vector.add(new Pair<>("Charlie", 28));

        System.out.println(vector);
    }

    public static void task_4() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                if (lineNumber % 2 == 0) {
                    writer.write(line);
                    writer.write(System.lineSeparator());
                }
                lineNumber++;
            }

            reader.close();
            writer.close();

            BufferedReader outputReader = new BufferedReader(new FileReader("output.txt"));

            System.out.println("Contents of output.txt:");

            while ((line = outputReader.readLine()) != null) {
                System.out.println(line);
            }

            outputReader.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void task_5() {
        Person person = new Person("Alice", 25, 123);
        person.serialize("person.ser");

        Person deserializedPerson = Person.deserialize("person.ser");
        System.out.println(deserializedPerson);
    }
}
