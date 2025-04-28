package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;

public class OperatorManagementSystem {

  private ArrayList<Operator> operatorArrayList = new ArrayList<>();
  private ArrayList<String> activitiesArrayList = new ArrayList<>();
  private ArrayList<Review> reviewArrayList = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    // Removes empty space and changes keyword to all lowercase.
    String trimmedKeyword = keyword.trim().toLowerCase();

    if (trimmedKeyword.equals("|")) {
      System.out.println("There are no matching operators found.");
      return;
    }

    ArrayList<Operator> matchedOperators = new ArrayList<>();

    // Loops through each of operator in the array list and applies condition.
    for (Operator operator : operatorArrayList) {
      if (trimmedKeyword.equals("*")
          || operator.getName().toLowerCase().contains(trimmedKeyword)
          || operator.getLocationFullName().toLowerCase().contains(trimmedKeyword)
          || operator.getLocationAbbreviation().toLowerCase().contains(trimmedKeyword)) {
        matchedOperators.add(operator);
      }
    }

    int matchingOperatorsCount = matchedOperators.size();

    // Checks to make sure we have an operator we want and applies if statements.
    if (matchingOperatorsCount > 0) {
      if (matchingOperatorsCount == 1) {
        MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      } else {
        MessageCli.OPERATORS_FOUND.printMessage(
            "are", String.valueOf(matchingOperatorsCount), "s", ":");
      }

      // Prints operator we want with * at the front.
      for (Operator operator : matchedOperators) {
        System.out.println("* " + operator.toString());
      }
    } else {
      System.out.println("There are no matching operators found.");
    }
  }

  public void createOperator(String operatorName, String location) {

    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    // Using Types Location enum to format the location we get to what we want.
    Types.Location checkLocation = Types.Location.fromString(location);

    // If location isnt valid then print using MessageCli
    if (checkLocation == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }

    String fullName = checkLocation.getFullName();
    String city = checkLocation.getNameEnglish();

    // Splits the operatorName at each space to turn it into an individual element.
    String[] words = operatorName.split(" ");
    String locationAbbreviation = checkLocation.getLocationAbbreviation();

    // Empty string that ends up being the capitalized words of operatorName.
    // Only capitalizes first letter of each word.
    String operatorNameWithCapitalizedWords = "";
    for (String word : words) {
      if (!word.isEmpty()) {
        operatorNameWithCapitalizedWords +=
            Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
      }
    }

    // Trims empty space incase nothing it added on at the end.
    operatorName = operatorNameWithCapitalizedWords.trim();

    // Empty String
    // Capitalize each character for the operatorAbbreviation.
    String operatorAbbreviation = "";
    for (String word : words) {
      char operatorTransform = Character.toUpperCase(word.charAt(0));
      operatorAbbreviation += operatorTransform;
    }

    int number = 1;
    // Checks if the Arraylist has a certain locationAbbreviation in it and adds number
    // to keep track of operators.
    for (Operator operator : operatorArrayList) {
      if (operator.getLocationAbbreviation().equals(locationAbbreviation)) {
        number++;
      }
    }

    // Error prevention if number of operators exceed 999.
    if (number >= 999) {
      System.out.println("Operator limit has been reached for " + city);
      return;
    }

    // Formats string to have 3 digits in it (00x).
    String numberCount = String.format("%03d", number);
    // Gets full name of operator that we want printed.
    String operatorId = operatorAbbreviation + "-" + locationAbbreviation + "-" + numberCount;

    // Checks if operator already exist.
    for (Operator operator : operatorArrayList) {
      if (operator.getName().equals(operatorName)
          && operator.getLocationFullName().equals(fullName)) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
            operatorName, fullName);
        return;
      }
    }

    // Adds operator to array list.
    operatorArrayList.add(new Operator(operatorName, operatorId, fullName, locationAbbreviation));
    ;

    // Prints message we want using MessageCli.
    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorId, fullName);
  }

  public void viewActivities(String operatorId) {
    int matchingActivities = 0;

    // Initiates checkOperator as false then applies conditions
    boolean checkOperator = false;
    for (Operator operator : operatorArrayList) {
      if (operator.getId().equals(operatorId)) {
        checkOperator = true;
        break;
      }
    }

    // If checkOperator is still false print message
    if (!checkOperator) {
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }

    // Loops through each element in activitesArrayList and applies condition
    for (String activity : activitiesArrayList) {
      if (activity.contains(operatorId)) {
        matchingActivities++;
      }
    }

    // If 0 matchingActivites have been found then print message accordingly
    if (matchingActivities == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    }
    // If 1 matchingActivites have been found then print message accordingly
    // else print message with the value being the amount of matching activities
    if (matchingActivities == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", String.valueOf(matchingActivities), "ies", ":");
    }

    // Prints each activity if it contains the operatorId
    for (String activity : activitiesArrayList) {
      if (activity.contains(operatorId)) {
        System.out.println(activity);
      }
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    activityName = activityName.trim();

    // If the activity name is less than 3 letters then print erorr message
    if (activityName.length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }

    // Convert the string activity type into activity type
    ActivityType newType = ActivityType.fromString(activityType.trim());

    // Initiates matchedOperator as null
    // Loops through operatorArrayList and checks if the Id matches the operatorId
    // If its a match we set matchedOperator to that operator which allows us to
    // Apply conditions later on
    Operator matchedOperator = null;
    for (Operator operator : operatorArrayList) {
      if (operator.getId().equals(operatorId)) {
        matchedOperator = operator;
        break;
      }
    }

    // if, if statement didnt run then matchedOperator would be null and print an error
    if (matchedOperator == null) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }

    // uses the operator class to get the name for the operator and store it
    String operatorName = matchedOperator.getName();

    // Count starts at 1 then loops through activitesArrayList and applies condition
    int activityCount = 1;
    for (String activity : activitiesArrayList) {
      if (activity.contains(operatorId)) {
        activityCount++;
      }
    }

    // If more than 999 activities made then print error
    if (activityCount > 999) {
      System.out.println("Activity limit has been reached for " + operatorId);
      return;
    }
    // Formats all the strings to add to arraylist
    String activityIdentification = String.format("%03d", activityCount);
    String combinedIdentification = operatorId + "-" + activityIdentification;
    String storedActivity =
        "* "
            + activityName
            + ": ["
            + combinedIdentification
            + "/"
            + newType
            + "] offered by "
            + operatorName;
    activitiesArrayList.add(storedActivity);

    // Print message
    MessageCli.ACTIVITY_CREATED.printMessage(
        activityName, combinedIdentification, newType.getName(), operatorName);
  }

  public void searchActivities(String keyword) {
    // Transforming keyword to trim excess space and make everything lowercase
    keyword = keyword.trim().toLowerCase();

    // Checks to make keyword isnt empty and there are activities to search
    if (keyword == null || keyword.trim().isEmpty() || activitiesArrayList.size() == 0) {
      System.out.println("There are no matching activities found.");
      return;
    }
    // Create an arraylist
    ArrayList<String> matchedList = new ArrayList<>();

    // Loops through the entire activities arraylist and apply the conditions
    for (String activity : activitiesArrayList) {
      // Format the keyword and activity element
      String lowerCaseActivity = activity.trim().toLowerCase();
      String lowerCaseKeyword = keyword.trim().toLowerCase();

      // If the keyword is "*" then add all activities to matchedList (arrayList)
      if (lowerCaseKeyword.equals("*")) {
        matchedList.add(activity);
        continue;
      }
      // Gets the start of the activity Id string from [ + 1
      int startOfCombinedId = activity.indexOf("[") + 1;
      // ends the activity id at / and makes the start of it the field above
      int endOfCombinedId = activity.indexOf("/", startOfCombinedId);
      // extract the parts we need
      String fullActivityId = activity.substring(startOfCombinedId, endOfCombinedId);
      // operatorId is the beginning of fullActivityId and at the last "-"
      String operatorId = fullActivityId.substring(0, fullActivityId.lastIndexOf("-"));

      // Initialises matchedOperator as null and applies condition
      Operator matchedOperator = null;
      for (Operator operator : operatorArrayList) {
        if (operator.getId().equals(operatorId)) {
          matchedOperator = operator;
          break;
        }
      }
      // Initalises matchesLocation as false
      boolean matchesLocation = false;
      // If matched operator is not null then apply conditions
      if (matchedOperator != null) {
        // Gets location name from matched operator and stores it
        String operatorFullName = matchedOperator.getLocationFullName().toLowerCase();
        // Gets location abbreviation from matched operator and stores it
        String operatorAbbreviation = matchedOperator.getLocationAbbreviation().toLowerCase();
        // Gets english location name from matched operator and stores it
        String englishName = matchedOperator.getLocationEnglishName().toLowerCase();
        // Gets Te Reo Maori location name from matched operator and stores it
        String teReoName = matchedOperator.getLocationFullName().toLowerCase();
        // Applies condition and if they return true, matchesLocation returns true
        matchesLocation =
            operatorFullName.contains(lowerCaseKeyword)
                || operatorAbbreviation.contains(lowerCaseKeyword)
                || englishName.contains(lowerCaseKeyword)
                || teReoName.contains(lowerCaseKeyword);
      }

      // If the activity contains the keyword add the activity to the matchedList
      // If matchesLocation is true add to array list
      if (lowerCaseActivity.contains(lowerCaseKeyword) || matchesLocation) {
        matchedList.add(activity);
      }
    }

    // Return size of the matched List array
    int searchCount = matchedList.size();

    // If searchCount is 0 print message accordingly
    if (searchCount == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    }
    // If searchCount is 1 print message accordingly
    // If searchCount is more than 1 print message accordingly using the
    // searchCount value as the placeholder
    if (searchCount == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", String.valueOf(searchCount), "ies", ":");
    }

    // Print each element from matchedList
    for (String activity : matchedList) {
      System.out.println(activity);
    }
  }

  public void addPublicReview(String activityId, String[] options) {
    // Initalise fields so we can manipulate to our benefit
    boolean check = false;
    String activityName = null;
    String reviewType = "public";

    for (String activity : activitiesArrayList) {
      // Gets the start of the activity Id string from [ + 1
      int startOfCombinedId = activity.indexOf("[") + 1;
      // ends the activity id at / and makes the start of it the field above
      int endOfCombinedId = activity.indexOf("/", startOfCombinedId);

      if (startOfCombinedId > 0 && endOfCombinedId > startOfCombinedId) {
        // extract the parts we need
        String fullActivityId = activity.substring(startOfCombinedId, endOfCombinedId);

        // checks they match then extracts the part we need
        if (fullActivityId.equals(activityId)) {
          check = true;
          int indexOfColon = activity.indexOf(":");
          if (indexOfColon > 0) {
            activityName = activity.substring(2, indexOfColon).trim();
          }
          break;
        }
      }
    }
    // if check is false then print message
    if (!check) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    // Create an object of type GenerateReviewId
    GenerateReviewId generateReviewId = new GenerateReviewId();
    // call method from GenerateReviewId class to generate a review id
    String reviewId = generateReviewId.generateReviewId(activityId, reviewArrayList);
    // Store the first element at index 0 as reviewerName
    // and so on for the other parameters
    String reviewerName = options[0];
    boolean anonymous = Boolean.parseBoolean(options[1]);
    int rating = Integer.parseInt(options[2]);
    String reviewString = options[3];

    // Create an object of type PublicReview
    PublicReview publicReview = new PublicReview(reviewerName, anonymous, rating, reviewString);
    // Call method in Review class to set the id
    publicReview.setActivityId(reviewId);
    // Add to arraylist
    reviewArrayList.add(publicReview);
    // Print message
    MessageCli.REVIEW_ADDED.printMessage(reviewType, reviewId, activityName);
  }

  public void addPrivateReview(String activityId, String[] options) {
    // Initalises same fields as Public review
    boolean check = false;
    String activityName = null;
    String reviewType = "Private";

    for (String activity : activitiesArrayList) {
      // Gets the start of the activity Id string from [ + 1
      int startOfCombinedId = activity.indexOf("[") + 1;
      // ends the activity id at / and makes the start of it the field above
      int endOfCombinedId = activity.indexOf("/", startOfCombinedId);

      if (startOfCombinedId > 0 && endOfCombinedId > startOfCombinedId) {
        // extract the parts we need
        String fullActivityId = activity.substring(startOfCombinedId, endOfCombinedId);
        // checks they match then extracts the part we need
        if (fullActivityId.equals(activityId)) {
          check = true;
          int indexOfColon = activity.indexOf(":");
          if (indexOfColon > 0) {
            activityName = activity.substring(2, indexOfColon).trim();
          }
          break;
        }
      }
    }
    // if check is false then print message
    if (!check) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    // Create an object of type GenerateReviewId
    GenerateReviewId generateReviewId = new GenerateReviewId();
    // call method from GenerateReviewId class to generate a review id
    String reviewId = generateReviewId.generateReviewId(activityId, reviewArrayList);
    // Store the first element at index 0 as reviewerName
    // and so on for the other parameters
    String reviewerName = options[0];
    String reviewerEmail = options[1];
    int rating = Integer.parseInt(options[2]);
    String reviewString = options[3];
    boolean followUpEmailRequested = options[4].equals("y");
    // Create privateReview of type PrivateReview and pass through parameters
    PrivateReview privateReview =
        new PrivateReview(
            reviewerName, reviewerEmail, rating, reviewString, followUpEmailRequested);
    privateReview.setActivityId(reviewId);
    // Add to arraylist
    reviewArrayList.add(privateReview);
    // Print message
    MessageCli.REVIEW_ADDED.printMessage(reviewType, reviewId, activityName);
  }

  public void addExpertReview(String activityId, String[] options) {
    // Initalises same fiels as Public review
    boolean check = false;
    String activityName = null;
    String reviewType = "Expert";

    for (String activity : activitiesArrayList) {
      // Gets the start of the activity Id string from [ + 1
      int startOfCombinedId = activity.indexOf("[") + 1;
      // ends the activity id at / and makes the start of it the field above
      int endOfCombinedId = activity.indexOf("/", startOfCombinedId);

      if (startOfCombinedId > 0 && endOfCombinedId > startOfCombinedId) {
        // extract the parts we need
        String fullActivityId = activity.substring(startOfCombinedId, endOfCombinedId);
        // checks they match then extracts the part we need
        if (fullActivityId.equals(activityId)) {
          check = true;
          int indexOfColon = activity.indexOf(":");
          if (indexOfColon > 0) {
            activityName = activity.substring(2, indexOfColon).trim();
          }
          break;
        }
      }
    }
    // if check is false then print message
    if (!check) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    // Create an object of type GenerateReviewId
    GenerateReviewId generateReviewId = new GenerateReviewId();
    // call method from GenerateReviewId class to generate a review id
    String reviewId = generateReviewId.generateReviewId(activityId, reviewArrayList);
    // Store the first element at index 0 as reviewerName
    // and so on for the other parameters
    String reviewerName = options[0];
    int rating = Integer.parseInt(options[1]);
    String reviewString = options[2];
    boolean recommend = options[3].equals("y");
    // Create expertReview of type ExpertReview and pass through parameters
    ExpertReview expertReview = new ExpertReview(reviewerName, rating, reviewString, recommend);
    expertReview.setActivityId(reviewId);
    // Add to arraylist
    reviewArrayList.add(expertReview);
    // Print message
    MessageCli.REVIEW_ADDED.printMessage(reviewType, reviewId, activityName);
  }

  public void displayReviews(String activityId) {
    // Set name as nothing
    String activityName = null;

    for (String activity : activitiesArrayList) {
      // Gets the start of the activity Id string from [ + 1
      int startOfCombinedId = activity.indexOf("[") + 1;
      // ends the activity id at / and makes the start of it the field above
      int endOfCombinedId = activity.indexOf("/", startOfCombinedId);

      if (startOfCombinedId > 0 && endOfCombinedId > startOfCombinedId) {
        // extract the parts we need
        String fullActivityId = activity.substring(startOfCombinedId, endOfCombinedId);
        // checks they match then extracts the part we need
        if (fullActivityId.equals(activityId)) {
          int indexOfColon = activity.indexOf(":");
          if (indexOfColon > 0) {
            activityName = activity.substring(2, indexOfColon).trim();
          }
          break;
        }
      }
    }
    // If name is still nothing exit
    if (activityName == null) {
      return;
    }

    ArrayList<Review> matchedReviews = new ArrayList<>();
    // Checks the review is valid by applying conditions
    for (Review review : reviewArrayList) {
      String reviewId = review.getActivityId();
      // ensures it has a reviewId that is valid
      if (reviewId != null && reviewId.startsWith(activityId + "-R")) {
        matchedReviews.add(review);
      }
    }
    // If empty print message
    if (matchedReviews.isEmpty()) {
      System.out.println("There are no reviews for activity '" + activityName + "'.");
      return;
    }
    // Gets size of array which is our review count
    int reviewCount = matchedReviews.size();
    // If equal to 1 print certain message
    // otherwise print another message
    if (reviewCount == 1) {
      MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", activityName);
    } else {
      MessageCli.REVIEWS_FOUND.printMessage("are", String.valueOf(reviewCount), "s", activityName);
    }

    for (Review review : matchedReviews) {
      // Checks that review of the for loop is an instance of PublicReview class
      if (review instanceof PublicReview) {
        // Cast PublicReview onto review
        PublicReview publicReview = (PublicReview) review;
        // Uses boolean to determine what name to show
        String nameToShow = publicReview.isAnonymous() ? "Anonymous" : review.getName();

        // Prints message
        MessageCli.REVIEW_ENTRY_HEADER.printMessage(
            String.valueOf(publicReview.getRating()),
            "5",
            "Public",
            publicReview.getActivityId(),
            nameToShow);
        System.out.println("  \"" + publicReview.getReviewString() + "\"");

        if (publicReview.isEndorsed()) {
          System.out.println("  Endorsed by admin.");
        }
      } else if (review instanceof PrivateReview) {
        // Cast PrivateReview onto review
        PrivateReview privateReview = (PrivateReview) review;
        // Prints Message
        MessageCli.REVIEW_ENTRY_HEADER.printMessage(
            String.valueOf(privateReview.getRating()),
            "5",
            "Private",
            privateReview.getActivityId(),
            privateReview.getName());
        System.out.println("  \"" + privateReview.getReviewString() + "\"");
        if (privateReview.isFollowUpEmailRequested()) {
          review.printMessage();
        } else {
          System.out.println("  Resolved: \"-\"");
        }
      } else if (review instanceof ExpertReview) {
        // Cast ExpertReview onto review
        ExpertReview expertReview = (ExpertReview) review;
        // Prints message
        MessageCli.REVIEW_ENTRY_HEADER.printMessage(
            String.valueOf(expertReview.getRating()),
            "5",
            "Expert",
            expertReview.getActivityId(),
            expertReview.getName());

        System.out.println("  \"" + expertReview.getReviewString() + "\"");
        // Checks if it is recommended by experts
        if (expertReview.getRecommend()) {
          System.out.println("  Recommended by experts.");
        }

        // Checks if there are images and prints accordingly
        if (!expertReview.images.isEmpty()) {
          System.out.print("  Images: [");
          for (int i = 0; i < expertReview.images.size(); i++) {
            System.out.print(expertReview.images.get(i));
            if (i != expertReview.images.size() - 1) {
              System.out.print(",");
            }
          }
          System.out.println("]");
        }
      }
    }
  }

  public void endorseReview(String reviewId) {
    for (Review review : reviewArrayList) {
      // Ensures the activityid and review Id match
      if (review.getActivityId().equals(reviewId)) {
        if (review instanceof PublicReview) {
          // Cast then checks if it is endorsed by calling method
          // then print message
          ((PublicReview) review).endorse();
          MessageCli.REVIEW_ENDORSED.printMessage(reviewId);
        } else {
          MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
        }
        return;
      }
    }
    // Print message
    MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
  }

  public void resolveReview(String reviewId, String response) {
    // no review to resolve yet
    Review reviewToResolve = null;

    for (Review review : reviewArrayList) {
      // Checks it is valid
      // then sets the review we want to resolve
      if (review.getActivityId().equals(reviewId)) {
        reviewToResolve = review;
        break;
      }
    }
    // If not found then print message
    if (reviewToResolve == null) {
      MessageCli.REVIEW_NOT_FOUND.printMessage((reviewId));
      return;
    }
    // Checks that review to Resolve is an instance of PrivateReview
    // otherwise print message
    if (!(reviewToResolve instanceof PrivateReview)) {
      MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
      return;
    }
    // Cast PrivateReview onto review to resolve
    PrivateReview privateReview = (PrivateReview) reviewToResolve;
    privateReview.setResponse(response);
    // Print message
    MessageCli.REVIEW_RESOLVED.printMessage(reviewId);
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // Set image to upload as nothing
    Review imageToUpload = null;

    for (Review review : reviewArrayList) {
      // Checks activityId Is valid
      // then set it as the review we want to upload images for
      if (review.getActivityId().equals(reviewId)) {
        imageToUpload = review;
        break;
      }
    }
    // If no review found then print message
    if (imageToUpload == null) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    }
    // Checks that the review is an instance of ExpertReview
    // if not print message
    if (!(imageToUpload instanceof ExpertReview)) {
      MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
      return;
    }
    // Cast ExpertReview onto imageToUpload
    ExpertReview expertReview = (ExpertReview) imageToUpload;

    expertReview.addImage(imageName);
    // Print message
    MessageCli.REVIEW_IMAGE_ADDED.printMessage(imageName, reviewId);
  }

  public void displayTopActivities() {
    // Initliase Locations as a string array
    String[] locations = {
      "Auckland | Tāmaki Makaurau",
      "Hamilton | Kirikiriroa",
      "Tauranga",
      "Taupo | Taupō-nui-a-Tia",
      "Wellington | Te Whanganui-a-Tara",
      "Nelson | Whakatu",
      "Christchurch | Ōtautahi",
      "Dunedin | Ōtepoti"
    };
    // Loops through everything in locations array
    for (String location : locations) {
      String topActivityName = null;
      double topAverage = -1;

      for (String activity : activitiesArrayList) {
        // Gets the start of the activity Id string from [ + 1
        int startOfCombinedId = activity.indexOf("[") + 1;
        // ends the activity id at / and makes the start of it the field above
        int endOfCombinedId = activity.indexOf("/", startOfCombinedId);
        // Combine them
        String fullActivityId = activity.substring(startOfCombinedId, endOfCombinedId);

        // Find operator ID from activityId (everything before last '-')
        String operatorId = fullActivityId.substring(0, fullActivityId.lastIndexOf("-"));
        // Checks for a match
        Operator matchedOperator = null;
        for (Operator operator : operatorArrayList) {
          if (operator.getId().equals(operatorId)) {
            matchedOperator = operator;
            break;
          }
        }
        // If no match continue
        if (matchedOperator == null) {
          continue;
        }

        String operatorLocation = matchedOperator.getLocationFullName();

        if (!operatorLocation.equals(location)) {
          continue;
        }

        int total = 0;
        int count = 0;
        // Loops through everything in the array list to check if they can be averaged
        // add total rating and increases count
        for (Review review : reviewArrayList) {
          if (review.getActivityId().startsWith(fullActivityId)) {
            if (review instanceof PublicReview || review instanceof ExpertReview) {
              total += review.getRating();
              count++;
            }
          }
        }
        // Ensure we can properly average it
        double average = (double) total / count;
        // extracts the values we want
        int start = activity.indexOf("* ") + 2;
        int end = activity.indexOf(": [");
        String activityName = activity.substring(start, end);
        // Checks for which one is top activity
        if (average > topAverage) {
          topAverage = average;
          topActivityName = activityName;
        }
      }
      // NO_REVIEWED_ACTIVITIES("No reviewed activities found in %s.")
      if (topActivityName == null) {
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location);
      } else {
        MessageCli.TOP_ACTIVITY.printMessage(
            location, topActivityName, String.valueOf(Math.round(topAverage)));
      }
    }
  }
}
