package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Operator {
  // ArrayList for activityIds
  private ArrayList<String> activityIds = new ArrayList<>();

  private String name;
  private String id;
  private String locationFullName;
  private String locationAbbreviation;

  public Operator(String name, String id, String locationFullName, String locationAbbreviation) {
    // Sets this instance of fields accordingly
    this.name = name;
    this.id = id;
    this.locationFullName = locationFullName;
    this.locationAbbreviation = locationAbbreviation;
  }
  // Sets if the activity contains the id given
  public boolean hasActivity(String activityId) {
    return activityIds.contains(activityId);
  }
  // Return name
  public String getName() {
    return name;
  }
  // return id
  public String getId() {
    return id;
  }
  // return locations full name
  public String getLocationFullName() {
    return locationFullName;
  }
  // returns the abbreviation of the location
  public String getLocationAbbreviation() {
    return locationAbbreviation;
  }
  // returns just the english name of the location
  public String getLocationEnglishName() {
    return Types.Location.fromString(locationAbbreviation).getNameEnglish();
  }
  // Prints message
  @Override
  public String toString() {
    return name + " ('" + id + "' located in '" + locationFullName + "')";
  }
}
