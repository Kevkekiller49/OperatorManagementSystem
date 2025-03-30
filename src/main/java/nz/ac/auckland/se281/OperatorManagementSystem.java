package nz.ac.auckland.se281;

import java.util.ArrayList;

public class OperatorManagementSystem {

  ArrayList<String> listOfOperators = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    boolean found = false;
    for (String operator : listOfOperators) {
      if (operator.toLowerCase().contains(keyword.trim().toLowerCase())
          || keyword.trim().contains("*"))
        ;
      if (!found) {
        if (listOfOperators.size() == 1) {
          MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
        } else if (listOfOperators.size() >= 2) {
          MessageCli.OPERATORS_FOUND.printMessage(
              "are", String.valueOf(listOfOperators.size()), "s", ":");
        }
        found = true;
      }
      System.out.println("* " + operator);
    }
    if (!found) {
      System.out.println("There are no matching operators found.");
    }
  }

  public void createOperator(String operatorName, String location) {

    Types.Location checkLocation = Types.Location.fromString(location);
    String fullName = checkLocation.getFullName();
    String city = checkLocation.getNameEnglish();

    String[] words = operatorName.split(" ");
    String locationAbbreviation = checkLocation.getLocationAbbreviation();

    String operatorAbbreviation = "";
    for (String word : words) {
      char operatorTransform = word.charAt(0);
      operatorAbbreviation += operatorTransform;
    }

    int number = 1;

    for (String operator : listOfOperators) {
      if (operator.contains("-" + locationAbbreviation + "-")) {
        number++;
      }
    }

    if (number >= 999) {
      System.out.println("Operator limit has been reached for " + city);
      return;
    }

    String numberCount = String.format("%03d", number);
    String operatorFullName =
        (operatorAbbreviation + "-" + locationAbbreviation + "-" + numberCount);

    for (String operator : listOfOperators) {

      if (operator.contains(operatorName) && operator.contains("located in '" + fullName + "'")) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
            operatorName, fullName);
        return;
      }
    }

    listOfOperators.add(
        operatorName + " ('" + operatorFullName + "' located in '" + fullName + "')");

    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorFullName, fullName);
  }

  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // TODO implement
  }

  public void searchActivities(String keyword) {
    // TODO implement
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
