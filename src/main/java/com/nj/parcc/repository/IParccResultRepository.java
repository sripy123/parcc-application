package com.nj.parcc.repository;

import org.springframework.data.repository.CrudRepository;

import com.nj.parcc.entity.ParccResultEntity;
/**
 * CrudRepository class for ParccService Application.
 * @author Sriram
 *
 */
public interface IParccResultRepository extends CrudRepository<ParccResultEntity, Long> {
}