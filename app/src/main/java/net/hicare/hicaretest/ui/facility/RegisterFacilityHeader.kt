package net.hicare.hicaretest.ui.facility

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegisterFacilityHeader(facilityName: String) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Filled.Settings,
            contentDescription = "Facility"
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = facilityName,
            style = MaterialTheme.typography.subtitle1,
            color = Color.Blue
        )
    }
}

@Preview
@Composable
fun PreviewRegisterFacilityHeader() {
    RegisterFacilityHeader(facilityName = "Facility 1")
}
