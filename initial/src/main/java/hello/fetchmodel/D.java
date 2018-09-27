package hello.fetchmodel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
public class D {
    @Id
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "e",
            joinColumns = @JoinColumn(name = "e_id")
    )
    private Set<E> es;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "f",
            joinColumns = @JoinColumn(name = "f_id")
    )
    private Set<F> fs;

    @Override
    public String toString() {
        return new StringJoiner(", ", D.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("es=" + es)
                .add("fs=" + fs)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        D d = (D) o;
        return Objects.equals(id, d.id) &&
                Objects.equals(es, d.es) &&
                Objects.equals(fs, d.fs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, es, fs);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<E> getEs() {
        return es;
    }

    public void setEs(Set<E> es) {
        this.es = es;
    }

    public Set<F> getFs() {
        return fs;
    }

    public void setFs(Set<F> fs) {
        this.fs = fs;
    }

    public D() {
    }

    public D(Long id, Set<E> es, Set<F> fs) {
        this.id = id;
        this.es = es;
        this.fs = fs;
    }
}
