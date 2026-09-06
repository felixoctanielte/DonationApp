package com.example.donationapp.data

import com.example.donationapp.data.model.DonationCampaign
import com.example.donationapp.data.model.News

/**
 * Data dummy lokal (bukan dari network).
 *
 * DIGUNAKAN OLEH:
 * - DummyDonationDataSource saja
 *
 * JANGAN dipanggil langsung dari Screen lagi.
 * Alur yang benar: Screen → ViewModel → Repository → DataSource → DummyData
 */
object DummyData {

    val campaigns = listOf(
        DonationCampaign(
            id = 1,
            title = "Bantuan Pendidikan Anak",
            description = "Membantu kebutuhan sekolah anak-anak yang membutuhkan.",
            collectedAmount = 35_000_000,
            targetAmount = 50_000_000,
            category = "Pendidikan"

        ),
        DonationCampaign(
            id = 2,
            title = "Bantuan Korban Banjir",
            description = "Menyediakan makanan, pakaian, dan tempat tinggal sementara.",
            collectedAmount = 72_000_000,
            targetAmount = 100_000_000,
            category = "Bencana"
        ),
        DonationCampaign(
            id = 3,
            title = "Pengobatan Pasien Kurang Mampu",
            description = "Membantu biaya pengobatan dan pembelian obat.",
            collectedAmount = 18_000_000,
            targetAmount = 40_000_000,
            category = "Kesehatan"
        )
    )

    val newList = listOf(
        News(
            id = 1,
            title = "Bantuan Pendidikan Telah Disalurkan",
            summary = "Donasi perlengkapan sekolah telah diterima oleh 100 siswa.",
            content = """
                Bantuan pendidikan dari para donatur telah disalurkan kepada 
                100 siswa yang membutuhkan.
                
                Bantuan yang diberikan berupa tas sekolah, buku, alat tulis, 
                seragam, dan biaya pendidikan.
                
                Terima kasih kepada seluruh donatur yang telah berpartisipasi.
            """.trimIndent(),
            date = "24 Juli 2026",
            category = "Pendidikan"
        ),
        News(
            id = 2,
            title = "Relawan Menyalurkan Bantuan Banjir",
            summary = "Tim relawan menyalurkan makanan dan pakaian kepada korban banjir.",
            content = """
                Tim relawan telah tiba di lokasi bencana dan menyalurkan 
                makanan, air bersih, pakaian, serta obat-obatan.
                
                Penyaluran bantuan dilakukan secara bertahap untuk memastikan 
                seluruh korban mendapatkan kebutuhan dasar.
            """.trimIndent(),
            date = "22 Juli 2026",
            category = "Bencana"
        ),
        News(
            id = 3,
            title = "Program Pemeriksaan Kesehatan Gratis",
            summary = "Program kesehatan gratis diikuti oleh ratusan masyarakat.",
            content = """
                Program pemeriksaan kesehatan gratis telah dilaksanakan dengan 
                dukungan para donatur dan tenaga kesehatan.
                
                Masyarakat mendapatkan pemeriksaan tekanan darah, gula darah, 
                konsultasi dokter, dan obat-obatan gratis.
            """.trimIndent(),
            date = "20 Juli 2026",
            category = "Kesehatan"
        )
    )
}
