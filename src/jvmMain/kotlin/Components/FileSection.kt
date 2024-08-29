package Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FileSection(
    title:String,
    onButtonClicked:()->Unit,
    modifier: Modifier
){
    var text by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 10.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            TextField(text,
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
            Button(
                "icons/insert_photo.svg",
                "Parcourir",
                Color.White,
                Color(0xff0073FF)
            ) { text = "2003" }
        }
    }
}