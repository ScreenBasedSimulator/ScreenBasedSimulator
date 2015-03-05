package edu.cmu.lti.bic.sbs.gson;

public class Drug {
  private String name = "";

  private String description = "";

  private String id = "";

  private double does;

  public Drug() {
    this.name = "Default Drug";
    this.description = "Default description";
    this.id = "Default id";
  }

  // constructor without does
  public Drug(String name, String description, String id) {
    super();
    this.name = name;
    this.description = description;
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the does
   */
  public double getDoes() {
    return does;
  }

  /**
   * @param does the does to set
   */
  public void setDoes(double does) {
    this.does = does;
  }
}
