package nz.ac.auckland.se281;

public class Operator {
  private String name;
  private String id;
  private String locationFullName;
  private String locationAbbreviation;

  public Operator(String name, String id, String locationFullName, String locationAbbreviation) {
    this.name = name;
    this.id = id;
    this.locationFullName = locationFullName;
    this.locationAbbreviation = locationAbbreviation;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public String getLocationFullName() {
    return locationFullName;
  }

  public String getLocationAbbreviation() {
    return locationAbbreviation;
  }

  public String getLocationEnglishName() {
    return Types.Location.fromString(locationAbbreviation).getNameEnglish();
  }

  @Override
  public String toString() {
    return name + " ('" + id + "' located in '" + locationFullName + "')";
  }
}
