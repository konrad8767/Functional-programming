public class Ammo {

    private float caliber; // in milimeters
    private int firingRate; // in round per minute
    private int ammoCapacity;

    public Ammo(float caliber, int firingRate, int ammoCapacity)
    {
        this.caliber = caliber;
        this.firingRate = firingRate;
        this.ammoCapacity = ammoCapacity;
    }

    public Ammo(){
    }

    public float getCaliber(){
        return caliber;
    }

    public void setCaliber(float caliber){
        this.caliber = caliber;
    }

    public int getFiringRate(){
        return firingRate;
    }

    public void setFiringRate(int firingRate){
        this.firingRate = firingRate;
    }

    public int getAmmoCapacity(){
        return ammoCapacity;
    }

    public void setAmmoCapacity(int ammoCapacity){
        this.ammoCapacity = ammoCapacity;
    }
}
