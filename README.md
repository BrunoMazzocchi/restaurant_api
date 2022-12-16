# Restaurant Menu API

This API allows users to retrieve information about the menu items offered by a restaurant, organized into categories and meals.

## Getting Started

To use this API, you will need to make HTTP requests to the API endpoint, which is provided in the documentation.

## API Reference

The API has the following endpoints:

* `GET /categories`: This endpoint returns a list of all the food categories offered by the restaurant.

* `GET /categories/{id}`: This endpoint allows users to retrieve detailed information about a specific food category by providing its ID.

* `GET /categories/{id}/meals`: This endpoint returns a list of all the meals offered in a specific food category.

* `GET /categories/{id}/meals/{meal_id}`: This endpoint allows users to retrieve detailed information about a specific meal in a food category by providing its ID.

* `POST /categories`: This endpoint allows users to add a new food category to the restaurant's menu.

* `POST /categories/{id}/meals`: This endpoint allows users to add a new meal to a specific food category.

* `PUT /categories/{id}`: This endpoint allows users to update the information for a specific food category.

* `PUT /categories/{id}/meals/{meal_id}`: This endpoint allows users to update the information for a specific meal in a food category.

* `DELETE /categories/{id}`: This endpoint allows users to delete a specific food category.

* `DELETE /categories/{id}/meals/{meal_id}`: This endpoint allows users to delete a specific meal in a food category.

## Examples

Here are some examples of how to use the API:

### Retrieve a list of food categories
