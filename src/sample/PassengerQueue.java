package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PassengerQueue {
    //passengerQueueList
    static LinkedList<String> passengers = new LinkedList<>();
    static  ArrayList <String> deleted  = new ArrayList<>();
    static LinkedList<String> boarded = new LinkedList<>();



    public static void add(Button add, Button done, ArrayList<Button> queue, LinkedList<String> waitingRoom, Stage primaryStage, ArrayList<Button> wait, LinkedList<String> seats, Label lb1, Label lb2,Label NoPassengers,Button stat) {
        //setting set visible false for waitingRoom names if name is in passengerQueue lIst
        for(int i = 0 ; i < waitingRoom.size();i++) {
            if(wait.get(i).getText().equals(queue.get(i).getText()+"-"+"seat:"+seats.get(i))){
                wait.get(i).setVisible(false);
            }
        }


        //generate a 1 six sided dice number
        Random rn = new Random();
        int ans = rn.nextInt(6) + 1;
        NoPassengers.setText("No of passengers to be moved:"+ ans +" ");
        // when move button is pressed it will move passengers according to dice number
        add.setOnAction(event -> {

            //passengerList size
            int passengerSize = passengers.size();
            // create and end value for the loop
            int endValue = passengers.size()+ans;
            if (waitingRoom.size() > endValue) {
                for (int i = passengerSize; i < endValue; i++) {
                    //set text for buttons in queue GUI
                    queue.get(i).setText(waitingRoom.get(i));
                    //add them to passengerList
                    passengers.add(waitingRoom.get(i));
                    wait.get(i+deleted.size()+boarded.size()).setVisible(false);
                    add.setVisible(false);
                }


            } else {
                //get the difference between waiting room size and passenger list size
                int difference =  waitingRoom.size() - passengers.size();
                //get the difference and subtract it from die number
                int rand = ans - difference;
                int f_rand = ans - rand;
                //get the last index of passengerList
                int end = passengers.size() - 1;
                for (int i = 0; i < f_rand; i++) {
                    //increment end
                    end += 1;
                    //add them to train queue
                    queue.get(end).setText(waitingRoom.get(end));
                    wait.get(end+deleted.size()+boarded.size()).setVisible(false);

                    //add them to passengerList
                    passengers.add(waitingRoom.get(end));

                }
                //if all passengers in waiting room is added to queue then disable the move/add button
                add.setStyle("-fx-background-color:red;");
                add.setDisable(true);
            }

            add.setVisible(false);





        });

        primaryStage.show();
        if(passengers.size()==waitingRoom.size()){
            //if waiting room size is equal to passengerList then disable the move button
            add.setStyle("-fx-background-color:red;");
            add.setDisable(true);
        }

        done.setOnAction(event -> {
            add.setDisable(false);
            add.setStyle("-fx-background-color:#618cd4; -fx-border-color:white; -fx-border-width:3");
            //close primary stage
            primaryStage.close();
            //set visible of move button to true
            add.setVisible(true);
            //call menu from TrainStation class
            TrainStation.menu(add, done, queue, waitingRoom, primaryStage, wait, seats,lb1,lb2,NoPassengers,stat);

        });

    }


    //length of queue
    public int Length(){
        return passengers.size();
    }
    //remove passenger from queue
    public void remove(){
        passengers.removeFirst();

    }
    //get passenger list
    public LinkedList<String> getList() {
        return passengers;
    }
    //add passenger
    public void Add(String x){
        boarded.add(x);
    }

}


