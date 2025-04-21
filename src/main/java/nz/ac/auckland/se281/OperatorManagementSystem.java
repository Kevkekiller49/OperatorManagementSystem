package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;

public class OperatorManagementSystem {

  ArrayList<String> operatorArrayList = new ArrayList<>();
  ArrayList<String> activitiesArrayList = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    // Removes empty space and changes keyword to all lowercase.
    String trimmedKeyword = keyword.trim().toLowerCase();
    int matchingOperatorsCount = 0;

    // Loops through each of operator in the array list and applies condition.
    for (String operator : operatorArrayList) {
      if (operator.toLowerCase().contains(trimmedKeyword) || trimmedKeyword.contains("*")) {
        matchingOperatorsCount++;
      }
      if (keyword.equals("|")) {
        System.out.println("There are no matching operators found.");
      }
    }

    // Checks to make sure we have an operator we want and applies if statements.
    if (matchingOperatorsCount > 0) {
      if (matchingOperatorsCount == 1) {
        MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      } else {
        MessageCli.OPERATORS_FOUND.printMessage(
            "are", String.valueOf(matchingOperatorsCount), "s", ":");
      }

      // Prints operator we want with * at the front.
      for (String operator : operatorArrayList) {
        if (operator.toLowerCase().contains(trimmedKeyword) || trimmedKeyword.contains("*")) {
          System.out.println("* " + operator);
        }
      }
    } else {
      System.out.println("There are no matching operators found.");
    }
  }

  public void createOperator(String operatorName, String location) {

    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    // Using Types Location enum to format the location we get to what we want.
    Types.Location checkLocation = Types.Location.fromString(location);

    // If location isnt valid then print using MessageCli
    if (checkLocation == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }

    String fullName = checkLocation.getFullName();
    String city = checkLocation.getNameEnglish();

    // Splits the operatorName at each space to turn it into an individual element.
    String[] words = operatorName.split(" ");
    String locationAbbreviation = checkLocation.getLocationAbbreviation();

    // Empty string that ends up being the capitalized words of operatorName.
    // Only capitalizes first letter of each word.
    String operatorNameWithCapitalizedWords = "";
    for (String word : words) {
      if (!word.isEmpty()) {
        operatorNameWithCapitalizedWords +=
            Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
      }
    }

    // Trims empty space incase nothing it added on at the end.
    operatorName = operatorNameWithCapitalizedWords.trim();

    // Empty String
    // Capitalize each character for the operatorAbbreviation.
    String operatorAbbreviation = "";
    for (String word : words) {
      char operatorTransform = Character.toUpperCase(word.charAt(0));
      operatorAbbreviation += operatorTransform;
    }

    int number = 1;
    // Checks if the Arraylist has a certain locationAbbreviation in it and adds number
    // to keep track of operators.
    for (String operator : operatorArrayList) {
      if (operator.contains("-" + locationAbbreviation + "-")) {
        number++;
      }
    }

    // Error prevention if number of operators exceed 999.
    if (number >= 999) {
      System.out.println("Operator limit has been reached for " + city);
      return;
    }

    // Formats string to have 3 digits in it (00x).
    String numberCount = String.format("%03d", number);
    // Gets full name of operator that we want printed.
    String operatorFullName =
        (operatorAbbreviation + "-" + locationAbbreviation + "-" + numberCount);

    // Checks if operator already exist.
    for (String operator : operatorArrayList) {

      if (operator.contains(operatorName) && operator.contains("located in '" + fullName + "'")) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
            operatorName, fullName);
        return;
      }
    }

    // Adds operator to array list.
    operatorArrayList.add(
        operatorName + " ('" + operatorFullName + "' located in '" + fullName + "')");

    // Prints message we want using MessageCli.
    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorFullName, fullName);
  }

  public void viewActivities(String operatorId) {
    int matchingActivities = 0;

    boolean checkOperator = false;
    for (String operator : operatorArrayList) {
      if (operator.contains("'" + operatorId + "'")) {
        checkOperator = true;
        break;
      }
    }

    if (!checkOperator) {
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }

    for (String activity : activitiesArrayList) {
      if (activity.contains(operatorId)) {
        matchingActivities++;
      }
    }

    if (matchingActivities == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    }
    if (matchingActivities == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      for (String activity : activitiesArrayList) {
        if (activity.contains(operatorId)) {
          System.out.println(activity);
        }
      }
      return;
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", String.valueOf(matchingActivities), "ies", ":");
      for (String activity : activitiesArrayList) {
        if (activity.contains(operatorId)) {
          System.out.println(activity);
        }
      }
      return;
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {

    if (activityName.length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }

    ActivityType newType = ActivityType.fromString(activityType.trim());

    boolean checkOperator = false;
    String operatorName = "";
    for (String operator : operatorArrayList) {
      if (operator.contains("'" + operatorId + "'")) {
        checkOperator = true;
        operatorName = operator.split(" \\(")[0];
        break;
      }
    }

    if (!checkOperator) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }

    int activityCount = 1;
    for (String activity : activitiesArrayList) {
      if (activity.contains(operatorId)) {
        activityCount++;
      }
    }

    if (activityCount > 999) {
      System.out.println("Activity limit has been reached for " + operatorId);
      return;
    }
    String activityID = String.format("%03d", activityCount);
    String combinedID = operatorId + "-" + activityID;
    String storedActivity =
        "* " + activityName + ": [" + combinedID + "/" + newType + "] offered by " + operatorName;
    activitiesArrayList.add(storedActivity);

    MessageCli.ACTIVITY_CREATED.printMessage(
        activityName, combinedID, newType.getName(), operatorName);
  }

  public void searchActivities(String keyword) {

    int searchCount = 0;

    if (activitiesArrayList.size() == 0) {
      System.out.println("There are no matching activities found.");
      return;
    }

      for (String activity : activitiesArrayList) {
        if (activity.contains(keyword.trim())) {
          searchCount++;
        }
      }

    //  ACTIVITIES_FOUND("There %s %s matching activit%s found%s"),
    // assertContains("There are 27 matching activities found:");
    if (searchCount == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    }
    if (searchCount == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      for (String activity : activitiesArrayList) {
        if (activity.contains(keyword.trim())) {
          System.out.println(activity);
        }
      }
      return;
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", String.valueOf(searchCount), "ies", ":");
      for (String activity : activitiesArrayList) {
        if (activity.contains(keyword.trim())) {
          System.out.println(activity);
        }
      }
      return;
    }
  }

  public void addPublicReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addPrivateReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addExpertReview(String activityId, String[] options) {
    // TODO implement
  }

  public void displayReviews(String activityId) {
    // TODO implement
  }

  public void endorseReview(String reviewId) {
    // TODO implement
  }

  public void resolveReview(String reviewId, String response) {
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }
}
