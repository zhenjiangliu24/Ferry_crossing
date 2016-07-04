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

public class Ship {
    int lenth;
    int position;
    Direction direct;
    int speed;

    /**
     *This function returns an instance of a Ship
     * @param length is the length of the ship
     * @param position is the position of the ship
     * @param direct is the direction of the ship
     * @param speed is the speed of the ship
     */
    public Ship(int length, int position, Direction direct, int speed){
        this.lenth = length;
        this.direct = direct;
        this.speed = speed;
        if(direct == Direction.EAST){
            this.position = -position;
        }else{
            this.position = position;
        }
        
    }
}
