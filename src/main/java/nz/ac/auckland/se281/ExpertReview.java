package nz.ac.auckland.se281;

import java.util.ArrayList;

public class ExpertReview extends Review {
  boolean recommend;

  ArrayList<String> images = new ArrayList<>();

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

  public void addImage(String imageName) {
    images.add(imageName);
  }
  
}
