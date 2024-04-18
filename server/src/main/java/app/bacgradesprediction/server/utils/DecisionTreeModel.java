package app.bacgradesprediction.server.utils;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Attribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static app.bacgradesprediction.server.utils.Constants.*;

public class DecisionTreeModel {
    private J48 tree;
    private Instances structure;

    public DecisionTreeModel() {
        this.tree = new J48();
        this.structure = null;
    }

    public void train(List<Map<String, ?>> records) throws Exception {

        System.out.println("STARTING REAL TRAINING");

        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute(TRIMESTRE_ONE));
        attributes.add(new Attribute(TRIMESTRE_TWO));
        attributes.add(new Attribute(TRIMESTRE_THREE));
        List<String> classValues = new ArrayList<>();
        classValues.add("Excellent");
        classValues.add("Very Good");
        classValues.add("Good");
        classValues.add("Acceptable");
        classValues.add("Passable");
        classValues.add("Bad");
        Attribute classAttribute = new Attribute("BACMention", classValues);
        attributes.add(classAttribute);

        Instances dataset = new Instances("BacGrades", attributes, records.size());
        dataset.setClassIndex(dataset.numAttributes() - 1);


        for (Map<String, ?> record : records) {
            DenseInstance instance = new DenseInstance(attributes.size());
            instance.setValue(attributes.get(0), (Float) record.get(TRIMESTRE_ONE));
            instance.setValue(attributes.get(1), (Float) record.get(TRIMESTRE_TWO));
            instance.setValue(attributes.get(2), (Float) record.get(TRIMESTRE_THREE));
            String bacMention = convertBacToCategory((Float) record.get(BAC));
            instance.setValue(classAttribute, bacMention);
            dataset.add(instance);


        }

        tree.buildClassifier(dataset);
        this.structure = new Instances(dataset, 0);

    }

    public String predict(float grade1, float grade2, float grade3) throws Exception {
        // Make sure `structure` is not null and has been initialized properly
        if (structure == null) {
            throw new IllegalStateException("The model structure has not been initialized.");
        }

        Instance instance = new DenseInstance(3);
        instance.setValue(structure.attribute(0), grade1);
        instance.setValue(structure.attribute(1), grade2);
        instance.setValue(structure.attribute(2), grade3);
        instance.setDataset(structure); // This must be set before calling classifyInstance

        // Ensure `tree` is not null and has been trained
        if (tree == null) {
            throw new IllegalStateException("Decision tree model has not been trained.");
        }

        double predictionIdx = tree.classifyInstance(instance);
        System.out.println(tree.toString());
        return instance.classAttribute().value((int) predictionIdx);
    }

}
