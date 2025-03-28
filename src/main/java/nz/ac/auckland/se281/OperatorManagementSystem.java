package nz.ac.auckland.se281;

public class OperatorManagementSystem {

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    System.out.println("There are no matching operators found.");
  }

  public void createOperator(String operatorName, String location) {

    Types.Location checkLocation = Types.Location.fromString(location);

    String fullName = checkLocation.getFullName();

    String[] words = operatorName.split(" ");


    String operatorAbbrevation = "";
    for (String word : words) {
      char operatorTransform = word.charAt(0);
      operatorAbbrevation += operatorTransform;
    }
    
    int number = 0;
    if (number >= 999) {
      System.out.println("Operater limit has been reached.");
    }
      number++;
      String numberCount = String.format("%03d", number);
      String operatorFullName = (operatorAbbrevation + "-" + location + "-" + numberCount);
  

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
