package patient;

public class Equipment {
	private String name = null;
	private String description = null;
	private Integer id = null;
	
	public Equipment(Integer id, String name, String description) {
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
	public Integer getId(){
		return id;
	}
	public Equipment setId(Integer id){
		this.id = id;
		return this;
	}
	
}
