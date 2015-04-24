package edu.cmu.lti.bic.sbs.gson;

/**
 * The Tool Class
 *
 * @author Victor Zhao, Ryan Sun
 *
 */
public class Tool {
  private String name = "Default Tool";

  private String id = null;

  public Tool() {
    this("Default id", "Default name", 100);
  }

  private double value;

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

  public String toString() {
    return name;
  }

  public Tool(String id, String name, double value) {

    this.id = id;
    this.name = name;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
