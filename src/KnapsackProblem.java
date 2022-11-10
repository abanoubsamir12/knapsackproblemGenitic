
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class KnapsackProblem {

    //---------------- initialize the population-------------------
    private static String makeGene(int numberOfItems) {
        StringBuilder gene = new StringBuilder(numberOfItems);
        char c;
        for (int i = 0; i < numberOfItems; i++) {
            c = '0';
            double rnd = Math.random();
            if(rnd > 0.5) {
                c = '1';
            }
            gene.append(c);
        }
        return gene.toString();
    }
    private static void makePopulation(ArrayList<chromosome> population, int numberOfItems) {
        for(int i = 0; i < 30; i++) {
            String x = makeGene(numberOfItems);
            boolean isExists = false;
            for (int j=0; j<population.size(); j++){
                if(x.equals( population.get(j).content)){
                    isExists = true;
                    break;
                }
            }
            if(!isExists)
                population.add(new chromosome(x));
        }
    }

    public static int closestInd(int rand , ArrayList<chromosome> population)
    {
        int min = 100000;
        int returnedInd = -1;
        for(int i=0 ; i<population.size() ; i++)
        {
            if(Math.abs(rand - population.get(i).rankVal) < min)
            {
                min = Math.abs(rand - population.get(i).rankVal);
                returnedInd = i;
            }
        }
        return  returnedInd;
    }
    //el mfrood el array bekhush mtsort 3 el fitness value bta3to (de hint bs)
    public static ArrayList<chromosome> rankSelection(ArrayList<chromosome> population)
    {
        int numofItems = population.get(0).content.length();
        int iterator = numofItems;
        double sum = numofItems * (numofItems+1) /2;
        for(int i=0 ; i< numofItems ; i++)
        {
            population.get(i).rankVal = (int) ((iterator)/sum * 100);
            iterator--;
        }
        int b = (int)(Math.random()*(100-0+1)+0);
        int c = (int)(Math.random()*(100-0+1)+0);
        if(c == b)
        {
            while(c == b)
            {
                c =  (int)(Math.random()*(100-0+1)+0);
            }
        }
        ArrayList<chromosome> res = new ArrayList<>();

        res.add(population.get(closestInd(b,population)));
        res.add(population.get(closestInd(c,population)));

        return  res;
    }
    public  static String  crossover(chromosome c1 , chromosome c2 ) {
        int bigMid = c1.content.length();
        int smallMid = bigMid/2;
        String c11 = "";
        for(int i=0 ; i< smallMid ; i++)
        {
            c11+= c1.content.charAt(i);
        }
        for(int i=smallMid ; i< bigMid ; i++)
        {
            c11+= c2.content.charAt(i);
        }
        String c21 = "";
        for(int i=0 ; i< smallMid ; i++)
        {
            c21+= c2.content.charAt(i);
        }
        for(int i=smallMid ; i< bigMid ; i++)
        {
            c21+= c1.content.charAt(i);
        }
        return c11+c21;
    }
    public static chromosome mutation(chromosome crom , int numofItems) {
        int b = (int)(Math.random()*(numofItems-0+1)+0);
        String ans ="";
        for(int i=0 ; i< numofItems ; i++)
        {
            if(i==b)
            {
                if(crom.content.charAt(i) == '1')
                    ans+='0';
                else
                    ans+='1';
            }
            else
                ans+= crom.content.charAt(i);
        }
        return  new chromosome(ans);
    }
    public static void calcFitness(int numberOfItems, ArrayList<chromosome> population
            , ArrayList<Integer>valueOfItems,ArrayList<Integer>weightOfItems){
        for (int i=0; i< population.size(); i++){
            for (int j=0; j<numberOfItems; j++){
                if(population.get(i).content.charAt(j) == '1'){
                    population.get(i).value+=valueOfItems.get(j);
                    population.get(i).weight += weightOfItems.get(j);
                }
            }
        }
    }







    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int test_cases= sc.nextInt();



        while(test_cases != 0){
            int knapsack_capacity = sc.nextInt();
            int numOfItems = sc.nextInt();
            ArrayList<Integer> value_of_items = new ArrayList<Integer>();
            ArrayList<Integer> weight_of_items = new ArrayList<Integer>();
            ArrayList<chromosome> population = new ArrayList<>();
            //ArrayList<Double> fitness = new ArrayList<Double>();
            for (int i=0; i < numOfItems; i++){
                int weight = sc.nextInt();
                int value = sc.nextInt();
                weight_of_items.add(weight);
                value_of_items.add(value);
            }
            makePopulation(population , numOfItems);
            calcFitness(numOfItems,population,value_of_items,weight_of_items);
            Collections.sort(population, new Comparator<chromosome>() {
                public int compare(chromosome b1, chromosome b2) {
                    if(b1.value > b2.value)
                        return 1;
                    else if(b1.value < b2.value)
                        return -1;
                    else
                        return 0;
                }
            });
            ArrayList<chromosome> rSelected = rankSelection(population);
            String bigS =  crossover(rSelected.get(0) , rSelected.get(1));
            rSelected.get(0 ).content = bigS.substring(0 , numOfItems);
            rSelected.get(1).content = bigS.substring(numOfItems , numOfItems*2);
            mutation(rSelected.get(0), numOfItems);


            for(int i=0 ; i< population.size() ; i++)
            {
                System.out.println(population.get(i));
            }
            test_cases--;
        }
    }

}