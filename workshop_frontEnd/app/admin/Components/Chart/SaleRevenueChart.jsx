import { useEffect, useRef } from 'react';
import Chart from 'chart.js/auto';

const SaleRevenueChart = () => {
  const chartRef = useRef(null);
  let chartInstance = null;

  useEffect(() => {
    // Dữ liệu cho biểu đồ
    const years = ["2016", "2017", "2018", "2019", "2020", "2021", "2022"];
    const salseData = [15, 30, 55, 45, 70, 65, 85];
    const revenueData = [99, 135, 170, 130, 190, 180, 270];

    // Tạo biểu đồ đường sử dụng Chart.js
    const ctx = chartRef.current.getContext('2d');
    chartInstance = new Chart(ctx, {
      type: 'line', // Loại biểu đồ là đường
      data: {
        labels: years, // Sử dụng các năm làm nhãn trục x
        datasets: [
          {
            label: 'Salse',
            data: salseData,
            backgroundColor: 'rgba(235, 22, 22, .7)',
            fill: true,
          },
          {
            label: 'Revenue',
            data: revenueData,
            backgroundColor: 'rgba(235, 22, 22, .5)',
            fill: true,
          },
        ],
      },
      options: {
        responsive: true,
      },
    });

    // Trả về một hàm để huỷ biểu đồ khi component unmount
    return () => {
      chartInstance.destroy();
    };
  }, []); // Tham số thứ hai là một mảng rỗng, đảm bảo useEffect chỉ chạy một lần sau khi component mount

  return (
    <div>
      <canvas ref={chartRef} width={400} height={250}/>
    </div>
  );
};

export default SaleRevenueChart;
