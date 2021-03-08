public class Price {        // costs of the guns during WWII

    public int marketPrice;
    public int productionCost;

    public Price(int marketPrice, int productionCost){
        this.marketPrice = marketPrice;
        this.productionCost = productionCost;
    }

    public Price(){
    }

    public int getMarketPrice(){
        return marketPrice;
    }

    public void setMarketPrice(int marketPrice){
        this.marketPrice = marketPrice;
    }

    public int getProductionCost(){
        return productionCost;
    }

    public void setProductionCost(int productionCost){
        this.productionCost = productionCost;
    }
}
