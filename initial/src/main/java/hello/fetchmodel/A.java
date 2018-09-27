package hello.fetchmodel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class A {
    @Id
    private Long id;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "a_id")
    @Fetch(FetchMode.JOIN)
    private Set<B> bs;

    private String name;

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", bs=" + bs +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a = (A) o;
        return Objects.equals(id, a.id) &&
                Objects.equals(bs, a.bs) &&
                Objects.equals(name, a.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bs, name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<B> getBs() {
        return bs;
    }

    public void setBs(Set<B> bs) {
        this.bs = bs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A() {
    }

    public A(Long id, Set<B> bs, String name) {
        this.id = id;
        this.bs = bs;
        this.name = name;
    }
}
