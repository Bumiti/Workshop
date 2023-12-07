// ignore_for_file: unnecessary_string_interpolations

import 'package:flutter/material.dart';
import 'package:workshop_mobi/model/workshopResponses.dart';
import 'package:workshop_mobi/screens/course_screen.dart';

class CoursesSection extends StatelessWidget {
  final List catNames;
  final List<Color> catColors;
  final List<Icon> catIcons;
  
  final List<CourseResponses> workshopList;

  CoursesSection({
    required this.catNames,
    required this.catColors,
    required this.catIcons,
    required this.workshopList,
  });

  @override
  Widget build(BuildContext context) {
    final screenHeight = MediaQuery.of(context).size.height;
    final screenWidth = MediaQuery.of(context).size.width;

    return Padding(
      padding: EdgeInsets.only(top: screenHeight * 0.025, left: screenWidth * 0.05, right: screenWidth * 0.05),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          GridView.builder(
            itemCount: catNames.length,
            shrinkWrap: true,
            physics: NeverScrollableScrollPhysics(),
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 3,
              childAspectRatio: 1.1,
            ),
            itemBuilder: (context, index) {
              return Column(
                children: [
                  Container(
                    height: screenWidth * 0.15,
                    width: screenWidth * 0.15,
                    decoration: BoxDecoration(
                      color: catColors[index],
                      shape: BoxShape.circle,
                    ),
                    child: Center(child: catIcons[index]),
                  ),
                  SizedBox(height: screenHeight * 0.01),
                  Text(
                    catNames[index],
                    style: TextStyle(
                      fontSize: screenHeight * 0.022,
                      fontWeight: FontWeight.w500,
                      color: Colors.black.withOpacity(0.7),
                    ),
                  )
                ],
              );
            },
          ),
          SizedBox(height: screenHeight * 0.01),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                "Workshop",
                style: TextStyle(
                  fontSize: screenHeight * 0.028,
                  fontWeight: FontWeight.w600,
                ),
              ),
              Text(
                "See All",
                style: TextStyle(
                  fontSize: screenHeight * 0.022,
                  fontWeight: FontWeight.w500,
                  color: Color(0xFF674AEF),
                ),
              ),
            ],
          ),
          SizedBox(
            height: screenHeight * 0.015,
          ),
          SizedBox(
            height: screenHeight * 0.37,
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: workshopList.length,
              itemBuilder: (context, index) {
                return InkWell(
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => CourseScreen(workshopList[index]),
                      ),
                    );
                  },
                  child: Container(
                    margin: EdgeInsets.only(right: screenWidth * 0.02),
                    padding: EdgeInsets.symmetric(vertical: screenHeight * 0.04, horizontal: screenWidth * 0.02),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(screenWidth * 0.04),
                      color: const Color.fromARGB(94, 94, 94, 94),
                    ),
                    child: Column(
                      children: [
                        Padding(
                          padding: EdgeInsets.all(screenWidth * 0.02),
                          child: Image.network(
                            "${workshopList[index].courseMediaInfos[0].urlImage}",
                            width: screenWidth * 0.2,
                            height: screenWidth * 0.2,
                          ),
                        ),
                        SizedBox(height: screenHeight * 0.01),
                        Text(
                          workshopList[index].name,
                          style: TextStyle(
                            fontSize: screenHeight * 0.028,
                            fontWeight: FontWeight.w600,
                            color: Colors.black.withOpacity(0.6),
                          ),
                        ),
                        SizedBox(height: screenHeight * 0.01),
                        Text(
                          "55 Videos",
                          style: TextStyle(
                            fontSize: screenHeight * 0.022,
                            fontWeight: FontWeight.w500,
                          ),
                        ),
                      ],
                    ),
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
