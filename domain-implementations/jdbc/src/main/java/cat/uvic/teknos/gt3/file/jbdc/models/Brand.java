package cat.uvic.teknos.gt3.file.jbdc.models;

public class Brand implements cat.uvic.teknos.gt3.domain.models.Brand {
    private int brandId;
    private String name;
    private String countryOfOrigin;
    private String contactInfo;

    @Override
    public int getBrandId() {
        return brandId;
    }

    @Override
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    @Override
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    @Override
    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
