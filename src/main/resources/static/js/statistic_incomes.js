function drawPieChart(series) {
    Highcharts.chart('pie_chart', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '<h5>Przychody</h5>',
            align: 'center'
        },
        tooltip: {
            pointFormat: '<b>{point.y} zł</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: series
    });
}

function incomes() {
    $.ajax({
        url: "http://localhost:8080/incomes",
        success: function (result) {
            const x = result.length;
            var series = [];
            var data = [];

            for(var i = 0; i < x; i++) {
                var object = {};
                object.name = result[i][0];
                object.y = result[i][1];
                data.push(object);
            }
            var seriesObject = {
                name: "Wydatki",
                colorByPoint: true,
                data: data
            }
            series.push(seriesObject);
            drawPieChart(series);
        }
    });
}