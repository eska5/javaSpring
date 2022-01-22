package AUI.Lab.schools.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "schools")
public class School {
    @Id
    @Column(unique = true)
    private String schoolName;
    private String country;
    private int year_of_foundation;

}
