# Plateplanner 🍽️

Plateplanner is a smart and user-friendly meal-planning app designed to simplify your weekly food preparation. With a few taps, you can add dishes for the week, generate recipes, and create a shopping list with precise ingredient quantities. Manage your pantry with ease by ticking off ingredients you already have, and focus on what you need to buy.

---
## Live on Amazon Appstore

Plateplanner is now available on the Amazon Appstore! [Download it here](https://www.amazon.com/dp/B0DQ8ZD3YL/ref=apps_sf_sta) and start planning your meals today!

---
## Screenshots  

<div style="display: flex; flex-wrap: wrap; gap: 10px;">
  <img src="https://github.com/user-attachments/assets/f2316c4d-dcfe-4f41-8f9e-4330934e4c3b" width="200" />
  <img src="https://github.com/user-attachments/assets/7f9e6fec-1f2e-46b5-86b5-e31d78d1a1ae" width="200" />
  <img src="https://github.com/user-attachments/assets/30161f38-942c-4d0a-8fe6-8001855160c0" width="200" />  
  <img src="https://github.com/user-attachments/assets/7749fd00-4edf-4caa-adfe-a17e4dfc36dd" width="200" />
  <img src="https://github.com/user-attachments/assets/56a16bb5-a30f-4428-a615-4d7dce85cc6e" width="200" />
  <img src="https://github.com/user-attachments/assets/3c3f7344-7286-4544-8a84-a5d35bbda85d" width="200" />
  <img src="https://github.com/user-attachments/assets/022dacc4-b92c-47cb-8287-316afec3a7f4" width="200" />
  <img src="https://github.com/user-attachments/assets/9a3dac22-64e2-4d67-aef3-bc997dffee0c" width="200" />
  <img src="https://github.com/user-attachments/assets/90ff7d70-e68b-4439-86d2-871db6607f3d" width="200" />
</div>

---

## Key Features

### 🌟 Core Functionalities:  
- **Plan Weekly Meals:** Add dishes for the week effortlessly.  
- **AI-Generated Recipes:** Get personalized recipes using OpenAI API based on your selected dishes and preferences.  
- **Generate Ingredients with Quantities:** Instantly receive ingredient quantities tailored to your meal plan.  
- **Smart Shopping List:** Tick off the ingredients you have; shop for only the essentials.  
- **Automatic Updates:** Deleting a dish updates your recipes and shopping list, thanks to Room Database's foreign key relationships.  

### 🍴 Customization Options:  
- Dietary Preferences: Select from vegetarian, non-vegetarian, or eggitarian meal plans.  
- Servings Customization: Generate shopping lists based on the number of people you're cooking for.  

---

## Technologies Used

### 🛠️ Tech Stack:  
- **Kotlin & Jetpack Compose:** Modern, declarative UI for an intuitive user experience.  
- **OpenAI API:** Generates AI-powered recipes based on user-selected dishes.  
- **Room Database:** Handles local storage with CRUD operations and foreign key support for relational data.  
- **DataStore:** Manages user preferences persistently.  
- **Hilt:** Simplifies dependency injection for better modularity and testing.  
- **MVVM Architecture with Repository Pattern:** Ensures a scalable and maintainable codebase.  

---

## App Structure

### 📂 File Organization:  
- **`data`**:  
  - **`local`**: Manages Room database and DAO.  
  - **`remote`**: Handles OpenAI API calls for generating recipes.  
  - **`localpreference`**: Manages preferences with DataStore.  
  - **`repo`**: Repository layer for data access.  

- **`di`**: Dependency injection setup using Hilt.  

- **`ui`**:  
  - **`component`**: Reusable composables like buttons, dialogs, etc.  
  - **`navigation`**: Handles navigation across screens.  
  - **`screen`**: Includes all screens: Splash, Home, Shopping List, Ingredient & Recipe.  

- **`utils`**: Utility classes and functions.  
- **`viewmodel`**: Manages UI-related data and business logic.  

---

## Screens

1. **Splash Screen**: A welcoming screen to introduce the app.  
2. **Home Screen**: Add dishes for the week, view the bottom navigation bar.  
3. **Shopping Screen**: Manage your shopping list, tick off ingredients you have.  
4. **Ingredients & Recipes Screen**: View AI-generated recipes and detailed ingredient lists for your selected dishes.  

---

## How It Works

1. Add dishes for the week from the Home Screen.  
2. Click on the *Generate* button to get AI-generated recipes and ingredient quantities.  
3. Head to the Shopping Screen to manage your shopping list.  
4. Delete a dish if needed, and watch all related data automatically update.  

---

## Future Enhancements

- Add a screen to collect user preferences (dietary preferences, number of servings, etc.) at the start.  
- Allow customization for each dish (e.g., change ingredient quantities, modify recipe details).  
- Integrate additional APIs for advanced recipe suggestions.  
- Improve UI with more visual elements and animations.  

---

## Make Weekly Meal Planning Effortless with Plateplanner!  

Plateplanner turns the hassle of meal planning into a seamless and enjoyable experience. With the power of AI, you can ensure every meal is perfectly planned and prepared. [Download it now](https://www.amazon.com/dp/B0DQ8ZD3YL/ref=apps_sf_sta) and take control of your weekly meals!

