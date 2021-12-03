package ua.edu.ucu.tempseries;

import lombok.Getter;

import java.lang.Math;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    @Getter
    private double[] series;
    private int series_len;
    private int memalloc;
    private final double MINIMUM = -273;

    public TemperatureSeriesAnalysis() {
        this.series = null;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        if(temperatureSeries.length == 0){
            this.series = null;
            return;
        }
        this.series = temperatureSeries;
        this.series_len = series.length;
        this.memalloc = series.length;
        for (int i = 0; i < series_len; i++){
            if (series[i] < MINIMUM){
                throw new InputMismatchException();
            }
        }
    }

    public double average() {
        if (this.series == null){
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (int i = 0; i < series_len; i++){
            sum += series[i];
        }
        return sum / series_len;
    }

    public double deviation() {
        if (this.series == null){
            throw new IllegalArgumentException();
        }
        double sum = 0;
        double mean = average();
        for (int i = 0; i < series_len; i++){
            sum += Math.pow(mean - series[i], 2);
        }
        double var = sum / (series_len - 1);
        return Math.sqrt(var);
    }

    public double min() {
        if (this.series == null){
            throw new IllegalArgumentException();
        }
        double minimum = series[0];
        for (int i = 0; i < series_len; i++){
            if (series[i] < minimum){
                minimum = series[i];
            }
        }
        return minimum;
    }

    public double max() {
        if (this.series == null){
            throw new IllegalArgumentException();
        }
        double[] reverse_sign = new double[series_len];
        int i = 0;
        for(double val : series){
            reverse_sign[i] = -val;
            i++;
        }
        return -(new TemperatureSeriesAnalysis(reverse_sign).min());
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.series == null){
            throw new IllegalArgumentException();
        }
        double absminimum = Math.abs(series[0] - tempValue);
        double minimum = series[0];
        for (int i = 1; i < series_len; i++){
            if ((Math.abs(series[i] - tempValue) < absminimum) || (Math.abs(series[i] - tempValue) == absminimum && series[i] > tempValue)){
                absminimum = Math.abs(series[i]) - tempValue;
                minimum = series[i];
            }
        }
        return minimum;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (this.series == null){
            throw new IllegalArgumentException();
        }
        int res_len = 0;
        for (int i = 0; i < series_len; i++){
            if (series[i] < tempValue) {
                res_len++;
            }
        }
        double[] res = new double[res_len];
        int j = 0;
        for (int i = 0; i < series_len; i++){
            if (series[i] < tempValue) {
                res[j] = series[i];
                j++;
            }
        }
        return res;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (this.series == null){
            throw new IllegalArgumentException();
        }
        int res_len = 0;
        for (int i = 0; i < series_len; i++){
            if (series[i] > tempValue) {
                res_len++;
            }
        }
        double[] res = new double[res_len];
        int j = 0;
        for (int i = 0; i < series_len; i++){
            if (series[i] > tempValue) {
                res[j] = series[i];
                j++;
            }
        }
        return res;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.series == null){
            throw new IllegalArgumentException();
        }
        return new TempSummaryStatistics(this);
    }

    public double addTemps(double... temps) {
        if (this.series == null){
            throw new IllegalArgumentException();
        }

        for (double v : temps) {
            if (v < MINIMUM) {
                throw new InputMismatchException();
            }
        }
        while (temps.length + series_len > memalloc){
            memalloc *= 2;
        }
        double[] temp = series;
        series = new double[memalloc];
        for (int i = 0; i < series_len; i++){
            series[i] = temp[i];
        }
        int j = 0;
        for (int i = series_len; i < series_len + temps.length; i++){
            series[i] = temps[j];
            j++;
        }
        series_len += temps.length;
        return this.average()*series_len;
    }
}
