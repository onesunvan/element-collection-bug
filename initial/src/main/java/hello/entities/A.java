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
    @SequenceGenerator(sequenceName = "a_seq", name = "a_seq")
    private Long id;

    private String name;

    public A() {
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
