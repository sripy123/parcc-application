package com.nj.parcc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Entity class for Table Marks
 * @author Sriram
 *
 */
@Entity
@Table(name = "marks")
public class MarksEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "markid")
	private Long id;

	@Column(name = "mark")
	private int mark;
	
	@Column(name = "subject")
	private String subject;

	@ManyToOne
	@JoinColumn(name = "parccid", nullable = false)
	private ParccResultEntity parccResultEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public ParccResultEntity getParccResultEntity() {
		return parccResultEntity;
	}

	public void setParccResultEntity(ParccResultEntity parccResultEntity) {
		this.parccResultEntity = parccResultEntity;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	

}
