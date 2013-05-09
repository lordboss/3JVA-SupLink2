function ColumnChart(categories, data){
	var chart;
    var colors = Highcharts.getOptions().colors,
        name = 'Clicks over time';

    function setChart(name, categories, data, color) {
		chart.xAxis[0].setCategories(categories, false);
		chart.series[0].remove(false);
		chart.addSeries({
			name: name,
			data: data,
			color: color || 'white'
		}, false);
		chart.redraw();
    }

    chart = new Highcharts.Chart({
        chart: {
            renderTo: 'columns',
            type: 'column'
        },
        credits: {
        	enabled: false
        },
        title: {
        	text: null
        },
        legend:{
            enabled: false
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            title: {
                text: null
            },
        },
        tooltip: {
            enabled: false
        },
        plotOptions: {
            column: {
                dataLabels: {
                    enabled: true,
                    color: colors[0],
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y +' clicks';
                    }
                }
            }
        },
        series: [{
            name: name,
            data: data,
            color: 'white'
        }],
        exporting: {
            enabled: false
        },
    });
};