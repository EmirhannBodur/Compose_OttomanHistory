package com.emirhan.ottomanhistory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.emirhan.ottomanhistory.model.PadisahData
import com.google.gson.Gson


@Composable
fun PadisahScreen(padisahListesi: List<PadisahData>,navController: NavController){
    LazyColumn(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
        items(padisahListesi){
                RowScreen(padisah = it, navController = navController)
        }
    }
}

@Composable
fun RowScreen(padisah: PadisahData,navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Gray)
        .border(border = BorderStroke(width = 3.dp, color = Color.LightGray), shape = RectangleShape)
        .padding(all = 5.dp)
        .clickable{
            navController.navigate("padisahDetail/${Gson().toJson(padisah)}")
        },
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(bitmap = ImageBitmap.imageResource(padisah.gorsel),
            contentDescription = padisah.isim,
            modifier = Modifier.size(width = 90.dp, height = 120.dp).padding(horizontal = 5.dp))
        Text(text = padisah.isim,
            fontSize = 35.sp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 5.dp)
            )
        Text(text = padisah.lakap.toString(),
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 20.sp,
            color = Color.Black)
        Row {
            Text(text = "Doğum Tarihi:${padisah.dogum}", modifier = Modifier.padding(horizontal = 5.dp), color = Color.Black)
            Text(text = "Ölüm Tarihi:${padisah.olum}", modifier = Modifier.padding(horizontal = 5.dp), color = Color.Black)
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun PadisahPreview(){
    OttomanHistoryTheme {
        val padisahList= ArrayList<PadisahData>()
        val osman= PadisahData(id = 1,
            isim = "1.Osman",
            dogum = 1254,
            olum = 1324,
            "Koyunhisar Muharebesi,Ermenibeli Muharebesi,Domaniç Muharebesi",
            "Orhan Bey,Alaeddin Bey,Fatma Hatun,Çoban Bey,Hamid Bey,Pazarlı Bey,Melik Bey",
            gorsel = R.drawable.osman_gazi,
            "",
            lakap = "Gazi,Bey",
            saltanatSuresi = 27,
            donem = "")
        val orhan= PadisahData(id = 2,
            isim = "Orhan",
            dogum = 1281,
            olum = 1362,
            savaslari = "Pelekanon Muharebesi ,Bursa Kuşatması ,İznik Kuşatması ,İzmit Kuşatması,Çimpe Kalesi'nin Fethi",
            cocuklari = "Süleyman Paşa,1.Murat Hüdavendigar,Şehzade İbrahim,Şehzade Halil,Şehzade Eyüp,Fatma Hatun,Hatice Hatun",
            gorsel = R.drawable.orhan_gazi,
            "",
            lakap = "Gazi,Bey",
            saltanatSuresi = 33,
            donem = "")
        val muratHudavendigar= PadisahData(id = 3,
            isim = "1.Murad",
            dogum = 1326,
            olum = 1389,
            savaslari = "Sazlıdere Muharebesi,Sırpsındığı Muharebesi,Çirmen Muharebesi,Frenkyazısı Muharebesi,I. Kosova Muharebesi",
            cocuklari = "Yıldırım Bayezid,Yakub Çelebi,Savcı Bey,Nefise Hatun,Sultan Hatun",
            gorsel = R.drawable.murat_hudavendigar,
            biyografi = "",
            lakap = "Hüdavendigar,Gazi",
            saltanatSuresi = 30,
            donem = "")
        val birinciBeyazid= PadisahData(id = 4,
            isim = "1.Beyazid",
            dogum = 1354,
            olum = 1403,
            cocuklari = "Ertuğrul Çelebi,Süleyman Çelebi,İsa Çelebi,Mustafa Çelebi,Musa Çelebi,1.Mehmed,Yusuf Çelebi,Kasım Çelebi,Hasan Çelebi,Ömer Çelebi,Korkud Çelebi,İbrahim Çelebi,Hundi Fatma Hatun,Erhondu Hatun,Öruz Hatun,Paşa Melek Hatun,Fatma Hatun,",
            savaslari = "Niğbolu Muharebesi,Ankara Savaşı,Akçay Ovası Savaşı,Kırkdilim Muharebesi,İstanbul Kuşatmaları (Birden fazla kez)",
            gorsel = R.drawable.birinci_beyazid,
            biyografi = "",
            lakap = "Yıldırım,Sultan-ı İklim-i Rum",
            saltanatSuresi = 13,
            donem = "")
        padisahList.add(osman)
        padisahList.add(orhan)
        padisahList.add(muratHudavendigar)
        padisahList.add(birinciBeyazid)
        PadisahScreen(padisahList)
    }
}

 */