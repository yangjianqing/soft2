<template>
  <div class="app-container">
    <div class="background">
      <div id="titleChart">销售大数据可视化页面</div>
      <!--左右上角-->
      <div class="bar_pieChart">
        <!-- 动态柱状图_左上角 -->
        <div id="barChart"/>
        <!-- 花瓣饼状图图_右上角 -->
        <div id="Big_pieChart"/>
      </div>

      <!--中间-->
      <div class="middle-container">
        <!-- 爱心图_中间 -->
        <div id="loveChart"/>
      </div>

      <!--左右下角-->
      <div class="side-container">
        <!-- 折线图_左下角 -->
        <div id="lineChart"/>
        <!-- 折线图_面积图_右下角 -->
        <div id="areaChart"/>
      </div>
    </div>
  </div>
</template>

<script>
import {  } from "@/api/system/bigdata";
import echarts from 'echarts';

export default {
  name: "Bigdata",
  data() {
    return {};
  },
  created() {

  },
  mounted() {
    this.background();
    this.barChart();
    this.Big_pieChart();
    this.loveChart();
    this.lineChart();
    this.areaChart();
  },
  methods: {
    //销售大数据可视化背景
    background() {

    },

    //动态柱状图
    barChart() {
      // 获取 echarts 实例
      let barChart = echarts.init(document.getElementById("barChart"));

      let data = [];
      for (let i = 0; i < 5; ++i) {
        data.push(Math.round(Math.random() * 200));
      }

      let option = {
        // x轴数据
        xAxis: {
          max: 'dataMax'
        },
        // y轴数据
        yAxis: {
          type: 'category',
          data: ['A', 'B', 'C', 'D', 'E'],
          inverse: true,
          animationDuration: 300,
          animationDurationUpdate: 300,
          max: 2
        },
        series: [
          {
            realtimeSort: true,
            name: 'X',
            type: 'bar',
            data: data,
            label: {
              show: true,
              position: 'right',
              valueAnimation: true
            }
          }
        ],
        legend: {
          show: true
        },
        animationDuration: 3000,
        animationDurationUpdate: 3000,
        animationEasing: 'linear',
        animationEasingUpdate: 'linear'
      };

      function update() {
        const data = option.series[0].data;
        for (let i = 0; i < data.length; ++i) {
          if (Math.random() > 0.9) {
            data[i] += Math.round(Math.random() * 2000);
          } else {
            data[i] += Math.round(Math.random() * 200);
          }
        }
        barChart.setOption(option);
      }

      setTimeout(function () {
        barChart.setOption(option);
        setInterval(update, 3000);
      }, 1000);
    },

    //饼状图图
    Big_pieChart() {
      // 获取 echarts 实例
      let Big_pieChart = echarts.init(document.getElementById("Big_pieChart"));

      let data = [];
      for (let i = 0; i < 5; ++i) {
        data.push(Math.round(Math.random() * 200));
      }

      let option = {
        series: [
          {
            type: 'pie',
            data: [
              {
                value: 100,
                name: 'A'
              },
              {
                value: 200,
                name: 'B'
              },
              {
                value: 300,
                name: 'C'
              },
              {
                value: 400,
                name: 'D'
              },
              {
                value: 500,
                name: 'E'
              }
            ],
            roseType: 'area'
          }
        ]
      };
      setTimeout(function () {
        Big_pieChart.setOption(option);
      }, 1000);
    },

    //折线图
    lineChart() {
      // 获取 echarts 实例
      let lineChart = echarts.init(document.getElementById("lineChart"));

      let data = [];
      for (let i = 0; i < 5; ++i) {
        data.push(Math.round(Math.random() * 200));
      }

      let option = {
        xAxis: {
          data: ['A', 'B', 'C', 'D', 'E']
        },
        yAxis: {},
        series: [
          {
            data: [10, 22, 28, 23, 19],
            type: 'line',
            smooth: true
          }
        ]
      };

      setTimeout(function () {
        lineChart.setOption(option);
      }, 1000);
    },

    //折线图_面积图
    areaChart() {
      // 获取 echarts 实例
      let areaChart = echarts.init(document.getElementById("areaChart"));

      let data = [];
      for (let i = 0; i < 5; ++i) {
        data.push(Math.round(Math.random() * 200));
      }

      let option = {
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            areaStyle: {}
          }
        ]
      };

      setTimeout(function () {
        areaChart.setOption(option);
      }, 1000);
    },

    //爱心图
    loveChart() {
      // 获取 echarts 实例
      let loveChart = echarts.init(document.getElementById("loveChart"));

      let data = [];
      for (let i = 0; i <= 100; i++) {
        let theta = (i / 100) * 360;
        let r = 5 * (1 + Math.sin((theta / 180) * Math.PI));
        data.push([r, theta]);
      }

      let option = {
        title: {
          text: ''
        },
        legend: {
          data: ['line']
        },
        polar: {},
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        angleAxis: {
          type: 'value',
          startAngle: 0
        },
        radiusAxis: {},
        series: [
          {
            coordinateSystem: 'polar',
            name: 'line',
            type: 'line',
            data: data
          }
        ]
      };

      setTimeout(function () {
        loveChart.setOption(option);
      }, 1000);
    }
  }
};
</script>

<style scoped>
.background {
  height: 100%;
  width: 100%;
}

#titleChart {
  font-size: 1.475rem;
  text-align: center; /* 水平居中 */
  line-height: 1rem;
  font-weight: bold; /* 设置为加粗 */
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}

#barChart, #lineChart, #Big_pieChart, #areaChart, #loveChart {
  width: 500px;
  height: 300px;
}

.bar_pieChart, .side-container {
  display: flex;
  justify-content: space-between; /* 将中间大图放置在中心位置 */
  align-items: flex-start; /* 将中间大图放置在中心位置 */
}

.middle-container {
  height: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
