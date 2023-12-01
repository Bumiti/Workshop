import 'package:flutter/material.dart';

class Pngwing1 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 119,
      height: 140,
      decoration: ShapeDecoration(
        image: DecorationImage(
          image: AssetImage('lib/assets/card.png'),
          fit: BoxFit.fill,
        ),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(96.50),
        ),
      ),
    );
  }
}

class YourTextAndImageWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(16),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                'Get Last Workshop',
                style: TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Colors.white,
                ),
              ),
              SizedBox(height: 6),
              Text(
                'Access largest set of courses',
                style: TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.normal,
                  color: Colors.white,
                ),
              ),
              Text(
                'provided by us on your',
                style: TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.normal,
                  color: Colors.white,
                ),
              ),
              Text(
                'interest',
                style: TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.normal,
                  color: Colors.white,
                ),
              ),
              SizedBox(height: 20),
              Container(
                decoration: BoxDecoration(
                  color: Color.fromARGB(250, 246, 121, 105),
                  borderRadius: BorderRadius.circular(20.0), // Adjust the border radius
                ),
                child: Container(
                  padding: const EdgeInsets.all(8.0),
                  margin:  const EdgeInsets.only( left: 25, right: 25),
                  child: Text(
                    'Get Now',
                    style: TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.w600,
                      color: Colors.white,
                    ),
                  ),
                ),
              ),
            ],
          ),
// Adjust the spacing as needed
          Pngwing1(), // Image on the right
        ],
      ),
    );
  }
}
