package hello.subentities;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class E {
    private Long id;

    public E() {
    }

    public E(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", E.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        E e = (E) o;
        return Objects.equals(id, e.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
