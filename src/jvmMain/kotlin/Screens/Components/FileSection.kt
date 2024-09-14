package Screens.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FileSection(
    title:String,
    initText:String,
    onButtonClicked:()->Unit,
    modifier: Modifier
){
    var text by remember { mutableStateOf(initText) }
    Column(modifier = modifier) {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 10.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            TextField(initText,
                onValueChange = {},
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(10.dp)).weight(1f)
                    .height(30.dp)
                    .padding(end = 10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color(0xff0073FF),
                    focusedIndicatorColor = Color(0xff0073FF)
                ),
                readOnly = true,
                trailingIcon = {
                    if (text == "")
                        Icon(painterResource("icons/error.svg"), "", tint = Color.Red)
                    else
                        Icon(painterResource("icons/mark.svg"), "", tint = Color.Green)
                }
            )
            Row (verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .padding(horizontal = 5.dp)
                    .shadow(2.dp, RoundedCornerShape(10.dp))
                    .background(Color(0xff0073FF))
                    .clickable { onButtonClicked() }
            ){
                Text("Parcourir", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                Icon(painter = painterResource("icons/insert_photo.svg"),"", tint = Color.White,modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
            }
        }
    }
}