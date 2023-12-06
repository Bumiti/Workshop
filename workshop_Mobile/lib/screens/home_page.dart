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
    Color(0xFFFFCF2F),
    Color(0XFF6FE08D),
    Color(0XFF61BDFD),
    Color(0XFFFC7F7F),
    Color(0XFFCB84FB),
    Color(0XFF78E667),
  ];

  List<Icon> catIcons = [
    Icon(Icons.category, color: Colors.white, size: 30),
    Icon(Icons.video_library, color: Colors.white, size: 30),
    Icon(Icons.assessment, color: Colors.white, size: 30),
    Icon(Icons.store, color: Colors.white, size: 30),
    Icon(Icons.play_circle_fill, color: Colors.white, size: 30),
    Icon(Icons.emoji_events, color: Colors.white, size: 30),
  ];

  @override
  Widget build(BuildContext context) {
    final apiService = ApiService();

    // Use FutureBuilder to handle asynchronous operations
    return Scaffold(
      body: FutureBuilder<List<CourseResponses>>(
        
        future: apiService.listworkshop(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
              
            return const CircularProgressIndicator();
          } else if (snapshot.hasError) {
            // If there's an error, display an error message
            return Text('Error: ${snapshot.error}');
          } else {

            return ListView(
              children: [
                // card
                Container(
                  padding: const EdgeInsets.only(top: 20, left: 15, right: 15),
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
                              borderRadius: BorderRadius.circular(22),
                            ),
                          ),
                          child: YourTextAndImageWidget(),
                        ),
                      ),
                    ],
                  ),
                ),

                // title courses
                CoursesSection(
                  
                  catNames: catNames,
                  catColors: catColors,
                  catIcons: catIcons,
                  workshopList: snapshot.hasData
                      ? snapshot.data!
                      : [],
                ),

                // top mentor
                TopMentorsSection(),

                // top rating
                TopRatingSection(),
              ],
            );
          }
        },
      ),
    );
  }
}
