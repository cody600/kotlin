package eu.tutorials.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp( navController: NavHostController){
    val recipeViewModel: MainViewModel =  viewModel()
    val viewState by recipeViewModel.categoriesState
    NavHost( navController = navController, startDestination = Screen.RecipeScreen.route){

        composable(route=Screen.RecipeScreen.route){
            RecipeScreen( viewState = viewState, navigateToDetail = {
                // Pass data from the current screen to the detail screen
                // It utilized the savestateHandle  it is component of the compose navigation framework
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }

        composable(route=Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?:
            Category(idCategory = "", strCategory = "", strCategoryThumb = "", strCategoryDescription = "")
            CategoryDetailScreen( category)
        }

    }//NavHost
}