import 'package:flutter/material.dart';

class InputPositionedWidget extends StatelessWidget {
  final TextEditingController textEditingController;
  final Size screenSize;
  InputPositionedWidget({
    super.key,
    required this.screenSize,
    required this.textEditingController,
  });

  @override
  Widget build(BuildContext context) {
    return Positioned(
      left: screenSize.width * 0.15, // 65 / screenSize.width
      top: screenSize.height * 0.38, // 357 / screenSize.height
      child: Container(
        width: screenSize.width * 0.698, // 300 / screenSize.width
        padding: EdgeInsets.symmetric(
          horizontal: screenSize.width * 0.023, // 10 / screenSize.width
          vertical: screenSize.height * 0.015, // 14 / screenSize.height
        ),
        decoration: ShapeDecoration(
          shape: RoundedRectangleBorder(
            side: BorderSide(
                width: screenSize.width * 0.002, // 0.80 / screenSize.width
                color: const Color(0xFFDEDEDE)),
            borderRadius: BorderRadius.circular(
                screenSize.width * 0.012), // 5 / screenSize.width
          ),
        ),
        child: Row(
          mainAxisSize: MainAxisSize.min,
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Container(
              padding: EdgeInsets.symmetric(
                vertical: screenSize.height * 0.002, // 2 / screenSize.height
              ),
              decoration: const BoxDecoration(color: Colors.white),
              child: Row(
                mainAxisSize: MainAxisSize.min,
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Center(
                    child: TextFormField(
                      textAlign: TextAlign.center,
                      controller: textEditingController,
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: screenSize.width * 0.028,
                        fontFamily: 'SVN-Effra',
                        fontWeight: FontWeight.w300,
                        height: 0,
                      ),
                      decoration: InputDecoration(
                        border: InputBorder.none,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
