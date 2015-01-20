package edu.cmu.lti.bic.sbs.gson;

public class Equipment {
	private String name = "";
	private String description = "";
	private String id = "";
	
	public Equipment(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public String getName(){
		return name;
	}
	public Equipment setName(String name){
		this.name = name;
		return this;
	}
	public String getDescription(){
		return description;
	}
	public Equipment setDescription(String description){
		this.description = description;
		return this;
	}
	public String getId(){
		return id;
	}
	public Equipment setId(String id){
		this.id = id;
		return this;
	}
	
}
