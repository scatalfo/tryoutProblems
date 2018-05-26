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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File file = new File("WeakBridgesIn.txt");
        Scanner s = new Scanner(file);
        s.nextLine();
        ArrayList<ArrayList<Integer>> bridges = new ArrayList<>();
        while(s.hasNext()) {
            int homeCityNum = s.nextInt();
            int destCity = s.nextInt();
            int numTourists = s.nextInt();
            int numCities = s.nextInt();
            int numBridges = s.nextInt();
            for(int j = 0; j < numBridges; j++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for(int i = 0; i < 3; i++) {
                    temp.add(s.nextInt());
                }
                bridges.add(temp);
            }
            System.out.println(bridges);
            
            ArrayList<City> cities = new ArrayList<>();
            for(ArrayList<Integer> bridge : bridges) {
                if(cities.size() > 0) {
//                    System.out.println("size > 0");
                    int size = cities.size();
                    boolean firstCityFound = false;
                    boolean secondCityFound = false;
                    for(int i = 0; i < size; i++) {
//                        System.out.println("----- top -----");
                        if(bridge.get(0) == cities.get(i).getCityNum()) {
                            cities.get(i).addDestCity(bridge.get(1), bridge.get(2));
                            firstCityFound = true;
                        }
                        if(bridge.get(1) == cities.get(i).getCityNum()) {
                            cities.get(i).addDestCity(bridge.get(0), bridge.get(2));
                            secondCityFound = true;
                        }
//                        for(City city : cities) {
//                            System.out.println(city);
//                        }
                    }
                    if(!firstCityFound) cities.add(new City(bridge.get(0), bridge.get(1), bridge.get(2)));
                    if(!secondCityFound) cities.add(new City(bridge.get(1), bridge.get(0), bridge.get(2)));
                } else {
                    cities.add(new City(bridge.get(0), bridge.get(1), bridge.get(2)));
                    cities.add(new City(bridge.get(1), bridge.get(0), bridge.get(2)));
//                    System.out.println("size == 0");
                }
            }
            Collections.sort(cities);
            System.out.println(cities);
            ArrayList<Integer> currentCities = new ArrayList<>();
            currentCities.add(homeCityNum);
            boolean dunzo = false;
            while(!dunzo) {
                // init temp array to store added connections so that 
                // don't have to modify currentCities while interating over it 
                ArrayList<Integer> temp = new ArrayList<>();
                for(int cityNum : currentCities) {
//                    System.out.println(cities.get(cityNum - 1));
                    // add connections of currentCity to currentCities
                    for(int connection : cities.get(cityNum - 1).connections) {
                        if(!cities.get(connection - 1).travelStatus()) {
                            temp.add(connection);
                        }
                    }
//                    System.out.println(temp);
                    // set currentCity to traveled
                    cities.get(cityNum - 1).setTraveled();
                    System.out.println((cityNum) + "");
                }
                for(int i : temp) {
                    currentCities.add(i);
                }
                
                // sets currentCites to all connections yet untraveled
                currentCities = temp;
                System.out.println(currentCities);
                boolean allTraveled = true;
                for(City city : cities) {
                    if(!city.travelStatus()) {
                        allTraveled = false;
                    }
                }
                dunzo = allTraveled;
            }
            
        }   
    }
    
}

class City implements Comparable<City> {
    public ArrayList<Integer> connections;
    ArrayList<Integer> capacities;
    private boolean traveled;
    private int cityNum;
    
    public City(int cityNum, int connection, int capacity) {
        this.capacities = new ArrayList<>();
        this.capacities.add(capacity);
        connections = new ArrayList<>();
        this.connections.add(connection);
        traveled = false;
        this.cityNum = cityNum;
    }
    
    public void setTraveled() {
        traveled = true;
    }
    
    public boolean travelStatus() {
        return traveled;
    }
    
    public void addDestCity(int city, int numPass) {
        connections.add(city);
        capacities.add(numPass);
    }
    
    public int getCityNum() { return this.cityNum; }
    
    public boolean equals(City other) {
        return other.cityNum == this.cityNum;
    }
    
    public int compareTo(City other) {
        return this.cityNum - other.cityNum;
    }
    
    public String toString() {
        String temp = cityNum + ", " + "Connections: ";
        for(int i : connections) {
            temp += i + " ";
        }
        return temp.substring(0, temp.length() - 1);
    }
}
