function PieChart(data, id, title){

	var chart;
    chart = new Highcharts.Chart({
        chart: {
            renderTo: id,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: title
        },
        credits: {
        	enabled: false
        },
        tooltip: {
        	enabled: false
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    distance: -30,
                    enabled: true,
                    color: '#FFFFFF',
                    connectorColor: '#000000',
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>';
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Clicks',
        }]
    });
    
 	chart.series[0].setData(data);
}