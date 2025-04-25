package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Operator {

  private ArrayList<String> activityIds = new ArrayList<>();

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

  public boolean hasActivity(String activityId) {
    return activityIds.contains(activityId);
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
