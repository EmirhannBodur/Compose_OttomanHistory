package com.emirhan.ottomanhistory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emirhan.ottomanhistory.model.PadisahData

@Composable
fun PadisahDetail(padisah: PadisahData){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())) {
        Text(text = padisah.isim,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black)
        Image(bitmap = ImageBitmap.imageResource(padisah.gorsel),
            contentDescription = padisah.isim,
            modifier = Modifier
                .size(height = 150.dp, width = 120.dp))
        Text(text = padisah.lakap.toString(),
            fontWeight = FontWeight.Light,
            fontSize = 20.sp,
            color = Color.Black,
            fontStyle = FontStyle.Italic)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = "Doğum Yılı: ${padisah.dogum}", modifier = Modifier.padding(all = 5.dp), fontWeight = FontWeight.Light, color = Color.Black, fontSize = 15.sp)
            Text(text = "Ölüm Yılı: ${padisah.olum}", modifier = Modifier.padding(all = 5.dp), fontWeight = FontWeight.Light, color = Color.Black, fontSize = 15.sp)
        }
        Text(text = padisah.donem.toString(), fontWeight = FontWeight.Light, color = Color.Black)
        Text(text = "Saltanat Süresi:${padisah.saltanatSuresi}", fontWeight = FontWeight.Light, color = Color.Black)
        Spacer(modifier = Modifier.fillMaxWidth().background(color = Color.Gray).height(1.dp).padding(all = 5.dp))
        Text(text = padisah.biyografi.toString(), textAlign = TextAlign.Center, color = Color.Black)
        Spacer(modifier = Modifier.fillMaxWidth().background(color = Color.Gray).height(1.dp).padding(all = 5.dp))
        Text(text = "ÇOCUKLARI", fontWeight = FontWeight.SemiBold, color = Color.Red)
        Text(text = padisah.cocuklari, textAlign = TextAlign.Center, color = Color.Black)
        Spacer(modifier = Modifier.fillMaxWidth().background(color = Color.Gray).height(1.dp).padding(all = 5.dp))
        Text(text = "SAVAŞLAR/İSYANLAR", fontWeight = FontWeight.SemiBold, color = Color.Red)
        Text(text = padisah.savaslari, textAlign = TextAlign.Center, color = Color.Black, modifier = Modifier.padding(all = 5.dp))

    }
}
