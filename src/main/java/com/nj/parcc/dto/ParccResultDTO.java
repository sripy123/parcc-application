package com.nj.parcc.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * ParccResult DTO class
 * @author Sriram
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "name", "school", "marks" })
public class ParccResultDTO extends StatusDTO {

	@JsonProperty("id")
	private String id;
	
	@NotBlank(message="PARCC_006")
	@JsonProperty("name")
	private String name;
	
	@NotBlank(message="PARCC_007")
	@JsonProperty("school")
	private String school;
	
	@JsonProperty("marks")
	@Valid
	private List<MarksDTO> marks = null;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("school")
	public String getSchool() {
		return school;
	}

	@JsonProperty("school")
	public void setSchool(String school) {
		this.school = school;
	}

	@JsonProperty("marks")
	public List<MarksDTO> getMarks() {
		return marks;
	}

	@JsonProperty("marks")
	public void setMarks(List<MarksDTO> marks) {
		this.marks = marks;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
