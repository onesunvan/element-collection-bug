package hello.fetchmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class B {
    @Id
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "B{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        B b = (B) o;
        return Objects.equals(id, b.id) &&
                Objects.equals(name, b.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public B() {
    }

    public B(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
