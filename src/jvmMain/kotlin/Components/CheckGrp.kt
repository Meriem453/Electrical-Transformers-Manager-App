package Components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CheckGrp(
    isChecked:Boolean,
    onChecked:()->Unit,
    text:String
){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {if (it) onChecked()},
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xff0073FF),
                checkmarkColor = Color.White,
            )
        )
        Text(text, fontWeight = FontWeight.Medium, fontSize = 18.sp, modifier = Modifier.padding(start = 10.dp))
    }
}