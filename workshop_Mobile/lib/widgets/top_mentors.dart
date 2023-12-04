import 'package:flutter/material.dart';

class TopMentorsSection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(top: 20, left: 15, right: 15),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                "Top Mentors",
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
          Container(
            padding: EdgeInsets.only(top: 15), // Adjusted top padding
            height: 130,
            child: ListView(
              scrollDirection: Axis.horizontal,
              children: [
                buildAvatar('Hoàng Nam', 'avatar'),
                buildAvatar('Thanh Hiếu', 'avatar'),
                buildAvatar('Hồng Hân', 'avatar'),
                buildAvatar('Hồng Quân', 'avatar'),
                buildAvatar('Quang Nam', 'avatar'),
                buildAvatar('Thành Trung', 'avatar'),
                // Add more mentor widgets as needed
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget buildAvatar(String name, String filename) {
    return Padding(
      padding: const EdgeInsets.only(right: 10),
      child: Column(
        children: [
          CircleAvatar(
            radius: 30,
            backgroundImage: AssetImage('lib/assets/$filename.jpg'),
          ),
          const SizedBox(
            height: 8,
          ),
          Text(
            name,
            style: TextStyle(
              fontSize: 15,
              fontWeight: FontWeight.w500,
            ),
          )
        ],
      ),
    );
  }
}
