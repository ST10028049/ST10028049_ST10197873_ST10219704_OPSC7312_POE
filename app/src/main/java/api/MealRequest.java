package api;

public class MealRequest {
    private String uid;
    private String mealName;
    private String mealDescription;
    private String mealType;

    // Constructor
    public MealRequest(String uid, String mealName, String mealDescription, String mealType) {
        this.uid = uid;
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealType = mealType;
    }

    // Getters
    public String getUid() {
        return uid;
    }

    public String getMealName() {
        return mealName;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public String getMealType() {
        return mealType;
    }
}
