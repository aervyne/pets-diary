function drawLineChartWithMeasurement(date, weight, height) {
    Highcharts.chart("chart", {
        chart: {
            type: 'line',
            width: 800,
            height: 500
        },
        title: {
            text: ''
        },
        xAxis: {
            categories: date,
            accessibility: {
                description: 'Months of the year'
            }
        },
        yAxis: {
            title : {
                text : '<b>Waga [kg] / Wzrost [cm]</b>'
            }
        },
        series: [{
            data: weight,
            name: "Waga"
        }, {
            data: height,
            name: "Wzrost"
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