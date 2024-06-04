package net.hicare.hicaretest.ui.facility

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.hicare.core.data.model.Facility
import net.hicare.hicaretest.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegisterFacilityForm(
    facilities: List<Facility>,
    onPopupSnackBar: () -> Unit,
    onSaveFacilityName: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Column {
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(id = R.string.first_facility_name),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        OutlinedTextField(
                            readOnly = true,
                            value = selectedOptionText,
                            onValueChange = { },
                            trailingIcon = {
                                Icon(
                                    modifier = Modifier
                                        .size(30.dp),
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search",
                                    tint = Color.Blue
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Gray,
                                backgroundColor = Color.White,
                                focusedIndicatorColor = Color.Blue
                            ),
                            placeholder = {
                                Text(text = stringResource(id = R.string.select_facility_name))
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            }
                        ) {
                            facilities.forEach { facility ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedOptionText = facility.facilityName
                                        expanded = false
                                    }
                                ) {
                                    Text(text = facility.facilityName)
                                }
                            }
                        }
                    }
                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down",
                    tint = Color.Blue
                )
            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(id = R.string.second_register),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = {
                        if (selectedOptionText.isEmpty()) {
                            onPopupSnackBar()
                        } else {
                            onSaveFacilityName(selectedOptionText)
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "sync",
                        tint = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = stringResource(id = R.string.sync),
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRegisterFacilityForm(){
    RegisterFacilityForm(emptyList(), {}, {})
}
