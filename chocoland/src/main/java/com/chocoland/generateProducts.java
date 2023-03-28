package com.chocoland;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

public class generateProducts{

    public static String pathToProductsCSV = "csvFiles/chocolates.csv";

    public static List<Product> products = new ArrayList<Product>();

    public static List<Product> generate() 
    {
        try {
            String line = "";
            int count = 0; 
            // change the path to  relative....
            BufferedReader br = new BufferedReader(new FileReader(pathToProductsCSV));  
            while ((line = br.readLine()) != null)
            
            {  
                count+=1;
                if(count==1) continue;
                String[] c = line.split(",");  
                Product product = new Product(Integer.parseInt(c[0].trim()), c[1], c[2], c[3], Double.parseDouble(c[4].trim()), c[5], c[6]);
                products.add(product);
            }  
            }
        catch(IOException e)
        {
            System.out.println(e);
        }
        for (Product p: products)
        {
            System.out.println(p.name + " " + p.cost);
        }

        return products;
    }

    public List<Product> getAllProducts()
    {
       
        return products;
    }

    // public static void main(String args[]){
    //     System.out.println(generateProducts.generate());
    // }
}
