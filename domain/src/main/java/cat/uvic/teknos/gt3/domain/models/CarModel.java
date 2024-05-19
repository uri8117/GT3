package cat.uvic.teknos.gt3.domain.models;

public interface CarModel {
    int getModelId();

    void setModelId(int modelId);

    int getBrandId();

    void setBrandId(int brandId);

    String getModel();

    void setModel(String modelName);
}
