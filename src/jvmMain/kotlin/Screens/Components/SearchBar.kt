package Screens.Components

import VIewModels.SearchTransfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.zIndex
import kotlin.math.exp

@Composable
fun SearchBar(
    hint:String,
    initText: String,
    onTextChanged:(text:String)->Unit,
    suggestions:List<SearchTransfo> = emptyList(),
    modifier: Modifier=Modifier,
    itemSelected:(transfo:SearchTransfo)->Unit={_ ->}
){
    var search by remember {
        mutableStateOf(initText)
    }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    Column(modifier=modifier) {
var expanded by remember { mutableStateOf(false) }
        TextField(
            value = search,
            onValueChange = {
                search = it
                onTextChanged(it)
                if(suggestions.isNotEmpty()) expanded=true
            },
            placeholder = {
                Text(hint, fontSize = 18.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = Color(0xff0073FF),
                focusedIndicatorColor = Color(0xff0073FF),
            ),
            leadingIcon = {
                Icon(Icons.Default.Search, "", tint = Color.Gray)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .shadow(2.dp, RoundedCornerShape(20.dp))
                .onGloballyPositioned { coordinates ->
                mTextFieldSize = coordinates.size.toSize()
            }
            ,shape = RoundedCornerShape(20.dp),
        )
        if(expanded){
            Column(modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                .background(Color.White)
                .shadow(1.dp)
            ) {
                suggestions.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        onClick = {
                            search = "${s.n_serie} ${s.marque}"
                            itemSelected(s)
                            expanded=false
                        }){Text(text = "${s.n_serie} ${s.marque}")} }
            }
        }
    }
}