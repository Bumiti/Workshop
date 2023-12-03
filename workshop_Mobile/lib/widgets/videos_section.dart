import 'package:flutter/material.dart';
import 'package:workshop_mobi/screens/course_screen.dart';

class VideoSetion extends StatelessWidget {
   final CourseScreen widget;
  List videoList = [
    'Introduction to Flutter',
    'Installing Flutter on Windows',
    'Setup Emulator on Windows',
    'Creating Our First App',
  ];

  VideoSetion({super.key, required this.widget});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: widget.courseResponses.courseMediaInfos.length,
      physics: NeverScrollableScrollPhysics(),
      shrinkWrap: true,
      itemBuilder: (context, index) {
        return Column(
          children: [
            ListTile(
              tileColor: index == 0
                  ? Color.fromARGB(255, 60, 120, 230)
                  : Color.fromARGB(140, 90, 145, 230),
              contentPadding: EdgeInsets.symmetric(vertical: 10, horizontal: 16),
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(10.0),
              ),
              leading: Container(
                padding: EdgeInsets.all(5),
                decoration: BoxDecoration(
                  color: index == 0 ? Color(0xFF674AEF) : Color(0xFF674AEF).withOpacity(0.6),
                  shape: BoxShape.circle,
                ),
                child: Icon(
                  Icons.play_arrow_rounded,
                  color: Colors.white,
                  size: 30,
                ),
              ),
              title: Text(
                widget.courseResponses.courseMediaInfos[index].title,
                style: TextStyle(
                  color: index == 0 ? Colors.white : Colors.black,
                  fontWeight: FontWeight.bold,
                ),
              ),
              subtitle: Text(
                "20 min 50 sec",
                style: TextStyle(
                  color: index == 0 ? Colors.white : Colors.black,
                ),
              ),
            ),
            SizedBox(height: 10), // Điều chỉnh giá trị này để thêm khoảng cách giữa các ListTile
          ],
        );
      },
    );
  }
}
