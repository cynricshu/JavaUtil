package test.predict;

/**
 * User: Cynric
 * Date: 14-4-7
 * Time: 20:17
 */
public class UserHotelInfo {
    float hygiene;
    float service;
    float facility;
    float location;
    float general;
    int userId;
    int hotelId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public float getHygiene() {
        return hygiene;
    }

    public void setHygiene(float hygiene) {
        this.hygiene = hygiene;
    }

    public float getService() {
        return service;
    }

    public void setService(float service) {
        this.service = service;
    }

    public float getFacility() {
        return facility;
    }

    public void setFacility(float facility) {
        this.facility = facility;
    }

    public float getLocation() {
        return location;
    }

    public void setLocation(float location) {
        this.location = location;
    }

    public float getGeneral() {
        return general;
    }

    public void setGeneral(float general) {
        this.general = general;
    }
}
