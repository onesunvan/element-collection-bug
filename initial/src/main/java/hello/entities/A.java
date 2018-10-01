package hello.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class A {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a_seq")
    @SequenceGenerator(sequenceName = "a_seq", name = "a_seq", allocationSize = 1)
    private Long id;

    private String name;

    public A() {
    }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public A(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
