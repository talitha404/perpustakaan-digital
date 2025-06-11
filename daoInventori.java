package perpustakaan;
import java.sql.*;
import java.util.*;

public class daoInventori {
    private Connection conn;

    public daoInventori(Connection conn) {
        this.conn = conn;
    }

    public void insertInventori(cInventori inv) throws SQLException {
        String sql = "INSERT INTO inventori (id_judul, jumlah_awal, lokasi_rak) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inv.getIdJudul());
            stmt.setInt(2, inv.getJumlahAwal());
            stmt.setString(3, inv.getLokasiRak());
            stmt.executeUpdate();
        }
    }

    public List<cInventori> getAllInventori() throws SQLException {
        List<cInventori> list = new ArrayList<>();
        String sql = "SELECT * FROM inventori";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new cInventori(
                    rs.getInt("id_inventori"),
                    rs.getInt("id_judul"),
                    rs.getInt("jumlah_awal"),
                    rs.getString("lokasi_rak")
                ));
            }
        }
        return list;
    }
}