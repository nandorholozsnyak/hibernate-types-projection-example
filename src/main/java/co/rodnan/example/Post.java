package co.rodnan.example;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    private Long id;

    private String title;

    private String description;

    private String a;

    private String b;

    private String c;

}
