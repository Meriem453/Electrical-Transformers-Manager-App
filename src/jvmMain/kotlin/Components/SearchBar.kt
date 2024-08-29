package Components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    hint:String,
    text: String,
    onTextChanged:(text:String)->Unit,
    suggestions:List<String> = emptyList(),
    modifier: Modifier=Modifier
){
    TextField(
        value = text,
        onValueChange = {onTextChanged(it)},
        placeholder = {
            Text(hint, fontSize = 18.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color(0xff0073FF),
            focusedIndicatorColor = Color(0xff0073FF),
        ),
        leadingIcon = {
            Icon(Icons.Default.Search,"", tint = Color.Gray)
        },
        modifier = modifier.padding(vertical = 5.dp).shadow(2.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp)
    )
}