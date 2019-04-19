package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.listener.ModelListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(ModelListener.class)
public class Customer extends Model{

    @Column(name = "country")
    private String country;

    @ManyToMany(mappedBy = "customers")
    private Set<Project> projects = new HashSet<>();
}
