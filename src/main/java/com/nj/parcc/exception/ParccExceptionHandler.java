package com.nj.parcc.exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nj.parcc.dto.StatusDTO;
import com.nj.parcc.service.ParccMessageService;
/**
 * Exception handler class for Parcc service. This class is a sub class of ResponseEntityExceptionHandler 
 * Overrides the handler method to customize the exception handling logic.
 * @author Sriram
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Component
public class ParccExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private ParccMessageService messageByLocaleService;

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.error("Getting handleMethodArgumentNotValid exception");
		StatusDTO statusDto = new StatusDTO();
		if(ex.getMessage().indexOf(ErrorMessages.PARCC_005.getMatchEntry()) > -1 ) {
			statusDto.setCode(ErrorMessages.PARCC_005.getCode());
			statusDto.setMsg(messageByLocaleService.getErrorMessage(ErrorMessages.PARCC_005));
		} else if(ex.getMessage().indexOf(ErrorMessages.PARCC_006.getMatchEntry()) > -1 ) {
			statusDto.setCode(ErrorMessages.PARCC_006.getCode());
			statusDto.setMsg(messageByLocaleService.getErrorMessage(ErrorMessages.PARCC_006));
		} else if(ex.getMessage().indexOf(ErrorMessages.PARCC_007.getMatchEntry()) > -1 ) {
			statusDto.setCode(ErrorMessages.PARCC_007.getCode());
			statusDto.setMsg(messageByLocaleService.getErrorMessage(ErrorMessages.PARCC_007));
		}
		return handleExceptionInternal(ex, statusDto, headers, status, request);
	}

}
