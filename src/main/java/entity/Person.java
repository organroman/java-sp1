package entity;

public class Person {
    private final String name;
    private final String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Name='" + name + '\'' +
                ", surname='" + surname + '\'';
    }
}
