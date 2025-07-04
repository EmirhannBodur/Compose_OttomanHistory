package com.emirhan.ottomanhistory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.emirhan.ottomanhistory.ui.theme.OttomanHistoryTheme


@Composable
fun MainScreenMenu(navController: NavController){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            ButtonDesing("Padişahlar") { //Navigate
                navController.navigate("padisah_page")
            }
            ButtonDesing("Savaşlar") { //Navigate
                navController.navigate("savas_page")
            }
            ButtonDesing("Yapılan Antlaşmalar") { //Navigate
            }
            ButtonDesing("Haritalar") { //Navigate
            }
            ButtonDesing("Ayarlar") { //Navigate
            }
        }
}
@Composable
fun ButtonDesing(text: String,onClick: () -> Unit){
    Button(onClick=onClick, colors = ButtonDefaults.buttonColors(
        contentColor = Color.Black,
        containerColor = Color.LightGray
    ),
        shape = RectangleShape,
        border = BorderStroke(width = 2.dp, color = Color.Gray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)) {
        Text(text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
            )
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview(){
    OttomanHistoryTheme {
    }
}

