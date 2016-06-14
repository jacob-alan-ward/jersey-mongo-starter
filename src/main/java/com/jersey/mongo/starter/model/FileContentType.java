package com.jersey.mongo.starter.model;

import java.io.InputStream;

/**
 *
 * @author Jacob
 */
public class FileContentType {

	private final InputStream file;
	private final String contentType;

	public FileContentType(InputStream file,
						   String contentType) {
		this.file = file;
		this.contentType = contentType;
	}

	public InputStream getFile() {
		return file;
	}

	public String getContentType() {
		return contentType;
	}
}
