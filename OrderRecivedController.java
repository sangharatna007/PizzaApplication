package com.pizza.shop.PizzaApplication.controller;

import com.pizza.shop.PizzaApplication.model.DateSorter;
import com.pizza.shop.PizzaApplication.model.Item;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.Instant;
import java.util.*;

/**
 * Created by sangharatna.davane on 1/30/2019.
 */

@RestController
public class OrderRecivedController {

    Resource outputResource = new ClassPathResource("/static/output_data_ordered.txt");
    String[] dataArray;

    @GetMapping(path="/getSortedData", produces = "application/json")
       public ResponseEntity<?> getSortedOrderDetails() throws IOException {

        try {
            List<Item> orderList = getSortedDataList("/static/sample_data_ordered.txt");
            writeDataToFile(orderList);
            return new ResponseEntity<>(orderList, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public List<Item> getSortedDataList(String filePath) throws IOException {
        List<Item> orderList = new ArrayList<>();
        Resource resource = new ClassPathResource(filePath);

        File file = resource.getFile();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine(); // read the first line
        String st = null;
        st = reader.readLine();
        while ((st = reader.readLine()) != null) {
            Item order = new Item();
            dataArray = st.split("\t\t");
            order.setOrder(dataArray[0]);
            order.setTime(Long.valueOf(dataArray[1]));
            orderList.add(order);
        }
        Collections.sort(orderList, new DateSorter());

        return orderList;
    }

    public void writeDataToFile(List<Item> orderList) throws IOException {
        
        File filepath= outputResource.getFile();
        FileOutputStream fileOut = new FileOutputStream(filepath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

        orderList.forEach((Item order) -> {
            try {
                objectOut.writeObject("order  " + order.getOrder() + "  at  :  " + Instant.ofEpochSecond(order.getTime()) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        objectOut.close();
    }
}

