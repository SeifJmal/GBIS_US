import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
public class KataMainClass{
     public static void main(String []args){
       Account account1 = new Account(1234, 10550.5);
       account1.createOperation("save", 1000);
       account1.createOperation("retrieve", 55000);
       account1.createOperation("retrieve", 10000);
       account1.createOperation("save", 3500);
       Account account2 = new Account(1235, 10550.5);
       account2.createOperation("save", 1000);
       account2.createOperation("save", 500);
       account1.showMyHistoryOfOperations();
     }
}
class Account{
    private int accountId;
    private double amount;
    private List<Operation> operations = new ArrayList<Operation>();
    public Account(int id, double amount){
        this.accountId = id;
        this.amount = amount;
    }
    
    private double addAmmount(double amountToAdd){
        this.amount += amountToAdd;
        return this.amount;
    }
    private double retreiveAmmount(double amoutToRetreive){
        if(this.canRetreive(amoutToRetreive)){
            this.amount -= amoutToRetreive;
            return this.amount;
        } else{ System.out.println("Cant retreive this amount");
            return this.amount;
        }
    }
    private boolean canRetreive(double amoutToRetreive){
        return this.amount>=amoutToRetreive && amoutToRetreive > 0.0;
    }
    public void createOperation(String operationType,double amount){
        Operation operation = new Operation(operationType,LocalDate.now(),amount);
        if(operation.getOperationType().equals("save")){
            operation.setNewBase(this.addAmmount(amount));
            this.operations.add(operation);
        } else if(operation.getOperationType().equals("retrieve") && 
        this.canRetreive(amount)){
            operation.setNewBase(this.retreiveAmmount(amount));
            this.operations.add(operation);
        } else {System.out.println("failed operation"); }
        
    }
    
    public void showMyHistoryOfOperations(){
        this.operations.sort(Comparator.comparing(Operation::getOperationDate));
        this.operations.forEach((operation) ->System.out.println( "Operation: "+operation.getOperationType()
        +" Date: "+ operation.getOperationDate()+ " Amount: "+operation.getAmount()
        +" Base: "+operation.getBase()));
    }
    
}
class Operation{
    private String operationType;
    private LocalDate operationDate;
    private double amount;
    private double base;
    
    public Operation(String operation, LocalDate operationDate,double amount){
        this.operationType = operation;
        this.operationDate = operationDate;
        this.amount = amount;
    }
    public void setNewBase(double base){
        this.base = base;
    }
    public String getOperationType(){
        return this.operationType;
    }
    public LocalDate getOperationDate(){
        return this.operationDate;
    }
    public double getAmount(){
        return this.amount;
    }
    public double getBase(){
        return this.base;
    }
    
}
