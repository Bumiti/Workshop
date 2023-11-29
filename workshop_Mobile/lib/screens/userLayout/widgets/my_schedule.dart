import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';

class MyStudentSchedule extends StatefulWidget {
  // ignore: use_key_in_widget_constructors
  const MyStudentSchedule({Key? key});

  @override
  State<MyStudentSchedule> createState() => _MyScheduleState();
}

class _MyScheduleState extends State<MyStudentSchedule> {
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
        SizedBox(
           height: screenHeight * 0.4,
          width: screenWidth * 0.9,
          child: TableCalendar(
            locale: 'en_US',
            rowHeight: 38,
            headerStyle:
                const HeaderStyle(formatButtonVisible: false, titleCentered: true),
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
