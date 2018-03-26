package com.nj.parcc.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.nj.parcc.exception.ErrorMessages;
import com.nj.parcc.service.ParccMessageService;
/**
 * Implementation class of {@link ParccMessageService} interface.
 * @author Sriram
 *
 */
@Service
public class ParccMessageServiceImpl implements ParccMessageService {

	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(String id) {
		return messageSource.getMessage(id, null, LocaleContextHolder.getLocale());
	}

	@Override
	public String getErrorMessage(ErrorMessages messages) {
		return this.getErrorMessage(messages, null);
	}

	@Override
	public String getErrorMessage(ErrorMessages messages, Object[] args) {
		return messageSource.getMessage(messages.getCode(), args, messages.getDefaultMessage(), LocaleContextHolder.getLocale());
	}

}