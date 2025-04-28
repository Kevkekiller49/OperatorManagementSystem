package nz.ac.auckland.se281;

import java.util.ArrayList;

public class ExpertReview extends Review {
  private boolean recommend;
  // Creates an array to store images that a strings
  protected ArrayList<String> images = new ArrayList<>();

  public ExpertReview(String reviewerName, int rating, String reviewString, boolean recommend) {
    // Sets this instance of fields accordingly
    super(reviewerName, rating, reviewString);
    this.recommend = recommend;
  }

  // Place holder for abstract class
  // ExpertReviews can not be anonymous
  @Override
  public boolean isAnonymous() {
    return false;
  }

  // Returns the boolean of recommend
  public boolean getRecommend() {
    return recommend;
  }

  // Method that adds images to the arraylist
  public void addImage(String imageName) {
    images.add(imageName);
  }
}
