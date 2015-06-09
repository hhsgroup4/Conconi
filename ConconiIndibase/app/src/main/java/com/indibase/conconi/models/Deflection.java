package com.indibase.conconi.models;

import java.util.ArrayList;

/**
 * Created by Vince on 2-6-2015.
 */
public class Deflection {
    private static int deflectionPoint;

    public static int getDeflectionPoint(ArrayList<Integer> measurements){
        //Variables
        int angle,first,last, size;
        size = measurements.size() -1;

        first = measurements.get(0);
        last = measurements.get(size);

        //With first and last point calculate the angle
        angle = getAngle(first,last,size);

        //Deflection point
        deflectionPoint = deflection_point(measurements, angle);
        //Pass deflection point
        System.out.println(deflectionPoint);

        return deflectionPoint;
    }
    //Gives the angle
    private static int getAngle(int start, int end, int length){
        return (end-start)/length;
    }

    //Compare points with function
    private static int deflection_point(ArrayList<Integer> array, int angle)
    {
        //Local variables
        int currentDeflection,currentAngle,difference,defPoint;
        currentDeflection=0;
        defPoint = 0;
        for(int i=0; i < array.size();  i++)
        {
            //Where we are
            currentAngle = angle*i +array.get(0) ;
            //Function-measure difference
            difference = array.get(i)-currentAngle;
            //condition
            if(difference > currentDeflection) {
                currentDeflection = difference;
                defPoint = array.get(i);
            }
        }

        return defPoint;
    }

}
/*
Unit Test
ArrayList<Integer> arrayRandom = new ArrayList<Integer>;

Random rand = new Random();
rand.setSeed(System.currentTimeMillis());
for (int i=0; i<4320; i++)
{
    Integer r = rand.nextInt() % 256;
    arrayRandom.add(r);
}



 */