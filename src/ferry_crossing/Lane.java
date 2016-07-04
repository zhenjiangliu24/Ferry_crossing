/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferry_crossing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ZhenjiangLiu
 */
public class Lane {
    List<Ship> ships;//this is an arraylist to contain all the ships inside the lane
    Direction direct;
    int shipNum;

    /**
     *
     * @param direct is the direction of a lane
     * @param shipNum is the number of how many ships are there in the lane
     */
    public Lane(Direction direct,int shipNum){
        this.ships = new ArrayList<>();
        this.direct = direct;
        this.shipNum = shipNum;
    }
}
