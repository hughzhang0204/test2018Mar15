package QEmployee;

import java.util.TreeSet;

public class testEmployee {


   public static void main(String[] args){


       TreeSet<Employee> lastNameComp = new TreeSet<Employee>(new EmployeeLastNameComp());
       lastNameComp.add(new Employee("Ram", "Thompson",23));
       lastNameComp.add(new Employee("John","Holder",34));
       lastNameComp.add(new Employee("Chris","Peterson",62));
       lastNameComp.add(new Employee("Tom","Deckert",48));
       for(Employee em : lastNameComp){
           System.out.println(em);
       }
       System.out.println("==========================================================");
       TreeSet<Employee> ageComp = new TreeSet<Employee>(new EmployeeAgeComp());
       ageComp.add(new Employee("Ram", "Thompson",23));
       ageComp.add(new Employee("John","Holder",34));
       ageComp.add(new Employee("Chris","Peterson",62));
       ageComp.add(new Employee("Tom","Deckert",48));
       for(Employee em : ageComp){
           System.out.println(em);
       }
   }

}
