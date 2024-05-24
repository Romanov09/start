package application.main.start.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String header;
    private String text;
    private LocalDate date;
    @ManyToOne
    private Person person;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", person=" + person +
                '}';
    }
}
