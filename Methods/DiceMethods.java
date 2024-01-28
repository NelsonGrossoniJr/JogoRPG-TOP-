package Methods;

import java.util.Random;

public class DiceMethods {
    Random random = new Random();

    public int dice2Sides(){

        return random.nextInt(2) + 1;
    }

    public int dice4Sides(){

        return random.nextInt(4) + 1;
    }

    public int dice6Sides(){

        return random.nextInt(6) + 1;
    }

    public int dice8Sides(){

        return random.nextInt(8) + 1;
    }


}
