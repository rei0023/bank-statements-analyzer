package org.example;

public class HTMLExporter implements ExporterI {

    @Override
    public String export(final SummaryStatistics summaryStatistics) {
        String result = "<!doctype html>";
        result += "<html lang='en'>";
        result += "<head><title>Bank Transactions Report</title></head>";
        result += "<body>";
        result += "<ul>";
        result += "<li>The sum is: " + summaryStatistics.getSum() + "</li>";
        result += "<li>The min is " + summaryStatistics.getMin() + "</li>";
        result += "<li>The max is " + summaryStatistics.getMax() + "</li>";
        result += "<li>The average is " + summaryStatistics.getAverage() + "</li>";
        result += "</ul>";
        result += "</body>";
        result += "</html>";
        return result;
    }
}
