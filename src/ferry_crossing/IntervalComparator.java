/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferry_crossing;

import java.util.Comparator;

/**
 *
 * @author ZhenjiangLiu
 */
public class IntervalComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        Interval i1 = (Interval)o1;
        Interval i2 = (Interval)o2;
        return (int)(i1.start-i2.start);
    }
    
}
