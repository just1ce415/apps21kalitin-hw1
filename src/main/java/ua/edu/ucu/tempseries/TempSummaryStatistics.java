package ua.edu.ucu.tempseries;


import lombok.Getter;

@Getter
public class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    protected TempSummaryStatistics(TemperatureSeriesAnalysis temperatureSeriesAnalysis){
        this.avgTemp = temperatureSeriesAnalysis.average();
        this.devTemp = temperatureSeriesAnalysis.deviation();
        this.minTemp = temperatureSeriesAnalysis.min();
        this.maxTemp = temperatureSeriesAnalysis.max();
    }
}
