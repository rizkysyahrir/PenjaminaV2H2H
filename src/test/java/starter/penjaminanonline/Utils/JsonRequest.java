package starter.penjaminanonline.Utils;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Random;

public class JsonRequest {
    // Metode untuk menghasilkan JSON request
    public static String generateJsonRequest() {
        // Membuat objek Random
        Random random = new Random();

        // Menghasilkan nomor acak 16 digit untuk npwp
        StringBuilder random16DigitNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            random16DigitNumber.append(random.nextInt(10));
        }

        StringBuilder random4DigitNumber = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            random4DigitNumber.append(random.nextInt(10));
        }
        // Copy the first 4 digits
        StringBuilder random12DigitNumber = new StringBuilder(random4DigitNumber);

        // Generate random 8 digits for the last part
        for (int i = 0; i < 8; i++) {
            random12DigitNumber.append(random.nextInt(10));
        }
        String static4DigitNumber = "0895";

        int jumlahTenagaKerja = random.nextInt(100) + 1;
        int jangkaWaktu = random.nextInt(12) + 1;
        // Menghitung tanggal_awal berdasarkan hari ini
        LocalDate tanggalAwal = LocalDate.now();

        // Menghitung tanggal_akhir berdasarkan tanggal_awal dan jangka_waktu (randomNumber)
        LocalDate tanggalAkhir = tanggalAwal.plusMonths(jangkaWaktu).plusDays(random.nextInt(30));

        // Membuat objek JSON untuk request
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("alamat_debitur", "Koto Aro Kecamatan Siulak");
        jsonRequest.put("alamat_usaha", "Desa Koto Aro Kecamatan Siulak");
        jsonRequest.put("cif", "637470");
        jsonRequest.put("coverage", "77");
        jsonRequest.put("jangka_waktu", jangkaWaktu);
        jsonRequest.put("jenis_agunan", "31");
        jsonRequest.put("jenis_identitas", "1");
        jsonRequest.put("jenis_kelamin", "1");
        jsonRequest.put("jenis_kredit", "1");
        jsonRequest.put("jenis_kur", "6");
        jsonRequest.put("jenis_linkage", "N");
        jsonRequest.put("jenis_pengikatan", "9");
        jsonRequest.put("jumlah_tenaga_kerja", jumlahTenagaKerja);
        jsonRequest.put("kode_bank", "115");
        jsonRequest.put("kode_pekerjaan", "7");
        jsonRequest.put("kode_pos", "31760");
        jsonRequest.put("kode_sektor", "011000");
        jsonRequest.put("kode_uker", "010");
        jsonRequest.put("lembaga_linkage", "null");
        jsonRequest.put("modal_usaha", "89900000");
        jsonRequest.put("nama_debitur", "Rizky Syahrir");
        jsonRequest.put("nilai_agunan", "0");
        jsonRequest.put("no_hp", static4DigitNumber+random12DigitNumber);
        jsonRequest.put("no_identitas", random16DigitNumber.toString());
        jsonRequest.put("no_ijin_usaha", "140/07/KU/Pem-2024");
        jsonRequest.put("no_pk", random16DigitNumber.toString());
        jsonRequest.put("no_rekening", random16DigitNumber.toString());
        jsonRequest.put("no_telepon", "null");
        jsonRequest.put("nomor_aplikasi", random16DigitNumber.toString());
        jsonRequest.put("marital_status", "1");
        jsonRequest.put("omset_usaha", "3800000");
        jsonRequest.put("npwp", random16DigitNumber.toString());
        jsonRequest.put("plafon_kredit", "10000000");
        jsonRequest.put("status_kolektibilitas", "0");
        jsonRequest.put("status_lunas", "L");
        jsonRequest.put("suku_bunga", "3");
        jsonRequest.put("tanggal_akhir", tanggalAkhir.toString());
        jsonRequest.put("tanggal_awal", tanggalAwal.toString());
        jsonRequest.put("tanggal_lahir", "1992-06-16");
        jsonRequest.put("tanggal_mulai_usaha", "1992-09-16");
        jsonRequest.put("tanggal_pk", "2023-06-16");
        jsonRequest.put("tanggal_rekam", "2023-06-16");
        jsonRequest.put("negara_tujuan", "null");

        // Mengembalikan JSON request dalam bentuk String
        return jsonRequest.toString();
    }

    // Metode main untuk pengujian
    public static void main(String[] args) {
        // Memanggil metode generateJsonRequest
        String jsonString = generateJsonRequest();
//        // Menampilkan JSON request
//        System.out.println(jsonString);
    }
}
