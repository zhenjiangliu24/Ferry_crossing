/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferry_crossing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ZhenjiangLiu
 */
public class Ferry_crossing {
    int laneNum;
    int width;
    int shipSpeed;
    int ferrySpeed;
    int earlyTime;
    int lateTime;
    List<Lane> laneList;
    ArrayList<Interval> intervals;
    MergeInterval merge;

    /**
     *The instance of the function is a demo to solve the ferry crossing problem
     */
    public Ferry_crossing(){
        this.laneList = new ArrayList<>();
        this.intervals = new ArrayList<>();
        this.merge = new MergeInterval();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Ferry_crossing demo = new Ferry_crossing();
        //read the input file in the format of txt
        //and load all the data into an arraylist of lane
        demo.readFile("input2.txt");
        //for all the ships, calculate the collision interval with the ferry
        for(int i = 0; i<demo.laneList.size();i++){
            for(int j = 0; j<demo.laneList.get(i).ships.size();j++){
                demo.calculateInterval(demo.laneList.get(i).ships.get(j).lenth, 
                        demo.laneList.get(i).ships.get(j).position, i);
            }
        }
        //also have to consider the early starting time
        if(demo.earlyTime!=0){
            demo.intervals.add(new Interval(0,demo.earlyTime));
        }
        //merge the collision interval
        ArrayList<Interval> mergeResult = demo.merge.merge(demo.intervals);
        //find the max gap between these collision intervals
        float result = demo.findMaxSafeInterval(mergeResult);
        System.out.println("result is: "+result);

    }
    
    /**
     *This function is to compute the largest gap between the collision interval
     * @param intervals
     * @return
     */
    public float findMaxSafeInterval(ArrayList<Interval> intervals)
    {
        if(intervals.isEmpty()){
            return (float)(this.lateTime-this.earlyTime);
        }else{
            float count = 0;
            for(int i = 0; i< intervals.size();i++){
                if(i==0){
                    count = intervals.get(i).start;
                }else{
                    count = Math.max(count, intervals.get(i).start-intervals.get(i-1).end);
                }
            }
            return count;
        }
    }
    
    /**
     *This function is to compute one ship's collision interval with the ferry
     * @param shipLength
     * @param shipPosition
     * @param laneNum 0 means the lane is next to the ferry
     */
    public void calculateInterval(int shipLength, int shipPosition, int laneNum)
    {
        float startInterval,endInterval;
        if(laneNum!=0){
            //the ship is not on the first lane
            startInterval = shipPosition/this.shipSpeed - this.width*laneNum/this.ferrySpeed;
            endInterval = startInterval + shipLength/this.shipSpeed;
        }else{
            //when the ship is on the first lane
            startInterval = shipPosition/this.shipSpeed - this.width*(laneNum+1)/this.ferrySpeed;
            endInterval = (shipPosition+shipLength)/this.shipSpeed;
        }
        this.intervals.add(new Interval(startInterval,endInterval));
    }
    
    /**
     *This function is to read the input files
     * @param fileName
     */
    public void readFile(String fileName)
    {
        try{
            FileReader inputfile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputfile);
            String line;
            int lineCount = 0;
            while((line = bufferReader.readLine())!=null){
                String[] data = line.split("\\s+");//split by space
                //Lane newLane;
                if(lineCount == 0){
                    this.laneNum = Integer.parseInt(data[0]);
                    this.width = Integer.parseInt(data[1]);
                    this.shipSpeed = Integer.parseInt(data[2]);
                    this.ferrySpeed = Integer.parseInt(data[3]);
                    this.earlyTime = Integer.parseInt(data[4]);
                    this.lateTime = Integer.parseInt(data[5]);
                    lineCount++;
                }else{
                    int laneShipNum = Integer.parseInt(data[1]);
                    Lane newLane;
                    if("W".equals(data[0])){
                        newLane = new Lane(Direction.WEST,laneShipNum);
                        this.laneList.add(newLane);

                    }else if("E".equals(data[0])){
                        newLane = new Lane(Direction.EAST,laneShipNum);
                        this.laneList.add(newLane);
                    }
                    int count = 2;
                    for(int i = 0; i<laneShipNum;i++){
                        Ship ship = new Ship(Integer.parseInt(data[count]),
                                Integer.parseInt(data[count+1]),
                                this.laneList.get(lineCount-1).direct,
                                this.shipSpeed);
                        if(this.laneList.get(lineCount-1).direct==Direction.WEST){
                            this.laneList.get(lineCount-1).ships.add(ship);
                        }else{
                            this.laneList.get(lineCount-1).ships.add(0, ship);
                        }
                        count+=2;
                    }
                    lineCount++;
                }
            }
        }catch(IOException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
}
