package app.bacgradesprediction.models;


public class StudentRecord {
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

    @Override
    public String toString() {
        return String.format("{ S1: %f, S2: %f, S3: %f, BAC: %f}",grade1,grade2,grade3,bac)  ;
    }
}
