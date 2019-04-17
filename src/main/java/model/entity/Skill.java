package model.entity;

import model.listener.ModelListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skills")
@EntityListeners(ModelListener.class)
public class Skill extends Model {

    @Column(name = "level")
    private String level;

    @ManyToMany(mappedBy = "skills")
    private Set<Developer> developers = new HashSet<>();
}
