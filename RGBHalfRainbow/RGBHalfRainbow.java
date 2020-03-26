package RGBHalfRainbow;
/*
from blue to red, five colours according to
https://simple.wikipedia.org/wiki/Rainbow
 */
public class RGBHalfRainbow {
    int[] value= new int[3];
    double min, max;//value range
    double l1, l2, l3,unitL;//between min and max
    public RGBHalfRainbow(double min, double max){
        this.min=min;
        this.max=max;
        unitL = (max-min)/4;//cut into 4 pieces
        l1 = unitL+ min;
        l2 = max- 2*unitL;
        l3 = max - unitL;
    }
     int[] getValue(double num){
         value[0]=255;
         value[2]= 0;
        if (num<=l1){
            value[1]= (int) (255*((num-min)/(unitL)));
        }else if (num<= l2){
            value[0] = (int) (255*(1-(num-l1)/unitL));
            value[1]= 255;
        }else if (num<= l3){
            value[0]=0;
            value[1]= (int) (255*(1-(num-l2)/unitL));
            value[2]= (int) (255*((num-l2)/unitL));
        }else {
            value[0]=0;
            value[1]=0;
            value[2]=(int) (255*(1-(num-l3)/unitL));
        }
        return value;
    }
}
