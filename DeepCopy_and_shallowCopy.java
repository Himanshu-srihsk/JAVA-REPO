class Address {
    String city;

    Address(String city) {
        this.city = city;
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow copy using clone() method
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();  // Creates a shallow copy
    }
}

public class DeepCopy_and_shallowCopy {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("New York");
        Person person1 = new Person("John", address);

        // Create shallow copy of person1
        Person person2 = (Person) person1.clone();

        // Changing the address in person2 will also change it for person1
        person2.address.city = "Los Angeles";

        System.out.println(person1.address.city);  // Output: Los Angeles (also changed)
        System.out.println(person2.address.city);  // Output: Los Angeles
    }
}


// class Address {
//     String city;

//     Address(String city) {
//         this.city = city;
//     }

//     // Method to create a deep copy of Address
//     protected Address clone() {
//         return new Address(this.city);
//     }
// }

// class Person implements Cloneable {
//     String name;
//     Address address;

//     Person(String name, Address address) {
//         this.name = name;
//         this.address = address;
//     }

//     // Deep copy method
//     protected Object clone() throws CloneNotSupportedException {
//         // Create a deep copy of Person
//         Person clonedPerson = (Person) super.clone();
//         // Deep copy of Address object
//         clonedPerson.address = this.address.clone();
//         return clonedPerson;
//     }
// }

// public class Main {
//     public static void main(String[] args) throws CloneNotSupportedException {
//         Address address = new Address("New York");
//         Person person1 = new Person("John", address);

//         // Create deep copy of person1
//         Person person2 = (Person) person1.clone();

//         // Changing the address in person2 will NOT affect person1
//         person2.address.city = "Los Angeles";

//         System.out.println(person1.address.city);  // Output: New York (unchanged)
//         System.out.println(person2.address.city);  // Output: Los Angeles
//     }
// }


// Practical Applications:
// Shallow Copy is often sufficient when the object being copied contains only primitive fields or when sharing internal objects between copies is desirable (e.g., when changes to shared data should be reflected across multiple objects).
// Deep Copy is preferred when you need to ensure that changes to the copy will not affect the original object, especially in cases where mutable objects are referenced within the original object.