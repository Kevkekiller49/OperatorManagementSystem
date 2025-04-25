package nz.ac.auckland.se281;

public class PublicReview extends Review {
  private boolean endorsed;
  private boolean anonymous;

  public PublicReview(String reviwerName, boolean anonymous, int rating, String reviewString) {
    super(reviwerName, rating, reviewString);
    this.anonymous = anonymous;
    this.endorsed = false;
  }

  public void endorse() {
    this.endorsed = true;
  }

  public boolean isEndorsed() {
    return endorsed;
  }

  @Override
  public boolean isAnonymous() {
    return this.anonymous;
  }

  @Override
  public String toString() {
    String formatName;
    if (!anonymous) {
      formatName = "Anonymous";
    } else {
      formatName = this.reviewerName;
    }

    String output =
        "Reviwer: "
            + formatName
            + "\n"
            + "Rating: "
            + this.rating
            + "\n"
            + "Review: "
            + this.reviewString;

    if (isEndorsed()) {
      output += "\nEndorsed: Yes";
    }

    return output;
  }
}
