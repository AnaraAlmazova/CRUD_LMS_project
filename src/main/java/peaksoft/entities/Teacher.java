package peaksoft.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "last_name")
    private String lastName;

 @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH, CascadeType.MERGE})

 @JoinColumn(name = "course_id")
 private Course course;
    @Transient
    private Long courseId;

}
