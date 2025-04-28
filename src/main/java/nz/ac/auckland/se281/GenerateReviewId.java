package nz.ac.auckland.se281;

import java.util.ArrayList;

public class GenerateReviewId {

  public String generateReviewId(String activityId, ArrayList<Review> reviewArrayList) {
    // Sets review number to 0
    int highestReviewNumber = 0;
    // Loops through entire array
    // checks if valid then applies condition
    for (Review review : reviewArrayList) {
      if (review.getActivityId().startsWith(activityId)) {
        // Gets the activityId
        // and the last hyphen in the string
        String reviewId = review.getActivityId();
        int indexOfDash = reviewId.lastIndexOf("-");
        // If the last hypen is more than 0 then apply condition
        if (indexOfDash > 0) {
          String reviewNumberString = reviewId.substring(indexOfDash + 1);
          // Gets the value for the number part
          if (reviewNumberString.length() > 1 && reviewNumberString.charAt(0) == 'R') {
            String numberPart = reviewNumberString.substring(1);
            // Finds the number and adds 1 then returns
            if (numberPart.length() > 0) {
              int reviewNumber = Integer.parseInt(numberPart);
              if (reviewNumber > highestReviewNumber) {
                highestReviewNumber = reviewNumber;
              }
            }
          }
        }
      }
    }
    int newReviewNumber = highestReviewNumber + 1;
    return activityId + "-R" + newReviewNumber;
  }
}
