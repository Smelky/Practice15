package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.listener.ModelListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skills")
@EntityListeners(ModelListener.class)
public class Skill extends Model {

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private LevelType level;

    @ManyToMany(mappedBy = "skills")
    private Set<Developer> developers = new HashSet<>();
}
