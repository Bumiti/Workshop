import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:workshop_mobi/components/my_course.dart';
import 'package:workshop_mobi/screens/teacherLayout/widgets/my_schedule.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _ShopPageState();
}

class _ShopPageState extends State<HomePage> {
  double targetHeightFactor = 0.06; // Tỉ lệ bạn muốn sử dụng
  late Future<String?> userName;

  @override
  void initState() {
    super.initState();
    userName = _getUserName();
  }

  Future<String?> _getUserName() async {
      final FlutterSecureStorage storage =  FlutterSecureStorage();
      String? userName = await storage.read(key: 'userName');
      print(userName);
    return userName;
  }

  double convertHeight(double height) {
    return MediaQuery.of(context).size.height * height;
  }

  double convertWidth(double width) {
    return MediaQuery.of(context).size.width * width;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFD7D7D7),
      body: Column(
        children: [
          Padding(
            padding: EdgeInsets.symmetric(
              vertical: convertHeight(0.025),
              horizontal: convertWidth(0.025),
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                FutureBuilder<String?>(
                  future: userName,
                  builder: (context, snapshot) {
                    if (snapshot.connectionState == ConnectionState.done) {
                     
                      return Text(
                        'Welcome back, ${snapshot.data}',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      );
                    } else if (snapshot.hasError) {
                      return Text('Error: ${snapshot.error}');
                    } else {
                      return CircularProgressIndicator();
                    }
                  },
                ),
              ],
            ),
          ),
          Padding(
            padding: EdgeInsets.symmetric(
              vertical: convertHeight(0.01),
              horizontal: convertWidth(0.025),
            ),
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
                  height: convertHeight(0.015),
                ),
                SingleChildScrollView(
                  scrollDirection: Axis.horizontal,
                  child: Row(
                    children: [
                      Padding(
                        padding: EdgeInsets.symmetric(
                          horizontal: convertWidth(0.015),
                        ),
                        child: MyCourse(),
                      ),
                      Padding(
                        padding: EdgeInsets.symmetric(
                          horizontal: convertWidth(0.015),
                        ),
                        child: MyCourse(),
                      ),
                      Padding(
                        padding: EdgeInsets.symmetric(
                          horizontal: convertWidth(0.015),
                        ),
                        child: MyCourse(),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
          Padding(
            padding: EdgeInsets.symmetric(
              horizontal: convertWidth(0.025),
              vertical: convertHeight(0.008),
            ),
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
                  height: convertHeight(0.025),
                ),
                SingleChildScrollView(
                  scrollDirection: Axis.horizontal,
                  child: Row(
                    children: [
                      Padding(
                        padding: EdgeInsets.symmetric(
                          horizontal: convertWidth(0.015),
                        ),
                        child: MySchedule(),
                      ),
                      Padding(
                        padding: EdgeInsets.symmetric(
                          horizontal: convertWidth(0.015),
                        ),
                        child: MySchedule(),
                      ),
                      Padding(
                        padding: EdgeInsets.symmetric(
                          horizontal: convertWidth(0.015),
                        ),
                        child: MySchedule(),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
