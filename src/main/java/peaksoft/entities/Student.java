package peaksoft.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.StudyFormat;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "study_format")

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;


    @Transient
    private Long groupId;
    @ManyToOne( cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "group_id")
    private Group group;

}
