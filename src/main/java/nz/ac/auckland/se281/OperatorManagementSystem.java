package nz.ac.auckland.se281;

import java.util.ArrayList;

public class OperatorManagementSystem {

  ArrayList<String> listOfOperators = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    // Removes empty space and changes keyword to all lowercase.
    String trimmedKeyword = keyword.trim().toLowerCase();
    int matchingOperatorsCount = 0;

    // Loops through each of operator in the array list and applies condition.
    for (String operator : listOfOperators) {
      if (operator.toLowerCase().contains(trimmedKeyword) || trimmedKeyword.contains("*")) {
        matchingOperatorsCount++;
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
      for (String operator : listOfOperators) {
        if (operator.toLowerCase().contains(trimmedKeyword) || trimmedKeyword.contains("*")) {
          System.out.println("* " + operator);
        }
      }
    } else {
      System.out.println("There are no matching operators found.");
    }
  }

  public void createOperator(String operatorName, String location) {

    // Using Types Location enum to format the location we get to what we want.
    Types.Location checkLocation = Types.Location.fromString(location);
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
    for (String operator : listOfOperators) {
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
    for (String operator : listOfOperators) {

      if (operator.contains(operatorName) && operator.contains("located in '" + fullName + "'")) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
            operatorName, fullName);
        return;
      }
    }

    // Adds operator to array list.
    listOfOperators.add(
        operatorName + " ('" + operatorFullName + "' located in '" + fullName + "')");

    // Prints message we want using MessageCli.
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
