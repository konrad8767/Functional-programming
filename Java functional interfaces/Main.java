import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Main {

    static Predicate<Ammo> isMachineGun = ammo -> ammo.getFiringRate() > 500
            && ammo.getAmmoCapacity() >= 20;
    static Predicate<Gun> usedInWw2 = gun -> gun.getProductionYear() <= 1945;
    static Predicate<Distribution> manufacturedOverMilion = distribution -> distribution.getManufacture() > 1;
    static Predicate<Invention> isInventedBeforeWw2 = invention -> invention.getYearOfInvention() < 1939;
    static Predicate<Price> isLowPrice = price -> price.getMarketPrice() < 20;


    static Function<List<Gun>, Price> getCheapestPrice = list -> list.stream()
            .min(Comparator.comparing(gun -> gun.getPrice().getMarketPrice()))
            .orElseThrow(() -> new RuntimeException(""))
            .getPrice();

    static Function<List<Gun>, Price> getMostExpensivePrice = list -> list.stream()
            .max(Comparator.comparing(gun -> gun.getPrice().getMarketPrice()))
            .orElseThrow(() -> new RuntimeException(""))
            .getPrice();

    static Function<List<Gun>, Ammo> getBiggestAmmoCapacity = list -> list.stream()
            .max(Comparator.comparing(gun -> gun.getAmmo().getAmmoCapacity()))
            .orElseThrow(() -> new RuntimeException(""))
            .getAmmo();

    static Function<List<Gun>, Ammo> getLowestAmmoCapacity = list -> list.stream()
            .min(Comparator.comparing(gun -> gun.getAmmo().getAmmoCapacity()))
            .orElseThrow(() -> new RuntimeException(""))
            .getAmmo();

    static Function<List<Gun>, Ammo> getBiggestCaliber = list -> list.stream()
            .max(Comparator.comparing(gun -> gun.getAmmo().getCaliber()))
            .orElseThrow(() -> new RuntimeException(""))
            .getAmmo();

    static Function<List<Gun>, Ammo> getSmallestCaliber = list -> list.stream()
            .min(Comparator.comparing(gun -> gun.getAmmo().getCaliber()))
            .orElseThrow(() -> new RuntimeException(""))
            .getAmmo();


    static UnaryOperator<Price> increasePrice = price -> {
        price.setMarketPrice(price.getMarketPrice() + 20);
        return price;
    };
    static UnaryOperator<Ammo> increaseAmmoCapacity = ammo -> {
        ammo.setAmmoCapacity(ammo.getAmmoCapacity() + 5);
        return ammo;
    };
    static UnaryOperator<Distribution> addUsers = distribution -> {
        distribution.setUsers(distribution.getUsers() + "J");
        return distribution;
    };
    static UnaryOperator<Invention> increaseYearOfInvention = invention -> {
        invention.setYearOfInvention(invention.getYearOfInvention() + 100);
        return invention;
    };


    static BinaryOperator<Gun> ammoUpdate = (Gun targetAmmo, Gun sourceAmmo) -> {
        Ammo sourceGunAmmo = sourceAmmo.getAmmo();
        sourceAmmo.setAmmo(targetAmmo.getAmmo());
        targetAmmo.setAmmo((sourceGunAmmo));
        return targetAmmo;
    };
    static BinaryOperator<Invention> inventorUpdate = (Invention targetIventor, Invention sourceInventor) -> {
        String sourceInventorName = sourceInventor.getInventor();
        sourceInventor.setInventor(targetIventor.getInventor());
        targetIventor.setInventor((sourceInventorName));
        return targetIventor;
    };
    static BinaryOperator<Distribution> increaseManufacture = (Distribution targetManufacture, Distribution sourceManufacture) -> {
        float sourceUpdatedManufacture = sourceManufacture.getManufacture();
        sourceManufacture.setManufacture(targetManufacture.getManufacture());
        targetManufacture.setManufacture(sourceUpdatedManufacture);
        return targetManufacture;
    };


    Consumer<Invention> printGunInventors = invention -> System.out.println(invention.getInventor() + " " + invention.getYearOfInvention());


    public static void main(String[] args) {

        Ammo thompshonAmmo = new Ammo((float) 11.43,1500, 50);
        Ammo thompshonAmmoUpdated = new Ammo((float) 11.43,2000, 100);
        Ammo coltAmmo = new Ammo((float) 11.43,85, 7);
        Ammo stenAmmo = new Ammo((float) 9,550, 32);
        Ammo stg44Ammo = new Ammo((float) 7.92,600, 30);
        Ammo garandAmmo = new Ammo((float) 7.62,30, 8);
        Ammo mauserAmmo = new Ammo((float) 5.6,15, 5);
        Ammo lugerAmmo = new Ammo((float) 7.65, 80, 7);

        Distribution thompsonDistribution = new Distribution("CUsUkG", (float) 1.5);
        Distribution coltDistribution = new Distribution("UsUk", (float) 2.7);
        Distribution stenDistribution = new Distribution("Uk", (float) 2);
        Distribution stg44Distribution = new Distribution("G", (float) 0.4);
        Distribution garandDistribution = new Distribution("US", (float) 4.5);
        Distribution mauserDistribution = new Distribution("UkG", (float) 14.6);
        Distribution lugerDistribution = new Distribution("G", (float) 2.3);
        Distribution randomDistribution = new Distribution("UkGJ", (float) 3);

        Invention thompsonInvention = new Invention("John T. Thompson", 1920);
        Invention coltInvention = new Invention("John Browning", 1911);
        Invention stenInvention = new Invention("Harold Trupin", 1940);
        Invention stg44Invention = new Invention("Hugo Schmeisser", 1944);
        Invention garandInvention = new Invention("John Garand", 1936);
        Invention mauserInvention = new Invention("Peter Paul von Mauser", 1935);
        Invention lugerInvention = new Invention("Georg Luger", 1898);
        Invention randomInvention = new Invention("Blabla Sialala", 0700);

        Price thompsonPrice = new Price(209, 70);
        Price coltPrice = new Price(15, 5);
        Price stenPrice = new Price(11, 4);
        Price stg44Price = new Price(26, 9);
        Price garandPrice = new Price(83, 28);
        Price mauserPrice = new Price(28, 9);
        Price lugerPrice = new Price(13, 4);

        Gun Thompshon = new Gun("Thompson","Submachine", 2, 1928, thompshonAmmo, thompsonDistribution, thompsonInvention, thompsonPrice);
        Gun ThompshonUpdated = new Gun("Thompson","Submachine", 2, 1928, thompshonAmmoUpdated, thompsonDistribution, thompsonInvention, thompsonPrice);
        Gun Colt = new Gun("Colt", "Pistol", 1, 1911, coltAmmo, coltDistribution, coltInvention, coltPrice);
        Gun Sten = new Gun("Sten", "Machinegun", 2, 1941, stenAmmo, stenDistribution, stenInvention, stenPrice);
        Gun Stg44 = new Gun("Stg 44", "Automatic", 2, 1944, stg44Ammo, stg44Distribution, stg44Invention, stg44Price);
        Gun Garand = new Gun("M1 Garand", "Semi-automatic", 2, 1936, garandAmmo, garandDistribution, garandInvention, garandPrice);
        Gun Mauser = new Gun("Mauser 98k", "Bolt-action", 2, 1935, mauserAmmo, mauserDistribution, mauserInvention, mauserPrice);

        Supplier<Gun> gunSupply = () -> new Gun("Luger P08", "Pistol", 1, 1898, lugerAmmo, lugerDistribution, lugerInvention, lugerPrice);
        ammoUpdate.apply(Thompshon, ThompshonUpdated);
        increasePrice.apply(coltPrice);
        inventorUpdate.apply(lugerInvention, randomInvention);
        increaseManufacture.apply(lugerDistribution, randomDistribution);

        List<Price> priceList = List.of(coltPrice, stenPrice, stg44Price, garandPrice, mauserPrice, lugerPrice);
        List<Price> cheapGuns = priceList.stream()
                .filter(isLowPrice)
                .collect(Collectors.toList());
        //Price lowestPrice = getCheapestPrice.apply(cheapGuns);

    }
}
