package net.spark.backend.service;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * A data integraty violation exception containing a message intended to be
 * shown to the end user.
 */
public class OutOfStockException extends RuntimeException {

	public OutOfStockException(String message) {
		super(message);
	}

}
