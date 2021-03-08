public class Gun {

    private String modelName;
    private String weaponType;
    private int Wield;              // 1-handed / 2-handed
    private int productionYear;
    private Ammo ammo;
    private Distribution distribution;
    private Invention invention;
    private Price price;

    public Gun(String modelName, String weaponType, int Wield, int productionYear, Ammo ammo, Distribution distribution, Invention invention, Price price)
    {
        this.modelName = modelName;
        this.weaponType = weaponType;
        this.Wield = Wield;
        this.productionYear = productionYear;
        this.ammo = ammo;
        this.distribution = distribution;
        this.invention = invention;
        this.price = price;
    }

    public Gun(){
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName){
        this.modelName = modelName;
    }

    public String getWeaponType(){
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public int getWield(){
        return Wield;
    }

    public void setWield(int Wield){
        this.Wield = Wield;
    }

    public int getProductionYear(){
        return productionYear;
    }

    public void setProductionYear(int productionYear){
        this.productionYear = productionYear;
    }

    public Ammo getAmmo(){
        return ammo;
    }

    public void setAmmo(Ammo ammo){
        this.ammo = ammo;
    }

    public Distribution getDistribution(){
        return distribution;
    }

    public void setDistribution(Distribution distribution){
        this.distribution = distribution;
    }

    public Invention getInvention(){
        return invention;
    }

    public void setInvention(Invention invention){
        this.invention = invention;
    }

    public Price getPrice(){
        return price;
    }

    public void setPrice(Price price){
        this.price = price;
    }
}
