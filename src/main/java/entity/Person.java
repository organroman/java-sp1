package entity;

import java.io.Serializable;
import java.util.Objects;

public class Person extends AbstractEntity implements Serializable {
    private final String name;
    private final String surname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

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
