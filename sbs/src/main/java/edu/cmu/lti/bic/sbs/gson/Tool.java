package edu.cmu.lti.bic.sbs.gson;

/**
 * The Tool Class
 * @author Victor Zhao <xinyunzh@andrew.cmu.edu>
 *
 */
public class Tool {
	private String name = null;
	private String description = null;
	private String id = null;
	
	public String getName() {
		return name;
	}
	
	public Tool setName(String toolName) {
		this.name = toolName;
		return this;
	}
	
	public String getId() {
		return id;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String toString() {
		return "The tool is " + name;
	}
	
	public Tool(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
}
