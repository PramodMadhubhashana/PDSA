import java.util.Scanner;

/**
 * Shopping_Card
 */
class Shopping_Card {

    public static void main(String[] args) {

        int num;
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 Insrt Data");
        System.out.println("2 Add to Card");
        System.out.println("3 Show all items.");
        System.out.println("Enter the Number..");
        num = scanner.nextInt();     
        AddData a1 = new AddData();     
        
        if(num == 1) {            
            ClearConsole c1 = new ClearConsole();
            System.out.println("1 Insert new item.");
            System.out.println("2 Delete data.");
            System.out.println("3 Show All Item.");
            num = scanner.nextInt();           

            switch (num) {
                case 1:

                    System.out.println("Enter the item Price.");
                    float price = scanner.nextFloat();
                    System.out.println("Enter Quantuty.");
                    int quantity = scanner.nextInt();
                    System.out.println("Enter item Code.");
                    int icode = scanner.nextInt();
                    System.out.println("Enter item Name.");
                    String  iname = scanner.nextLine();
        
                    a1.InsertData(price, quantity, icode, iname);                    
                    System.out.println("Insert Succesfull...");

                    break;
        
                case 2:
                    a1.deleteEnd();
                    System.out.println("Delete Successfull...");
                    break;

                case 3:
                    a1.print();
                    break;
                default :
                    main(args);
            }             
        }
        else if(num == 2) {
            ClearConsole c2 = new ClearConsole();
            CartList cartList = new CartList();
            int itemcode;

            for (;;) {
                System.out.println("Enter item Code (or -1 to exit):");
                itemcode = scanner.nextInt();
            
                if (itemcode == -1) {
                    break; // exit the loop if -1 is entered
                }
            
                System.out.println("Enter Quantity:");
                int qty = scanner.nextInt();
                AddData addData = new AddData();
                Data item = addData.searchNode(itemcode);
            
                if (item != null) {
                    cartList.InsertCart(item.price, qty, itemcode, item.iname);
                } else {
                    System.out.println("No Items");
                }
            }            
            
            System.out.println("Rs- " + cartList.TotalPrice());

        }        
        else if(num == 3){
            a1.print();
        }
        else{            
            main(args);
        }
        scanner.close();    
    }    
    
}

/**
 * Cart
 */
class Cart {
    
    float price;
    int quantity;
    int icode;
    String iname;
    Cart next;
    Cart Prev;

    public Cart(float price, int quantity, int icode, String iname){
        this.price = price;
        this.quantity = quantity;
        this.icode = icode;
        this.iname = iname;
    }
}


/**
 * InnerShopping_Card
 */
class CartList {

    Cart head;
    Cart tail;

    public CartList(){        
        this.head = null;
        this.tail = null;                
    }

    public void InsertCart(float price, int quantity, int icode, String iname){

        Cart c1 = new Cart(price, quantity, icode, iname);

        if(this.head == null){
            this.head = c1;
            this.tail = c1;
        }
        else {
            c1.Prev = this.tail;
            this.tail.next = c1;
            this.tail = c1;
        }
    }

    public float TotalPrice() {
        Cart current = head;
        float price = 0;
        while (current != null) {
            System.out.println(current.icode + " " + current.iname + " " + current.quantity + " " +  current.price);    
            price += current.price * current.quantity;
            current = current.next;
        }
        return price;        
    }
    
}

/**
 * Data
 */
class Data {   

    float price;
    int quantity;
    int icode;
    String iname;
    Data next;
    Data prev;

    public Data(float price, int quantity, int icode, String iname){
        this.price = price;
        this.quantity = quantity;
        this.icode = icode;
        this.iname = iname;
    }
}

/**
 * AddData
 */
class AddData {

    Data head;
    Data tail;
    
    float price;
    int quantity;
    int icode;
    String iname;

    public AddData(){
        this.head = null;
        this.tail = null;;
    }

    public void InsertData(float price, int quantity, int icode, String iname){
        Data d1 = new Data(price, quantity, icode, iname);

        if(this.head == null){
            this.head = d1;
            this.tail = d1;
        }
        else {
            d1.prev = this.tail;
            this.tail.next = d1;
            this.tail = d1;
        }
    }

    public Data searchNode(int target) {
        Data current = head;

        while (current != null) {
            if (current.icode == target) {
                return current; 
            }
            current = current.next;
        }
        return null;
    }


    public void deleteEnd() {            

        if(this.tail == null) {
            System.out.println("Link list is emty!!!!!!!!");
        }
        else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
    }
    public void print() {
        
        Data tempP = this.head;

        if(tempP == null) {
            System.out.println("Link list is Emty!!!!!!!!!!!");
        }
        else {
            while (tempP != null) {
                System.out.println(tempP.price);
                System.out.println(tempP.icode);
                System.out.println(tempP.iname);
                tempP = tempP.next;
            }
        }
    }
    
}

class ClearConsole {    

    public ClearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            
            System.out.println("Error while clearing console: " + e.getMessage());
        }
    }
}


