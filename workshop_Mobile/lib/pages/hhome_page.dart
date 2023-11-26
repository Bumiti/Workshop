import 'package:flutter/material.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';
import 'package:test/components/my_course.dart';
import 'package:test/components/my_schedule.dart';

class HhomePage extends StatefulWidget {
  const HhomePage({super.key});

  @override
  State<HhomePage> createState() => _ShopPageState();
}

class _ShopPageState extends State<HhomePage> {
  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      backgroundColor: const Color(0xFFD7D7D7),
      body: Column(
        children: [
          Padding(
            padding: EdgeInsets.symmetric(vertical: 25, horizontal: 25),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Text(
                  'Welcome back, Hung',
                  style: TextStyle(fontWeight: FontWeight.bold),
                ),
              ],
            ),
          ),
          Padding(
            padding: EdgeInsets.symmetric(vertical: 10, horizontal: 25),
            child: Column(
              children: [
                Row(
                  children: [
                    Text(
                      'Your trending course',
                    ),
                  ],
                ),
                SizedBox(
                  height: 15,
                ),
                SingleChildScrollView(
                  scrollDirection: Axis.horizontal,
                  child: Row(children: [
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 15),
                      child: MyCourse(),
                    ),
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 15),
                      child: MyCourse(),
                    ),
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 15),
                      child: MyCourse(),
                    ),
                  ]),
                )
              ],
            ),
          ),
          Padding(
            padding: EdgeInsets.symmetric(horizontal: 25, vertical: 8),
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    Text(
                      'Your schedule',
                    ),
                  ],
                ),
                SizedBox(
                  height: 25,
                ),
                SingleChildScrollView(
                  scrollDirection: Axis.horizontal,
                  child: Row(children: [
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 15),
                      child: MySchedule(),
                    ),
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 15),
                      child: MySchedule(),
                    ),
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 15),
                      child: MySchedule(),
                    ),
                  ]),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }
}
