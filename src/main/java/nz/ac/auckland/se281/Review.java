package nz.ac.auckland.se281;

public abstract class Review {
  protected String reviewerName;
  protected int rating;
  protected String reviewString;

  public Review(String reviewerName, int rating, String reviewString) {
    this.reviewerName = reviewerName;
    this.rating = rating;
    this.reviewString = reviewString;

    if (this.rating <= 0) {
      this.rating = 1;
      return;
    } else if (this.rating >= 6) {
      this.rating = 5;
      return;
    }
  }

  public abstract boolean isAnonymous();

  public String getName() {
    return this.reviewerName;
  }

  public int getRating() {
    return this.rating;
  }

  public String getReviewString() {
    return this.reviewString;
  }

  public abstract String toString();
  

}
