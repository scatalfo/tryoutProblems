
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bcata
 */
public class Problem3 {
    public static void main(String[] args) throws IOException{
        FileReader fr = new FileReader("C:\\Users\\bcata\\Documents\\NetBeansProjects\\2016Problems\\2013Problems\\EatingFrenzyIn.txt");
        BufferedReader br = new BufferedReader(fr);
        int numberOfInputs = Integer.parseInt(br.readLine());
        for (int input = 0; input < numberOfInputs; input++){
            int numberOfTeams = Integer.parseInt(br.readLine());
            Scanner line = new Scanner(br.readLine());
            Node root = new Node(0);
            for (int team = 0; team < numberOfTeams; team++){
                if (team == 0){
                    root = new Node(line.nextInt());
                }
                else{
                    Node node = new Node(line.nextInt());
                    root.addChildToBottom(root, node);
                    //System.out.println("added child node");
                }
            }
            Node.encrypt(root);
            System.out.println("");
        }
    }
    
}
class Node {
    private int teamNum;
    private Node parent;
    private ArrayList<Node> children;

    public Node(int teamNum) { //root node
        this.teamNum = teamNum;
        this.children = new ArrayList<>();
    }

    public Node(int teamNum, Node parent) {
        this.teamNum = teamNum;
        this.parent = parent;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public int numChildren() {
        return this.children.size();
    }
    
    public int getTeamNum() {
        return this.teamNum;
    }
    
    public int fullDepth(int depth){
        if (this.numChildren() < 2){
            return depth;
        }
        else{
            depth++;
            return Math.min(this.children.get(0).fullDepth(depth), this.children.get(1).fullDepth(depth));
        }
    }
    
    public void addChildToBottom(Node root, Node child) {
        Node bottomNode = root;
        while (bottomNode.numChildren() == 2) {
            //check left branch
            if (bottomNode.children.get(0).numChildren() != 2){
                bottomNode = bottomNode.children.get(0);
            }
            //check right branch
            else if (bottomNode.children.get(1).numChildren() != 2){
                bottomNode = bottomNode.children.get(1);
            }
            else{
                if (bottomNode.children.get(0).fullDepth(0) > bottomNode.children.get(1).fullDepth(0)){
                    bottomNode = bottomNode.children.get(1);
                }
                else{
                    bottomNode = bottomNode.children.get(0);
                }
            }
        }
        bottomNode.addChild(child);
        child.setParent(bottomNode);
    }
    
    public void setParent(Node parent){
        this.parent = parent;
    }
    
    public static void encrypt(Node node){
        if (node.children.size() == 0){
            //System.out.println("Found a node without two children. Num children: " + node.children.size());
            System.out.print(node.getTeamNum() + " ");
        }else{
            if (node.children.size() == 2){
                encrypt(node.children.get(1));
            }
            encrypt(node.children.get(0));
            System.out.print(node.getTeamNum() + " ");
        }
    }
}

