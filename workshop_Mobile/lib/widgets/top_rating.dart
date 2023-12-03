// top_rating_section.dart
import 'package:flutter/material.dart';
import 'package:workshop_mobi/widgets/course_preview_card.dart';

class TopRatingSection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(left: 15, right: 15),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                "Top Rating",
                style: TextStyle(
                  fontSize: 23,
                  fontWeight: FontWeight.w600,
                ),
              ),
              Text(
                "See All",
                style: TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.w500,
                  color: Color(0xFF674AEF),
                ),
              ),
            ],
          ),
          SizedBox(height: 10),
          CoursePreviewCard(
            courseTitle: 'Flutter',
            instructor: 'John Doe',
            imageAsset: 'lib/assets/course_card1.png',
            rating: 5,
            price: '99.99',
            time: '3h',
            authorName: 'Hồng Quân',
            avatarImageAsset: 'lib/assets/avatar.jpg',
          ),
          CoursePreviewCard(
            courseTitle: 'React Native',
            instructor: 'John Doe',
            imageAsset: 'lib/assets/course_card2.png',
            rating: 4.5,
            price: '33.99',
            time: '2h 30min',
            authorName: 'Thanh Hiếu',
            avatarImageAsset: 'lib/assets/avatar.jpg',
          ),
          // You can add more CoursePreviewCard widgets as needed
        ],
      ),
    );
  }
}
