package api;

public class MealRequest {
    private String uid;
    private String mealName;
    private String mealDescription;
    private String mealType;
    private int calories;
    private String date; // Added field
    private String time;  // Added field

    // Constructor that accepts all parameters
    public MealRequest(String uid, String mealName, String mealDescription, String mealType, int calories, String date, String time) {
        this.uid = uid;
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealType = mealType;
        this.calories = calories;
        this.date = date;
        this.time = time;
    }

    // Default constructor
    public MealRequest() {}

    // Getters and Setters
    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }

    public String getMealName() { return mealName; }
    public void setMealName(String mealName) { this.mealName = mealName; }

    public String getMealDescription() { return mealDescription; }
    public void setMealDescription(String mealDescription) { this.mealDescription = mealDescription; }

    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }

    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}
