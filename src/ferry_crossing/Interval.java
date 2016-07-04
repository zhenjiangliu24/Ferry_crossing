/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferry_crossing;

/**
 *
 * @author ZhenjiangLiu
 */
public class Interval {
    float start;
    float end;

    /**
     *The instance of the function is an emtpy time interval
     */
    public Interval(){
        this.start = 0;
        this.end = 0;
    }

    /**
     *The instance of the function is a time interval
     * @param start
     * @param end
     */
    public Interval(float start, float end){
        this.start = start;
        this.end = end;
    }
}
