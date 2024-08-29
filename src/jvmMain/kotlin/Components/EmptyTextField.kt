package Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun EmptyTextField(
    title:String,
    onTextChanged:(String)->Unit,
    modifier: Modifier
){
    var text by remember { mutableStateOf("") }
Column {
    Text(title, fontSize = 18.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 10.dp))
    TextField(text,{
        text=it
        onTextChanged(it)
    },modifier=modifier.clip(RoundedCornerShape(10.dp)).border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(10.dp)),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color(0xff0073FF),
            focusedIndicatorColor = Color(0xff0073FF))
        )
}
}