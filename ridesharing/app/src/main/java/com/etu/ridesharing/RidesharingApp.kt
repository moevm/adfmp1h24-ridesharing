package com.etu.ridesharing

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.data.DataDriveInfoList
import com.etu.ridesharing.models.DriveInfoModel
import com.etu.ridesharing.ui.screens.AboutScreen
import com.etu.ridesharing.ui.screens.FindCompanionScreen
import com.etu.ridesharing.ui.screens.MyDrivesScreen
import kotlinx.coroutines.launch


enum class RidesharingScreen(@StringRes val title: Int) {
    Primary(title = R.string.app_name),
    Profile(title = R.string.profile),
    FindCompanion(title = R.string.find_companion),
    CreateDrive(title = R.string.create_drive),
    MyDrives(title = R.string.my_drives),
    DriveHistory(title = R.string.drive_history),
    Support(title = R.string.support),
    About(title = R.string.about),
    Admin(title = R.string.admin)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RidesharingAppBar(
    onClickMenu: () -> Unit,
    currentScreen: RidesharingScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier){

        TopAppBar(
            title = { Text(stringResource(currentScreen.title)) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = modifier,
            navigationIcon = {
                IconButton(onClick = onClickMenu) {
                    Icon(Icons.Outlined.Menu, contentDescription = "Localized description")
                }
            }
        )
    }

}


@Composable
fun RidesharingApp(
    driveInfoModel: DriveInfoModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RidesharingScreen.valueOf(
        backStackEntry?.destination?.route ?: RidesharingScreen.Primary.name
    )
    var myDrivesList by remember { mutableStateOf(DataDriveInfoList.driveList) }
    fun removeDriveInfo(driveInfo: DriveInfoModel) {
        myDrivesList = myDrivesList.toMutableList().apply { remove(driveInfo) }
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column() {
                    RidesharingScreen.values().forEach { screen ->
                        if(screen.name !== RidesharingScreen.Primary.name){
                            Text(
                                text = stringResource(screen.title),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        scope.launch {
                                            drawerState.close() // Закрываем навигационный ящик после выбора страницы
                                            navController.navigate(screen.name)
                                        }
                                    }
                            )
                            if(screen.name == RidesharingScreen.Profile.name){
                                Divider()
                            }

                        }
                    }
                }
            }
            /**/

        },
    ) {
        Scaffold(
            topBar = {
                    RidesharingAppBar(
                        onClickMenu = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                        currentScreen = currentScreen,
                        canNavigateBack = false,
                        navigateUp = { /* TODO: implement back navigation */ }
                    )

            }
        ) { innerPadding ->
            //val uiState by viewModel.uiState.collectAsState()
            NavHost(
                navController = navController,
                startDestination = RidesharingScreen.FindCompanion.name,
                modifier = Modifier.padding(innerPadding),
            ) {
                composable(route = RidesharingScreen.Primary.name) {
                    /*StartOrderScreen(
                    quantityOptions = DataSource.quantityOptions,
                    onNextButtonClicked = {
                        viewModel.setQuantity(it)
                        navController.navigate(CupcakeScreen.Flavor.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )*/
                }
                composable(route = RidesharingScreen.FindCompanion.name) {
                    FindCompanionScreen(companionDrivesList = myDrivesList)
                    /*val context = LocalContext.current
                SelectOptionScreen(
                    subtotal = uiState.price,
                    options = DataSource.flavors.map { id -> context.resources.getString(id) },
                    onNextButtonClicked = { navController.navigate(CupcakeScreen.Pickup.name) },
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    onSelectionChanged = { viewModel.setFlavor(it) },
                    modifier = Modifier.fillMaxHeight()
                )*/
                }
                composable(route = RidesharingScreen.CreateDrive.name) {
                    /*SelectOptionScreen(
                    subtotal = uiState.price,
                    options = uiState.pickupOptions,
                    onNextButtonClicked = { navController.navigate(CupcakeScreen.Summary.name) },
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    onSelectionChanged = { viewModel.setDate(it) },
                    modifier = Modifier.fillMaxHeight()
                )*/
                }
                composable(route = RidesharingScreen.MyDrives.name) {
                    MyDrivesScreen(myDrivesList = myDrivesList,
                        onRemoveDrive = { driveInfo -> removeDriveInfo(driveInfo) }
                    )
                    /*OrderSummaryScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    onSendButtonClicked = { subject: String, summary: String ->

                    },
                    modifier = Modifier.fillMaxHeight()
                )*/
                }
                composable(route = RidesharingScreen.DriveHistory.name) {


                }
                composable(route = RidesharingScreen.Support.name) {
                }
                composable(route = RidesharingScreen.Admin.name) {
                    RideSharingAppAdmin()
                }
                composable(route = RidesharingScreen.About.name) {
                    AboutScreen()
                }
            }
        }
    }
}