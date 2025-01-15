package com.example.pertemuan14firebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pertemuan14firebase.ui.ViewModel.EditMhsViewModel
import com.example.pertemuan14firebase.ui.ViewModel.PenyediaViewModel
import com.example.pertemuan14firebase.ui.view.DetailMhsView
import com.example.pertemuan14firebase.ui.view.EditMhsView
import com.example.pertemuan14firebase.ui.view.HomeMhsView
import com.example.pertemuan14firebase.ui.view.InsertMhsView

@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(
            DestinasiHome.route
        ) {
            HomeMhsView(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsert.route)
                },
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                    println("PengelolaHalaman: nim = $nim")
                },
                onEditClick = { nim ->
                    navController.navigate("${DestinasiUpdate.route}/$nim")
                    println("PengelolaHalaman: nim = $nim")
                }
            )
        }
        composable(
            DestinasiInsert.route
        ) {
            InsertMhsView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }
        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val nim =
                backStackEntry.arguments?.getString(DestinasiDetail.NIM)

            nim?.let {
                DetailMhsView(
                    nim = nim,
                    onBack = { navController.popBackStack() },
                )
            }
        }
        composable(
            DestinasiUpdate.routeWithArg
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString(DestinasiUpdate.NIM) ?: ""
            val viewModel: EditMhsViewModel = viewModel(factory = PenyediaViewModel.Factory)
            val mahasiswaState by viewModel.mahasiswaState.collectAsState(initial = null)
            SideEffect {
                viewModel.getMahasiswaByNim(nim)
            }
            mahasiswaState?.let { mahasiswa ->
                EditMhsView(
                    mahasiswa = mahasiswa,
                    onUpdateSuccess = {
                        navController.popBackStack()
                    },
                    onCancel = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
