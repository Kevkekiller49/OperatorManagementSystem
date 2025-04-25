package nz.ac.auckland.se281;

import java.util.ArrayList;

public class GenerateReviewId {

  public String generateReviewId(String activityId, ArrayList<Review> reviewArrayList) {
    int highestReviewNumber = 0;

    for (Review review : reviewArrayList) {
      if (review.getActivityId().startsWith(activityId)) {
        String reviewId = review.getActivityId();
        int indexOfDash = reviewId.lastIndexOf("-");

        if (indexOfDash > 0) {
          String reviewNumberString = reviewId.substring(indexOfDash + 1);

          if (reviewNumberString.length() > 1 && reviewNumberString.charAt(0) == 'R') {
            String numberPart = reviewNumberString.substring(1);

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
