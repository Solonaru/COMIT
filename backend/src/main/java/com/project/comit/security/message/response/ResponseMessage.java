package com.project.comit.security.message.response;

public class ResponseMessage {
	private String message;

	/* ----- CONSTRUCTORS ----- */
	public ResponseMessage(String message) {
		this.message = message;
	}

	/* ----- GETTERS & SETTERS ----- */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
