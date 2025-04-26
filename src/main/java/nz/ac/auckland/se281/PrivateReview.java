package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  private String reviewerEmail;
  private boolean followUpEmailRequested;
  private String response;

  public PrivateReview(String reviewerName, String reviewerEmail, int rating, String reviewString, boolean followUpEmailRequested) {
    super(reviewerName, rating, reviewString);
    this.reviewerEmail = reviewerEmail;
    this.followUpEmailRequested = followUpEmailRequested;
    this.response = null;


  }

  @Override
  public boolean isAnonymous() {
    return false;
  }

  @Override
  public void printMessage() {
    if (response != null) {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(response);
    } else if (followUpEmailRequested) {
      MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(reviewerEmail);
    } 
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public String getReviewerEmail() {
    return reviewerEmail;
  }

  public boolean isFollowUpEmailRequested() {
    return followUpEmailRequested;
  }

  public String getResponse() {
    return response;
  }

}
