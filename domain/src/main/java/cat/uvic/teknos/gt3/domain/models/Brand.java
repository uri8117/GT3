package cat.uvic.teknos.gt3.domain.models;

public interface Brand {
    int getBrandId();

    void setBrandId(int brandId);

    String getName();

    void setName(String brandName);

    String getCountryOfOrigin();

    void setCountryOfOrigin(String originCountry);

    String getContactInfo();

    void setContactInfo(String contactInfo);
}
