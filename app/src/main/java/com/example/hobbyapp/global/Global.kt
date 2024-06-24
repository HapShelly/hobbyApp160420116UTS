package com.example.hobbyapp.global

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import com.example.hobbyapp.model.News
import com.squareup.picasso.Picasso

@BindingAdapter("setImageUrl")
fun setImage(imageView: ImageView, url: String) {
    Picasso.get().load(url).resize(800,800).centerCrop().into(imageView);
}
class Global{

    companion object {
        val baseUrl = "http://192.168.18.40/ANMP/hobbyApp/"

        fun makeAlert(context: Context, title:String, message:String){
            val alert = AlertDialog.Builder(context)

            alert.setTitle(title)
            alert.setMessage(message)
            alert.setPositiveButton("OK") { _,_ ->}
            alert.show()
        }

        val listOfNews = arrayListOf(
            News(
                title = "BLACKPINK’s Lisa Menyatakan Gaya ‘ROCKSTAR’ dalam Teaser Terbaru",
                imgUrl = "https://www.allkpop.com/upload/2024/06/content/200853/1718888029-20240620-lisa.jpg",
                preview = "Lisa dari BLACKPINK telah mengungkapkan gambar teaser untuk single solo terbarunya ‘ROCKSTAR’. Dalam foto konsep tersebut, Lisa tampil mencolok dengan anting-anting besar dan kacamata hitam. Rilis solo mendatangnya ‘ROCKSTAR’ dijadwalkan akan diluncurkan pada tanggal 28 Juni pukul 09:00 KST",
                content = "Lisa, anggota dari grup fenomenal K-pop BLACKPINK, siap membuat pernyataan berani dengan teaser terbaru untuk single solo mendatangnya, ‘ROCKSTAR’. Dalam gambar teaser yang dirilis, Lisa terlihat memukau dengan aksesori besar dan kacamata hitam yang ikonik, menjanjikan era baru dalam karir solonya.\n" +
                        "\n" +
                        "Single ‘ROCKSTAR’ ini akan menjadi comeback solo pertama Lisa sejak debut solonya dengan ‘LALISA’ pada tahun 2021. Penggemar telah menantikan dengan penuh antisipasi untuk melihat apa yang akan dibawa oleh Lisa kali ini, terutama setelah kesuksesan besar dari debut solonya.\n" +
                        "\n" +
                        "Dalam teaser yang telah dirilis, Lisa menampilkan kepercayaan diri dan kehadiran yang karismatik, meninggalkan penggemar dalam kegembiraan. Klip tersebut dengan cepat menjadi viral, mendapatkan 1 juta suka hanya dalam beberapa jam setelah rilis. Di akhir teaser, Anda dapat mendengar Lisa berkata, “Baby I’m a rockstar,” yang mungkin mengisyaratkan nama lagu tersebut.\n" +
                        "\n" +
                        "Penggemar BLACKPINK dan musik K-pop secara umum telah menunjukkan dukungan mereka melalui media sosial, dengan banyak yang menyatakan kegembiraan mereka untuk mendengar musik baru dari Lisa. Dengan gaya yang selalu berubah dan kemampuan untuk menyesuaikan diri dengan tren terbaru, Lisa tidak diragukan lagi akan membawa sesuatu yang segar dan menarik ke industri musik.",
                author = "Germaine-Jay",
                createdAt = "21 Juni 2024"
            ),
            News(
                title = "BABYMONSTER Bersiap Membuat Gelombang dengan Single Digital ‘FOREVER’",
                imgUrl = "https://www.allkpop.com/upload/2024/06/content/201120/1718896834-header-photo.jpg",
                preview = "Grup idola K-pop BABYMONSTER telah merilis teaser sampul majalah untuk single digital mereka yang akan datang, ‘FOREVER’. Pada tanggal 21 Juni tengah malam KST, para anggota grup berpose untuk sampul edisi Juli 2024 dari majalah ‘FOREVER’, memancarkan karisma yang menakjubkan di latar belakang malam",
                content = "BABYMONSTER, fenomena baru di dunia K-pop, siap untuk kembali ke panggung musik dengan single digital terbaru mereka, ‘FOREVER’. Setelah sukses besar dengan lagu debut mereka ‘Batter Up’ dan lagu lanjutan ‘Stuck in the Middle’, BABYMONSTER tidak menunjukkan tanda-tanda untuk berhenti. Dengan teaser yang dirilis pada tanggal 19 Juni tengah malam KST, mereka mengumumkan tanggal rilis single ‘FOREVER’ yang sangat ditunggu-tunggu, yaitu 1 Juli pukul 00:00 KST.\n" +
                        "\n" +
                        "Single ‘FOREVER’ diharapkan akan melanjutkan kesuksesan yang telah diraih oleh BABYMONSTER. Video musik untuk lagu sebelumnya ‘SHEESH’ berhasil mencapai lebih dari 100 juta tayangan dalam waktu 10 hari, menetapkan standar baru untuk lagu debut oleh grup idola K-pop perempuan.\n" +
                        "\n" +
                        "BABYMONSTER telah terus membangun status mereka sebagai grup idola generasi kelima yang terkemuka dengan merilis lagu debut ‘Batter Up’ dan lagu lanjutan ‘Stuck in the Middle’ hanya dalam waktu empat bulan. Kini, dengan ‘FOREVER’, mereka bersiap untuk menaklukkan musim panas dengan suara yang segar dan energik.\n" +
                        "\n" +
                        "Dengan setiap rilis, BABYMONSTER terus menunjukkan keberanian dan inovasi dalam musik mereka. ‘FOREVER’ dijanjikan akan menjadi sebuah lagu yang tidak hanya menangkap esensi dari suara unik BABYMONSTER tetapi juga menandai evolusi mereka sebagai artis.",
                author = "Sophie-Ha",
                createdAt = "21 Juni 2024"
            ),
            News(
                title = "Karina aespa dan Pemain Bintang Bersatu untuk Memecahkan Misteri Supranatural di Netflix",
                imgUrl = "https://www.allkpop.com/upload/2024/06/content/200853/1718888029-20240620-lisa.jpg",
                preview = "Karina dari aespa siap menghadapi tantangan baru dalam acara varietas petualangan misteri. Netflix mengumumkan pada tanggal 4 bahwa Karina, bersama dengan komedian Lee Yong-jin, John Park, Lee Eun-ji, Hyeri, dan Kim Do-hoon, akan membintangi konten orisinal Netflix baru, \"Mystery Investigation Team\"",
                content = "Karina dari aespa akan mengambil peran baru yang menarik dalam sebuah acara varietas petualangan misteri yang akan tayang di Netflix. Dalam acara yang berjudul \"Mystery Investigation Team\", Karina akan bergabung dengan deretan bintang lainnya seperti komedian Lee Yong-jin, penyanyi John Park, aktris Lee Eun-ji, idola K-pop Hyeri, dan Kim Do-hoon untuk membentuk sebuah tim penyelidikan misteri.\n" +
                        "\n" +
                        "Tim yang terdiri dari enam anggota ini akan bekerja sama untuk memecahkan kasus-kasus aneh yang terjadi. Poster utama dan trailer acara telah dirilis, menampilkan anggota tim penyelidikan bersama dengan makhluk tak dikenal dan sosok aneh. Anggota tim digambarkan dengan ekspresi serius saat mereka melacak kasus, dipasangkan dengan tagline \"Kasus Aneh, Penyelidikan Jenius\", meningkatkan antisipasi untuk penampilan mereka.\n" +
                        "\n" +
                        "Trailer tersebut menangkap set yang realistis dan situasi tak terduga. Anggota tim berjuang untuk menemukan petunjuk di tengah ketegangan yang ekstrem. Kerja sama tim menjadi salah satu sorotan dari acara tersebut. Pada satu titik, ketika semua orang merasa takut, Lee Yong-jin dengan humoris berkata, \"Karina, kamu duluan,\" menambahkan humor khasnya ke dalam pengalaman menonton.\n" +
                        "\n" +
                        "Disutradarai oleh PD Jung Jong-yeon, yang dikenal dengan pembangunan dunia unik dalam acara seperti \"Great Escape\", \"Girls’ High School Mystery Class\", dan \"The Devil’s Plan\", dia menjelaskan bahwa acara ini menampilkan sebuah grup yang hanya menangani misteri supranatural, mirip dengan \"X-Files\". Tim produksi “Mystery Investigation Team” menyebutkan, \"Proses tak terduga dari tim penyelidikan yang memecahkan kasus misterius diharapkan dapat merangsang insting detektif para penonton\". \"Mystery Investigation Team\" akan tersedia di Netflix mulai tanggal 18 Juni 2024.",
                author = "Alec06",
                createdAt = "9 Juni 2024"
            ),
            News(
                title = "Ahli Industri Musik Membahas Apakah ILLIT Meniru NewJeans",
                imgUrl = "https://www.allkpop.com/upload/2024/05/content/141058/1715698710-header-photo.jpg",
                preview = "Para ahli industri musik telah mengungkapkan keprihatinan mereka atas kemiripan yang mencolok dalam \"konsep\" antara ILLIT dan NewJeans, bukan masalah \"plagiarisme\" atau \"peniruan\" secara langsung.",
                content = "Konflik antara HYBE dan ADOR muncul dari dugaan peniruan ILLIT terhadap NewJeans, yang menjadi inti dari perselisihan. Di pusat perdebatan ini adalah klausul non-kompetisi, yang dinegosiasikan antara Min Hee Jin, CEO ADOR, dan HYBE sebagai bagian dari perjanjian pemegang saham yang bertepatan dengan debut ILLIT. Ketika kekhawatiran muncul tentang whistleblowing internal dan kecurigaan peniruan ILLIT, HYBE menggunakan hak audit mereka dan membuatnya publik melalui media. Min Hee Jin menyatakan posisinya, menekankan, \"HYBE sangat melanggar pencapaian budaya ADOR dan artis-artisnya, termasuk NewJeans.\" Dia menyoroti, \"Belift Lab, label di bawah HYBE, meluncurkan ILLIT pada bulan Maret dan telah meniru NewJeans dalam berbagai aktivitas hiburan, mulai dari gaya rambut dan makeup hingga kostum, koreografi, fotografi, videografi, dan penampilan acara\".\n" +
                        "\n" +
                        "Kecurigaan bahwa ILLIT menjiplak NewJeans menyebar dan memuncak di komunitas online yang sering dikunjungi oleh kaum muda di usia 20-an, yang merupakan konsumen utama K-pop. Memang benar bahwa sejak debut mereka, ILLIT telah dievaluasi sebagai grup yang mirip dengan NewJeans. Kedua grup berada di bawah label HYBE, keduanya adalah grup idola perempuan dengan lima anggota, keduanya debut dengan musik digital yang cepat, keduanya mengadopsi konsep seragam sekolah yang imajinatif, dan koreografi mereka juga serupa. Selain itu, kedua grup membuat penampilan di acara merek fashion tak lama setelah debut mereka. Pengguna internet telah menunjukkan kemiripan antara sampul album dan karya seni NewJeans dan ILLIT, kartu foto yang disisipkan dalam album, dan ilustrasi 2D yang menggambarkan anggota sebagai karakter.\n" +
                        "\n" +
                        "Para ahli di industri musik telah sepakat tentang kemiripan antara NewJeans dan ILLIT. Seorang eksekutif dari agensi musik menyatakan, \"Kunci dari grup idola terletak pada konsep mereka. Bahkan jika ada perbedaan dalam detailnya, jika konsep keseluruhannya serupa, itu dapat dilihat sebagai pelanggaran yang jelas.\" Perwakilan lain dari agensi musik yang berbeda juga berkomentar, \"Terlepas dari kemiripan antara grup, jika grup dengan konsep serupa di bawah perusahaan induk yang sama debut pada bulan Maret, melakukan aktivitas pada bulan April, dan grup lain melakukan comeback pada bulan Mei, tampaknya ada kesalahan penjadwalan yang signifikan, terlepas dari niatnya.\" Ahli lain di industri musik juga dengan jelas menyatakan bahwa ILLIT telah sangat dipengaruhi oleh NewJeans.",
                author = "Sophie-Ha",
                createdAt = "14 Mei 2024"
            ),
            News(
                title = "BABYMONSTER Akan Debut Penampilan Langsung ‘LIKE THAT’ di ‘M Countdown’ Mnet",
                imgUrl = "https://www.allkpop.com/upload/2024/06/content/100840/1718023210-2024-06-10-9.png",
                preview = "YG Entertainment mengumumkan bahwa BABYMONSTER akan tampil di ‘M Countdown’ Mnet pada tanggal 13 dan 20, mengikuti perilisan video dance performance eksklusif untuk lagu ‘LIKE THAT’. Grup ini bertujuan untuk mempersembahkan penampilan panggung langsung.",
                content = "BABYMONSTER, grup idola K-pop yang sedang naik daun, siap membuat debut penampilan langsung mereka dengan lagu ‘LIKE THAT’ di acara musik populer ‘M Countdown’ Mnet. YG Entertainment mengonfirmasi bahwa penampilan ini akan berlangsung pada tanggal 13 dan 20, sebagai bagian dari promosi untuk lagu terbaru mereka.\n" +
                        "\n" +
                        "Keputusan ini diambil setelah perilisan video dance performance eksklusif untuk ‘LIKE THAT’, yang telah menarik perhatian penggemar dan media. Dengan penampilan langsung ini, BABYMONSTER ingin terhubung lebih dekat dengan penggemar melalui siaran musik, meskipun memiliki jadwal yang padat dengan pertemuan penggemar di luar negeri selama empat minggu mulai tanggal 8 Juni 2024.\n" +
                        "\n" +
                        "Antisipasi untuk penampilan ini sangat tinggi, karena ini akan menjadi pertama kalinya panggung ‘LIKE THAT’ ditampilkan di Korea. Grup ini diharapkan dapat kembali memikat penggemar musik dengan kemampuan penampilan langsung mereka yang mengesankan, yang mencakup koreografi yang adiktif, gaya yang stylish, serta vokal dan rap yang lancar.\n" +
                        "\n" +
                        "'Lagu ‘LIKE THAT’ sendiri adalah sebuah lagu yang menarik dengan aransemen minimalis dan hook yang mudah diingat. Video performance eksklusif untuk lagu ini telah melampaui 36 juta tayangan di YouTube, menunjukkan tren yang meningkat. Dengan rilis panggung ‘LIKE THAT’, popularitasnya diharapkan akan meningkat lebih jauh.\n" +
                        "\n" +
                        "Sementara itu, BABYMONSTER juga sedang mengadakan pertemuan penggemar pertama mereka, ‘[BABYMONSTER PRESENTS: SEE YOU THERE]’, dengan tujuh acara di lima kota. Mereka berencana untuk melanjutkan momentum mereka dengan merilis lagu baru pada bulan Juli dan meluncurkan album penuh pertama mereka pada musim gugur ini.",
                author = "Alec06",
                createdAt = "9 Juni 2024"
            ),
        )
    }
}
