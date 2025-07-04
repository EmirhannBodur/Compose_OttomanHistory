package com.emirhan.ottomanhistory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emirhan.ottomanhistory.model.PadisahData
import com.emirhan.ottomanhistory.model.SavaslarData
import com.emirhan.ottomanhistory.ui.theme.OttomanHistoryTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private val padisahList= ArrayList<PadisahData>()
    private val savasList= ArrayList<SavaslarData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController= rememberNavController()
            OttomanHistoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = "menu_page"){
                            composable("menu_page") { MainScreenMenu(navController) }
                            composable("savas_page") {
                                savasVerisi()
                                SavasScreen(savasList, navController = navController)
                            }
                            composable("padisah_page") {
                                padisahVerisi()
                                PadisahScreen(padisahListesi = padisahList, navController = navController)
                            }
                            composable("padisahDetail/{secilenPadisah}", arguments = listOf(
                                navArgument("secilenPadisah"){
                                    type= NavType.StringType
                                }
                            )
                            ) {
                                val padisahString= remember{
                                    it.arguments?.getString("secilenPadisah")
                                }
                                val secilenPadisah= Gson().fromJson(padisahString, PadisahData::class.java)
                                PadisahDetail(padisah = secilenPadisah)
                            }
                        }
                    }
                }
            }
        }
    }
    private fun padisahVerisi(){
        val osman= PadisahData(id = 1,
            isim = "1.Osman",
            dogum = 1254,
            olum = 1324,
            "Koyunhisar Muharebesi,Ermenibeli Muharebesi,Domaniç Muharebesi",
            "Orhan Bey,Alaeddin Bey,Fatma Hatun,Çoban Bey,Hamid Bey,Pazarlı Bey,Melik Bey",
            gorsel = R.drawable.osman_gazi,
            "Osman Gazi, 1258 yılında bugün Bilecik sınırları içinde yer alan Söğüt’te dünyaya gelmiştir. Kayı boyunun lideri olan Ertuğrul Gazi'nin oğludur ve annesi ise Türk tarihinde \"Hayme Ana\" olarak bilinen, dirayetli ve saygıdeğer bir kadındır. Osman Gazi, küçük yaşlardan itibaren savaş meydanlarında babasının yanında yer alarak tecrübe kazanmış, cesareti, liderlik vasfı ve halkına olan bağlılığı ile kısa sürede çevresindeki Türkmen boylarının güvenini kazanmıştır. O dönemde Anadolu’da Moğol baskısı ve Anadolu Selçuklu Devleti’nin zayıflığı nedeniyle siyasi otorite büyük ölçüde sarsılmıştı. Bu boşluk, özellikle uç beyliği konumundaki bölgelerde bağımsız hareket eden liderlerin önünü açmıştı. Bu ortamda Osman Gazi, babasının ölümünden sonra 1281 yılında Kayı boyunun başına geçmiş, ardından 1299 yılında Bizans’a karşı giriştiği başarılı mücadelelerle Osmanlı Beyliği’ni kurarak tarih sahnesine çıkmıştır.\n" +
                    "\n" +
                    "Osman Gazi, Anadolu’daki diğer Türk beyliklerinden farklı olarak, doğuya ya da güneye yönelmek yerine, Bizans topraklarına karşı batı yönlü bir fetih siyaseti izlemiştir. Bu doğrultuda ilk olarak Karacahisar’ı ele geçirmiş, ardından Bilecik, İnegöl, Kulaca Hisar, Yenişehir ve çevresindeki Bizans kalelerini birer birer Osmanlı topraklarına katmıştır. 1302 yılında Bizans’a karşı yapılan Koyunhisar Savaşı, Osmanlı'nın kazandığı ilk büyük meydan savaşı olmuş, bu zafer Osman Gazi’nin adını Anadolu ve Bizans topraklarında duyurmuştur. Osman Gazi'nin fethettiği Yenişehir, beyliğin merkezi haline getirilmiş ve burada ilk Osmanlı divanı kurulmuştur. Bu gelişmeler, Osmanlı Beyliği’nin artık sadece bir aşiret devleti olmadığını, kurumsallaşmaya ve büyümeye başlayan bir devlet haline geldiğini göstermektedir.\n" +
                    "\n" +
                    "Osman Gazi, savaşçı kimliğinin yanı sıra dini değerlere bağlı, adaletli ve ileri görüşlü bir lider olarak tanınır. Ahilik teşkilatıyla yakın ilişkiler kurmuş, toplumda birlik ve düzeni sağlamak için dini ve ahlaki ilkeleri ön planda tutmuştur. Halkına merhametli, düşmanına karşı ise kararlı bir tutum sergilemiştir. Onun döneminde Osmanlı Devleti henüz küçük bir beylik olsa da, attığı temeller; güçlü bir merkez, düzenli bir ordu ve halkla bütünleşmiş bir yönetim anlayışı üzerine inşa edilmiştir. Bu anlayış, ondan sonra gelen Osmanlı padişahlarının da temel yol göstericisi olmuştur.\n" +
                    "\n" +
                    "Hayatının ilerleyen dönemlerinde yaşlılık ve hastalık sebebiyle yönetimi yavaş yavaş oğlu Orhan Bey’e devretmiş, ancak devlet işlerinden tamamen elini çekmemiştir. Bursa, onun ömrü boyunca fethetmek istediği en önemli şehirlerden biri olmuş, uzun süren kuşatmanın ardından şehrin alınmasına çok az bir zaman kala, 1326 yılında vefat etmiştir. Ölümünden sonra vasiyeti gereği Bursa’ya gömülmüştür. Bugün Tophane’de bulunan türbesi, hem tarihî bir miras hem de Osmanlı’nın doğuşunun sembolüdür.\n" +
                    "\n" +
                    "Osman Gazi’nin eşleri arasında en çok bilinenleri Rabia Bala Hatun ve Malhun Hatun’dur. Bu evliliklerden Orhan Gazi, Alaeddin Bey, Pazarlu Bey, Melik Bey ve Fatma Hatun gibi çocukları dünyaya gelmiştir. Orhan Gazi, babasının ardından tahta geçmiş ve Osmanlı’yı küçük bir beyden büyük bir devlete dönüştürme sürecini devam ettirmiştir. Osman Gazi, yalnızca bir devlet kurucusu değil, aynı zamanda bir ideolojinin, bir yönetim sisteminin ve bir medeniyetin de kurucusu olarak tarih sahnesindeki yerini almıştır. Kurduğu Osmanlı Devleti, yüzyıllar boyunca üç kıtada hüküm sürecek, hem İslam dünyasının hem de dünya tarihinin en büyük imparatorluklarından biri olacaktır. Onun attığı sağlam temeller, adalet anlayışı ve halkıyla kurduğu güçlü bağ, Osmanlı’nın asırlarca ayakta kalmasının en önemli nedenlerinden biri olmuştur.",
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
            "Orhan Gazi, 1281 yılında Söğüt’te dünyaya gelmiş ve 1362 yılında Bursa’da vefat etmiştir. Osmanlı Devleti’nin ikinci padişahı olan Orhan Gazi, kurucu Osman Gazi’nin oğludur. Annesi konusunda bazı kaynaklarda Malhun Hatun, bazılarında ise Rabia Bala Hatun adı geçmektedir. Orhan Gazi, küçük yaşlardan itibaren babasının yanında savaşlara katılmış, askerî tecrübelerini bu şekilde kazanmıştır. Babasının fetih politikasını devam ettirmiş, devletin sınırlarını hem toprak olarak genişletmiş hem de kurumsal olarak önemli bir seviyeye taşımıştır. Osman Gazi’nin sağlığında onunla birlikte birçok sefere çıkan Orhan Bey, özellikle Bursa kuşatmasında aktif görev almış, şehrin fethinden hemen önce babasının vefatıyla tahta geçmiştir.\n" +
                    "\n" +
                    "1326 yılında Osman Gazi'nin ölümüyle birlikte Osmanlı tahtına çıkan Orhan Gazi, ilk olarak Bursa’yı fethederek babasının vasiyetini yerine getirmiştir. Bursa'nın fethi, Osmanlı Devleti açısından dönüm noktası olmuş, şehir kısa süre sonra başkent ilan edilmiştir. Bu fetihle birlikte Osmanlı Devleti, uç beyliği karakterinden sıyrılarak daha düzenli bir devlet yapısına geçiş sürecine girmiştir. Orhan Gazi, sadece bir asker değil, aynı zamanda büyük bir teşkilatçıdır. Onun döneminde ilk defa Osmanlı ordusu düzenli bir yapıya kavuşturulmuş ve Yaya ve Müsellem adı verilen ilk daimi ordu teşkil edilmiştir. Bu gelişme, daha sonra kurulacak olan Yeniçeri Ocağı’nın da temelini oluşturmuştur.\n" +
                    "\n" +
                    "Orhan Gazi döneminde Osmanlı Devleti, sadece Bizans’a karşı değil, diğer Anadolu beylikleri ve batıdaki Latin topluluklarına karşı da etkin mücadelelerde bulunmuştur. 1331 yılında İznik, 1337’de İzmit, 1354’te Çimpe Kalesi fethedilmiştir. Özellikle Çimpe Kalesi’nin alınmasıyla birlikte Osmanlılar ilk kez Rumeli’ye (Balkanlara) ayak basmış ve Avrupa’daki genişleme politikaları başlamıştır. Bu, ileriki yüzyıllarda Osmanlı’nın bir dünya gücüne dönüşmesini sağlayacak önemli bir adımdır. Orhan Gazi, savaşlarda gösterdiği başarı kadar, halkla kurduğu ilişkiler, adaletli yönetimi ve halkın ihtiyaçlarına gösterdiği duyarlılıkla da tanınmıştır. Halkına karşı mütevazı bir padişah olan Orhan Gazi, zaman zaman sade kıyafetlerle halkın arasına karışmış, onların derdini bizzat dinlemiştir.\n" +
                    "\n" +
                    "Orhan Gazi döneminde sadece fetihler değil, aynı zamanda büyük kurumsal reformlar da gerçekleştirilmiştir. İlk Osmanlı medresesi İznik’te açılmış, böylece dinî ve bilimsel eğitim kurumsallaşmıştır. Kadı ve subaşı gibi idari görevler netleşmiş, devletin adalet ve güvenlik sistemi kurulmaya başlanmıştır. Parasal sistemde de önemli bir adım atılmış, Orhan Gazi döneminde Osmanlı’nın ilk resmi gümüş parası (akçe) bastırılmıştır. Bu, ekonomik bağımsızlığın ve gelişimin en somut göstergelerinden biridir.\n" +
                    "\n" +
                    "Orhan Gazi'nin eşleri arasında en çok bilinenleri Nilüfer Hatun, Asporça Hatun ve Theodora’dır. Nilüfer Hatun, Bizanslı Yarhisar Tekfuru’nun kızı olup, İslam’a geçmiş ve Orhan Gazi ile evlenmiştir. Bu evlilikten Osmanlı'nın üçüncü padişahı olacak olan I. Murad (Hüdavendigar) dünyaya gelmiştir. Diğer çocukları arasında Şehzade Süleyman Paşa, İbrahim Bey, Kasım Bey ve Fatma Hatun gibi isimler bulunmaktadır. Özellikle Şehzade Süleyman Paşa, Rumeli’ye geçiş sürecinde büyük rol oynamış, ancak genç yaşta bir kazada vefat etmiştir.\n" +
                    "\n" +
                    "Orhan Gazi, 1362 yılında yaşlılık ve hastalık nedeniyle vefat etmiş ve Bursa’da, babası Osman Gazi'nin yakınında bulunan Gümüşlü Kümbet’e defnedilmiştir. Ölümünden sonra tahta oğlu I. Murad geçmiş ve Osmanlı Devleti’nin merkeziyetçi yapıya geçişi hız kazanmıştır. Orhan Gazi, Osmanlı tarihinin yalnızca ikinci padişahı olsa da, devletin kurumsallaşmasında, ilk düzenli ordunun kurulmasında, Rumeli’ye geçişin sağlanmasında ve adaletli bir yönetim anlayışının yerleşmesinde oynadığı rol nedeniyle, sadece Osmanlı tarihinde değil, Türk ve İslam dünyasında da önemli bir yere sahiptir. Mütevazı kişiliği, halkına yakın duruşu, vizyoner devlet anlayışı ve köklü reformlarıyla Osman Gazi’nin kurduğu beyliği, imparatorluk yolunda kararlı bir şekilde ilerleten padişah olarak tarihe geçmiştir.",
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
            biyografi = "I. Murad, 1326 yılında Bursa’da doğmuş ve 1389 yılında Kosova’da şehit olmuştur. Osmanlı Devleti’nin üçüncü padişahı olan I. Murad, babası Orhan Gazi’nin vefatının ardından 1362 yılında tahta geçmiştir. Annesi Bizans kökenli Nilüfer Hatun’dur. Gençlik yıllarında iyi bir eğitim alan I. Murad, küçük yaşlardan itibaren hem askerî hem idarî konularda yetişmiş, devlet yönetiminde babasına yardımcı olmuştur. Babasının hükümdarlığı döneminde özellikle Rumeli’ye geçişte önemli görevler üstlenmiş, Şehzade Süleyman Paşa’nın vefatından sonra tahtın en güçlü varisi haline gelmiştir. Orhan Gazi’nin ölümünden sonra devlet erkânının da desteğiyle tahta çıkmış ve Osmanlı Devleti’ni kurumsal anlamda gerçek bir imparatorluğa dönüştüren ilk padişah olmuştur.\n" +
                    "\n" +
                    "I. Murad, tahta çıktığında Osmanlı hâlâ genç bir devletti; ancak o, hem Anadolu’da hem de Rumeli’de yaptığı başarılı fetihlerle ve oluşturduğu güçlü devlet yapısıyla Osmanlı’yı büyük bir bölgesel güç haline getirmiştir. Onun döneminde Osmanlı Devleti ilk kez Balkanlar'da büyük bir güç olarak tanınmıştır. 1362 yılında tahta çıktıktan kısa süre sonra Edirne’yi fethederek büyük bir başarıya imza atmış ve bu şehri Osmanlı’nın yeni başkenti yapmıştır. Edirne’nin fethi, hem Bizans hem de Balkan devletleri üzerinde büyük bir etki yaratmış, Osmanlı'nın Avrupa’daki varlığını kalıcı hale getirmiştir. Ardından Filibe, Gümülcine, Serez ve Manastır gibi önemli şehirler Osmanlı topraklarına katılmıştır.\n" +
                    "\n" +
                    "I. Murad, savaş meydanlarındaki başarısının yanı sıra devlet teşkilatında da köklü reformlar yapmıştır. Onun döneminde Osmanlı Devleti’nin askerî yapısı tamamen yeniden düzenlenmiş ve Kapıkulu Ocağı adı verilen, devlete bağlı ilk profesyonel ordu kurulmuştur. Bu ordunun belkemiğini oluşturacak olan Yeniçeri Ocağı, onun döneminde tesis edilmiştir. Ayrıca devşirme sistemi de ilk kez I. Murad zamanında uygulanmaya başlanmıştır. Bu sistem sayesinde gayrimüslim ailelerin çocukları küçük yaşta alınıp Müslüman olarak yetiştirilmiş ve devlet hizmetine alınmıştır. Bu uygulama, ilerleyen dönemlerde Osmanlı’nın güçlü bir bürokrasi ve askerî yapı kurmasını sağlayan temel etkenlerden biri olmuştur.\n" +
                    "\n" +
                    "Sadece askerî ve idarî reformlarla yetinmeyen I. Murad, aynı zamanda adaletin tesisi için kadılık ve müftülük sistemini de güçlendirmiştir. Osmanlı’nın farklı bölgelerinde kadılar tayin edilmiş, halkla devlet arasındaki hukuki ilişki daha düzenli hale getirilmiştir. Onun döneminde vakıf müesseseleri güçlenmiş, camiler, medreseler, kervansaraylar ve hamamlar inşa edilerek sosyal devlet anlayışı kuvvetlendirilmiştir.\n" +
                    "\n" +
                    "I. Murad döneminin en önemli olaylarından biri de 1389 yılında yapılan Birinci Kosova Savaşıdır. Osmanlı ile Sırp, Bosnalı, Arnavut ve Macar kuvvetlerinin oluşturduğu Haçlı ittifakı arasında geçen bu büyük meydan savaşında Osmanlı ordusu kesin bir zafer kazanmış, Balkanlar’daki hâkimiyetini perçinlemiştir. Ancak bu zaferin hemen ardından savaş meydanını dolaşan I. Murad, bir Sırp askeri olan Miloş Obiliç tarafından hançerlenerek şehit edilmiştir. Böylece I. Murad, savaş meydanında şehit edilen ilk Osmanlı padişahı olarak tarihe geçmiştir.\n" +
                    "\n" +
                    "I. Murad, halk arasında Hüdavendigar yani “hükümdarların efendisi” unvanıyla anılmıştır. Bu unvan, onun sadece askerî başarısından değil, aynı zamanda adaletli yönetim anlayışından, halkına duyduğu saygıdan ve derin dini hassasiyetlerinden kaynaklanmaktadır. Onun döneminde Osmanlı Devleti bir uç beyliğinden bölgesel bir güce dönüşmüş, Balkanlar’da ve Anadolu’da kalıcı hâkimiyet kurulmuştur. Aynı zamanda Osmanlı saray ve idare sisteminin temelleri onun zamanında atılmıştır.\n" +
                    "\n" +
                    "I. Murad’ın eşleri arasında Gülçiçek Hatun en bilinenidir. Bu evlilikten ileride Osmanlı tahtına geçecek olan Yıldırım Bayezid (Bayezid I) dünyaya gelmiştir. Diğer çocukları arasında Yakup Çelebi, Savcı Bey ve İbrahim Bey gibi isimler yer almaktadır. Yakup Çelebi, Kosova Savaşı sonrasında kardeşi Yıldırım Bayezid tarafından tahta geçişte birliği sağlamak adına öldürülmüştür. Savcı Bey ise daha önce bir isyan girişiminde bulunmuş ve bunun bedelini canıyla ödemiştir.\n" +
                    "\n" +
                    "I. Murad’ın naaşı Kosova’dan getirilerek Bursa’daki kendi yaptırdığı Hüdavendigar Türbesine defnedilmiştir. Aynı zamanda şehit edildiği yere, yani Kosova Ovası’na da sembolik olarak bir şehitlik makamı yapılmıştır. I. Murad, hem fetihleri hem de kurduğu askerî-idarî sistemlerle Osmanlı Devleti’nin büyüme devrinin mimarlarından biri olmuş, ardından gelen padişahlar için sağlam bir zemin hazırlamıştır. Onun attığı adımlar, Osmanlı’nın yalnızca bir Anadolu devleti değil, aynı zamanda Avrupa ve Balkanlar’da da etkili bir imparatorluk haline gelmesini sağlayan temel yapı taşları olmuştur.",
            lakap = "Hüdavendigar,Gazi",
            saltanatSuresi = 30,
            donem = "")
        val birinciBeyazid= PadisahData(id = 4,
            isim = "1.Beyazid",
            dogum = 1354,
            olum = 1403,
            cocuklari = "Ertuğrul Çelebi,Süleyman Çelebi,İsa Çelebi,Mustafa Çelebi,Musa Çelebi,1.Mehmed,Yusuf Çelebi,Kasım Çelebi,Hasan Çelebi,Ömer Çelebi,Korkud Çelebi,İbrahim Çelebi,Hundi Fatma Hatun,Erhondu Hatun,Öruz Hatun,Paşa Melek Hatun,Fatma Hatun,",
            savaslari = "Niğbolu Muharebesi,Ankara Savaşı,Akçay Ovası Savaşı,Kırkdilim Muharebesi,İstanbul Kuşatmaları",
            gorsel = R.drawable.birinci_beyazid,
            biyografi = "I. Bayezid, lakabıyla Yıldırım Bayezid, 1364 yılında Edirne’de doğmuş ve 1403 yılında Akşehir’de vefat etmiştir. Osmanlı Devleti’nin dördüncü padişahı olan Yıldırım Bayezid, babası I. Murad’ın 1389 yılında Kosova Meydan Muharebesi’nde şehit edilmesinin hemen ardından savaş meydanında Osmanlı tahtına geçirilmiştir. Annesi Gülçiçek Hatun’dur. Küçük yaşlardan itibaren devlet yönetimi ve askerî alanda yetiştirilen Bayezid, sert mizacı, cesareti ve hızlı hareket kabiliyetiyle tanınmış, bu özellikleri dolayısıyla \"Yıldırım\" unvanını almıştır. Askerî dehası, stratejik kararlılığı ve genişleme politikaları ile Osmanlı Devleti’ni kısa sürede bir Balkan ve Anadolu süper gücü haline getirmiştir.\n" +
                    "\n" +
                    "Tahta geçtiği anda, babasının ölümünden sonra oluşabilecek otorite boşluğunu engellemek amacıyla Kosova Meydanı’nda kardeşi Yakup Çelebi’yi boğdurtarak tahta tek başına sahip olmuştur. Bu olay, Osmanlı tarihinde ilk kardeş katli olarak kayda geçmiştir. Yıldırım Bayezid’in hükümdarlık dönemi, hem içerideki isyanlar hem de dış tehditlerle yoğun geçen bir dönem olmuş, buna rağmen Osmanlı sınırları büyük ölçüde genişlemiştir.\n" +
                    "\n" +
                    "Yıldırım Bayezid’in ilk icraatlarından biri, Anadolu’daki Türk beyliklerini Osmanlı otoritesi altına almaya yönelik çalışmaları olmuştur. Germiyanoğulları, Karasioğulları, Hamitoğulları ve Aydınoğulları gibi beylikler ya savaşla ya da evlilik yoluyla Osmanlı topraklarına katılmıştır. Bu süreçte Anadolu’daki Türk birliğini sağlama yönünde en ciddi adımları atan padişah olmuştur. Bu sayede Anadolu’da siyasi birlik büyük ölçüde sağlanmış, Osmanlı Devleti Anadolu’nun en güçlü siyasi gücü haline gelmiştir. Ayrıca, Anadolu Hisarı’nı inşa ettirerek İstanbul’un fethi için stratejik bir altyapı oluşturmuştur.\n" +
                    "\n" +
                    "Batı cephesinde ise özellikle Haçlılara karşı büyük zaferler kazanmıştır. En dikkat çekici zaferi, 1396 yılında Macaristan Krallığı öncülüğünde oluşturulan Haçlı ordusunu Niğbolu Savaşı’nda bozguna uğratmasıdır. Bu zafer, Osmanlı’nın Avrupa’daki varlığını kesinleştirmiş, Yıldırım Bayezid'in adını Batı’da da duyurmuştur. Bu başarıdan sonra Halife tarafından “Sultan-ı İklim-i Rûm” yani \"Anadolu'nun Sultanı\" unvanı verilmiştir. Bu, Osmanlı padişahlarına halifelik makamından verilen ilk büyük siyasî unvandır.\n" +
                    "\n" +
                    "Yıldırım Bayezid’in bir diğer önemli icraatı ise Osmanlı’da ilk kez merkezi otoriteyi güçlendirme ve mutlak sultanlık anlayışını yerleştirme çabasıdır. Devlet teşkilatını merkezileştirmiş, beylerin etkisini azaltmış ve saray teşkilatını güçlendirmiştir. Bu çerçevede Divan teşkilatında düzenlemeler yapmış, tımar sistemini geliştirerek devlete bağlı sipahi ordusunun sayısını artırmıştır. Bayındırlık alanında da aktif olan Yıldırım, Bursa, Edirne ve diğer şehirlerde camiler, medreseler, imarethaneler ve hanlar yaptırmış; özellikle Bursa’da yaptırdığı Ulu Camii dönemin mimari şaheserlerinden biri olmuştur.\n" +
                    "\n" +
                    "Ancak Yıldırım Bayezid’in hükümdarlığının son yılları, Osmanlı için büyük bir kırılma dönemine sahne olmuştur. O sırada Orta Asya’dan Anadolu’ya doğru ilerleyen büyük Türk-Moğol hükümdarı Timur (Tamerlane) ile olan siyasi rekabet, iki büyük gücün çatışmasına neden olmuştur. Yıldırım Bayezid, Timur’un Anadolu beyliklerine müdahalesine karşı çıkmış, bu da iki hükümdarın 1402 Ankara Savaşı’nda karşı karşıya gelmesine yol açmıştır. Ankara yakınlarında yapılan bu savaşta Osmanlı ordusu bozguna uğramış ve Yıldırım Bayezid, savaş meydanında Timur’a esir düşmüştür. Bu, Osmanlı tarihinde ilk kez bir padişahın düşman eline geçmesidir ve devletin kurumsal düzeni ciddi bir darbe almıştır. Yıldırım Bayezid yaklaşık bir yıl esaret altında kalmış, 1403 yılında Akşehir’de vefat etmiştir. Ölüm nedeni konusunda farklı rivayetler olsa da çoğu tarihçi, esaretin ve mağlubiyetin etkisiyle büyük bir ruhsal çöküş yaşadığı konusunda hemfikirdir.\n" +
                    "\n" +
                    "Vefatının ardından cenazesi oğlu Musa Çelebi tarafından Bursa’ya getirilerek Yıldırım Külliyesi içindeki türbeye defnedilmiştir. Yıldırım Bayezid’in eşleri arasında en bilinenleri Devlet Hatun ve Despina Hatun’dur. Çocukları arasında Osmanlı tahtı için mücadele eden ve Fetret Devri olarak bilinen karışıklık dönemine neden olan Çelebi Mehmed, Musa Çelebi, İsa Çelebi, Süleyman Çelebi gibi isimler yer alır. Bu dönem, Osmanlı’nın yaklaşık 11 yıl boyunca merkezi otorite olmadan, kardeşler arasında süren bir iç savaşla yönetilmesine neden olmuştur.\n" +
                    "\n" +
                    "Yıldırım Bayezid, her ne kadar sonu esaretle bitmiş olsa da, Osmanlı tarihinin en cesur ve savaşçı padişahlarından biri olarak kabul edilir. Onun döneminde devlet hem doğuda hem batıda büyük bir sıçrama yaşamış, İstanbul’un fethi için ilk ciddi hazırlıklar yapılmış ve Osmanlı kurumsal yapısı daha olgun bir hale gelmiştir. Sert mizacı, hızlı hareket eden yapısı ve stratejik zekâsı ile tarihe geçen Yıldırım Bayezid, hem başarıları hem de dramatik sonuyla Osmanlı tarihinin en dikkat çeken padişahlarından biri olmuştur.",
            lakap = "Yıldırım,Sultan-ı İklim-i Rum",
            saltanatSuresi = 13,
            donem = "")
        val isaCelebi= PadisahData(id = 5,
            isim = "İsa Çelebi",
            dogum = 1380,
            olum = 1406,
            cocuklari = "",
            savaslari = "Ankara Savaşı,Fetret Devri Savaşları,Ulubat Muharebesi,Bursa'yı Geri Alma Girişimleri,Beypazarı ve Ankara Muharebeleri",
            gorsel = R.drawable.isa_celebi,
            biyografi = "İsa Çelebi, yaklaşık 1380’li yıllarda doğmuş, 1406 yılında hayatını kaybetmiştir. Osmanlı padişahı Yıldırım Bayezid’in oğullarından biri olan İsa Çelebi, annesi Devlet Hatun ya da Müslüman olmuş bir Bizans prensesi olabilir; kaynaklarda bu konuda farklı görüşler mevcuttur. İsa Çelebi, küçük yaşlardan itibaren iyi bir eğitim almış ve devlet hizmetine hazırlanmıştır. Babasının sağlığında sancak beyliği görevinde bulunmuş, özellikle Balıkesir ve Bursa civarında görev yapmıştır. Askerî alanda da deneyim kazanmış ve devlet yönetimi konusunda donanımlı bir şehzade olarak yetişmiştir. Ancak onun hayatı, Osmanlı’nın en karışık dönemlerinden biri olan Fetret Devri ile birlikte siyasi bir mücadelenin içine sürüklenmiştir.\n" +
                    "\n" +
                    "İsa Çelebi’nin ön plana çıkışı, 1402 yılında Ankara Savaşı’nın ardından babası Yıldırım Bayezid’in Timur’a esir düşmesiyle olmuştur. Bu savaş, sadece bir askerî mağlubiyet değil, aynı zamanda Osmanlı Devleti’nin merkezi otoritesinin çökmesi anlamına gelmiştir. Timur, Osmanlı topraklarında kendi nüfuzunu artırmak için Yıldırım Bayezid’in hayatta kalan oğullarını Anadolu’ya farklı bölgelere atayarak aralarındaki taht mücadelesini teşvik etmiştir. Bu durum, yaklaşık 11 yıl sürecek olan Fetret Devrinin başlamasına yol açmıştır.\n" +
                    "\n" +
                    "İsa Çelebi, Ankara Savaşı sonrasında kardeşi Mehmed Çelebi ile mücadeleye girişmiş, özellikle Bursa ve Batı Anadolu’da hâkimiyet kurmak istemiştir. 1403 yılında Bursa’ya girerek burada bağımsızlığını ilan etmiş ve kendini Osmanlı tahtının varisi olarak görmüştür. Aynı dönemde Edirne'de Süleyman Çelebi, Amasya’da Mehmed Çelebi, Rumeli’de ise ilerleyen yıllarda Musa Çelebi ayrı ayrı bölgeleri yönetmeye başlamıştır. İsa Çelebi, kısa süre içinde Bursa’yı ve çevresini kontrol altına almış, ancak bu hâkimiyet kalıcı olmamıştır.\n" +
                    "\n" +
                    "1404-1405 yılları arasında Mehmed Çelebi ile İsa Çelebi arasında şiddetli bir iç savaş dönemi yaşanmıştır. Bu süreçte İsa Çelebi birkaç defa Mehmed Çelebi’ye karşı üstünlük sağlamaya çalışsa da, Mehmed’in askerî zekâsı, halk nezdindeki meşruiyeti ve Anadolu’daki beylerin desteği İsa’nın lehine olmamıştır. 1405 yılında Ulubat Meydan Muharebesi'nde Mehmed Çelebi’ye yenilen İsa Çelebi, kaçmak zorunda kalmış, ardından Balıkesir, Kütahya ve çevresinde tekrar destek toplamaya çalışmıştır. Ancak başarısız olmuş ve taraftarlarının çoğu dağılmıştır.\n" +
                    "\n" +
                    "Yenilgi sonrası kısa bir süre dağlarda, kırsal bölgelerde gizlenerek yaşamak zorunda kalan İsa Çelebi, 1406 yılında yakalanarak öldürülmüştür. Ölüm şekli konusunda kaynaklar farklılık gösterir; bazı kaynaklara göre boğdurulmuş, bazılarına göre bir hançerle öldürülmüştür. Ancak kesin olan, kardeşi Mehmed Çelebi’nin rakiplerini birer birer ortadan kaldırarak Osmanlı tahtına tek başına hâkim olmaya başlaması sürecinde İsa Çelebi’nin direnişinin son bulduğudur.\n" +
                    "\n" +
                    "İsa Çelebi’nin ölümü, Osmanlı’daki iç savaş sürecinin önemli dönüm noktalarından biri olmuştur. Onun tasfiyesiyle birlikte Anadolu’da Mehmed Çelebi’nin hâkimiyeti güçlenmiş, kalan diğer kardeşleri Süleyman ve Musa Çelebi ile mücadeleye odaklanılmıştır. İsa Çelebi geride tahtı kazanamamış bir şehzade olarak kalmış, ancak Fetret Devri'nin ilk yıllarında Bursa ve çevresindeki kısa süreli hükmüyle bu çalkantılı dönemin etkili figürlerinden biri olmuştur.\n" +
                    "\n" +
                    "Tarihçiler tarafından değerlendirildiğinde İsa Çelebi’nin kişiliği genellikle hırslı, kararlı ama stratejik açıdan zayıf olarak tanımlanır. Askerî gücü kadar diplomatik ve siyasi destekten yoksun olması, taht yarışında elini zayıflatmıştır. Ayrıca Mehmed Çelebi’nin halk nezdinde daha kabul görmesi ve Anadolu beylikleriyle kurduğu dengeli ilişkiler, İsa’nın ilerlemesini engellemiştir. İsa Çelebi, Osmanlı tarihinde kısa süreli bir iktidar mücadelesiyle hatırlanır; ancak onun mücadelesi, Fetret Devri'nin karanlık ve karmaşık atmosferinde önemli bir yer tutar. Onunla birlikte Osmanlı, merkeziyetçi yapının ne kadar kırılgan olduğunu görmüş, aynı zamanda bu tür krizlerden nasıl çıkılması gerektiğine dair ciddi tecrübeler edinmiştir.",
            lakap = "",
            saltanatSuresi = 0,
            donem = "Fetret Devri")
        val EmirSuleymanCelebi= PadisahData(id = 6,
            isim = "Emir Süleyman Çelebi",
            dogum = 1377,
            olum = 1411,
            cocuklari = "Orhan Çelebi,Mehmed Şah Çelebi",
            savaslari = "Tırnova Kuşatması,Niğbolu Savaşı,Ankara Savaşı,Fetret Devri Savaşları,İsa Çelebi ile Mücadeleleri, Musa Çelebi ile Mücadeleleri",
            gorsel = R.drawable.emir_suleyman_celebi,
            biyografi = "Süleyman Çelebi, 1377 yılı civarında doğmuş, 1411 yılında Edirne yakınlarında hayatını kaybetmiştir. Osmanlı Padişahı Yıldırım Bayezid’in oğullarından biri olan Süleyman Çelebi, annesi Rum veya Sırp kökenli bir hatun olan Despina Hatun’dur. Babasının sağlığında sancak beyliği görevlerinde bulunmuş ve özellikle Rumeli’de yetişmiştir. Sert mizaçlı, atılgan ve siyaset bilgisine sahip bir şehzade olarak tanınan Süleyman, Osmanlı tahtı üzerinde hak iddia eden kardeşleri gibi, babasının 1402 yılında Ankara Savaşı’nda Timur’a esir düşmesinin ardından başlayan ve 11 yıl sürecek olan Fetret Devri’nde önemli bir aktör haline gelmiştir. Bu dönem, Osmanlı Devleti’nin siyasi otoritesinin çöktüğü, kardeşler arasında iç savaşların yaşandığı son derece çalkantılı bir süreçtir.\n" +
                    "\n" +
                    "Ankara Savaşı’ndan sonra Timur’un sağladığı serbestlik ortamında her bir şehzade kendi bölgesinde egemenlik kurmaya çalışırken, Süleyman Çelebi Edirne’de merkezi ele geçirmiş ve Rumeli bölgesinde fiilen Osmanlı hükümdarı gibi hareket etmiştir. Edirne gibi stratejik bir şehri kontrol etmesi, ona Balkanlar’daki Osmanlı gücünü kullanma ve Avrupa devletleriyle doğrudan ilişki kurma avantajı sağlamıştır. Bu sayede Venedik, Macaristan ve Bizans gibi devletlerle diplomatik ilişkiler kurmuş; Rumeli halkı ve askerleri tarafından bir süre boyunca fiilî sultan olarak kabul edilmiştir.\n" +
                    "\n" +
                    "Süleyman Çelebi, kendisini resmen padişah ilan etmese de, Edirne'de bir nevi bağımsız hükümdarlık yürütmüştür. Paralar bastırmış, hutbe okutmuş, seferler düzenlemiş ve tıpkı bir padişah gibi davranmıştır. Balkanlar'da güvenliğini sağlamak ve Batı'dan gelecek tehditlere karşı avantaj kazanmak amacıyla Bizans İmparatoru II. Manuel Palaiologos ile yakın ilişkiler kurmuş, hatta ona bazı Anadolu topraklarını vermek pahasına barış sağlamıştır. Ancak bu durum, Anadolu’daki diğer Osmanlı unsurları tarafından bir zayıflık ve toprak kaybı olarak değerlendirilmiş, Süleyman’ın siyasi imajını zedelemiştir.\n" +
                    "\n" +
                    "Kardeşi Mehmed Çelebi, Amasya merkezli olarak Anadolu’da hâkimiyet kurmaya başladığında, Süleyman Çelebi ile kaçınılmaz olarak bir çatışma ortamı doğmuştur. 1405–1409 yılları arasında Mehmed Çelebi ile defalarca karşı karşıya gelen Süleyman, Rumeli’deki gücüne güvense de, Anadolu’daki desteği sınırlıydı. Ayrıca bir başka kardeşi olan Musa Çelebi, bir süre Mehmed Çelebi’nin tarafında yer aldıktan sonra Balkanlar’a geçerek Süleyman’a karşı ayaklanmış ve Balkan halklarının da desteğini almıştır. Musa Çelebi, 1410 yılında Vardar bölgesinde Süleyman Çelebi’ye ağır bir darbe vurmuş, onu Edirne’ye kadar geri çekilmeye zorlamıştır.\n" +
                    "\n" +
                    "Giderek güç kaybeden Süleyman Çelebi, siyasi ve askerî açıdan sıkışmış, kendi iç çevresindeki beylerin bile desteğini kaybetmeye başlamıştır. Sonunda 1411 yılında Edirne yakınlarında Musa Çelebi'nin adamları tarafından kıstırılmış ve öldürülmüştür. Bazı kaynaklara göre öldürülmeden önce teslim olmak istemişse de kabul edilmemiştir. Ölümüyle birlikte Rumeli’deki Osmanlı yönetimi Musa Çelebi’nin eline geçmiş, ancak kısa süre sonra Mehmed Çelebi tekrar duruma hâkim olmuş ve Osmanlı tahtında tek başına söz sahibi olmuştur. Bu gelişmeyle birlikte Fetret Devri’nin sona ermesine giden süreç hızlanmıştır.\n" +
                    "\n" +
                    "Süleyman Çelebi, Osmanlı tahtına resmen geçmemiş olsa da, kendi döneminde bir padişah gibi davranmış ve parasal, idari, diplomatik ve askerî birçok faaliyeti doğrudan yürütmüştür. Onun Edirne merkezli yönetimi, Osmanlı'nın Balkanlar'daki varlığını koruması açısından kritik öneme sahiptir. Ancak diğer kardeşlerine göre daha uzlaşmacı ve Batı yanlısı politikaları, özellikle Anadolu’daki ulemâ ve askerî çevrelerde ciddi tepki toplamıştır. Bu yönüyle tarihçiler tarafından hem devlet çıkarlarını Batı’ya fazla taviz vererek zedelediği, hem de stratejik olarak Musa Çelebi’ye karşı zayıf hamleler yaptığı eleştirileriyle anılır.\n" +
                    "\n" +
                    "Yine de Süleyman Çelebi, Osmanlı Devleti’nin en zor dönemlerinden biri olan Fetret Devri’nde hem iç karışıklıkları yönetmeye çalışmış, hem de devletin Avrupa’daki dengelerini koruyarak Osmanlı’yı Balkanlar’da tutmayı başarmıştır. Onun mücadele dolu ve trajik sonla biten hayatı, Osmanlı tarihinde taht mücadelesi içinde kaybolan ama etkili izler bırakan bir şehzadenin hikâyesidir. Ardında resmî bir türbe bırakmamakla birlikte, adı hem Rumeli siyasetinde hem de Osmanlı'nın kriz dönemleri tarihinde güçlü bir şekilde yer almıştır.",
            lakap = "Rumeli'nin ilk sultanı",
            donem = "Fetret Devri",
            saltanatSuresi = 0)
        val musaCelebi= PadisahData(id = 7,
            isim = "Musa Çelebi",
            dogum = 1388,
            olum = 1413,
            cocuklari = "",
            saltanatSuresi = 0,
            gorsel = R.drawable.musa_celebi,
            donem = "Fetret Devri",
            savaslari = "Ankara Savaşı,Fetret Devri Savaşları,Süleyman Çelebi ile mücadeleleri,Konstantinopolis Kuşatması,Mehmet Çelebi ile mücadeleleri",
            lakap = "Rumeli'nin ikinci sultanı",
            biyografi = "Musa Çelebi, yaklaşık 1388 yılında dünyaya gelmiş, 1413 yılında Sofya yakınlarında hayatını kaybetmiştir. Osmanlı padişahı Yıldırım Bayezid’in oğullarından biri olan Musa Çelebi, annesi olarak genellikle Bulgar ya da Sırp asıllı bir cariye gösterilir. Hayatının ilk yılları hakkında fazla bilgi bulunmasa da, Ankara Savaşı’na (1402) kadar babasının gözetiminde büyüyüp yetiştiği bilinmektedir. 1402 yılında Osmanlı ordusunun Timur karşısında ağır bir yenilgi aldığı Ankara Savaşı sonrası Yıldırım Bayezid’in esir düşmesiyle başlayan ve yaklaşık 11 yıl süren Fetret Devri sırasında Musa Çelebi, Osmanlı’nın dağılmış otoritesinde söz sahibi olmaya çalışan dört kardeşten biri olarak tarih sahnesine çıkmıştır.\n" +
                    "\n" +
                    "Ankara Savaşı’nın ardından Timur, Yıldırım Bayezid’in oğullarını kendi menfaatine göre yönlendirmiş ve Osmanlı topraklarında onları birbirine karşı konumlandırmıştır. Bu ortamda Musa Çelebi, ilk etapta Anadolu’da doğrudan güçlü bir bölge elde edememiştir. Ancak kardeşi Mehmed Çelebi, onu bir müttefik olarak değerlendirip Rumeli’ye göndermeye karar vermiştir. O dönemde Rumeli’de Edirne merkezli olarak hüküm süren ağabeyi Süleyman Çelebi, Balkanlar’da güçlü bir siyasi yapı kurmuş, ancak bu süreçte Bizans ve batılı güçlerle olan yakın ilişkileri nedeniyle Anadolu’daki birçok çevrenin tepkisini çekmiştir.\n" +
                    "\n" +
                    "1409 yılında Mehmed Çelebi, Musa’yı güçlü bir ordu ile Rumeli’ye geçirmiştir. Bu olay, Fetret Devri’ndeki dengeleri değiştirmiştir. Musa Çelebi, Edirne'yi kuşatıp ele geçirmiş ve Süleyman Çelebi’yi 1411 yılında öldürterek Rumeli’deki tüm Osmanlı varlığı üzerinde denetimi sağlamıştır. Bu gelişmeyle birlikte Musa Çelebi de tıpkı diğer kardeşleri gibi fiilî bir Osmanlı padişahı gibi davranmaya başlamış, hutbe okutmuş, para bastırmış ve kendi hükümdarlığını ilan etmiştir. Ancak resmî anlamda hiçbir zaman Osmanlı tahtına \"sultan\" unvanıyla oturmamıştır.\n" +
                    "\n" +
                    "Musa Çelebi’nin Rumeli’deki iktidarı, oldukça sert ve baskıcı bir yapıya sahipti. Önceki yöneticilere sadık olan beyleri görevden almış, bazılarını cezalandırmış, hatta öldürtmüştür. Özellikle Süleyman Çelebi döneminde Bizans ve diğer Balkan devletleriyle kurulan diplomatik ilişkileri tamamen kesmiş, bu da çevresindeki desteği hızla azaltmıştır. Katı tutumu, özellikle Bizans İmparatoru II. Manuel başta olmak üzere birçok Batılı devletin ona karşı cephe almasına neden olmuştur. Öyle ki, bu devletler Mehmed Çelebi’ye destek vermeye başlamış, böylece kardeşler arasında son büyük mücadele aşamasına geçilmiştir.\n" +
                    "\n" +
                    "1413 yılına gelindiğinde, Mehmed Çelebi güçlü bir ordu kurarak Rumeli’ye geçmiş ve kardeşi Musa Çelebi ile Çamurluova (Samokov) Savaşı’nda karşı karşıya gelmiştir. Bu savaşta Musa Çelebi ağır bir yenilgi almış, ordu dağıldıktan sonra ormanlık alanlara kaçmışsa da kısa süre içinde yakalanarak öldürülmüştür. Bazı kaynaklara göre, öldürülmeden önce teslim olmaya çalıştığı, ancak Mehmed Çelebi tarafından affedilmediği söylenir. Musa Çelebi’nin cesedi Sofya yakınlarına gömülmüştür; mezarının yeri tam olarak belli olmamakla birlikte bazı halk anlatıları onun şehit olduğuna inanır.\n" +
                    "\n" +
                    "Musa Çelebi, Osmanlı tarihinde kısa süreli, sert ve sarsıcı bir hükümdarlık dönemiyle anılır. Onun iktidarı resmî bir sultanlık statüsüne ulaşmamış olsa da, bir hükümdar gibi hareket etmiş, devlet erkini elinde tutmuş ve önemli kararlar almıştır. Ancak sert mizacı, siyasi uzlaşıya kapalı yaklaşımı ve Batı ile olan diplomatik bağları koparması, onu yalnızlaştırmış ve hızlı bir çöküşe sürüklemiştir. Diğer kardeşleriyle kıyaslandığında askerî cesareti kadar stratejik esnekliği sınırlıydı. Buna rağmen, Osmanlı’nın çalkantılı Fetret Devri döneminde Balkanlar'daki varlığı koruması, onun mücadeleci ve savaşçı karakterinin bir göstergesidir.\n" +
                    "\n" +
                    "Musa Çelebi’nin ölümüyle birlikte Fetret Devri sona ermiş, kardeşi Mehmed Çelebi (Çelebi Mehmed) Osmanlı tahtında tek hâkim olarak \"birinci kurucu\" sayılacak derecede yeniden birlik sağlamıştır. Musa Çelebi’nin kısa süren hâkimiyeti, Osmanlı'daki iktidar mücadelesinin ne kadar çetin olduğunu gösteren en belirgin örneklerden biridir. Ardında resmî bir türbe ya da uzun bir miras bırakmamış olsa da, Osmanlı tarihinde “gölge padişahlar” olarak anılan isimler arasında yer almış ve kardeş kavgası döneminin en etkili figürlerinden biri olmuştur.")
        val birinciMehmed= PadisahData(id = 8,
            isim = "Çelebi Mehmed",
            dogum = 1389,
            olum = 1421,
            cocuklari = "2.Murat,Mustafa Çelebi,Kasım Çelebi,Ahmed Çelebi,Yusuf Çelebi,Mahmud Çelebi,Orhan Çelebi,Fatma Hatun,Selcuk Hatun",
            donem = "Fetret devri sonu.",
            savaslari = "Ulubat Muharebesi,Musa Çelebi ile Mücadeleleri,Anadolu Harekatları,Osmanlı-Venedik Deniz Savaşı,Eflak Seferi,Şeyh Bedreddin İsyanı,Düzmece Mustafa İsyanı",
            lakap = "Anadolu Sultanı,Çelebi,Kirişçi",
            gorsel = R.drawable.mehmet_celebi,
            saltanatSuresi = 8,
            biyografi = "Çelebi Mehmed, yaklaşık 1389 yılında Amasya’da doğmuş, 1421 yılında Edirne’de vefat etmiştir. Osmanlı Devleti’nin beşinci padişahı olan Mehmed Çelebi, Yıldırım Bayezid’in oğludur. Annesi Germiyanoğulları Beyliği hanedanına mensup olan Devlet Hatun’dur. Eğitimli, sabırlı, ileri görüşlü ve dengeli karakteriyle tanınan Mehmed, küçük yaşlarından itibaren Amasya’da sancak beyliği yaparak hem devlet yönetimi hem de askerî konularda deneyim kazanmıştır. Babası Bayezid’in Timur’a esir düştüğü 1402 Ankara Savaşı sonrası Osmanlı Devleti’nde otorite çökmüş ve taht kavgası içinde geçen Fetret Devri başlamıştır. Bu zorlu süreçte Mehmed Çelebi, siyasi dengeleri iyi okuyarak hem kardeşleriyle mücadele etmiş hem de halkın güvenini kazanarak Osmanlı Devleti’ni yeniden ayağa kaldırmıştır.\n" +
                    "\n" +
                    "Ankara Savaşı sonrasında Timur, Osmanlı’nın dört bir yanını Bayezid’in oğulları arasında paylaştırmış ve böylece kardeşler arasında iktidar mücadelesi alevlenmiştir. İsa Çelebi, Musa Çelebi ve Süleyman Çelebi, Mehmed’in en güçlü rakipleri olmuştur. Mehmed Çelebi başlangıçta Amasya merkezli olarak Doğu ve Orta Anadolu’da hüküm sürmeye başlamış ve halk tarafından “en olgun ve umut vadeden” şehzade olarak görülmüştür. 1403 ile 1413 yılları arasında geçen 10 yıllık süre boyunca hem kardeşleriyle savaşmış, hem Anadolu’daki beyliklerle mücadele etmiş hem de halk desteğini sağlamaya çalışmıştır.\n" +
                    "\n" +
                    "Önce kardeşi İsa Çelebi ile giriştiği mücadeleyi kazanmış ve Ulubat Savaşı ile onun iktidarına son vermiştir. Ardından Musa Çelebi’yi Rumeli’ye geçmesi için desteklemiş, ancak Musa'nın güçlenip kendi aleyhine davranmasıyla araları açılmıştır. Bu kez bizzat ordusunun başına geçerek 1413 yılında Çamurluova (Samokov) Savaşı'nda Musa Çelebi’yi mağlup etmiş, onu öldürterek rakipsiz kalmıştır. Bu zaferin ardından Osmanlı tahtında yeniden tek egemen olarak Edirne’ye girmiş ve böylece Fetret Devri'ni sona erdirmiştir. Bu yönüyle Mehmed Çelebi, Osmanlı Devleti'ni dağılmanın eşiğinden kurtaran padişah olarak tarihe geçmiştir.\n" +
                    "\n" +
                    "Tahta geçtikten sonra \"I. Mehmed\" unvanını almış ve saltanatı boyunca devleti tekrar toparlamaya, içte ve dışta düzeni sağlamaya çalışmıştır. Anadolu’daki beylikler bir kez daha güçlenmişti; bu nedenle Aydınoğulları, Menteşeoğulları, Germiyanoğulları gibi beyliklere karşı seferler düzenleyerek Anadolu Türk birliğini yeniden kurmak için önemli adımlar atmıştır. Bu süreçte hem askerî başarılar kazanmış hem de siyasî evlilikler ve anlaşmalar yoluyla Anadolu’daki otoritesini pekiştirmiştir.\n" +
                    "\n" +
                    "Ancak Mehmed Çelebi’nin en zor sınavlarından biri, içeriden gelen tehditler olmuştur. 1416 yılında çıkan ve Osmanlı tarihinde büyük bir dini-sosyal kırılma olan Şeyh Bedreddin İsyanı, onun iktidarını ciddi şekilde sarsmıştır. Şeyh Bedreddin, hem dinî hem sosyal söylemleriyle halkın büyük bir kısmını etkileyerek Rumeli’de ayaklanma başlatmıştır. İsyan kısa sürede yayılsa da Mehmed Çelebi bu krizi ustalıkla yönetmiş, Bedreddin'in taraftarlarını dağıtmış ve isyanın bastırılmasının ardından Bedreddin’i yakalatıp idam ettirmiştir. Bu olay, merkezi otoriteye karşı çıkan en önemli dini-siyasi hareketlerden biri olarak Osmanlı tarihinde yerini almıştır.\n" +
                    "\n" +
                    "Çelebi Mehmed döneminde devlet yeniden yapılanmaya girmiştir. Tımar sistemi güçlendirilmiş, Anadolu’da yeni kadı ve subaşılar görevlendirilmiş, medrese ve cami inşaatları hız kazanmıştır. Bursa, Edirne ve Amasya gibi şehirlerde birçok vakıf eser inşa edilmiştir. Bursa’da yaptırdığı Yeşil Külliye, camisi, türbesi ve medresesi ile birlikte onun hem mimari hem de siyasi vizyonunu yansıtır. Aynı zamanda, merkezî idarenin yeniden güçlendirilmesi adına saray teşkilatı ve bürokrasi sistemleri yeniden düzenlenmiştir.\n" +
                    "\n" +
                    "I. Mehmed’in eşi Emine Hatun, Dulkadiroğulları Beyliği’ne mensuptur. Bu evlilik, Anadolu’daki siyasi dengeyi sağlamak amacıyla yapılmış ve dostane ilişkileri pekiştirmiştir. Bu evlilikten doğan oğlu, sonraki padişah olacak olan II. Murad’dır. Çelebi Mehmed, saltanatının son yıllarında II. Murad’ı tahta hazırlamak için Amasya’ya göndermiş, devlet işlerini yavaş yavaş ona devretmeye başlamıştır.\n" +
                    "\n" +
                    "1421 yılında Edirne’de vefat eden Mehmed Çelebi, Bursa’daki Yeşil Türbe’ye defnedilmiştir. Ardında Osmanlı’yı yeniden ayağa kaldıran, halkı yorgun bir iç savaştan çıkarıp devlete yeniden güven kazandıran bir hükümdar olarak hatırlanmıştır. O, Osmanlı tarihçileri tarafından çoğu zaman ikinci kurucu olarak anılır. Çünkü Osman Gazi ile temeli atılan devletin, neredeyse parçalanmak üzereyken tekrar tek çatı altında toplanmasını ve kurumsallaşmasını sağlamıştır.\n" +
                    "\n" +
                    "Mehmed Çelebi; sabırlı, kararlı, halkına yakın, iyi eğitimli ve krizi yöneten bir lider olarak Osmanlı tarihinde özel bir yere sahiptir. Onun döneminde atılan temeller, hem II. Murad’ın hem de torunu Fatih Sultan Mehmed’in devleti büyük bir imparatorluğa dönüştürmesine zemin hazırlamıştır. Kısa sayılabilecek saltanatı boyunca iç savaş, isyan, bölünme ve yeniden diriliş gibi ağır süreçleri başarıyla yöneten Çelebi Mehmed, Osmanlı’nın en zor zamanlarında devlet aklını temsil eden padişah olmuştur.")
        val ikinciMurad= PadisahData(id = 9,
            isim = "2. Murad",
            dogum = 1404,
            olum = 1451,
            donem = "",
            gorsel = R.drawable.ikinci_murad,
            saltanatSuresi = 28,
            lakap = "Koca Sultan,Han,Gazi,Muradi",
            savaslari = "Edirne-Segedin Antlaşması,Varna Savaşı,II. Kosova Savaşı,Eflak Seferleri,Sırbistan Seferleri,Anadolu Türk Beylikleriyle Mücadeleler (Karamanoğulları, Germiyanoğulları vb.)",
            cocuklari = "Şehzade Büyük Ahmed,Şehzade Alaaddin,Fatih Sultan Mehmed,Şehzade İsfanyar,Şehzade Hüseyin,Şehzade Orhan, Şehzade Hasan,Şehzade Küçük Ahmed,Şehzade Selçuk,Şehzade Yusuf,Hatice Hatun,Fatma Hatun,Şehzâde Hatun,Erhundi Hatun",
            biyografi = "II. Murad, 1404 yılında Amasya’da dünyaya gelmiştir. Babası Osmanlı Devleti’nin beşinci padişahı I. Mehmed (Çelebi), annesi ise Dulkadiroğulları beyliğine mensup Emine Hatun’dur. Küçük yaşlardan itibaren iyi bir eğitim almış, devlet yönetimi ve askeri konularda tecrübe kazanması için babası tarafından Amasya’ya sancak beyi olarak gönderilmiştir. Babasının 1421 yılında vefat etmesi üzerine henüz 17 yaşında iken tahta çıkmış ve Osmanlı tahtına geçen altıncı padişah olmuştur. " +
                    "II. Murad’ın tahta geçişi, Osmanlı Devleti için oldukça çalkantılı bir döneme denk gelmiştir. Henüz yeni padişah olmuşken, Bizans’ın kışkırtmasıyla amcası Düzmece Mustafa isyan etmiş ve kendisini padişah ilan etmiştir. II. Murad, kısa sürede bu tehdidi bertaraf ederek Düzmece Mustafa’yı yakalatmış ve idam ettirmiştir. Ardından kardeşi Şehzade Mustafa’nın isyanı ile uğraşmak zorunda kalmış ve onu da bertaraf ederek devletin iç güvenliğini sağlamlaştırmıştır. Bu iki isyan, II. Murad’ın daha ilk yıllarında ne kadar ciddi krizlerle mücadele ettiğini göstermektedir.Saltanat yılları boyunca hem batıda hem doğuda önemli mücadelelere girişmiştir. Balkanlar’da Osmanlı karşıtı birleşik Haçlı ordularıyla defalarca savaşmak zorunda kalmıştır. 1444 yılında Macaristan, Polonya, Papalık ve bazı Balkan devletlerinin oluşturduğu büyük bir Haçlı ordusu, Osmanlı üzerine yürümüştür. II. Murad, bu savaş öncesinde isteğiyle tahttan feragat ederek henüz 12 yaşında olan oğlu II. Mehmed’i tahta çıkarmıştır. Ancak Varna’da yaklaşan büyük tehdit karşısında devlet adamlarının ısrarı üzerine tekrar tahta dönmüş ve 10 Kasım 1444’te yapılan Varna Savaşı’nda Haçlı ordusunu kesin bir yenilgiye uğratarak büyük bir zafer kazanmıştır. Bu savaş, Osmanlı'nın Balkanlardaki hakimiyetini daha da pekiştirmiştir. Bu zaferin ardından II. Murad bir süre daha tahtta kalmış, ardından tekrar oğlu lehine tahttan çekilmiştir. Ancak bu ikinci dönemde de özellikle batıdaki siyasi ve askeri istikrarsızlıklar nedeniyle 1446 yılında tahta ikinci kez çıkmak zorunda kalmıştır. 1448 yılında II. Kosova Savaşı’nda yeniden birleşen Haçlı orduları bir kez daha Osmanlı karşısında mağlup edilmiştir. Bu savaş, Osmanlı'nın Avrupa’daki varlığını uzun vadeli olarak güvence altına almıştır.\n" +
                    "\n" +
                    "II. Murad, yalnızca bir asker ve devlet adamı değil, aynı zamanda sanat ve kültüre de değer veren bir padişahtı. Sarayda birçok ilim ve sanat insanını himaye etmiş, mimari faaliyetlerde bulunmuştur. Barış dönemlerinde ülke imarıyla ilgilenmiş, eğitim ve dini yapılar yaptırmıştır. Aynı zamanda dindar ve sade yaşantısıyla da tanınmıştır.\n" +
                    "\n" +
                    "1451 yılında Edirne’de vefat etmiş ve Bursa’daki Muradiye Külliyesi’ne defnedilmiştir. Ölümünden sonra tahta geçen oğlu II. Mehmed, kısa süre içinde İstanbul’u fethederek Osmanlı tarihinin dönüm noktalarından birine imza atacaktır. II. Murad, genç yaşta tahta çıkmasına rağmen kararlı, sabırlı ve devletin bekasını her şeyin önünde tutan bir lider olarak Osmanlı tarihine geçmiştir ")
        val ikinciMehmed= PadisahData(id = 10,
            isim = "2.Mehmed",
            dogum = 1432,
            olum = 1481,
            donem = "",
            gorsel = R.drawable.ikinci_mehmed,
            saltanatSuresi = 32,
            lakap = "Fatih Sultan Mehmed Han,Kayser-i Rum,Ebu'l Feth",
            cocuklari = "2.Bayezid,Mustafa,Cem Sultan,Gevherhan Hatun",
            savaslari = "İstanbul'un Fethi,Sırbistan Seferleri,Belgrad Kuşatması,Mora'nın Fethi,Amasra'nın Fethi,Trabzon'un Fethi,Eflak Seferi ,Midilli'nin Fethi,Bosna'nın Fethi,Osmanlı-Venedik Savaşları,Karaman Seferleri,Arnavutluk Seferleri,Otlukbeli Savaşı,Kırım'ın Fethi,Boğdan Seferi,Otranto Seferi",
            biyografi = "II. Mehmed, 30 Mart 1432 tarihinde Edirne’de doğmuştur. Babası Osmanlı padişahı II. Murad, annesi ise Hüma Hatun’dur. Küçük yaşlardan itibaren çok yönlü bir eğitim almıştır. Arapça, Farsça, Yunanca, Latince ve Sırpça gibi birçok dili öğrenmiş, matematik, coğrafya, astronomi ve askeri strateji alanlarında dersler almıştır. Eğitimine önem veren II. Mehmed, genç yaşta zekâsı, kararlılığı ve hedefleriyle dikkat çekmiştir.\n" +
                    "\n" +
                    "İlk kez 12 yaşında, babası II. Murad’ın kendi isteğiyle tahtı bırakması sonucu 1444 yılında Osmanlı tahtına çıkmıştır. Ancak yaşı küçük olduğu ve devletin ciddi dış tehditlerle karşı karşıya kalması nedeniyle bu dönemde etkili bir yönetim sergilemesi zor olmuştur. Aynı yıl Haçlılar büyük bir orduyla Osmanlı topraklarına saldırmış, bunun üzerine II. Mehmed’in isteği ve devlet adamlarının baskısıyla II. Murad yeniden tahta geçmiştir. Bu dönemde II. Mehmed tahttan inmiş olsa da, gelişmeleri dikkatle takip etmeye devam etmiş ve devlet yönetimi konusunda olgunlaşmıştır.\n" +
                    "\n" +
                    "1451 yılında babası II. Murad’ın vefatı üzerine tekrar Osmanlı tahtına çıkmış ve bu kez kalıcı olarak padişah olmuştur. Bu ikinci saltanat dönemi, sadece Osmanlı tarihinde değil, dünya tarihinde de derin izler bırakmıştır. II. Mehmed’in en büyük hedefi, Doğu Roma İmparatorluğu’nun başkenti olan İstanbul’u fethetmekti. Yüzyıllardır pek çok hükümdarın kuşatıp alamadığı bu şehri alarak hem dini hem siyasi açıdan büyük bir başarı kazanmak istiyordu.\n" +
                    "\n" +
                    "Yoğun hazırlıklar sonucunda, 6 Nisan 1453’te İstanbul kuşatılmış ve 53 gün süren mücadeleden sonra 29 Mayıs 1453’te İstanbul fethedilmiştir. Bu zafer, Orta Çağ’ın sonu ve Yeni Çağ’ın başlangıcı olarak kabul edilmiş, II. Mehmed de tarihe \"Fatih Sultan Mehmed\" unvanıyla geçmiştir. İstanbul’un fethiyle Bizans İmparatorluğu sona ermiş, Osmanlı Devleti ise bir imparatorluk haline gelmiştir. Ayrıca fetihten sonra şehir imar edilmiş, camiler, medreseler, kütüphaneler ve saraylar inşa edilerek yeniden bir kültür ve ticaret merkezi haline getirilmiştir.\n" +
                    "\n" +
                    "Fatih Sultan Mehmed, İstanbul’un fethiyle yetinmemiş, doğuda ve batıda birçok sefer düzenlemiştir. Sırbistan, Mora, Eflak, Bosna, Arnavutluk, Trabzon Rum İmparatorluğu, Karaman Beyliği, Akkoyunlular gibi pek çok devlet ve beylik ile savaşmış, Osmanlı topraklarını genişletmiştir. 1473 yılında Otlukbeli Savaşı’nda Akkoyunlu hükümdarı Uzun Hasan’ı mağlup etmiştir. Ayrıca Kırım Hanlığı’nın Osmanlı’ya bağlanmasıyla Karadeniz tamamen Osmanlı hâkimiyetine girmiştir.\n" +
                    "\n" +
                    "Fatih Sultan Mehmed yalnızca büyük bir asker ve devlet adamı değil, aynı zamanda kültür ve bilimle ilgilenen bir padişahtı. Sarayında dönemin en önemli bilim insanlarını, sanatçılarını ve filozoflarını topladı. Hem Doğu hem Batı kültürüne ilgi duyar, farklı inançlara saygı gösterir, bilimsel düşünceye önem verirdi. Kanunname-i Ali Osman adlı hukuk düzenlemesiyle Osmanlı’da merkezi otoriteyi güçlendirdi, devlet yapısını daha sağlam bir hale getirdi.\n" +
                    "\n" +
                    "Fatih Sultan Mehmed, 3 Mayıs 1481 tarihinde henüz yeni bir sefere çıkmak üzereyken Gebze yakınlarında vefat etti. Ölüm nedeni tam olarak bilinmese de zehirlenmiş olabileceğine dair bazı iddialar vardır. Cenazesi İstanbul’a getirilmiş ve kendi yaptırdığı Fatih Camii'nin haziresine defnedilmiştir.\n" +
                    "\n" +
                    "II. Mehmed, İstanbul’un fatihi olmasının yanı sıra, Osmanlı'yı bir cihan imparatorluğuna dönüştüren lider olarak hatırlanır. Onun ileri görüşlülüğü, ilme verdiği önem ve güçlü karakteri, yalnızca Osmanlı tarihini değil, dünya tarihini de derinden etkilemiştir.\n" +
                    "\n")
        val ikinciBayezid= PadisahData(id = 11,
            isim = "2.Bayezid",
            saltanatSuresi = 31,
            gorsel = R.drawable.ikinci_beyazid,
            donem = "",
            lakap = "Sultan Bayezid-i Veli,Han,Gazi,Adli",
            dogum = 1447,
            olum = 1512,
            cocuklari = "Şehzade Ahmed,Şehzade Korkut,1.Selim,Şehzade Mahmud,Şehzade Mehmed,Şehzade Alemşah,Şehzade Abdullah,Şehzade Şehinşah,Gevherimüluk Hatun,Selçuk Hatun,Hatice Hatun,Ayşe Hatun,Hundi Hatun,Aynışah Hatun,Fatma Hatun,Hümaşah Hatun,Kamerşah Hatun,Şah Hatun,İlaldı Hatun",
            savaslari = "Cem Sultan İsyanı ve Mücadelesi,Osmanlı-Memlûk Savaşları,Boğdan Seferi,Osmanlı-Macar Savaşları,Osmanlı-Lehistan Savaşları,Osmanlı-Venedik Savaşları,Şahkulu İsyanı",
            biyografi = "II. Bayezid, 3 Aralık 1447 tarihinde Dimetoka’da (bugünkü Yunanistan sınırlarında) dünyaya gelmiştir. Babası, İstanbul’un fatihi olan II. Mehmed (Fatih Sultan Mehmed), annesi ise Emine Gülbahar Hatun’dur. Küçük yaşlardan itibaren klasik Osmanlı şehzade eğitimi almış, Kur’an, fıkıh, edebiyat, matematik ve özellikle dinî ilimlerde ileri düzeyde yetişmiştir. Aynı zamanda iyi bir hattat ve şair olan II. Bayezid, \"Adli\" mahlasını kullanarak şiirler kaleme almıştır.\n" +
                    "\n" +
                    "Babası Fatih Sultan Mehmed’in vefatının ardından, 1481 yılında Osmanlı tahtına çıkmıştır. Ancak tahta geçiş süreci sancılı olmuştur. Kardeşi Cem Sultan, saltanat mücadelesine girişmiş ve bu mücadele Osmanlı tarihinde önemli bir iç karışıklığa yol açmıştır. Cem Sultan, önce Konya ve çevresinde hâkimiyet sağlamaya çalışmış, daha sonra Memlükler’e ve ardından Rodos Şövalyeleri’ne sığınmıştır. Sonunda Papa'nın eline geçmesiyle Avrupa siyasetinde bir piyon haline getirilmiştir. II. Bayezid, kardeşi Cem Sultan’ın hayatta ve Avrupa’da esir durumda olması nedeniyle yıllarca Avrupa’ya karşı diplomatik baskılara maruz kalmıştır.\n" +
                    "\n" +
                    "Saltanatı boyunca fetihlerden ziyade iç istikrar, adalet ve ilmi gelişmeye önem veren bir padişah olarak tanınmıştır. Babasının son yıllarında uyguladığı merkeziyetçi ve sert politikalardan farklı olarak, halkın ve ulemanın desteğini kazanmaya çalışmıştır. Onun döneminde Anadolu’da birçok cami, medrese, kütüphane ve hayır kurumu inşa edilmiştir. İstanbul’daki en büyük eserlerinden biri olan Beyazıt Camii ve külliyesi bu dönemde inşa edilmiştir.\n" +
                    "\n" +
                    "II. Bayezid döneminde Osmanlı donanması gelişmeye devam etmiş, Akdeniz'deki Osmanlı varlığı güçlenmiştir. 1492 yılında İspanya'da Engizisyon zulmüne uğrayan Yahudiler ve Müslümanlar, Bayezid’in emriyle Osmanlı topraklarına getirilmiş ve özellikle Selanik, İstanbul ve İzmir gibi şehirlere yerleştirilmiştir. Bu olay, onun hoşgörülü ve insani yaklaşımı açısından tarihe geçen bir örnek olmuştur. Bu göç, Osmanlı ekonomisine, bilimsel ve kültürel hayatına da büyük katkı sağlamıştır.\n" +
                    "\n" +
                    "Bayezid’in son yılları ise oldukça sıkıntılı geçmiştir. Oğulları arasında yaşanan taht mücadeleleri, özellikle Yavuz Sultan Selim ile olan çekişme, siyasi bir krize dönüşmüştür. Yavuz Selim’in kararlı tutumu karşısında daha fazla direnemeyen II. Bayezid, 1512 yılında tahtı oğluna bırakmak zorunda kalmıştır. Tahttan feragat ettikten kısa bir süre sonra, 26 Mayıs 1512 tarihinde Dimetoka’ya gitmek üzereyken yolda hastalanarak vefat etmiştir. İstanbul’daki Bayezid Camii haziresine defnedilmiştir.\n" +
                    "\n" +
                    "II. Bayezid, Osmanlı tarihinin en sakin ve içe dönük padişahlarından biri olarak anılır. Fetih ve genişleme yerine huzur, adalet ve dini değerlere öncelik vermiştir. Bilime, sanata ve özellikle dini eğitime önem vermiş, alimleri ve sanatkârları desteklemiştir. Dönemi nispeten barışçıl geçmiş, ancak taht mücadelesi ve Cem Sultan olayı nedeniyle siyasi açıdan zorlayıcı olmuştur.")
        val birinciSelim= PadisahData(id = 12,
            isim = "1.Selim",
            dogum = 1470,
            olum = 1520,
            saltanatSuresi = 8,
            donem = "",
            gorsel = R.drawable.birinci_selim,
            lakap = "Yavuz Sultan Selim,Han,Gazi,Selimi",
            cocuklari = "1.Süleyman,Üveys Paşa,Hatice Sultan,Beyhan Sultan,Fatma Sultan,Hafize Sultan,Şah Sultan,Şehzade Orhan,Şehzade Musa,Şehzade Korkut,Şehzade Salih,Beyhan Sultan",
            savaslari = "Çaldıran Savaşı,Turnadağ Muharebesi,Mercidabık Muharebesi,Ridaniye Muharebesi,Şahkulu isyanının bastırılması",
            biyografi = "I. Selim, 10 Ekim 1470 tarihinde Amasya’da doğmuştur. Babası II. Bayezid, annesi ise Gülbahar Hatun’dur. Küçük yaşlardan itibaren dönemin önemli alimlerinden ders almış, dinî ilimler, matematik, tarih ve savaş sanatlarında iyi bir eğitim görmüştür. Aynı zamanda iyi bir kılıç ustası, okçu ve güçlü bir liderlik yeteneğine sahipti. Sert mizacı, kararlı duruşu ve keskin zekâsıyla daha genç yaşta dikkat çekmiştir. Osmanlı tarihinde “Yavuz” unvanını alacak kadar güçlü ve gözü kara bir karaktere sahipti.\n" +
                    "\n" +
                    "Tahta çıkmadan önce Trabzon Sancakbeyi olarak görev yapan Selim, Karadeniz kıyılarında Gürcüler ve Safevilerle mücadele ederek askeri tecrübe kazanmıştır. Babası II. Bayezid’in yumuşak yönetim tarzından rahatsız olan Yavuz Sultan Selim, 1512 yılında babasını tahttan indirerek Osmanlı tahtına çıkmıştır. Bu olay, Osmanlı tarihinde ilk kez bir padişahın fiilen zorla tahttan indirildiği örneklerden biri olmuştur. Tahta geçer geçmez içteki rakipleriyle hesaplaşmış ve diğer şehzadeleri ortadan kaldırarak otoritesini sağlamlaştırmıştır.\n" +
                    "\n" +
                    "Yavuz Sultan Selim’in saltanatı kısa sürmüş olsa da (1512-1520), Osmanlı Devleti’nin sınırlarını üç kıtaya yaydığı ve halifelik makamını ele geçirdiği en önemli dönüm noktalarından biri olmuştur. Saltanatının en dikkat çekici olayı, 1514 yılında yapılan Çaldıran Savaşıdır. Bu savaşta Safevi hükümdarı Şah İsmail büyük bir yenilgiye uğratılmış, Doğu Anadolu’nun büyük bölümü Osmanlı topraklarına katılmıştır. Bu zafer, sadece toprak kazancı değil, aynı zamanda Sünni-Şii mücadelesinde Osmanlı’nın üstünlüğü açısından da önemlidir.\n" +
                    "\n" +
                    "1516-1517 yıllarında Yavuz Sultan Selim, Memlük Sultanlığı’na karşı sefere çıktı. Mercidabık (1516) ve Ridaniye (1517) savaşlarında Memlükleri mağlup etti. Bu zaferlerle Suriye, Filistin, Mısır ve Hicaz Osmanlı topraklarına katıldı. Böylece kutsal şehirler olan Mekke ve Medine'nin himayesi Osmanlı’ya geçti. Abbasi Halifesi III. Mütevekkil, halifelik makamını ve kutsal emanetleri Yavuz’a devretti. Böylece Osmanlı Padişahları, İslam dünyasının halifesi sıfatını da taşıyarak siyasi gücün yanında dini liderliği de üstlenmiş oldular.\n" +
                    "\n" +
                    "Yavuz Sultan Selim, sert mizacına rağmen son derece ileri görüşlü bir hükümdardı. Kısa süren 8 yıllık saltanatı boyunca Osmanlı'nın doğu sınırlarını güvence altına almış, İslam dünyasında birlik sağlamış ve devleti küresel bir güç haline getirmiştir. Askerî dehası ve otoriter yönetimiyle Osmanlı'yı hem doğuda hem güneyde büyük bir imparatorluğa dönüştürmüştür.\n" +
                    "\n" +
                    "22 Eylül 1520 tarihinde, henüz 50 yaşındayken Çorlu yakınlarında aniden hastalanarak vefat etmiştir. Ölüm sebebinin vücudunda çıkan bir çıbanın (şirpençe) enfekte olması olduğu rivayet edilir. Cenazesi İstanbul’a getirilmiş ve Fatih’te kendi adıyla anılan Yavuz Selim Camii’nin avlusuna defnedilmiştir.\n" +
                    "\n" +
                    "Yavuz Sultan Selim, az ama öz hüküm süren, kararlılığıyla devletin gidişatını kökten değiştiren ve Osmanlı'nın hem siyasi hem dini liderliğini perçinleyen bir padişah olarak tarihe geçmiştir. Onun ardından tahta geçen oğlu Kanuni Sultan Süleyman, imparatorluğu en ihtişamlı dönemine taşıyacaktır.\n" +
                    "\n")
        val birinciSuleyman= PadisahData(id = 13,
            isim = "1. Süleyman",
            dogum = 1494,
            olum = 1566,
            saltanatSuresi = 45,
            donem = "",
            gorsel = R.drawable.birinci_suleyman,
            lakap = "Kanûni Sultan Süleyman,Han,Muhteşem,Muhibbi",
            cocuklari = "Şehzade Mahmud,Şehzade Mustafa,Şehzade Mehmed,Şehzade Murad,Mihrimah Sultan,Şehzade Abdullah,Şehzade Selim,Şehzade Bayezid,Şehzade Cihangir,Raziye Sultan",
            savaslari = "Belgrad'ın Fethi,Rodos'un Fethi,Mohaç Meydan Muharebesi,Birinci Viyana Kuşatması,Almanya Seferi ve Alman Seferi,Irak Seferi ve Tebriz'in Fethi,Preveze Deniz Muharebesi,Budin'in Fethi ve Macaristan'ın Osmanlı Eyaleti Olması,Trablusgarp'ın Fethi,Nahçıvan Seferi,Sigetvar Kuşatması",
            biyografi = "I. Süleyman, 6 Kasım 1494 tarihinde Trabzon’da doğmuştur. Babası Yavuz Sultan Selim, annesi Hafsa Hatun’dur. Küçük yaşlardan itibaren dönemin en iyi hocalarından eğitim almış; Kur’an, Arapça, Farsça, matematik, edebiyat, tarih ve siyaset gibi alanlarda yetişmiştir. Genç yaşta Manisa Sancakbeyliği yaparak devlet yönetimi tecrübesi kazanmıştır. Babası Yavuz Sultan Selim’in 1520 yılında vefat etmesinin ardından, 26 yaşında tahta çıkmıştır.\n" +
                    "\n" +
                    "I. Süleyman, Osmanlı tahtına çıktığında, devlet zaten güçlü ve geniş sınırlara sahipti. Ancak o, hem bu sınırları daha da ileriye taşıyacak hem de Osmanlı’yı zirveye ulaştıracaktı. 46 yıl süren (1520–1566) saltanatı, Osmanlı Devleti'nin en uzun süren ve en ihtişamlı dönemidir.\n" +
                    "\n" +
                    "Dış politikada çok sayıda sefere çıkmıştır. Belgrad (1521) ve Rodos (1522) seferleriyle Balkanlar ve Akdeniz’de hâkimiyet güçlenmiş, ardından 1526’da Mohaç Meydan Muharebesi ile Macaristan Osmanlı kontrolüne girmiştir. 1529’da Viyana Kuşatması ile Osmanlı ordusu Orta Avrupa’nın kapısına kadar dayanmıştır. Ayrıca doğuda Safevîler’e karşı seferler düzenlemiş ve Irakeyn Seferi ile Doğu Anadolu, Irak ve çevresi Osmanlı topraklarına katılmıştır.\n" +
                    "\n" +
                    "Denizlerde de büyük başarılara imza atılmıştır. Barbaros Hayreddin Paşa’nın kaptan-ı derya olmasıyla Osmanlı donanması Akdeniz’in en güçlü filosu haline gelmiştir. 1538 Preveze Deniz Savaşı, Osmanlı’nın denizlerdeki hâkimiyetini ilan ettiği dönüm noktası olmuştur. Aynı zamanda Hint Okyanusu'na seferler düzenlenmiş, Kızıldeniz ve Basra Körfezi Osmanlı etkisi altına girmiştir.\n" +
                    "\n" +
                    "Ancak Kanuni sadece bir asker ve sefer padişahı değildir. Aynı zamanda \"Kanuni\" unvanını almasını sağlayan bir hukuk reformcusudur. Osmanlı hukuk sistemini yazılı hale getirerek İslam hukukuyla örfi hukuku dengeleyen kanunnameler oluşturmuştur. Bu düzenlemeler, devletin idari yapısını ve toplumsal düzenini güçlendirmiştir. Onun devrinde Osmanlı sadece bir askeri güç değil, aynı zamanda hukuk, sanat, mimari ve edebiyatın zirveye ulaştığı bir medeniyet merkezi haline gelmiştir.\n" +
                    "\n" +
                    "Döneminde Mimar Sinan, Osmanlı mimarisinin altın çağını yaşatmış; Süleymaniye Camii gibi başyapıtlar inşa edilmiştir. Şair olarak da yetenekli olan Sultan Süleyman, \"Muhibbi\" mahlasıyla çok sayıda şiir yazmıştır. Döneminde sarayda edebiyat, felsefe ve bilim meclisleri kurulmuş; sanatkârlar desteklenmiştir.\n" +
                    "\n" +
                    "Özel hayatı da siyasi tarih açısından önemlidir. Eşi Hürrem Sultan ve veziriazamı Rüstem Paşa ile olan ilişkisi, saray içinde dengeleri etkilemiş; oğlu Şehzade Mustafa’nın idamı ve Şehzade Bayezid’in isyanı gibi olaylar saltanatının ilerleyen yıllarında devleti içten sarsmıştır.\n" +
                    "\n" +
                    "Kanuni Sultan Süleyman, 1566 yılında çıktığı son sefer olan Zigetvar Kuşatması sırasında, 7 Eylül 1566’da ordugâhta vefat etmiştir. Ölüm haberi askerlerin morali bozulmasın diye bir süre gizlenmiştir. Cenazesi İstanbul’a getirilerek kendi adını taşıyan Süleymaniye Camii’ne defnedilmiştir.\n" +
                    "\n" +
                    "46 yıl süren saltanatı boyunca Osmanlı Devleti, hem yüzölçümü hem de siyasi, kültürel ve ekonomik anlamda zirveye ulaşmıştır. Avrupa’da “Muhteşem Süleyman”, doğuda ise adaletli tutumlarıyla “Kanuni” olarak anılmıştır. Ardında imparatorluğu yüzyıllarca ayakta tutacak güçlü bir miras bırakmıştır.\n" +
                    "\n")
        val ikinciSelim= PadisahData(id = 14,
            isim = "2. Selim",
            dogum = 1524,
            olum = 1574,
            lakap = "Sarı",
            gorsel = R.drawable.ikinci_selim,
            donem = "",
            saltanatSuresi = 8,
            cocuklari = "3.Murad,Şehzade Abdullah,Şehzade Osman,Şehzade Mustafa,Şehzade Süleyman,Şehzade Mehmed,Şehzade Mahmud,Şehzade Cihangir,Gevherhan Sultan,Esmehan Sultan,Şah Sultan,Fatma Sultan,Ayşe Sultan,Mihrimah Sultan",
            savaslari = "Sakız Adası'nın Fethi,Yemen Seferi,Kıbrıs'ın Fethi,Tunus'un Fethi,İnebahtı Deniz Savaşı,Astrahan Seferi",
            biyografi = "II. Selim, 28 Mayıs 1524’te İstanbul’da doğdu. Babası Kanuni Sultan Süleyman, annesi Hürrem Sultan’dır. Babasının ölümünden sonra 1566’da tahta geçti ve 1574’e kadar Osmanlı padişahı olarak görev yaptı. Saltanatı sırasında devleti büyük ölçüde sadrazam Sokullu Mehmed Paşa yönetti. Kıbrıs’ın fethi ve ardından gelen İnebahtı Deniz Savaşı bu dönemin önemli olaylarındandır. Saray hayatına ve keyfe düşkünlüğüyle tanınır; “Sarı Selim” lakabıyla anılır. 15 Aralık 1574’te İstanbul’da vefat etti ve Ayasofya’daki türbesine defnedildi. Osmanlı tarihinde tahta çıkan ilk Hürrem Sultan oğludur.")
        val ucuncuMurad= PadisahData(id = 15,
            isim = "3.Murad",
            dogum = 1546,
            olum = 1595,
            saltanatSuresi = 20,
            gorsel = R.drawable.ucuncu_murad,
            donem = "",
            lakap = "Murad-i Salis,Muradi",
            cocuklari = "3. Mehmed,Şehzade Mahmud,Şehzade Mustafa,Şehzade Osman,Şehzade Cihangir,Şehzade Süleyman,Şehzade Abdullah,Şehzade Ömer,Ayşe Sultan,Fatma Sultan,Mihrimah Sultan,Fahriye Sultan,Rukiye Sultan",
            savaslari = "Osmanlı-İran Savaşları,Lehistan Seferi,İspanya ile çatışmalar",
            biyografi = "III. Murad, 4 Temmuz 1546 tarihinde Manisa’da doğmuştur. Babası II. Selim, annesi ise Osmanlı sarayının güçlü kadın figürlerinden biri olan Nurbanu Sultan’dır. Osmanlı geleneklerine uygun olarak iyi bir eğitim almış, özellikle dini ilimlerde ve edebiyatta yetişmiştir. Şehzadelik yıllarını Manisa Sancakbeyliği görevinde geçirerek yöneticilik tecrübesi kazanmıştır. Babasının 1574 yılında vefat etmesi üzerine 28 yaşında Osmanlı tahtına çıkmıştır.\n" +
                    "\n" +
                    "III. Murad’ın tahta çıkışıyla birlikte \"kardeş katli\" uygulaması yeniden gündeme gelmiş ve birçok şehzadesi (öz kardeşi) daha tahta çıktığı ilk günlerde boğdurulmuştur. Bu uygulama, Osmanlı’da merkezi otoriteyi korumak amacıyla yapılmış olsa da, toplumda ve tarihçiler arasında tartışmalara neden olmuştur.\n" +
                    "\n" +
                    "Saltanatı boyunca devletin idaresi üzerinde annesi Nurbanu Sultan ve ardından eşi Safiye Sultan önemli ölçüde etkili olmuştur. Bu dönem Osmanlı tarihinde “kadınlar saltanatı” olarak adlandırılan sürecin derinleştiği bir dönemdir. Ayrıca saray hayatının lüksü, harem düzeninin büyümesi ve israf gibi eleştiriler, bu dönemde daha sık duyulur olmuştur.\n" +
                    "\n" +
                    "Dış politikada ise III. Murad’ın döneminde Osmanlı Devleti, hem doğuda hem batıda önemli savaşlara girmiştir. 1578 yılında başlayan ve 1590’a kadar süren Osmanlı-Safevî Savaşı, doğuda İran ile yapılan en uzun savaşlardan biri olmuş ve Ferhat Paşa Antlaşması ile sonuçlanmıştır. Bu antlaşmayla Osmanlı, Doğu Anadolu, Azerbaycan ve Luristan gibi geniş toprakları ele geçirmiştir.\n" +
                    "\n" +
                    "Batıda ise 1593 yılında başlayan Osmanlı-Avusturya Savaşı (15 Yıl Savaşları’nın başlangıcı), uzun ve yıpratıcı bir mücadeleyi başlatmıştır. Bu savaş, III. Murad hayattayken sonuçlanmasa da, Osmanlı-Avrupa ilişkilerinde yeni bir dönemin başlangıcını temsil eder. Bu dönemde Avusturya ile yaşanan sınır mücadeleleri, Osmanlı’nın batıda artık zorlanmaya başladığının da göstergesi olmuştur.\n" +
                    "\n" +
                    "III. Murad, iç politikada ise zaman zaman bozulmalarla karşı karşıya kalmıştır. Rüşvet, iltimas, saray harcamalarının artması ve yeniçeri disiplinsizliği gibi unsurlar devlet düzeninde zayıflamalara yol açmıştır. Özellikle saraydaki memuriyetlerin para karşılığı verilmesi (iltizam ve arpalık sistemlerinin kötüye kullanımı), toplumda adalet duygusunun zedelenmesine neden olmuştur.\n" +
                    "\n" +
                    "Tüm bu sıkıntılara rağmen III. Murad, ilmiye sınıfına ve sanata büyük önem vermiştir. Camiler, medreseler, türbeler yaptırmış; aynı zamanda şair yönüyle de öne çıkmıştır. \"Muradi\" mahlasıyla şiirler kaleme almış, divan edebiyatına katkıda bulunmuştur. Mimar Sinan’ın son büyük projelerinden biri olan Üsküdar’daki Atik Valide Külliyesi, onun döneminde inşa edilmiştir.\n" +
                    "\n" +
                    "III. Murad, 15/16 Ocak 1595 tarihinde İstanbul’da vefat etmiş ve Ayasofya Camii’nin arkasındaki III. Murad Türbesi’ne defnedilmiştir. Yerine oğlu III. Mehmed geçmiştir.\n" +
                    "\n" +
                    "III. Murad dönemi, Osmanlı'nın zirveden aşağıya doğru inişe geçtiği bir dönemin habercisi sayılabilir. Fetihler devam etmiş olsa da, içeride artan yozlaşma, saray ve harem etkisi ile devlet yapısındaki gevşemeler, ilerideki ciddi sorunların temelini oluşturmuştur.")
        val ucuncuMehmed= PadisahData(id = 16,
            isim = "3.Mehmed",
            dogum = 1566,
            olum = 1603,
            saltanatSuresi = 8,
            donem = "",
            lakap = "Mehmed-i Salis,Adli",
            gorsel = R.drawable.ucuncu_mehmed,
            cocuklari = "1.Ahmed,Şehzade Mustafa,Şehzade Selim,Şehzade Mahmud,Şehzade Cihangir,Dilbura Hatun,Hatice Hatun,Ayşe Hatun",
            savaslari = "Haçova Meydan Muharebesi,Kanije Savunması,Celali İsyanları",
            biyografi = "III. Mehmed, 26 Mayıs 1566 tarihinde Manisa’da doğmuştur. Babası III. Murad, annesi ise Osmanlı sarayında etkili bir figür olan Safiye Sultan’dır. Küçük yaşlardan itibaren iyi bir eğitim almış, özellikle dini ilimler, tarih ve edebiyatla ilgilenmiştir. Şehzadelik döneminde Manisa sancakbeyliği yaparak devlet yönetimi tecrübesi kazanmıştır. Babası III. Murad’ın 1595 yılında vefatı üzerine, 29 yaşında Osmanlı tahtına geçmiştir.\n" +
                    "\n" +
                    "Tahta geçtiği ilk günlerde Osmanlı’nın klasik ama acı geleneklerinden biri olan kardeş katlini uygulamış ve tam 19 kardeşini boğdurtmuştur. Bu olay, Osmanlı tarihindeki en büyük kardeş katli olarak kabul edilir ve hem saray içinde hem halk arasında büyük yankı uyandırmıştır. Bu sert giriş, III. Mehmed'in saltanatına gölge düşürmüş ve kamuoyunda onun kişiliği üzerine olumsuz bir algı oluşturmuştur.\n" +
                    "\n" +
                    "III. Mehmed’in padişah olduğu dönemde Osmanlı Devleti, uzun süredir devam eden Avusturya Savaşları (1593-1606) içinde yer almaktaydı. Bu savaşlar, Osmanlı ordusu açısından zorlu geçmiş ve 1596 yılında gerçekleşen Haçova Meydan Muharebesi, bu sürecin dönüm noktası olmuştur. Genellikle sefere çıkmayan Osmanlı padişahlarının aksine, III. Mehmed bu sefere bizzat orduya komuta ederek katılmıştır. Haçova Savaşı’nda Osmanlı ordusu zor anlar yaşamış ancak savaşın son aşamasında elde edilen galibiyet, hem askeri hem de siyasi olarak büyük moral sağlamıştır. Bu zafer, onun kişisel olarak katıldığı tek sefer olarak tarihe geçmiştir.\n" +
                    "\n" +
                    "Ancak III. Mehmed'in bu askerî başarısına rağmen, devlet içindeki bozulmalar devam etmiştir. Özellikle Saray, harem ve rüşvet düzeni, annesi Safiye Sultan’ın büyük etkisiyle daha da karmaşık hale gelmiş, devlet yönetiminde denge bozulmuştur. Yeniçeri disiplinsizliği, mali sıkıntılar, halkın vergi yükünün artması gibi sorunlar da III. Mehmed döneminde daha görünür hale gelmiştir. Devletin klasik kurumları işlemeye devam etse de, artık sistemin içindeki yozlaşma belirtileri göz ardı edilemez boyutlara ulaşmıştır.\n" +
                    "\n" +
                    "III. Mehmed aynı zamanda edebiyata ve özellikle şiire ilgi duyan bir padişahtı. \"Adlî\" mahlasıyla şiirler kaleme almıştır. Dindar bir yapıya sahipti ve dini değerlere önem verirdi. Bu nedenle halk tarafından takdir edilen yönleri de olmuştur. Ancak karakteri itibariyle içine kapanık, kararsız ve zaman zaman duygusal kararlar veren bir padişah olarak tanımlanır.\n" +
                    "\n" +
                    "21 Aralık 1603 tarihinde, henüz 37 yaşındayken İstanbul’da vefat etmiştir. Ölüm nedeni kesin olarak bilinmemekle birlikte, hastalık ve psikolojik yıpranmanın etkili olduğu düşünülmektedir. Cenazesi, babası III. Murad’ın yanında, Ayasofya Camii arkasındaki türbeye defnedilmiştir. Yerine, henüz 13 yaşında olan oğlu I. Ahmed geçmiştir.\n" +
                    "\n" +
                    "III. Mehmed’in saltanatı, Osmanlı’nın klasik yapısının çözülmeye başladığı ve büyük değişimlerin eşiğine geldiği bir dönemdir. Fetihler azalmış, merkezi otorite zayıflamaya başlamış ve saray içi çekişmeler devlet işlerini doğrudan etkilemiştir. Her ne kadar Haçova zaferi gibi bir başarıya imza atmış olsa da, dönemi genellikle duraklamanın habercisi olarak görülür.\n" +
                    "\n")
        val birinciAhmed= PadisahData(id = 17,
            isim = "1. Ahmed",
            dogum = 1590,
            olum = 1617,
            donem = "",
            saltanatSuresi = 14,
            gorsel = R.drawable.birinci_ahmed,
            lakap = "Ahmed-i Evvel,Bahti",
            cocuklari = "2.Osman(Genç Osman),4.Murad,Sultan İbrahim,Şehzade Mehmed,Şehzade Bayezid,Şehzade Süleyman,Şehzade Kasım,Ayşe Sultan,Fatma Sultan,Gevherhan Sultan,Atike Sultan,Hanzade Sultan",
            savaslari = "Osmanlı-Avusturya Savaşları,Osmanlı-Safevi Savaşları,Celali İsyanları",
            biyografi = "I. Ahmed, 18 Nisan 1590 tarihinde Manisa’da doğmuştur. Babası III. Mehmed, annesi ise Rum asıllı olup “Mahpeyker Kösem Sultan” olarak tanınan Valide Kösem Sultan’dır. Henüz 13 yaşında iken babasının vefatı üzerine, 1603 yılında Osmanlı tahtına çıkmıştır. Tahta çıktığında çok genç olması nedeniyle onun dönemine dair beklentiler sınırlıydı, ancak zamanla güçlü iradesi ve karakteriyle devletin yönetiminde etkili olmuştur.\n" +
                    "\n" +
                    "I. Ahmed’in tahta çıkışı, Osmanlı hanedan geleneği açısından önemli bir dönüm noktasıdır. Babası III. Mehmed, tahta çıktığında kardeşlerini öldürerek geleneksel \"kardeş katli\" uygulamasını sürdürmüştü. Ancak I. Ahmed, şehzade kardeşi Mustafa’yı öldürtmemiş ve bu tutumu Osmanlı tahtına geçiş anlayışında \"ekber ve erşed\" sistemine doğru evrilen yeni bir dönemi başlatmıştır. Böylece tahta geçişte yaşça en büyük ve en olgun olan şehzadenin padişah olması anlayışı temellenmiştir.\n" +
                    "\n" +
                    "Saltanatı boyunca hem içeride hem dışarıda ciddi sorunlarla karşı karşıya kalmıştır. En önemli iç sorunlardan biri, Celâlî isyanlarıdır. Anadolu’da ekonomik sıkıntılar, ağır vergiler ve otorite boşluğu nedeniyle patlak veren bu isyanlar, Osmanlı taşrasında büyük yıkıma neden olmuş ve halk üzerinde ağır baskılar yaratmıştır. I. Ahmed, bu isyanları bastırmak için yoğun çaba harcamış ve zaman zaman sert yöntemlere başvurmuştur.\n" +
                    "\n" +
                    "Dış politikada ise Osmanlı Devleti, doğuda İran’la, batıda ise Avusturya ile mücadele halindeydi. 1606 yılında imzalanan Zitvatorok Antlaşması, Osmanlı ile Avusturya arasındaki savaşları sona erdirmiştir. Bu antlaşma, Osmanlı açısından ilk kez karşı tarafla eşit statüde imzalanan bir diplomatik belge olmuştur. Aynı yıllarda 1603-1612 Osmanlı-Safevî Savaşı da sürmekteydi. Savaş, I. Ahmed döneminde sonuçlanmamış ancak ilerleyen yıllarda barışa ulaşılmasına zemin hazırlamıştır.\n" +
                    "\n" +
                    "I. Ahmed’in padişahlık dönemi aynı zamanda Osmanlı mimarisi açısından da önemli eserlerin ortaya çıktığı bir süreçtir. En bilinen ve kalıcı mirası, hiç kuşkusuz Sultan Ahmed Camii’dir. 1609 yılında yapımına başlanan bu cami, 1616 yılında tamamlanmıştır. Avrupa’da \"Blue Mosque (Mavi Camii)\" olarak bilinen yapı, zarif mimarisi, altı minaresi ve geniş avlusu ile Osmanlı klasik mimarisinin en görkemli örneklerinden biridir.\n" +
                    "\n" +
                    "Dindar bir kişiliğe sahip olan I. Ahmed, dini ilimlere ve alimlere büyük değer vermiştir. Aynı zamanda edebiyata ve şiire ilgi duyan bir padişahtı; \"Bahti\" mahlasıyla şiirler yazmıştır. Sarayda Kösem Sultan’ın etkisi büyüktü ve bu durum ilerleyen yıllarda Kadınlar Saltanatı'nın güçlenmesine temel hazırlamıştır.\n" +
                    "\n" +
                    "I. Ahmed, genç yaşta tahta çıkmasına rağmen devleti dirayetle yönetmiş, köklü değişimlere zemin hazırlamış ve saraydaki taht geçiş sistemine yeni bir anlayış kazandırmıştır. Ancak sağlığı hiçbir zaman çok güçlü olmamış, sık sık hastalanmıştır. 22 Kasım 1617 tarihinde, henüz 27 yaşındayken İstanbul’da vefat etmiş ve kendi yaptırdığı Sultan Ahmed Camii’nin türbesine defnedilmiştir.\n" +
                    "\n" +
                    "Yerine, kardeşi olan ve onun sağ bıraktığı I. Mustafa tahta çıkarılmıştır. Böylece ilk kez bir padişahın oğlu değil, kardeşi tahta geçmiş ve yeni sistem fiilen uygulanmıştır.\n" +
                    "\n")
        val birinciMustafa= PadisahData(id = 18,
            isim = "1.Mustafa",
            dogum = 1591,
            olum = 1639,
            saltanatSuresi = 1,
            donem = "",
            lakap = "Deli",
            cocuklari = "Çocuğu olmamıştır.",
            savaslari = "Savaş bulunmamaktadır.",
            gorsel = R.drawable.birinci_mustafa,
            biyografi = "I. Mustafa, 1591 yılında Manisa’da doğmuştur. Babası III. Mehmed, annesi ise adı kesin olarak bilinmemekle birlikte \"Halime Hatun\" olduğu kabul edilir. Ağabeyi I. Ahmed’in padişah olması üzerine, kardeş katli uygulanmamış ve hayatta bırakılmıştır. Bu durum, Osmanlı tarihinde bir ilki oluşturmuş; tahta geçişteki mutlak kardeş katli anlayışı terk edilmiş, yerine \"ekberiyet\" yani yaşça en büyük hanedan üyesinin tahta çıkması uygulaması fiilen başlamıştır.\n" +
                    "\n" +
                    "I. Mustafa, I. Ahmed’in 1617’de vefatının ardından, yaşça en büyük hanedan üyesi olarak Osmanlı tahtına çıkarılmıştır. Ancak zihinsel dengesizlikleri ve psikolojik rahatsızlıkları nedeniyle tahta çıkışı büyük tartışmalara yol açmıştır. Tarihçiler, onun çocukluğundan itibaren uzun süre kafes hayatı yaşamasının bu durumun oluşmasında etkili olduğunu belirtir. Kafeste geçen yıllar, ruhsal sağlığı üzerinde derin izler bırakmıştır.\n" +
                    "\n" +
                    "Saltanatı boyunca devlet yönetimine etkin bir şekilde katılamamış, kararları saraydaki devlet adamları ve özellikle annesi Halime Sultan ile şeyhülislamlar aracılığıyla yönlendirilmiştir. Onun döneminde özellikle veziriazam Köprülü Mehmed Paşa’nın öncüllerinden sayılabilecek Davud Paşa gibi tecrübeli yöneticiler, devletin idaresini üstlenmiştir.\n" +
                    "\n" +
                    "İlk saltanatı yalnızca 3 ay (1617–1618) sürmüştür. Zihinsel yeterliliğinin yetersiz olduğu düşünülerek tahttan indirilmiş ve yerine I. Ahmed’in oğlu II. Osman (Genç Osman) padişah yapılmıştır. Ancak II. Osman’ın 1622’de yeniçeriler tarafından tahttan indirilip öldürülmesinden sonra, I. Mustafa ikinci kez padişah yapılmıştır. Bu da onu iki kez tahta çıkan nadir Osmanlı padişahlarından biri yapmıştır.\n" +
                    "\n" +
                    "İkinci saltanatı (1622–1623) de oldukça çalkantılı geçmiştir. Yeniçeriler arasında disiplinsizlik, isyanlar ve saray içi çekişmeler artmıştır. I. Mustafa, yine etkin bir yönetim sergileyememiş, kararları genellikle vezirler, saray kadınları ve şeyhülislamlar tarafından alınmıştır. Bu durum, Osmanlı devlet yapısının merkezî gücünde ciddi bir zayıflamaya yol açmıştır.\n" +
                    "\n" +
                    "1623 yılında, devletin ileri gelenleri ve şeyhülislamın da etkisiyle tahttan ikinci kez indirildi. Yerine I. Ahmed’in bir diğer oğlu olan IV. Murad tahta çıkarıldı. I. Mustafa ise hayatının geri kalanını tekrar sarayda, gözaltı ve gözetim altında geçirdi.\n" +
                    "\n" +
                    "20 Ocak 1639 tarihinde İstanbul’da vefat etti. Ayasofya Camii avlusundaki Sultan türbelerinden birine, ağabeyi I. Ahmed’in yanına defnedildi.")
        val ikinciOsman= PadisahData(id = 19,
            isim = "2.Osman",
            dogum = 1604,
            olum = 1622,
            saltanatSuresi = 4,
            donem = "",
            gorsel = R.drawable.ikinci_osman,
            lakap = "Genç,Şehit,Farisi",
            cocuklari = "Şehzade Ömer,Şehzade Mustafa,Zeynep Sultan",
            savaslari = "İtalya ve Akdeniz Seferi,Hotin Seferi,",
            biyografi = "II. Osman, halk arasında bilinen adıyla Genç Osman, 3 Kasım 1604 tarihinde İstanbul'da doğmuştur. Babası I. Ahmed, annesi ise Mahfiruz Hatun’dur. Henüz 14 yaşındayken, amcası I. Mustafa’nın tahttan indirilmesiyle 1618 yılında Osmanlı tahtına çıkmıştır. Tahta çıktığında çok genç olmasına rağmen, zekâsı, kararlılığı ve reformist düşünceleriyle Osmanlı tarihinde dikkat çeken bir padişah olmuştur.\n" +
                    "\n" +
                    "Genç Osman, iyi eğitim almış, Arapça, Farsça, Latince ve Yunanca gibi dilleri öğrenmiştir. Avrupa'yı ve reform hareketlerini yakından takip etmiş, Osmanlı Devleti'ni çağın gereklerine uygun olarak yeniden şekillendirmeyi hedeflemiştir. Ancak bu reformcu tutumu, özellikle saray çevresi ve Yeniçeri Ocağı tarafından hoş karşılanmamış, ilerideki dramatik sonunun da temelini oluşturmuştur.\n" +
                    "\n" +
                    "Saltanatı sırasında Osmanlı Devleti, doğuda Safevîler, batıda ise Lehistan (Polonya) ile mücadele halindeydi. 1621 yılında Hotin Seferi’ne bizzat komuta ederek katılmış, ancak Yeniçeri ordusunun disiplinsizliği ve isteksizliği yüzünden Hotin Kalesi alınamamış, sefer başarısız sonuçlanmıştır. Bu olay, II. Osman’ın gözünde Yeniçeri Ocağı’nın ne kadar işlevsiz hale geldiğini açıkça göstermiştir.\n" +
                    "\n" +
                    "Sefer dönüşünde, devletin yeniden güçlenmesi için köklü değişimlere ihtiyaç olduğunu düşünen Genç Osman, Yeniçeri Ocağı'nı kaldırmak, devlet yönetiminde köklü reformlar yapmak, sarayı sadeleştirmek ve eğitim sistemini yeniden düzenlemek gibi planlar yapmıştır. Ayrıca devşirme sistemini kaldırarak Anadolu Türk gençlerinden yeni bir ordu kurmayı da düşünmüştür. Ancak bu düşüncelerini saray içindeki danışmanlarıyla paylaşması, planlarının yayılmasına ve Yeniçeriler arasında büyük bir tepki oluşmasına neden olmuştur.\n" +
                    "\n" +
                    "II. Osman, reformları hayata geçiremeden önce saray içindeki disiplini sağlamak amacıyla bazı yetkilileri görevden almış ve çevresini değiştirmeye çalışmıştır. Ancak bu hamleler yeterli olmadı. 1622 yılında Yeniçeriler ayaklandı ve kısa sürede isyan büyüyerek saraya ulaştı. Sadrazam Davud Paşa görevden alındı, padişah saraydan çıkarılarak isyancıların eline verildi.\n" +
                    "\n" +
                    "Genç Osman, isyancılar tarafından Yedikule Zindanları’na hapsedildi ve kısa bir süre sonra, 20 Mayıs 1622 tarihinde, henüz 18 yaşındayken boğularak öldürüldü. Bu olay, Osmanlı tarihindeki ilk padişah öldürülmesi vakası olarak tarihe geçti. Ölümü, sadece bir padişahın değil, aynı zamanda genç ve umut vadeden bir reformcunun da trajik sonunu simgeledi.\n" +
                    "\n" +
                    "Cenazesi, Sultanahmet Camii yakınlarındaki I. Ahmed Türbesi’ne, babasının yanına defnedilmiştir.\n" +
                    "\n")
        val dorduncuMurad= PadisahData(
            id = 20,
            isim = "4.Murat",
            dogum = 1612,
            olum = 1640,
            saltanatSuresi = 16,
            donem = "",
            lakap = "Bağdat Fatihi,Sahib-i Kıran,Muradi,Gazi",
            gorsel = R.drawable.dorduncu_murad,
            savaslari = "İç İsyanların Bastırılması,Revan Seferi,Bağdat Seferi,Akdeniz Seferleri",
            cocuklari = "Şehzade Ahmed,Şehzade Numan,Şehzade Orhan,Şehzade Hasan,Şehzade Süleyman,Şehzade Mehmed,Ayşe Sultan,Kaya Sultan,Gevherhan Sultan,Ismihan Sultan,Safiye Sultan,Fatma Sultan,Rabia Sultan",
            biyografi ="IV. Murad, 27 Temmuz 1612 tarihinde İstanbul’da doğmuştur. Babası I. Ahmed, annesi ise Osmanlı tarihinin en etkili kadınlarından biri olarak bilinen Kösem Sultan’dır. Ağabeyi Genç Osman’ın tahttan indirilip öldürülmesi, ardından amcası I. Mustafa’nın kısa süreli saltanatı sonrası, 1623 yılında henüz 11 yaşındayken Osmanlı tahtına çıkarılmıştır. Küçük yaşta tahta geçtiği için ilk yıllarda yönetim annesi Kösem Sultan ve saray ileri gelenlerinin etkisindedir.\n" +
                    "\n" +
                    "Saltanatının ilk dönemleri iç karışıklıklarla doludur. Yeniçerilerin disiplinsizliği, Celâlî isyanlarının yeniden alevlenmesi, ekonomik bozulma, vergilerin düzensiz toplanması ve devlet otoritesinin ciddi şekilde zayıflaması, bu dönemi tam bir kargaşa çağına çevirmiştir. Ayrıca İran ile süren savaşlar, Osmanlı'nın doğu sınırlarında da ciddi baskı oluşturmaktaydı.\n" +
                    "\n" +
                    "IV. Murad, 17 yaşlarına geldiğinde devletin kontrolünü fiilen eline almış ve çok sert bir disiplinle yönetime damgasını vurmaya başlamıştır. Kısa sürede sarayı, orduyu ve bürokrasiyi yeniden şekillendirmiş, birçok isyanı bastırmıştır. Özellikle alkol, tütün ve gece sokağa çıkma yasaklarını çok sıkı bir şekilde uygulamış, bizzat tebdil-i kıyafetle (kılık değiştirerek) İstanbul sokaklarında gezerek yasağa uymayanları tespit etmiş ve sert şekilde cezalandırmıştır. Bu yönüyle en otoriter Osmanlı padişahlarından biri olarak anılır.\n" +
                    "\n" +
                    "İçki yasağını uygularken, kendisinin de içki kullandığı bilinmektedir. Ancak bunu saray içinde sınırlandırmış, halka örnek teşkil etmemesi için dışa kapalı tutmuştur. Uyguladığı yasaklar ve cezalar o kadar serttir ki, bazen ölümle sonuçlanan uygulamalar da olmuştur. Korkulan ama saygı duyulan bir hükümdar haline gelmiştir.\n" +
                    "\n" +
                    "IV. Murad’ın en önemli seferlerinden biri, 1635 yılında İran üzerine düzenlediği Revan Seferidir. Bu seferde Revan (Erivan) Kalesi alınmıştır, ancak kalıcı bir başarı sağlanamamıştır. Ardından 1638 yılında düzenlediği Bağdat Seferi, Osmanlı-İran savaşlarının kaderini belirlemiştir. Bu seferde Bağdat Osmanlı topraklarına katılmış, 1639 yılında imzalanan Kasr-ı Şirin Antlaşması ile bugünkü Türkiye-İran sınırı büyük ölçüde netleşmiştir ve günümüze kadar gelen kalıcılıkla kabul görmüştür.\n" +
                    "\n" +
                    "Bu sefer, IV. Murad’ın bizzat komuta ettiği son büyük askerî zafer olarak Osmanlı tarihinde önemli bir yer tutar. Seferlerindeki kararlılığı, ordudaki disiplini ve merkezi otoriteyi güçlendirme çabaları, onun kısa süren ama etkili saltanatının başlıca başarıları arasında yer alır.\n" +
                    "\n" +
                    "Ancak kişisel hayatı, bu sert ve disiplinli padişahın arka planında duygusal ve zaman zaman dengesiz bir yön barındırır. İçkiye olan düşkünlüğü, sinirli yapısı ve çevresine karşı ani öfke patlamaları, ilerleyen yaşlarında hem sağlığını hem de ruhsal dengesini olumsuz etkilemiştir.\n" +
                    "\n" +
                    "9 Şubat 1640 tarihinde, henüz 27 yaşındayken İstanbul’da vefat etmiştir. Ölüm nedeni büyük ihtimalle siroz ve alkole bağlı iç organ yetmezliği olarak kabul edilir. Sultanahmet Camii’nin avlusundaki I. Ahmed Türbesi’ne, babasının yanına defnedilmiştir.")
        val ibrahim= PadisahData(id = 21,
            isim = "İbrahim",
            dogum = 1615,
            olum = 1648,
            saltanatSuresi = 8,
            donem = "",
            gorsel = R.drawable.ibrahim,
            lakap = "Girit Fatihi,Asabi,Şehit",
            cocuklari = "IV. Mehmed,Şehzade Süleyman (II. Süleyman),Şehzade Ahmed (II. Ahmed),Şehzade Orhan,Şehzade Selim,Şehzade Murad,Şehzade Hasan,Fatma Sultan,Gevherhan Sultan,Ayşe Sultan,Ümmügülsüm Sultan,Bican Sultan,Atike Sultan,Kaya Sultan",
            savaslari = "Girit Kuşatması'nın Başlaması ,İç Karışıklıklar ve İsyanlar",
            biyografi = "I. İbrahim, 5 Kasım 1615 tarihinde İstanbul’da doğmuştur. Babası I. Ahmed, annesi ise Kösem Sultan’dır. Ağabeyi IV. Murad’ın uzun ve otoriter saltanatı sırasında “kafes”te tutulmuş, yani sarayın kapalı bir bölümünde dış dünya ile teması olmadan yaşamıştır. Bu izolasyon, onun ruhsal sağlığı üzerinde derin etkiler bırakmış ve ilerideki davranışlarını belirleyen önemli bir faktör olmuştur.\n" +
                    "\n" +
                    "IV. Murad, ömrü boyunca erkek kardeşlerini öldürtmüş, İbrahim’i ise uzun süre öldürmeyi ertelemiştir. Ancak ölüm döşeğindeyken onu öldürtmedi ve böylece IV. Murad’ın 1640’taki vefatının ardından, I. İbrahim Osmanlı tahtına çıktı. Tahta çıktığında, halk ve saray çevresi onun akli dengesinden şüphe etmekteydi. Hatta ilk başta “padişah oldu ama aklı yerinde değil” söylentileri yayılmış, kendisi de bir süre tahta geçmeye direnir gibi davranmıştır.\n" +
                    "\n" +
                    "Saltanatı süresince Osmanlı Devleti dışarıdan değil, içerden zayıflamaya devam etmiştir. İbrahim, devlet işlerinden büyük oranda uzak durmuş; yönetim genellikle annesinin (Kösem Sultan), saraya yakın devlet adamlarının ve harem kadınlarının kontrolünde yürütülmüştür. Özellikle sadrazam Kara Mustafa Paşa ve sonrasında Cinci Hoca gibi kişilere duyduğu güven, saray içi yozlaşmayı ve rüşvet mekanizmasını güçlendirmiştir.\n" +
                    "\n" +
                    "En dikkat çeken gelişmelerden biri, ekonomik çöküş ve saray israfının artmasıdır. İbrahim'in aşırı lüks düşkünlüğü, değerli kürklere, mücevherlere ve saray eğlencelerine büyük paralar harcaması, hazineyi zor duruma sokmuştur. Aynı zamanda yeniçeri ocağı ve halk üzerindeki baskılar artmış, vergiler halkı bezdirmiştir.\n" +
                    "\n" +
                    "Haremdeki kadınlara olan düşkünlüğüyle de tanınan I. İbrahim, tarihe \"Harem Düşkünü\" veya \"Deli İbrahim\" lakaplarıyla geçmiştir. Haremde geçirdiği zamanların etkisiyle kararları dengesizleşmiş, bazı kadınlara olağanüstü yetkiler vermesi, devletin düzenini sarstığı gibi, halkın güvenini de zedelemiştir. Özellikle Şekerpare Hatun ve diğer hasekilerin etkisi, saray siyasetini doğrudan etkilemiştir.\n" +
                    "\n" +
                    "1644 yılında, dönemin önemli dış politik olaylarından biri olan Girit Seferi başlamıştır. Venediklilerle yaşanan deniz savaşları, Girit’in fethi için Osmanlı ordusunu yormuş, ancak bu savaşın sonuçları I. İbrahim’in ölümünden sonra ortaya çıkacaktır. Yani bu sefer onun döneminde başlamış ama tamamlanamamıştır.\n" +
                    "\n" +
                    "Devlet içindeki kargaşa, ekonomik kriz ve halkın huzursuzluğu giderek büyüyünce, saraydaki yüksek rütbeli isimler, ulema ve yeniçerilerin de desteğiyle 1648 yılında I. İbrahim tahttan indirildi. Yerine, oğlu IV. Mehmed geçirildi. İbrahim önce Topkapı Sarayı’na hapsedildi, ardından kısa bir süre sonra, 8 Ağustos 1648’de, boğdurularak öldürüldü. Böylece Osmanlı tarihinde tahttan indirildikten sonra öldürülen ikinci padişah oldu (ilki Genç Osman’dı). Cenazesi Ayasofya Camii avlusundaki Sultan türbelerine defnedildi.")
        val dorduncuMehmed= PadisahData(id = 22,
            isim = "4.Mehmed",
            dogum = 1642,
            olum = 1693,
            saltanatSuresi = 39,
            gorsel = R.drawable.dorduncu_mehmed,
            lakap = "Avcı",
            donem = "",
            cocuklari = "2.Mustafa,3.Ahmed,Şehzade Selim,Şehzade Mahmud,Şehzade İbrahim,Şehzade Bayezid,Şehzaden Süleyman,Hatice Sultan,Ümmi Sultan,Ümmü Gülsüm Sultan,Gevher Sultan,Safiye Sultan,Fatma Sultan",
            savaslari = "Girit Savaşı'nın Devamı ve Kandiye'nin Fethi,Osmanlı-Lehistan Savaşları,II. Viyana Kuşatması,Kutsal İttifak Savaşları",
            biyografi = "IV. Mehmed, 2 Ocak 1642 tarihinde İstanbul'da doğmuştur. Babası I. İbrahim, annesi ise Osmanlı tarihinin en etkili kadın figürlerinden biri olan Turhan Hatice Sultan’dır. Babasının 1648 yılında tahttan indirilip öldürülmesi üzerine, henüz 6 yaşındayken Osmanlı tahtına çıkarılmıştır. Bu durum, Osmanlı tarihinde en küçük yaşta tahta çıkan padişahlardan biri olması açısından önemlidir.\n" +
                    "\n" +
                    "Küçük yaşta tahta çıktığı için devletin yönetimi, ilk yıllarda annesi Valide Turhan Sultan, şeyhülislamlar ve vezir-i azamların elindeydi. Bu dönemde saray kadınlarının etkisi büyüktü; Kadınlar Saltanatı olarak bilinen dönemin son evresi, IV. Mehmed’in ilk yıllarına denk gelir. Özellikle Köprülü Mehmed Paşa'nın 1656’da sadrazam olması, hem Kadınlar Saltanatı’nın sona ermesini sağlamış hem de Osmanlı’yı ciddi bir toparlanma sürecine sokmuştur.\n" +
                    "\n" +
                    "Köprülü Mehmed Paşa, padişahtan tam yetki alarak devleti güçlü bir disiplinle yeniden düzenlemiş, yolsuzluklarla mücadele etmiş ve orduyu yeniden yapılandırmıştır. Onun ölümünden sonra oğlu Köprülü Fazıl Ahmed Paşa görevi devralmış ve Osmanlı, bu baba-oğul sadrazamlar sayesinde bir süreliğine eski kudretine geri dönmüştür.\n" +
                    "\n" +
                    "IV. Mehmed’in saltanatının önemli dış olaylarından biri 1664’te Avusturya ile yapılan savaşlar ve Vasvar Antlaşmasıdır. Bu antlaşmayla Osmanlı Devleti, Avusturya karşısında toprak kaybı yaşamadan prestijini korumayı başarmıştır. Ancak asıl büyük olay, 1672–1676 Osmanlı-Lehistan Savaşı ve Bucaş Antlaşmasıdır. Bu antlaşma ile Podolya ve Kamaniçe Osmanlı topraklarına katılmıştır. Bucaş, Osmanlı’nın batıda ulaştığı en geniş sınırları göstermesi açısından tarihî öneme sahiptir.\n" +
                    "\n" +
                    "IV. Mehmed, aynı zamanda bir av tutkunu olmasıyla tanınır. Bu nedenle halk arasında ve tarih kitaplarında “Avcı Mehmed” olarak anılmıştır. Hatta zamanının önemli bir bölümünü Edirne ve çevresinde ava çıkarak geçirmiştir. Bu tutkusu, zamanla devlet işlerinden uzaklaşmasına neden olmuş ve yönetim üzerindeki etkisi azalmıştır.\n" +
                    "\n" +
                    "1683 yılında Osmanlı, Sadrazam Merzifonlu Kara Mustafa Paşa komutasında Viyana’yı kuşatmıştır. Ancak bu kuşatma başarısızlıkla sonuçlanmış ve Kutsal İttifak denilen Avrupa koalisyonunun Osmanlı’ya karşı birleşmesine yol açmıştır. Bu olay, Osmanlı’nın Avrupa’daki ilerleyişinin sona erdiği tarihî bir kırılma noktasıdır.\n" +
                    "\n" +
                    "Viyana bozgununun ardından Osmanlı orduları geri çekilmek zorunda kalmış, ardından Macaristan ve Balkanlar’da önemli toprak kayıpları yaşanmıştır. Bu gelişmeler, IV. Mehmed’e olan güvenin saray ve ordu nezdinde sarsılmasına yol açmıştır. Nihayetinde, 1687 yılında bir yeniçeri isyanı sonucu tahttan indirildi. Yerine kardeşi II. Süleyman geçirildi.\n" +
                    "\n" +
                    "Tahttan indirildikten sonra Topkapı Sarayı’nda göz hapsine alınan IV. Mehmed, yaklaşık 5 yıl boyunca sessiz bir yaşam sürdü. 6 Ocak 1693 tarihinde İstanbul’da vefat etti. Cenazesi, Yeni Camii (Eminönü)’ndeki Sultan IV. Mehmed Türbesi’ne defnedildi.")
        val ikinciSuleyman= PadisahData(id = 23,
            isim = "2.Süleyman",
            dogum = 1642,
            olum = 1691,
            saltanatSuresi = 3,
            donem = "",
            gorsel = R.drawable.ikinci_suleyman,
            lakap = "Süleyman-i Sani,Gazi",
            savaslari = "Kutsal İttifak Savaşları,Belgrad'ın Kaybedilmesi,Niş'in Kaybedilmesi,Belgrad'ın Geri Alınması,Salankamen Muharebesi",
            cocuklari = "Çocuğu olmamıştır.",
            biyografi = "II. Süleyman, 15 Nisan 1642 tarihinde İstanbul'da doğmuştur. Babası I. İbrahim, annesi ise Saliha Dilaşub Sultan’dır. Ağabeyi IV. Mehmed’in uzun süren saltanatı boyunca kafeste, yani sarayın hapsedilmiş bölümünde tutulmuştur. Bu kafes hayatı, II. Süleyman’ın yaklaşık 40 yılı aşkın bir süre dış dünya ile neredeyse hiç temas kurmadan yaşamasına sebep olmuştur. Eğitimli olsa da, siyasal ve askerî tecrübesi yok denecek kadar azdı.\n" +
                    "\n" +
                    "IV. Mehmed’in 1687’de Viyana bozgununun ardından çıkan isyanla tahttan indirilmesi üzerine, 45 yaşında Osmanlı tahtına çıkmıştır. Bu yaştan önce hiçbir idarî görevi olmaması, onun padişahlığa hazır olmadığı yönünde tartışmalara neden olmuştur. Ancak dönemin şartlarında tahta geçecek uygun başka hanedan üyesi olmaması nedeniyle tercih edilmiştir.\n" +
                    "\n" +
                    "Tahta çıktığında Osmanlı Devleti çok zor bir dönemden geçmekteydi. Kutsal İttifak adı verilen Habsburglar, Lehistan, Venedik ve Rusya’dan oluşan büyük bir Avrupa koalisyonu, Osmanlı’ya karşı birleşmişti. Balkanlar, Macaristan, Dalmaçya ve Mora’da Osmanlı ordusu geri çekilmekte, iç karışıklıklar ve ekonomik krizler artmaktaydı. II. Süleyman bu dönemde etkin bir yönetim sergilemekten ziyade, devleti güçlü sadrazamlarına emanet etmiş bir padişah olmuştur.\n" +
                    "\n" +
                    "Saltanatının en dikkat çekici yönü, veziriazam olarak atadığı Köprülü Fazıl Mustafa Paşa ile başlamıştır. 1689 yılında sadrazam olan Fazıl Mustafa Paşa, devleti toparlamak için disiplinli, kararlı ve reformcu bir yönetim ortaya koymuştur. Yeniçeri Ocağı’na düzen getirmiş, orduyu tekrar sefer için hazır hale getirmiş ve halk üzerindeki vergi baskısını bir nebze azaltmıştır. Bu dönemde Osmanlı ordusu Niş, Vidin ve Tımışvar gibi bazı bölgeleri yeniden ele geçirmiştir.\n" +
                    "\n" +
                    "II. Süleyman, içki ve eğlenceye düşkün olmamış, dindar ve sade bir padişah olarak tanınmıştır. Saltanatı boyunca sarayda huzur bozulmamış, ancak aktif bir liderlik sergilememiştir. Dönemin zor şartlarında devleti Köprülüler gibi tecrübeli devlet adamlarına emanet etmesi, onun en doğru kararlarından biri olarak değerlendirilir.\n" +
                    "\n" +
                    "22 Haziran 1691 tarihinde, Edirne’de 63 yaşında vefat etmiştir. Ölüm nedeni doğal nedenlere dayansa da, bazı kaynaklar uzun yıllar kafeste yaşamasının fiziksel ve ruhsal sağlığını olumsuz etkilediğini vurgular. Cenazesi, İstanbul’da Süleymaniye Camii avlusundaki Kanuni Sultan Süleyman Türbesi’ne, dedesinin yanına defnedilmiştir.\n" +
                    "\n" +
                    "Yerine, kardeşi II. Ahmed tahta geçmiştir.\n" +
                    "\n")
        val ikinciAhmed= PadisahData(id = 24,
            isim = "2.Ahmed",
            dogum = 1643,
            olum = 1695,
            saltanatSuresi = 4,
            donem = "",
            gorsel = R.drawable.ikinci_ahmed,
            lakap = "Ahmed-i Sani,Gazi",
            cocuklari = "Şehzade İbrahim,Şehzade Selim,Atike Sultan,Hatice Sultan,Asiye Sultan",
            savaslari = "Haçova Muharebesi,Zenta Savaşı",
            biyografi = "II. Ahmed, 25 Şubat 1643 tarihinde İstanbul’da doğmuştur. Babası I. İbrahim, annesi ise Hatice Muazzez Sultan’dır. Ağabeyleri IV. Mehmed ve II. Süleyman gibi o da uzun yıllar boyunca sarayda “kafes hayatı” yaşamıştır. Devlet işlerinden uzak, gözlemci konumda geçen bu uzun dönem, onun karakterini etkileyen temel unsurlardan biri olmuştur. 1691 yılında II. Süleyman’ın ölümü üzerine, 48 yaşında Osmanlı tahtına çıkmıştır.\n" +
                    "\n" +
                    "Tahta geçtiğinde Osmanlı Devleti hâlâ Kutsal İttifak Savaşları içinde çalkantılı bir dönemden geçmekteydi. Avusturya, Venedik, Lehistan ve Rusya’dan oluşan bu ittifaka karşı Osmanlı, Balkanlar, Macaristan, Mora ve Tuna boylarında toprak kayıpları yaşamaktaydı. Bu süreçte Osmanlı ordusunun başında çok önemli bir isim vardı: Köprülü Fazıl Mustafa Paşa. II. Ahmed de tıpkı ağabeyi II. Süleyman gibi, devleti yetenekli bir sadrazama emanet etmenin en akıllıca çözüm olduğunu görmüştü.\n" +
                    "\n" +
                    "Köprülü Fazıl Mustafa Paşa, II. Ahmed’in ilk yıllarında devlet yönetimini sıkı disiplinle sürdürmüş, yeniçeri düzeni, eyalet vergileri ve ordu lojistiğinde önemli düzenlemeler yapmıştır. 1691’deki Salankamen Savaşı sırasında Avusturyalılarla yapılan çarpışmalarda başarılı olunsa da, Fazıl Mustafa Paşa bu savaşta şehit düşmüştür. Bu kayıp, Osmanlı yönetimi için büyük bir darbe olmuştur.\n" +
                    "\n" +
                    "Sadrazamın ölümüyle birlikte Osmanlı Devleti yeniden iç ve dış baskılarla baş başa kalmıştır. II. Ahmed, zeki ve iyi niyetli bir padişah olmasına rağmen, etkin bir liderlik ortaya koyamamış, hem saray entrikaları hem de cephe sorunları karşısında zayıf kalmıştır. Onun döneminde de önemli toprak kayıpları yaşanmış, özellikle Balkan coğrafyasında Avusturya üstünlüğü pekişmiştir. Buna rağmen padişah, devletin dini yapısına, vakıflara ve medrese eğitimine önem vermiş, dindar yapısı ile halktan saygı görmüştür.\n" +
                    "\n" +
                    "II. Ahmed aynı zamanda musikiye ve edebiyata ilgi duyan bir padişahtı. Döneminde saray çevresinde ilim ve sanatla uğraşan kişilere destek olmuş, geleneksel Osmanlı kültürünü yaşatma çabasına girmiştir. Ancak kısa süren saltanatı, bu alanlarda da büyük bir iz bırakmasına engel olmuştur.\n" +
                    "\n" +
                    "6 Şubat 1695 tarihinde, 52 yaşındayken Edirne’de vefat etmiştir. Cenazesi İstanbul’a getirilmiş ve Kanuni Sultan Süleyman Türbesi’nin hazîresine, dedesi I. Süleyman’ın yakınına defnedilmiştir. Yerine, yeğeni II. Mustafa Osmanlı tahtına geçmiştir.\n" +
                    "\n")
        val ikinciMustafa= PadisahData(id = 25,
            isim = "2.Mustafa",
            dogum = 1664,
            olum = 1703,
            saltanatSuresi = 8,
            donem = "",
            gorsel = R.drawable.ikinci_mustafa,
            lakap = "Mustafa-yi Sani,İkbali,Gazi",
            cocuklari = "1.Mahmud,Şehzade Mehmed,Şehzade Selim,Şehzade Murad,Şehzade Ahmed,3.Osman,Şehzade Hasan,Şehzade Hüseyin,Şehzade Ahmed,Atike Sultan,Büyük Ayşe Sultan,Emine Sultan,Safiye Sultan,Büyük Rukiye Sultan,Zahide Sultan,Hatice Sultan,Fatma Sultan,Esma Sultan,Küçük Rukiye Sultan,Ümmügülsüm Sultan,Zeynep Sultan,Emetullah Sultan,Küçük Ayşe Sultan",
            savaslari = "Lugos Muharebesi,Olaş Muharebesi,Azak Kalesi'nin Kaybı,Zenta Muharebesi",
            biyografi = "II. Mustafa, 6 Şubat 1664 tarihinde Edirne’de doğmuştur. Babası IV. Mehmed, annesi ise Emetullah Rabia Gülnuş Sultan’dır. İyi bir eğitim almış, Arapça ve Farsça öğrenmiş; dinî ilimlere, şiire ve tarih bilgisine meraklı bir padişah olarak yetişmiştir. Amcası II. Ahmed’in 1695 yılında ölümü üzerine, tahta geçtiğinde 31 yaşındaydı.\n" +
                    "\n" +
                    "II. Mustafa’nın tahta çıkışı, Osmanlı’nın Kutsal İttifak Savaşları dönemine denk gelmişti. Avusturya, Venedik, Lehistan ve Rusya gibi devletlerle savaşlar sürüyor, Osmanlı toprak kaybetmeye devam ediyordu. II. Mustafa, Osmanlı tarihinde bir kırılma olan bu dönemde, son kez sefere çıkan padişah olmuştur. İlk olarak 1695 yılında Eğri Seferi'ne, ardından 1696 ve 1697’de Avusturya üzerine seferlere çıkmıştır.\n" +
                    "\n" +
                    "Bu seferlerde ilk başlarda bazı başarılar elde edilse de, en kritik dönüm noktası 1697 Zenta Savaşı olmuştur. Bu savaşta, Sadrazam Elmas Mehmed Paşa'nın komutasındaki Osmanlı ordusu, Avusturyalı Prens Eugene tarafından Tisa Nehri geçilirken baskına uğramış, Osmanlı büyük bir bozguna uğramış ve Sadrazam şehit düşmüştür. Zenta bozgunu, Osmanlı’nın Batı’daki üstünlüğünün fiilen sona erdiği an olarak kabul edilir.\n" +
                    "\n" +
                    "Bu yenilgiden sonra Osmanlı Devleti, savaşlara devam edecek ekonomik ve askerî gücü kendinde bulamamış ve 1699 yılında Karlofça Antlaşması imzalanmıştır. Bu antlaşmayla Osmanlı, Tarihinde ilk kez büyük çaplı toprak kayıplarını resmen kabul etmiş; Macaristan, Podolya, Ukrayna, Azak Kalesi ve Dalmaçya gibi birçok bölge elden çıkmıştır. Bu gelişme, Osmanlı’nın Avrupa’da savunmaya çekildiği ve gerileme döneminin başladığı nokta olarak değerlendirilir.\n" +
                    "\n" +
                    "II. Mustafa bu gelişmelere rağmen, iç yönetimde bazı reform denemelerinde bulunmuştur. Özellikle iltizam (vergi toplama) sistemini kaldırıp doğrudan hazineden maaş verilmesi gibi girişimlerle mali yapıyı düzene sokmak istemiştir. Ayrıca kadrolu subaylık sistemi getirmeye çalışmış, ancak bu yenilikler yeniçeriler ve saraydaki geleneksel gruplar tarafından hoş karşılanmamıştır.\n" +
                    "\n" +
                    "Saray ve ordu içindeki huzursuzluklar giderek artmış, özellikle Yeniçeri Ocağı’nın etkisi ve sadrazamlar üzerindeki baskısı II. Mustafa’yı yönetimde etkisiz hale getirmiştir. Nihayetinde 22 Ağustos 1703 tarihinde, Edirne Vakası olarak bilinen isyan sonucu tahttan indirilmiş; yerine kardeşi III. Ahmed geçirilmiştir.\n" +
                    "\n" +
                    "Tahttan indirildikten sonra Topkapı Sarayı’na kapatılan II. Mustafa, geri kalan yıllarını sessizce geçirmiştir. 29 Aralık 1703 tarihinde, 39 yaşındayken İstanbul’da vefat etmiştir. Cenazesi Yeni Camii (Eminönü) türbesine defnedilmiştir.")
        val ucuncuAhmed= PadisahData(id = 26,
            isim = "3.Ahmed",
            dogum = 1673,
            olum = 1736,
            saltanatSuresi = 27,
            donem = "Lale Devri",
            gorsel = R.drawable.ucuncu_ahmed,
            lakap = "Ahmed-i Salis,Necrib,Gazi",
            cocuklari = "1.Mahmud,3.Osman,Şehzade Mehmed,Şehzade İbrahim,Şehzade Abdülhamid (1.Abdülhamid),Şehzade Süleyman,Şehzade Beyazıt,Şehzade Selim,Ayşe Sultan,Emetullah Sultan,Fatma Sultan,Zeynep Sultan,Ümmügülsüm Sultan,Esma Sultan,Zeyneb Sultan,Hatice Sultan",
            savaslari = "Prut Savaşı,Osmanlı-Venedik Savaşları,Petevaradin Savaşları,Osmanlı-İran Savaşları",
            biyografi = "III. Ahmed, 30 Aralık 1673 tarihinde Dobriç (Bulgaristan’ın bugün Deliorman olarak bilinen bölgesi)'te doğmuştur. Babası IV. Mehmed, annesi ise Emetullah Rabia Gülnuş Sultan’dır. Eğitimli, sanatla ilgilenen, şiir yazan ve entelektüel yönü güçlü bir şehzade olarak yetişmiştir. Ağabeyi II. Mustafa’nın 1703 Edirne Vakası ile tahttan indirilmesinin ardından, aynı yıl içerisinde Osmanlı tahtına geçirilmiştir. Tahta çıktığında 30 yaşındaydı.\n" +
                    "\n" +
                    "III. Ahmed’in saltanatı, Osmanlı’nın askerî gerilemesine rağmen kültürel olarak büyük bir canlanma yaşadığı, tarihî açıdan oldukça özel bir dönemdir. Bu dönem, özellikle 1718–1730 yılları arasında süren Lale Devri ile özdeşleşmiştir. Ancak bu devre geçmeden önce, padişahlığının ilk yıllarında Osmanlı hâlâ Avusturya, Venedik ve Rusya gibi devletlerle savaş hâlindeydi.\n" +
                    "\n" +
                    "1710–1711 arasında Rusya ile Prut Savaşı yapılmış, bu savaşta Sadrazam Baltacı Mehmed Paşa’nın liderliğinde Osmanlı ordusu Rus Çarı I. Petro’yu (Deli Petro) sıkıştırmış ve Prut Antlaşması ile Azak Kalesi Osmanlı’ya geri verilmiştir. Bu başarı, Osmanlı’nın Karlofça sonrası ilk diplomatik ve askerî kazancı olarak görülür.\n" +
                    "\n" +
                    "1715’te Osmanlı, Venedik’e karşı savaş açmış ve Mora yeniden Osmanlı topraklarına katılmıştır. Ancak bu gelişme Avusturya’nın müdahalesine neden olmuş, 1716–1718 Osmanlı-Avusturya Savaşı sonunda Osmanlı ordusu Petrovaradin Savaşı’nda büyük bir yenilgi almış, Sadrazam Silahdar Ali Paşa şehit düşmüştür. Bunun sonucunda 1718’de Pasarofça Antlaşması imzalanmış ve Belgrad da dâhil olmak üzere bazı Balkan toprakları Avusturya’ya bırakılmıştır.\n" +
                    "\n" +
                    "Pasarofça Antlaşması, aynı zamanda Osmanlı’nın savaşlardan uzaklaşıp kültürel, bilimsel ve estetik gelişmelere yöneldiği Lale Devri’nin başlangıcını simgeler. III. Ahmed bu dönemde, sadrazamı Nevşehirli Damat İbrahim Paşa ile birlikte başkent İstanbul’u yeniden imar ettirmiş, çeşmeler, köşkler, kütüphaneler ve matbaa gibi yapılarla kültürel kalkınmayı desteklemiştir. İbrahim Müteferrika ve Said Efendi’nin 1727’de kurduğu ilk Osmanlı matbaası, bu dönemin en önemli gelişmelerindendir.\n" +
                    "\n" +
                    "III. Ahmed aynı zamanda şiirle ilgilenen, “Necib” mahlasıyla şiirler yazan bir padişahtı. Sarayında şairlere, hattatlara ve sanatkârlara yer vermiş, barok ve rokoko etkili mimarî tarzları başlatan süslemeleri teşvik etmiştir.\n" +
                    "\n" +
                    "Ancak Lale Devri’nin artan saray masrafları, eğlencelere ve lükse olan düşkünlük, halk arasında hoşnutsuzluk yaratmıştır. Özellikle Patrona Halil adlı eski bir yeniçerinin öncülüğünde çıkan isyan, 1730 yılında Lale Devri’ni sona erdirmiştir. İsyan sonucu, III. Ahmed tahtan indirilmiş, yerine I. Mahmud geçirilmiştir.\n" +
                    "\n" +
                    "Tahttan indirildikten sonra yaklaşık 6 yıl sarayda göz hapsinde tutulan III. Ahmed, 1 Temmuz 1736 tarihinde İstanbul’da vefat etmiştir. Cenazesi, Yeni Camii avlusundaki Sultan Türbeleri’ne defnedilmiştir.")
        val birinciMahmud= PadisahData(id = 27,
            isim = "1.Mahmud",
            dogum = 1696,
            olum = 1754,
            donem = "",
            saltanatSuresi = 24,
            gorsel = R.drawable.birinci_mahmud,
            lakap = "Sebkati,Gazi",
            cocuklari = "Çocuğu olmamıştır.",
            savaslari = "Osmanlı-İran Savaşları,Osmanlı-Rus-Avusturya Savaşları,Niş Kuşatması,Groçka Meydan Muharebesi,Belgrad'ın Geri Alınması",
            biyografi = "I. Mahmud, 2 Ağustos 1696 tarihinde İstanbul’da doğmuştur. Babası II. Mustafa, annesi ise Saliha Sultan’dır. Henüz çocuk yaşta iken babası tahttan indirilmiş ve padişahlık sırası amcaları ile kuzenlerine geçmiştir. I. Mahmud, sarayda iyi bir eğitim almış; Arapça, Farsça, fıkıh ve edebiyatla ilgilenmiş; sakin, ağırbaşlı ve kültüre önem veren bir şehzade olarak yetişmiştir.\n" +
                    "\n" +
                    "1730 yılında, Patrona Halil önderliğinde gerçekleşen Lale Devri karşıtı isyan sonucu, amcası III. Ahmed tahttan indirildi ve I. Mahmud Osmanlı tahtına çıkarıldı. Tahta çıktığında 34 yaşındaydı. Ancak ilk günlerinden itibaren yönetimi tam anlamıyla eline alamadı; çünkü İstanbul’da Patrona Halil ve isyancılar saraya hâkimdi. I. Mahmud, bu karışıklık dönemini çok akıllıca yönetti. Bir yandan isyancıların taleplerini geçici olarak kabul etti, diğer yandan saray ve ulema desteğiyle hareket ederek isyancı liderleri birer birer ortadan kaldırdı. Böylece tahta çıktıktan sadece birkaç hafta sonra, devlet otoritesini yeniden sağladı ve Patrona Halil ve yandaşları idam edildi.\n" +
                    "\n" +
                    "Bu olay, hem Lale Devri’nin kesin olarak sona erdiğini hem de Osmanlı’da mutlak padişah otoritesinin yeniden kurulduğunu gösterir. I. Mahmud’un ilk başarılarından biri budur.\n" +
                    "\n" +
                    "Saltanatı boyunca hem iç karışıklıklarla mücadele etmiş hem de dış tehditlere karşı savaşlar yürütmüştür. 1736–1739 Osmanlı-Avusturya ve Osmanlı-Rus savaşlarında, Osmanlı ordusu Belgrad Seferi ile önemli başarılar elde etmiş, Sadrazam Hekimoğlu Ali Paşa ve Fransız arabuluculuğuyla 1739 Belgrad Antlaşması imzalanmıştır. Bu antlaşmayla Belgrad Osmanlı’ya bırakılmış, Rusya Karadeniz'e çıkamamış ve bu gelişme Osmanlı'nın 18. yüzyıldaki son büyük diplomatik zaferlerinden biri olmuştur.\n" +
                    "\n" +
                    "I. Mahmud aynı zamanda Nadir Şah'ın Safevî tahtına geçmesiyle İran'da yaşanan kargaşayı da fırsat bilerek doğu sınırlarında Osmanlı etkisini artırmaya çalıştı. Ancak İran’daki istikrarsızlık uzun vadede fayda sağlamadı ve doğudaki başarılar kalıcı olamadı.\n" +
                    "\n" +
                    "Dönemin bir diğer önemli olayı ise İstanbul’da çıkan büyük yangınlar, özellikle 1750 yangını oldu. Bu yangınlar sonrası şehrin yeniden imarı için çalışmalar başlatıldı. Aynı zamanda cami, medrese ve kütüphane yaptırarak şehircilik ve eğitime büyük katkılar sundu. En bilinen eserlerinden biri, Nuruosmaniye Camii’nin inşasına başlanmasıdır (ölümünden sonra tamamlanmıştır).\n" +
                    "\n" +
                    "I. Mahmud’un kişisel yönü de oldukça dikkat çekicidir. Son derece dindar, sade yaşam süren, şiirle ilgilenen, “Sebkati” mahlasıyla şiirler yazan bir padişahtı. Sanatçılara ve âlimlere değer verir, onları sarayında ağırlardı. Devlet adamlarını liyakate göre seçmeye çalışmış, sık sık sadrazam değiştirmemiştir. Bu da onun döneminde görece bir istikrar sağlamıştır.\n" +
                    "\n" +
                    "13 Aralık 1754 tarihinde, İstanbul’da vefat etti. 58 yaşında olan I. Mahmud, Yeni Camii Türbesi’ne defnedilmiştir. Yerine, kardeşi III. Osman tahta geçmiştir.")
        val ucuncuOsman= PadisahData(id = 28,
            isim = "3.Osman",
            dogum = 1699,
            olum = 1757,
            saltanatSuresi = 3,
            gorsel = R.drawable.ucuncu_osman,
            donem = "",
            lakap ="Osman-ı Salis,Sofu",
            cocuklari = "Çocuğu olmamıştır.",
            savaslari = "İran Sınırında ki Gerginlikler",
            biyografi = "III. Osman, 2 Ocak 1699 tarihinde İstanbul’da doğmuştur. Babası II. Mustafa, annesi ise Şehsuvar Valide Sultan’dır. Ağabeyi I. Mahmud’un ardından 13 Aralık 1754’te Osmanlı tahtına çıkmıştır. Tahta çıktığında 55 yaşındaydı ve uzun yıllar boyunca “kafes” hayatı yaşamıştı. Bu durum, onun hem hayata hem insanlara karşı mesafeli, kuşkucu ve içe kapanık bir karakter geliştirmesine yol açtı.\n" +
                    "\n" +
                    "Saltanatı yalnızca 3 yıl kadar sürdü (1754–1757), ancak bu kısa sürede dahi ilginç yönleriyle dikkat çekti. III. Osman, kişisel olarak sert, asabi, aksi ve mesafeli biri olarak tanınır. Saraya kadınların girmesini istemediği, kadınların ayak seslerinden rahatsız olduğu ve bu yüzden saray zeminine tahta yerine halı döşenmesini yasaklayıp taş zemine döndüğü rivayet edilir. Aynı zamanda gece geç saatlerde sarayda yalnız dolaşması ve hizmetlileri korkutması, dönem kaynaklarında anlatılan detaylar arasındadır.\n" +
                    "\n" +
                    "Devlet işlerine gelince, III. Osman işinin ehli olan devlet adamlarıyla çalışmaya gayret etti. Sadrazam olarak önce Koca Ragıp Paşa’yı göreve getirdi. Ragıp Paşa, diplomasi ve iç işlerde tecrübeli biriydi. Onun sayesinde III. Osman döneminde önemli bir kriz yaşanmadı. Ancak padişah, sadrazamların kararlarını çoğu zaman sorgular, bazen ani öfke nöbetleriyle yöneticileri görevden almak isterdi. Bu tutumu nedeniyle saray çevresinde tedirginlik yaratan bir padişah portresi çizdi.\n" +
                    "\n" +
                    "III. Osman döneminde büyük bir dış savaş yaşanmadı, ancak içte bazı reform girişimleri başladı. Eğitim kurumlarına ve özellikle ulema sınıfına verdiği önem bilinmektedir. Medrese düzeninin yeniden yapılandırılmasını istemiş, bazı rüşvetçi kadıların görevden alınmasını sağlamıştır.\n" +
                    "\n" +
                    "Sanata ve mimariye ilgisi az olsa da, birkaç küçük çaplı yapı çalışması desteklemiştir. Şiir yazmaz, musikiyle ilgilenmezdi. Bu da onu seleflerinden ayıran özelliklerden biridir. Dini değerlere sıkı sıkıya bağlıydı ve sık sık camilere giderek halkın arasında görünmeyi severdi.\n" +
                    "\n" +
                    "30 Ekim 1757 tarihinde, İstanbul’da vefat etti. Ölüm nedeni büyük ihtimalle yaşlılık ve iç hastalıklar olarak belirtilmiştir. Cenazesi, Yeni Camii Türbesi’ne defnedildi. Yerine, kuzeni olan III. Mustafa Osmanlı tahtına çıkmıştır.")
        val ucuncuMustafa= PadisahData(id = 29,
            isim = "3.Mustafa",
            dogum = 1717,
            olum = 1774,
            saltanatSuresi = 16,
            gorsel = R.drawable.ucuncu_mustafa,
            donem = "",
            lakap = "Musafa-i Salis,Cihangir,İlk Yenilikçi,Gazi",
            cocuklari = "3.Selim,Şehzade Mehmed,Hatice Sultan,Beyhan Sultan,Şah Sultan,Fatma Sultan,Hibetullah Sultan,Mihrimah Sultan,Mihrişah Sultan",
            savaslari = "Osmanlı-Rus Savaşı,Çeşme Vakası,Mora Seferi",
            biyografi = "III. Mustafa, 28 Ocak 1717 tarihinde İstanbul’da doğmuştur. Babası III. Ahmed, annesi Mihrişah Kadın’dır. Lale Devri sonrasında tahttan indirilen babasıyla birlikte uzun süre sarayda gözlemci konumda kalmış, kafes hayatı sürmüş, ancak bu dönemde iyi bir eğitim alarak yetişmiştir. Matematik, astronomi ve dinî ilimlere ilgisi vardı. III. Osman’ın 1757’de vefat etmesinin ardından, Osmanlı tahtına çıktı. Tahta çıktığında 40 yaşındaydı.\n" +
                    "\n" +
                    "Saltanatı, 1757–1774 yılları arasında 17 yıl sürdü ve hem iç hem dış meselelerle dolu zorlu bir dönemdi. III. Mustafa, devletin durumunu iyi analiz etmiş ve ciddi ıslahat (reform) girişimlerinde bulunmuştur. Ancak bu reform çabaları, büyük ölçüde Avrupa devletleriyle yaşanan savaşlar nedeniyle sekteye uğramıştır.\n" +
                    "\n" +
                    "Padişah olduktan sonra ilk iş olarak sadrazamlığa Koca Ragıp Paşa’yı getirdi. Ragıp Paşa’nın bilgeliği ve tecrübesi sayesinde, ilk yıllar görece sakin geçti. Ancak onun 1763’teki ölümünden sonra Osmanlı Devleti yeniden siyasi çalkantılara ve dış tehditlere açık hâle geldi. Bu dönemde Osmanlı-Rus ilişkileri giderek kötüleşti.\n" +
                    "\n" +
                    "1768 yılında, Osmanlı ile Rusya arasında savaş patlak verdi. Bu savaşta Osmanlı, birçok cephede ağır kayıplar verdi. En dikkat çekici olaylardan biri, 1770’te Çeşme Deniz Savaşı sırasında Osmanlı donanmasının Ruslar tarafından tamamen yakılmasıydı. Bu büyük yenilgi, Osmanlı deniz gücünü neredeyse bitirme noktasına getirdi. Ayrıca Rus orduları Balkanlar üzerinden Osmanlı topraklarına ilerlemeye başladı.\n" +
                    "\n" +
                    "Bu sırada III. Mustafa, içte birçok reform girişimi başlatmaya çalıştı. Özellikle ordu düzeni, topçu sınıfı, modern mühendislik eğitimi ve mali yapı üzerinde çalışmalarda bulundu. Nitekim onun döneminde Baron de Tott adlı bir Fransız subay getirildi ve Osmanlı ordusunun teknik eğitimi konusunda danışmanlık yaptı. Bu gelişmeler, Nizam-ı Cedid (Yeni Düzen) düşüncesinin temel taşlarını oluşturdu ve onun ölümünden sonra oğlu III. Selim tarafından daha kapsamlı şekilde uygulanacaktı.\n" +
                    "\n" +
                    "III. Mustafa, reformlarında kararlı olmasına rağmen, dönemin siyasi yapısı, mali krizi ve savaşların yıkıcı etkisi onun çabalarının karşılık bulmasını zorlaştırdı. Aynı zamanda çok duygusal, melankolik bir yapıya sahipti. Savaş yenilgileri arttıkça içine kapanmış ve büyük üzüntüler yaşamıştır. Rusya karşısındaki başarısızlıklar, onu derinden etkilemiş, hatta savaş sona ermeden kabullenemeyeceği bir barış antlaşmasının yaklaştığını fark edince büyük bir moral bozukluğuna girmiştir.\n" +
                    "\n" +
                    "21 Ocak 1774 tarihinde, İstanbul’da vefat etti. Ölüm sebebi kalp krizi ya da aşırı üzüntüye bağlı ruhsal çöküntü olarak yorumlanır. Cenazesi, Laleli Camii Külliyesi’ne, kendi yaptırdığı caminin hazîresine defnedilmiştir. Yerine, kardeşi I. Abdülhamid tahta geçmiştir.\n" +
                    "\n")
        val birinciAbdulhamid= PadisahData(id = 30,
            isim = "1.Abdülhamid",
            dogum = 1725,
            olum = 1789,
            saltanatSuresi = 15,
            donem = "",
            gorsel = R.drawable.birinci_abdulhamid,
            lakap = "Abdü'l-Hâmîd-i Evvel, Islâhatçı, Gazi",
            savaslari = "Osmanlı-İran Savaşı,Osmanlı-Rus ve Osmanlı-Avusturya Savaşları",
            cocuklari = "4.Mustafa,2.Mahmud,Şehzade Abdullah,Şehzade Mehmed,Şehzade Ahmed,Şehzade Abdülaziz,Şehzade Abdurrahman,Şehzade Mehmed Nusret,Esma Sultan,Hibetullah Sultan,Ayşe Dürrüşevher Sultan,Aynişah Sultan,Hatice Sultan,Emine Sultan,Rabia Sultan,Fatma Sultan,Alemşah Sultan,Saliha Sultan",
            biyografi = "I. Abdülhamid, 20 Mart 1725 tarihinde İstanbul’da doğmuştur. Babası III. Ahmed, annesi Rabia Şermi Sultan’dır. Lale Devri sonrasında, uzun yıllar boyunca sarayda “kafes hayatı” sürdürmüş; devlet işlerinden uzak ama eğitimli, ağırbaşlı ve dindar bir şehzade olarak yetişmiştir. Ağabeyi III. Mustafa’nın 1774 yılında vefat etmesi üzerine, 49 yaşında Osmanlı tahtına çıkmıştır.\n" +
                    "\n" +
                    "Tahta geçer geçmez, Osmanlı Devleti’nin o dönemde içinde bulunduğu ağır durumla yüzleşmek zorunda kaldı. Rusya ile 6 yıldır süren savaşın sonunda yapılan ve Osmanlı açısından çok ağır şartlar içeren Küçük Kaynarca Antlaşması (1774), daha I. Abdülhamid tahta çıkmadan kabul edilmek zorunda kalınmıştı. Bu antlaşma ile Kırım’ın bağımsızlığı, Osmanlı’nın Karadeniz’deki egemenliğinin sarsılması ve Rusya’ya Ortodoksların hamiliği gibi çok önemli tavizler verilmişti. I. Abdülhamid bu antlaşmayı üzülerek kabul etmiş ve saltanatına tarihteki en aşağılayıcı diplomatik kayıplardan biriyle başlamak zorunda kalmıştır.\n" +
                    "\n" +
                    "Bu durum onun padişahlığı boyunca izlerini hissettirmiştir. Son derece dindar, sade ve adaletli bir padişah olan Abdülhamid, devleti içinde bulunduğu çöküşten kurtarmak için büyük çaba göstermiştir. Mali disiplini yeniden sağlamak, ordu reformlarını sürdürmek, yolsuzlukla mücadele etmek ve özellikle eyalet yönetimlerinde merkezi otoriteyi güçlendirmek gibi hedefleri vardı. Ancak hem içteki bozulmalar hem de dış baskılar bu çabaları sınırlı kıldı.\n" +
                    "\n" +
                    "1768-1774 savaşının ardından Osmanlı-Rus gerginliği tamamen bitmemişti. Nitekim 1783 yılında Rusya, Kırım’ı tamamen ilhak etti. Bu durum Osmanlı için bir diplomatik ve moral yıkım oldu. I. Abdülhamid bu gelişmeye büyük tepki gösterdi, ancak devletin askerî gücü bu işgale engel olmaya yetmedi. Bu olay padişahı derinden yaralamış, hatta Kırım'ın kaybedildiğini öğrendiği gün baygınlık geçirdiği rivayet edilmiştir.\n" +
                    "\n" +
                    "Buna rağmen I. Abdülhamid döneminde iç reformlar önem kazandı. Sadrazam Hâlil Hamid Paşa ile birlikte, Avrupa’daki teknik gelişmelerin takip edilmesi için Fransa’ya öğrenciler gönderildi, askeri ıslahatlara girişildi, hendesehane (mühendislik okulu) kurulması planlandı. Bu reformlar, onun oğlu II. Mahmud ve özellikle III. Selim döneminde etkisini daha da gösterecektir.\n" +
                    "\n" +
                    "Padişah kişisel yaşamında son derece mütevazı ve halktan yana bir hükümdardı. Devlet hazinesini kendi harcamaları için kullanmaz, çoğu zaman ihtiyaç sahiplerine dağıtılmak üzere vakıflar kurar, borçluların borçlarını öderdi. Laleli Camii ve Hamidiye Külliyesi, onun döneminde İstanbul’da inşa edilen önemli yapılardandır.\n" +
                    "\n" +
                    "7 Nisan 1789 tarihinde, 64 yaşındayken İstanbul’da vefat etti. Cenazesi, Beyazıt’ta kendi adını taşıyan I. Abdülhamid Türbesi’ne defnedilmiştir. Yerine, III. Mustafa’nın oğlu olan III. Selim Osmanlı tahtına geçmiştir.")
        val ucuncuSelim= PadisahData(id = 31,
            isim = "3.Selim",
            dogum = 1761,
            olum = 1808,
            saltanatSuresi = 18,
            donem = "",
            gorsel = R.drawable.ucuncu_selim,
            lakap = "Selîm-î Sâlis, Bestekâr, İlhamî, Şehit",
            cocuklari = "III. Selim'in çocuğu olmamıştır.",
            savaslari = "Osmanlı-Avusturya ve Osmanlı-Rus Savaşları,Fransa'ya Karşı Savaş,Osmanlı-Rus Savaşı",
            biyografi = "III. Selim, 24 Aralık 1761 tarihinde İstanbul’da doğmuştur. Babası III. Mustafa, annesi Mihrişah Sultan’dır. Henüz şehzadeyken dönemin devlet meselelerine ilgi göstermeye başlamış, özellikle Avrupa’daki gelişmeleri yakından takip etmiş; çok iyi eğitim alarak yetişmiştir. Fransızca öğrenmiş, musikiyle ilgilenmiş, klasik şiire merak sarmış ve son derece entelektüel bir kişilik geliştirmiştir.\n" +
                    "\n" +
                    "Amcası I. Abdülhamid’in 1789’da vefatı üzerine, henüz 28 yaşındayken Osmanlı tahtına çıktı. Tahta geçtiğinde Avrupa, Fransız Devrimi ile çalkalanmaktaydı. Bu devrim, hem kıta siyaseti hem de Osmanlı üzerinde doğrudan ve dolaylı etkiler yaratacaktı.\n" +
                    "\n" +
                    "III. Selim, daha tahta çıkarken Osmanlı’nın içinde bulunduğu askerî, idarî ve ekonomik çöküşü fark etmişti. Bu nedenle daha ilk yıllarından itibaren kapsamlı bir reform süreci başlattı. Bu reformlara genel olarak “Nizam-ı Cedid” (Yeni Düzen) adı verilir.\n" +
                    "\n" +
                    "Nizam-ı Cedid’in en önemli adımı, modern, Batı tarzında bir ordu kurmaktı. Bu amaçla yabancı danışmanlar getirildi, yeni kışlalar yapıldı, disiplinli bir ordu eğitimi başlatıldı. Ayrıca bu orduyu finanse etmek için “İrad-ı Cedid Hazinesi” adıyla ayrı bir bütçe oluşturuldu. Geleneksel yapılar bu reformlara direnç gösterdi; özellikle Yeniçeri Ocağı, bu yeni yapılanmayı kendisine karşı bir tehdit olarak gördü.\n" +
                    "\n" +
                    "III. Selim sadece askerî alanda değil, bürokraside, eğitimde, diplomasi ve ticarette de yenilikler getirdi. Avrupa devletlerinde sürekli elçilikler kuruldu; bu durum Osmanlı’nın Avrupa ile diplomatik bağlarını modernleştirmesini sağladı. Paris, Londra ve Viyana gibi merkezlerde Osmanlı elçileri görev yapmaya başladı.\n" +
                    "\n" +
                    "Ancak reformlara karşı oluşan iç muhalefet giderek arttı. Özellikle ulema, yeniçeriler ve saray içindeki gelenekçi kanatlar, padişahı Batı yanlısı ve “yeni düzenci” olmakla suçladılar. Bu tepkiler zamanla bir isyana dönüştü. 1807 yılında Kabakçı Mustafa önderliğinde çıkan ayaklanma, III. Selim’in bütün reform çabalarını yerle bir etti. Ayaklanma sonucunda III. Selim tahttan indirildi; yerine IV. Mustafa geçirildi.\n" +
                    "\n" +
                    "Tahttan indirilen III. Selim, sarayda göz hapsine alındı. Ancak onun en büyük destekçilerinden biri olan Alemdar Mustafa Paşa, 1808’de İstanbul’a gelerek bir karşı darbe düzenledi. III. Selim’i yeniden tahta çıkarmak istedi. Fakat bu girişim sırasında IV. Mustafa’nın emriyle sarayda tutulan III. Selim boğdurularak öldürüldü. Ölüm tarihi 28 Temmuz 1808, ölüm yeri Topkapı Sarayı’dır. 46 yaşında hayatını kaybetti.\n" +
                    "\n" +
                    "Cenazesi, Laleli Camii hazîresindeki III. Mustafa Türbesi’ne defnedildi.\n" +
                    "\n")
        val dorduncuMustafa= PadisahData(id = 32,
            isim = "4.Mustafa",
            dogum = 1779,
            olum = 1808,
            saltanatSuresi = 1,
            donem = "",
            lakap = "",
            gorsel = R.drawable.dorduncu_mustafa,
            cocuklari = "Emine Sultan",
            savaslari = "Kabakçı Mustafa İsyanı,Alemdar Mustafa Paşa Olayı",
            biyografi = "IV. Mustafa, 8 Eylül 1779 tarihinde İstanbul’da doğmuştur. Babası I. Abdülhamid, annesi Ayşe Sineperver Sultan’dır. Ağabeyi III. Selim’in reformcu karakterine karşın, IV. Mustafa daha gelenekçi ve durağan bir yapıda yetişmiştir. Kafes hayatı sürerken devlet işlerinden uzak kalmış, ancak taht sırasına yaklaştıkça çevresinde yeniçeri ve saray uleması destekli gruplarla ilişki kurmuştur.\n" +
                    "\n" +
                    "29 Mayıs 1807’de, Kabakçı Mustafa İsyanı sonucunda III. Selim tahttan indirildiğinde, IV. Mustafa isyancıların desteğiyle Osmanlı tahtına geçirildi. Tahta geçtiğinde 27 yaşındaydı. Ancak onun saltanatı, sadece 1 yıl 2 ay kadar süren, siyasi istikrarsızlıkla ve iktidar mücadelesiyle dolu bir dönem olmuştur.\n" +
                    "\n" +
                    "IV. Mustafa’nın iktidara gelişi, Nizam-ı Cedid karşıtlarının zaferi olarak yorumlandı. Yeniçeriler, reformları destekleyen devlet adamlarını tasfiye etti; Nizam-ı Cedid ordusu ve kurumları dağıtıldı. Devlet, reform sürecinde kaydettiği ilerlemeleri kısa sürede geri aldı. IV. Mustafa, III. Selim’e göre daha pasif bir padişah görüntüsü çizdi; birçok kararı çevresindeki etkili kişilere bıraktı.\n" +
                    "\n" +
                    "Ancak kısa sürede, bu karşı-devrim ortamından rahatsız olan bir grup devlet adamı Alemdar Mustafa Paşa önderliğinde yeniden harekete geçti. Alemdar, III. Selim’i tekrar tahta çıkarmak üzere Temmuz 1808’de İstanbul’a yürüdü. Bu durum karşısında paniğe kapılan IV. Mustafa ve çevresi, sarayda tutuklu bulunan III. Selim’i yeniden tahta çıkarma girişimlerine karşılık onu öldürme kararı aldı. Nitekim III. Selim, 28 Temmuz 1808’de boğdurularak öldürüldü.\n" +
                    "\n" +
                    "Ancak bu hamle IV. Mustafa’yı kurtaramadı. Alemdar saraya girerek IV. Mustafa’yı tahttan indirdi ve yerine kardeşi II. Mahmud tahta çıkarıldı. IV. Mustafa gözaltına alındı ve kardeşini de öldürerek yeniden tahta çıkma girişiminde bulunduğu gerekçesiyle, 15 Kasım 1808 tarihinde Topkapı Sarayı’nda idam edildi. İdam kararı bizzat II. Mahmud tarafından onaylandı. 29 yaşında ölen IV. Mustafa, Yeni Cami Türbesi’ne defnedilmiştir.")
        val ikinciMahmud= PadisahData(id = 33,
            isim = "2.Mahmud",
            dogum = 1785,
            olum = 1839,
            saltanatSuresi = 30,
            donem = "",
            gorsel = R.drawable.ikinci_mahmud,
            lakap = "Mahmud-u Sânî, İnkılâpçı, Gazi",
            cocuklari = "Sultan Abdülmecid,Sultan Abdülaziz,Saliha Sultan,Atiye Sultan,Hatice Sultan,Adile Sultan",
            savaslari = "Sırp İsyanının Bastırılması,1821-1823 Osmanlı-İran Savaşı,Yunan İsyanı ve 1828-1829 Osmanlı-Rus Savaşı,Fransa'nın Cezayir'i İşgali,Kavalalı Mehmed Ali Paşa İsyanı",
            biyografi = "II. Mahmud, 20 Temmuz 1785 tarihinde İstanbul’da doğmuştur. Babası I. Abdülhamid, annesi Nakşidil Sultan’dır. Osmanlı tarihinde derin izler bırakmış, köklü reformlara imza atmış padişahlardan biridir. Amcası III. Selim’in reform girişimlerine yakından tanık olmuş, onun ölümünden sonra 28 Temmuz 1808’de Alemdar Mustafa Paşa’nın desteğiyle tahta çıkmıştır. Tahta çıktığında 23 yaşındaydı.\n" +
                    "\n" +
                    "Saltanatı boyunca Osmanlı’nın hem içte hem dışta yıpranmış yapısını toparlamak için çok yönlü bir modernleşme ve merkezileşme politikası izledi. Ancak bu süreç son derece sancılı geçti. İlk olarak Alemdar Mustafa Paşa ile birlikte Sekban-ı Cedid adlı yeni bir ordu kuruldu. Fakat bu girişim kısa sürede yeniçerilerin tepkisini çekti; isyan çıktı, Alemdar öldürüldü, yeni ordu dağıtıldı. Bu olay II. Mahmud’a devletin gerçek gücünün hâlâ yeniçerilerin elinde olduğunu gösterdi.\n" +
                    "\n" +
                    "Ancak II. Mahmud vazgeçmedi. Onun en büyük hedeflerinden biri, Yeniçeri Ocağı’nı kaldırmak ve merkezi otoriteyi sağlamaktı. Bu hedefini 1826 yılında “Vaka-i Hayriye” (Hayırlı Olay) olarak tarihe geçen olayla gerçekleştirdi. Yeniçeri Ocağı topa tutuldu, binlerce yeniçeri öldürüldü veya sürgüne gönderildi. Ardından Asâkir-i Mansûre-i Muhammediyye adıyla Batı tarzı yeni bir ordu kuruldu.\n" +
                    "\n" +
                    "Askeri reformlarla yetinmeyen II. Mahmud, aynı zamanda idari, eğitimsel, hukuki ve toplumsal alanlarda da önemli düzenlemeler yaptı:\n" +
                    "\n" +
                    "Divan teşkilatını kaldırdı, bakanlık benzeri kurullar kurdu.\n" +
                    "\n" +
                    "Memurlara kıyafet mecburiyeti ve devlet görevlileri için sicil sistemi getirdi.\n" +
                    "\n" +
                    "Posta, karantina, pasaport ve nüfus gibi modern devlet işleyişini sağlayacak birçok sistemi kurdu.\n" +
                    "\n" +
                    "İlk kez takvim, gazete (Takvim-i Vekayi) ve modern eğitim kurumları Osmanlı’da onun döneminde ortaya çıktı.\n" +
                    "\n" +
                    "Medreselere alternatif olarak rüştiye (ortaokul düzeyi) okullarını kurdurdu.\n" +
                    "\n" +
                    "Yurt dışına öğrenci göndermeye başladı.\n" +
                    "\n" +
                    "Müftülük ve şeyhülislamlık makamlarını denetim altına aldı.\n" +
                    "\n" +
                    "Ancak reformlar kadar zorluklar da eksik olmadı. II. Mahmud’un saltanatında çok sayıda iç ayaklanma çıktı (örneğin: Tepedelenli Ali Paşa isyanı, Mehmet Ali Paşa krizi). Ayrıca Osmanlı’nın egemenliğindeki Yunan isyanı 1821’de patlak verdi ve 1829’daki Edirne Antlaşması ile Yunanistan bağımsızlığını kazandı. Bu olay, Osmanlı tarihinde ilk kez bir ulusun ayrılarak bağımsız devlet kurması anlamına geliyordu.\n" +
                    "\n" +
                    "Dış politikada da büyük zorluklar yaşandı. Rusya ile 1828–1829 Savaşı, Osmanlı için ağır sonuçlar doğurdu. Edirne Antlaşması’yla bazı toprak kayıpları yaşandı ve Rusya, Osmanlı iç işlerine karışabilecek haklar elde etti. Diğer yandan Mısır Valisi Kavalalı Mehmet Ali Paşa, Osmanlı merkezi otoritesine baş kaldırdı. Onunla yapılan mücadele II. Mahmud’un son yıllarına damga vurdu.\n" +
                    "\n" +
                    "II. Mahmud, 1 Temmuz 1839 tarihinde, 54 yaşında İstanbul’da vefat etti. Ölüm nedeni, uzun süredir muzdarip olduğu verem hastalığıydı. Cenazesi, Divanyolu’ndaki II. Mahmud Türbesi’ne defnedildi. Yerine oğlu Abdülmecid tahta çıktı.\n" +
                    "\n")
        val abdulmecid= PadisahData(id = 34,
            isim = "Abdülmecid",
            dogum = 1823,
            olum = 1861,
            donem = "",
            saltanatSuresi = 22,
            gorsel = R.drawable.abdulmecid,
            lakap = "Tanzimâtçı, Gazi",
            cocuklari = "V. Murad,II. Abdülhamid,V. Mehmed (Mehmed Reşad),VI. Mehmed (Mehmed Vahideddin),Şehzade Mehmed Burhaneddin,Şehzade Mehmed Rüşdü,Şehzade Ahmed Kemalettin,Şehzade Mehmed Abid,Şehzade Mehmed Vasıf,Şehzade Nureddin,Şehzade Süleyman,Fatma Sultan,Refia Sultan,Cemile Sultan,Münire Sultan,Behiye Sultan,Mediha Sultan,Naile Sultan,Seniha Sultan,Zekiye Sultan",
            savaslari = "Kırım Savaşı,Sinop Baskını ,Sivastopol Kuşatması,",
            biyografi = "Sultan Abdülmecid, 25 Nisan 1823 tarihinde İstanbul’da, Beşiktaş Sarayı’nda doğmuştur. Babası II. Mahmud, annesi Bezmialem Valide Sultan’dır. Küçük yaşlardan itibaren iyi bir eğitim aldı; Arapça ve Farsçanın yanı sıra Fransızca öğrendi. Müzik, resim ve batı kültürüne özel ilgi duydu. 1 Temmuz 1839 tarihinde, babasının vefatı üzerine henüz 16 yaşındayken Osmanlı tahtına çıktı. Bu, Osmanlı tarihinde büyük değişimlerin başladığı bir dönemin habercisiydi.\n" +
                    "\n" +
                    "Tahta çıktığında Osmanlı Devleti hem içte hem dışta ağır baskılar altındaydı. Mısır Valisi Kavalalı Mehmed Ali Paşa ile yaşanan kriz henüz çözülmemişti. Ancak Abdülmecid’in tahta çıkmasından sadece 4 ay sonra, 3 Kasım 1839’da, Hariciye Nazırı Mustafa Reşid Paşa tarafından Tanzimat Fermanı (Gülhane Hatt-ı Hümayunu) ilan edildi. Bu ferman, Osmanlı tarihinde ilk kez hukuk önünde eşitlik, mülkiyet güvencesi, vergi ve askerlik sisteminin adil hale getirilmesi gibi ilkeleri içeren anayasal yönetime geçişin temelini oluşturdu.\n" +
                    "\n" +
                    "Abdülmecid, Tanzimat reformlarını destekledi. Hukuk, eğitim, maliye ve askerî alanda geniş çaplı değişiklikler başlatıldı:\n" +
                    "\n" +
                    "Meclis-i Vâlâ, Meclis-i Maarif, Meclis-i Tanzimat gibi yeni idari yapılar oluşturuldu.\n" +
                    "\n" +
                    "İlk modern ceza kanunu ve ticaret hukuku hazırlandı.\n" +
                    "\n" +
                    "Maarif Nezareti (Milli Eğitim Bakanlığı) kuruldu, rüştiyeler (ortaokullar) yaygınlaştırıldı.\n" +
                    "\n" +
                    "Batılı tarzda ilk kız ve erkek liseleri açıldı.\n" +
                    "\n" +
                    "İlk telgraf hattı, ilk demiryolu, ilk Osmanlı bankası gibi teknolojik gelişmeler onun döneminde hayata geçti.\n" +
                    "\n" +
                    "Mekteb-i Mülkiye (Siyasal Bilgiler Okulu) ve Darülmaarif gibi yükseköğretim kurumları kuruldu.\n" +
                    "\n" +
                    "Bu reformlar, özellikle gayrimüslim tebaanın haklarının artırılması açısından önemliydi. 1856’da Islahat Fermanı ile gayrimüslimlerin devlet görevlerine alınması, okul ve ibadethane açmaları gibi yeni haklar tanındı. Ancak bu durum, bazı Müslüman kesimlerde hoşnutsuzluk yarattı.\n" +
                    "\n" +
                    "Dış politikada, Abdülmecid Avrupa ile iyi ilişkiler kurmaya çalıştı. En önemli gelişme, 1853–1856 Kırım Savaşı oldu. Osmanlı, bu savaşta İngiltere ve Fransa ile birlikte Rusya’ya karşı savaştı. Savaş sonunda imzalanan Paris Antlaşması (1856) ile Osmanlı Devleti, Avrupa devletler sistemi içine resmen alındı. Bu, uluslararası meşruiyet açısından önemli bir adımdı.\n" +
                    "\n" +
                    "Ancak Abdülmecid’in döneminde ekonomik sorunlar giderek büyüdü. Reformların ve savaşların maliyeti çok yüksekti. Bu nedenle ilk dış borç 1854 yılında Londra’dan alındı. Zamanla borçlanma arttı ve bu durum, sonraki yıllarda Osmanlı ekonomisini iflasa sürükleyecek süreci başlattı.\n" +
                    "\n" +
                    "Abdülmecid’in kişisel yönü ise oldukça nazik, barışçıl ve sanatsever bir padişah olduğunu gösterir. Batı müziğine ilgi duyar, piyano çalardı. Aynı zamanda klasik Türk müziğine de önem verirdi. Yabancı elçileri sarayda ağırlarken Fransızca konuşur, Batı kültürüne oldukça açık bir imaj çizerdi.\n" +
                    "\n" +
                    "Saray mimarisi de onun döneminde büyük değişim yaşadı. Dolmabahçe Sarayı’nın yapımına başlanmış ve büyük ölçüde tamamlanmıştır. Bu yapı, Osmanlı’nın Batı mimarî anlayışına geçişinin simgesi hâline gelmiştir.\n" +
                    "\n" +
                    "Ancak artan sağlık sorunları ve siyasi baskılar Abdülmecid’i yıprattı. 25 Haziran 1861 tarihinde, henüz 38 yaşındayken İstanbul’da vefat etti. Ölüm nedeni verem olarak kayıtlara geçmiştir. Cenazesi, Yavuz Sultan Selim Camii hazîresindeki Sultan türbesine defnedildi. Yerine kardeşi Sultan Abdülaziz geçti.\n" +
                    "\n")
        val abdulaziz= PadisahData(id = 35,
            isim = "Abdülaziz",
            dogum = 1830,
            olum = 1876,
            saltanatSuresi = 15,
            donem = "",
            lakap = "Bahtsız, Şehit",
            gorsel = R.drawable.abdulaziz,
            savaslari = "Karadağ İsyanı,Girit İsyanı,Yemen Seferleri,Balkan İsyanları",
            cocuklari = "Şehzade Yusuf İzzeddin Efendi,Şehzade Mahmud Celaleddin Efendi,Şehzade Mehmed Selim Efendi, Abdülmecid Efendi (Son Osmanlı Halifesi),Şehzade Mehmed Şevket Efendi,Şehzade Mehmed Seyfeddin Efendi,Saliha Sultan,Nazime Sultan,Esma Sultan,Emine Sultan, Fâtıma Sultan, Münire Sultan",
            biyografi = "Sultan Abdülaziz, 8 Şubat 1830 tarihinde İstanbul’da, Eyüpsultan’da doğdu. Babası II. Mahmud, annesi Pertevniyal Valide Sultan’dır. Ağabeyi Sultan Abdülmecid’in vefatının ardından, 25 Haziran 1861 tarihinde Osmanlı tahtına çıktı. Tahta geçtiğinde 31 yaşındaydı. Güçlü fiziği, sert mizacı, kararlı yapısı ve Batı’ya olan ilgisiyle dikkat çeken bir padişahtı.\n" +
                    "\n" +
                    "Sultan Abdülaziz, Tanzimat reformlarının genel çerçevesine sadık kaldı, ancak bazı alanlarda daha otoriter ve merkeziyetçi bir yönetim tarzı benimsedi. Eğitim, ulaşım, haberleşme, donanma ve bürokrasi gibi birçok alanda önemli adımlar attı. Özellikle denizciliğe özel önem verdi ve Osmanlı donanmasını modernleştirme konusunda büyük çaba gösterdi.Osmanlı Donanması, Abdülaziz döneminde dünyanın üçüncü büyük filosu hâline geldi. Yeni buharlı savaş gemileri satın alındı veya Tersane-i Amire’de inşa edildi. Ancak bu donanmanın finansmanı ağır borçlanmalarla sağlandığı için, devlet maliyesini zorladı.\n" +
                    "\n" +
                    "Demiryolu projeleri hız kazandı. İstanbul-Edirne, İzmir-Aydın ve Şam-Medine gibi hatlar planlandı ve bazıları işletilmeye başlandı.\n" +
                    "\n" +
                    "Eğitim alanında, Darüşşafaka (yetim Müslüman çocuklar için), Mekteb-i Sultani (Galatasaray Lisesi), sanayi mektepleri ve rüştiyeler açıldı. Modern öğretmen okulları ve askeri akademiler teşvik edildi.\n" +
                    "\n" +
                    "İlk kez Osmanlı padişahı olarak Avrupa’ya resmî ziyaret gerçekleştirdi. 1867 yılında Fransa, İngiltere, Prusya ve Avusturya’ya gitti. Bu ziyaret, Batı basınında büyük ilgiyle karşılandı ve Osmanlı’nın imajını güçlendirmeyi hedefledi.\n" +
                    "\n" +
                    "Ancak Abdülaziz döneminde devletin mali durumu hızla bozulmaya başladı. Donanma, saray inşaatları (özellikle Dolmabahçe, Beylerbeyi, Çırağan) ve lüks harcamalar nedeniyle devlet borçlanmaya bağımlı hâle geldi. 1875’e gelindiğinde Osmanlı Devleti dış borçlarını ödeyemez duruma geldi ve borçları ödeyemediğini ilan etti (moratoryum). Bu, Avrupa’daki Osmanlı tahvillerinin çökmesine yol açtı ve devletin ekonomik bağımsızlığı zedelendi.\n" +
                    "\n" +
                    "İçeride ise yönetimine karşı artan hoşnutsuzluklar, memur maaşlarının ödenememesi, ayanlar ve halk üzerindeki baskılar sebebiyle ortam giderek gerginleşti. Özellikle Mithat Paşa ve genç Osmanlı aydınları, Abdülaziz’i meşrutiyet karşıtı ve baskıcı bir figür olarak görmeye başladılar.30 Mayıs 1876 tarihinde, askerî ve bürokratik bir darbe ile tahttan indirildi. Yerine yeğeni V. Murad geçirildi. Abdülaziz önce Topkapı Sarayı’na, sonra Feriye Sarayı’na götürüldü. Kısa bir süre sonra, 4 Haziran 1876 tarihinde, bilek damarları kesilmiş hâlde ölü bulundu. Resmî açıklama intihar yönündeydi; ancak birçok tarihçiye göre bu ölüm oldukça şüpheli koşullarda gerçekleşmiştir.\n" +
                    "\n" +
                    "Cenazesi, Divanyolu’nda babası II. Mahmud’un türbesine defnedildi.")
        val besinciMurad= PadisahData(id = 36,
            isim = "5.Murad",
            dogum = 1840,
            olum = 1904,
            lakap = "Deli",
            saltanatSuresi = 0,
            gorsel = R.drawable.besinci_murad,
            cocuklari = "Şehzade Mehmed Selahaddin Efendi,Şehzade Süleyman Efendi,Şehzade Seyfeddin Efendi,Hatice Sultan,Fehime Sultan,Fatma Sultan,Aliye Sultan",
            savaslari = "Balkan İsyanları",
            donem = "",
            biyografi = "V. Murad, 21 Kasım 1840 tarihinde İstanbul’da doğdu. Babası Abdülmecid, annesi Şevkefza Kadın’dır. Hem sanatla hem de özellikle psikolojik sorunlarla ilgilenen bir padişahtı. Çok genç yaşta tahta çıktı; 30 Mayıs 1876’da tahta geçen Murad’ın saltanatı sadece 93 gün sürdü. Zayıf sağlığı ve özellikle epilepsi hastalığı nedeniyle devlet işlerini yürütmede zorlandı. Halk ve devlet adamları arasında güven kazanamadı. Bu sebeple kısa sürede tahttan indirildi.\n" +
                    "\n" +
                    "Tahttan indirildikten sonra sarayda göz hapsinde tutuldu. Onun döneminde Osmanlı’da önemli siyasi gelişmeler yaşanıyordu; 1876 yılında İlk Osmanlı Anayasası (Kanun-i Esasi) ilan edildi. V. Murad, bu gelişmelerin hemen öncesinde ve sırasında yaşadığı sağlık sorunları nedeniyle aktif rol alamadı. Tahttan indirilmesine rağmen, bu anayasal sürecin gerçekleşmesine dolaylı olarak zemin hazırlayan dönemde padişah olarak bulunmuş oldu.\n" +
                    "\n" +
                    "V. Murad, tahttan indirildikten sonra özellikle psikolojik rahatsızlıkları yüzünden sarayda kapalı bir yaşam sürdü. Sağlık durumu giderek kötüleşti. 29 Ağustos 1904 tarihinde, 63 yaşında İstanbul’da vefat etti. Cenazesi, babası Abdülmecid’in türbesine defnedildi.\n" +
                    "\n" +
                    "V. Murad, Osmanlı tarihinde kısa ve hastalıklı bir saltanatın padişahı olarak anılır. Reformların başladığı, meşrutiyetin ilk adımlarının atıldığı kritik bir dönemde yaşadı ancak kendisi bu dönüşüm sürecinde aktif olamadı. Kişisel trajedisi, Osmanlı padişahları arasında farklı bir yer tutmasını sağladı.")
        val ikinciAbdulhamid= PadisahData(id = 37,
            isim = "2.Abdülhamid",
            dogum = 1842,
            olum = 1918,
            saltanatSuresi = 33,
            donem = "",
            gorsel = R.drawable.ikinci_abdulhamid,
            lakap = "Abdü'l Hamîd-i Sânî, Gazi",
            savaslari = "93 Harbi (1877-1878 Osmanlı-Rus Savaşı),1897 Osmanlı-Yunan Savaşı (Teselya Savaşı),Yemen İsyanları,Makedonya Sorunu,31 Mart Vakası (1909)",
            cocuklari = "Şehzade Mehmed Selim Efendi,Şehzade Mehmed Abdülkadir Efendi,Şehzade Ahmed Nuri Efendi,Şehzade Mehmed Burhaneddin Efendi,Şehzade Abdürrahim Hayri Efendi,Şehzade Ahmed Nureddin Efendi,Şehzade Mehmed Abid Efendi,Şehzade Mehmed Bedreddin Efendi,Ulviye Sultan,Zekiye Sultan,Naime Sultan,Fatma Naime Sultan,Ayşe Sultan(Osmanoğlu),Şadiye Sultan,Refia Sultan,Hatice Sultan,Aliye Sultan,Cemile Sultan,Samiye Sultan,Saliha Sultan",
            biyografi = "II. Abdülhamid, 21 Eylül 1842 tarihinde İstanbul’da doğdu. Babası Sultan Abdülmecid, annesi Tirimüjgan Kadın’dır. Sarayda sıkı bir eğitim aldı, Arapça, Farsça ve Fransızca öğrendi. Genç yaşlarından itibaren devlet işlerine ilgi duydu ve tecrübeli isimlerle yakın temas kurdu. 31 Ağustos 1876 tarihinde tahta çıktı. Saltanatı 33 yıl sürdü ve bu süre Osmanlı tarihindeki en uzun padişahlıklardan biridir.\n" +
                    "\n" +
                    "Tahta çıktığında Osmanlı Devleti ağır bir siyasi, ekonomik ve toplumsal kriz içindeydi. V. Murad’ın kısa ve hasta saltanatı sonrası, II. Abdülhamid devletin toparlanması ve modernleşme sürecini kendi otoriter yönetimi altında sürdürdü. Onun döneminde özellikle Meşrutiyet rejimi ilan edildi (1876), ancak 1878’de meclis kapatıldı ve fiili olarak mutlak monarşi dönemi başladı.\n" +
                    "\n" +
                    "II. Abdülhamid, devletin güvenliği ve bütünlüğünü korumayı en önemli hedefi olarak gördü. Merkezi otoriteyi güçlendirdi, eğitim ve altyapı yatırımlarını artırdı. Özellikle Demiryolu projeleri hız kazandı; Hicaz Demiryolu onun döneminde tamamlandı. Modern postane ve telgraf sistemi geliştirildi, eğitimde Darülfünun (üniversite) kuruldu.\n" +
                    "\n" +
                    "Ancak aynı zamanda baskıcı bir yönetim sergiledi. Muhaliflerine karşı sert tedbirler aldı, istihbarat örgütleri kurdu ve sansürü sıkılaştırdı. Jön Türkler olarak bilinen genç Osmanlı aydınlarıyla sürekli çatıştı. Bu durum hem iç huzursuzluğu artırdı hem de Osmanlı’nın dış baskılarını tetikledi.\n" +
                    "\n" +
                    "Dış politikada ise Osmanlı’nın toprak bütünlüğünü korumak için yoğun mücadele verdi. Balkanlar’da milliyetçi hareketlerin artışı ve 93 Harbi (1877-78 Osmanlı-Rus Savaşı) büyük zorluklar yarattı. Savaş sonunda Berlin Antlaşması imzalandı ancak bu antlaşma Osmanlı’nın Balkanlardaki egemenliğini ciddi ölçüde zayıflattı.\n" +
                    "\n" +
                    "II. Abdülhamid, kültür ve sanata da önem verdi. Sarayda hem Osmanlı hem Batı müziği gelişti. Ayrıca mimari eserler inşa ettirdi; en bilinen eserlerden biri Yıldız Sarayıdır.\n" +
                    "\n" +
                    "1908 yılında gerçekleşen II. Meşrutiyet’in ilanı sonrası baskıları azalsa da, 1909 yılında tahttan indirildi ve yerine kardeşi V. Mehmed geçti. 10 Şubat 1918’de İstanbul’da, 75 yaşında vefat etti ve Yavuz Sultan Selim Camii’nin hazîresine defnedildi.\n" +
                    "\n" +
                    "II. Abdülhamid, Osmanlı tarihinde hem modernleşme hamlelerini gerçekleştiren, hem de sert otoriter yönetimiyle tartışmalı bir figür olarak kalmıştır. Onun dönemi, imparatorluğun son büyük direniş yıllarına ve büyük dönüşümlere tanıklık etmiştir.")
        val besinciMehmed= PadisahData(id = 38,
            isim = "5.Mehmed",
            dogum = 1844,
            olum = 1918,
            donem = "",
            saltanatSuresi = 9,
            gorsel = R.drawable.besinci_mehmed,
            lakap = "Reşâd",
            savaslari = "Trablusgarp Savaşı,Balkan Savaşları,I. Dünya Savaşı",
            cocuklari = "Şehzade Mehmed Ziyaeddin Efendi,Şehzade Mahmud Necmeddin Efendi,Şehzade Ömer Hilmi Efendi",
            biyografi = "V. Mehmed, 2 Kasım 1861’de İstanbul’da doğdu. Babası Sultan Abdülaziz, annesi Gevheri Kadın’dır. 1909 yılında II. Abdülhamid’in tahttan indirilmesinin ardından Osmanlı tahtına çıktı. Saltanatı 1918 yılına kadar sürdü. Dönemi, Birinci Dünya Savaşı’nın Osmanlı üzerindeki etkileriyle şekillendi.\n" +
                    "\n" +
                    "Tahta geçtiğinde, İmparatorluk iç ve dış sorunlarla boğuşuyordu. V. Mehmed, savaş yıllarında devlet yönetiminde daha çok padişahın sembolik bir figür olduğu, gerçek gücün İttihat ve Terakki Cemiyeti’nin elinde olduğu bir dönemde görev yaptı. Ancak yine de, devlet işlerinde temsil yetkisi vardı ve imzalar onun adına atılıyordu.\n" +
                    "\n" +
                    "Birinci Dünya Savaşı boyunca Osmanlı, Almanya’nın yanında savaşa katıldı. Savaş sürecinde cephelerde ve iç politikada büyük çalkantılar yaşandı. Savaşın sonunda 1918’de imzalanan Mondros Mütarekesi, Osmanlı’nın fiilen teslimiyeti anlamına geldi.\n" +
                    "\n" +
                    "V. Mehmed, 4 Temmuz 1918’de vefat etti. Cenazesi, İstanbul’da Yavuz Sultan Selim Camii haziresine defnedildi. Saltanatı Osmanlı’nın son dönemine denk geldi ve imparatorluğun çöküş sürecinde sembolik bir rol üstlendi.")
        val altinciMehmed= PadisahData(id = 39,
            isim = "6.Mehmed",
            dogum = 1861,
            olum = 1926,
            saltanatSuresi = 4,
            donem = "",
            lakap = "Vahîdüddîn",
            gorsel = R.drawable.altinci_mehmed,
            cocuklari = "Şehzade Mehmed Ertuğrul Efendi,Fenire Sultan,Falma Ulviye Sultan,Rukiye Sabiha Sultan",
            savaslari = "",
            biyografi = "VI. Mehmet, 14 Ocak 1861'de İstanbul'da doğdu. Babası Sultan Abdülmecid, annesi Gülüstü Kadın'dır. 1918'de babası V. Mehmed'in vefatı üzerine tahta çıktı. Saltanatı, Osmanlı İmparatorluğu'nun son dönemlerine ve Türkiye Cumhuriyeti'nin kuruluş sürecine denk gelir. Birinci Dünya Savaşı'nın ardından imzalanan Mondros Mütarekesi sonrası ülke işgal altındaydı ve imparatorluk ciddi siyasi, ekonomik ve sosyal sorunlarla karşı karşıyaydı.\n" +
                    "\n" +
                    "VI. Mehmet, saltanatı boyunca hem işgal güçleriyle hem de iç siyasi muhalefetle mücadele etti. İstanbul'dan Anadolu'daki direniş hareketini desteklemeye çalıştı ancak güçleri kısıtlıydı. Mustafa Kemal Paşa'nın önderliğinde başlayan Kurtuluş Savaşı döneminde padişahın yetkileri giderek azaldı. 1922'de saltanat kaldırıldı ve kendisi 17 Kasım 1922'de yurt dışına sürgüne gönderildi. Yaşamının geri kalanını Avrupa'da sürgünde geçirdi.\n" +
                    "\n" +
                    "6. Mehmet, 16 Mayıs 1926'da Sanremo, İtalya'da vefat etti. Cenazesi Türkiye'ye getirilmeyerek, sürgünde defnedildi. Osmanlı'nın son padişahı olarak, tarih içinde imparatorluğun son dönemlerine ve modern Türkiye'nin kuruluşuna tanıklık eden önemli bir figürdür.")
        padisahList.add(osman)
        padisahList.add(orhan)
        padisahList.add(muratHudavendigar)
        padisahList.add(birinciBeyazid)
        padisahList.add(isaCelebi)
        padisahList.add(EmirSuleymanCelebi)
        padisahList.add(musaCelebi)
        padisahList.add(birinciMehmed)
        padisahList.add(ikinciMurad)
        padisahList.add(ikinciMehmed)
        padisahList.add(ikinciBayezid)
        padisahList.add(birinciSelim)
        padisahList.add(birinciSuleyman)
        padisahList.add(ikinciSelim)
        padisahList.add(ucuncuMurad)
        padisahList.add(ucuncuMehmed)
        padisahList.add(birinciAhmed)
        padisahList.add(birinciMustafa)
        padisahList.add(ikinciOsman)
        padisahList.add(dorduncuMurad)
        padisahList.add(ibrahim)
        padisahList.add(dorduncuMehmed)
        padisahList.add(ikinciSuleyman)
        padisahList.add(ikinciAhmed)
        padisahList.add(ikinciMustafa)
        padisahList.add(ucuncuAhmed)
        padisahList.add(birinciMahmud)
        padisahList.add(ucuncuOsman)
        padisahList.add(ucuncuMustafa)
        padisahList.add(birinciAbdulhamid)
        padisahList.add(ucuncuSelim)
        padisahList.add(dorduncuMustafa)
        padisahList.add(ikinciMahmud)
        padisahList.add(abdulmecid)
        padisahList.add(abdulaziz)
        padisahList.add(besinciMurad)
        padisahList.add(ikinciAbdulhamid)
        padisahList.add(besinciMehmed)
        padisahList.add(altinciMehmed)
    }
    private fun savasVerisi(){
        val koyunHisarSavasi= SavaslarData(id = 1,doneminPadisahi = "1.Osman Bey", savas = "KoyunHisar Muharebesi", devlet2 = "Bizans İmparatorluğu", dusmanKomutan = "George Mouzalon", tarih = "1302", antlasma = "", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, dostGucleri = 5000, dusmanGucleri = 2000, devlet1 = "Osmanlı Beyliği", sonuc = "Kesin Osmanlı Zaferi")
        val dimbosSavasi= SavaslarData(id = 2, doneminPadisahi = "1.Osman Bey", savas = "Dimbos Savaşı", devlet2 = "Bizans İmparatorluğu", dusmanKomutan = "Local Governors", tarih = "1303", antlasma = "", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, dostGucleri = 3000, dusmanGucleri = 10000, devlet1 = "Osmanlı Beyliği", sonuc = "Kesin Osmanlı Zaferi")
        val sakaryaNehriFetihleri= SavaslarData(id = 3, doneminPadisahi = "1.Osman Bey", savas = "Lefke,Akhisar,Geyve,Gölpazarı savaşları", devlet2 = "Bizans İmparatorluğu", dusmanKomutan = "Çeşitli Tekfurlar", tarih = "1313,1315", antlasma = "", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, dostGucleri = 3000, dusmanGucleri = 5000, devlet1 = "Osmanlı Beyliği", sonuc = "Kesin Osmanlı Zaferi")
        val marmaraBolgesiFetihleri= SavaslarData(id = 4, devlet1 = "Osmanlı Beyliği", doneminPadisahi = "Orhan Bey", savas = "Mudanya,Yalova,Akyazı,Mudurnu,Sapanca,Samandıra Savaşları", devlet2 = "Bizans İmparatorluğu","Çeşitli Bizans Tekfurları", tarih = "1321-1326", antlasma = "", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, dostGucleri = 6000, dusmanGucleri = 8000, sonuc = "Kesin Osmanlı zaferi")
        val bursaKusatmasi= SavaslarData(id = 5, devlet1 = "Osmanlı Beyliği", doneminPadisahi = "Orhan Bey", savas = "Bursa Kuşatması", devlet2 = "Bizans İmparatorluğu", dusmanKomutan = "Saroz", tarih = "1326", antlasma = "", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, dostGucleri = 6000, dusmanGucleri = 1500, sonuc = "Kesin Osmanlı Zaferi")
        val eskihisarSavasi= SavaslarData(id = 6, devlet1 = "Osmanlı Beyliği", devlet2 = "Bizans İmaparatorluğu", doneminPadisahi = "Orhan Bey", dusmanKomutan = "III. Andronikos", dostGucleri = 8000, dusmanGucleri = 6000, bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, antlasma = "", savas = "Eskihisar (Palekanon)", sonuc = "Kesin Osmanlı Zaferi", tarih = "1329")
        val iznikFethi= SavaslarData(id = 7, devlet1 = "Osmanlı Beyliği", devlet2 = "Bizans İmparatorluğu", savas = "Nicea Kuşatması (İznik'in Fethi)", doneminPadisahi = "Orhan Bey", dusmanKomutan = "İznik Tekfuru", dusmanGucleri = 2300, dostGucleri = 10000, tarih =  "1331", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, sonuc = "Kesin Osmanlı Zaferi", antlasma = "")
        val izmitFethi= SavaslarData(id = 8, devlet1 = "Osmanlı Devleti", devlet2 = "Bizans İmparatorluğu", doneminPadisahi = "Orhan Gazi", dusmanKomutan = "İzmit Tekfuru", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, savas = "İzmit Kuşatması", tarih = "1337", antlasma = "", dostGucleri = 7400, dusmanGucleri = 20000, sonuc = "Kesin Osmanlı Zaferi")
        val geliboluSavaslari= SavaslarData(id = 9, devlet1 = "Osmanlı Devleti", devlet2 = "Bizans İmparatorluğu", savas = "1. ve 2. Gelibolu Savaşları", tarih = "1353 ve dolayları", antlasma = "", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, dusmanKomutan = "VI. İoannis", doneminPadisahi = "Orhan Gazi", dusmanGucleri = 10000, dostGucleri = 15000, sonuc = "Kesin Osmanlı Zaferi")
        val sazlidereSavasi= SavaslarData(id = 10, devlet1 = "Osmanlı Devleti", devlet2 = "Bizans İmparatorluğu", savas = "Sazlıdere Savaşı (Edirne'nin Fethi)", tarih = "1361", doneminPadisahi = "Orhan Gazi", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, antlasma = "", dostGucleri = 10000, dusmanGucleri = 2000, dusmanKomutan = "Edirne Tekfuru", sonuc = "Kesin Osmanlı Zaferi")
        val sirpsindigiSavasi= SavaslarData(id = 11, devlet1 = "Osmanlı Devleti", devlet2 = "Sırp İmparatorluğu ve 4 devlet", doneminPadisahi = "1.Murad", dusmanKomutan = "Jovan Ugljesa,Vusakin Mrnjavcevic,1.Lui", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.sirp, antlasma = "", savas = "Sırpsındığı Savaşı", tarih = "1364", dostGucleri = 10000, dusmanGucleri = 60000, sonuc = "Kesin Osmanlı Zaferi")
        val cirmenSavasi= SavaslarData(id = 12, devlet1 = "Osmanlı Devleti", doneminPadisahi = "1.Murad", savas = "Çirmen Savaşı", devlet2 = "Sırbıstan Prensliği", tarih = "1371", antlasma = "", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.sirbistan, dusmanKomutan = "Vukašin Mrnjavčević,Jovan Uglješa", dostGucleri = 4000, dusmanGucleri = 70000, sonuc = "Kesin Osmanlı Zaferi")
        val birinciKosova= SavaslarData(id = 13, devlet1 = "Osmanlı Devleti", doneminPadisahi = "1.Murad", savas = "1.Kosova Savaşı", devlet2 = "Moravyalı Sırbistan", tarih = "1389", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.moravyalisirbistan, antlasma = "", dusmanKomutan = "Lazar Hrebelyanovic", dostGucleri = 40000, dusmanGucleri = 35000, sonuc = "Kesin Osmanlı Zaferi")
        val birincIstanbulKusatmasi= SavaslarData(id = 14, devlet1 = "Osmanlı Devleti", doneminPadisahi = "1.Bayezid", savas = "1.İstanbul Kuşatması", tarih = "1391", devlet2 = "Bizans İmparatorluğu", bayrak1 = R.drawable.osmanli, bayrak2 = R.drawable.bizans, antlasma = "", dusmanKomutan = "2.Manuil", dostGucleri = null,dusmanGucleri = null, sonuc = "Sonuçsuz")
        savasList.add(koyunHisarSavasi)
        savasList.add(dimbosSavasi)
        savasList.add(sakaryaNehriFetihleri)
        savasList.add(marmaraBolgesiFetihleri)
        savasList.add(bursaKusatmasi)
        savasList.add(eskihisarSavasi)
        savasList.add(iznikFethi)
        savasList.add(izmitFethi)
        savasList.add(geliboluSavaslari)
        savasList.add(sazlidereSavasi)
        savasList.add(sirpsindigiSavasi)
        savasList.add(cirmenSavasi)
        savasList.add(birinciKosova)
        savasList.add(birincIstanbulKusatmasi)
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OttomanHistoryTheme {

    }
}