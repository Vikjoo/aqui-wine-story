package net.spark.cellar;

import javax.persistence.Entity;

@Entity
public class Image extends CellarObject{

	private String uri;


	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}


}
