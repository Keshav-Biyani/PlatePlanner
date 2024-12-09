package com.example.plateplanner.utils

fun generateGPTQuery(dishes: List<String>, preferences: String, numberOfPeople: Int): String {
    // Define your template string
  val template = """
      I will provide a list of dishes, along with preferences such as vegetarian, non-vegetarian, and the number of people to serve. Based on this information, generate a shopping list with ingredient quantities and detailed recipes in the following JSON format:

      {
        "shoppingList": [
          {"item": "ingredient1", "quantity": "quantity1"},
          {"item": "ingredient2", "quantity": "quantity2"}
        ],
        "recipes": [
          {
            "id": 1,
            "name": "dish1",
            "ingredients": [
              {"ingredient": "ingredient1", "quantity": "quantity1"},
              {"ingredient": "ingredient2", "quantity": "quantity2"}
            ],
            "instructions": "Step 1, Step 2"
          },
          {
            "id": 2,
            "name": "dish2",
            "ingredients": [
              {"ingredient": "ingredient3", "quantity": "quantity3"},
              {"ingredient": "ingredient4", "quantity": "quantity4"}
            ],
            "instructions": "Step 1, Step 2"
          }
        ]
      }

      Each recipe should include:

      - `id`: Unique identifier for the recipe.
      - `name`: Name of the dish, matching the name provided in the list.
      - `ingredients`: List of ingredients specific to the dish, each including the ingredient name and quantity required based on the number of people.
      - `instructions`: Step-by-step instructions for cooking the dish, formatted in detail. Each step should outline the preparation and cooking methods with specific ingredient quantities.

      Example of Detailed Instructions:
      Each recipe should have well-defined steps, including quantities,precise and Detailed instructions, formatted as follows:

      "Prepare the Paneer:
      - Cube the paneer and soak it in warm water for 10 minutes. Set aside.

      Make the Masala:
      - Heat 1 tablespoon of oil and 1 tablespoon of butter in a pan.
      - Add 1 teaspoon of cumin seeds and let them splutter.
      - Add 1/2 cup finely chopped onions, sauté until golden brown.
      - Add 1 tablespoon ginger-garlic paste, cook until the raw smell disappears."

      Request:
      Generate the shopping list with ingredient quantities and recipes for the following dishes:
        Dishes: $dishes  
        Preferences: $preferences 
        Number of people: $numberOfPeople

      I only want the output in the specified JSON format with all fields populated based on the provided dishes, preferences, and number of people. Ensure to include detailed ingredient quantities and cooking steps in the instructions.

  """.trimIndent()

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

    val temp1 = """
        I will provide a list of dishes, along with preferences such as vegetarian, non-vegetarian, and the number of people to serve. Based on this information, generate detailed recipes in the following JSON format:

        {
          "recipes": [
            {
              "id": "1",
              "name": "dish1",
              "ingredients": [
                {"ingredient": "ingredient1", "quantity": "quantity1"},
                {"ingredient": "ingredient2", "quantity": "quantity2"}
              ],
              "instructions": "Step 1, Step 2"
            },
            {
              "id": "2",
              "name": "dish2",
              "ingredients": [
                {"ingredient": "ingredient3", "quantity": "quantity3"},
                {"ingredient": "ingredient4", "quantity": "quantity4"}
              ],
              "instructions": "Step 1, Step 2"
            }
          ]
        }

        Each recipe should include:

        - `id`: Unique identifier for the recipe.
        - `name`: Name of the dish, matching the name provided in the list.
        - `ingredients`: List of ingredients specific to the dish, each including the ingredient name and the quantity required, adjusted to serve the specified number of people.
        - `instructions`: Step-by-step instructions for cooking the dish, formatted in detail. Each step should outline the preparation and cooking methods with specific ingredient quantities.

        Example of Detailed Instructions:
        Each recipe should have well-defined steps, including quantities and precise instructions, formatted as follows:

        "Prepare the Paneer:
        - Cube the paneer and soak it in warm water for 10 minutes. Set aside.

        Make the Masala:
        - Heat 1 tablespoon of oil and 1 tablespoon of butter in a pan.
        - Add 1 teaspoon of cumin seeds and let them splutter.
        - Add 1/2 cup finely chopped onions, sauté until golden brown.
        - Add 1 tablespoon ginger-garlic paste, cook until the raw smell disappears."

        Request:
        Generate recipes with ingredient quantities adjusted for the specified number of people and detailed cooking steps for the following dishes:
        Dishes: $dishes  
        Preferences: $preferences 
        Number of people: $numberOfPeople

        I only want the output in the specified JSON format with all fields populated based on the provided dishes, preferences, and number of people. Ensure to include detailed ingredient quantities and cooking steps in the instructions.

    """.trimIndent()

    return temp1
}
