package com.nj.parcc.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
/**
 * Entity class for Parcc_result table
 * @author X205535
 *
 */
@Entity
@Table(name = "parcc_result")
public class ParccResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parccid")
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="school")
    private String school;
    
    @OneToMany(mappedBy="parccResultEntity",cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<MarksEntity> marksMaps = new HashSet<>(0);


    public ParccResultEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
    public Set<MarksEntity> getMarksMaps() {
        return marksMaps;
    }

    public void setMarksMaps(Set<MarksEntity> marksMaps) {
        this.marksMaps = marksMaps;
    }



	@Override
	public String toString() {
		return "ParccResultEntity [id=" + id + ", name=" + name + ", school=" + school + "]";
	}

   
}