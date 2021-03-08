public class Invention {

    public String inventor; // name of inventor
    public int yearOfInvention;

    public Invention(String inventor, int yearOfInvention){
        this.inventor = inventor;
        this.yearOfInvention = yearOfInvention;
    }

    public Invention(){
    }

    public String getInventor(){
        return inventor;
    }

    public void setInventor(String inventor){
        this.inventor = inventor;
    }

    public int getYearOfInvention(){
        return yearOfInvention;
    }

    public void setYearOfInvention(int yearOfInvention){
        this.yearOfInvention = yearOfInvention;
    }

}
