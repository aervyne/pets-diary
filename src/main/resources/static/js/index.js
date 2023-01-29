function drawLineChartWithMeasurement(date, weight, height) {
    Highcharts.chart("chart", {
        chart: {
            type: 'line',
            width: 500
        },
        title: {
            text: 'Line chart'
        },
        xAxis: {
            categories: date,
            accessibility: {
                description: 'Months of the year'
            }
        },
        series: [{
            data: weight
        }, {
            data: height
        }]
    })
}

const selectElement = document.getElementById('petSelect');
selectElement.addEventListener('change', (event) => {
    $.ajax({
        url: `http://localhost:8080/controlmeasurements/${event.target.value}`,
        type: 'GET',
        dataType: 'json', // added data type
        success: function(petMeasurements) {
            const dates = petMeasurements.map((measurement) => measurement.date);
            const weights = petMeasurements.map((measurement) => measurement.weight);
            const heights = petMeasurements.map((measurement) => measurement.height);
            drawLineChartWithMeasurement(dates, weights, heights)
        }
    });
});