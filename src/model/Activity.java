package model;
/**
 * Tämä on aktiviteetin luokka
 *@author Roni, Tommi, Marika, Ville
 *projekti työ otp-8
 */

public class Activity implements Activity_IF{
    private int id;
    private String name;
    private int spid;
    private String location;
    private String description;

    private Shift_IF[] shifts;

    /**
     * Tyhjä constructori
     */
    public Activity() {
    }
    /**
     * paremetrillinen constructori
     * @param name aktiviteetin nimi
     * @param spid palvelun tuottajan id
     * @param location palvelun sijainti
     * @param description palvelun kuvaus/lisätieto
     */
    public Activity(String name, int spid, String location, String description) {
        this.name = name;
        this.spid = spid;
        this.location = location;
        this.description = description;
    }
    /**
     * toinen parametrillinen constructori
     * @param id tietokannan luoma id numero
     * @param name aktiviteetin nimi
     * @param spid palvelun tuottajan id
     * @param location palvelun sijainti
     * @param description palvelun kuvaus/lisätieto
     */
    public Activity(int id, String name, int spid, String location, String description) {
        this.id = id;
        this.name = name;
        this.spid = spid;
        this.location = location;
        this.description = description;
    }
    /**
     * @return id palauttaa aktiviteetin id numeron
     */
    public int getId() {
        return id;
    }

    /**
     * @param id asettaa oliolle id numeron
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return name palauttaa aktiviteetin nimen
     */
    public String getName() {
        return name;
    }
    /**
     * @param name asettaa oliolle nimi arvon
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return spid palauttaa palvelun tarjoajan ip numeron
     */
    public int getSpid() {
        return spid;
    }
    /**
     * @param spid asettaa oliolle palvelu tuottajan id numero
     */
    public void setSpid(int spid) {
        this.spid = spid;
    }
    /**
     * @return location palauttaa sijainti tiedon
     */
    public String getLocation() {
        return location;
    }
    /**
     * @param location asettaa oliolle uuden sijainti tiedon
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * @return description palauttaa kuvaus arvon
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description asettaa uuden kuvaus arvon
     */
    public void setDescription(String description) {
        this.description = description;
    }


}
