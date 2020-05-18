package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.AddEditToDoScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.ToDoListScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem
import org.junit.Test

/**
 * Validates TO-DOs creation flows using Screen Object Pattern.
 */
class AddToDoTest : BaseTest() {
    private val addEditToDoScreen = AddEditToDoScreen()
    private val toDoListScreen = ToDoListScreen()

    @Test
    fun addsNewTodo() {

        toDoListScreen
                .clickAddFabButton()
                .addNewToDo(todoItem)
                .verifyToDoIsDisplayed(todoItem)
    }

    @Test
    fun addsNewTodoWithoutDescription() {
        toDoListScreen
                .clickAddFabButton()
                .typeToDoTitle(todoItem.title)
                .clickDoneFabButton()
                .verifyToDoIsDisplayed(todoItem)
    }

    @Test
    fun triesToAddEmptyToDo() {
        toDoListScreen
                .clickAddFabButton()
                .addEmptyToDo()
                .verifySnackbarForEmptyToDo()
    }

    companion object {
        private var todoItem = TodoItem()
    }
}
