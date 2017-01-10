package fr.wildcodeschool.planecalculator;

/**
 * Created by romain on 03/01/17.
 */

public class PlaneModel {

    private double massEmpty;
    private double massMax;
    private double fuelCapacity;
    private double fuelDensity;
    private double minCentrage;
    private double maxCentrage;
    private double planeEmptyCentrage;
    private double passAvantCentrage;
    private double passArriereCentrage;
    private double bagageCentrage;
    private double fuelCentrage;
    private PassengerModel passager1, passager2, passager3, passager4;
    private double bagagesWeight;

    public PlaneModel(){}

    public PlaneModel(double massEmpty, double massMax, double fuelCapacity, double fuelDensity, double minCentrage, double maxCentrage, double planeEmptyCentrage, double passAvantCentrage, double passArriereCentrage, double bagageCentrage, double fuelCentrage) {
        this.massEmpty = massEmpty;
        this.massMax = massMax;
        this.fuelCapacity = fuelCapacity;
        this.fuelDensity = fuelDensity;
        this.minCentrage = minCentrage;
        this.maxCentrage = maxCentrage;
        this.planeEmptyCentrage = planeEmptyCentrage;
        this.passAvantCentrage = passAvantCentrage;
        this.passArriereCentrage = passArriereCentrage;
        this.bagageCentrage = bagageCentrage;
        this.fuelCentrage = fuelCentrage;
    }

    public String calculOptimum(){

        double[][] matrix = new double[6][2];
        matrix[0]= fuelAndCentrage(passager1.getWeight() + passager2.getWeight(), passager3.getWeight() + passager4.getWeight());
        matrix[1]= fuelAndCentrage(passager1.getWeight() + passager3.getWeight(), passager2.getWeight() + passager4.getWeight());
        matrix[2]= fuelAndCentrage(passager1.getWeight() + passager4.getWeight(), passager3.getWeight() + passager2.getWeight());
        matrix[3]= fuelAndCentrage(passager2.getWeight() + passager3.getWeight(), passager1.getWeight() + passager4.getWeight());
        matrix[4]= fuelAndCentrage(passager2.getWeight() + passager4.getWeight(), passager3.getWeight() + passager1.getWeight());
        matrix[5]= fuelAndCentrage(passager3.getWeight() + passager4.getWeight(), passager1.getWeight() + passager2.getWeight());

        String result = "";
        int indiceOptimal = 0;

        for(int i = 1; i<matrix.length; i++){

            if(matrix[i][0]>matrix[indiceOptimal][0]){
                indiceOptimal = i;
            }
            if (matrix[i][0] == matrix[indiceOptimal][0]){
                if (matrix[i][1]<matrix[indiceOptimal][1]){
                    indiceOptimal = i;
                }

            }

        }

        switch (indiceOptimal){
            case 0:
                result = "Les passagers " + passager1.getName() + " et " + passager2.getName() + " montent à l'avant\n";
                break;
            case 1:
                result = "Les passagers " + passager1.getName() + " et " + passager3.getName() + " montent à l'avant\n";
                break;
            case 2:
                result = "Les passagers " + passager1.getName() + " et " + passager4.getName() + " montent à l'avant\n";
                break;
            case 3:
                result = "Les passagers " + passager2.getName() + " et " + passager3.getName() + " montent à l'avant\n";
                break;
            case 4:
                result = "Les passagers " + passager2.getName() + " et " + passager4.getName() + " montent à l'avant\n";
                break;
            case 5:
                result = "Les passagers " + passager3.getName() + " et " + passager4.getName() + " montent à l'avant\n";
                break;
        }

        return result + "on emporte " + matrix[indiceOptimal][0] + "L d'essence";
    }

    private double[] fuelAndCentrage(double poidAvant, double poidArriere){

        // Calculer massTotal
        double massTotal = poidAvant+poidArriere+this.bagagesWeight+this.massEmpty;

        //Calculer maxmassFuel
        double maxFuelMasse = this.massMax- massTotal;
        double qtyFuel = maxFuelMasse / fuelDensity;
        if (qtyFuel > fuelCapacity){
            qtyFuel = fuelCapacity;
            maxFuelMasse = qtyFuel * fuelDensity;
        }

        //Calculer des moments
        double planeEmptyMoment = massEmpty*planeEmptyCentrage;
        double pAvantMoment = poidAvant*passAvantCentrage;
        double pArriereMoment = poidArriere*passArriereCentrage;
        double bagagesMoment = this.bagagesWeight*bagageCentrage;
        double fuelMoment = maxFuelMasse*fuelCentrage;
        double momentTotal = planeEmptyMoment+pAvantMoment+pArriereMoment+bagagesMoment+fuelMoment;
        double massMax = massTotal+maxFuelMasse;

        //Caluler le Centrage
        double levierTotal = momentTotal/massMax;

        return new double[]{qtyFuel,levierTotal};
    }

    public PassengerModel getPassager1() {
        return passager1;
    }

    public void setPassager1(PassengerModel passager1) {
        this.passager1 = passager1;
    }

    public PassengerModel getPassager2() {
        return passager2;
    }

    public void setPassager2(PassengerModel passager2) {
        this.passager2 = passager2;
    }

    public PassengerModel getPassager3() {
        return passager3;
    }

    public void setPassager3(PassengerModel passager3) {
        this.passager3 = passager3;
    }

    public PassengerModel getPassager4() {
        return passager4;
    }

    public void setPassager4(PassengerModel passager4) {
        this.passager4 = passager4;
    }

    public double getBagagesWeight() {
        return bagagesWeight;
    }

    public void setBagagesWeight(double bagagesWeight) {
        this.bagagesWeight = bagagesWeight;
    }
}
