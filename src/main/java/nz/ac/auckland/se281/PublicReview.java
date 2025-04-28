package nz.ac.auckland.se281;

public class PublicReview extends Review {
  private boolean endorsed;
  private boolean anonymous;

  // Child class to PublicReview
  public PublicReview(String reviwerName, boolean anonymous, int rating, String reviewString) {
    // Sets this instance of fields that we need
    super(reviwerName, rating, reviewString);
    this.anonymous = anonymous;
    this.endorsed = false;
  }

  // if endorse is called then set endorsed to true
  public void endorse() {
    this.endorsed = true;
  }

  // Returns boolean value of endorse
  public boolean isEndorsed() {
    return endorsed;
  }

  // Returns its anonymity
  @Override
  public boolean isAnonymous() {
    return this.anonymous;
  }
}
