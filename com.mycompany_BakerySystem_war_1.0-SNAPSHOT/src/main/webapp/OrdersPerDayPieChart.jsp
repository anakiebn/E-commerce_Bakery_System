<%@page import="java.time.LocalDate"%>
<%@page import="za.co.groupA.ServiceImpl.OrderServiceImpl"%>
<%@page import="za.co.groupA.Service.OrderService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<!DOCTYPE html>
<html>
    <style>

        .sidenav {
            height: 100%;
            width: 0;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #111;
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 60px;
        }

        .sidenav a {
            padding: 8px 8px 8px 32px;
            text-decoration: none;
            font-size: 25px;
            color: #818181;
            display: block;
            transition: 0.3s;
        }

        .sidenav a:hover {
            color: #f1f1f1;
        }

        .sidenav .closebtn {
            position: absolute;
            top: 0;
            right: 25px;
            font-size: 36px;
            margin-left: 50px;
        }

        #main {
            transition: margin-left .5s;
            padding: 16px;
        }

        @media screen and (max-height: 450px) {
            .sidenav {
                padding-top: 15px;
            }
            .sidenav a {
                font-size: 18px;
            }
        }

    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <body>
        <div id="main">
        <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="#">No. of orders per day</a>
            <a href="#">No. of orders per week</a>
            <a href="#">Trending products</a>
            <a href="#">Trending category</a>
        </div>
        <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; reports</span>

        <button id="showChartButton" onclick="showChart()">Show Chart</button>
        <div id="chartContainer" style="display: none;">
            <canvas id="myChart" style="width:100%;max-width:600px"></canvas>
        </div>

        <canvas id="myChart" style="width:100%;max-width:600px"></canvas>
        </div>
            <%
                int year = 2023;
                int month = 06;
                int dayOfMonth = 1;
                String monthh;

                OrderService os = (OrderServiceImpl) request.getAttribute("orderService");
                List<Integer> i = (ArrayList<Integer>) os.noOfOrdersPerDay(month, year);
                LocalDate date = LocalDate.of(year, month, dayOfMonth);
                List<Integer> activeDays = new ArrayList();
                for (int y = 0; y < date.lengthOfMonth(); y++) {
                    if (date.getDayOfWeek().getValue() <= 5) {
                        System.out.println(date);
                        activeDays.add(date.getDayOfMonth());
                        date = date.plusDays(1);

                        continue;
                    }
                    date = date.plusDays(1);
                }

                System.out.println(i);
            %>
        <script>
            function showChart() {
                const showChartButton = document.getElementById("showChartButton");
                const chartContainer = document.getElementById("chartContainer");

                const xValues = [<%
                    for (int j = 0; j < activeDays.size(); j++) {
                        out.print(activeDays.get(j));
                        if (j < activeDays.size() - 1) {
                            out.print(", ");
                        }
                    }
            %>];
                const yValues = [<%
                    for (int j = 0; j < i.size(); j++) {
                        out.print(i.get(j));
                        if (j < i.size() - 1) {
                            out.print(", ");
                        }
                    }
            %>];
                const barColors = ["yellow", "red", "green", "blue", "orange", "brown", "gray", "black", "purple"];

                new Chart("myChart", {
                    type: "bar",
                    data: {
                        labels: xValues,
                        datasets: [{
                                backgroundColor: barColors,
                                data: yValues
                            }]
                    },
                    options: {
                        legend: {display: false},
                        title: {
                            display: true,
                            text: "Number of Orders per Day"
                        }
                    }
                });
                showChartButton.disabled = true;
                chartContainer.style.display = "block";
            }</script>

        <script>
            function openNav() {
                document.getElementById("mySidenav").style.width = "250px";
                document.getElementById("main").style.marginLeft = "250px";
                document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
            }

            function closeNav() {
                document.getElementById("mySidenav").style.width = "0";
                document.getElementById("main").style.marginLeft = "0";
                document.body.style.backgroundColor = "white";
            }
        </script>

    </body>
</html>
