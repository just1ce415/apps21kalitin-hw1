package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionMin(){
        double[] series = {};
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(series);

        temperatureSeriesAnalysis.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionMax(){
        double[] series = {};
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(series);

        temperatureSeriesAnalysis.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionClosestToZero(){
        double[] series = {};
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(series);

        temperatureSeriesAnalysis.findTempClosestToZero();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionClosestToValue(){
        double[] series = {};
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(series);

        temperatureSeriesAnalysis.findTempClosestToValue(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionLessThan(){
        double[] series = {};
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(series);

        temperatureSeriesAnalysis.findTempsLessThen(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionGreaterThan(){
        double[] series = {};
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(series);

        temperatureSeriesAnalysis.findTempsGreaterThen(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionDeviation(){
        double[] series = {};
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(series);

        temperatureSeriesAnalysis.deviation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionSummary(){
        double[] series = {};
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(series);

        temperatureSeriesAnalysis.summaryStatistics();
    }

    @Test(expected = InputMismatchException.class)
    public void testMinTemp(){
        double[] series = {0.0, 1.1, -274};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(series);
    }

    @Test
    public void testMin(){
        double[] series = {-5.6, -99.88, 56.98, 20.4, 1.999, -35.1, 30.0, 100.111, -1.999, -20.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(series);

        assertEquals(-99.88, seriesAnalysis.min(), 0.00001);
    }

    @Test
    public void testMax(){
        double[] series = {-5.6, -99.88, 56.98, 20.4, 1.999, -35.1, 30.0, 100.111, -1.999, -20.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(series);

        assertEquals(100.111, seriesAnalysis.max(), 0.00001);
    }

    @Test
    public void testDev(){
        double[] series = {-5.6, -99.88, 56.98, 20.4, 1.999, -35.1, 30.0, 100.111, -1.999, -20.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(series);

        assertEquals(53.78176, seriesAnalysis.deviation(), 0.01);
    }

    @Test
    public void testClosestToZero(){
        double[] series = {-5.6, -99.88, 56.98, 20.4, 1.999, -35.1, 30.0, 100.111, -1.999, -20.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(series);

        assertEquals(1.999, seriesAnalysis.findTempClosestToZero(), 0.0001);
    }

    @Test
    public void testClosestToVal(){
        double[] series = {-5.6, -99.88, 56.98, 20.4, 1.999, -35.1, 30.0, 100.111, -1.999, -20.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(series);

        assertEquals(20.4, seriesAnalysis.findTempClosestToValue(20), 0.0001);
    }

    @Test
    public void testLessThan(){
        double[] series = {-5.6, -99.88, 56.98, 20.4, 1.999, -35.1, 30.0, 100.111, -1.999, -20.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(series);

        double[] res = seriesAnalysis.findTempsLessThen(-20);
        assertEquals(-35.1, res[1], 0.0001);
        assertEquals(-99.88, res[0], 0.0001);
    }

    @Test
    public void testGreaterThan(){
        double[] series = {-5.6, -99.88, 56.98, 20.4, 1.999, -35.1, 30.0, 100.111, -1.999, -20.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(series);

        double[] res = seriesAnalysis.findTempsGreaterThen(56);
        assertEquals(100.111, res[1], 0.0001);
        assertEquals(56.98, res[0], 0.0001);
    }

    @Test
    public void testSummary(){
        double[] series = {-5.6, -99.88, 56.98, 20.4, 1.999, -35.1, 30.0, 100.111, -1.999, -20.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(series);

        TempSummaryStatistics summaryStatistics = new TempSummaryStatistics(seriesAnalysis);
        assertEquals(4.6511, summaryStatistics.getAvgTemp(), 0.01);
        assertEquals(53.78176, summaryStatistics.getDevTemp(), 0.01);
        assertEquals(-99.88, summaryStatistics.getMinTemp(), 0.0001);
        assertEquals(100.111, summaryStatistics.getMaxTemp(), 0.0001);
    }

    @Test
    public void testAddTemps(){
        double[] series = {-5.6, -99.88, 56.98, 20.4, 1.999, -35.1, 30.0, 100.111, -1.999, -20.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(series);

        double res = seriesAnalysis.addTemps(1.2, 1.3, 1.5);
        assertEquals(50.511, res, 0.0001);
        assertEquals(-20.4, seriesAnalysis.getSeries()[9], 0.0001);
        assertEquals(1.3, seriesAnalysis.getSeries()[11], 0.0001);
    }
}
