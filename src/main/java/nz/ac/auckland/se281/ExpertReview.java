package nz.ac.auckland.se281;

public class ExpertReview extends Review {
  boolean recommend;

  public ExpertReview(String reviewerName, int rating, String reviewString, boolean recommend) {
    super(reviewerName, rating, reviewString);
    this.recommend = recommend;
  }

  @Override
  public boolean isAnonymous() {
    return false;
  }

  public boolean getRecommend() {
    return recommend;
  }


  
}
