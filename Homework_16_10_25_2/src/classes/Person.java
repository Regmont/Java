package classes;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.age = 0;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = 0;
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{firstName='" + firstName + "', lastName='" + lastName + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(firstName, lastName, age);
    }

    public void displayInfo() {
        System.out.println(this.toString());
    }

    public void displayInfo(boolean detailed) {
        if (detailed) {
            System.out.println("Detailed Person Info:");
            System.out.println("  First Name: " + firstName);
            System.out.println("  Last Name: " + lastName);
            System.out.println("  Age: " + age);
        } else {
            displayInfo();
        }
    }
}
