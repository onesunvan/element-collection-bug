package hello.fetchmodel;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class F {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", F.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        F f = (F) o;
        return Objects.equals(id, f.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public F() {
    }

    public F(Long id) {
        this.id = id;
    }
}
