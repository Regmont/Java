package classes;

import java.io.*;

public class Person implements Serializable {
    private final String name;
    private final int age;
    private transient int id = 0;

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public void serialize(String file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }
    }

    public static Person deserialize(String file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Person) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization error: " + e.getMessage());

            return null;
        }
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", id=" + id + "}";
    }
}
