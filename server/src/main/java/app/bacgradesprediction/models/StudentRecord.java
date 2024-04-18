package app.bacgradesprediction.models;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static app.bacgradesprediction.server.utils.Constants.*;

@Getter
public class StudentRecord implements Serializable {

    private float grade1;
    private float grade2;
    private float grade3;
    private float bac ;

    public StudentRecord( float grade1, float grade2, float grade3,float bac) {
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.bac = bac;
    }

    public StudentRecord( ArrayList<Float> grades, float bac) {
        this.grade1 = grades.get(0);
        this.grade2 = grades.get(1);
        this.grade3 = grades.get(2);
        this.bac = bac;
    }

    @Override
    public String toString() {
        return String.format("{ %s: %f, %s: %f, %s: %f, %s: %f}",TRIMESTRE_ONE,grade1,TRIMESTRE_TWO,grade2,TRIMESTRE_THREE,grade3,BAC,bac)  ;
    }

    public Map<String,?> toMapTransformation(){
        Map<String, Float> record = new HashMap<>();
        record.put(TRIMESTRE_ONE,grade1);
        record.put(TRIMESTRE_TWO,grade2);
        record.put(TRIMESTRE_THREE,grade3);
        record.put(BAC,bac);

        return record;
    };


}
