package model;
/**
 * aktiviteetti olio
 * @author Roni, Tommi, Marika, Ville
 *
 */

public class Activity implements Activity_IF{
    private int id;
    private String name;
    private int spid;
    private String location;
    private String description;

    private Shift_IF[] shifts;

/**
 * constructori
 */
    public Activity() {
    }
/**
 * parametrillinen constructori
 * @param name nimi
 * @param spid palveluntuottajan id numero
 * @param location sijainti
 * @param description kuvaus
 */
    public Activity(String name, int spid, String location, String description) {
        this.name = name;
        this.spid = spid;
        this.location = location;
        this.description = description;
    }
/**
 * parametrillinen constructori
 * @param id id numero
 * @param name nimi
 * @param spid palveluntuottajan id numero
 * @param location sijainti
 * @param description kuvaus
 */
    public Activity(int id, String name, int spid, String location, String description) {
        this.id = id;
        this.name = name;
        this.spid = spid;
        this.location = location;
        this.description = description;
    }
    /**
     * id numeron getteri
     * @return id
     */
    public int getId() {
        return id;
    }
    /**id numeron setteri
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**nimen getteri
     * @return name
     */
    public String getName() {
        return name;
    }
    /**nimen setteri
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**palvelun tuottajan id numeron getteri
     * @return spid
     */
    public int getSpid() {
        return spid;
    }
    /**palveluntuottajan id numeron setteri
     * @param spid
     */
    public void setSpid(int spid) {
        this.spid = spid;
    }
    /**sijainnin getteri
     * @return location
     */
    public String getLocation() {
        return location;
    }
    /**sijainnin setteri
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**kuvauksen palautus
     * @return description
     */
    public String getDescription() {
        return description;
    }
    /**kuvauksen setteri
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


}
