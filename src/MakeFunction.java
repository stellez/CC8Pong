/**
 * Created by Steven on 01/09/2014.
 */
public class MakeFunction {
    private float gradient;
    private int bConstant;

    public MakeFunction(){
        //y = 1*x + 10
        gradient = 1;
        bConstant = 10;
    }

    public int getYPosition(int XPos){
       return ((int)(gradient*XPos)) + bConstant;
    }
}
