package com.nj.parcc.service;

import com.nj.parcc.exception.ErrorMessages;
/**
 * Interface to handle the Message service functionalities for Parcc service application. 
 * @author Sriram
 *
 */
public interface ParccMessageService {
	/**
	 * Method to return the message from messages.properties for the given id. 
	 * @param id
	 * @return
	 */
	String getMessage(String id);
	/**
	 * Method to return the message from nessages.properties for the give ErrorMessages Enum
	 * @param messages
	 * @return
	 */
	String getErrorMessage(ErrorMessages messages);
	/**
	 * Method to return the message from nessages.properties for the give ErrorMessages Enum. 
	 * Use this method in order to dynamically substitute the values by supplying the values in args 
	 * parameter. 
	 * @param messages
	 * @param args
	 * @return
	 */
	String getErrorMessage(ErrorMessages messages, Object[] args);
}