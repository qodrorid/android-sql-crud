package qodr.sqlitecrud.model;

/**
 * Created by zainal on 2/10/17 - 9:45 PM.
 * Zainal Fahrudin
 * fnzainal@gmail.com
 */
public class SantriModel {

    public String getId_santri() {
        return id_santri;
    }

    public void setId_santri(String id_santri) {
        this.id_santri = id_santri;
    }

    public String getNama_santri() {
        return nama_santri;
    }

    public void setNama_santri(String nama_santri) {
        this.nama_santri = nama_santri;
    }

    public String getAsal_kota() {
        return asal_kota;
    }

    public void setAsal_kota(String asal_kota) {
        this.asal_kota = asal_kota;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public void setSkill_santri(String skill_santri) {
        this.skill_santri = skill_santri;
    }

    private String id_santri = "id_santri";
    private String nama_santri = "nama_santri";
    private String asal_kota = "asal_kota";
    private String tgl_lahir = "tgl_lahir";

    public String getSkill_santri() {
        return skill_santri;
    }

    private String skill_santri = "skill";

}
