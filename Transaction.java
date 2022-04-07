
package bank_management;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class Transaction implements Comparable<Transaction> {

    @Override
    public int compareTo(Transaction o) {
    return Double.compare(this.timestamp, o.timestamp);
        }
    public enum Type {WITHDRAW,DEPOSIT};
    private Type type;
    private long timestamp;
    private String id;
    private double amount;
    
    
    public Transaction(Type type,long timestamp,String id,double amount){
         if(id == null || id.isEmpty() || amount < 0){
            throw new IllegalArgumentException("INVALID ARGUMENTS");
        }
         this.type = type;
         this.timestamp = timestamp;
         this.id = id;
         this.amount = amount;
    }
    
     public Transaction(Transaction source){
         if(id == null || id.isEmpty() || amount < 0){
            throw new IllegalArgumentException("INVALID ARGUMENTS");
        }
         this.type = source.type;
         this.timestamp = source.timestamp;
         this.id = source.id;
         this.amount = source.amount;
    }
     
     public Type getType(){
         return this.type;
     }
     
     public long getTimestamp(){
         return this.timestamp;
     }
     
     public String getId(){
         return this.id;
     }
     
      public double getAmount(){
         return this.amount;
     }
      
      public void setType(Type type){
          this.type = type;
      }
      
      public void setTimestamp(long timestamp){
          this.timestamp = timestamp;
      }
      
      public void setId(String id){
          if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("INVALID ID");
        }
          this.id = id;
      }
      
      public void setAmount(double amount){
          if(amount < 0){
            throw new IllegalArgumentException("INVALID AMOUNT");
        }
          this.amount = amount;
      }
      
      public String returnDate(){
          Date date = new Date(this.timestamp * 1000);
          return new SimpleDateFormat("dd-MM-yyyy").format(date);
          
      }
      
       @Override
      public boolean equals(Object obj){
       if(obj == null){
           return false;
       }if(!(obj instanceof Transaction)){
           return false;
       }
       
       Transaction transaction = (Transaction) obj;
       return Objects.equals(type,transaction.type) && timestamp == transaction.timestamp && id == transaction.id && amount == transaction.amount ;
       
   }
    @Override
    public int hashCode(){
        return Objects.hash(type,timestamp,id,amount);
    }
    
     @Override
    public String toString() {
        return (type) + "    " +
            "\t" + this.returnDate() + "" +
            "\t" + id + "" +
            "\t$" + amount + "";
    }
}
