package com.example.android.architecture.blueprints.todoapp.test.steps.addtodo

import com.example.android.architecture.blueprints.todoapp.test.screens.AddEditToDoScreen
import com.example.android.architecture.blueprints.todoapp.test.screens.ToDoListScreen
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Assert.assertTrue

class AddTodoSteps {

    private val toDoListScreen = ToDoListScreen()
    private val addEditToDoScreen = AddEditToDoScreen()

    @Given("I can see todo list")
    fun i_can_see_todo_list() {
        assertTrue("Todo list is not displayed", toDoListScreen.isTodoListDisplayed())
    }

    @When("I click add button")
    fun i_click_add_button() {
        toDoListScreen.clickOnAddButton()
    }

    @And("I enter todo title {string}")
    fun i_enter_todo_title(title: String) {
        addEditToDoScreen.typeToDoTitle(title)
    }

    @And("I enter todo description {string}")
    fun i_enter_todo_description(description: String) {
        addEditToDoScreen.typeToDoDescription(description)
    }

    @And("I click done button")
    fun i_click_done_button() {
        addEditToDoScreen.clickDoneFabButton()
    }

    @Then("I expect to see successfully added todo on the list {string} {string}")
    fun i_expect_to_see_successfully_added_todo_on_the_list(title: String, description: String) {
        assertTrue("Added todo is not displayed on the list", toDoListScreen.isAddedTodoDisplayed(title, description))
    }

    /*
    Result for empty added todo
     */
    @Then("I expect to see information that todo item can not be empty")
    fun i_expect_to_see_information_that_todo_item_can_not_be_empty() {
        addEditToDoScreen.isEmptyTitlePopupDisplayed()
    }
}