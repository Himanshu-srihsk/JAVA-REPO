import java.util.HashMap;
import java.util.Objects;
/*Rules for Overriding hashCode() and equals():
Consistency: If two objects are equal according to equals(), they must have the same hash code.
Symmetry: If a.equals(b) returns true, then b.equals(a) must also return true.
Transitivity: If a.equals(b) and b.equals(c), then a.equals(c) must return true.
Non-nullity: If a.equals(b) is true, then a and b cannot be null.
Consistency with hashCode(): If two objects are equal, they must have the same hash code. However, objects with the same hash code may not necessarily be equal (because of hash collisions). */

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override hashCode() to generate a hash code based on name and age
    @Override
    public int hashCode() {
        return Objects.hash(name,age); // Generates a hash code using key fields
    }

    // Override equals() to check if two objects are "equal"
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age &&
               Objects.equals(name, person.name);
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class java_override_rule {
    public static void main(String[] args) {
        // Create a HashMap with Person objects as keys
        HashMap<Person, String> map = new HashMap<>();
        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Bob", 30);
        Person p3 = new Person("Alice", 25);  // Same properties as p1

        // Insert the objects into the HashMap
        map.put(p1, "Developer");
        map.put(p2, "Manager");

        // Test collision and equality
        System.out.println("p1 equals p3: " + p1.equals(p3));  // true
        map.put(p3, "Architect");  // This will replace the value for p1 (same key)

        // Display the HashMap contents
        for (Person person : map.keySet()) {
            System.out.println(person + " -> " + map.get(person));
        }
    }
}
/*p1 equals p3: true
Alice (25) -> Architect
Bob (30) -> Manager */