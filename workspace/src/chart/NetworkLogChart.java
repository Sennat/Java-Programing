/* ************************************************************************************
 * Copyright (C) Oct 10, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 11:10:38 PM.
 * ************************************************************************************
 */

package chart;

import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class NetworkLogChart {
 
    public VBox createChart(VBox chart_vBox) {
    	final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Data Protocol");       
        
        final LineChart<String, Number> lineChart = 
                new LineChart<String, Number>(xAxis,yAxis);
                
        lineChart.setTitle("Data Link Network Monitoring");
                                
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Data-Link Course Project");
        
        series1.getData().add(new XYChart.Data("Resent", 8));
        series1.getData().add(new XYChart.Data("Resived", 2));
        series1.getData().add(new XYChart.Data("Error", 10));
        series1.getData().add(new XYChart.Data("Acknowladge", 15));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("series1");
        series2.getData().add(new XYChart.Data("Jan", 44));
        series2.getData().add(new XYChart.Data("Feb", 35));
        series2.getData().add(new XYChart.Data("Mar", 36));
        
      //Creating a VBox object  
        chart_vBox = new VBox(lineChart);
        chart_vBox.setMaxSize(760, 300);
        chart_vBox.setPadding(new Insets(10, 10, 55, 10));
        lineChart.getData().addAll(series1, series2);
        lineChart.setStyle("-fx-fill: #d8bfd833; "
        		+ "-fx-background-color: #e9967a, white; "
        		+ "-fx-stroke: #e9967a; "
        		+ "-fx-stroke-width: 2px;");
        
		return chart_vBox;
    }

}
