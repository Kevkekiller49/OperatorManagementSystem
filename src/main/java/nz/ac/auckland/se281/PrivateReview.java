package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  private String reviewerEmail;
  private boolean followUpEmailRequested;
  private String response;

  public PrivateReview(
      String reviewerName,
      String reviewerEmail,
      int rating,
      String reviewString,
      boolean followUpEmailRequested) {
    // Sets this instances fields
    super(reviewerName, rating, reviewString);
    this.reviewerEmail = reviewerEmail;
    this.followUpEmailRequested = followUpEmailRequested;
    this.response = null;
  }

  // Private review can not be anonymous, this is place holder
  // to satisfy the abstract class
  @Override
  public boolean isAnonymous() {
    return false;
  }

  // Prints messages if there is a response or not
  @Override
  public void printMessage() {
    if (response != null) {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(response);
    } else if (followUpEmailRequested) {
      MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(reviewerEmail);
    }
  }

  // Sets response
  public void setResponse(String response) {
    this.response = response;
  }

  // Sets email
  public String getReviewerEmail() {
    return reviewerEmail;
  }

  // Checks if a follow up is requested
  public boolean isFollowUpEmailRequested() {
    return followUpEmailRequested;
  }

  // returns response
  public String getResponse() {
    return response;
  }
}
