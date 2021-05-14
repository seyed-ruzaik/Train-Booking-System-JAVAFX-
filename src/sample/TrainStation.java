package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class TrainStation extends Application {
    static ArrayList<Button> simulationReport = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //create a new pane
        Pane root = new Pane();
        //create buttons for menu and move button
        Button done = new Button("Menu");
        Button add = new Button("Move");
        Button stat = new Button();
        stat.setStyle("-fx-background-color:#ba9800; -fx-border-color:white; -fx-border-width:5");
        done.setStyle("-fx-background-color:#618cd4; -fx-border-color:white; -fx-border-width:3");
        add.setStyle("-fx-background-color:#618cd4; -fx-border-color:white; -fx-border-width:3");
        root.setStyle("-fx-background-color:#d49961;");
        //create 2 labels for waiting room and train queue
        Label lb1 = new Label("Waiting Room\n_____________________________");
        Label lb2 = new Label("Train Queue\n_______________________________");
        //create label for No-Passengers to be moved in GUI
        Label NoPassengers = new Label();
        //set font sizes
        lb1.setFont(Font.font(14));
        lb2.setFont(Font.font(14));
        NoPassengers.setStyle("-fx-background-color:yellow; -fx-border-color:white; -fx-border-width:3;");

        NoPassengers.setFont(Font.font(13));
        NoPassengers.setMinSize(100.0,10.0);
        NoPassengers.maxHeight(10.0);

        //set layouts, min and max sizes for labels
        lb2.setLayoutX(700);
        lb2.setLayoutY(5.0);
        lb1.setLayoutX(5);
        lb1.setLayoutY(5.0);
        lb1.setMinSize(150.0,10.0);
        lb1.maxHeight(10.0);
        NoPassengers.setLayoutX(340.0);
        NoPassengers.setLayoutY(280.0);

        //set layouts, min max sizes for buttons
        done.setLayoutX(450);
        done.setLayoutY(320);
        done.setMinSize(30,20);
        done.maxWidth(30);
        add.setLayoutX(350);
        add.setLayoutY(320);
        stat.setLayoutX(370);
        stat.setLayoutY(180);
        stat.setMinSize(65.0,80.0);
        stat.maxWidth(65.0);
        stat.maxHeight(80.0);

        //lists containing waiting room buttons and train queue
        ArrayList<Button> wait = new ArrayList<>();
        ArrayList<Button> queue = new ArrayList<>();
        //linked list of passengers in waiting room and their seats
        LinkedList<String> waitingRoom = new LinkedList<>();
        LinkedList<String> seats = new LinkedList<>();
        //waiting method to load names from text files to data structure
        Waiting(waitingRoom,seats);
        //call sort method to sort them in seat order
        sort(waitingRoom,seats);

        System.out.println("************************");
        System.out.println("Denuwara Express Program");
        System.out.println("************************");
        System.out.println();

        //waitingRoom_GUI
        Button[] btn = new Button[42];
        double x = 30;
        double y = 30;
        for(int i = 0 ; i<btn.length ;i++){
            btn[i] = new Button();
            if (i<21) {
                btn[i].setLayoutX(5);
                btn[i].setLayoutY(20 + y);
                btn[i].setText("empty");
                btn[i].setMinSize(30.0, 20.0);
                btn[i].maxWidth(30.0);
                btn[i].setStyle("-fx-background-color:orange;");
                wait.add(btn[i]);
                y += 30;
            } else  {
                btn[i].setText("empty");
                btn[i].setStyle("-fx-background-color:orange;");
                wait.add(btn[i]);
                btn[i].setLayoutX(120);
                btn[i].setLayoutY(20 + x);
                btn[i].setMinSize(30, 20);
                btn[i].maxWidth(30);
                x = x + 30;
            }
            root.getChildren().addAll(btn[i]);

        }


        //Train Queue GUI
        Button[] train = new Button[42];
        double a = 30;
        double b = 30;
        for(int j = 0 ; j <train.length ;j++){
            btn[j] = new Button();
            if (j<21) {
                btn[j].setLayoutX(700);
                btn[j].setLayoutY(20 + a);
                btn[j].setMinSize(30.0, 20.0);
                btn[j].maxWidth(30.0);
                btn[j].setText("");
                btn[j].setStyle("-fx-background-color:grey;");
                queue.add(btn[j]);
                a += 30;
            } else  {
                btn[j].setText("");
                btn[j].setStyle("-fx-background-color:pink;");
                queue.add(btn[j]);
                btn[j].setLayoutX(850);
                btn[j].setLayoutY(20 + b);
                btn[j].setMinSize(30, 20);
                btn[j].maxWidth(30);
                b += 30;
            } //add the buttons to pane
            root.getChildren().addAll(btn[j]);


        }
        //gui for run simulation method
        Button[] simulation = new Button[42];
        double c = 30;
        double d = 30;
        for(int k = 0 ; k <simulation.length ;k++){
            btn[k] = new Button();
            if (k<21) {
                btn[k].setLayoutX(700);
                btn[k].setLayoutY(20 + c);
                btn[k].setMinSize(30.0, 20.0);
                btn[k].maxWidth(30.0);
                btn[k].setText("");
                btn[k].setStyle("-fx-background-color:pink;");
                simulationReport.add(btn[k]);
                c += 30;
            } else  {
                btn[k].setText("");
                btn[k].setStyle("-fx-background-color:pink;");
                simulationReport.add(btn[k]);
                btn[k].setLayoutX(850);
                btn[k].setLayoutY(20 + d);
                btn[k].setMinSize(30, 20);
                btn[k].maxWidth(30);
                d += 30;
            } //add the buttons to pane
            root.getChildren().addAll(btn[k]);


        }
        stat.setVisible(false);




        for(Button button:simulationReport){
            button.setVisible(false);
        }

        //display passengers names in waiting room
        for (int k = 0; k < waitingRoom.size(); k++) {
            wait.get(k).setText(waitingRoom.get(k)+"-seat:"+seats.get(k));
        }
        for(int j = waitingRoom.size(); j < wait.size();j++){
            wait.get(j).setVisible(false);
        }


        //add them to pane
        root.getChildren().add(NoPassengers);
        root.getChildren().add(done);
        root.getChildren().add(stat);
        root.getChildren().add(add);
        root.getChildren().add(lb1);
        root.getChildren().add(lb2);
        //set title and set scene
        primaryStage.setTitle("Train Simulation");
        primaryStage.setScene(new Scene(root, 1100, 700));


        menu(add,done,queue,waitingRoom,primaryStage,wait,seats,lb1,lb2,NoPassengers,stat);


    }//method for loading passenger names from file to data structure
    static void Waiting(LinkedList<String> waitingRoom, LinkedList<String> seats){
        //file-name
        String f_name = "../CW_01/Booking-details.txt";
        String line = null;
        try { //new FileReader
            FileReader myFile = new FileReader(f_name);

            //new BufferedReader
            int count1 = 0;
            BufferedReader bufferedReader = new BufferedReader(myFile);
            while ((line = bufferedReader.readLine()) != null) {
                count1++;
                if(count1 > 2) {
                    //split method remove the strings that shouldn't be added to list
                    String i = (line.replaceAll("[0-9]", ""));
                    String[] letters = i.split(" booked seat_no ");
                    //add them to waitingRoom list
                    waitingRoom.addAll(Arrays.asList(letters));

                }
            }

            bufferedReader.close();
            //file not found exceptions
        } catch (IOException ex) {
            System.out.println("Error reading file '" + f_name + "'");


        }
        String file  = "../CW_01/Booking-details.txt";
        String line_2 = null;
        try { //new FileReader
            FileReader fileReader = new FileReader(file);

            //new BufferedReader
            int count2 = 0;
            BufferedReader BR = new BufferedReader(fileReader);
            while ((line_2 = BR.readLine()) != null) {
                count2++;
                if(count2 > 2) {
                    //get the seat numbers from file and add them to data structure
                    String i = (line_2.replaceAll("\\D+", ""));
                    seats.add(i);
                }
            }

            BR.close();
            //file not found exceptions
        } catch (IOException ex) {
            System.out.println("Error reading file '" + f_name + "'");


        }
    } //menu method
    static void menu(Button add, Button done,ArrayList<Button> queue,LinkedList<String> waitingRoom,Stage primaryStage,ArrayList <Button> wait,LinkedList<String> seats,Label lb1,Label lb2,Label NoPassengers,Button stat){
        PassengerQueue passengerQueue = new PassengerQueue();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter A to add passengers to Queue\nEnter V to view the Train Queue\nEnter D to delete a passenger\nEnter S to store train queue details to file\nEnter L to load the data\nEnter R to run simulation and produce report\nEnter Q to quit");
        System.out.println("____________________________________________");
        System.out.print("Enter Choice:");
        String choice = sc.nextLine().toLowerCase();
        //use switch case to output the desired choice
        switch (choice) {
            //add method
            case "a":
                if(PassengerQueue.passengers.size()==42){
                    primaryStage.close();
                    System.out.println();
                    System.out.println("Queue is full at the moment");
                    System.out.println();
                    menu(add, done, queue, waitingRoom, primaryStage, wait, seats, lb1, lb2, NoPassengers, stat);
                }else {
                    PassengerQueue.add(add, done, queue, waitingRoom, primaryStage, wait, seats, lb1, lb2, NoPassengers, stat);
                }
                break;
            //quit program
            case "q":
                System.out.println();
                System.out.println("Thank you for using our service");
                System.out.println();
                System.exit(0);
                break;
            //view method
            case "v":
                view(queue, wait, done,primaryStage,add,waitingRoom,seats,lb1,lb2,NoPassengers,stat);
                break;
            //delete method
            case  "d": //check if passenger queue is empty or not
                if(PassengerQueue.passengers.size()==0){
                    System.out.println();
                    System.out.println("You cannot use this option because you have no passengers in queue");
                    System.out.println();
                    menu(add, done, queue, waitingRoom, primaryStage, wait, seats, lb1, lb2, NoPassengers, stat);
                    break;
                }else {
                    delete(queue, seats, waitingRoom, wait);
                    menu(add, done, queue, waitingRoom, primaryStage, wait, seats, lb1, lb2, NoPassengers, stat);
                }
                break;
            //save method
            case "s":
                if(PassengerQueue.passengers.size()==0){
                    System.out.println();
                    System.out.println("You cannot use this option try adding some passengers first");
                    System.out.println();
                    menu(add, done, queue, waitingRoom, primaryStage, wait, seats, lb1, lb2, NoPassengers, stat);
                    break;
                }else {
                    save(seats);
                    menu(add, done, queue, waitingRoom, primaryStage, wait, seats, lb1, lb2, NoPassengers, stat);
                }
                break;
            //load method
            case "l":
                load(seats,queue,waitingRoom,wait);
                menu(add, done, queue, waitingRoom, primaryStage, wait, seats, lb1, lb2,NoPassengers,stat);
                break;
            //for every invalid option display an error message and call the menu method again
            case "r": //if passenger queue is greater than 0 open gui else show an error message
                if(PassengerQueue.passengers.size() > 0) {
                    simulation(waitingRoom, wait, queue, passengerQueue, primaryStage, add,lb1,lb2,NoPassengers,done,seats,stat);
                    primaryStage.show();
                    break;
                }else {
                    System.out.println();
                    System.out.println("Error add some passengers to Train Queue then try again");
                    System.out.println();

                }
                menu(add, done, queue, waitingRoom, primaryStage, wait, seats, lb1, lb2, NoPassengers,stat);
                break;
            default: //display an error message if an invalid option is entered in the console
                System.out.println();
                System.out.println("Invalid option !");
                System.out.println();
                menu(add,done,queue,waitingRoom,primaryStage,wait,seats,lb1,lb2,NoPassengers,stat);
                break;
        }

    } //sort method to sort customer names in seat order from file
    static void sort(LinkedList<String> waitingRoom , LinkedList<String> seats){
        //bubble sort algorithm to sort seats numbers
        for (int i = 0; i < seats.size(); i++) {
            for (int j = i+1; j < seats.size(); j++){
                int tmp = 0;
                String sort ;
                if (Integer.parseInt(seats.get(i)) > Integer.parseInt(seats.get(j)) ) {
                    // swap tmp and seats.get(i)
                    tmp = Integer.parseInt(seats.get(i));
                    sort = waitingRoom.get(i);
                    seats.set(i, seats.get(j));
                    waitingRoom.set(i,waitingRoom.get(j));
                    seats.set(j, String.valueOf(tmp));
                    waitingRoom.set(j, sort);
                }
            }
        }


    } //view method to view train queue
    static void view(ArrayList<Button> queue,ArrayList<Button> wait,Button done,Stage primaryStage,Button add,LinkedList<String> waitingRoom,LinkedList<String> seats,Label lb1 , Label lb2,Label NoPassengers,Button stat){
        //set visible to false to avoid displaying the waiting room in view method
        NoPassengers.setVisible(false);
        for (Button button : wait) {
            button.setVisible(false);
        }
        //set text for the label
        lb1.setText("View-Train Queue\n___________________________________");
        lb2.setVisible(false);
        //set visible false to hide move button
        add.setVisible(false);
        //set values of double x and double y values for button layout for train queue
        double x = 30;
        double y = 30;
        for(int j = 0 ; j< queue.size();j++){
            if(j <21) {
                queue.get(j).setLayoutX(5);
                queue.get(j).setLayoutY(20+y);
                y += 30;
            }else{
                queue.get(j).setLayoutX(700);
                queue.get(j).setLayoutY(20+x);
                x += 30;
            }
        }  //set seat numbers for buttons in View method
        for(int j =0 ; j < 42 ; j++){
            queue.get(j).setText("seat "+ (j + 1));
        }
        //display empty if passenger is in waitingRoom but not added to Queue
        for(int j =0 ; j < waitingRoom.size() ; j++){
            int index = seats.indexOf(seats.get(j));
            int seat = Integer.parseInt(seats.get(index));
            queue.get(seat-1).setText("seat "+ (Integer.parseInt(seats.get(j)))+":empty"+":In waitingRoom");
        }
        //display the passenger name and their respective seat number in it
        for(int i = 0 ; i < PassengerQueue.passengers.size(); i++) {
            int seatNo = Integer.parseInt(seats.get(i));
            queue.get(seatNo-1).setText("seat "+seatNo+": "+PassengerQueue.passengers.get(i)+":In Queue");

        } //gui for passengers who boarded to train
        if(PassengerQueue.passengers.size()==0 && PassengerQueue.boarded.size()>0){
            for(int i = 0 ; i < PassengerQueue.boarded.size(); i++) {
                int seatNo = Integer.parseInt(seats.get(i));
                queue.get(seatNo-1).setText("seat "+seatNo+": "+PassengerQueue.boarded.get(i)+":Boarded");

            }
            for(int j =0 ; j < waitingRoom.size() ; j++){
                int seat = Integer.parseInt(seats.get(PassengerQueue.boarded.size()+j));
                queue.get(seat-1).setText("seat "+ seat+": Empty "+":In waitingRoom");
            }

        }


        //show stage
        primaryStage.show();

        done.setOnAction(event -> {
            NoPassengers.setVisible(true);
            //set seat numbers for train queue
            for(int j = 0 ; j < queue.size(); j++){
                queue.get(j).setText("");
            }
            //set passengers names in queue to the train queue
            for(int i = 0 ; i< PassengerQueue.passengers.size();i++) {
                queue.get(i).setText(PassengerQueue.passengers.get(i));
            }

            lb1.setText("Waiting Room\n_____________________________");
            lb2.setVisible(true);
            add.setVisible(true);
            lb2.setVisible(true);
            primaryStage.close();
            //set visible true for waiting room gui buttons
            for (Button button : wait) {
                button.setVisible(false);
            }
            for(int  i = (PassengerQueue.passengers.size()+PassengerQueue.boarded.size()) ; i < waitingRoom.size()+PassengerQueue.boarded.size();i++){
                wait.get(i).setVisible(true);
            }
            for(int a = waitingRoom.size()+PassengerQueue.boarded.size() ; a < wait.size();a++){
                wait.get(a).setVisible(false);
            }
            //set layouts for Queue gui buttons
            double a = 30;
            double b = 30;
            for(int j = 0 ; j< queue.size();j++){
                if(j <21) {
                    queue.get(j).setLayoutX(700);
                    queue.get(j).setLayoutY(20+a);
                    a += 30;
                }else{
                    queue.get(j).setLayoutX(850);
                    queue.get(j).setLayoutY(20+b);
                    b += 30;
                }
            } //call the menu method
            menu(add,done,queue,waitingRoom,primaryStage,wait,seats,lb1,lb2,NoPassengers,stat);
        });

    }//delete method
    static void delete(ArrayList<Button> queue,LinkedList<String> seats,LinkedList<String> waitingRoom,ArrayList<Button> wait){
        Scanner sc = new Scanner(System.in);
        //get passenger name
        System.out.println("Enter the name:");
        String name = sc.nextLine().toLowerCase();
        //get passenger seat number
        System.out.println("Enter seat_no:");
        String seatNo = sc.nextLine();
        //index of the given seat number
        int SeatIndex = seats.indexOf(seatNo);
        //index of given name in waitingRoom
        int NameIndex = waitingRoom.indexOf(name);

        //if the index of name in waiting room is equivalent to index of seat number then remove passenger from queue
        if(SeatIndex == NameIndex && PassengerQueue.passengers.contains(name)){
            System.out.println();
            System.out.println(name+": deleted ");
            System.out.println();
            PassengerQueue.deleted.add(seatNo);
            //remove passenger from queue
            PassengerQueue.passengers.remove(name);
            //remove passenger from waitingRoom also
            waitingRoom.remove(SeatIndex);
            //delete passenger seat from the data structure
            seats.remove(SeatIndex);
        }else {
            //if customer enters invalid details out an error message
            System.out.println();
            System.out.println("Invalid details");
            System.out.println();
        }for(int j = 0 ; j< queue.size();j++){
            queue.get(j).setText("");
        }

        //re-order the queue
        for (int i = 0; i < PassengerQueue.passengers.size(); i++) {
            queue.get(i).setText(PassengerQueue.passengers.get(i));

        }
        for(int i = 0 ; i < waitingRoom.size();i++) {
            if (wait.get(i).getText().equals(queue.get(i).getText() + "-" + "seat:" + seats.get(i))) {
                wait.get(i).setVisible(false);
            }
        }

        //save method to store queue details to a file
    }static void save(LinkedList<String> seats){
        //file writer
        try{  //set the file name and path
            FileWriter FileWriter = new FileWriter("Queue-Details.txt");
            FileWriter.write("Queue-Details"+"\n_____________");
            FileWriter.write("\n");
            for(int i = 0 ; i < PassengerQueue.passengers.size();i++) {
                FileWriter.write(PassengerQueue.passengers.get(i)+" seat:"+seats.get(i)+":In Queue"+"\n");
            }  //close file writer
            FileWriter.close();
        }catch(Exception ex){
            System.out.println(ex);
        } //display a message after writing the file without an error
        System.out.println();
        System.out.println("Successfully Stored");
        System.out.println();




    } //load method to load the program
    static  void load(LinkedList<String> seats,ArrayList<Button> queue,LinkedList<String> waitingRoom,ArrayList<Button> wait){
        PassengerQueue.passengers.clear();
        for(int i = 0 ; i < queue.size();i++){
            queue.get(i).setText("");
        }
        System.out.println();
        System.out.println("Loaded successfully");
        System.out.println();

        try {  //file name
            File myObj = new File("Queue-Details.txt");
            Scanner myReader = new Scanner(myObj);
            //count to skip number of lines
            int count  = 0;
            while (myReader.hasNextLine()) {
                //read the line and do nothing
                String names = myReader.nextLine();
                //increment count
                count++;
                if(count>2) { //if count is greater than two start adding the characters to list
                    // use regex [0-9] and replace them with an empty string to avoid reading the number or adding
                    String i = names.replaceAll("[0-9]","");
                    //split the necessary words that is not required to be added to the data structure
                    String[] letters = i.split(" seat::In Queue");

                    //add name of passenger to train queue from file
                    PassengerQueue.passengers.addAll(Arrays.asList(letters));

                }
            } //close reader
            myReader.close();
            //file not found exceptions
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println("Error File Could Not Be Found");
            System.out.println();

        }

        try { //read the line again from same file name
            File myObj2 = new File("Queue-Details.txt");
            Scanner reader = new Scanner(myObj2);
            //set count to skip the unnecessary lines
            int count2  = 0;
            while (reader.hasNextLine()) {
                //read lines but never add them to data structure
                String seatNo = reader.nextLine();
                count2++;
                if(count2 > 2) {
                    //when count is greater than 2
                    //read the line and use regex \\D+ take only the numbers from text file
                    String i = seatNo.replaceAll("\\D+","");
                    //if seat number is not contains in data structure it will be added
                    if(!seats.contains(i)){
                        seats.add(i);
                    }


                }
            } //close reader
            reader.close();
            //use of file not found exceptions
        } catch (FileNotFoundException ex) {


        }
        //show names and seat numbers from data structure and show them in queue
        for(int j = 0 ; j < PassengerQueue.passengers.size(); j++) {
            int seatNo = Integer.parseInt(seats.get(j));
            queue.get(j).setText(PassengerQueue.passengers.get(j));

        }
        for(int i = PassengerQueue.passengers.size() ; i < waitingRoom.size();i++){
            wait.get(i).setVisible(true);
        }

    } //dice method to generate a number between 1-6
    public static int dice(){
        Random dice = new Random();
        return dice.nextInt(6)+1;

    }
    static int endTime = 0 ;
    //Run simulation method
    static void simulation(LinkedList<String> waitingRoom, ArrayList<Button> wait, ArrayList<Button> queue, PassengerQueue passengerQueue,Stage primaryStage,Button add,Label lb1,Label lb2,Label NoPassengers,Button done,LinkedList<String> seats,Button stat){
        //initialize  variables
        int time  = 0 ;
        int min = 18;
        int max = 0 ;
        int count = 0;
        stat.setVisible(true);
        for(Button button:wait){
            button.setVisible(false);
        }
        for(Button button:queue){
            button.setVisible(false);
        }
        NoPassengers.setVisible(false);
        lb2.setVisible(false);
        lb1.setText("Simulation Report\n_____________________");
        lb1.setStyle("-fx-font-weight:bold;");
        LinkedList<String> reportDetails = new LinkedList<>();

        for(int  i = 0 ; i < PassengerQueue.passengers.size();i++){
            simulationReport.get(i).setVisible(true);
            simulationReport.get(i).setStyle("-fx-background-color: yellow;");
        }
        add.setVisible(false);
        double y = 30;
        double x = 30;
        for(int  i = 0 ; i < simulationReport.size();i++){
            if(i <21) {
                simulationReport.get(i).setLayoutX(5);
                simulationReport.get(i).setLayoutY(20+y);
                y += 30;
            }else{
                simulationReport.get(i).setLayoutX(700);
                simulationReport.get(i).setLayoutY(20+x);
                x += 30;
            }
        }


        int size = PassengerQueue.passengers.size();
        while (PassengerQueue.passengers.size() != 0) {

            int delay = dice() + dice() + dice();
            time = time +delay;
            if(time < min) {
                min = time;
            }

            if(time > max){
                max = time;
            }



            for(int i = 0 ; i< passengerQueue.Length();i++){
                queue.get(i).setText("");
                wait.get(i).setVisible(false);
            }
            passengerQueue.Add(passengerQueue.getList().getFirst());
            int num = PassengerQueue.boarded.size()-1;

            simulationReport.get(count).setText(passengerQueue.getList().getFirst()+"-waitingTime"+time+"s");
            reportDetails.add("Name: " + passengerQueue.getList().getFirst() + "\nWaiting Time-" + time + "s" + "\nSeat-" + seats.get(PassengerQueue.boarded.size()-1)+"\n");

            count++;

            passengerQueue.remove();
            waitingRoom.removeFirst();




        }
        int avg = (time)/(count);
        endTime += time;
        done.setOnAction(event -> {
            for(Button button:simulationReport){
                button.setText("");
            }
            primaryStage.close();
            stat.setVisible(false);
            for(Button button:queue){
                button.setVisible(true);
            }
            for(Button button:simulationReport){
                button.setVisible(false);
            }
            NoPassengers.setVisible(true);
            //set seat numbers for train queue
            for(int j = 0 ; j < queue.size(); j++){
                queue.get(j).setText("");
            }
            //set passengers names in queue to the train queue
            for(int i = 0 ; i< PassengerQueue.passengers.size();i++) {
                queue.get(i).setText(PassengerQueue.passengers.get(i));
            }

            lb1.setText("Waiting Room\n_____________________________");
            lb1.setStyle("-fx-font-weight: normal;");
            lb2.setVisible(true);
            add.setVisible(true);
            lb2.setVisible(true);
            //set visible true for waiting room gui buttons
            for (Button button : wait) {
                button.setVisible(false);
            }
            for(int  i = (PassengerQueue.passengers.size()+PassengerQueue.boarded.size()) ; i < waitingRoom.size()+PassengerQueue.boarded.size();i++){
                wait.get(i).setVisible(true);
            }
            for(int a = waitingRoom.size()+PassengerQueue.boarded.size() ; a < wait.size();a++){
                wait.get(a).setVisible(false);
            }



            menu(add,done,queue,waitingRoom,primaryStage,wait,seats,lb1,lb2,NoPassengers,stat);

        });
        stat.setText("1.Average Waiting Time: "+avg+"s"+"\n2.Min Waiting Time: "+min+"s"+"\n3.Max Waiting Time: "+max+"s"+"\n4.Max Length: "+count);


        try {
            FileWriter fileWriter = new FileWriter("Simulation-Report.txt");
            fileWriter.write("Simulation Report\n_________________"+"\n");
            fileWriter.write("\n");
            fileWriter.write(stat.getText());
            fileWriter.write("\n ");
            fileWriter.write("\n");
            for(int i = 0 ; i < reportDetails.size();i++){
                fileWriter.write(reportDetails.get(i));
                fileWriter.write("\n");

            }fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        //launch
        launch(args);
    }
}
