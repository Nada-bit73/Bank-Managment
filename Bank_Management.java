/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_management;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Amcِ
 */
public class Bank_Management {
      
    
    static String ACCOUNTS_FILE = "C:/Userلs/Amcِ/Documents/NetBeansProjects/Bank_Management/src/bank_management/accounts.txt";
    static String TRANSACTIONS_FILE = "C:/Userلs/Amcِ/Documents/NetBeansProjects/Bank_Management/src/bank_management/transactions.txt";

    static Bank bank = new Bank();
    public static void main(String[] args) {
         try {
            ArrayList<Account> accounts = returnAccounts();
            loadAccounts(accounts);

            ArrayList<Transaction> transactions = returnTransactions();
            runTransactions(transactions);
            bank.deductTaxes();
            for (Account account : accounts) {
                System.out.println("\n\t\t\t\t\t ACCOUNT\n\n\t"+account+"\n\n");
                transactionHistory(account.getId());
            }
            
         } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
      
      
     
    }
    public static Account createObject(String[] values){
        try{
           return (Account) Class.forName("src.bank_management." + values[0])
          .getConstructor(String.class, String.class, double.class)
          .newInstance(values[1], values[2], Double.parseDouble(values[3]));
         }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
       
    }
    
    public static void loadAccounts(ArrayList<Account> accounts){
       for(Account account:accounts){
           bank.addAccount(account);
       }        
    }
    
    public static ArrayList<Account> returnAccounts() throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(ACCOUNTS_FILE);
        Scanner scan = new Scanner(fis);
        ArrayList<Account> accounts = new ArrayList<Account>();
        
        while(scan.hasNextLine()){
            accounts.add(createObject(scan.nextLine().split(",")));
        }
        scan.close();
        return accounts;
    }
    
    
      public static ArrayList<Transaction> returnTransactions() throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(TRANSACTIONS_FILE);
        Scanner scan = new Scanner(fis);
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        
        while(scan.hasNextLine()){
            String[] values = scan.nextLine().split(",");
            transactions.add(new Transaction(Transaction.Type.valueOf(values[1]), Long.parseLong(values[0]), values[2], Double.parseDouble(values[3])));
    
        }
          scan.close();
        Collections.sort(transactions);
        return transactions;
       
        
    }
      
      public static void runTransactions(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            bank.executeTransaction(transaction);
        }
    }
      
      public static void transactionHistory(String id)  {
        System.out.println("\t\t\t\t   TRANSACTION HISTORY\n\t");
        for (Transaction transaction : bank.getTransactions(id)) {
         
            System.out.println("\t"+transaction+"\n");
        }
        System.out.println("\n\t\t\t\t\tAFTER TAX\n");
        System.out.println("\t" + bank.getAccount(id) +"\n\n\n\n");
    }
}
