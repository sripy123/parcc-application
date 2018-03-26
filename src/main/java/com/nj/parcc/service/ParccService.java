package com.nj.parcc.service;

import java.util.List;

import com.nj.parcc.dto.ParccResultDTO;
import com.nj.parcc.exception.ParccServiceException;
/**
 * Interface to handle the business logic of Parcc Service application.
 * @author Sriram
 *
 */
public interface ParccService {
	/**
	 * Method to fetch all the Parcc test results.
	 * @return List<ParccResultDTO>
	 * @throws ParccServiceException
	 */
	List<ParccResultDTO> getParccResults() throws ParccServiceException;
	/**
	 * Method to fetch the Parcc test result based on the given Parcc id
	 * @param id
	 * @return ParccResultDTO
	 * @throws ParccServiceException
	 */
	ParccResultDTO getParccResults(long id) throws ParccServiceException;
	/**
	 * Method to create the Parcc test result in the database
	 * @param parccResultDTO
	 * @return ParccResultDTO
	 * @throws ParccServiceException
	 */
	ParccResultDTO createParccResult(ParccResultDTO parccResultDTO) throws ParccServiceException;
	/**
	 * Method to update the Parcc test result in the database
	 * @param parccResultDTO
	 * @return ParccResultDTO
	 * @throws ParccServiceException
	 */
	ParccResultDTO updateParccResult(ParccResultDTO parccResultDTO) throws ParccServiceException;
	/**
	 * Method to delete the Parcc test result based on the given Parcc id
	 * @param id
	 * @throws ParccServiceException
	 */
	void deleteParccResult(long id) throws ParccServiceException;

	
}
