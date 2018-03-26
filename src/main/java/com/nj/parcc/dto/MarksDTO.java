package com.nj.parcc.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Marks DTO Class
 * @author Sriram
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "sub", "mark" })
public class MarksDTO {

	@JsonProperty("id")
	private String id;
	
	@NotBlank(message="PARCC_008")
	@JsonProperty("sub")
	private String sub;

	
	@Digits(fraction = 0, integer = 3, message = "PARCC_005")
	@Min(value=0, message = "PARCC_005") @Max(value=100, message = "PARCC_005")
	@JsonProperty("mark")
	private String mark;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("sub")
	public String getSub() {
		return sub;
	}

	@JsonProperty("sub")
	public void setSub(String sub) {
		this.sub = sub;
	}

	@JsonProperty("mark")
	public String getMark() {
		return mark;
	}

	@JsonProperty("mark")
	public void setMark(String mark) {
		this.mark = mark;
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
