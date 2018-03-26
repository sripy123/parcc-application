package com.nj.parcc.rest;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nj.parcc.dto.ParccResultDTO;
import com.nj.parcc.exception.ErrorMessages;
import com.nj.parcc.exception.ParccServiceException;
import com.nj.parcc.service.ParccMessageService;
import com.nj.parcc.service.ParccService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * Rest controller class for Parcc Service application.
 * @author Sriram
 *
 */
@Validated
@RestController
@Api(value = "/parcc", description = "NJ Parcc Students Services")
public class ParccStudentController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	public ParccService parccService;

	@Autowired
	public ParccMessageService parccMsgService;

	@RequestMapping(value = { "/parcc/v1/{id}",
			"/parcc/v1" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rest service to get the Parcc Test results of NJ Students", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request"),
			@ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "No student(s) result found"),
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Parcc test resulsts Returned successfully."),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server issue, contact Administrator.") })
	public @ResponseBody ResponseEntity<List<ParccResultDTO>> getStudentResults(@PathVariable("id") Optional<String> id,
			HttpServletRequest request) {
		logger.info("Rest call service to get Parcc student Resulsts");
		HttpStatus httpStatus = HttpStatus.OK;
		List<ParccResultDTO> parccList = null;
		try {
			if (id.isPresent()) {
				parccList = Stream.of(parccService.getParccResults(Long.valueOf(id.get())))
						.collect(Collectors.toCollection(ArrayList::new));
			} else {
				parccList = parccService.getParccResults();
			}
			
			if (parccList.isEmpty()) {
				httpStatus = HttpStatus.NO_CONTENT;
			} else {
				logger.info("Parcc student Resulsts size "+parccList.size());
			}
		} catch (ParccServiceException pe) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<ParccResultDTO>>(parccList, httpStatus);
	}

	@RequestMapping(value = { "/parcc/v1" }, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rest service to update the Parcc Test result of a student", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request"),
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Parcc student result updated successfully."),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server issue, contact Administrator.") })
	public @ResponseBody ResponseEntity<ParccResultDTO> updateStudentResult(
			@ApiParam @RequestBody @Valid ParccResultDTO parccResultDTO, @PathVariable("id") Optional<String> id,
			HttpServletRequest request) {
		logger.info("Rest call service to Update the Parcc student Result");
		HttpStatus httpStatus = HttpStatus.OK;
		ParccResultDTO response = null;
		try {
			response = parccService.updateParccResult(parccResultDTO);
			response.setCode(ErrorMessages.PARCC_002.getCode());
			response.setMsg(parccMsgService.getErrorMessage(ErrorMessages.PARCC_002));
		} catch (ParccServiceException ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ParccResultDTO>(response, httpStatus);
	}

	@RequestMapping(value = { "/parcc/v1" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rest service to create the Parcc Test result of a student", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request"),
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Parcc student result created successfully."),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server issue, contact Administrator.") })
	public @ResponseBody ResponseEntity<ParccResultDTO> createStudentResult(
			@ApiParam @RequestBody @Valid ParccResultDTO parccResultDTO, HttpServletRequest request) {
		logger.info("Rest call service to Create the Parcc student Result");
		HttpStatus httpStatus = HttpStatus.OK;
		ParccResultDTO response = null;
		try {
			response = parccService.createParccResult(parccResultDTO);
			response.setCode(ErrorMessages.PARCC_001.getCode());
			response.setMsg(parccMsgService.getErrorMessage(ErrorMessages.PARCC_001));
		} catch (ParccServiceException ex) {
			response = new ParccResultDTO();
			response.setCode(ErrorMessages.PARCC_500.getCode());
			response.setMsg(parccMsgService.getErrorMessage(ErrorMessages.PARCC_500));
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<ParccResultDTO>(response, httpStatus);
	}

	@RequestMapping(value = { "/parcc/v1/{id}",
			"/parcc/v1" }, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rest service to delete the Parcc Test result of a student", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request"),
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Parcc student result deleted successfully."),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server issue, contact Administrator.") })
	public @ResponseBody ResponseEntity<ParccResultDTO> deleteStudentResult(@PathVariable("id") Optional<String> id,
			HttpServletRequest request) {
		logger.info("Rest call service to Delete the Parcc student Result");
		HttpStatus httpStatus = HttpStatus.OK;
		ParccResultDTO response = new ParccResultDTO();
		if (id.isPresent()) {
			try {
				parccService.deleteParccResult(Long.parseLong(id.get()));
				response.setCode(ErrorMessages.PARCC_004.getCode());
				response.setMsg(parccMsgService.getErrorMessage(ErrorMessages.PARCC_004));
			} catch (ParccServiceException ex) {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			response.setCode(ErrorMessages.PARCC_003.getCode());
			response.setMsg(parccMsgService.getErrorMessage(ErrorMessages.PARCC_003));
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<ParccResultDTO>(response, httpStatus);
	}

}
