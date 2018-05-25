/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2013.problems;

import java.util.*;
import java.io.*;

/**
 *
 * @author Varun Jindal
 */
public class Reflection {
    
    public static void main(String args[]) throws IOException {
        File file = new File("ReflectionIn.txt");
        Scanner s = new Scanner(file);
        s.nextLine();
        while(s.hasNextLine()) {
            int width = s.nextInt();
            int height = s.nextInt();
            int initX = s.nextInt();
            int initY = s.nextInt();
            int velX = s.nextInt();
            int velY = s.nextInt();
            int initVelX = velX;
            int initVelY = velY;
            int currentX = initX;
            int currentY = initY;
            int ticks = 0;
            boolean firstTime = false;
            while(!((currentX == initX && currentY == initY) && (initVelX == velX && initVelY == velY)) || !firstTime) {
                firstTime = true;
                currentY += velY;
                currentX += velX;
                ticks++;
                if(currentX >= width || currentX <= 1) {
                    if(currentX == width || currentX == 1) {
                        velX *= -1;
                    } else if(currentX > width) {
                        int diff = currentX - width;
                        currentX = width - diff;
                        velX *= -1;
                    } else if(currentX < 1) {
                        int diff = currentX - 1;
                        currentX = 1 - diff;
                        velX *= -1;
                    }
                }
                if(currentY >= height || currentY <= 1) {
                    if(currentY == height || currentY == 1) {
                        velY *= -1;
                    } else if(currentY > height) {
                        int diff = currentY - height;
                        currentY = height - diff;
                        velY *= -1;
                    } else if(currentY < 1) {
                        int diff = currentY - 1;
                        currentY = 1 - diff;
                        velY *= -1;
                    }
                }
//                System.out.println(currentX + ", " + currentY + " || " + velX + ", " + velY);
            }
            System.out.println(ticks);
        }
        
    }
}
