import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';

class MySchedule extends StatefulWidget {
  MySchedule({Key? key});

  @override
  State<MySchedule> createState() => _MyScheduleState();
}

class _MyScheduleState extends State<MySchedule> {
  DateTime today = DateTime.now();

  void _onDaySelected(DateTime day, DateTime focusedDay) {
    setState(() {
      today = day;
    });
  }

  @override
  Widget build(BuildContext context) {
    double screenHeight = MediaQuery.of(context).size.height;
    double screenWidth = MediaQuery.of(context).size.width;

    return Column(
      children: [
        Stack(
          alignment: Alignment.topCenter,
          children: [
            // Container(
            //   width: screenWidth * 0.6,
            //   height: screenHeight * 0.2,
            //   decoration: BoxDecoration(
            //     color: Colors.white,
            //     borderRadius: BorderRadius.circular(16),
            //   ),
            //   child: Padding(
            //     padding: EdgeInsets.symmetric(horizontal: screenWidth * 0.04),
            //     child: Column(
            //       children: [
            //         Text(
            //           '1',
            //           style:
            //               TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
            //         ),
            //       ],
            //     ),
            //   ),
            // ),
            // Container(
            //   width: screenWidth * 0.6,
            //   height: screenHeight * 0.065,
            //   decoration: BoxDecoration(
            //     color: Color.fromARGB(255, 233, 190, 173),
            //     borderRadius: BorderRadius.only(
            //       topLeft: Radius.circular(16),
            //       topRight: Radius.circular(16),
            //     ),
            //   ),
            //   alignment: Alignment.center,
            //   child: Padding(
            //     padding: EdgeInsets.symmetric(
            //         horizontal: screenWidth * 0.02,
            //         vertical: screenHeight * 0.01),
            //     child: Column(
            //       children: [
            //         Align(
            //           alignment: Alignment.centerLeft,
            //           child: Text(
            //             'Guitar class',
            //             style: TextStyle(
            //                 fontSize: 20, fontWeight: FontWeight.bold),
            //           ),
            //         ),
            //       ],
            //     ),
            //   ),
            // ),
          ],
        ),
        Container(
          height: 340,
          width: 400,
          child: TableCalendar(
            locale: 'en_US',
            rowHeight: 43,
            headerStyle:
                HeaderStyle(formatButtonVisible: false, titleCentered: true),
            availableGestures: AvailableGestures.all,
            selectedDayPredicate: (day) => isSameDay(day, today),
            focusedDay: today,
            firstDay: DateTime.utc(2023, 1, 1),
            lastDay: DateTime.utc(2023, 12, 31),
            onDaySelected: _onDaySelected,
          ),
        )
      ],
    );
  }
}
