package Chp00;
//nested class and overloaded constructors to eliminate the complexity of the constructor parameters

//the principles are based on NeuralNetConfiguration class in Deeplearning4j:
// to show a cascade of overloaded constructors which are used to "configure" an object avoiding large amounts of parameters
//related tutorial referenced: https://www.geeksforgeeks.org/static-class-in-java/

public class MasterProject {
    public int number;
    public char type;

    public MasterProject(char type, int number) {
        this.number = number;
        this.type = type;
    }

    public class Project {
        private int amount;//unit is million ringgit
        private int year;
        private String contractor;

        //all-in-one constructor

        public Project() {
            amount = 0;
            year = 1+970;
            contractor = "None";
        }

        public Project amount(int amount){
            this.amount=amount;
            return this;
        }

        public Project year(int year){
            this.year=year;
            return this;
        }

        public Project constructor(String contractor) {
            this.contractor = contractor;
            return this;
        }

        @Override
        public String toString() {
            return "Project{" +
                    //accessed from master class
                    "type: " + type+
                    ", number: "+ number +
                    //
                    ", amount=" + amount +
                    ", year=" + year +
                    ", contractor='" + contractor + '\'' +
                    '}';
        }
    }

}

class main{
    public static void main(String[] args) {
        MasterProject projectA0= new MasterProject('A',1);
        MasterProject.Project projectA1 = projectA0. new Project();
        projectA1 = projectA1.amount(100).year(2015).constructor("Wanlin");
        System.out.println(projectA1.toString());

        MasterProject.Project projectA2= projectA0. new Project();//another A type project
        projectA2 = projectA2.amount(120).year(2013).constructor("Chanyon");
        System.out.println(projectA2.toString());

        MasterProject projectB0= new MasterProject('B',2);
        MasterProject.Project projectB1 = projectB0. new Project();
        projectB1 = projectB1.amount(85).year(2016).constructor("Hunte");
        System.out.println(projectB1.toString());
    }
}
