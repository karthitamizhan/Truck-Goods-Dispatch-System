import java.util.*;
class Goods{
    String name;
    int weight;
    int quantity;
    static int capacity=0, t_quantity=0;
    int full=500, totalWeight;
    static HashMap<String, Integer> l=new HashMap<>();
    static HashMap<String, List<Integer>> last=new HashMap<>();
    void addItem(String name){
        this.name=name;
    }
    void addWeight(int weight){
        this.weight=weight;
    }
    boolean addQuantity(int quantity){
        this.quantity=quantity;
        capacity+=weight*quantity;
        totalWeight=weight*quantity;
        if(capacity>full){
            capacity-=weight*quantity;
            return false;
        }
        if(last.get(name)==null)
            last.put(name, new ArrayList(Arrays.asList(weight,quantity,weight*quantity)));
        if(l.get(name)==null)
            l.put(name, weight);
        return true;
    }
    void display(){
        System.out.println("The total weight of the goods is "+capacity);
    }
    void remaining(){
        System.out.println("the remaining capacity of the goods is "+ (full-capacity));
    }
    boolean removeItem(String item, int q){
        if(l.containsKey(item)){
            int a=l.get(item);
            capacity-=a*q;
            
            return true;
        }
        return false;
    }
    void displayAll(){
        int n=0;
        System.out.println("Sno \tGoods name \tWeight \t\tQuantity \tTotol weigth");
        System.out.println("----------------------------------------------------------------------");
        for(String s:last.keySet()){
            System.out.print("\n"+ ++n);
            System.out.print("\t"+s);
            List<Integer> ans=last.get(s);
            for(int res:ans){
            System.out.print("\t\t"+res);
            }
        }
    }
}
class TruckApplication{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Goods g=new Goods();
        while(true){
            System.out.println("\n 1.Add goods \n 2.Remove goods \n 3.Display Goods \n 4.cancel");
            System.out.println("\nEnter your choice");
            int choice=sc.nextInt();
            switch(choice){
                case 1: System.out.println("Name of goods");
                        g.addItem(sc.next());
                        System.out.println("Weight of goods");
                        g.addWeight(sc.nextInt());
                        System.out.println("Quantity of goods");
                        if(g.addQuantity(sc.nextInt())){
                            System.out.println("Goods added successfully");
                            g.remaining();
                            g.display();
                        }else{
                            System.out.println("Goods cannot be added");
                            g.display();
                        }
                        break;
                
                case 2: System.out.println("Enter name of goods to remove & quantity");
                        if(g.removeItem(sc.next(), sc.nextInt())){
                            g.remaining();
                            g.display();
                        }else{
                            System.out.println("The goods is not in the truck");
                        }
                        break;

                case 3: g.displayAll();
                        break;
                
                case 4: System.exit(0);
            }
        }
    }
}