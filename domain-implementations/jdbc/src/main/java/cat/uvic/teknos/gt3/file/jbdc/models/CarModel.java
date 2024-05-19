package cat.uvic.teknos.gt3.file.jbdc.models;

public class CarModel implements cat.uvic.teknos.gt3.domain.models.CarModel {
    private int modelId;
    private int brandId;
    private String model;

    @Override
    public int getModelId() {
        return modelId;
    }

    @Override
    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    @Override
    public int getBrandId() {
        return brandId;
    }

    @Override
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }
}
