package model.entity;

import model.listener.ModelListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "developers")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(ModelListener.class)
public class Developer extends Model {

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "salary")
    private int salary;

    @ManyToMany
    @JoinTable(name = "developer_skill", joinColumns = {@JoinColumn(name = "dev_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "developer_project", joinColumns = {@JoinColumn(name = "dev_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> projects = new HashSet<>();
}
