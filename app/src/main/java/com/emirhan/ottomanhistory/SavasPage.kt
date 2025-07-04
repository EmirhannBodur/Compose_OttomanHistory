package com.emirhan.ottomanhistory


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.emirhan.ottomanhistory.model.SavaslarData
import com.emirhan.ottomanhistory.ui.theme.OttomanHistoryTheme
import kotlinx.coroutines.launch

@Composable
fun SavasScreen(savasListesi: List<SavaslarData>,navController: NavController){
    Column(modifier = Modifier.fillMaxSize().background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var name by remember { mutableStateOf("") }


        // 'savasListesi'ni 'name' değişkenindeki metni içeren savaşlara göre filtreler.
        // Büyük/küçük harf duyarsız arama yapar.
        val filtrededList=savasListesi.filter {
            it.savas.contains(name,ignoreCase = true)
        }
        // Filtrelenmiş listedeki savaş isimlerinin yalnızca benzersiz olanlarını alır.
        val uniqueFilteredList=filtrededList.distinctBy {
            it.savas
        }
        val coroutineScope= rememberCoroutineScope()
        val listState= rememberLazyListState()

        OutlinedTextField(value = name,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedPlaceholderColor = Color.DarkGray,
                focusedPlaceholderColor = Color.Black,
                focusedLeadingIconColor = Color.Black,
                unfocusedLeadingIconColor = Color.DarkGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.LightGray
            ),
            onValueChange = {name=it},
            placeholder = {Text("Ara...", color = Color.Black)},
            leadingIcon ={Icon(imageVector = Icons.Default.Search, contentDescription = "")},
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth())
        if (name.isNotEmpty()){
            if (uniqueFilteredList.isNotEmpty()){
                Column(modifier = Modifier.fillMaxWidth()) {
                    uniqueFilteredList.forEach { item ->
                        Text(
                            text = item.savas,
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                                .clickable{
                                    val index =savasListesi.indexOfFirst { it.id==item.id }
                                    if (index!=-1){
                                        coroutineScope.launch {
                                            listState.animateScrollToItem(index)
                                        }
                                    }
                                }
                        )
                    }
                }
            }else if(name.isNotEmpty()&&filtrededList.isNotEmpty()){
                Text(
                    text = "Sonuç yok",
                    color = Color.Black,
                    modifier = Modifier.padding(all = 12.dp)
                )
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
            itemsIndexed(savasListesi){ index,item ->
                SavasRow(savaslar = item)
            }
        }
    }
}
@Stable
val lightBlue = Color(0xFF00B8D4)


@Composable
fun SavasRow(savaslar: SavaslarData){
 Column(modifier = Modifier.fillMaxWidth().padding(all = 5.dp).clickable{
 }
     .background(color = lightBlue),
     horizontalAlignment = Alignment.CenterHorizontally) {
     Text(
         text = savaslar.savas,
         fontWeight = FontWeight.SemiBold,
         fontFamily = FontFamily.Serif,
         fontSize = 20.sp,
         textAlign = TextAlign.Center,
         color = Color.Black,
         style = MaterialTheme.typography.titleMedium
     )
     Text(
         text = savaslar.tarih,
         textAlign = TextAlign.Center,
         fontStyle = FontStyle.Italic,
         fontWeight = FontWeight.Light,
         style = MaterialTheme.typography.bodyLarge,
         textDecoration = TextDecoration.Underline,
         color = Color.Black,
         modifier = Modifier
             .padding(all = 5.dp)
     )
     Row(modifier = Modifier.fillMaxWidth()) {
         Image(bitmap = ImageBitmap.imageResource(savaslar.bayrak1), contentDescription = savaslar.devlet1, modifier = Modifier.size(150.dp))
         Spacer(modifier = Modifier.padding(horizontal = 40.dp))
         Image(bitmap = ImageBitmap.imageResource(savaslar.bayrak2), contentDescription = savaslar.devlet2, modifier = Modifier.size(150.dp))
     }
     Row(modifier = Modifier.fillMaxWidth()) {
         Text(
             text = savaslar.devlet1,
             fontWeight = FontWeight.SemiBold,
             fontFamily = FontFamily.Monospace,
             color = Color.Black,
             modifier = Modifier
                 .padding(all = 5.dp),
             textAlign = TextAlign.Center
         )
         Spacer(modifier = Modifier.padding(horizontal = 35.dp))
         Text(
             text = savaslar.devlet2,
             fontWeight = FontWeight.SemiBold,
             fontFamily = FontFamily.Monospace,
             color = Color.Black,
             modifier = Modifier
                 .padding(all = 5.dp),
             textAlign = TextAlign.Center
         )
     }
     Spacer(modifier = Modifier.padding(all = 10.dp).fillMaxWidth().background(Color.Blue))
     Text(
         text = savaslar.sonuc,
         textDecoration = TextDecoration.Underline,
         fontWeight = FontWeight.Bold,
         color = Color.Red,
         modifier = Modifier
             .padding(all = 5.dp)
     )
 }
}
/*@Preview(showBackground = true)
@Composable
fun SavasPreview(){
    OttomanHistoryTheme {
        val savasList= ArrayList<SavaslarData>()
        val koyunHisarSavasi= SavaslarData(id = 1, doneminPadisahi = "1.Osman Bey", savas = "KoyunHisar Muharebesi", devlet2 = "Bizans İmparatorluğu", dusmanKomutan = "George Mouzalon", tarih = "1302", antlasma = "", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, dostGucleri = 5000, dusmanGucleri = 2000, devlet1 = "Osmanlı Beyliği",  sonuc = "Kesin Osmanlı Zaferi")
        val dimbosSavasi= SavaslarData(id = 2, doneminPadisahi = "1.Osman", savas = "Dimbos Savaşı", devlet2 = "Bizans İmparatorluğu", dusmanKomutan = "Local Governors", tarih = "1303", antlasma = "", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, dostGucleri = 3000, dusmanGucleri = 10000, devlet1 = "Osmanlı Beyliği", sonuc = "Kesin Osmanlı Zaferi")
        val sakaryaNehriFetihleri= SavaslarData(id = 3, doneminPadisahi = "1.Osman", savas = "Lefke,Akhisar,Geyve,Gölpazarı Savaşları", devlet2 = "Bizans İmparatorluğu", dusmanKomutan = "Çeşitli Tekfurlar", tarih = "1313,1315", antlasma = "", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, dostGucleri = 3000, dusmanGucleri = 5000, devlet1 = "Osmanlı Beyliği", sonuc = "Kesin Osmanlı Zaferi")
        savasList.add(koyunHisarSavasi)
        savasList.add(dimbosSavasi)
        savasList.add(sakaryaNehriFetihleri)
        SavasScreen(savasList)
    }
}

 */

