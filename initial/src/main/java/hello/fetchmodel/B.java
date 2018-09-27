package hello.fetchmodel;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class B {
    @Id
    private Long id;

    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "b_id")
//    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 2)
    private List<C> cs;

    public List<C> getCs() {
        return cs;
    }

    public void setCs(List<C> cs) {
        this.cs = cs;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", B.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("cs=" + cs)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        B b = (B) o;
        return Objects.equals(id, b.id) &&
                Objects.equals(name, b.name) &&
                Objects.equals(cs, b.cs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cs);
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

    public B(Long id, String name, List<C> cs) {
        this.id = id;
        this.name = name;
        this.cs = cs;
    }
}
