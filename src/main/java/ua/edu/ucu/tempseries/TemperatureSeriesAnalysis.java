package ua.edu.ucu.tempseries;

import lombok.Getter;

import java.lang.Math;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    @Getter
    private double[] series;
    private int seriesLen;
    private int memalloc;
    private final double Minimum = -273;

    public TemperatureSeriesAnalysis() {
        this.series = null;
    }

    public TemperatureSeriesAnalysis(final double[] temperatureSeries) {
        if (temperatureSeries.length == 0) {
            this.series = null;
            return;
        }
        this.series = temperatureSeries;
        this.seriesLen = series.length;
        this.memalloc = series.length;
        for (int i = 0; i < seriesLen; i++) {
            if (series[i] < Minimum) {
                throw new InputMismatchException();
            }
        }
    }

    public double average() {
        if (this.series == null) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (int i = 0; i < seriesLen; i++) {
            sum += series[i];
        }
        return sum / seriesLen;
    }

    public double deviation() {
        if (this.series == null) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        double mean = average();
        for (int i = 0; i < seriesLen; i++) {
            sum += Math.pow(mean - series[i], 2);
        }
        double var = sum / (seriesLen - 1);
        return Math.sqrt(var);
    }

    public double min() {
        if (this.series == null) {
            throw new IllegalArgumentException();
        }
        double minimum = series[0];
        for (int i = 0; i < seriesLen; i++) {
            if (series[i] < minimum) {
                minimum = series[i];
            }
        }
        return minimum;
    }

    public double max() {
        if (this.series == null) {
            throw new IllegalArgumentException();
        }
        double[] reverseSign = new double[seriesLen];
        int i = 0;
        for (double val : series) {
            reverseSign[i] = -val;
            i++;
        }
        return -(new TemperatureSeriesAnalysis(reverseSign).min());
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(final double tempValue) {
        if (this.series == null) {
            throw new IllegalArgumentException();
        }
        double absminimum = Math.abs(series[0] - tempValue);
        double minimum = series[0];
        for (int i = 1; i < seriesLen; i++) {
            if ((Math.abs(series[i] - tempValue) < absminimum)
                    || (Math.abs(series[i] - tempValue)
                    == absminimum && series[i] > tempValue)) {
                absminimum = Math.abs(series[i]) - tempValue;
                minimum = series[i];
            }
        }
        return minimum;
    }

    public double[] findTempsLessThen(final double tempValue) {
        if (this.series == null) {
            throw new IllegalArgumentException();
        }
        int resLen = 0;
        for (int i = 0; i < seriesLen; i++) {
            if (series[i] < tempValue) {
                resLen++;
            }
        }
        double[] res = new double[resLen];
        int j = 0;
        for (int i = 0; i < seriesLen; i++) {
            if (series[i] < tempValue) {
                res[j] = series[i];
                j++;
            }
        }
        return res;
    }

    public double[] findTempsGreaterThen(final double tempValue) {
        if (this.series == null) {
            throw new IllegalArgumentException();
        }
        int resLen = 0;
        for (int i = 0; i < seriesLen; i++) {
            if (series[i] > tempValue) {
                resLen++;
            }
        }
        double[] res = new double[resLen];
        int j = 0;
        for (int i = 0; i < seriesLen; i++) {
            if (series[i] > tempValue) {
                res[j] = series[i];
                j++;
            }
        }
        return res;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.series == null) {
            throw new IllegalArgumentException();
        }
        return new TempSummaryStatistics(this);
    }

    public double addTemps(double... temps) {
        if (this.series == null) {
            throw new IllegalArgumentException();
        }

        for (double v : temps) {
            if (v < Minimum) {
                throw new InputMismatchException();
            }
        }
        while (temps.length + seriesLen > memalloc) {
            memalloc *= 2;
        }
        double[] temp = series;
        series = new double[memalloc];
        for (int i = 0; i < seriesLen; i++) {
            series[i] = temp[i];
        }
        int j = 0;
        for (int i = seriesLen; i < seriesLen + temps.length; i++) {
            series[i] = temps[j];
            j++;
        }
        seriesLen += temps.length;
        return this.average() * seriesLen;
    }
}
