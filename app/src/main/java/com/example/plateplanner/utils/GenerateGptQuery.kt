package com.example.plateplanner.utils

fun generateGPTQuery(dishes: List<String>, preferences: String, numberOfPeople: Int): String {
    // Define your template string
    val template = """I will provide a list of dishes, along with preferences such as vegetarian, non-vegetarian, and the number of people to serve. Based on this information, generate a ShoppingList data class that includes:
1. A shopping list (shoppingList) with the required ingredients and their quantities for all dishes.
2. A list of Recipe objects, where each Recipe contains:
   - id: Unique identifier for the recipe.
   - name: Name of the dish.
   - ingredients: Ingredients specific to the dish (without quantities).
   - instructions: Step-by-step instructions for cooking the dish, formatted in detail. Each step should clearly outline the preparation method, including specific ingredient amounts and detailed cooking instructions, similar to this example:

     Prepare the Paneer:
     - Cube the paneer and soak it in warm water for 10 minutes. This keeps it soft. Set aside.

     Make the Masala:
     - Heat 1 tablespoon oil and 1 tablespoon butter in a pan.
     - Add cumin seeds, and let them splutter.
     - Add the finely chopped onions, sauté until golden brown.
     - Add the ginger-garlic paste, cook until the raw smell goes away.

     (Continue with similar detailed steps...)
Here is the structure of the ShoppingList and Recipe data classes for your reference:
data class ShoppingList(
    val shoppingList: List<String>,
    val recipes: List<Recipe>
)

data class Recipe(
    val id: String,
    val name: String,
    val ingredients: List<String>,
    val instructions: String
)

Please provide only the ShoppingList data class as the output, with all fields populated based on the dishes, preferences, and the number of people. The instructions should be detailed, including ingredient amounts and cooking steps. Now, generate the ShoppingList for the following dishes:

Dishes: ${dishes.joinToString(", ") { "\"$it\"" }}
Preferences: $preferences 
Number of people: $numberOfPeople
I only want the code, no other lines or explanations, and the output should be structured like an API response in JSON format."""

    val temp = """
        I will provide a list of dishes, along with preferences such as vegetarian, non-vegetarian, and the number of people to serve. Based on this information, generate a shopping list in the following JSON format:

        {
          "shoppingList": ["item1", "item2", "item3"],
          "recipes": [
            {
              "id": "1",
              "name": "dish1",
              "ingredients": ["ingredient1", "ingredient2"],
              "instructions": "Step 1, Step 2"
            },
            {
              "id": "2",
              "name": "dish2",
              "ingredients": ["ingredient3", "ingredient4"],
              "instructions": "Step 1, Step 2"
            }
          ]
        }

        Each recipe will include:
        - id: Unique identifier for the recipe.
        - name: Name of the dish as same as i have provided in list.
        - ingredients: Ingredients specific to the dish (without quantities).
        - instructions: Step-by-step instructions for cooking the dish, formatted in detail. Each step should clearly outline the preparation method, including specific ingredient amounts and detailed cooking instructions, similar to this example:

           Prepare the Paneer:
           - Cube the paneer and soak it in warm water for 10 minutes. This keeps it soft. Set aside.

           Make the Masala:
           - Heat 1 tablespoon oil and 1 tablespoon butter in a pan.
           - Add cumin seeds, and let them splutter.
           - Add the finely chopped onions, sauté until golden brown.
           - Add the ginger-garlic paste, cook until the raw smell goes away.

           (Continue with similar detailed steps...)

        Now, generate the shopping list and recipes for the following dishes:

        Dishes: $dishes  
        Preferences: $preferences 
        Number of people: $numberOfPeople

        I only want the output in the given JSON format, with all fields populated based on the dishes, preferences, and the number of people. The instructions should be detailed, including ingredient amounts and cooking steps.

    """.trimIndent()

    return temp
}
