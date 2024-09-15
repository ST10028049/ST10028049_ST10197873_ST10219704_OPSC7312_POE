package api;

public class MealRequest {
    private String uid;
    private String mealName;
    private String mealDescription;
    private String mealType;

    public MealRequest(String uid, String mealName, String mealDescription, String mealType) {
        this.uid = uid;
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealType = mealType;
    }

    // Getters and setters
}

