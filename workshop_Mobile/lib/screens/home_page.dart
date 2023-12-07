import 'package:flutter/material.dart';
import 'package:workshop_mobi/api/api_service.dart';
import 'package:workshop_mobi/model/workshopResponses.dart';
import 'package:workshop_mobi/widgets/card_section.dart';
import 'package:workshop_mobi/widgets/title_course.dart';
import 'package:workshop_mobi/widgets/top_mentors.dart';
import 'package:workshop_mobi/widgets/top_rating.dart';

class PublicHomeLanding extends StatefulWidget {
  const PublicHomeLanding({Key? key});

  @override
  State<PublicHomeLanding> createState() => _HomePageState();
}

class _HomePageState extends State<PublicHomeLanding> {
  List catNames = [
    "Category",
    'Classes',
    'Free Course',
    'BookStore',
    'Live Course',
    'LeaderBoard',
  ];
  List<Color> catColors = [
    const Color(0xFFFFCF2F),
    const Color(0XFF6FE08D),
    const Color(0XFF61BDFD),
    const Color(0XFFFC7F7F),
    const Color(0XFFCB84FB),
    const Color(0XFF78E667),
  ];

  List<Icon> catIcons = [
    const Icon(Icons.category, color: Colors.white, size: 30),
    const Icon(Icons.video_library, color: Colors.white, size: 30),
    const Icon(Icons.assessment, color: Colors.white, size: 30),
    const Icon(Icons.store, color: Colors.white, size: 30),
    const Icon(Icons.play_circle_fill, color: Colors.white, size: 30),
    const Icon(Icons.emoji_events, color: Colors.white, size: 30),
  ];

  @override
  Widget build(BuildContext context) {
    final apiService = ApiService();

    // Sử dụng MediaQuery để lấy thông tin kích thước màn hình
    final screenHeight = MediaQuery.of(context).size.height;
    final screenWidth = MediaQuery.of(context).size.width;

    return Scaffold(
      body: FutureBuilder<List<CourseResponses>>(
        future: apiService.listworkshop(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const CircularProgressIndicator();
          } else if (snapshot.hasError) {
            return Text('Lỗi: ${snapshot.error}');
          } else if (snapshot.data == null) {
            return Text('Dữ liệu là null');
          } else {
            return ListView(
              children: [
                Container(
                  // Sử dụng kích thước màn hình để điều chỉnh padding
                  padding: EdgeInsets.only(
                    top: screenHeight * 0.04, // 4% của chiều cao màn hình
                    left: screenWidth * 0.05, // 5% của chiều rộng màn hình
                    right: screenWidth * 0.05,
                  ),
                  child: Row(
                    children: [
                      Expanded(
                        flex: 1,
                        child: Container(
                          clipBehavior: Clip.antiAlias,
                          decoration: ShapeDecoration(
                            gradient: const LinearGradient(
                              begin: Alignment(0.00, -1.00),
                              end: Alignment(0, 1),
                              colors: [Color(0xFF5769F8), Color(0xFF5769F8)],
                            ),
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(20),
                            ),
                          ),
                          child: YourTextAndImageWidget(),
                        ),
                      ),
                    ],
                  ),
                ),
                CoursesSection(
                  catNames: catNames,
                  catColors: catColors,
                  catIcons: catIcons,
                  workshopList: snapshot.data!,
                ),
                TopMentorsSection(),
                TopRatingSection(),
              ],
            );
          }
        },
      ),
    );
  }
}
