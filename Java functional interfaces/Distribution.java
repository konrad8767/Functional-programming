public class Distribution {

    private String users; // C - Canada, Us - United States, Uk - United Kingdom, G - Germany
    private float manufacture; // in milions


    public Distribution(String users, float manufacture){
        this.users = users;
        this.manufacture = manufacture;
    }

    public Distribution(){
    }

    public String getUsers(){
        return users;
    }

    public void setUsers(String users){
        this.users = users;
    }

    public float getManufacture(){
        return manufacture;
    }

    public void setManufacture(float manufacture){
        this.manufacture = manufacture;
    }
}
