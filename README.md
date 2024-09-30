## ST10028049_ST10197873_ST10219704_ST10054051_OPSC7312

# POE

Part 2


# HealthSync App - README FILE


## Overview
HealthSync is a comprehensive wellness-focused mobile application designed to assist users in managing their health and fitness goals seamlessly. The app integrates fitness tracking, meal planning, and personalized wellness features, ensuring users maintain a balanced, healthy lifestyle. The intuitive interface and robust functionality encourage users to engage consistently with their fitness and nutrition habits.

## Purpose
HealthSync aims to provide users with an all-in-one platform that simplifies the management of their health and wellness routines. By combining features such as real-time workout tracking, personalized nutrition plans, and social challenges, HealthSync empowers users to make informed decisions about their health, stay motivated, and achieve their fitness goals effectively.


## Design Considerations
When designing HealthSync, several key considerations were taken into account to ensure the app meets user needs and provides an optimal user experience:

1. **User-Centric Design:**
   - **Intuitive Interface:** The app features a clean and user-friendly interface, making it easy for users of all ages and tech-savviness to navigate and utilize its features.
   - **Accessibility:** Ensured that the app is accessible to users with disabilities by incorporating features like biometric authentication and voice-over support.

2. **Security and Privacy:**
   - **Data Protection:** Implemented robust authentication methods, including single sign-on (SSO) and biometric authentication, to safeguard user data.
   - **Privacy Compliance:** Ensured compliance with data protection regulations to protect user information.

3. **Scalability:**
   - **Modular Architecture:** Designed the app with a modular architecture to facilitate the addition of new features and scalability as user base grows.
   - **API Integration:** Utilized RESTful APIs to ensure seamless communication between the app and backend services.

4. **Performance:**
   - **Optimized Code:** Focused on writing efficient code to ensure the app runs smoothly without lags or crashes.
   - **Resource Management:** Managed resources effectively to minimize battery consumption and optimize app performance.

5. **Customization:**
   - **Personalized Plans:** Offered personalized meal and fitness plans tailored to individual user goals and preferences.
   - **Flexible Settings:** Allowed users to customize app settings, including language preferences and notification settings.

6. **Engagement and Motivation:**
   - **Social Challenges:** Incorporated social challenges to foster a sense of community and healthy competition among users.
   - **Achievements and Rewards:** Implemented an achievements system to motivate users by rewarding their progress and milestones.


## Features
1. **User Authentication:**
   - **Welcome Page:** Users are greeted with the option to log in or sign up.
   - **Single Sign-On (SSO):** Users can log in using SSO integration for convenience.
   - **Biometric Authentication:** Adds an extra layer of security by supporting fingerprint authentication.
   
2. **Fitness Tracking:**
   - **Exercise Tracker:** Log exercises, view burned calories, and track workout durations.
   - **Fitness Programs:** Choose from beginner, intermediate, or advanced exercises, complete with fitness tips and cardio-specific workouts.

3. **Meal Management:**
   - **Food Diary:** Log meals, track daily calorie intake, and record water consumption. Users can view their meal details and make changes easily.
   - **Meal Plan:** Personalized meal plans with detailed recipe instructions, ingredient lists, and cookware requirements.

4. **User Goals & Reports:**
   - **Progress Reports:** Track fitness and nutrition progress through detailed analytics.
   - **Goals:** Set and track daily fitness, nutrition, and water intake goals.

5. **Additional Features:**
   - **Settings:** Modify app settings, profile, and language options.
   - **Contact Us:** Users can send feedback or report issues.
   - **Achievements:** Pre-defined achievements to motivate users in their health journey.


## Technologies Used
1. **Android (Kotlin):** The app is built using Kotlin, offering a clean, user-friendly interface with various fitness, nutrition, and goal-setting features.
2. **REST API Integration:** Connects to a backend REST API to store and retrieve user data, managing authentication, meal tracking, and grocery delivery functionality.
3. **Firebase Authentication:** Utilized for secure login, registration, and biometric authentication capabilities.
4. **External Libraries:**
   - **Gson:** Parses JSON data from the REST API for displaying meal plans, exercise data, and grocery information.
   - **Retrofit:** Facilitates network calls to the REST API.
5. **Biometric API:** Implements fingerprint authentication for enhanced security.


## GitHub & GitHub Actions
### GitHub
HealthSync's development is managed using GitHub for version control. The repository is regularly updated with commits that reflect the ongoing progress of the project. Key aspects include:
- **Branching Strategy:** Utilized feature branches to manage new features and bug fixes, ensuring a clean and organized codebase.
- **Commit Messages:** Maintained clear and descriptive commit messages for easy tracking of changes and collaboration.
- **Issues & Project Management:** Used GitHub Issues and Projects to track tasks, feature requests, and bug reports, facilitating efficient project management.

### GitHub Actions
Automated workflows are set up using GitHub Actions to streamline the development process:
- **Automated Builds:** Configured workflows to automatically build the Android app upon code commits, ensuring that the app compiles correctly.
- **Automated Testing:** Implemented automated testing for core functionalities, running unit tests to verify the integrity of the codebase,  ensuring the app runs smoothly across different devices.
- **Continuous Integration (CI):** Enabled continuous integration to automatically test and build the app, ensuring that new changes do not introduce bugs or break existing features.

**Resources:**
- [Automated Build Android App with GitHub Actions](https://github.com/marketplace/actions/automated-build-android-app-with-github-action)
- [Sample GitHub Actions Workflow](https://github.com/lMAD5112/Github-actions/blob/main/.github/workflows/build.yml)


## Unit Testing
Detailed unit testing has been implemented for key features to ensure reliability and performance:
- **User Registration and Login:** Tested authentication flows, including SSO and biometric authentication.
- **API Connectivity:** Verified successful data retrieval and submission to the REST API for exercises, meals, and grocery information.
- **Error Handling:** Ensured the app gracefully handles invalid inputs and network errors without crashing.


## Installation and Setup
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/username/HealthSync.git
   ```
2. **Open in Android Studio:**
   - Launch Android Studio and select "Open an existing project."
   - Navigate to the cloned `HealthSync` directory and open it.

3. **Configure Firebase:**
   - Set up Firebase for authentication by adding the `google-services.json` file to the `app` directory.
   - Follow Firebase setup instructions to enable authentication methods used in the app.

4. **Install Dependencies:**
   - Ensure all external libraries (e.g., Gson, Retrofit) are included in the `build.gradle` file.
   - Sync the project to download all dependencies.

5. **Connect to REST API:**
   - Update the API endpoint URLs in the app's configuration files if necessary.
   - Ensure the REST API is hosted and accessible for the app to communicate with it.

6. **Build and Run:**
   - Select an Android emulator or connected device.
   - Click the "Run" button in Android Studio to build and launch the app.


## Usage
1. **Register & Login:**
   - Open the app and choose to register or log in.
   - Register using your email or log in via Single Sign-On (SSO).
   - Set up biometric authentication for enhanced security.

2. **Track Fitness:**
   - Access the Exercise Tracker to log workouts, view burned calories, and monitor workout durations.
   - Select from various Fitness Programs tailored to your fitness level.

3. **Log Meals & Water Intake:**
   - Use the Food Diary to create meal plans for each day or week, keeps track of it
   - View detailed information about each recipe

4. **Set Goals:**
   - Define personal fitness, nutrition, and hydration goals.
   - Monitor your progress towards achieving these goals through detailed reports.

5. **Achievements:**
   - Unlock pre-defined achievements as you reach various milestones in your health journey.

## Video Demonstration
Watch the full video demonstration of the HealthSync app, showcasing its features and how it connects to a REST API, here: [HealthSync App Demo]).


## AI Tools Usage
AI tools were instrumental in the development of HealthSync in the following ways:
- **Code Debugging:** Utilized ChatGPT to troubleshoot and resolve API connection issues, specifically related to JSON data parsing.
- **Code Generation:** Leveraged ChatGPT to generate Kotlin code snippets for user authentication and error handling, which were then refined and integrated into the app.
- **Design Assistance:** Employed AI tools to assist in designing the app's user interface and generating visual assets like the app icon.
- **Content Generation:** Used AI for drafting documentation and creating user-facing content such as fitness tips and recipe descriptions.
- **Image Generation:** An AI tool was used to create the app's icon and other visual assets.

All AI-generated content was reviewed and customized to fit the specific needs of HealthSync, ensuring accuracy and relevance.


## Conclusion
HealthSync offers a comprehensive platform for users to manage their health, fitness, and nutrition goals effectively. By integrating features such as biometric authentication, fitness tracking, personalized meal plans, and grocery delivery options, HealthSync empowers users to lead healthier and more balanced lifestyles.

The app's development leverages modern technologies and best practices, including RESTful API integration, Firebase Authentication, and automated workflows with GitHub Actions, ensuring a robust and scalable solution. HealthSync's user-centric design and engaging features make it a valuable tool for anyone looking to improve their wellness journey.

## Contact
For any questions, feedback, or support, please reach out through the "Contact Us" feature within the app or email us at [support@healthsync.com](mailto:support@healthsync.com).

---

**Note:** This app is currently in prototype development. Some features may be deferred until the final POE submission.

---
