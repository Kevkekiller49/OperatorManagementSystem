package nz.ac.auckland.se281;

public abstract class Review {
  protected String reviewerName;
  protected int rating;
  protected String reviewString;
  private String activityId;
  private String reviewId;

  // Abstract class for the rest that will be the parent class for
  // public reviews, expert reviews and private reviews
  public Review(String reviewerName, int rating, String reviewString) {
    // Sets this instance of these fields so we can maniuplate later on
    this.reviewerName = reviewerName;
    this.rating = rating;
    this.reviewString = reviewString;
    // Checks if its more than or less than or equal to 0 and set to 1 or 5
    if (this.rating <= 0) {
      this.rating = 1;
      return;
    } else if (this.rating >= 6) {
      this.rating = 5;
      return;
    }
  }

  // Sets activity id
  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }

  // Sets review id
  public void setReviewId(String reviewId) {
    this.reviewId = reviewId;
  }

  // Return activity id
  public String getActivityId() {
    return activityId;
  }

  // Return review id
  public String getReviewId() {
    return reviewId;
  }

  // Ensures each sub class has this method
  public abstract boolean isAnonymous();

  // Returns name
  public String getName() {
    return this.reviewerName;
  }

  // Returns rating
  public int getRating() {
    return this.rating;
  }

  // Returns the review itself
  public String getReviewString() {
    return this.reviewString;
  }

  // Placeholder for another subclass
  public void printMessage() {}
}
