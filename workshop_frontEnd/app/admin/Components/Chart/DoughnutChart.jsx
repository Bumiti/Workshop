import { useEffect, useRef } from 'react';
import Chart from 'chart.js/auto';

const DoughnutChart = ({ data }) => {
    const chartRef = useRef(null);

    useEffect(() => {
        const ctx = chartRef.current.getContext('2d');
        const myChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ['A', 'B', 'C', 'D', 'E'],
                datasets: [{
                    data: [12, 19, 3, 5, 2],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                // Các tùy chọn của biểu đồ (nếu cần)
            }
        });

        return () => {
            myChart.destroy();
        };
    }, [data]);

    return <canvas ref={chartRef} width={400} height={400}></canvas>;
};

export default DoughnutChart;