package Screens.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun DropDown(districts:List<String>,Label:String,selected:(item:String,position:Int)->Unit,enabled:Boolean,modifier: Modifier=Modifier){
    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier) {
        Text(Label, fontSize = 18.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 10.dp))
        TextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            readOnly = true,
            trailingIcon = {
                Icon(icon,"contentDescription", modifier = Modifier.clickable { mExpanded = !mExpanded })},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color(0xff0073FF)
            ),
            enabled = enabled
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            districts.forEachIndexed { index, s ->
                DropdownMenuItem(
                onClick = {
                    mSelectedText=districts[index]
                    selected(s,index)
                    mExpanded=false
                }){Text(text = s)} }
        }
    }
}