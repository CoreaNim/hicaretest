package net.hicare.hicaretest.ui.facility

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.hicare.core.data.model.Facility
import net.hicare.core.network.util.ResultWrapper
import net.hicare.hicaretest.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FacilityScreen() {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = scaffoldState.snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data, modifier = Modifier.padding(50.dp),
                    shape = RoundedCornerShape(10.dp)
                )
            }
        }) {
        RegisterContent(scaffoldState)
    }
}

@Composable
fun RegisterContent(scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val facilityViewModel = hiltViewModel<FacilityViewModel>()
    var facilities = emptyList<Facility>()

    LaunchedEffect("findAllFacilities") {
        facilityViewModel.findAllFacilities()
    }

    val facilityName = facilityViewModel.facilityName.collectAsState()
    when (val response = facilityViewModel.facilitiesResult.collectAsState().value) {
        is ResultWrapper.Success -> {
            facilities = response.value.data
        }

        is ResultWrapper.AppServerError -> {
            popupSnackBar(scope, scaffoldState, response.serverError.message)
        }

        is ResultWrapper.GenericError, ResultWrapper.NetworkError -> {
            popupSnackBar(scope, scaffoldState, context.getString(R.string.api_call_failed))
        }

        else -> {}
    }

    Column {
        RegisterFacilityHeader(facilityName = facilityName.value.ifEmpty {
            stringResource(id = R.string.register_a_new_facility)
        })

        RegisterFacilityForm(facilities = facilities, onPopupSnackBar = {
            popupSnackBar(scope, scaffoldState, context.getString(R.string.please_select_fiacility))
        }, onSaveFacilityName = {
            facilityViewModel.saveFacilityName(it)
        })
    }
}

fun popupSnackBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    message: String,
    onDismissCallback: () -> Unit = {}
) {
    scope.launch {
        scaffoldState.snackbarHostState.showSnackbar(message = message)
        onDismissCallback.invoke()
    }
}
