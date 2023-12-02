// course_preview_card.dart
import 'package:flutter/material.dart';

class CoursePreviewCard extends StatefulWidget {
  final String courseTitle;
  final String instructor;
  final String imageAsset;
  final double rating;
  final String price;
  final String time;
  final String avatarImageAsset;
  final String authorName;

  CoursePreviewCard({
    required this.courseTitle,
    required this.instructor,
    required this.imageAsset,
    required this.rating,
    required this.price,
    required this.time,
    required this.avatarImageAsset,
    required this.authorName,
  });

  @override
  _CoursePreviewCardState createState() => _CoursePreviewCardState();
}

class _CoursePreviewCardState extends State<CoursePreviewCard> {
  bool isFavorite = false;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 10),
      child: Card(
        elevation: 2,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        child: Stack(
          children: [
            Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // Smaller image with added padding to the right
                Padding(
                  padding: EdgeInsets.only(left: 10, right: 5),
                  child: ClipRRect(
                    borderRadius: BorderRadius.horizontal(
                      left: Radius.circular(10),
                      right: Radius.circular(0),
                    ),
                    child: Image.asset(
                      widget.imageAsset,
                      width: 110,
                      height: 150,
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
                // Title, instructor, and rating
                Expanded(
                  child: Padding(
                    padding: EdgeInsets.all(10),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          widget.courseTitle,
                          style: TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                        SizedBox(height: 5),
                        Text(
                          'Instructor: ${widget.instructor}',
                          style: TextStyle(
                            fontSize: 14,
                            color: Colors.grey,
                          ),
                        ),
                        SizedBox(height: 5),
                        Row(
                          children: [
                            Icon(
                              Icons.star,
                              color: Colors.yellow,
                              size: 18,
                            ),
                            SizedBox(width: 5),
                            Text(
                              '${widget.rating}',
                              style: TextStyle(
                                fontSize: 16,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            SizedBox(width: 5),
                            Icon(
                              Icons.access_time,
                              size: 18,
                            ),
                            SizedBox(width: 5),
                            Text(
                              widget.time,
                              style: TextStyle(
                                fontSize: 16,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        ),
                        SizedBox(height: 10),
                        Row(
                          children: [
                            CircleAvatar(
                              backgroundImage: AssetImage(widget.avatarImageAsset),
                              radius: 15,
                            ),
                            SizedBox(width: 5),
                            Text(
                              widget.authorName,
                              style: TextStyle(
                                fontSize: 14,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
            // Heart icon in the top-right corner
            Positioned(
              top: 1,
              right: 1,
              child: IconButton(
                icon: Icon(
                  isFavorite ? Icons.favorite : Icons.favorite_border,
                  color: isFavorite ? Colors.red : Colors.grey,
                ),
                onPressed: () {
                  setState(() {
                    isFavorite = !isFavorite;
                  });
                },
              ),
            ),
            // Price in the bottom-right corner
            Positioned(
              bottom: 5,
              right: 5,
              child: Text(
                '\$${widget.price}',
                style: TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
