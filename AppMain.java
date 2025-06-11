package perpustakaan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        Connection conn = DBConnection.connect();

        if (conn != null) {
            System.out.println("Koneksi ke database berhasil!");
        } else {
            System.out.println("Koneksi ke database gagal!");
            return; // Keluar dari aplikasi jika koneksi gagal
        }

        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== Menu Perpustakaan Digital ===");
            System.out.println("1. Login sebagai Pengunjung Lama");
            System.out.println("2. Daftar sebagai Pengunjung Baru");
            System.out.println("3. Login sebagai Admin");
            System.out.println("4. Keluar");

            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    menuPengunjung(scanner, conn);
                    break;
                case 2:
                    daftarPengunjung(scanner, conn);
                    break;
                case 3:
                    menuAdmin(scanner, conn);
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        } while (pilihan != 4);

        // Tutup koneksi database setelah aplikasi selesai
        try {
            conn.close();
            System.out.println("Koneksi database ditutup.");
        } catch (Exception e) {
            System.out.println("Gagal menutup koneksi: " + e.getMessage());
        }

        scanner.close();
    }

    public static void menuPengunjung(Scanner scanner, Connection conn) {
        int pilihan;
        do {
            System.out.println("\n=== Menu Pengunjung Lama ===");
            System.out.println("1. Pinjam Buku");
            System.out.println("2. Pengembalian Buku");
            System.out.println("3. Laporan Buku Hilang");
            System.out.println("4. Cari Buku");
            System.out.println("5. Kembali ke Menu Utama");

            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.println("Fitur peminjaman buku belum dikembangkan.");
                    break;
                case 2:
                    System.out.println("Fitur pengembalian buku belum dikembangkan.");
                    break;
                case 3:
                    System.out.println("Fitur laporan buku hilang belum dikembangkan.");
                    break;
                case 4:
                    System.out.println("Fitur pencarian buku belum dikembangkan.");
                    break;
                case 5:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        } while (pilihan != 5);
    }

    public static void daftarPengunjung(Scanner scanner, Connection conn) {
        System.out.println("\n=== Pendaftaran Pengunjung Baru ===");
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Email: ");
        String email = scanner.nextLine();
        System.out.print("Masukkan Telepon: ");
        String telepon = scanner.nextLine();
        System.out.print("Masukkan Alamat: ");
        String alamat = scanner.nextLine();

        System.out.println("Akun member berhasil dibuat untuk " + nama);
    }

    public static void menuAdmin(Scanner scanner, Connection conn) {
        int pilihan;
        daoUser userDAO = new daoUser(conn);
        daoBuku bukuDAO = new daoBuku (conn);
        cUser user = null; 
        cBuku buku = null;
        String nama, role, password, email, telepon, alamat, input;
        //String judul, penulis, penerbit, tahunTerbit,idKategori;
        
        do {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Update Buku");
            System.out.println("4. Cari Buku");
            System.out.println("5. Lihat Buku");
            System.out.println("6. Tambah User");
            System.out.println("7. Hapus User");
            System.out.println("8. Update User");
            System.out.println("9. Cari User");
            System.out.println("10. Lihat User");          
            System.out.println("11. Kembali ke Menu Utama");

            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
               case 1:
                    System.out.println("\n=== Tambah Buku ===");
                    System.out.print("Masukkan Judul: ");
                    String judul = scanner.nextLine();
                    System.out.print("Masukkan Penulis: ");
                    String penulis = scanner.nextLine();
                    System.out.print("Masukkan Penerbit: ");
                    String penerbit = scanner.nextLine();
                    System.out.print("Masukkan Tahun Terbit: ");
                    int tahunTerbit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan ID Kategori: ");
                    int idKategori = scanner.nextInt();
                    scanner.nextLine();

                    buku = new cBuku(0, judul, penulis, penerbit, tahunTerbit, idKategori);

                    try {
                        bukuDAO.insertBuku(buku);
                        System.out.println("Buku berhasil ditambahkan: " + judul);
                    } catch (SQLException e) {
                        System.out.println("Gagal menambahkan buku: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n=== Hapus Buku ===");
                    System.out.print("Masukkan ID Buku yang ingin dihapus: ");
                    int idJudulHapus = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        bukuDAO.deleteBuku(idJudulHapus);
                    } catch (SQLException e) {
                        System.out.println("Gagal menghapus buku: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("\n=== Update Buku ===");
                    System.out.print("Masukkan ID Buku yang ingin diperbarui: ");
                    int idJudulUpdate = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Masukkan Judul Baru: ");
                    judul = scanner.nextLine();
                    System.out.print("Masukkan Penulis Baru: ");
                    penulis = scanner.nextLine();
                    System.out.print("Masukkan Penerbit Baru: ");
                    penerbit = scanner.nextLine();
                    System.out.print("Masukkan Tahun Terbit Baru: ");
                    tahunTerbit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan ID Kategori Baru: ");
                    idKategori = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        bukuDAO.updateBuku(idJudulUpdate, judul, penulis, penerbit, tahunTerbit, idKategori);
                    } catch (SQLException e) {
                        System.out.println("Gagal memperbarui buku: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("\n=== Cari Buku ===");
                    System.out.print("Masukkan kata kunci pencarian (Judul, Penulis, atau Penerbit): ");
                    String keyword = scanner.nextLine();

                    try {
                        List<cBuku> hasilPencarian = bukuDAO.searchBuku(keyword);
                        if (hasilPencarian.isEmpty()) {
                            System.out.println("Tidak ada buku yang cocok dengan kata kunci: " + keyword);
                        } else {
                            System.out.println("\n?Hasil Pencarian:");
                            System.out.println("ID\tJudul\tPenulis\tPenerbit\tTahun\tKategori");
                            System.out.println("------------------------------------------------------------");
                            for (cBuku b : hasilPencarian) {
                                System.out.println(b.getIdJudul() + "\t" + b.getJudul() + "\t" + b.getPenulis() + "\t" + b.getPenerbit() + "\t" + b.getTahunTerbit() + "\t" + b.getIdKategori());
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Gagal mencari buku: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\n=== Daftar Buku ===");

                    try {
                        List<cBuku> daftarBuku = bukuDAO.getAllBuku();
                        if (daftarBuku.isEmpty()) {
                            System.out.println("Tidak ada buku yang terdaftar.");
                        } else {
                            System.out.println("ID\tJudul\tPenulis\tPenerbit\tTahun\tKategori");
                            System.out.println("------------------------------------------------------------");
                            for (cBuku b : daftarBuku) {
                                System.out.println(b.getIdJudul() + "\t" + b.getJudul() + "\t" + b.getPenulis() + "\t" + b.getPenerbit() + "\t" + b.getTahunTerbit() + "\t" + b.getIdKategori());
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Gagal mengambil daftar buku: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("\n=== Tambah User Baru ===");
                    System.out.print("Masukkan Nama: ");
                    nama = scanner.nextLine();
                    System.out.print("Masukkan Role (Admin/Member): ");
                    role = scanner.nextLine();
                    System.out.print("Masukkan Password: ");
                    password = scanner.nextLine();
                    System.out.print("Masukkan Email: ");
                    email = scanner.nextLine();
                    System.out.print("Masukkan Telepon: ");
                    telepon = scanner.nextLine();
                    System.out.print("Masukkan Alamat: ");
                    alamat = scanner.nextLine();

                    user = new cUser(0, nama, role, password, email, telepon, alamat); // Gunakan objek yang sudah dideklarasikan
                    try {
                        userDAO.insertUser(user);
                        System.out.println("User berhasil ditambahkan: " + nama);
                    } catch (SQLException e) {
                        System.out.println("Gagal menambahkan user: " + e.getMessage());
                    }
                    break;


                case 7:
                    System.out.println("\n=== Hapus User ===");
                    System.out.print("Masukkan ID atau Nama User yang ingin dihapus: ");
                    input = scanner.nextLine();

                    try {
                        userDAO.deleteUser(input);
                    } catch (SQLException e) {
                        System.out.println("Gagal menghapus user: " + e.getMessage());
                    }
                    break;
                    
                case 8:
                    System.out.println("\n=== Update User ===");
                    System.out.print("Masukkan ID User yang ingin diperbarui: ");
                    int idUser = scanner.nextInt();
                    scanner.nextLine(); // Konsumsi newline

                    System.out.print("Masukkan Nama Baru: ");
                    nama = scanner.nextLine();
                    System.out.print("Masukkan Role Baru (Admin/Member): ");
                    role = scanner.nextLine();
                    System.out.print("Masukkan Password Baru: ");
                    password = scanner.nextLine();
                    System.out.print("Masukkan Email Baru: ");
                    email = scanner.nextLine();
                    System.out.print("Masukkan Telepon Baru: ");
                    telepon = scanner.nextLine();
                    System.out.print("Masukkan Alamat Baru: ");
                    alamat = scanner.nextLine();

                    try {
                        userDAO.updateUser(idUser, nama, role, password, email, telepon, alamat);
                    } catch (SQLException e) {
                        System.out.println("Gagal memperbarui user: " + e.getMessage());
                    }
                break;

                case 9:
                    System.out.println("\n=== Cari User ===");
                    System.out.print("Masukkan ID, Nama, atau Email User yang ingin dicari: ");
                    input = scanner.nextLine();

                    try {
                        user = userDAO.findUser(input);
                        if (user != null) {
                            System.out.println("\nUser ditemukan:");
                            System.out.println("ID      : " + user.getIdUser());
                            System.out.println("Nama    : " + user.getNama());
                            System.out.println("Role    : " + user.getRole());
                            System.out.println("Email   : " + user.getEmail());
                            System.out.println("Telepon : " + user.getTelepon());
                            System.out.println("Alamat  : " + user.getAlamat());
                        } else {
                            System.out.println("User dengan " + input + " tidak ditemukan.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Gagal mencari user: " + e.getMessage());
                    }
                break;

                case 10:
                    System.out.println("\n=== Daftar User ===");
                    try {
                        List<cUser> users = userDAO.getAllUsers();
                        if (users.isEmpty()) {
                            System.out.println("Tidak ada user yang terdaftar.");
                        } else {
                            System.out.println("ID\tNama\tRole\tEmail\tTelepon\tAlamat");
                            System.out.println("------------------------------------------------------------");
                            for (cUser u : users) {
                                System.out.println(u.getIdUser() + "\t" + u.getNama() + "\t" + u.getRole() + "\t"
                                        + u.getEmail() + "\t" + u.getTelepon() + "\t" + u.getAlamat());
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Terjadi kesalahan saat mengambil daftar user: " + e.getMessage());
                    }
                    break;

                case 11:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        } while (pilihan != 11);
    }
}



