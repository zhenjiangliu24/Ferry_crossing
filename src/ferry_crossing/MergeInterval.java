/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferry_crossing;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ZhenjiangLiu
 */
public class MergeInterval {

    /**
     *This function is to merge the overlap interval
     * @param intervals is an arraylist of intervals
     * @return the return type is an arraylist of merged intervals
     */
    public ArrayList<Interval> merge(ArrayList<Interval> intervals){
        if(intervals.isEmpty() || intervals.size() ==1){
            return intervals;
        }
        Collections.sort(intervals, new IntervalComparator());
        Interval first = intervals.get(0);
        float start = first.start;
        float end = first.end;
        ArrayList<Interval> result = new ArrayList<>();
        for(int i = 1; i< intervals.size();i++){
            Interval current = intervals.get(i);
            if(current.start<=end){
                end = Math.max(end, current.end);
            }else{
                result.add(new Interval(start,end));
                start = current.start;
                end = current.end;
            }
        }
        result.add(new Interval(start,end));
        return result;
    }
}
