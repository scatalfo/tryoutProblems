/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2018.problems.varun;

import java.util.*;
import java.io.*;

/**
 *
 * @author Varun Jindal
 */
public class WeakBridges {
    
    static ArrayList<Integer> greatestMin = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File file = new File("WeakBridgesIn.txt");
        Scanner s = new Scanner(file);
        s.nextLine();
        ArrayList<ArrayList<Integer>> bridges = new ArrayList<>();
        while(s.hasNext()) {
            greatestMin.clear();
            bridges.clear();
            int homeCityNum = s.nextInt();
            int destCity = s.nextInt();
            int numTourists = s.nextInt();
            int numCities = s.nextInt();
            int numBridges = s.nextInt();
            
            // adds all given bridges to a bridges array
            for(int j = 0; j < numBridges; j++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for(int i = 0; i < 3; i++) {
                    temp.add(s.nextInt());
                }
                bridges.add(temp);
            }
            
            ArrayList<City> cities = new ArrayList<>(); // create arraylist of City objects
            for(ArrayList<Integer> bridge : bridges) { // iterate through bridges
                if(cities.size() > 0) {
                    int size = cities.size(); // stores size for this iteration because each iteration changes the size of cities
                    boolean firstCityFound = false; // checks if first city in bridge is already in cities array
                    boolean secondCityFound = false; // checks if second city in bridge is already in cities array
                    for(int i = 0; i < size; i++) {
                        if(bridge.get(0) == cities.get(i).getCityNum()) { // if first city in bridge is already in cities array, add other city in bridge as a destination city
                            cities.get(i).addDestCity(bridge.get(1), bridge.get(2));
                            firstCityFound = true;
                        }
                        if(bridge.get(1) == cities.get(i).getCityNum()) { // if second city in bridge is already in cities array, add other city in bridge as a destination city
                            cities.get(i).addDestCity(bridge.get(0), bridge.get(2));
                            secondCityFound = true;
                        }
                    }
                    /* SIDE NOTE:
                            newArrayList is helper method to create an ArrayList with values in one line
                            it is defined below
                    */
                    if(!firstCityFound) cities.add(new City(bridge.get(0), newArrayList(new int[] {bridge.get(1)}), newArrayList(new int[] {bridge.get(2)}), false)); // if first city not in cities array, add to cities array
                    if(!secondCityFound) cities.add(new City(bridge.get(1), newArrayList(new int[] {bridge.get(0)}), newArrayList(new int[] {bridge.get(2)}), false)); // if second city not in cities array, add to cities array
                } else { // if cities is empty add both cities found in bridge
                    cities.add(new City(bridge.get(0), newArrayList(new int[] {bridge.get(1)}), newArrayList(new int[] {bridge.get(2)}), false));
                    cities.add(new City(bridge.get(1), newArrayList(new int[] {bridge.get(0)}), newArrayList(new int[] {bridge.get(2)}), false));
                }
            }
            
            Collections.sort(cities); // sorts cities, able to do this because City implements Comparable
            recurse(cities, numTourists + 1, homeCityNum, destCity); // solves country
            
            // find greatest number out of the numbers in greatestMin array
            int max = 0;
            for(int i : greatestMin) {
                if(i > max) {
                    max = i;
                }
            }
            
            // once greatest number out of the minimum's of the path capacities has been found (the ideal path has been found), 
            //      calculate the number of trips and the tourists in the last bus
            int lastTripRemainder = numTourists % (max - 1);
            if(lastTripRemainder == 0) {
                System.out.println((numTourists / (max - 1)) + " " + (max - 1));
            } else{
                System.out.println(((numTourists / (max - 1)) + 1) + " " + lastTripRemainder);
            }            
        }   
    }
    
    // helper method to initialize an array with values in one line
    private static ArrayList<Integer> newArrayList(int[] values) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int val : values) {
            temp.add(val);
        }
        return temp;
    }
    
    // recursing solution
    public static int recurse(ArrayList<City> cities, int min, int currentCity, int destCity) {
        // exit condition
        if(currentCity == destCity) {
            // returns the minimum capacity found in path from homeCity to destCity
            return min;
        }
        for(int i = 0; i < cities.size(); i++) {
            if(cities.get(i).getCityNum() == currentCity) { // first find currentCity in cities array
                cities.get(i).setTraveled(); // set currentCity as traveled
                for(int cityNum : cities.get(i).connections) { // iterate through all the cities connected to the current city
                    if(!cities.get(cityNum - 1).travelStatus()) { // if this connection has not been traveled to
                        int tempMin = min; // create tempMin so that min for all cities does not change, only min for this connection
                        if(tempMin > cities.get(i).getCapacity(cityNum)) {  // if the bridge connecting the currentCity and this connection has a capacity less than the minimum capacity so far 
                            tempMin = cities.get(i).getCapacity(cityNum);
                        }
                        // create tempCities to pass cities array by value rather than reference to next recursion
                        // this allows each path to store its own set of city objects so that each path can have its own
                        //      set of beenTraveled booleans as to not skip paths
                        ArrayList<City> tempCities = new ArrayList<>();
                        for(City city : cities) {
                            // create new cities so that it is not pass by reference
                            tempCities.add(new City(city.getCityNum(), city.connections, city.capacities, city.travelStatus()));
                        }
                        // add minimum capacity value of every possible path
                        greatestMin.add(recurse(tempCities, tempMin, cityNum, destCity));
                    }
                }
            }
        }
        return 0;
    }
    
}

// class to store cities
class City implements Comparable<City> {
    public ArrayList<Integer> connections; // stores all City Numbers this city is connected to
    ArrayList<Integer> capacities; // stores all capacities of the bridges that connect this city to the each of the connections, in the same order as the connections array so that each capacity corresponds to the city it connects to
    private boolean traveled; // whether or not this city has been traveled to
    private int cityNum; // city number as described in problem
    
    public City(int cityNum, ArrayList<Integer> connections, ArrayList<Integer> capacities, boolean traveled) {
        this.capacities = new ArrayList<>();
        for(int capacity : capacities) {
            this.capacities.add(capacity);
        }
        this.connections = new ArrayList<>();
        for(int connection : connections) {
            this.connections.add(connection);
        }
        this.traveled = traveled;
        this.cityNum = cityNum;
    }
    
    // sets this city as traveled
    public void setTraveled() {
        traveled = true;
    }
    
    // returns travelStatus
    public boolean travelStatus() {
        return traveled;
    }
    
    // adds a connecting city
    public void addDestCity(int city, int numPass) {
        connections.add(city);
        capacities.add(numPass);
    }
    
    // returns city number
    public int getCityNum() { return this.cityNum; }
    
    // checks whether this city equals another
    public boolean equals(City other) {
        return other.cityNum == this.cityNum;
    }
    
    // necessary to extend Comparable, makes it easy to sort City arrays with Collections.sort() 
    @Override
    public int compareTo(City other) {
        return this.cityNum - other.cityNum;
    }
    
    // allows for easier debugging by giving information everytime you print a City object rather than just printing its memory location
    public String toString() {
        String temp = cityNum + ": " + "Connections: ";
        for(int i : connections) {
            temp += i + " ";
        }
        if(this.traveled) {
            temp += " Traveled";
        }
        return temp.substring(0, temp.length() - 1);
    }
    
    // returns capacity of bridge connecting this city to city number passed to getCapacity
    // able to do this because connections and capacities arrays have corresponding orders
    public int getCapacity(int city) {
        for(int i = 0; i < connections.size(); i++) {
            if(connections.get(i) == city) {
                return capacities.get(i);
            }
        }
        return 0;
    }
}
