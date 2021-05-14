package sample;
public class Passengers {

    private String f_name;
    private int secondsInQueue;


    public String getName(){
        return f_name;
    }
    public void setName(String name){
        this.f_name = name;


    }


    public int getSeconds(){
        return secondsInQueue;
    }

    public void setSecondsInQueue (int sec){
        this.secondsInQueue = sec;


    }

    public void display(){
        System.out.println("Name: " + getName());
        System.out.println("Time-Remaining: " + getSeconds() + "s");

    }
}
